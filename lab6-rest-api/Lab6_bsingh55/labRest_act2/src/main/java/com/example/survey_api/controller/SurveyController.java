package com.example.survey_api.controller;

import com.example.survey_api.model.Survey;
import com.example.survey_api.model.SurveyItem;
import com.example.survey_api.repository.SurveyItemRepository;
import com.example.survey_api.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyItemRepository surveyItemRepository;

    // Get all surveys
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    // Create a new survey
    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) throws URISyntaxException {
        Survey savedSurvey = surveyRepository.save(survey);
        URI location = new URI("/api/surveys/" + savedSurvey.getId());
        return ResponseEntity.created(location).body(savedSurvey);  // Returns 201 Created
    }

    // Update the name of an existing survey
    @PutMapping("/{surveyId}")
    public ResponseEntity<Survey> updateSurveyName(@PathVariable Long surveyId, @RequestBody Survey surveyDetails) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey with id " + surveyId + " not found."));
        survey.setName(surveyDetails.getName());  // Use setName since Survey uses 'name'
        return ResponseEntity.ok(surveyRepository.save(survey));
    }

    // Add a SurveyItem to an existing survey, with a check to avoid duplicates
    @PutMapping("/{surveyId}/addItem")
    public ResponseEntity<Survey> addSurveyItem(@PathVariable Long surveyId, @RequestBody SurveyItem surveyItem) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey with id " + surveyId + " not found."));

        // Check if the survey item already exists
        if (survey.getSurveyItems().contains(surveyItem)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Survey item already exists in the survey.");
        }

        survey.getSurveyItems().add(surveyItemRepository.save(surveyItem));
        return ResponseEntity.ok(surveyRepository.save(survey));
    }

    // Mark a survey as "Completed"
    @PutMapping("/{surveyId}/complete")
    public ResponseEntity<Survey> completeSurvey(@PathVariable Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey with id " + surveyId + " not found."));
        survey.completeSurvey();  // Ensure completeSurvey() sets the state to 'Completed'
        return ResponseEntity.ok(surveyRepository.save(survey));
    }

    // Mark a survey as "Deleted"
    @PutMapping("/{surveyId}/delete")
    public ResponseEntity<Survey> deleteSurvey(@PathVariable Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey with id " + surveyId + " not found."));
        survey.deleteSurvey();  // Ensure deleteSurvey() sets the state to 'Deleted'
        return ResponseEntity.ok(surveyRepository.save(survey));
    }
    // Get a specific survey by ID
    @GetMapping("/{surveyId}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey with id " + surveyId + " not found."));
        return ResponseEntity.ok(survey);
    }

}
