package com.example.survey_api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SurveyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String questionStem;

    @ElementCollection
    private List<String> possibleAnswers;

    private String correctAnswer;

    // Default constructor with list initialization to avoid null pointer issues
    public SurveyItem() {
        this.possibleAnswers = new ArrayList<>();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionStem() {
        return questionStem;
    }

    public void setQuestionStem(String questionStem) {
        this.questionStem = questionStem;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // Override toString method for better logging and debugging
    @Override
    public String toString() {
        return "SurveyItem{" +
                "id=" + id +
                ", questionStem='" + questionStem + '\'' +
                ", possibleAnswers=" + possibleAnswers +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

    // Override equals and hashCode safely

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyItem that = (SurveyItem) o;
        return Objects.equals(questionStem, that.questionStem) &&
                Objects.equals(possibleAnswers, that.possibleAnswers) &&
                Objects.equals(correctAnswer, that.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionStem, possibleAnswers, correctAnswer);
    }
}
