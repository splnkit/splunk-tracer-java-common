package com.splunk.tracer.shared;

import io.opentracing.Span;

public class Main {
    public static void main(String... args) throws Exception {
        ExampleTracer tracer = new ExampleTracer(new Options.OptionsBuilder()
                .withAccessToken("08243c00-a31b-499d-9fae-776b41990997")
                .withCollectorProtocol("http")
                .withCollectorPort(8086)
                .build()
        );

        Span span = tracer.buildSpan("parent span").startManual();

        Thread.sleep(1000);

        span.finish();

        tracer.flushInternal(true);
    }
}
