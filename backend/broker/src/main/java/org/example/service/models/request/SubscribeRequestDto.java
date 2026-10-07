package org.example.service.models.request;

import org.example.service.models.enums.VulnerabilityEnum;


public record SubscribeRequestDto (
        VulnerabilityEnum type
) { }
