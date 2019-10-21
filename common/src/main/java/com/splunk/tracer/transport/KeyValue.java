package com.splunk.tracer.transport;

/**
 * <pre>
 * Represent both tags and log fields.
 * </pre>
 *
 */
public  final class KeyValue { 
   
    private volatile String key_;

    private volatile int valueCase_;
  
    private volatile Object value_;

    public int hashCode() {
        return key_.length();
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
         return true;
        }
        if (!(obj instanceof com.splunk.tracer.transport.KeyValue)) {
          return super.equals(obj);
        }
        com.splunk.tracer.transport.KeyValue other = (com.splunk.tracer.transport.KeyValue) obj;
        boolean result = true;
        result = result && getKey()
            .equals(other.getKey());
        result = result && getValueCase() == other.getValueCase();
        if (!result) return false;
        switch (valueCase_) {
          case 2:
            result = result && getStringValue()
                .equals(other.getStringValue());
            break;
          case 3:
            result = result && (getIntValue()
                == other.getIntValue());
            break;
          case 4:
            result = result && (
                java.lang.Double.doubleToLongBits(getDoubleValue())
                == java.lang.Double.doubleToLongBits(
                    other.getDoubleValue()));
            break;
          case 5:
            result = result && (getBoolValue()
                == other.getBoolValue());
            break;
          case 6:
            result = result && getJsonValue()
                .equals(other.getJsonValue());
            break;
          case 0:
          default:
        }
        result = result;
        return result;
    }
    public String getKey() {
        return key_;
    }

    public Object getValue() {
        return value_;
    }
    public int getValueCase() {
        return valueCase_;
    }
    public String getStringValue() {
       if (valueCase_ == 2){
            return (String) value_;
        } else {
            return value_.toString();
        }
    }

    public long getIntValue() {
       if (valueCase_ == 3) {
            return (long) value_;
        } else {
            return 0;
        }
    }
    public Boolean getBoolValue() {
        if (valueCase_ == 4){
            return (boolean) value_;
        } else {
            return false;
        }
    }

    public Double getDoubleValue() {
        if (valueCase_ == 5){
            return (Double) value_;
        } else {
            return new Double(0);
        }
    }

    public String getJsonValue() {
        if (valueCase_ == 6){
            return (String) value_;
        } else {
            return "{}";
        }
    }

    private KeyValue(KeyValueBuilder builder) 
    { 
        this.key_ = builder.key_;
        this.value_ = builder.value_; 
    } 
  
    // Static class Builder 
    public static class KeyValueBuilder { 
  
        /// instance fields 
        private String key_;

        private int valueCase_;

        private Object value_;


        public KeyValueBuilder() {
        }      

        public KeyValueBuilder(String key, Object value) {
            this.key_ = key;
            this.value_ = value;
        }
        public KeyValueBuilder setKey(String key) {
            this.key_ = key;
            return this;
        }
        public KeyValueBuilder setValue(Object value) {
            this.value_ = value;
            return this;
        }
        public KeyValue build() {
            // call the private constructor in the outer class
            return new KeyValue(this);
        }

        public KeyValueBuilder setStringValue(String value) 
        { 
            this.value_ = value;
            this.valueCase_ = 2;
            return this; 
        }

        public KeyValueBuilder setIntValue(long value) 
        { 
            this.value_ = value;
            this.valueCase_ = 3;
            return this; 
        }

        public KeyValueBuilder setDoubleValue(double value) 
        { 
            this.value_ = value;
            this.valueCase_ = 4;
            return this; 
        }  

        public KeyValueBuilder setBoolValue(boolean value) 
        { 
            this.value_ = value;
            this.valueCase_ = 5; 
            return this; 
        }

        public KeyValueBuilder setJsonValue(String value) 
        { 
            this.value_ = value;
            this.valueCase_ = 6; 
            return this; 
        }
    }

    public static KeyValueBuilder KeyValueBuilder() {
        return new KeyValueBuilder();
    }
  
}    
//   private KeyValue(
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
//             java.lang.String s = input.readStringRequireUtf8();

