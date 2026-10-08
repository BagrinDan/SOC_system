package com.lab2.incident.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * ==============
 * Broker service
 * ==============
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.65.1)",
    comments = "Source: incident.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IncidentBrokerGrpc {

  private IncidentBrokerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "incident.IncidentBroker";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.lab2.incident.proto.IncidentRequest,
      com.lab2.incident.proto.PublishResponse> getPublishIncidentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PublishIncident",
      requestType = com.lab2.incident.proto.IncidentRequest.class,
      responseType = com.lab2.incident.proto.PublishResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.lab2.incident.proto.IncidentRequest,
      com.lab2.incident.proto.PublishResponse> getPublishIncidentMethod() {
    io.grpc.MethodDescriptor<com.lab2.incident.proto.IncidentRequest, com.lab2.incident.proto.PublishResponse> getPublishIncidentMethod;
    if ((getPublishIncidentMethod = IncidentBrokerGrpc.getPublishIncidentMethod) == null) {
      synchronized (IncidentBrokerGrpc.class) {
        if ((getPublishIncidentMethod = IncidentBrokerGrpc.getPublishIncidentMethod) == null) {
          IncidentBrokerGrpc.getPublishIncidentMethod = getPublishIncidentMethod =
              io.grpc.MethodDescriptor.<com.lab2.incident.proto.IncidentRequest, com.lab2.incident.proto.PublishResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PublishIncident"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lab2.incident.proto.IncidentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lab2.incident.proto.PublishResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IncidentBrokerMethodDescriptorSupplier("PublishIncident"))
              .build();
        }
      }
    }
    return getPublishIncidentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.lab2.incident.proto.SubscribeRequest,
      com.lab2.incident.proto.IncidentResponse> getSubscribeToIncidentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeToIncidents",
      requestType = com.lab2.incident.proto.SubscribeRequest.class,
      responseType = com.lab2.incident.proto.IncidentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.lab2.incident.proto.SubscribeRequest,
      com.lab2.incident.proto.IncidentResponse> getSubscribeToIncidentsMethod() {
    io.grpc.MethodDescriptor<com.lab2.incident.proto.SubscribeRequest, com.lab2.incident.proto.IncidentResponse> getSubscribeToIncidentsMethod;
    if ((getSubscribeToIncidentsMethod = IncidentBrokerGrpc.getSubscribeToIncidentsMethod) == null) {
      synchronized (IncidentBrokerGrpc.class) {
        if ((getSubscribeToIncidentsMethod = IncidentBrokerGrpc.getSubscribeToIncidentsMethod) == null) {
          IncidentBrokerGrpc.getSubscribeToIncidentsMethod = getSubscribeToIncidentsMethod =
              io.grpc.MethodDescriptor.<com.lab2.incident.proto.SubscribeRequest, com.lab2.incident.proto.IncidentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeToIncidents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lab2.incident.proto.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.lab2.incident.proto.IncidentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IncidentBrokerMethodDescriptorSupplier("SubscribeToIncidents"))
              .build();
        }
      }
    }
    return getSubscribeToIncidentsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IncidentBrokerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IncidentBrokerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IncidentBrokerStub>() {
        @java.lang.Override
        public IncidentBrokerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IncidentBrokerStub(channel, callOptions);
        }
      };
    return IncidentBrokerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IncidentBrokerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IncidentBrokerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IncidentBrokerBlockingStub>() {
        @java.lang.Override
        public IncidentBrokerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IncidentBrokerBlockingStub(channel, callOptions);
        }
      };
    return IncidentBrokerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IncidentBrokerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IncidentBrokerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IncidentBrokerFutureStub>() {
        @java.lang.Override
        public IncidentBrokerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IncidentBrokerFutureStub(channel, callOptions);
        }
      };
    return IncidentBrokerFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * ==============
   * Broker service
   * ==============
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void publishIncident(com.lab2.incident.proto.IncidentRequest request,
        io.grpc.stub.StreamObserver<com.lab2.incident.proto.PublishResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPublishIncidentMethod(), responseObserver);
    }

    /**
     */
    default void subscribeToIncidents(com.lab2.incident.proto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<com.lab2.incident.proto.IncidentResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeToIncidentsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service IncidentBroker.
   * <pre>
   * ==============
   * Broker service
   * ==============
   * </pre>
   */
  public static abstract class IncidentBrokerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return IncidentBrokerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service IncidentBroker.
   * <pre>
   * ==============
   * Broker service
   * ==============
   * </pre>
   */
  public static final class IncidentBrokerStub
      extends io.grpc.stub.AbstractAsyncStub<IncidentBrokerStub> {
    private IncidentBrokerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IncidentBrokerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IncidentBrokerStub(channel, callOptions);
    }

    /**
     */
    public void publishIncident(com.lab2.incident.proto.IncidentRequest request,
        io.grpc.stub.StreamObserver<com.lab2.incident.proto.PublishResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPublishIncidentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribeToIncidents(com.lab2.incident.proto.SubscribeRequest request,
        io.grpc.stub.StreamObserver<com.lab2.incident.proto.IncidentResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeToIncidentsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service IncidentBroker.
   * <pre>
   * ==============
   * Broker service
   * ==============
   * </pre>
   */
  public static final class IncidentBrokerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<IncidentBrokerBlockingStub> {
    private IncidentBrokerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IncidentBrokerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IncidentBrokerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.lab2.incident.proto.PublishResponse publishIncident(com.lab2.incident.proto.IncidentRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPublishIncidentMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.lab2.incident.proto.IncidentResponse> subscribeToIncidents(
        com.lab2.incident.proto.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeToIncidentsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service IncidentBroker.
   * <pre>
   * ==============
   * Broker service
   * ==============
   * </pre>
   */
  public static final class IncidentBrokerFutureStub
      extends io.grpc.stub.AbstractFutureStub<IncidentBrokerFutureStub> {
    private IncidentBrokerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IncidentBrokerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IncidentBrokerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.lab2.incident.proto.PublishResponse> publishIncident(
        com.lab2.incident.proto.IncidentRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPublishIncidentMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUBLISH_INCIDENT = 0;
  private static final int METHODID_SUBSCRIBE_TO_INCIDENTS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUBLISH_INCIDENT:
          serviceImpl.publishIncident((com.lab2.incident.proto.IncidentRequest) request,
              (io.grpc.stub.StreamObserver<com.lab2.incident.proto.PublishResponse>) responseObserver);
          break;
        case METHODID_SUBSCRIBE_TO_INCIDENTS:
          serviceImpl.subscribeToIncidents((com.lab2.incident.proto.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<com.lab2.incident.proto.IncidentResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPublishIncidentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.lab2.incident.proto.IncidentRequest,
              com.lab2.incident.proto.PublishResponse>(
                service, METHODID_PUBLISH_INCIDENT)))
        .addMethod(
          getSubscribeToIncidentsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.lab2.incident.proto.SubscribeRequest,
              com.lab2.incident.proto.IncidentResponse>(
                service, METHODID_SUBSCRIBE_TO_INCIDENTS)))
        .build();
  }

  private static abstract class IncidentBrokerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IncidentBrokerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.lab2.incident.proto.Incident.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IncidentBroker");
    }
  }

  private static final class IncidentBrokerFileDescriptorSupplier
      extends IncidentBrokerBaseDescriptorSupplier {
    IncidentBrokerFileDescriptorSupplier() {}
  }

  private static final class IncidentBrokerMethodDescriptorSupplier
      extends IncidentBrokerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    IncidentBrokerMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IncidentBrokerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IncidentBrokerFileDescriptorSupplier())
              .addMethod(getPublishIncidentMethod())
              .addMethod(getSubscribeToIncidentsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
