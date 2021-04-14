package com.elsys.surveyio.survey;


import com.elsys.surveyio.question.CreateQuestionDto;
import com.elsys.surveyio.question.Question;

import java.util.List;

public class CreateSurveyDto {
    private String name;
    private List<CreateQuestionDto> questions;

    public CreateSurveyDto(){}

    public CreateSurveyDto(String name, List<CreateQuestionDto> questions) {
        this.name = name;
        this.questions = questions;
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
