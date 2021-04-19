package com.elsys.surveyio.survey;

import com.elsys.surveyio.question.Question;
import com.elsys.surveyio.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private Set<Question> questions;
    @ManyToOne()
    @JsonBackReference
    private User user;
    private Boolean open = true;

    public Survey() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
