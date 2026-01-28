package com.jayzebra.feedsmodule.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

/**
 * DTO for the POST /feednotes request body.
 * Based on the FeedNoteCreate schema in the OpenAPI specification, using Lombok.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedNoteCreateRequestdto {

    @NotBlank(message = "Message cannot be empty.")
    private String message;
}
