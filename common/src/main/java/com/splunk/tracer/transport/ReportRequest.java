package com.splunk.tracer.transport;

import com.splunk.tracer.transport.Auth;
import com.splunk.tracer.transport.InternalMetrics;
import com.splunk.tracer.transport.KeyValue;
import com.splunk.tracer.transport.Reporter;
import com.splunk.tracer.transport.Span;

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
            
            convertedSpan.put("time", String.format("%d.%d", ts.getSeconds(), ts.getNanos())); //+ ts.getNanos()/1000000000);
            convertedSpan.put("sourcetype", "splunktracing:span");
            JSONObject event_obj = new JSONObject();
            event_obj.put("operation_name", span.getOperationName());
            event_obj.put("trace_id", Long.toHexString(trace_id));
            event_obj.put("span_id", Long.toHexString(span_id));
            event_obj.put("parent_span_id", Long.toHexString(parent_span_id));
            event_obj.put("guid", Long.toHexString(guid));
            event_obj.put("timestamp", ts.getSeconds() + ts.getNanos()/1000000000);
            event_obj.put("duration", span.getDurationMicros());
            // event_obj.put("tags", new JsonObject(DictToJson(span.Tags)));
            // event_obj.put("baggage", new JsonObject(StringDictToJson(span.Context.GetBaggage())));
            // event_obj.put("device", reporter.Tags["device"].ToString());
            // event_obj.put("component_name", reporter.Tags["component_name"].ToString());
            // event_obj.put("tracer_platform_version", reporter.Tags["tracer_platform_version"].ToString());
            // event_obj.put("tracer_platform", reporter.Tags["tracer_platform"].ToString());
            // event_obj.put("tracer_version", reporter.Tags["tracer_version"].ToString());
            convertedSpan.put("event", event_obj);
            event_obj_list.add(convertedSpan.toString());
        }
        String json_content = String.join("\n", event_obj_list);
        return toReportContent(json_content);
    }

}

        // var event_obj_list = new List<string>();
        // var epochZero = new DateTimeOffset(1970, 1, 1, 0, 0, 0, TimeSpan.Zero); 
        // var convertedSpan = new JsonObject();
        // convertedSpan.Add("time", (span.StartTimestamp.UtcTicks - epochZero.UtcTicks) / 10000000.0);
        // convertedSpan.Add("sourcetype", "splunktracing:span");
        // convertedSpan.Add("event", new JsonObject() {
        //   ["component_name"] = reporter.Tags["component_name"].ToString(),
        //   ["operation_name"] = span.OperationName,
        //   ["tracer_platform_version"] = reporter.Tags["tracer_platform_version"].ToString(),
        //   ["tracer_platform"] = reporter.Tags["tracer_platform"].ToString(),
        //   ["tracer_version"] = reporter.Tags["tracer_version"].ToString(),
        //   ["trace_id"] = Utilities.IdToHex(span.Context.TraceId),
        //   ["span_id"] = Utilities.IdToHex(span.Context.SpanId),
        //   ["parent_span_id"] = Utilities.IdToHex(span.Context.ParentSpanId),
        //   ["device"] = reporter.Tags["device"].ToString(),
        //   ["guid"] = reporter.ReporterId,
        //   ["timestamp"] = (span.StartTimestamp.UtcTicks - epochZero.UtcTicks) / 10000000.0,  //UtcTicks
        //   ["duration"] = Convert.ToUInt64(Math.Abs(span.Duration.Ticks) / 10),
        //   ["tags"] = new JsonObject(DictToJson(span.Tags)), //span.Tags
        //   ["baggage"] = new JsonObject(StringDictToJson(span.Context.GetBaggage())), // span.Context.GetBaggage()
        //   }
        // );
        // event_obj_list.Add(convertedSpan.ToString());
        // foreach (var log in span.LogData)
        // {
        //   var log_obj = new JsonObject();
        //   log_obj.Add("time", (log.Timestamp.UtcTicks - epochZero.UtcTicks) / 10000000.0);
        //   log_obj.Add("sourcetype", "splunktracing:log");
        //   log_obj.Add("event", new JsonObject() {
        //     ["component_name"] = reporter.Tags["component_name"].ToString(),
        //     ["operation_name"] = span.OperationName,
        //     ["tracer_platform_version"] = reporter.Tags["tracer_platform_version"].ToString(),
        //     ["tracer_platform"] = reporter.Tags["tracer_platform"].ToString(),
        //     ["tracer_version"] = reporter.Tags["tracer_version"].ToString(),
        //     ["trace_id"] = Utilities.IdToHex(span.Context.TraceId),
        //     ["span_id"] = Utilities.IdToHex(span.Context.SpanId),
        //     ["parent_span_id"] = Utilities.IdToHex(span.Context.ParentSpanId),
        //     ["device"] = reporter.Tags["device"].ToString(),
        //     ["guid"] = reporter.ReporterId,
        //     ["timestamp"] = (log.Timestamp.UtcTicks - epochZero.UtcTicks) / 10000000.0,
        //     ["tags"] = new JsonObject(DictToJson(span.Tags)),
        //     ["baggage"] = new JsonObject(StringDictToJson(span.Context.GetBaggage())), 
        //     ["fields"] = new JsonObject(LogFieldsToJson(log.Fields)),
        //   });
        //   event_obj_list.Add(log_obj.ToString());
        // }

        // return string.Join("\n", event_obj_list);