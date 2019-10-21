package com.splunk.tracer.shared;

import com.splunk.tracer.transport.InternalMetrics;
import com.splunk.tracer.transport.MetricsSample;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Tracks client metrics for internal purposes.
 */
class ClientMetrics {
    private final AtomicLong spansDropped = new AtomicLong(0);

    void addSpansDropped(int size) {
        if (size != 0) {
            spansDropped.addAndGet(size);
        }
    }

    long getSpansDropped() {
        return spansDropped.get();
    }

    InternalMetrics toInternalMetricsAndReset() {
        return InternalMetrics.InternalMetricsBuilder()
                .addCounts(MetricsSample.MetricsSampleBuilder()
                        .setName("spans.dropped")
                        .setIntValue(getAndResetSpansDropped())
                        .build()
                ).build();
    }

    private long getAndResetSpansDropped() {
        return spansDropped.getAndSet(0);
    }
}
