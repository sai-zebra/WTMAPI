package com.jayzebra.surveys.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SurveyResponseCreateDto {
    private Map<String, String> responses;

}
