package com.splunk.tracer.shared;

import com.splunk.tracer.grpc.ReportRequest;
import com.splunk.tracer.grpc.ReportResponse;

// Has to be an abstract class and not an interface becuase interfaces don't allow package private methods.
abstract class CollectorClient {
    abstract void reconnect();

    abstract void shutdown();

    abstract ReportResponse report(ReportRequest request);
}