//             key_ = s;
//             break;
//           }
//           case 18: {
//             java.lang.String s = input.readStringRequireUtf8();
//             valueCase_ = 2;
//             value_ = s;
//             break;
//           }
//           case 24: {
//             valueCase_ = 3;
//             value_ = input.readInt64();
//             break;
//           }
//           case 33: {
//             valueCase_ = 4;
//             value_ = input.readDouble();
//             break;
//           }
//           case 40: {
//             valueCase_ = 5;
//             value_ = input.readBool();
//             break;
//           }
//           case 50: {
//             java.lang.String s = input.readStringRequireUtf8();
//             valueCase_ = 6;
//             value_ = s;
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
//       this.unknownFields = unknownFields.build();
//       makeExtensionsImmutable();
//     }
//   }


//   @java.lang.Override
//   protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//       internalGetFieldAccessorTable() {
//     return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_KeyValue_fieldAccessorTable
//         .ensureFieldAccessorsInitialized(
//             com.lightstep.tracer.grpc.KeyValue.class, com.lightstep.tracer.grpc.KeyValue.Builder.class);
//   }

//   private java.lang.Object value_;
//   public enum ValueCase
//       implements com.google.protobuf.Internal.EnumLite {
//     STRING_VALUE(2),
//     INT_VALUE(3),
//     DOUBLE_VALUE(4),
//     BOOL_VALUE(5),
//     JSON_VALUE(6),
//     VALUE_NOT_SET(0);
//     private final int value;
//     private ValueCase(int value) {
//       this.value = value;
//     }
//     /**
//      * @deprecated Use {@link #forNumber(int)} instead.
//      */
//     @java.lang.Deprecated
//     public static ValueCase valueOf(int value) {
//       return forNumber(value);
//     }

//     public static ValueCase forNumber(int value) {
//       switch (value) {
//         case 2: return STRING_VALUE;
//         case 3: return INT_VALUE;
//         case 4: return DOUBLE_VALUE;
//         case 5: return BOOL_VALUE;
//         case 6: return JSON_VALUE;
//         case 0: return VALUE_NOT_SET;
//         default: return null;
//       }
//     }
//     public int getNumber() {
//       return this.value;
//     }
//   };

//   public ValueCase
//   getValueCase() {
//     return ValueCase.forNumber(
//         valueCase_);
//   }


//   public static final int STRING_VALUE_FIELD_NUMBER = 2;
//   /**
//    * <pre>
//    * Holds arbitrary string data; well-formed JSON strings should go in
//    * json_value.
//    * </pre>
//    *
//    * <code>string string_value = 2;</code>
//    */
//   public java.lang.String getStringValue() {
//     java.lang.Object ref = "";
//     if (valueCase_ == 2) {
//       ref = value_;
//     }
//     if (ref instanceof java.lang.String) {
//       return (java.lang.String) ref;
//     } else {
//       com.google.protobuf.ByteString bs = 
//           (com.google.protobuf.ByteString) ref;
//       java.lang.String s = bs.toStringUtf8();
//       if (valueCase_ == 2) {
//         value_ = s;
//       }
//       return s;
//     }
//   }


//   public long getIntValue() {
//     if (valueCase_ == 3) {
//       return (java.lang.Long) value_;
//     }
//     return 0L;
//   }


//   public double getDoubleValue() {
//     if (valueCase_ == 4) {
//       return (java.lang.Double) value_;
//     }
//     return 0D;
//   }

//   public boolean getBoolValue() {
//     if (valueCase_ == 5) {
//       return (java.lang.Boolean) value_;
//     }
//     return false;
//   }


//   public java.lang.String getJsonValue() {
//     java.lang.Object ref = "";
//     if (valueCase_ == 6) {
//       ref = value_;
//     }
//     if (ref instanceof java.lang.String) {
//       return (java.lang.String) ref;
//     } else {
//       com.google.protobuf.ByteString bs = 
//           (com.google.protobuf.ByteString) ref;
//       java.lang.String s = bs.toStringUtf8();
//       if (valueCase_ == 6) {
//         value_ = s;
//       }
//       return s;
//     }
//   }




