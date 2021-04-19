package com.elsys.surveyio.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyRepository  extends JpaRepository<Survey, Long> {
    Optional<Survey> findByPrivateId(String privateId);
}
