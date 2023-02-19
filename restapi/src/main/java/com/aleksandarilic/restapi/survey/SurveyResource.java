package com.aleksandarilic.restapi.survey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SurveyResource {

    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

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

}
