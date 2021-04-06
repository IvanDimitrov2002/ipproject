package com.elsys.surveyio.question;

import java.util.List;

public class CreateQuestionDto {
    private String question;
    private Long surveyId;
    private List<Long> answersIds;

    public CreateQuestionDto(){}

    public CreateQuestionDto(String question, Long surveyId, List<Long> answersIds) {
        this.question = question;
        this.surveyId = surveyId;
        this.answersIds = answersIds;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public List<Long> getAnswersIds() {
        return answersIds;
    }

    public void setAnswersIds(List<Long> answersIds) {
        this.answersIds = answersIds;
    }
}
