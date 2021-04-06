package com.elsys.surveyio.question;

import com.elsys.surveyio.answer.Answer;
import com.elsys.surveyio.survey.Survey;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    @ManyToOne
    private Survey survey;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public Question(){}

    public Question(String question, Survey survey, Set<Answer> answers) {
        this.question = question;
        this.survey = survey;
        this.answers = answers;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
