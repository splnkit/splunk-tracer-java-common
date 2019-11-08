package com.splunk.tracer.transport;

import com.splunk.tracer.transport.Auth;
import com.splunk.tracer.transport.InternalMetrics;
import com.splunk.tracer.transport.KeyValue;
import com.splunk.tracer.transport.Reporter;
import com.splunk.tracer.transport.Span;
import com.splunk.tracer.transport.Log;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public final class ReportRequest { 
    
    private long offsetMicros_;

    private Auth auth_;
    /**
     * <code>.lightstep.collector.Auth auth = 2;</code>
     */
    public boolean hasAuth() {
      return auth_ != null;
    }
    /**
     * <code>.lightstep.collector.Auth auth = 2;</code>
     */
    public Auth getAuth() {
      return auth_ == null ? Auth.getDefaultInstance() : auth_;
    }

    private List<Span> spans_;
    /**
     * <code>repeated .lightstep.collector.Span spans = 3;</code>
     */
    public List<Span> getSpansList() {
      return spans_;
    }

    public int getSpansCount() {
        return spans_.size();
    }
    /**
     * <code>repeated .lightstep.collector.Span spans = 3;</code>
     */
    public Span getSpans(int index) {
        return spans_.get(index);
    }

    private com.splunk.tracer.transport.InternalMetrics internalMetrics_;
    /**
     * <code>.lightstep.collector.InternalMetrics internal_metrics = 6;</code>
     */
    public boolean hasInternalMetrics() {
      return internalMetrics_ != null;
    }
    /**
     * <code>.lightstep.collector.InternalMetrics internal_metrics = 6;</code>
     */
    public com.splunk.tracer.transport.InternalMetrics getInternalMetrics() {
      return internalMetrics_ == null ? com.splunk.tracer.transport.InternalMetrics.getDefaultInstance() : internalMetrics_;
    }

    private Reporter reporter_;
    /**
     * <code>.lightstep.collector.Reporter reporter = 1;</code>
     */
    public boolean hasReporter() {
      return reporter_ != null;
    }
    /**
     * <code>.lightstep.collector.Reporter reporter = 1;</code>
     */
    public com.splunk.tracer.transport.Reporter getReporter() {
      return reporter_ == null ? com.splunk.tracer.transport.Reporter.getDefaultInstance() : reporter_;
    }

    public ReportRequest(ReportRequestBuilder builder) 
    { 
        this.auth_ = builder.auth_;
        this.reporter_ = builder.reporter_;
        this.spans_ = builder.spans_;
        this.internalMetrics_ = builder.internalMetrics_;
        // this.auth_ = builder.auth_; 
    } 

    public static ReportRequestBuilder ReportRequestBuilder() 
    { 
        return new ReportRequestBuilder();
    }    
  
    // Static class Builder 
    public static class ReportRequestBuilder { 
  
        /// instance fields 
        private Auth auth_;
        private List<Span> spans_;
        private InternalMetrics internalMetrics_;
        private Reporter reporter_;
        private long offsetMicros_;
  
        public ReportRequestBuilder() 
        { 
        }  
  
        // Setter methods 
        public ReportRequestBuilder setAuth(Auth auth) 
        { 
            this.auth_ = auth; 
            return this; 
        } 
        public ReportRequestBuilder setReporter(Reporter reporter) 
        {
            this.reporter_ = reporter;
            return this;
        }

        public ReportRequestBuilder addAllSpans(List<Span> spans)
        {
            this.spans_ = spans;
            return this;
        }
        
        public ReportRequestBuilder setTimestampOffsetMicros(long offset)
        {
            this.offsetMicros_ = offset;
            return this;
        }

        public ReportRequestBuilder setInternalMetrics(InternalMetrics metrics)
        {
            this.internalMetrics_ = metrics;
            return this;
        }
        // build method to deal with outer class 
        // to return outer instance 
        public ReportRequest build() 
        { 
            return new ReportRequest(this); 
        } 
    } 
  
    public static byte[] toReportContent(String json) 
    { 
        try {
            byte[] data = json.getBytes("UTF-8");
            ByteArrayOutputStream arr = new ByteArrayOutputStream();
            OutputStream zipper = new GZIPOutputStream(arr);
            zipper.write(data);
            zipper.close();
            return arr.toByteArray();
        }
        catch (java.io.IOException e)
        {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public byte[] toJson()
    {
        ArrayList<String> event_obj_list = new ArrayList<String>();
        for (Span span : spans_)
        {
            JSONObject convertedSpan = new JSONObject();
            long trace_id = span.getSpanContext().getTraceId();
            long span_id = span.getSpanContext().getSpanId();
            long parent_span_id = span.getReferencesList().getSpanContext().getSpanId();
            long guid = reporter_.getReporterId();
            Timestamp ts = span.getStartTimestamp();
//            long duration =
            
            convertedSpan.put("time", ts.toString()); //+ ts.getNanos()/1000000000);
            convertedSpan.put("sourcetype", "splunktracing:span");
            JSONObject event_obj = new JSONObject();
            event_obj.put("operation_name", span.getOperationName());
            event_obj.put("trace_id", Long.toHexString(trace_id));
            event_obj.put("span_id", Long.toHexString(span_id));
            event_obj.put("parent_span_id", Long.toHexString(parent_span_id));
            event_obj.put("guid", Long.toHexString(guid));
            event_obj.put("timestamp", ts.toString());
            event_obj.put("duration", span.getDurationMicros());
            event_obj.put("tags", MergeSpanReporterTags(span.getTagsList()));
            event_obj.put("baggage", new JSONObject(span.getSpanContext().getBaggage()));
            event_obj.put("device", reporter_.getTag("device"));
            event_obj.put("component_name", reporter_.getTag("component_name"));
            event_obj.put("tracer_platform_version", reporter_.getTag("tracer_platform_version"));
            event_obj.put("tracer_platform", reporter_.getTag("tracer_platform"));
            event_obj.put("tracer_version", reporter_.getTag("tracer_version"));
            convertedSpan.put("event", event_obj);
            event_obj_list.add(convertedSpan.toString());

            for (Log log : span.getLogsList())
            {
                JSONObject convertedLog = new JSONObject();
                Timestamp lts = log.getTimestamp();
                convertedLog.put("time", lts.toString());  //String.format("%d.%09d", lts.getSeconds(), lts.getNanos()));
                convertedLog.put("sourcetype", "splunktracing:log");
                JSONObject log_obj = new JSONObject();
                log_obj.put("operation_name", span.getOperationName());
                log_obj.put("trace_id", Long.toHexString(trace_id));
                log_obj.put("span_id", Long.toHexString(span_id));
                log_obj.put("parent_span_id", Long.toHexString(parent_span_id));
                log_obj.put("guid", Long.toHexString(guid));
                log_obj.put("timestamp", lts.toString());
                log_obj.put("tags", MergeSpanReporterTags(span.getTagsList()));
                log_obj.put("baggage", new JSONObject(span.getSpanContext().getBaggage()));
                log_obj.put("device", reporter_.getTag("device"));
                log_obj.put("component_name", reporter_.getTag("component_name"));
                log_obj.put("tracer_platform_version", reporter_.getTag("tracer_platform_version"));
                log_obj.put("tracer_platform", reporter_.getTag("tracer_platform"));
                log_obj.put("tracer_version", reporter_.getTag("tracer_version"));
                log_obj.put("fields", DictToJson(log.getFields()));
                convertedLog.put("event", log_obj);

                event_obj_list.add(convertedLog.toString());
            }
        }
        String json_content = String.join("\n", event_obj_list);
        return toReportContent(json_content);
    }

    public static JSONObject DictToJson(List<KeyValue> thing)
    {
        JSONObject out_obj = new JSONObject();
        for (KeyValue kvpair : thing)
        {
          out_obj.put(kvpair.getKey(),kvpair.getValue());
        }
        return out_obj;
    }

    public JSONObject MergeSpanReporterTags(List<KeyValue> thing)
    {
        JSONObject out_obj = DictToJson(thing);

        for (KeyValue rtag : reporter_.getTagsList())
            {
                String k  = rtag.getKey();
                if ( k != "device" && k != "component_name" && k != "tracer_platform_version" && k != "tracer_platform" && k != "tracer_version")
                {
                    out_obj.put(rtag.getKey(),rtag.getValue());
                }
            }
        return out_obj;
    }

}