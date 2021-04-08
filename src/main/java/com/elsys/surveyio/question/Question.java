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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="question_id")
    private Set<Answer> answers;

    public Question(){}

    public Question(String question, Survey survey, Set<Answer> answers) {
        this.question = question;
        this.answers = answers;
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
