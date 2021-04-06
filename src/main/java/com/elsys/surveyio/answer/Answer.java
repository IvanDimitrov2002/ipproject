package com.elsys.surveyio.answer;

import com.elsys.surveyio.question.Question;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String answer;
    @ManyToOne
    private Question question;
    private Integer votes = 0;

    public Answer(){}

    public Answer(String answer, Question question, Integer votes) {
        this.answer = answer;
        this.question = question;
        this.votes = votes;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
