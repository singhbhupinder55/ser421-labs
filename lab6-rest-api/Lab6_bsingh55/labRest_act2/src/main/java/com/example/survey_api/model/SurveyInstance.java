package com.example.survey_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Survey survey; // Link to the Survey

    private String userName;  // User associated with this survey instance

    private String state = "Created";  // Default state is Created


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "survey_instance_id")
    private List<SurveyItemInstance> surveyItemInstances = new ArrayList<>();

    // Mark the survey as in progress
    public void markInProgress() {
        this.state = "InProgress";
    }

    // Mark the survey as completed
    public void markCompleted() {
        this.state = "Completed";
    }

    public void submitAnswer(Long itemId, String selectedAnswer) {
        SurveyItemInstance itemInstance = surveyItemInstances.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Survey Item Instance not found"));

        itemInstance.answer(selectedAnswer);
    }
}
