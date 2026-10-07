package org.example.service.models.response;


import org.example.service.models.enums.ResponseStatusEnum;

public record PublishResponseDto (
    boolean success,
    String messageId,
    ResponseStatusEnum status
){ }
