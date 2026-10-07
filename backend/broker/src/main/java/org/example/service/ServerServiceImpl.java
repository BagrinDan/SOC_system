package org.example.service;

import com.lab2.incident.proto.*;
import io.grpc.stub.StreamObserver;
import org.example.service.interfaces.ServerService;
import org.example.service.models.enums.ResponseStatusEnum;
import org.example.service.models.enums.VulnerabilityEnum;
import org.example.service.models.mappers.IncidentMapper;
import org.example.service.models.mappers.PublishMapper;
import org.example.service.models.mappers.SubscribeMapper;
import org.example.service.models.request.IncidentRequestDto;
import org.example.service.models.request.SubscribeRequestDto;
import org.example.service.models.response.IncidentResponseDto;
import org.example.service.models.response.PublishResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;



public class ServerServiceImpl extends IncidentBrokerGrpc.IncidentBrokerImplBase implements ServerService {
    private static final Logger logger = LoggerFactory.getLogger(ServerServiceImpl.class);

    private final Map<VulnerabilityEnum, Set<StreamObserver<IncidentResponse>>> subscribers = new ConcurrentHashMap<>();

    public ServerServiceImpl() {
        for (VulnerabilityEnum type : VulnerabilityEnum.values()) {
            if (type != VulnerabilityEnum.UNKNOWN) {
                subscribers.put(type, ConcurrentHashMap.newKeySet());
            }
        }
    }

    @Override
    public void publishIncident(IncidentRequest grpcRequest, StreamObserver<PublishResponse> responseObserver) {
        // mapping from PROTO to DTO
        IncidentRequestDto dtoRequest = IncidentMapper.INSTANCE.toDto(grpcRequest);
        logger.info("[Publish] Got incident: {} {}", dtoRequest.messageId(), dtoRequest.type());

        // generating UUID and timestamp
        String messageId = "msg_" + UUID.randomUUID();
        long timestamp = System.currentTimeMillis() / 1000;
        logger.info("[DEBUG | SererService ] Got incident: {} {}", dtoRequest.messageId(), dtoRequest.type());

        // Creating dto response
        IncidentResponseDto dtoResponse = new IncidentResponseDto(
                dtoRequest.type(),
                dtoRequest.sourceIp(),
                dtoRequest.payload(),
                dtoRequest.targetEndpoint(),
                messageId,
                timestamp
        );

        // mapping from DTO to PROTO and sending to subs
        IncidentResponse grpcEvent = IncidentMapper.INSTANCE.toGrpc(dtoResponse);
        this.multicastToSubscribers(grpcEvent, dtoRequest.type());

        // transmitting answer to pub
        PublishResponseDto publishResponse = new PublishResponseDto(
                true,
                messageId,
                ResponseStatusEnum.SEND_TO_BROKER
        );

        PublishResponse grpcResponse = PublishMapper.INSTANCE.toGrpc(publishResponse);
        responseObserver.onNext(grpcResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void subscribeToIncidents(SubscribeRequest request, StreamObserver<IncidentResponse> responseObserver) {
        SubscribeRequestDto dtoRequest = SubscribeMapper.INSTANCE.toDto(request);
        VulnerabilityEnum type = dtoRequest.type();

        Set<StreamObserver<IncidentResponse>> topicSubscribers = subscribers.get(type);

        if (topicSubscribers == null) {
            logger.warn("[Subscribe] Unknown topic: {}", type);
            responseObserver.onError(
                    io.grpc.Status.INVALID_ARGUMENT
                            .withDescription("Unknown topic: " + type)
                            .asRuntimeException()
            );
            return;
        }

        topicSubscribers.add(responseObserver);
        logger.info("[Subscribe] New subscriber for topic: {}", type);

        if (responseObserver instanceof io.grpc.stub.ServerCallStreamObserver<?> serverCallObserver) {
            serverCallObserver.setOnCancelHandler(() -> {
                topicSubscribers.remove(responseObserver);
                logger.info("[Subscribe] Subscriber disconnected from topic: {}", type);
            });
        }
    }

    // Multicasting incident to subs
    private void multicastToSubscribers(IncidentResponse grpcEvent, VulnerabilityEnum type) {
        Set<StreamObserver<IncidentResponse>> topicSubscribers = subscribers.get(type);

        if (topicSubscribers == null || topicSubscribers.isEmpty()) {
            logger.debug("[Broker] No active subscribers for topic: {}", type);
            return;
        }

        for (StreamObserver<IncidentResponse> subscriberStream : topicSubscribers) {
            try {
                subscriberStream.onNext(grpcEvent);
            } catch (Exception e) {
                logger.warn("[WARN] Error on sending to sub, deleting stream: {}", e.getMessage());
                topicSubscribers.remove(subscriberStream);
            }
        }
    }
}