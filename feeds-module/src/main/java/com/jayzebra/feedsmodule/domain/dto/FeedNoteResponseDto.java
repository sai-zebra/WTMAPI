package com.jayzebra.feedsmodule.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing  FeedNote list in API responses.
 * Based on the FeedNote schema in the OpenAPI specification, using Lombok.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedNoteResponseDto {
    private UUID id;
    private String message;
    private OffsetDateTime createdAt;
}
