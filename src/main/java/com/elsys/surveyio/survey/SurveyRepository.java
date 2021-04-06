package com.elsys.surveyio.survey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository  extends JpaRepository<Survey, Long> {
}
