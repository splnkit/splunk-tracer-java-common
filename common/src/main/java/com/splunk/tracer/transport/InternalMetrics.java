package com.splunk.tracer.transport;

import com.splunk.tracer.transport.MetricsSample;

import java.util.ArrayList;

public final class InternalMetrics { 
   
    private final String reporter_id_;
    private final ArrayList<KeyValue> tags_;
    private final MetricsSample counts_;

    public InternalMetrics()
    {
        reporter_id_ = "";
        tags_ = new ArrayList();
        counts_ = MetricsSample.MetricsSampleBuilder().build();
    }
    
    private static final com.splunk.tracer.transport.InternalMetrics DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.splunk.tracer.transport.InternalMetrics();
    }
    public static com.splunk.tracer.transport.InternalMetrics getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    public String getInternalMetricsId() {
        return reporter_id_;
    }

    public ArrayList<KeyValue> getTags() {
        return tags_;
    }

    public static InternalMetricsBuilder InternalMetricsBuilder() {
        return new InternalMetricsBuilder();
    }

    public static class InternalMetricsBuilder {

        private String reporter_id_;
        private ArrayList<KeyValue> tags_;    //required
        private MetricsSample counts_;

        public InternalMetricsBuilder() {
        }

        public InternalMetricsBuilder(String id) {
            this.reporter_id_ = id;
        }
        public InternalMetricsBuilder setInternalMetricsId(String id) {
            this.reporter_id_ = id;
            return this;
        }

        public InternalMetricsBuilder addCounts(MetricsSample counts)
        {
            this.counts_ = counts;
            return this;
        }

        public InternalMetricsBuilder addTags(KeyValue tag) {
            this.tags_.add(tag);
            return this;
        }
        public InternalMetrics build() {
            // call the private constructor in the outer class
            return new InternalMetrics(this);
        }
    }
    private InternalMetrics(InternalMetricsBuilder builder) {
        this.tags_ = builder.tags_;
        this.reporter_id_ = builder.reporter_id_;
        this.counts_ = builder.counts_;
    }
}