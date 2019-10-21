package com.splunk.tracer.transport;

import com.splunk.tracer.transport.Timestamp;
import java.util.List;
import java.util.ArrayList;


public final class Log { 
   
    private final Timestamp startTimestamp_;
    private final List<KeyValue> fields_;

    public Timestamp getTimestamp() {
        return startTimestamp_;
    }
    public List<KeyValue> getFields() {
        return fields_;
    }
    public List<KeyValue> getFieldsList() {
        return fields_;
    }

    public static LogBuilder LogBuilder() {
        return new LogBuilder();
    }

    public static class LogBuilder {

        private Timestamp startTimestamp_;
        private List<KeyValue> fields_;    //required

        public LogBuilder() {
            fields_ = new ArrayList<KeyValue>();
        }

        public LogBuilder(Timestamp ts) {
            this.startTimestamp_ = ts;
        }

        public LogBuilder setTimestamp(Timestamp ts) {
            this.startTimestamp_ = ts;
            return this;
        }

        public LogBuilder addFields(KeyValue field) {
            this.fields_.add(field);
            return this;
        }
        public Log build() {
            // call the private constructor in the outer class
            return new Log(this);
        }
    }
    private Log(LogBuilder builder) {
        this.fields_ = builder.fields_;
        this.startTimestamp_ = builder.startTimestamp_;
    }
}