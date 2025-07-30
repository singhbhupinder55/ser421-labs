package com.example.survey_api.controller;

import com.example.survey_api.model.Survey;
import com.example.survey_api.model.SurveyInstance;
import com.example.survey_api.model.SurveyItemInstance;
import com.example.survey_api.repository.SurveyInstanceRepository;
import com.example.survey_api.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/surveyInstances")
public class SurveyInstanceController {

    private final SurveyInstanceRepository surveyInstanceRepository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyInstanceController(SurveyInstanceRepository surveyInstanceRepository, SurveyRepository surveyRepository) {
        this.surveyInstanceRepository = surveyInstanceRepository;
        this.surveyRepository = surveyRepository;
    }

    // Create a new SurveyInstance for a specific survey (POST)
    @PostMapping("/surveys/{surveyId}/instances")
    public ResponseEntity<SurveyInstance> createSurveyInstance(@PathVariable Long surveyId, @RequestBody String userName) {
        System.out.println("Survey ID: " + surveyId);
        System.out.println("User Name: " + userName);

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found"));

        SurveyInstance surveyInstance = new SurveyInstance();
        surveyInstance.setSurvey(survey);
        surveyInstance.setUserName(userName);
        surveyInstance.setState("Created");

        // Create SurveyItemInstances for each SurveyItem in the Survey
        survey.getSurveyItems().forEach(surveyItem -> {
            SurveyItemInstance itemInstance = new SurveyItemInstance();
            itemInstance.setSurveyItem(surveyItem);
            surveyInstance.getSurveyItemInstances().add(itemInstance);
        });

        SurveyInstance savedInstance = surveyInstanceRepository.save(surveyInstance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInstance);
    }

    // Get Survey Instances filtered by state (GET /api/surveyInstances?state=Created)
        @GetMapping(params = "state")
        public List<SurveyInstance> getSurveyInstancesByState(@RequestParam String state) {
        return surveyInstanceRepository.findByState(state);
        }


    // Submit an answer to a survey item instance
    @PutMapping("/{instanceId}/items/{itemId}")
    public ResponseEntity<SurveyItemInstance> submitAnswer(@PathVariable Long instanceId, @PathVariable Long itemId, @RequestBody String selectedAnswer) {
        SurveyInstance surveyInstance = surveyInstanceRepository.findById(instanceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey Instance not found"));

        // Submit the answer to the specific item
        surveyInstance.submitAnswer(itemId, selectedAnswer);

        surveyInstanceRepository.save(surveyInstance);

        // Return the updated survey item instance
        SurveyItemInstance itemInstance = surveyInstance.getSurveyItemInstances().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey Item Instance not found"));

        return ResponseEntity.ok(itemInstance);
    }

    // Mark a SurveyInstance as "InProgress" (PUT)
    @PutMapping("/{id}/markInProgress")
    public ResponseEntity<SurveyInstance> markSurveyInProgress(@PathVariable Long id) {
        SurveyInstance surveyInstance = surveyInstanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SurveyInstance not found"));
        surveyInstance.markInProgress();
        return ResponseEntity.ok(surveyInstanceRepository.save(surveyInstance));
    }

    // Mark a SurveyInstance as "Completed" (PUT)
    @PutMapping("/{id}/markCompleted")
    public ResponseEntity<SurveyInstance> markSurveyCompleted(@PathVariable Long id) {
        SurveyInstance surveyInstance = surveyInstanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SurveyInstance not found"));
        surveyInstance.markCompleted();
        return ResponseEntity.ok(surveyInstanceRepository.save(surveyInstance));
    }

    // Get a specific SurveyInstance by ID (GET)
    @GetMapping("/{id}")
    public SurveyInstance getSurveyInstance(@PathVariable Long id) {
        return surveyInstanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SurveyInstance not found"));
    }

    // Get all SurveyInstances (GET)
    @GetMapping
    public List<SurveyInstance> getAllSurveyInstances() {
        return surveyInstanceRepository.findAll();
    }

    // Delete a SurveyInstance (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveyInstance(@PathVariable Long id) {
        if (surveyInstanceRepository.existsById(id)) {
            surveyInstanceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}