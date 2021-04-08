package com.elsys.surveyio.survey;


import com.elsys.surveyio.question.Question;

import java.util.List;

public class CreateSurveyDto {
    private String name;
    private List<Question> questions;

    public CreateSurveyDto(){}

    public CreateSurveyDto(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
