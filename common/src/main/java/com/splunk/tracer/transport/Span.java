package com.splunk.tracer.transport;

import com.splunk.tracer.transport.Timestamp;
import java.util.ArrayList;
import java.util.List;

public final class Span { 
   
    private final List<Log> logs_;

    private final String operationName_;

    private final List<KeyValue> tags_;

    private final Timestamp startTimestamp_;

    private final SpanContext spanContext_;

    private final Reference references_;

    private final long durationMicros_;

    public Span() {
        startTimestamp_ = new Timestamp(0, 0);
        logs_ = new ArrayList<Log>();
        operationName_ = "";
        tags_ = new ArrayList<KeyValue>();
        spanContext_ = new SpanContext();
        references_ = new Reference();
        durationMicros_ = 0L;
    }

    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
         return true;
        }
        if (!(obj instanceof com.splunk.tracer.transport.Span)) {
          return super.equals(obj);
        }
        com.splunk.tracer.transport.Span other = (com.splunk.tracer.transport.Span) obj;
    
        boolean result = true;
        result = result && (hasSpanContext() == other.hasSpanContext());
        if (hasSpanContext()) {
          result = result && getSpanContext()
              .equals(other.getSpanContext());
        }
        result = result && getOperationName()
            .equals(other.getOperationName());
        result = result && getReferencesList()
            .equals(other.getReferencesList());
        result = result && (hasStartTimestamp() == other.hasStartTimestamp());
        if (hasStartTimestamp()) {
          result = result && getStartTimestamp()
              .equals(other.getStartTimestamp());
        }
        result = result && (getDurationMicros()
            == other.getDurationMicros());
        result = result && getTagsList()
            .equals(other.getTagsList());
        result = result && getLogsList()
            .equals(other.getLogsList());
        return result;
    }

    public String getOperationName() {
        return operationName_;
    }
    public Timestamp getStartTimestamp() {
        return startTimestamp_;
    }

    public SpanContext getSpanContext() {
        return spanContext_;
    }

    public boolean hasSpanContext() {
        return spanContext_.getTraceId() != 0L;
    }

    public boolean hasStartTimestamp() {
        return startTimestamp_.getSeconds() != 0;
    }

    public long getDurationMicros() {
        return durationMicros_;
    }

    public Reference getReferencesList() {
        return references_;
    }

    public int getTagsCount() {
        return tags_.size();
    }
    public KeyValue getTags(int idx) {
        return tags_.get(idx);
    }
    public int getLogsCount() {
        return logs_.size();
    }
    public Log getLogs(int idx) {
        return logs_.get(idx);
    }
    public List<Log> getLogsList() {
        if (logs_ == null) {
            return new ArrayList<Log>();
        }
        return logs_;
    } 

    public List<KeyValue> getTagsList() {
        if (tags_ == null) {
            return new ArrayList<KeyValue>();
        }
        return tags_;
    }  

    public Span(SpBuilder builder) 
    { 
        this.operationName_ = builder.operationName_;
        this.logs_ = builder.logs_; 
        this.tags_ = builder.tags_;
        this.startTimestamp_ = builder.startTimestamp_;
        this.spanContext_ = builder.spanContext_;
        this.references_ = builder.references_;
        this.durationMicros_ = builder.durationMicros_; 
    }

    public static SpBuilder SpBuilder() 
    { 
        return new SpBuilder(); 
    } 

    // Static class Builder 
    public static class SpBuilder { 
  
        /// instance fields 

        private String operationName_;

        private List<Log> logs_; 

        private List<KeyValue> tags_;

        private Timestamp startTimestamp_;

        private SpanContext spanContext_;

        private Reference references_;

        private long durationMicros_;
  
        public SpBuilder() 
        {
            startTimestamp_ = new Timestamp(0,0);
            tags_ = new ArrayList<KeyValue>();
            logs_ = new ArrayList<Log>();
            operationName_ = "";
            spanContext_ = new SpanContext();
            references_ = new Reference();
            durationMicros_ = 0L;
        } 
    
        // Setter methods 

        public SpBuilder setOperationName(String name) 
        { 
            this.operationName_ = name; 
            return this; 
        }

        public SpBuilder setSpanContext(SpanContext ctx)
        {
            this.spanContext_ = ctx;
            return this;
        }

        public SpBuilder setStartTimestamp(Timestamp ts)
        {
            this.startTimestamp_ = ts;
            return this;
        }
        // public SpBuilder withStartTimestamp(long ts) {
        //     this.startTimestamp_ = new Timestamp(ts);
        //     return this;
        // }
        // public SpBuilder withTraceIdAndSpanId(int tid, int sid) {
        //     this.trace_id_ = tid;
        //     this.span_id_ = sid;
        //     return this;
        // }

        public SpBuilder setDurationMicros(long duration)
        {
            this.durationMicros_ = duration;
            return this;
        }
        public long getDurationMicros() {
            return this.durationMicros_;
        }
        public SpBuilder addLogs(Log log)
        {
            this.logs_.add(log);
            return this; 
        }

        public SpBuilder addTags(KeyValue tag)
        {
            this.tags_.add(tag);
            return this; 
        }

        public SpBuilder addReferences(Reference ref)
        {
            this.references_ = ref;
            return this;
        }
        public Timestamp getStartTimestamp() 
        {
            return this.startTimestamp_;
        }
        // build method to deal with outer class 
        // to return outer instance 
        public Span build() 
        { 
            return new Span(this); 
        }
        
        public List<KeyValue> getTagsList() {
            return this.tags_;
        }
        public Timestamp getstartTimestamp() {
            return this.startTimestamp_;
        }
        public String getOperationName() {
            return this.operationName_;
        }
        public int getTagsCount() {
            return this.tags_.size();
        }
        public KeyValue getTags(int idx) {
            return this.tags_.get(idx);
        }
        public int getLogsCount() {
            return this.logs_.size();
        }
        public Log getLogs(int idx) {
            return this.logs_.get(idx);
        }
        public SpanContext getSpanContext() {
            return spanContext_;
        }

    } 
  
    @Override
    public String toString() 
    { 
        return "id = " + this.operationName_ + ", name = "; 
    } 
}

// public  final class Span {
// private static final long serialVersionUID = 0L;
//   // Use Span.newBuilder() to construct.
//   private Span(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
//     super(builder);
//   }
//   private Span() {
//     operationName_ = "";
//     references_ = java.util.Collections.emptyList();
//     durationMicros_ = 0L;
//     tags_ = java.util.Collections.emptyList();
//     logs_ = java.util.Collections.emptyList();
//   }

//   @java.lang.Override
//   public final com.google.protobuf.UnknownFieldSet
//   getUnknownFields() {
//     return this.unknownFields;
//   }
//   private Span(
//       com.google.protobuf.CodedInputStream input,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     this();
//     if (extensionRegistry == null) {
//       throw new java.lang.NullPointerException();
//     }
//     int mutable_bitField0_ = 0;
//     com.google.protobuf.UnknownFieldSet.Builder unknownFields =
//         com.google.protobuf.UnknownFieldSet.newBuilder();
//     try {
//       boolean done = false;
//       while (!done) {
//         int tag = input.readTag();
//         switch (tag) {
//           case 0:
//             done = true;
//             break;
//           case 10: {
//             com.lightstep.tracer.grpc.SpanContext.Builder subBuilder = null;
//             if (spanContext_ != null) {
//               subBuilder = spanContext_.toBuilder();
//             }
//             spanContext_ = input.readMessage(com.lightstep.tracer.grpc.SpanContext.parser(), extensionRegistry);
//             if (subBuilder != null) {
//               subBuilder.mergeFrom(spanContext_);
//               spanContext_ = subBuilder.buildPartial();
//             }

//             break;
//           }
//           case 18: {
//             java.lang.String s = input.readStringRequireUtf8();

