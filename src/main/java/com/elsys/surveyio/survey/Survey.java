package com.elsys.surveyio.survey;

import com.elsys.surveyio.question.Question;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Survey {
    @PrePersist
    public void initializePrivateId() {
        if (privateId == null) {
            privateId = UUID.randomUUID().toString();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(updatable = false)
    private String privateId;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="survey_id")
    private Set<Question> questions;
    private Boolean open = true;

    public Survey() {}

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateId() {
        return privateId;
    }

    public void setPrivateId(String privateId) {
        this.privateId = privateId;
    }

    public Long getId() {
        return id;
    }
}
