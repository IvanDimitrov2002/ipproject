package com.elsys.surveyio.answer;

import com.elsys.surveyio.question.Question;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String answer;
    private Integer votes = 0;

    public Answer(){}

    public Long getId() {
        return id;
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