//             operationName_ = s;
//             break;
//           }
//           case 26: {
//             if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
//               references_ = new java.util.ArrayList<com.lightstep.tracer.grpc.Reference>();
//               mutable_bitField0_ |= 0x00000004;
//             }
//             references_.add(
//                 input.readMessage(com.lightstep.tracer.grpc.Reference.parser(), extensionRegistry));
//             break;
//           }
//           case 34: {
//             com.google.protobuf.Timestamp.Builder subBuilder = null;
//             if (startTimestamp_ != null) {
//               subBuilder = startTimestamp_.toBuilder();
//             }
//             startTimestamp_ = input.readMessage(com.google.protobuf.Timestamp.parser(), extensionRegistry);
//             if (subBuilder != null) {
//               subBuilder.mergeFrom(startTimestamp_);
//               startTimestamp_ = subBuilder.buildPartial();
//             }

//             break;
//           }
//           case 40: {

//             durationMicros_ = input.readUInt64();
//             break;
//           }
//           case 50: {
//             if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
//               tags_ = new java.util.ArrayList<com.lightstep.tracer.grpc.KeyValue>();
//               mutable_bitField0_ |= 0x00000020;
//             }
//             tags_.add(
//                 input.readMessage(com.lightstep.tracer.grpc.KeyValue.parser(), extensionRegistry));
//             break;
//           }
//           case 58: {
//             if (!((mutable_bitField0_ & 0x00000040) == 0x00000040)) {
//               logs_ = new java.util.ArrayList<com.lightstep.tracer.grpc.Log>();
//               mutable_bitField0_ |= 0x00000040;
//             }
//             logs_.add(
//                 input.readMessage(com.lightstep.tracer.grpc.Log.parser(), extensionRegistry));
//             break;
//           }
//           default: {
//             if (!parseUnknownFieldProto3(
//                 input, unknownFields, extensionRegistry, tag)) {
//               done = true;
//             }
//             break;
//           }
//         }
//       }
//     } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//       throw e.setUnfinishedMessage(this);
//     } catch (java.io.IOException e) {
//       throw new com.google.protobuf.InvalidProtocolBufferException(
//           e).setUnfinishedMessage(this);
//     } finally {
//       if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
//         references_ = java.util.Collections.unmodifiableList(references_);
//       }
//       if (((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
//         tags_ = java.util.Collections.unmodifiableList(tags_);
//       }
//       if (((mutable_bitField0_ & 0x00000040) == 0x00000040)) {
//         logs_ = java.util.Collections.unmodifiableList(logs_);
//       }
//       this.unknownFields = unknownFields.build();
//       makeExtensionsImmutable();
//     }
//   }
//   public static final com.google.protobuf.Descriptors.Descriptor
//       getDescriptor() {
//     return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_Span_descriptor;
//   }

//   @java.lang.Override
//   protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//       internalGetFieldAccessorTable() {
//     return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_Span_fieldAccessorTable
//         .ensureFieldAccessorsInitialized(
//             com.lightstep.tracer.grpc.Span.class, com.lightstep.tracer.grpc.Span.Builder.class);
//   }

//   private int bitField0_;
//   public static final int SPAN_CONTEXT_FIELD_NUMBER = 1;
//   private com.lightstep.tracer.grpc.SpanContext spanContext_;
//   /**
//    * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//    */
//   public boolean hasSpanContext() {
//     return spanContext_ != null;
//   }
//   /**
//    * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//    */
//   public com.lightstep.tracer.grpc.SpanContext getSpanContext() {
//     return spanContext_ == null ? com.lightstep.tracer.grpc.SpanContext.getDefaultInstance() : spanContext_;
//   }
//   /**
//    * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//    */
//   public com.lightstep.tracer.grpc.SpanContextOrBuilder getSpanContextOrBuilder() {
//     return getSpanContext();
//   }

//   public static final int OPERATION_NAME_FIELD_NUMBER = 2;
//   private volatile java.lang.Object operationName_;
//   /**
//    * <code>string operation_name = 2;</code>
//    */
//   public java.lang.String getOperationName() {
//     java.lang.Object ref = operationName_;
//     if (ref instanceof java.lang.String) {
//       return (java.lang.String) ref;
//     } else {
//       com.google.protobuf.ByteString bs = 
//           (com.google.protobuf.ByteString) ref;
//       java.lang.String s = bs.toStringUtf8();
//       operationName_ = s;
//       return s;
//     }
//   }
//   /**
//    * <code>string operation_name = 2;</code>
//    */
//   public com.google.protobuf.ByteString
//       getOperationNameBytes() {
//     java.lang.Object ref = operationName_;
//     if (ref instanceof java.lang.String) {
//       com.google.protobuf.ByteString b = 
//           com.google.protobuf.ByteString.copyFromUtf8(
//               (java.lang.String) ref);
//       operationName_ = b;
//       return b;
//     } else {
//       return (com.google.protobuf.ByteString) ref;
//     }
//   }

//   public static final int REFERENCES_FIELD_NUMBER = 3;
//   private java.util.List<com.lightstep.tracer.grpc.Reference> references_;
//   /**
//    * <code>repeated .lightstep.collector.Reference references = 3;</code>
//    */
//   public java.util.List<com.lightstep.tracer.grpc.Reference> getReferencesList() {
//     return references_;
//   }
//   /**
//    * <code>repeated .lightstep.collector.Reference references = 3;</code>
//    */
//   public java.util.List<? extends com.lightstep.tracer.grpc.ReferenceOrBuilder> 
//       getReferencesOrBuilderList() {
//     return references_;
//   }
//   /**
//    * <code>repeated .lightstep.collector.Reference references = 3;</code>
//    */
//   public int getReferencesCount() {
//     return references_.size();
//   }
//   /**
//    * <code>repeated .lightstep.collector.Reference references = 3;</code>
//    */
//   public com.lightstep.tracer.grpc.Reference getReferences(int index) {
//     return references_.get(index);
//   }
//   /**
//    * <code>repeated .lightstep.collector.Reference references = 3;</code>
//    */
//   public com.lightstep.tracer.grpc.ReferenceOrBuilder getReferencesOrBuilder(
//       int index) {
//     return references_.get(index);
//   }

//   public static final int START_TIMESTAMP_FIELD_NUMBER = 4;
//   private com.google.protobuf.Timestamp startTimestamp_;
//   /**
//    * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//    */
//   public boolean hasStartTimestamp() {
//     return startTimestamp_ != null;
//   }
//   /**
//    * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//    */
//   public com.google.protobuf.Timestamp getStartTimestamp() {
//     return startTimestamp_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : startTimestamp_;
//   }
//   /**
//    * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//    */
//   public com.google.protobuf.TimestampOrBuilder getStartTimestampOrBuilder() {
//     return getStartTimestamp();
//   }

//   public static final int DURATION_MICROS_FIELD_NUMBER = 5;
//   private long durationMicros_;
//   /**
//    * <code>uint64 duration_micros = 5;</code>
//    */
//   public long getDurationMicros() {
//     return durationMicros_;
//   }

//   public static final int TAGS_FIELD_NUMBER = 6;
//   private java.util.List<com.lightstep.tracer.grpc.KeyValue> tags_;
//   /**
//    * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//    */
//   public java.util.List<com.lightstep.tracer.grpc.KeyValue> getTagsList() {
//     return tags_;
//   }
//   /**
//    * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//    */
//   public java.util.List<? extends com.lightstep.tracer.grpc.KeyValueOrBuilder> 
//       getTagsOrBuilderList() {
//     return tags_;
//   }
//   /**
//    * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//    */
//   public int getTagsCount() {
//     return tags_.size();
//   }
//   /**
//    * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//    */
//   public com.lightstep.tracer.grpc.KeyValue getTags(int index) {
//     return tags_.get(index);
//   }
//   /**
//    * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//    */
//   public com.lightstep.tracer.grpc.KeyValueOrBuilder getTagsOrBuilder(
//       int index) {
//     return tags_.get(index);
//   }

