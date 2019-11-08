package com.splunk.tracer.transport;

import java.util.List;
import java.util.ArrayList;

public final class Reporter { 
   
    private final Long reporter_id_;
    private List<KeyValue> tags_;

    public Reporter() {
        reporter_id_ = 0L;
        tags_ = new ArrayList();
    }
    private static final com.splunk.tracer.transport.Reporter DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.splunk.tracer.transport.Reporter();
    }
    public static com.splunk.tracer.transport.Reporter getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    public Long getReporterId() {
        return reporter_id_;
    }

    public List<KeyValue> getTags() {
        return tags_;
    }

    public Object getTag(String key) {
        for (KeyValue tag_pair : tags_)
        {
            if (key == tag_pair.getKey()) 
            {
                return tag_pair.getValue();
            }
        }
        return "";
    }

    public List<KeyValue> getTagsList() {
        return tags_;
    }

    public void addTags(KeyValue tag) {
        tags_.add(tag);
    }

    public static class ReporterBuilder {

        private Long reporter_id_;
        private List<KeyValue> tags_;    //required

        public ReporterBuilder() {
        }

        public ReporterBuilder(Long id) {
            this.reporter_id_ = id;
            this.tags_ = new ArrayList();
        }
        public ReporterBuilder setReporterId(Long id) {
            this.reporter_id_ = id;
            return this;
        }
        public ReporterBuilder addTags(KeyValue tag) {
            this.tags_.add(tag);
            return this;
        }
        public Reporter build() {
            // call the private constructor in the outer class
            return new Reporter(this);
        }
    }
    private Reporter(ReporterBuilder builder) {
        this.tags_ = builder.tags_;
        this.reporter_id_ = builder.reporter_id_;
    }
}