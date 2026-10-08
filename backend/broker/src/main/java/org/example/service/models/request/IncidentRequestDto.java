package org.example.service.models.request;

import lombok.Builder;
import org.example.service.models.enums.VulnerabilityEnum;



@Builder
public record IncidentRequestDto(
        VulnerabilityEnum type,
        String sourceIp,
        String payload,
        String targetEndpoint,
        String messageId,
        long timestamp
) {
    public IncidentRequestDto build(
            String messageId,
            long timestamp
    ) {
        return new IncidentRequestDto(
                type,
                sourceIp,
                payload,
                targetEndpoint,
                messageId,
                timestamp
        );
    }
}