//   public static final int LOGS_FIELD_NUMBER = 7;
//   private java.util.List<com.lightstep.tracer.grpc.Log> logs_;
//   /**
//    * <code>repeated .lightstep.collector.Log logs = 7;</code>
//    */
//   public java.util.List<com.lightstep.tracer.grpc.Log> getLogsList() {
//     return logs_;
//   }
//   /**
//    * <code>repeated .lightstep.collector.Log logs = 7;</code>
//    */
//   public java.util.List<? extends com.lightstep.tracer.grpc.LogOrBuilder> 
//       getLogsOrBuilderList() {
//     return logs_;
//   }
//   /**
//    * <code>repeated .lightstep.collector.Log logs = 7;</code>
//    */
//   public int getLogsCount() {
//     return logs_.size();
//   }
//   /**
//    * <code>repeated .lightstep.collector.Log logs = 7;</code>
//    */
//   public com.lightstep.tracer.grpc.Log getLogs(int index) {
//     return logs_.get(index);
//   }
//   /**
//    * <code>repeated .lightstep.collector.Log logs = 7;</code>
//    */
//   public com.lightstep.tracer.grpc.LogOrBuilder getLogsOrBuilder(
//       int index) {
//     return logs_.get(index);
//   }

//   private byte memoizedIsInitialized = -1;
//   @java.lang.Override
//   public final boolean isInitialized() {
//     byte isInitialized = memoizedIsInitialized;
//     if (isInitialized == 1) return true;
//     if (isInitialized == 0) return false;

//     memoizedIsInitialized = 1;
//     return true;
//   }

//   @java.lang.Override
//   public void writeTo(com.google.protobuf.CodedOutputStream output)
//                       throws java.io.IOException {
//     if (spanContext_ != null) {
//       output.writeMessage(1, getSpanContext());
//     }
//     if (!getOperationNameBytes().isEmpty()) {
//       com.google.protobuf.GeneratedMessageV3.writeString(output, 2, operationName_);
//     }
//     for (int i = 0; i < references_.size(); i++) {
//       output.writeMessage(3, references_.get(i));
//     }
//     if (startTimestamp_ != null) {
//       output.writeMessage(4, getStartTimestamp());
//     }
//     if (durationMicros_ != 0L) {
//       output.writeUInt64(5, durationMicros_);
//     }
//     for (int i = 0; i < tags_.size(); i++) {
//       output.writeMessage(6, tags_.get(i));
//     }
//     for (int i = 0; i < logs_.size(); i++) {
//       output.writeMessage(7, logs_.get(i));
//     }
//     unknownFields.writeTo(output);
//   }

//   @java.lang.Override
//   public int getSerializedSize() {
//     int size = memoizedSize;
//     if (size != -1) return size;

//     size = 0;
//     if (spanContext_ != null) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeMessageSize(1, getSpanContext());
//     }
//     if (!getOperationNameBytes().isEmpty()) {
//       size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, operationName_);
//     }
//     for (int i = 0; i < references_.size(); i++) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeMessageSize(3, references_.get(i));
//     }
//     if (startTimestamp_ != null) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeMessageSize(4, getStartTimestamp());
//     }
//     if (durationMicros_ != 0L) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeUInt64Size(5, durationMicros_);
//     }
//     for (int i = 0; i < tags_.size(); i++) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeMessageSize(6, tags_.get(i));
//     }
//     for (int i = 0; i < logs_.size(); i++) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeMessageSize(7, logs_.get(i));
//     }
//     size += unknownFields.getSerializedSize();
//     memoizedSize = size;
//     return size;
//   }

//   @java.lang.Override
//   public boolean equals(final java.lang.Object obj) {
//     if (obj == this) {
//      return true;
//     }
//     if (!(obj instanceof com.lightstep.tracer.grpc.Span)) {
//       return super.equals(obj);
//     }
//     com.lightstep.tracer.grpc.Span other = (com.lightstep.tracer.grpc.Span) obj;

//     boolean result = true;
//     result = result && (hasSpanContext() == other.hasSpanContext());
//     if (hasSpanContext()) {
//       result = result && getSpanContext()
//           .equals(other.getSpanContext());
//     }
//     result = result && getOperationName()
//         .equals(other.getOperationName());
//     result = result && getReferencesList()
//         .equals(other.getReferencesList());
//     result = result && (hasStartTimestamp() == other.hasStartTimestamp());
//     if (hasStartTimestamp()) {
//       result = result && getStartTimestamp()
//           .equals(other.getStartTimestamp());
//     }
//     result = result && (getDurationMicros()
//         == other.getDurationMicros());
//     result = result && getTagsList()
//         .equals(other.getTagsList());
//     result = result && getLogsList()
//         .equals(other.getLogsList());
//     result = result && unknownFields.equals(other.unknownFields);
//     return result;
//   }

//   @java.lang.Override
//   public int hashCode() {
//     if (memoizedHashCode != 0) {
//       return memoizedHashCode;
//     }
//     int hash = 41;
//     hash = (19 * hash) + getDescriptor().hashCode();
//     if (hasSpanContext()) {
//       hash = (37 * hash) + SPAN_CONTEXT_FIELD_NUMBER;
//       hash = (53 * hash) + getSpanContext().hashCode();
//     }
//     hash = (37 * hash) + OPERATION_NAME_FIELD_NUMBER;
//     hash = (53 * hash) + getOperationName().hashCode();
//     if (getReferencesCount() > 0) {
//       hash = (37 * hash) + REFERENCES_FIELD_NUMBER;
//       hash = (53 * hash) + getReferencesList().hashCode();
//     }
//     if (hasStartTimestamp()) {
//       hash = (37 * hash) + START_TIMESTAMP_FIELD_NUMBER;
//       hash = (53 * hash) + getStartTimestamp().hashCode();
//     }
//     hash = (37 * hash) + DURATION_MICROS_FIELD_NUMBER;
//     hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
//         getDurationMicros());
//     if (getTagsCount() > 0) {
//       hash = (37 * hash) + TAGS_FIELD_NUMBER;
//       hash = (53 * hash) + getTagsList().hashCode();
//     }
//     if (getLogsCount() > 0) {
//       hash = (37 * hash) + LOGS_FIELD_NUMBER;
//       hash = (53 * hash) + getLogsList().hashCode();
//     }
//     hash = (29 * hash) + unknownFields.hashCode();
//     memoizedHashCode = hash;
//     return hash;
//   }

//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       java.nio.ByteBuffer data)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       java.nio.ByteBuffer data,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       com.google.protobuf.ByteString data)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       com.google.protobuf.ByteString data,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(byte[] data)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       byte[] data,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(java.io.InputStream input)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       java.io.InputStream input,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.Span parseDelimitedFrom(java.io.InputStream input)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseDelimitedWithIOException(PARSER, input);
//   }
//   public static com.lightstep.tracer.grpc.Span parseDelimitedFrom(
//       java.io.InputStream input,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       com.google.protobuf.CodedInputStream input)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input);
//   }
//   public static com.lightstep.tracer.grpc.Span parseFrom(
//       com.google.protobuf.CodedInputStream input,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input, extensionRegistry);
//   }

//   @java.lang.Override
//   public Builder newBuilderForType() { return newBuilder(); }
//   public static Builder newBuilder() {
//     return DEFAULT_INSTANCE.toBuilder();
//   }
//   public static Builder newBuilder(com.lightstep.tracer.grpc.Span prototype) {
//     return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
//   }
//   @java.lang.Override
//   public Builder toBuilder() {
//     return this == DEFAULT_INSTANCE
//         ? new Builder() : new Builder().mergeFrom(this);
//   }

