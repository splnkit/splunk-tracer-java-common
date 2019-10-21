package com.splunk.tracer.transport;

import java.util.ArrayList;

public final class MetricsSample { 
   
    private final String name_;
    private final long value_;
    private final String reporter_id_;
    private final ArrayList<KeyValue> tags_;

    public String getMetricsSampleId() {
        return reporter_id_;
    }

    public ArrayList<KeyValue> getTags() {
        return tags_;
    }

    public static MetricsSampleBuilder MetricsSampleBuilder() {
        return new MetricsSampleBuilder();
    }

    public static class MetricsSampleBuilder {

        private String name_;
        private long value_;
        private String reporter_id_;
        private ArrayList<KeyValue> tags_;    //required

        public MetricsSampleBuilder() {
        }

        public MetricsSampleBuilder(String id) {
            this.reporter_id_ = id;
        }

        public MetricsSampleBuilder setName(String name)
        {
            this.name_ = name;
            return this;
        }

        public MetricsSampleBuilder setIntValue(long value)
        {
            this.value_ = value;
            return this;
        }

        public MetricsSampleBuilder setMetricsSampleId(String id) {
            this.reporter_id_ = id;
            return this;
        }
        public MetricsSampleBuilder addTags(KeyValue tag) {
            this.tags_.add(tag);
            return this;
        }
        public MetricsSample build() {
            // call the private constructor in the outer class
            return new MetricsSample(this);
        }
    }
    private MetricsSample(MetricsSampleBuilder builder) {
        this.tags_ = builder.tags_;
        this.reporter_id_ = builder.reporter_id_;
        this.name_ = builder.name_;
        this.value_ = builder.value_;
    }
}