//   @java.lang.Override
//   public void writeTo(com.google.protobuf.CodedOutputStream output)
//                       throws java.io.IOException {
//     if (!getKeyBytes().isEmpty()) {
//       com.google.protobuf.GeneratedMessageV3.writeString(output, 1, key_);
//     }
//     if (valueCase_ == 2) {
//       com.google.protobuf.GeneratedMessageV3.writeString(output, 2, value_);
//     }
//     if (valueCase_ == 3) {
//       output.writeInt64(
//           3, (long)((java.lang.Long) value_));
//     }
//     if (valueCase_ == 4) {
//       output.writeDouble(
//           4, (double)((java.lang.Double) value_));
//     }
//     if (valueCase_ == 5) {
//       output.writeBool(
//           5, (boolean)((java.lang.Boolean) value_));
//     }
//     if (valueCase_ == 6) {
//       com.google.protobuf.GeneratedMessageV3.writeString(output, 6, value_);
//     }
//     unknownFields.writeTo(output);
//   }

//   @java.lang.Override
//   public int getSerializedSize() {
//     int size = memoizedSize;
//     if (size != -1) return size;

//     size = 0;
//     if (!getKeyBytes().isEmpty()) {
//       size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, key_);
//     }
//     if (valueCase_ == 2) {
//       size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, value_);
//     }
//     if (valueCase_ == 3) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeInt64Size(
//             3, (long)((java.lang.Long) value_));
//     }
//     if (valueCase_ == 4) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeDoubleSize(
//             4, (double)((java.lang.Double) value_));
//     }
//     if (valueCase_ == 5) {
//       size += com.google.protobuf.CodedOutputStream
//         .computeBoolSize(
//             5, (boolean)((java.lang.Boolean) value_));
//     }
//     if (valueCase_ == 6) {
//       size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, value_);
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
//     if (!(obj instanceof com.lightstep.tracer.grpc.KeyValue)) {
//       return super.equals(obj);
//     }
//     com.lightstep.tracer.grpc.KeyValue other = (com.lightstep.tracer.grpc.KeyValue) obj;

//     boolean result = true;
//     result = result && getKey()
//         .equals(other.getKey());
//     result = result && getValueCase().equals(
//         other.getValueCase());
//     if (!result) return false;
//     switch (valueCase_) {
//       case 2:
//         result = result && getStringValue()
//             .equals(other.getStringValue());
//         break;
//       case 3:
//         result = result && (getIntValue()
//             == other.getIntValue());
//         break;
//       case 4:
//         result = result && (
//             java.lang.Double.doubleToLongBits(getDoubleValue())
//             == java.lang.Double.doubleToLongBits(
//                 other.getDoubleValue()));
//         break;
//       case 5:
//         result = result && (getBoolValue()
//             == other.getBoolValue());
//         break;
//       case 6:
//         result = result && getJsonValue()
//             .equals(other.getJsonValue());
//         break;
//       case 0:
//       default:
//     }
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
//     hash = (37 * hash) + KEY_FIELD_NUMBER;
//     hash = (53 * hash) + getKey().hashCode();
//     switch (valueCase_) {
//       case 2:
//         hash = (37 * hash) + STRING_VALUE_FIELD_NUMBER;
//         hash = (53 * hash) + getStringValue().hashCode();
//         break;
//       case 3:
//         hash = (37 * hash) + INT_VALUE_FIELD_NUMBER;
//         hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
//             getIntValue());
//         break;
//       case 4:
//         hash = (37 * hash) + DOUBLE_VALUE_FIELD_NUMBER;
//         hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
//             java.lang.Double.doubleToLongBits(getDoubleValue()));
//         break;
//       case 5:
//         hash = (37 * hash) + BOOL_VALUE_FIELD_NUMBER;
//         hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
//             getBoolValue());
//         break;
//       case 6:
//         hash = (37 * hash) + JSON_VALUE_FIELD_NUMBER;
//         hash = (53 * hash) + getJsonValue().hashCode();
//         break;
//       case 0:
//       default:
//     }
//     hash = (29 * hash) + unknownFields.hashCode();
//     memoizedHashCode = hash;
//     return hash;
//   }

