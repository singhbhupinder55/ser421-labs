package com.example.survey_api.repository;

import com.example.survey_api.model.SurveyInstance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyInstanceRepository extends JpaRepository<SurveyInstance, Long> {
    List<SurveyInstance> findByState(String state);

}
