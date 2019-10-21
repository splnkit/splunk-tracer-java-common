package com.splunk.tracer.shared;

import com.splunk.tracer.transport.KeyValue;
import com.splunk.tracer.transport.Log;
import com.splunk.tracer.transport.Span.SpBuilder;

import java.util.HashMap;
import java.util.Map;

public class Span implements io.opentracing.Span {

    static final String LOG_KEY_EVENT = "event";
    static final String LOG_KEY_MESSAGE = "message";

    private final Object mutex = new Object();
    private final AbstractTracer tracer;
    private final long startTimestampRelativeNanos;
    private final SpBuilder grpcSpan;

    private SpanContext context;

    Span(AbstractTracer tracer, SpanContext context, SpBuilder grpcSpan, long startTimestampRelativeNanos) {
        this.context = context;
        this.tracer = tracer;
        this.grpcSpan = grpcSpan;
        this.startTimestampRelativeNanos = startTimestampRelativeNanos;

        if (tracer != null && tracer.metaEventLoggingEnabled && Util.IsNotMetaSpan(this)) {
            tracer.buildSpan(SplunkTracingConstants.MetaEvents.SpanStartOperation)
                    .ignoreActiveSpan()
                    .withTag(SplunkTracingConstants.MetaEvents.MetaEventKey, true)
                    .withTag(SplunkTracingConstants.MetaEvents.SpanIdKey, context.getSpanId())
                    .withTag(SplunkTracingConstants.MetaEvents.TraceIdKey, context.getTraceId())
                    .start()
                    .finish();
        }
    }

    @Override
    public SpanContext context() {
        return context;
    }

    @Override
    public void finish() {
        finish(nowMicros());
    }

    @Override
    public void finish(long finishTimeMicros) {
        if (tracer.metaEventLoggingEnabled && Util.IsNotMetaSpan(this)) {
            tracer.buildSpan(SplunkTracingConstants.MetaEvents.SpanFinishOperation)
                    .ignoreActiveSpan()
                    .withTag(SplunkTracingConstants.MetaEvents.MetaEventKey, true)
                    .withTag(SplunkTracingConstants.MetaEvents.SpanIdKey, context.getSpanId())
                    .withTag(SplunkTracingConstants.MetaEvents.TraceIdKey, context.getTraceId())
                    .start()
                    .finish();
        }
        synchronized (mutex) {
            grpcSpan.setDurationMicros(durationMicros(finishTimeMicros));
            tracer.addSpan(grpcSpan.build());
        }
    }

    @Override
    public Span setTag(String key, String value) {
        if (key == null || value == null) {
            tracer.debug("key (" + key + ") or value (" + value + ") is null, ignoring");
            return this;
        }
        synchronized (mutex) {
            grpcSpan.addTags(KeyValue.KeyValueBuilder().setKey(key).setStringValue(value).build());
        }
        return this;
    }

    @Override
    public Span setTag(String key, boolean value) {
        if (key == null) {
            tracer.debug("key is null, ignoring");
            return this;
        }
        synchronized (mutex) {
            grpcSpan.addTags(KeyValue.KeyValueBuilder().setKey(key).setBoolValue(value).build());
        }
        return this;
    }

    @Override
    public Span setTag(String key, Number value) {
        if (key == null || value == null) {
            tracer.debug("key (" + key + ") or value (" + value + ") is null, ignoring");
            return this;
        }
        synchronized (mutex) {
            if (value instanceof Long || value instanceof Integer) {
                grpcSpan.addTags(KeyValue.KeyValueBuilder().setKey(key).setIntValue(value.longValue()).build());
            } else if (value instanceof Double || value instanceof Float) {
                grpcSpan
                    .addTags(KeyValue.KeyValueBuilder().setKey(key).setDoubleValue(value.doubleValue()).build());
            } else {
                grpcSpan
                    .addTags(KeyValue.KeyValueBuilder().setKey(key).setStringValue(value.toString()).build());
            }
        }
        return this;
    }

    @Override
    public <T> Span setTag(io.opentracing.tag.Tag<T> tag, T value) {
        if (tag == null || value == null) {
            tracer.debug("tag (" + tag + ") or value (" + value + ") is null, ignoring");
            return this;
        }
        // No lock needed, as Tag ought to invoke one of the other setTag() overloads.
        tag.set(this, value);
        return this;
    }

    @Override
    public synchronized String getBaggageItem(String key) {
        return context.getBaggageItem(key);
    }

    @Override
    public synchronized Span setBaggageItem(String key, String value) {
        context = context.withBaggageItem(key, value);
        return this;
    }

    public synchronized Span setOperationName(String operationName) {
        grpcSpan.setOperationName(operationName);
        return this;
    }

