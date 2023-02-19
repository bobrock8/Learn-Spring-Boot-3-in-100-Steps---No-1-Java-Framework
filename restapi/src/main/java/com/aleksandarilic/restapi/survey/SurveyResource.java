package com.aleksandarilic.restapi.survey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyResource {

    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }


    //GET
    @RequestMapping(name = "Retrieve all surveys", path = "surveys")
    public List<Survey> retrieveAllSurveys() {
        return SurveyService.getSurveys();
    }

    @RequestMapping(name = "Retrieve survey by Id", path = "surveys/{surveyId}")
    public Survey retrieveSurveyById(@PathVariable String surveyId) {

        Survey survey =  surveyService.getSurveyById(surveyId);
        if(survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return survey;
    }

    @RequestMapping(name = "Retrieve questions for survey by Id", path = "surveys/{surveyId}/questions")
    public List<Question> retrieveSurveyQuestions(@PathVariable String surveyId) {

        Survey survey =  surveyService.getSurveyById(surveyId);
        if(survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        List<Question> questions = survey.getQuestions();

        if(questions == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return questions;
    }

    @RequestMapping(name = "Retrieve questions by Id", path = "surveys/{surveyId}/questions/{questionId}")
    public Question retrieveQuestionById(
            @PathVariable String surveyId,
            @PathVariable String questionId
    ) {

        Survey survey =  surveyService.getSurveyById(surveyId);
        if(survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Question question = surveyService.getQuestionById(survey, questionId);

        if(question == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return question;
    }

    // POST

    @RequestMapping(path = "surveys/{surveyId}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewSurveyQuestion(
            @PathVariable String surveyId,
            @RequestBody Question question
    ) {

        String questionId = surveyService.addNewSurveyQuestion(surveyId, question);

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().
                        path("/{questionId}").
                        buildAndExpand(questionId).toUri();

        return ResponseEntity.created(location).build();

    }

    // DELETE
    @RequestMapping(name = "Delete questions by Id", path = "surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteQuestionById(
            @PathVariable String surveyId,
            @PathVariable String questionId
    ) {

        Survey survey =  surveyService.getSurveyById(surveyId);
        if(survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

       surveyService.deleteQuestionById(survey, questionId);
       return ResponseEntity.noContent().build();
    }

    //PUT
    @RequestMapping(name = "Update questions by Id", path = "surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateQuestionById(
            @PathVariable String surveyId,
            @PathVariable String questionId,
            @RequestBody Question question
    ) {

        surveyService.updateSurveyQuestion(surveyId, questionId,question);
        return ResponseEntity.noContent().build();
    }

//    {
//        "id": "SOME_ID",
//            "description": "Your Favorite Cloud Platform",
//            "options": [
//        "AWS",
//                "Azure",
//                "Google Cloud",
//                "Oracle Cloud"
//    ],
//        "correctAnswer": "Google Cloud"
//    }

}
