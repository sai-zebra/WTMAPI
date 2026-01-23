package com.jayzebra.surveys.adapter.output.entity;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.modulith.NamedInterface;

import java.util.Map;

@NamedInterface
@Entity
@Getter
@Setter
public class SurveyResponse {
    @Id
    @NotBlank(message = "Id should not be null")
    private String id;
    @NotBlank(message = "surveyId should not be blank")
    private String surveyId;
    @ElementCollection
    private Map<String, String> responses;
}
