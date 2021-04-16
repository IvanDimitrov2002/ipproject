package com.elsys.surveyio.user;

import com.elsys.surveyio.survey.Survey;

import java.util.List;

public class CreateUserDto {
    private String username;
    private String password;
    private List<Survey> surveys;

    public CreateUserDto(){};

    public CreateUserDto(String username, String password, List<Survey> surveys) {
        this.username = username;
        this.password = password;
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

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}
