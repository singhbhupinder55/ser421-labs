package com.example.survey_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyItemInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SurveyItem surveyItem;  // Link to the actual survey item

    private String selectedAnswer;
    private boolean isCorrect;
    private boolean isCompleted = false;

    // Answer the survey item
    public void answer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        this.isCompleted = true;
        String correct = surveyItem != null ? surveyItem.getCorrectAnswer() : null;
        this.isCorrect = selectedAnswer != null && selectedAnswer.equals(correct);
    }
}