//   @java.lang.Override
//   protected Builder newBuilderForType(
//       com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
//     Builder builder = new Builder(parent);
//     return builder;
//   }
//   /**
//    * Protobuf type {@code lightstep.collector.Span}
//    */
//   public static final class Builder extends
//       com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
//       // @@protoc_insertion_point(builder_implements:lightstep.collector.Span)
//       com.lightstep.tracer.grpc.SpanOrBuilder {
//     public static final com.google.protobuf.Descriptors.Descriptor
//         getDescriptor() {
//       return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_Span_descriptor;
//     }

//     @java.lang.Override
//     protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//         internalGetFieldAccessorTable() {
//       return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_Span_fieldAccessorTable
//           .ensureFieldAccessorsInitialized(
//               com.lightstep.tracer.grpc.Span.class, com.lightstep.tracer.grpc.Span.Builder.class);
//     }

//     // Construct using com.lightstep.tracer.grpc.Span.newBuilder()
//     private Builder() {
//       maybeForceBuilderInitialization();
//     }

//     private Builder(
//         com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
//       super(parent);
//       maybeForceBuilderInitialization();
//     }
//     private void maybeForceBuilderInitialization() {
//       if (com.google.protobuf.GeneratedMessageV3
//               .alwaysUseFieldBuilders) {
//         getReferencesFieldBuilder();
//         getTagsFieldBuilder();
//         getLogsFieldBuilder();
//       }
//     }
//     @java.lang.Override
//     public Builder clear() {
//       super.clear();
//       if (spanContextBuilder_ == null) {
//         spanContext_ = null;
//       } else {
//         spanContext_ = null;
//         spanContextBuilder_ = null;
//       }
//       operationName_ = "";

//       if (referencesBuilder_ == null) {
//         references_ = java.util.Collections.emptyList();
//         bitField0_ = (bitField0_ & ~0x00000004);
//       } else {
//         referencesBuilder_.clear();
//       }
//       if (startTimestampBuilder_ == null) {
//         startTimestamp_ = null;
//       } else {
//         startTimestamp_ = null;
//         startTimestampBuilder_ = null;
//       }
//       durationMicros_ = 0L;

//       if (tagsBuilder_ == null) {
//         tags_ = java.util.Collections.emptyList();
//         bitField0_ = (bitField0_ & ~0x00000020);
//       } else {
//         tagsBuilder_.clear();
//       }
//       if (logsBuilder_ == null) {
//         logs_ = java.util.Collections.emptyList();
//         bitField0_ = (bitField0_ & ~0x00000040);
//       } else {
//         logsBuilder_.clear();
//       }
//       return this;
//     }

//     @java.lang.Override
//     public com.google.protobuf.Descriptors.Descriptor
//         getDescriptorForType() {
//       return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_Span_descriptor;
//     }

//     @java.lang.Override
//     public com.lightstep.tracer.grpc.Span getDefaultInstanceForType() {
//       return com.lightstep.tracer.grpc.Span.getDefaultInstance();
//     }

//     @java.lang.Override
//     public com.lightstep.tracer.grpc.Span build() {
//       com.lightstep.tracer.grpc.Span result = buildPartial();
//       if (!result.isInitialized()) {
//         throw newUninitializedMessageException(result);
//       }
//       return result;
//     }

//     @java.lang.Override
//     public com.lightstep.tracer.grpc.Span buildPartial() {
//       com.lightstep.tracer.grpc.Span result = new com.lightstep.tracer.grpc.Span(this);
//       int from_bitField0_ = bitField0_;
//       int to_bitField0_ = 0;
//       if (spanContextBuilder_ == null) {
//         result.spanContext_ = spanContext_;
//       } else {
//         result.spanContext_ = spanContextBuilder_.build();
//       }
//       result.operationName_ = operationName_;
//       if (referencesBuilder_ == null) {
//         if (((bitField0_ & 0x00000004) == 0x00000004)) {
//           references_ = java.util.Collections.unmodifiableList(references_);
//           bitField0_ = (bitField0_ & ~0x00000004);
//         }
//         result.references_ = references_;
//       } else {
//         result.references_ = referencesBuilder_.build();
//       }
//       if (startTimestampBuilder_ == null) {
//         result.startTimestamp_ = startTimestamp_;
//       } else {
//         result.startTimestamp_ = startTimestampBuilder_.build();
//       }
//       result.durationMicros_ = durationMicros_;
//       if (tagsBuilder_ == null) {
//         if (((bitField0_ & 0x00000020) == 0x00000020)) {
//           tags_ = java.util.Collections.unmodifiableList(tags_);
//           bitField0_ = (bitField0_ & ~0x00000020);
//         }
//         result.tags_ = tags_;
//       } else {
//         result.tags_ = tagsBuilder_.build();
//       }
//       if (logsBuilder_ == null) {
//         if (((bitField0_ & 0x00000040) == 0x00000040)) {
//           logs_ = java.util.Collections.unmodifiableList(logs_);
//           bitField0_ = (bitField0_ & ~0x00000040);
//         }
//         result.logs_ = logs_;
//       } else {
//         result.logs_ = logsBuilder_.build();
//       }
//       result.bitField0_ = to_bitField0_;
//       onBuilt();
//       return result;
//     }

//     @java.lang.Override
//     public Builder clone() {
//       return (Builder) super.clone();
//     }
//     @java.lang.Override
//     public Builder setField(
//         com.google.protobuf.Descriptors.FieldDescriptor field,
//         java.lang.Object value) {
//       return (Builder) super.setField(field, value);
//     }
//     @java.lang.Override
//     public Builder clearField(
//         com.google.protobuf.Descriptors.FieldDescriptor field) {
//       return (Builder) super.clearField(field);
//     }
//     @java.lang.Override
//     public Builder clearOneof(
//         com.google.protobuf.Descriptors.OneofDescriptor oneof) {
//       return (Builder) super.clearOneof(oneof);
//     }
//     @java.lang.Override
//     public Builder setRepeatedField(
//         com.google.protobuf.Descriptors.FieldDescriptor field,
//         int index, java.lang.Object value) {
//       return (Builder) super.setRepeatedField(field, index, value);
//     }
//     @java.lang.Override
//     public Builder addRepeatedField(
//         com.google.protobuf.Descriptors.FieldDescriptor field,
//         java.lang.Object value) {
//       return (Builder) super.addRepeatedField(field, value);
//     }
//     @java.lang.Override
//     public Builder mergeFrom(com.google.protobuf.Message other) {
//       if (other instanceof com.lightstep.tracer.grpc.Span) {
//         return mergeFrom((com.lightstep.tracer.grpc.Span)other);
//       } else {
//         super.mergeFrom(other);
//         return this;
//       }
//     }