//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       java.nio.ByteBuffer data)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       java.nio.ByteBuffer data,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       com.google.protobuf.ByteString data)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       com.google.protobuf.ByteString data,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(byte[] data)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       byte[] data,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws com.google.protobuf.InvalidProtocolBufferException {
//     return PARSER.parseFrom(data, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(java.io.InputStream input)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       java.io.InputStream input,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseDelimitedFrom(java.io.InputStream input)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseDelimitedWithIOException(PARSER, input);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseDelimitedFrom(
//       java.io.InputStream input,
//       com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
//       com.google.protobuf.CodedInputStream input)
//       throws java.io.IOException {
//     return com.google.protobuf.GeneratedMessageV3
//         .parseWithIOException(PARSER, input);
//   }
//   public static com.lightstep.tracer.grpc.KeyValue parseFrom(
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
//   public static Builder newBuilder(com.lightstep.tracer.grpc.KeyValue prototype) {
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
//    * <pre>
//    * Represent both tags and log fields.
//    * </pre>
//    *
//    * Protobuf type {@code lightstep.collector.KeyValue}
//    */
//   public static final class Builder extends
//       com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
//       // @@protoc_insertion_point(builder_implements:lightstep.collector.KeyValue)
//       com.lightstep.tracer.grpc.KeyValueOrBuilder {
//     public static final com.google.protobuf.Descriptors.Descriptor
//         getDescriptor() {
//       return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_KeyValue_descriptor;
//     }

//     @java.lang.Override
//     protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
//         internalGetFieldAccessorTable() {
//       return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_KeyValue_fieldAccessorTable
//           .ensureFieldAccessorsInitialized(
//               com.lightstep.tracer.grpc.KeyValue.class, com.lightstep.tracer.grpc.KeyValue.Builder.class);
//     }

//     // Construct using com.lightstep.tracer.grpc.KeyValue.newBuilder()
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
//       }
//     }
//     @java.lang.Override
//     public Builder clear() {
//       super.clear();
//       key_ = "";

//       valueCase_ = 0;
//       value_ = null;
//       return this;
//     }

//     @java.lang.Override
//     public com.google.protobuf.Descriptors.Descriptor
//         getDescriptorForType() {
//       return com.lightstep.tracer.grpc.Collector.internal_static_lightstep_collector_KeyValue_descriptor;
//     }

//     @java.lang.Override
//     public com.lightstep.tracer.grpc.KeyValue getDefaultInstanceForType() {
//       return com.lightstep.tracer.grpc.KeyValue.getDefaultInstance();
//     }


//     @java.lang.Override
//     public com.lightstep.tracer.grpc.KeyValue buildPartial() {
//       com.lightstep.tracer.grpc.KeyValue result = new com.lightstep.tracer.grpc.KeyValue(this);
//       result.key_ = key_;
//       if (valueCase_ == 2) {
//         result.value_ = value_;
//       }
//       if (valueCase_ == 3) {
//         result.value_ = value_;
//       }
//       if (valueCase_ == 4) {
//         result.value_ = value_;
//       }
//       if (valueCase_ == 5) {
//         result.value_ = value_;
//       }
//       if (valueCase_ == 6) {
//         result.value_ = value_;
//       }
//       result.valueCase_ = valueCase_;
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
//       if (other instanceof com.lightstep.tracer.grpc.KeyValue) {
//         return mergeFrom((com.lightstep.tracer.grpc.KeyValue)other);
//       } else {
//         super.mergeFrom(other);
//         return this;
//       }
//     }

