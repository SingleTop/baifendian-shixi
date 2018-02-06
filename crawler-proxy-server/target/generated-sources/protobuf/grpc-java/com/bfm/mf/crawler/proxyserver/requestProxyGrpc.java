package com.bfm.mf.crawler.proxyserver;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 0.14.0)",
    comments = "Source: proxyentry.proto")
public class requestProxyGrpc {

  private requestProxyGrpc() {}

  public static final String SERVICE_NAME = "proxy_server.requestProxy";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.bfm.mf.crawler.proxyserver.CrawlRequest,
      com.bfm.mf.crawler.proxyserver.CrawlResponse> METHOD_CRAWL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proxy_server.requestProxy", "crawl"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.bfm.mf.crawler.proxyserver.CrawlRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.bfm.mf.crawler.proxyserver.CrawlResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<com.bfm.mf.crawler.proxyserver.ask,
      com.bfm.mf.crawler.proxyserver.reply> METHOD_IS_HAS_RESOURCE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proxy_server.requestProxy", "isHasResource"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.bfm.mf.crawler.proxyserver.ask.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.bfm.mf.crawler.proxyserver.reply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static requestProxyStub newStub(io.grpc.Channel channel) {
    return new requestProxyStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static requestProxyBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new requestProxyBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static requestProxyFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new requestProxyFutureStub(channel);
  }

  /**
   */
  public static interface requestProxy {

    /**
     */
    public void crawl(com.bfm.mf.crawler.proxyserver.CrawlRequest request,
        io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.CrawlResponse> responseObserver);

    /**
     */
    public void isHasResource(com.bfm.mf.crawler.proxyserver.ask request,
        io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.reply> responseObserver);
  }

  @io.grpc.ExperimentalApi
  public static abstract class AbstractrequestProxy implements requestProxy, io.grpc.BindableService {

    @java.lang.Override
    public void crawl(com.bfm.mf.crawler.proxyserver.CrawlRequest request,
        io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.CrawlResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CRAWL, responseObserver);
    }

    @java.lang.Override
    public void isHasResource(com.bfm.mf.crawler.proxyserver.ask request,
        io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.reply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_IS_HAS_RESOURCE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return requestProxyGrpc.bindService(this);
    }
  }

  /**
   */
  public static interface requestProxyBlockingClient {

    /**
     */
    public com.bfm.mf.crawler.proxyserver.CrawlResponse crawl(com.bfm.mf.crawler.proxyserver.CrawlRequest request);

    /**
     */
    public com.bfm.mf.crawler.proxyserver.reply isHasResource(com.bfm.mf.crawler.proxyserver.ask request);
  }

  /**
   */
  public static interface requestProxyFutureClient {

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.bfm.mf.crawler.proxyserver.CrawlResponse> crawl(
        com.bfm.mf.crawler.proxyserver.CrawlRequest request);

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.bfm.mf.crawler.proxyserver.reply> isHasResource(
        com.bfm.mf.crawler.proxyserver.ask request);
  }

  public static class requestProxyStub extends io.grpc.stub.AbstractStub<requestProxyStub>
      implements requestProxy {
    private requestProxyStub(io.grpc.Channel channel) {
      super(channel);
    }

    private requestProxyStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected requestProxyStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new requestProxyStub(channel, callOptions);
    }

    @java.lang.Override
    public void crawl(com.bfm.mf.crawler.proxyserver.CrawlRequest request,
        io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.CrawlResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CRAWL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void isHasResource(com.bfm.mf.crawler.proxyserver.ask request,
        io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_IS_HAS_RESOURCE, getCallOptions()), request, responseObserver);
    }
  }

  public static class requestProxyBlockingStub extends io.grpc.stub.AbstractStub<requestProxyBlockingStub>
      implements requestProxyBlockingClient {
    private requestProxyBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private requestProxyBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected requestProxyBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new requestProxyBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public com.bfm.mf.crawler.proxyserver.CrawlResponse crawl(com.bfm.mf.crawler.proxyserver.CrawlRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CRAWL, getCallOptions(), request);
    }

    @java.lang.Override
    public com.bfm.mf.crawler.proxyserver.reply isHasResource(com.bfm.mf.crawler.proxyserver.ask request) {
      return blockingUnaryCall(
          getChannel(), METHOD_IS_HAS_RESOURCE, getCallOptions(), request);
    }
  }

  public static class requestProxyFutureStub extends io.grpc.stub.AbstractStub<requestProxyFutureStub>
      implements requestProxyFutureClient {
    private requestProxyFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private requestProxyFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected requestProxyFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new requestProxyFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.bfm.mf.crawler.proxyserver.CrawlResponse> crawl(
        com.bfm.mf.crawler.proxyserver.CrawlRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CRAWL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<com.bfm.mf.crawler.proxyserver.reply> isHasResource(
        com.bfm.mf.crawler.proxyserver.ask request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_IS_HAS_RESOURCE, getCallOptions()), request);
    }
  }

  private static final int METHODID_CRAWL = 0;
  private static final int METHODID_IS_HAS_RESOURCE = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final requestProxy serviceImpl;
    private final int methodId;

    public MethodHandlers(requestProxy serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CRAWL:
          serviceImpl.crawl((com.bfm.mf.crawler.proxyserver.CrawlRequest) request,
              (io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.CrawlResponse>) responseObserver);
          break;
        case METHODID_IS_HAS_RESOURCE:
          serviceImpl.isHasResource((com.bfm.mf.crawler.proxyserver.ask) request,
              (io.grpc.stub.StreamObserver<com.bfm.mf.crawler.proxyserver.reply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final requestProxy serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
        .addMethod(
          METHOD_CRAWL,
          asyncUnaryCall(
            new MethodHandlers<
              com.bfm.mf.crawler.proxyserver.CrawlRequest,
              com.bfm.mf.crawler.proxyserver.CrawlResponse>(
                serviceImpl, METHODID_CRAWL)))
        .addMethod(
          METHOD_IS_HAS_RESOURCE,
          asyncUnaryCall(
            new MethodHandlers<
              com.bfm.mf.crawler.proxyserver.ask,
              com.bfm.mf.crawler.proxyserver.reply>(
                serviceImpl, METHODID_IS_HAS_RESOURCE)))
        .build();
  }
}
