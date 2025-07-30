package com.example.survey_api;

import com.example.survey_api.model.Survey;
import com.example.survey_api.model.SurveyItem;
import com.example.survey_api.model.SurveyInstance;
import com.example.survey_api.model.SurveyItemInstance;
import com.example.survey_api.repository.SurveyItemRepository;
import com.example.survey_api.repository.SurveyRepository;
import com.example.survey_api.repository.SurveyInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final SurveyRepository surveyRepository;
    private final SurveyItemRepository surveyItemRepository;
    private final SurveyInstanceRepository surveyInstanceRepository;

    @Autowired
    public DataLoader(SurveyRepository surveyRepository, SurveyItemRepository surveyItemRepository, SurveyInstanceRepository surveyInstanceRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyItemRepository = surveyItemRepository;
        this.surveyInstanceRepository = surveyInstanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Creating Survey Items
        SurveyItem item1 = new SurveyItem();
        item1.setQuestionStem("What is your favorite color?");
        item1.setPossibleAnswers(Arrays.asList("Red", "Blue", "Green"));
        item1.setCorrectAnswer("Blue");

        SurveyItem item2 = new SurveyItem();
        item2.setQuestionStem("What is your favorite programming language?");
        item2.setPossibleAnswers(Arrays.asList("Java", "Python", "JavaScript", "C++"));
        item2.setCorrectAnswer("Java");

        SurveyItem item3 = new SurveyItem();
        item3.setQuestionStem("What is your favorite season?");
        item3.setPossibleAnswers(Arrays.asList("Spring", "Summer", "Fall", "Winter"));
        item3.setCorrectAnswer("Summer");

        SurveyItem item4 = new SurveyItem();
        item4.setQuestionStem("What is your favorite car brand?");
        item4.setPossibleAnswers(Arrays.asList("Toyota", "Ford", "Tesla"));
        item4.setCorrectAnswer("Tesla");

        SurveyItem item5 = new SurveyItem();
        item5.setQuestionStem("What is your favorite sport?");
        item5.setPossibleAnswers(Arrays.asList("Soccer", "Basketball", "Tennis"));
        item5.setCorrectAnswer("Basketball");

        surveyItemRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5));

        // Creating Surveys and adding Survey Items
        Survey survey1 = new Survey();
        survey1.setName("Favorite Colors Survey");
        survey1.setSurveyItems(Arrays.asList(item1, item3));

        Survey survey2 = new Survey();
        survey2.setName("Programming Languages Survey");
        survey2.setSurveyItems(Arrays.asList(item2, item4, item5));

        survey1 = surveyRepository.save(survey1);
        survey2 = surveyRepository.save(survey2);


        // Creating Survey Instance
        SurveyInstance instance1 = new SurveyInstance();
        instance1.setSurvey(survey2); // Associate with "Programming Languages Survey"
        instance1.setUserName("John Doe");

        // Creating SurveyItemInstances linked to the survey instance
        SurveyItemInstance surveyItemInstance1 = new SurveyItemInstance();
        surveyItemInstance1.setSurveyItem(item2); // Link to "What is your favorite programming language?"

        instance1.getSurveyItemInstances().add(surveyItemInstance1); // Add to survey instance

        surveyInstanceRepository.save(instance1); // Save survey instance with item instances
    }
}
