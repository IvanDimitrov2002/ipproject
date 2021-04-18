package com.elsys.surveyio.survey;


import com.elsys.surveyio.question.CreateQuestionDto;

import java.util.List;

public class CreateSurveyDto {
    private String name;
    private List<CreateQuestionDto> questions;
    private Boolean open = true;
    private Long userId;

    public CreateSurveyDto(){}

    public CreateSurveyDto(String name, List<CreateQuestionDto> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<CreateQuestionDto> questions) {
        this.questions = questions;
    }
}