    public Span setComponentName(String componentName) {
        if (componentName == null) {
            tracer.debug("componentName is null, ignoring");
            return this;
        }
        return setTag(SplunkTracingConstants.Tags.COMPONENT_NAME_KEY, componentName);
    }

    @SuppressWarnings("WeakerAccess")
    public void close() {
        finish();
    }

    public AbstractTracer getTracer() {
        return tracer;
    }

    public final Span log(Map<String, ?> fields) {
        return log(nowMicros(), fields);
    }

    @Override
    public final Span log(long timestampMicros, Map<String, ?> fields) {
        com.splunk.tracer.transport.Log.LogBuilder log = Log.LogBuilder()
            .setTimestamp(Util.epochTimeMicrosToProtoTime(timestampMicros));
        for (Map.Entry<String, ?> kv : fields.entrySet()) {
            String key = kv.getKey();
            Object value = kv.getValue();

            if (key == null) {
                continue; // There's not much we can do here.
            }
            if (value == null) {
                value = ""; // Fallback
            }

            final KeyValue.KeyValueBuilder outKV = KeyValue.KeyValueBuilder().setKey(key);
            final Object inValue = value;

            if (inValue instanceof String) {
                outKV.setStringValue((String)inValue);
            } else if (inValue instanceof Number) {
                if (inValue instanceof Long || inValue instanceof Integer) {
                    outKV.setIntValue(((Number) inValue).longValue());
                } else if (inValue instanceof Double || inValue instanceof Float) {
                    outKV.setDoubleValue(((Number) inValue).doubleValue());
                } else {
                    outKV.setStringValue(inValue.toString());
                }
            } else if (inValue instanceof Boolean) {
                outKV.setBoolValue((Boolean)inValue);
            } else {
                outKV.setJsonValue(Span.stringToJSONValue(inValue.toString()));
            }
            log.addFields(outKV.build());
        }

        synchronized (mutex) {
            grpcSpan.addLogs(log.build());
        }
        return this;
    }

    @Override
    public Span log(String message) {
        return log(nowMicros(), message, null);
    }

    @Override
    public Span log(long timestampMicroseconds, String message) {
        return log(timestampMicroseconds, message, null);
    }

    private Span log(long timestampMicroseconds, String message, /* @Nullable */ Object payload) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("message", message);
        if (payload != null) {
            fields.put("payload", payload);
        }
        return log(timestampMicroseconds, fields);
    }

    private long nowMicros() {
        // Note that startTimestampRelativeNanos will be -1 if the user
        // provided an explicit start timestamp in the SpanBuilder.
        if (startTimestampRelativeNanos > 0) {
            long durationMicros = (System.nanoTime() - startTimestampRelativeNanos) / 1000;
            return Util.protoTimeToEpochMicros(grpcSpan.getStartTimestamp())+ durationMicros;
        } else {
            return System.currentTimeMillis() * 1000;
        }
    }

    private long durationMicros(long finishTimeMicros) {
        return finishTimeMicros - Util.protoTimeToEpochMicros(grpcSpan.getStartTimestamp());
    }

    /**
     * Quotes a plain string into a valid JSON value.
     *
     * Adapted from https://android.googlesource.com/platform/dalvik/libcore/json/src/main/java/org/json/JSONStringer.java
     */
    static String stringToJSONValue(String value) {
        StringBuilder out = new StringBuilder(value.length() + 2);
        out.append("\"");
        for (int i = 0, length = value.length(); i < length; i++) {
            char c = value.charAt(i);
            /*
             * From RFC 4627, "All Unicode characters may be placed within the
             * quotation marks except for the characters that must be escaped:
             * quotation mark, reverse solidus, and the control characters
             * (U+0000 through U+001F)."
             */
            switch (c) {
                case '"':
                case '\\':
                case '/':
                    out.append('\\').append(c);
                    break;
                case '\t':
                    out.append("\\t");
                    break;
                case '\b':
                    out.append("\\b");
                    break;
                case '\n':
                    out.append("\\n");
                    break;
                case '\r':
                    out.append("\\r");
                    break;
                case '\f':
                    out.append("\\f");
                    break;
                default:
                    if (c <= 0x1F) {
                        out.append(String.format("\\u%04x", (int) c));
                    } else {
                        out.append(c);
                    }
                    break;
            }
        }
        out.append("\"");
        return out.toString();
    }

    /**
     * For unit testing only.
     */
    long getStartTimestampRelativeNanos() {
        return startTimestampRelativeNanos;
    }

    /**
     * For unit testing in JRE test.
     */
    @SuppressWarnings("WeakerAccess")
    public SpBuilder getGrpcSpan() {
        return grpcSpan;
    }
}
