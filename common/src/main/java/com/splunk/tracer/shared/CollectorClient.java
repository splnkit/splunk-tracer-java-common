package com.splunk.tracer.shared;

import com.splunk.tracer.transport.ReportRequest;
import com.splunk.tracer.transport.ReportResponse;

// Has to be an abstract class and not an interface becuase interfaces don't allow package private methods.
abstract class CollectorClient {
    abstract void reconnect();

    abstract void shutdown();

    abstract ReportResponse report(ReportRequest request);
}
