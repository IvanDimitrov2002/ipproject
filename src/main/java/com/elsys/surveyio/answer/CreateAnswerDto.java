package com.elsys.surveyio.answer;

public class CreateAnswerDto {
    private String answer;

    public CreateAnswerDto(String answer, Long questionId, Integer votes) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
