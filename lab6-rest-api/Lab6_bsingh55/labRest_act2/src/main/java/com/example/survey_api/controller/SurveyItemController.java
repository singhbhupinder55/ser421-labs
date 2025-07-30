package com.example.survey_api.controller;

import com.example.survey_api.model.SurveyItem;
import com.example.survey_api.repository.SurveyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveyItems")
public class SurveyItemController {

    @Autowired
    private SurveyItemRepository surveyItemRepository;

    // Create a Survey Item (POST)
    @PostMapping
    public ResponseEntity<SurveyItem> createSurveyItem(@RequestBody SurveyItem surveyItem) {
        SurveyItem savedItem = surveyItemRepository.save(surveyItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    // Retrieve all Survey Items (GET)
    @GetMapping
    public ResponseEntity<List<SurveyItem>> getAllSurveyItems() {
        List<SurveyItem> items = surveyItemRepository.findAll();
        return ResponseEntity.ok(items);
    }
    // Delete Survey Item by ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveyItem(@PathVariable Long id) {
        if (surveyItemRepository.existsById(id)) {
            surveyItemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
