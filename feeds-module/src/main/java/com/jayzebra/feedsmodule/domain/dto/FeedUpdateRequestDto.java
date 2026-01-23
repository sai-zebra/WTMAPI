package com.jayzebra.feedsmodule.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO for requesting feed update
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedUpdateRequestDto {

    //Validation to ensure title should not be blank
    @NotBlank(message = "title should not be blank")
    private String title;

    //Validation to ensure message should not be blank
    @NotBlank(message = "message should not be null")
    private String message;
}

