package com.elsys.surveyio.question;

import com.elsys.surveyio.answer.CreateAnswerDto;

import java.util.List;

public class CreateQuestionDto {
    private String question;
    private List<CreateAnswerDto> answers;
    private Boolean required = false;
    private Boolean multiple = false;

    public CreateQuestionDto(){}

    public CreateQuestionDto(String question, Long surveyId, List<Long> answersIds) {
        this.question = question;
    }

    public Boolean getRequired() {
        return required;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<CreateAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<CreateAnswerDto> answers) {
        this.answers = answers;
    }
}
