package com.elsys.surveyio.user;

import com.elsys.surveyio.survey.Survey;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Set<Survey> surveys;
    @Column(unique = true)
    private String username;
    private String password;

    public User(){};

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Set<Survey> surveys, String username, String password) {
        this.surveys = surveys;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Set<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(Set<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
