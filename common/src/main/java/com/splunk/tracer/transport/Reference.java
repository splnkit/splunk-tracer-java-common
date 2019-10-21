package com.splunk.tracer.transport;

import com.splunk.tracer.transport.SpanContext;

import java.util.ArrayList;

public final class Reference { 
   
    private final String reporter_id_;
    private final ArrayList<KeyValue> tags_;
    private final SpanContext span_ctx_;
    private final Relationship relationship_;

    public Reference() 
    {
        reporter_id_ = "";
        tags_ = new ArrayList<KeyValue>();
        span_ctx_ = new SpanContext();
        relationship_ = Relationship.forNumber(0);
    }

    public String getReferenceId() {
        return reporter_id_;
    }

    public SpanContext getSpanContext() {
        return span_ctx_;
    }

    public ArrayList<KeyValue> getTags() {
        return tags_;
    }

    public static ReferenceBuilder ReferenceBuilder() 
    { 
        return new ReferenceBuilder(); 
    }

    public static class ReferenceBuilder {

        private String reporter_id_;
        private ArrayList<KeyValue> tags_;    //required
        private SpanContext span_ctx_;
        private Relationship relationship_;

        public ReferenceBuilder() 
        { 
        } 

        public ReferenceBuilder setSpanContext(SpanContext ctx)
        {
            this.span_ctx_ = ctx;
            return this;
        }

        public ReferenceBuilder(String id) {
            this.reporter_id_ = id;
        }
        public ReferenceBuilder setReferenceId(String id) {
            this.reporter_id_ = id;
            return this;
        }
        public ReferenceBuilder setRelationship(Relationship rel) {
            this.relationship_ = rel;
            return this;
        }
        public ReferenceBuilder addTags(KeyValue tag) {
            this.tags_.add(tag);
            return this;
        }
        public Reference build() {
            // call the private constructor in the outer class
            return new Reference(this);
        }
    }
    private Reference(ReferenceBuilder builder) {
        this.tags_ = builder.tags_;
        this.reporter_id_ = builder.reporter_id_;
        this.span_ctx_ = builder.span_ctx_;
        this.relationship_ = builder.relationship_;
    }
    public static enum Relationship {

        CHILD_OF(0),
        FOLLOWS_FROM(1),
        UNRECOGNIZED(-1);

        public int value_;

        public int getNumber() {
          if (this.value_ == -1) {
            throw new java.lang.IllegalArgumentException(
                "Can't get the number of an unknown enum value.");
          }
          return this.value_;
        }

        public static Relationship forNumber(int value) {
          switch (value) {
            case 0: return CHILD_OF;
            case 1: return FOLLOWS_FROM;
            default: return null;
          }
        }

        private Relationship(int id) {
            this.value_ = id;
        }
    }
}