//     public Builder mergeFrom(com.lightstep.tracer.grpc.Span other) {
//       if (other == com.lightstep.tracer.grpc.Span.getDefaultInstance()) return this;
//       if (other.hasSpanContext()) {
//         mergeSpanContext(other.getSpanContext());
//       }
//       if (!other.getOperationName().isEmpty()) {
//         operationName_ = other.operationName_;
//         onChanged();
//       }
//       if (referencesBuilder_ == null) {
//         if (!other.references_.isEmpty()) {
//           if (references_.isEmpty()) {
//             references_ = other.references_;
//             bitField0_ = (bitField0_ & ~0x00000004);
//           } else {
//             ensureReferencesIsMutable();
//             references_.addAll(other.references_);
//           }
//           onChanged();
//         }
//       } else {
//         if (!other.references_.isEmpty()) {
//           if (referencesBuilder_.isEmpty()) {
//             referencesBuilder_.dispose();
//             referencesBuilder_ = null;
//             references_ = other.references_;
//             bitField0_ = (bitField0_ & ~0x00000004);
//             referencesBuilder_ = 
//               com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
//                  getReferencesFieldBuilder() : null;
//           } else {
//             referencesBuilder_.addAllMessages(other.references_);
//           }
//         }
//       }
//       if (other.hasStartTimestamp()) {
//         mergeStartTimestamp(other.getStartTimestamp());
//       }
//       if (other.getDurationMicros() != 0L) {
//         setDurationMicros(other.getDurationMicros());
//       }
//       if (tagsBuilder_ == null) {
//         if (!other.tags_.isEmpty()) {
//           if (tags_.isEmpty()) {
//             tags_ = other.tags_;
//             bitField0_ = (bitField0_ & ~0x00000020);
//           } else {
//             ensureTagsIsMutable();
//             tags_.addAll(other.tags_);
//           }
//           onChanged();
//         }
//       } else {
//         if (!other.tags_.isEmpty()) {
//           if (tagsBuilder_.isEmpty()) {
//             tagsBuilder_.dispose();
//             tagsBuilder_ = null;
//             tags_ = other.tags_;
//             bitField0_ = (bitField0_ & ~0x00000020);
//             tagsBuilder_ = 
//               com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
//                  getTagsFieldBuilder() : null;
//           } else {
//             tagsBuilder_.addAllMessages(other.tags_);
//           }
//         }
//       }
//       if (logsBuilder_ == null) {
//         if (!other.logs_.isEmpty()) {
//           if (logs_.isEmpty()) {
//             logs_ = other.logs_;
//             bitField0_ = (bitField0_ & ~0x00000040);
//           } else {
//             ensureLogsIsMutable();
//             logs_.addAll(other.logs_);
//           }
//           onChanged();
//         }
//       } else {
//         if (!other.logs_.isEmpty()) {
//           if (logsBuilder_.isEmpty()) {
//             logsBuilder_.dispose();
//             logsBuilder_ = null;
//             logs_ = other.logs_;
//             bitField0_ = (bitField0_ & ~0x00000040);
//             logsBuilder_ = 
//               com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
//                  getLogsFieldBuilder() : null;
//           } else {
//             logsBuilder_.addAllMessages(other.logs_);
//           }
//         }
//       }
//       this.mergeUnknownFields(other.unknownFields);
//       onChanged();
//       return this;
//     }

//     @java.lang.Override
//     public final boolean isInitialized() {
//       return true;
//     }

//     @java.lang.Override
//     public Builder mergeFrom(
//         com.google.protobuf.CodedInputStream input,
//         com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//         throws java.io.IOException {
//       com.lightstep.tracer.grpc.Span parsedMessage = null;
//       try {
//         parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
//       } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//         parsedMessage = (com.lightstep.tracer.grpc.Span) e.getUnfinishedMessage();
//         throw e.unwrapIOException();
//       } finally {
//         if (parsedMessage != null) {
//           mergeFrom(parsedMessage);
//         }
//       }
//       return this;
//     }
//     private int bitField0_;

