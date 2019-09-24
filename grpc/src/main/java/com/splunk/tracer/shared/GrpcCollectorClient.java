package com.splunk.tracer.shared;

import com.splunk.tracer.grpc.ReportResponse;
import com.splunk.tracer.grpc.ReportRequest;
import com.splunk.tracer.grpc.GrpcClientInterceptor;
import com.splunk.tracer.grpc.CollectorServiceGrpc;
import com.splunk.tracer.grpc.CollectorServiceGrpc.CollectorServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

class GrpcCollectorClient extends CollectorClient {
  private final ManagedChannelBuilder<?> channelBuilder;
  private ManagedChannel channel;
  private CollectorServiceBlockingStub blockingStub;
  private final AbstractTracer tracer;
  private final long deadlineMillis;

  /**
   * Constructor client for accessing CollectorService using the existing channel
   */
  GrpcCollectorClient(
          AbstractTracer tracer,
          ManagedChannelBuilder channelBuilder,
          long deadlineMillis
  ) {
    this.tracer = tracer;
    this.channelBuilder = channelBuilder;
    this.deadlineMillis = deadlineMillis;
    connect();
  }

  synchronized void shutdown() {
    try {
      channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      tracer.warn("Interrupted while shutting down client, force shutdown now!");
      channel.shutdownNow();
    }
  }

  /**
   * Blocking call to report
   */
  synchronized ReportResponse report(ReportRequest request) {
    try {
      ReportResponse response = blockingStub.
              withDeadlineAfter(deadlineMillis, TimeUnit.MILLISECONDS).
              withInterceptors(new GrpcClientInterceptor(request.getAuth().getAccessToken())).
              report(request);

      return response;
    } catch (StatusRuntimeException e) {
      tracer.error("Status runtime exception (likely malformed spans): ", e);
    } catch (Exception e) {
      tracer.error("Exception sending report to collector: ", e);
    }

    return null;
  }

  private synchronized void connect() {
    channel = channelBuilder.build();
    blockingStub = CollectorServiceGrpc.newBlockingStub(channel);
  }

  synchronized void reconnect() {
    this.shutdown();
    this.connect();
  }
}
