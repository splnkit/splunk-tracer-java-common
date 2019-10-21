package com.splunk.tracer.shared;

public final class SplunkTracingConstants {
    private SplunkTracingConstants() {}

    public final class Tags {
        private Tags() {}

        public static final String LEGACY_COMPONENT_NAME_KEY = "component_name";
        public static final String COMPONENT_NAME_KEY = "component_name";
        public static final String COMPONENT = "component_name";
        public static final String GUID_KEY = "guid";
    }

    public final class MetaEvents {
        private MetaEvents() {}

        public static final String MetaEventKey = "meta_event";
        public static final String PropagationFormatKey = "propagation_format";
        public static final String TraceIdKey = "trace_id";
        public static final String SpanIdKey = "span_id";
        public static final String TracerGuidKey = "tracer_guid";
        public static final String ExtractOperation = "extract_span";
        public static final String InjectOperation = "inject_span";
        public static final String SpanStartOperation = "span_start";
        public static final String SpanFinishOperation = "span_finish";
        public static final String TracerCreateOperation = "tracer_create";
    }

    final class Internal {
        private Internal() {}

        static final String REPORTING_THREAD_NAME = "splunk-reporting-thread";
    }
}