//     private com.lightstep.tracer.grpc.SpanContext spanContext_ = null;
//     private com.google.protobuf.SingleFieldBuilderV3<
//         com.lightstep.tracer.grpc.SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, com.lightstep.tracer.grpc.SpanContextOrBuilder> spanContextBuilder_;
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public boolean hasSpanContext() {
//       return spanContextBuilder_ != null || spanContext_ != null;
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public com.lightstep.tracer.grpc.SpanContext getSpanContext() {
//       if (spanContextBuilder_ == null) {
//         return spanContext_ == null ? com.lightstep.tracer.grpc.SpanContext.getDefaultInstance() : spanContext_;
//       } else {
//         return spanContextBuilder_.getMessage();
//       }
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public Builder setSpanContext(com.lightstep.tracer.grpc.SpanContext value) {
//       if (spanContextBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         spanContext_ = value;
//         onChanged();
//       } else {
//         spanContextBuilder_.setMessage(value);
//       }

//       return this;
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public Builder setSpanContext(
//         com.lightstep.tracer.grpc.SpanContext.Builder builderForValue) {
//       if (spanContextBuilder_ == null) {
//         spanContext_ = builderForValue.build();
//         onChanged();
//       } else {
//         spanContextBuilder_.setMessage(builderForValue.build());
//       }

//       return this;
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public Builder mergeSpanContext(com.lightstep.tracer.grpc.SpanContext value) {
//       if (spanContextBuilder_ == null) {
//         if (spanContext_ != null) {
//           spanContext_ =
//             com.lightstep.tracer.grpc.SpanContext.newBuilder(spanContext_).mergeFrom(value).buildPartial();
//         } else {
//           spanContext_ = value;
//         }
//         onChanged();
//       } else {
//         spanContextBuilder_.mergeFrom(value);
//       }

//       return this;
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public Builder clearSpanContext() {
//       if (spanContextBuilder_ == null) {
//         spanContext_ = null;
//         onChanged();
//       } else {
//         spanContext_ = null;
//         spanContextBuilder_ = null;
//       }

//       return this;
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public com.lightstep.tracer.grpc.SpanContext.Builder getSpanContextBuilder() {
      
//       onChanged();
//       return getSpanContextFieldBuilder().getBuilder();
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     public com.lightstep.tracer.grpc.SpanContextOrBuilder getSpanContextOrBuilder() {
//       if (spanContextBuilder_ != null) {
//         return spanContextBuilder_.getMessageOrBuilder();
//       } else {
//         return spanContext_ == null ?
//             com.lightstep.tracer.grpc.SpanContext.getDefaultInstance() : spanContext_;
//       }
//     }
//     /**
//      * <code>.lightstep.collector.SpanContext span_context = 1;</code>
//      */
//     private com.google.protobuf.SingleFieldBuilderV3<
//         com.lightstep.tracer.grpc.SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, com.lightstep.tracer.grpc.SpanContextOrBuilder> 
//         getSpanContextFieldBuilder() {
//       if (spanContextBuilder_ == null) {
//         spanContextBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
//             com.lightstep.tracer.grpc.SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, com.lightstep.tracer.grpc.SpanContextOrBuilder>(
//                 getSpanContext(),
//                 getParentForChildren(),
//                 isClean());
//         spanContext_ = null;
//       }
//       return spanContextBuilder_;
//     }

//     private java.lang.Object operationName_ = "";
//     /**
//      * <code>string operation_name = 2;</code>
//      */
//     public java.lang.String getOperationName() {
//       java.lang.Object ref = operationName_;
//       if (!(ref instanceof java.lang.String)) {
//         com.google.protobuf.ByteString bs =
//             (com.google.protobuf.ByteString) ref;
//         java.lang.String s = bs.toStringUtf8();
//         operationName_ = s;
//         return s;
//       } else {
//         return (java.lang.String) ref;
//       }
//     }
//     /**
//      * <code>string operation_name = 2;</code>
//      */
//     public com.google.protobuf.ByteString
//         getOperationNameBytes() {
//       java.lang.Object ref = operationName_;
//       if (ref instanceof String) {
//         com.google.protobuf.ByteString b = 
//             com.google.protobuf.ByteString.copyFromUtf8(
//                 (java.lang.String) ref);
//         operationName_ = b;
//         return b;
//       } else {
//         return (com.google.protobuf.ByteString) ref;
//       }
//     }
//     /**
//      * <code>string operation_name = 2;</code>
//      */
//     public Builder setOperationName(
//         java.lang.String value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
  
//       operationName_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>string operation_name = 2;</code>
//      */
//     public Builder clearOperationName() {
      
//       operationName_ = getDefaultInstance().getOperationName();
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>string operation_name = 2;</code>
//      */
//     public Builder setOperationNameBytes(
//         com.google.protobuf.ByteString value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
//   checkByteStringIsUtf8(value);
      
//       operationName_ = value;
//       onChanged();
//       return this;
//     }

//     private java.util.List<com.lightstep.tracer.grpc.Reference> references_ =
//       java.util.Collections.emptyList();
//     private void ensureReferencesIsMutable() {
//       if (!((bitField0_ & 0x00000004) == 0x00000004)) {
//         references_ = new java.util.ArrayList<com.lightstep.tracer.grpc.Reference>(references_);
//         bitField0_ |= 0x00000004;
//        }
//     }

//     private com.google.protobuf.RepeatedFieldBuilderV3<
//         com.lightstep.tracer.grpc.Reference, com.lightstep.tracer.grpc.Reference.Builder, com.lightstep.tracer.grpc.ReferenceOrBuilder> referencesBuilder_;

//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public java.util.List<com.lightstep.tracer.grpc.Reference> getReferencesList() {
//       if (referencesBuilder_ == null) {
//         return java.util.Collections.unmodifiableList(references_);
//       } else {
//         return referencesBuilder_.getMessageList();
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public int getReferencesCount() {
//       if (referencesBuilder_ == null) {
//         return references_.size();
//       } else {
//         return referencesBuilder_.getCount();
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public com.lightstep.tracer.grpc.Reference getReferences(int index) {
//       if (referencesBuilder_ == null) {
//         return references_.get(index);
//       } else {
//         return referencesBuilder_.getMessage(index);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder setReferences(
//         int index, com.lightstep.tracer.grpc.Reference value) {
//       if (referencesBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureReferencesIsMutable();
//         references_.set(index, value);
//         onChanged();
//       } else {
//         referencesBuilder_.setMessage(index, value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder setReferences(
//         int index, com.lightstep.tracer.grpc.Reference.Builder builderForValue) {
//       if (referencesBuilder_ == null) {
//         ensureReferencesIsMutable();
//         references_.set(index, builderForValue.build());
//         onChanged();
//       } else {
//         referencesBuilder_.setMessage(index, builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder addReferences(com.lightstep.tracer.grpc.Reference value) {
//       if (referencesBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureReferencesIsMutable();
//         references_.add(value);
//         onChanged();
//       } else {
//         referencesBuilder_.addMessage(value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder addReferences(
//         int index, com.lightstep.tracer.grpc.Reference value) {
//       if (referencesBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureReferencesIsMutable();
//         references_.add(index, value);
//         onChanged();
//       } else {
//         referencesBuilder_.addMessage(index, value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder addReferences(
//         com.lightstep.tracer.grpc.Reference.Builder builderForValue) {
//       if (referencesBuilder_ == null) {
//         ensureReferencesIsMutable();
//         references_.add(builderForValue.build());
//         onChanged();
//       } else {
//         referencesBuilder_.addMessage(builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder addReferences(
//         int index, com.lightstep.tracer.grpc.Reference.Builder builderForValue) {
//       if (referencesBuilder_ == null) {
//         ensureReferencesIsMutable();
//         references_.add(index, builderForValue.build());
//         onChanged();
//       } else {
//         referencesBuilder_.addMessage(index, builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder addAllReferences(
//         java.lang.Iterable<? extends com.lightstep.tracer.grpc.Reference> values) {
//       if (referencesBuilder_ == null) {
//         ensureReferencesIsMutable();
//         com.google.protobuf.AbstractMessageLite.Builder.addAll(
//             values, references_);
//         onChanged();
//       } else {
//         referencesBuilder_.addAllMessages(values);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder clearReferences() {
//       if (referencesBuilder_ == null) {
//         references_ = java.util.Collections.emptyList();
//         bitField0_ = (bitField0_ & ~0x00000004);
//         onChanged();
//       } else {
//         referencesBuilder_.clear();
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public Builder removeReferences(int index) {
//       if (referencesBuilder_ == null) {
//         ensureReferencesIsMutable();
//         references_.remove(index);
//         onChanged();
//       } else {
//         referencesBuilder_.remove(index);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public com.lightstep.tracer.grpc.Reference.Builder getReferencesBuilder(
//         int index) {
//       return getReferencesFieldBuilder().getBuilder(index);
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public com.lightstep.tracer.grpc.ReferenceOrBuilder getReferencesOrBuilder(
//         int index) {
//       if (referencesBuilder_ == null) {
//         return references_.get(index);  } else {
//         return referencesBuilder_.getMessageOrBuilder(index);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public java.util.List<? extends com.lightstep.tracer.grpc.ReferenceOrBuilder> 
//          getReferencesOrBuilderList() {
//       if (referencesBuilder_ != null) {
//         return referencesBuilder_.getMessageOrBuilderList();
//       } else {
//         return java.util.Collections.unmodifiableList(references_);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public com.lightstep.tracer.grpc.Reference.Builder addReferencesBuilder() {
//       return getReferencesFieldBuilder().addBuilder(
//           com.lightstep.tracer.grpc.Reference.getDefaultInstance());
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public com.lightstep.tracer.grpc.Reference.Builder addReferencesBuilder(
//         int index) {
//       return getReferencesFieldBuilder().addBuilder(
//           index, com.lightstep.tracer.grpc.Reference.getDefaultInstance());
//     }
//     /**
//      * <code>repeated .lightstep.collector.Reference references = 3;</code>
//      */
//     public java.util.List<com.lightstep.tracer.grpc.Reference.Builder> 
//          getReferencesBuilderList() {
//       return getReferencesFieldBuilder().getBuilderList();
//     }
//     private com.google.protobuf.RepeatedFieldBuilderV3<
//         com.lightstep.tracer.grpc.Reference, com.lightstep.tracer.grpc.Reference.Builder, com.lightstep.tracer.grpc.ReferenceOrBuilder> 
//         getReferencesFieldBuilder() {
//       if (referencesBuilder_ == null) {
//         referencesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
//             com.lightstep.tracer.grpc.Reference, com.lightstep.tracer.grpc.Reference.Builder, com.lightstep.tracer.grpc.ReferenceOrBuilder>(
//                 references_,
//                 ((bitField0_ & 0x00000004) == 0x00000004),
//                 getParentForChildren(),
//                 isClean());
//         references_ = null;
//       }
//       return referencesBuilder_;
//     }

//     private com.google.protobuf.Timestamp startTimestamp_ = null;
//     private com.google.protobuf.SingleFieldBuilderV3<
//         com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> startTimestampBuilder_;
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public boolean hasStartTimestamp() {
//       return startTimestampBuilder_ != null || startTimestamp_ != null;
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public com.google.protobuf.Timestamp getStartTimestamp() {
//       if (startTimestampBuilder_ == null) {
//         return startTimestamp_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : startTimestamp_;
//       } else {
//         return startTimestampBuilder_.getMessage();
//       }
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public Builder setStartTimestamp(com.google.protobuf.Timestamp value) {
//       if (startTimestampBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         startTimestamp_ = value;
//         onChanged();
//       } else {
//         startTimestampBuilder_.setMessage(value);
//       }

//       return this;
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public Builder setStartTimestamp(
//         com.google.protobuf.Timestamp.Builder builderForValue) {
//       if (startTimestampBuilder_ == null) {
//         startTimestamp_ = builderForValue.build();
//         onChanged();
//       } else {
//         startTimestampBuilder_.setMessage(builderForValue.build());
//       }

//       return this;
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public Builder mergeStartTimestamp(com.google.protobuf.Timestamp value) {
//       if (startTimestampBuilder_ == null) {
//         if (startTimestamp_ != null) {
//           startTimestamp_ =
//             com.google.protobuf.Timestamp.newBuilder(startTimestamp_).mergeFrom(value).buildPartial();
//         } else {
//           startTimestamp_ = value;
//         }
//         onChanged();
//       } else {
//         startTimestampBuilder_.mergeFrom(value);
//       }

//       return this;
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public Builder clearStartTimestamp() {
//       if (startTimestampBuilder_ == null) {
//         startTimestamp_ = null;
//         onChanged();
//       } else {
//         startTimestamp_ = null;
//         startTimestampBuilder_ = null;
//       }

//       return this;
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public com.google.protobuf.Timestamp.Builder getStartTimestampBuilder() {
      
//       onChanged();
//       return getStartTimestampFieldBuilder().getBuilder();
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     public com.google.protobuf.TimestampOrBuilder getStartTimestampOrBuilder() {
//       if (startTimestampBuilder_ != null) {
//         return startTimestampBuilder_.getMessageOrBuilder();
//       } else {
//         return startTimestamp_ == null ?
//             com.google.protobuf.Timestamp.getDefaultInstance() : startTimestamp_;
//       }
//     }
//     /**
//      * <code>.google.protobuf.Timestamp start_timestamp = 4;</code>
//      */
//     private com.google.protobuf.SingleFieldBuilderV3<
//         com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
//         getStartTimestampFieldBuilder() {
//       if (startTimestampBuilder_ == null) {
//         startTimestampBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
//             com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
//                 getStartTimestamp(),
//                 getParentForChildren(),
//                 isClean());
//         startTimestamp_ = null;
//       }
//       return startTimestampBuilder_;
//     }

//     private long durationMicros_ ;
//     /**
//      * <code>uint64 duration_micros = 5;</code>
//      */
//     public long getDurationMicros() {
//       return durationMicros_;
//     }
//     /**
//      * <code>uint64 duration_micros = 5;</code>
//      */
//     public Builder setDurationMicros(long value) {
      
//       durationMicros_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>uint64 duration_micros = 5;</code>
//      */
//     public Builder clearDurationMicros() {
      
//       durationMicros_ = 0L;
//       onChanged();
//       return this;
//     }

//     private java.util.List<com.lightstep.tracer.grpc.KeyValue> tags_ =
//       java.util.Collections.emptyList();
//     private void ensureTagsIsMutable() {
//       if (!((bitField0_ & 0x00000020) == 0x00000020)) {
//         tags_ = new java.util.ArrayList<com.lightstep.tracer.grpc.KeyValue>(tags_);
//         bitField0_ |= 0x00000020;
//        }
//     }

//     private com.google.protobuf.RepeatedFieldBuilderV3<
//         com.lightstep.tracer.grpc.KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, com.lightstep.tracer.grpc.KeyValueOrBuilder> tagsBuilder_;

//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public java.util.List<com.lightstep.tracer.grpc.KeyValue> getTagsList() {
//       if (tagsBuilder_ == null) {
//         return java.util.Collections.unmodifiableList(tags_);
//       } else {
//         return tagsBuilder_.getMessageList();
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public int getTagsCount() {
//       if (tagsBuilder_ == null) {
//         return tags_.size();
//       } else {
//         return tagsBuilder_.getCount();
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public com.lightstep.tracer.grpc.KeyValue getTags(int index) {
//       if (tagsBuilder_ == null) {
//         return tags_.get(index);
//       } else {
//         return tagsBuilder_.getMessage(index);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder setTags(
//         int index, com.lightstep.tracer.grpc.KeyValue value) {
//       if (tagsBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureTagsIsMutable();
//         tags_.set(index, value);
//         onChanged();
//       } else {
//         tagsBuilder_.setMessage(index, value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder setTags(
//         int index, com.lightstep.tracer.grpc.KeyValue.Builder builderForValue) {
//       if (tagsBuilder_ == null) {
//         ensureTagsIsMutable();
//         tags_.set(index, builderForValue.build());
//         onChanged();
//       } else {
//         tagsBuilder_.setMessage(index, builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder addTags(com.lightstep.tracer.grpc.KeyValue value) {
//       if (tagsBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureTagsIsMutable();
//         tags_.add(value);
//         onChanged();
//       } else {
//         tagsBuilder_.addMessage(value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder addTags(
//         int index, com.lightstep.tracer.grpc.KeyValue value) {
//       if (tagsBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureTagsIsMutable();
//         tags_.add(index, value);
//         onChanged();
//       } else {
//         tagsBuilder_.addMessage(index, value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder addTags(
//         com.lightstep.tracer.grpc.KeyValue.Builder builderForValue) {
//       if (tagsBuilder_ == null) {
//         ensureTagsIsMutable();
//         tags_.add(builderForValue.build());
//         onChanged();
//       } else {
//         tagsBuilder_.addMessage(builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder addTags(
//         int index, com.lightstep.tracer.grpc.KeyValue.Builder builderForValue) {
//       if (tagsBuilder_ == null) {
//         ensureTagsIsMutable();
//         tags_.add(index, builderForValue.build());
//         onChanged();
//       } else {
//         tagsBuilder_.addMessage(index, builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder addAllTags(
//         java.lang.Iterable<? extends com.lightstep.tracer.grpc.KeyValue> values) {
//       if (tagsBuilder_ == null) {
//         ensureTagsIsMutable();
//         com.google.protobuf.AbstractMessageLite.Builder.addAll(
//             values, tags_);
//         onChanged();
//       } else {
//         tagsBuilder_.addAllMessages(values);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder clearTags() {
//       if (tagsBuilder_ == null) {
//         tags_ = java.util.Collections.emptyList();
//         bitField0_ = (bitField0_ & ~0x00000020);
//         onChanged();
//       } else {
//         tagsBuilder_.clear();
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public Builder removeTags(int index) {
//       if (tagsBuilder_ == null) {
//         ensureTagsIsMutable();
//         tags_.remove(index);
//         onChanged();
//       } else {
//         tagsBuilder_.remove(index);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public com.lightstep.tracer.grpc.KeyValue.Builder getTagsBuilder(
//         int index) {
//       return getTagsFieldBuilder().getBuilder(index);
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public com.lightstep.tracer.grpc.KeyValueOrBuilder getTagsOrBuilder(
//         int index) {
//       if (tagsBuilder_ == null) {
//         return tags_.get(index);  } else {
//         return tagsBuilder_.getMessageOrBuilder(index);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public java.util.List<? extends com.lightstep.tracer.grpc.KeyValueOrBuilder> 
//          getTagsOrBuilderList() {
//       if (tagsBuilder_ != null) {
//         return tagsBuilder_.getMessageOrBuilderList();
//       } else {
//         return java.util.Collections.unmodifiableList(tags_);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public com.lightstep.tracer.grpc.KeyValue.Builder addTagsBuilder() {
//       return getTagsFieldBuilder().addBuilder(
//           com.lightstep.tracer.grpc.KeyValue.getDefaultInstance());
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public com.lightstep.tracer.grpc.KeyValue.Builder addTagsBuilder(
//         int index) {
//       return getTagsFieldBuilder().addBuilder(
//           index, com.lightstep.tracer.grpc.KeyValue.getDefaultInstance());
//     }
//     /**
//      * <code>repeated .lightstep.collector.KeyValue tags = 6;</code>
//      */
//     public java.util.List<com.lightstep.tracer.grpc.KeyValue.Builder> 
//          getTagsBuilderList() {
//       return getTagsFieldBuilder().getBuilderList();
//     }
//     private com.google.protobuf.RepeatedFieldBuilderV3<
//         com.lightstep.tracer.grpc.KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, com.lightstep.tracer.grpc.KeyValueOrBuilder> 
//         getTagsFieldBuilder() {
//       if (tagsBuilder_ == null) {
//         tagsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
//             com.lightstep.tracer.grpc.KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, com.lightstep.tracer.grpc.KeyValueOrBuilder>(
//                 tags_,
//                 ((bitField0_ & 0x00000020) == 0x00000020),
//                 getParentForChildren(),
//                 isClean());
//         tags_ = null;
//       }
//       return tagsBuilder_;
//     }

//     private java.util.List<com.lightstep.tracer.grpc.Log> logs_ =
//       java.util.Collections.emptyList();
//     private void ensureLogsIsMutable() {
//       if (!((bitField0_ & 0x00000040) == 0x00000040)) {
//         logs_ = new java.util.ArrayList<com.lightstep.tracer.grpc.Log>(logs_);
//         bitField0_ |= 0x00000040;
//        }
//     }

//     private com.google.protobuf.RepeatedFieldBuilderV3<
//         com.lightstep.tracer.grpc.Log, com.lightstep.tracer.grpc.Log.Builder, com.lightstep.tracer.grpc.LogOrBuilder> logsBuilder_;

//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public java.util.List<com.lightstep.tracer.grpc.Log> getLogsList() {
//       if (logsBuilder_ == null) {
//         return java.util.Collections.unmodifiableList(logs_);
//       } else {
//         return logsBuilder_.getMessageList();
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public int getLogsCount() {
//       if (logsBuilder_ == null) {
//         return logs_.size();
//       } else {
//         return logsBuilder_.getCount();
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public com.lightstep.tracer.grpc.Log getLogs(int index) {
//       if (logsBuilder_ == null) {
//         return logs_.get(index);
//       } else {
//         return logsBuilder_.getMessage(index);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder setLogs(
//         int index, com.lightstep.tracer.grpc.Log value) {
//       if (logsBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureLogsIsMutable();
//         logs_.set(index, value);
//         onChanged();
//       } else {
//         logsBuilder_.setMessage(index, value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder setLogs(
//         int index, com.lightstep.tracer.grpc.Log.Builder builderForValue) {
//       if (logsBuilder_ == null) {
//         ensureLogsIsMutable();
//         logs_.set(index, builderForValue.build());
//         onChanged();
//       } else {
//         logsBuilder_.setMessage(index, builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder addLogs(com.lightstep.tracer.grpc.Log value) {
//       if (logsBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureLogsIsMutable();
//         logs_.add(value);
//         onChanged();
//       } else {
//         logsBuilder_.addMessage(value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder addLogs(
//         int index, com.lightstep.tracer.grpc.Log value) {
//       if (logsBuilder_ == null) {
//         if (value == null) {
//           throw new NullPointerException();
//         }
//         ensureLogsIsMutable();
//         logs_.add(index, value);
//         onChanged();
//       } else {
//         logsBuilder_.addMessage(index, value);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder addLogs(
//         com.lightstep.tracer.grpc.Log.Builder builderForValue) {
//       if (logsBuilder_ == null) {
//         ensureLogsIsMutable();
//         logs_.add(builderForValue.build());
//         onChanged();
//       } else {
//         logsBuilder_.addMessage(builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder addLogs(
//         int index, com.lightstep.tracer.grpc.Log.Builder builderForValue) {
//       if (logsBuilder_ == null) {
//         ensureLogsIsMutable();
//         logs_.add(index, builderForValue.build());
//         onChanged();
//       } else {
//         logsBuilder_.addMessage(index, builderForValue.build());
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder addAllLogs(
//         java.lang.Iterable<? extends com.lightstep.tracer.grpc.Log> values) {
//       if (logsBuilder_ == null) {
//         ensureLogsIsMutable();
//         com.google.protobuf.AbstractMessageLite.Builder.addAll(
//             values, logs_);
//         onChanged();
//       } else {
//         logsBuilder_.addAllMessages(values);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder clearLogs() {
//       if (logsBuilder_ == null) {
//         logs_ = java.util.Collections.emptyList();
//         bitField0_ = (bitField0_ & ~0x00000040);
//         onChanged();
//       } else {
//         logsBuilder_.clear();
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public Builder removeLogs(int index) {
//       if (logsBuilder_ == null) {
//         ensureLogsIsMutable();
//         logs_.remove(index);
//         onChanged();
//       } else {
//         logsBuilder_.remove(index);
//       }
//       return this;
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public com.lightstep.tracer.grpc.Log.Builder getLogsBuilder(
//         int index) {
//       return getLogsFieldBuilder().getBuilder(index);
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public com.lightstep.tracer.grpc.LogOrBuilder getLogsOrBuilder(
//         int index) {
//       if (logsBuilder_ == null) {
//         return logs_.get(index);  } else {
//         return logsBuilder_.getMessageOrBuilder(index);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public java.util.List<? extends com.lightstep.tracer.grpc.LogOrBuilder> 
//          getLogsOrBuilderList() {
//       if (logsBuilder_ != null) {
//         return logsBuilder_.getMessageOrBuilderList();
//       } else {
//         return java.util.Collections.unmodifiableList(logs_);
//       }
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public com.lightstep.tracer.grpc.Log.Builder addLogsBuilder() {
//       return getLogsFieldBuilder().addBuilder(
//           com.lightstep.tracer.grpc.Log.getDefaultInstance());
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public com.lightstep.tracer.grpc.Log.Builder addLogsBuilder(
//         int index) {
//       return getLogsFieldBuilder().addBuilder(
//           index, com.lightstep.tracer.grpc.Log.getDefaultInstance());
//     }
//     /**
//      * <code>repeated .lightstep.collector.Log logs = 7;</code>
//      */
//     public java.util.List<com.lightstep.tracer.grpc.Log.Builder> 
//          getLogsBuilderList() {
//       return getLogsFieldBuilder().getBuilderList();
//     }
//     private com.google.protobuf.RepeatedFieldBuilderV3<
//         com.lightstep.tracer.grpc.Log, com.lightstep.tracer.grpc.Log.Builder, com.lightstep.tracer.grpc.LogOrBuilder> 
//         getLogsFieldBuilder() {
//       if (logsBuilder_ == null) {
//         logsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
//             com.lightstep.tracer.grpc.Log, com.lightstep.tracer.grpc.Log.Builder, com.lightstep.tracer.grpc.LogOrBuilder>(
//                 logs_,
//                 ((bitField0_ & 0x00000040) == 0x00000040),
//                 getParentForChildren(),
//                 isClean());
//         logs_ = null;
//       }
//       return logsBuilder_;
//     }
//     @java.lang.Override
//     public final Builder setUnknownFields(
//         final com.google.protobuf.UnknownFieldSet unknownFields) {
//       return super.setUnknownFieldsProto3(unknownFields);
//     }

//     @java.lang.Override
//     public final Builder mergeUnknownFields(
//         final com.google.protobuf.UnknownFieldSet unknownFields) {
//       return super.mergeUnknownFields(unknownFields);
//     }


//     // @@protoc_insertion_point(builder_scope:lightstep.collector.Span)
//   }

//   // @@protoc_insertion_point(class_scope:lightstep.collector.Span)
//   private static final com.lightstep.tracer.grpc.Span DEFAULT_INSTANCE;
//   static {
//     DEFAULT_INSTANCE = new com.lightstep.tracer.grpc.Span();
//   }

//   public static com.lightstep.tracer.grpc.Span getDefaultInstance() {
//     return DEFAULT_INSTANCE;
//   }

//   private static final com.google.protobuf.Parser<Span>
//       PARSER = new com.google.protobuf.AbstractParser<Span>() {
//     @java.lang.Override
//     public Span parsePartialFrom(
//         com.google.protobuf.CodedInputStream input,
//         com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//         throws com.google.protobuf.InvalidProtocolBufferException {
//       return new Span(input, extensionRegistry);
//     }
//   };

//   public static com.google.protobuf.Parser<Span> parser() {
//     return PARSER;
//   }

//   @java.lang.Override
//   public com.google.protobuf.Parser<Span> getParserForType() {
//     return PARSER;
//   }

//   @java.lang.Override
//   public com.lightstep.tracer.grpc.Span getDefaultInstanceForType() {
//     return DEFAULT_INSTANCE;
//   }

// }