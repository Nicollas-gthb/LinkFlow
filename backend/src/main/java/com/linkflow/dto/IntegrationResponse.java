package com.linkflow.dto;

import com.linkflow.domain.Integration;
import com.linkflow.domain.User;

import java.time.OffsetDateTime;
import java.util.UUID;

public record IntegrationResponse(

        UUID id,
        String name,
        String description,
        String sourceUrl,
        String targetUrl,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {

    public static IntegrationResponse from(Integration integration) {
        return new IntegrationResponse(
                integration.getId(),
                integration.getName(),
                integration.getDescription(),
                integration.getSourceUrl(),
                integration.getTargetUrl(),
                integration.getActive(),
                integration.getCreatedAt(),
                integration.getUpdatedAt()
        );
    }
}
