package com.jayzebra.surveys.domain.service;

import com.jayzebra.surveys.adapter.output.entity.Survey;
import com.jayzebra.surveys.adapter.output.entity.SurveyResponse;
import com.jayzebra.surveys.domain.dto.SurveyCreateDto;
import com.jayzebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.jayzebra.surveys.domain.port.input.SurveyUseCase;
import com.jayzebra.surveys.domain.port.output.SurveyRepositoryPort;
import java.util.List;
import java.util.UUID;

/**
 Service - Main logic
 **/
public class SurveyService implements SurveyUseCase {

    private final SurveyRepositoryPort surveyRepositoryPort;

    public SurveyService(SurveyRepositoryPort surveyRepositoryPort) {
        this.surveyRepositoryPort = surveyRepositoryPort;
    }

    //Function to create new service
    @Override
    public Survey createSurvey(SurveyCreateDto surveyCreateDto) {
        Survey survey = new Survey();
        survey.setId(UUID.randomUUID().toString());
        survey.setTitle(surveyCreateDto.getTitle());
        survey.setQuestions(surveyCreateDto.getQuestions());
        return surveyRepositoryPort.saveSurvey(survey);
    }

    //Function to implement submit survey response
    @Override
    public void submitSurveyResponse(String surveyId, SurveyResponseCreateDto responseDto) {
        // Ensure the survey exists before accepting a response
        surveyRepositoryPort.findSurveyById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + surveyId));

        SurveyResponse response = new SurveyResponse();
        response.setId(UUID.randomUUID().toString());
        response.setSurveyId(surveyId);
        response.setResponses(responseDto.getResponses());
        surveyRepositoryPort.saveSurveyResponse(response);
    }

    //Function to list all surveys
    @Override
    public List<Survey> listSurveys() {
        return surveyRepositoryPort.findAllSurveys();
    }
}

