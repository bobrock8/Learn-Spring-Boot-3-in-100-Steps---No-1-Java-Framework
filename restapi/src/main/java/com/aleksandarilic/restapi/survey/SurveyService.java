package com.aleksandarilic.restapi.survey;


import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

@Service
public class SurveyService {

    private static List<Survey> surveys = new ArrayList<>();

    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public static List<Survey> getSurveys() {
        return surveys;
    }

    public Survey getSurveyById(String id) {
        Optional<Survey> optionalSurvey =
                surveys.stream().filter(survey -> Objects.equals(survey.getId(), id)).findFirst();
        if (optionalSurvey.isEmpty()) return null;

        return optionalSurvey.get();
    }

    public Question getQuestionById(Survey survey, String questionId) {
        Optional<Question> optionalQuestion =
                survey.getQuestions().stream().filter(question -> Objects.equals(question.getId(), questionId)).findFirst();
        if (optionalQuestion.isEmpty()) return null;

        return optionalQuestion.get();
    }

    public String addNewSurveyQuestion(String surveyId, Question question) {
        Survey survey = getSurveyById(surveyId);
        if (survey == null) return null;

        SecureRandom secureRandom = new SecureRandom();
        String randomId = new BigInteger(32, secureRandom).toString();
        question.setId(randomId);
        survey.getQuestions().add(question);
        return question.getId();
    }
}
