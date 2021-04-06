package com.elsys.surveyio.survey;


import java.util.List;

public class CreateSurveyDto {
    private String name;
    private List<Long> questionsIds;

    public CreateSurveyDto(){}

    public CreateSurveyDto(String name, List<Long> questionsIds) {
        this.name = name;
        this.questionsIds = questionsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<Long> questionsIds) {
        this.questionsIds = questionsIds;
    }
}