//     public Builder mergeFrom(com.lightstep.tracer.grpc.KeyValue other) {
//       if (other == com.lightstep.tracer.grpc.KeyValue.getDefaultInstance()) return this;
//       if (!other.getKey().isEmpty()) {
//         key_ = other.key_;
//         onChanged();
//       }
//       switch (other.getValueCase()) {
//         case STRING_VALUE: {
//           valueCase_ = 2;
//           value_ = other.value_;
//           onChanged();
//           break;
//         }
//         case INT_VALUE: {
//           setIntValue(other.getIntValue());
//           break;
//         }
//         case DOUBLE_VALUE: {
//           setDoubleValue(other.getDoubleValue());
//           break;
//         }
//         case BOOL_VALUE: {
//           setBoolValue(other.getBoolValue());
//           break;
//         }
//         case JSON_VALUE: {
//           valueCase_ = 6;
//           value_ = other.value_;
//           onChanged();
//           break;
//         }
//         case VALUE_NOT_SET: {
//           break;
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
//       com.lightstep.tracer.grpc.KeyValue parsedMessage = null;
//       try {
//         parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
//       } catch (com.google.protobuf.InvalidProtocolBufferException e) {
//         parsedMessage = (com.lightstep.tracer.grpc.KeyValue) e.getUnfinishedMessage();
//         throw e.unwrapIOException();
//       } finally {
//         if (parsedMessage != null) {
//           mergeFrom(parsedMessage);
//         }
//       }
//       return this;
//     }
//     private int valueCase_ = 0;
//     private java.lang.Object value_;
//     public ValueCase
//         getValueCase() {
//       return ValueCase.forNumber(
//           valueCase_);
//     }

//     public Builder clearValue() {
//       valueCase_ = 0;
//       value_ = null;
//       onChanged();
//       return this;
//     }


//     private java.lang.Object key_ = "";
//     /**
//      * <code>string key = 1;</code>
//      */
//     public java.lang.String getKey() {
//       java.lang.Object ref = key_;
//       if (!(ref instanceof java.lang.String)) {
//         com.google.protobuf.ByteString bs =
//             (com.google.protobuf.ByteString) ref;
//         java.lang.String s = bs.toStringUtf8();
//         key_ = s;
//         return s;
//       } else {
//         return (java.lang.String) ref;
//       }
//     }
//     /**
//      * <code>string key = 1;</code>
//      */
//     public com.google.protobuf.ByteString
//         getKeyBytes() {
//       java.lang.Object ref = key_;
//       if (ref instanceof String) {
//         com.google.protobuf.ByteString b = 
//             com.google.protobuf.ByteString.copyFromUtf8(
//                 (java.lang.String) ref);
//         key_ = b;
//         return b;
//       } else {
//         return (com.google.protobuf.ByteString) ref;
//       }
//     }
//     /**
//      * <code>string key = 1;</code>
//      */
//     public Builder setKey(
//         java.lang.String value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
  
//       key_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>string key = 1;</code>
//      */
//     public Builder clearKey() {
      
//       key_ = getDefaultInstance().getKey();
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>string key = 1;</code>
//      */
//     public Builder setKeyBytes(
//         com.google.protobuf.ByteString value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
//   checkByteStringIsUtf8(value);
      
//       key_ = value;
//       onChanged();
//       return this;
//     }

