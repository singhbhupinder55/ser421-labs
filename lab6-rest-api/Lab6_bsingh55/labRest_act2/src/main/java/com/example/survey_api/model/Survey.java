package com.example.survey_api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // Use 'name' for the survey's title or name

    @OneToMany
    @JoinColumn(name = "survey_id")
    private List<SurveyItem> surveyItems;

    private String state;

    public Survey() {}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SurveyItem> getSurveyItems() {
        return surveyItems;
    }

    public void setSurveyItems(List<SurveyItem> surveyItems) {
        this.surveyItems = surveyItems;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void completeSurvey() {
        this.state = "Completed";
    }

    public void deleteSurvey() {
        this.state = "Deleted";
    }

    @PrePersist
    public void prePersist() {
        if (this.state == null) {
            this.state = "Created";
        }
    }

}
