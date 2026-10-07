package org.example.service.models.response;


import lombok.Builder;
import org.example.service.models.enums.VulnerabilityEnum;

@Builder
public record IncidentResponseDto(
        VulnerabilityEnum type,
        String sourceIp,
        String payload,
        String targetEndpoint,
        String messageId,
        long timestamp
) {
    public org.example.service.models.request.IncidentRequestDto build(
            String messageId,
            long timestamp
    ) {
        return new org.example.service.models.request.IncidentRequestDto(
                type,
                sourceIp,
                payload,
                targetEndpoint,
                messageId,
                timestamp
        );
    }
}