//     /**
//      * <pre>
//      * Holds arbitrary string data; well-formed JSON strings should go in
//      * json_value.
//      * </pre>
//      *
//      * <code>string string_value = 2;</code>
//      */
//     public java.lang.String getStringValue() {
//       java.lang.Object ref = "";
//       if (valueCase_ == 2) {
//         ref = value_;
//       }
//       if (!(ref instanceof java.lang.String)) {
//         com.google.protobuf.ByteString bs =
//             (com.google.protobuf.ByteString) ref;
//         java.lang.String s = bs.toStringUtf8();
//         if (valueCase_ == 2) {
//           value_ = s;
//         }
//         return s;
//       } else {
//         return (java.lang.String) ref;
//       }
//     }
//     /**
//      * <pre>
//      * Holds arbitrary string data; well-formed JSON strings should go in
//      * json_value.
//      * </pre>
//      *
//      * <code>string string_value = 2;</code>
//      */
//     public com.google.protobuf.ByteString
//         getStringValueBytes() {
//       java.lang.Object ref = "";
//       if (valueCase_ == 2) {
//         ref = value_;
//       }
//       if (ref instanceof String) {
//         com.google.protobuf.ByteString b = 
//             com.google.protobuf.ByteString.copyFromUtf8(
//                 (java.lang.String) ref);
//         if (valueCase_ == 2) {
//           value_ = b;
//         }
//         return b;
//       } else {
//         return (com.google.protobuf.ByteString) ref;
//       }
//     }
//     /**
//      * <pre>
//      * Holds arbitrary string data; well-formed JSON strings should go in
//      * json_value.
//      * </pre>
//      *
//      * <code>string string_value = 2;</code>
//      */
//     public Builder setStringValue(
//         java.lang.String value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
//   valueCase_ = 2;
//       value_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <pre>
//      * Holds arbitrary string data; well-formed JSON strings should go in
//      * json_value.
//      * </pre>
//      *
//      * <code>string string_value = 2;</code>
//      */
//     public Builder clearStringValue() {
//       if (valueCase_ == 2) {
//         valueCase_ = 0;
//         value_ = null;
//         onChanged();
//       }
//       return this;
//     }
//     /**
//      * <pre>
//      * Holds arbitrary string data; well-formed JSON strings should go in
//      * json_value.
//      * </pre>
//      *
//      * <code>string string_value = 2;</code>
//      */
//     public Builder setStringValueBytes(
//         com.google.protobuf.ByteString value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
//   checkByteStringIsUtf8(value);
//       valueCase_ = 2;
//       value_ = value;
//       onChanged();
//       return this;
//     }

//     /**
//      * <code>int64 int_value = 3;</code>
//      */
//     public long getIntValue() {
//       if (valueCase_ == 3) {
//         return (java.lang.Long) value_;
//       }
//       return 0L;
//     }
//     /**
//      * <code>int64 int_value = 3;</code>
//      */
//     public Builder setIntValue(long value) {
//       valueCase_ = 3;
//       value_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>int64 int_value = 3;</code>
//      */
//     public Builder clearIntValue() {
//       if (valueCase_ == 3) {
//         valueCase_ = 0;
//         value_ = null;
//         onChanged();
//       }
//       return this;
//     }

//     /**
//      * <code>double double_value = 4;</code>
//      */
//     public double getDoubleValue() {
//       if (valueCase_ == 4) {
//         return (java.lang.Double) value_;
//       }
//       return 0D;
//     }
//     /**
//      * <code>double double_value = 4;</code>
//      */
//     public Builder setDoubleValue(double value) {
//       valueCase_ = 4;
//       value_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>double double_value = 4;</code>
//      */
//     public Builder clearDoubleValue() {
//       if (valueCase_ == 4) {
//         valueCase_ = 0;
//         value_ = null;
//         onChanged();
//       }
//       return this;
//     }

//     /**
//      * <code>bool bool_value = 5;</code>
//      */
//     public boolean getBoolValue() {
//       if (valueCase_ == 5) {
//         return (java.lang.Boolean) value_;
//       }
//       return false;
//     }
//     /**
//      * <code>bool bool_value = 5;</code>
//      */
//     public Builder setBoolValue(boolean value) {
//       valueCase_ = 5;
//       value_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <code>bool bool_value = 5;</code>
//      */
//     public Builder clearBoolValue() {
//       if (valueCase_ == 5) {
//         valueCase_ = 0;
//         value_ = null;
//         onChanged();
//       }
//       return this;
//     }

