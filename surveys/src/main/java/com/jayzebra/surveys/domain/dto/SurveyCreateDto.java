package com.jayzebra.surveys.domain.dto;


import lombok.Data;

import java.util.List;


@Data
public class SurveyCreateDto {
    private String title;
    private List<String> questions;

}

