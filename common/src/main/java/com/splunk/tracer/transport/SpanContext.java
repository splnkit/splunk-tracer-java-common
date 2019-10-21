package com.splunk.tracer.transport;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public final class SpanContext { 
   
    private final long trace_id_;
    private final long span_id_;
    private final List<KeyValue> tags_;
    private final Map<String,String> baggage_;

    public SpanContext() 
    { 
        trace_id_ = 0L;
        span_id_ = 0L;
        tags_ = new ArrayList<KeyValue>();
        baggage_ = new HashMap<String,String>();
    } 
    public long getTraceId() {
        return trace_id_;
    }

    public long getSpanId() {
        return span_id_;
    }

    public List<KeyValue> getTags() {
        return tags_;
    }

    public Map<String,String> getBaggage() {
        return baggage_;
    }

    public static SpanContextBuilder SpanContextBuilder() 
    { 
        return new SpanContextBuilder();
    }

    public static class SpanContextBuilder {

        private long trace_id_;
        private long span_id_;
        private List<KeyValue> tags_;    //required
        private Map<String,String> baggage_;


        public SpanContextBuilder() 
        { 
        } 

        public SpanContextBuilder(long id) {
            this.trace_id_ = id;
        }
        
        public SpanContextBuilder setTraceId(long id) {
            this.trace_id_ = id;
            return this;
        }
        public SpanContextBuilder putAllBaggage(Map<String,String> baggage)
        {
            this.baggage_ = baggage;
            return this;
        }
        public SpanContextBuilder setSpanId(long id) {
            this.span_id_ = id;
            return this;
        }

        public SpanContextBuilder addTags(KeyValue tag) {
            this.tags_.add(tag);
            return this;
        }
        public SpanContext build() {
            // call the private constructor in the outer class
            return new SpanContext(this);
        }
    }
    private SpanContext(SpanContextBuilder builder) {
        this.tags_ = builder.tags_;
        this.trace_id_ = builder.trace_id_;
        this.span_id_ = builder.span_id_;
        this.baggage_ = builder.baggage_;
    }
}