//     /**
//      * <pre>
//      * Must be a well-formed JSON value. Truncated JSON should go in
//      * string_value. Should not be used for tags.
//      * </pre>
//      *
//      * <code>string json_value = 6;</code>
//      */
//     public java.lang.String getJsonValue() {
//       java.lang.Object ref = "";
//       if (valueCase_ == 6) {
//         ref = value_;
//       }
//       if (!(ref instanceof java.lang.String)) {
//         com.google.protobuf.ByteString bs =
//             (com.google.protobuf.ByteString) ref;
//         java.lang.String s = bs.toStringUtf8();
//         if (valueCase_ == 6) {
//           value_ = s;
//         }
//         return s;
//       } else {
//         return (java.lang.String) ref;
//       }
//     }
//     /**
//      * <pre>
//      * Must be a well-formed JSON value. Truncated JSON should go in
//      * string_value. Should not be used for tags.
//      * </pre>
//      *
//      * <code>string json_value = 6;</code>
//      */
//     public com.google.protobuf.ByteString
//         getJsonValueBytes() {
//       java.lang.Object ref = "";
//       if (valueCase_ == 6) {
//         ref = value_;
//       }
//       if (ref instanceof String) {
//         com.google.protobuf.ByteString b = 
//             com.google.protobuf.ByteString.copyFromUtf8(
//                 (java.lang.String) ref);
//         if (valueCase_ == 6) {
//           value_ = b;
//         }
//         return b;
//       } else {
//         return (com.google.protobuf.ByteString) ref;
//       }
//     }
//     /**
//      * <pre>
//      * Must be a well-formed JSON value. Truncated JSON should go in
//      * string_value. Should not be used for tags.
//      * </pre>
//      *
//      * <code>string json_value = 6;</code>
//      */
//     public Builder setJsonValue(
//         java.lang.String value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
//   valueCase_ = 6;
//       value_ = value;
//       onChanged();
//       return this;
//     }
//     /**
//      * <pre>
//      * Must be a well-formed JSON value. Truncated JSON should go in
//      * string_value. Should not be used for tags.
//      * </pre>
//      *
//      * <code>string json_value = 6;</code>
//      */
//     public Builder clearJsonValue() {
//       if (valueCase_ == 6) {
//         valueCase_ = 0;
//         value_ = null;
//         onChanged();
//       }
//       return this;
//     }
//     /**
//      * <pre>
//      * Must be a well-formed JSON value. Truncated JSON should go in
//      * string_value. Should not be used for tags.
//      * </pre>
//      *
//      * <code>string json_value = 6;</code>
//      */
//     public Builder setJsonValueBytes(
//         com.google.protobuf.ByteString value) {
//       if (value == null) {
//     throw new NullPointerException();
//   }
//   checkByteStringIsUtf8(value);
//       valueCase_ = 6;
//       value_ = value;
//       onChanged();
//       return this;
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


//     // @@protoc_insertion_point(builder_scope:lightstep.collector.KeyValue)
//   }

//   // @@protoc_insertion_point(class_scope:lightstep.collector.KeyValue)
//   private static final com.lightstep.tracer.grpc.KeyValue DEFAULT_INSTANCE;
//   static {
//     DEFAULT_INSTANCE = new com.lightstep.tracer.grpc.KeyValue();
//   }

//   public static com.lightstep.tracer.grpc.KeyValue getDefaultInstance() {
//     return DEFAULT_INSTANCE;
//   }

//   private static final com.google.protobuf.Parser<KeyValue>
//       PARSER = new com.google.protobuf.AbstractParser<KeyValue>() {
//     @java.lang.Override
//     public KeyValue parsePartialFrom(
//         com.google.protobuf.CodedInputStream input,
//         com.google.protobuf.ExtensionRegistryLite extensionRegistry)
//         throws com.google.protobuf.InvalidProtocolBufferException {
//       return new KeyValue(input, extensionRegistry);
//     }
//   };

//   public static com.google.protobuf.Parser<KeyValue> parser() {
//     return PARSER;
//   }

//   @java.lang.Override
//   public com.google.protobuf.Parser<KeyValue> getParserForType() {
//     return PARSER;
//   }

//   @java.lang.Override
//   public com.lightstep.tracer.grpc.KeyValue getDefaultInstanceForType() {
//     return DEFAULT_INSTANCE;
//   }

// }