package com.elsys.surveyio.answer;

public class CreateAnswerDto {
    private String answer;
    private Long questionId;
    private Integer votes;

    public CreateAnswerDto(String answer, Long questionId, Integer votes) {
        this.answer = answer;
        this.questionId = questionId;
        this.votes = votes;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
