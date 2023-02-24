package com.aleksandarilic.restapi.survey;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = SurveyResource.class)
@AutoConfigureMockMvc(addFilters = false)
class SurveyResourceMVCTests {


    @MockBean
    private SurveyService surveyService;

    @Autowired
    private MockMvc mockMvc;


    private static final String SPECIFIC_QUESTION_URL =
            "http://localhost:8080/surveys/Survey1/questions/Question1";

    private static final String DEFAULT_QUESTION_URL =
            "http://localhost:8080/surveys/Survey1/questions";

    // MOCK -> retrieveQuestionById
    // fire a request /surveys/{surveyId}/questions/{questionId}
    @Test
    void test_retrieveQuestionById_return_404() throws Exception {
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);


        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());

        assertEquals(404, mvcResult.getResponse().getStatus());
    }


    @Test
    void test_retrieveQuestionById_return_expected_response() throws Exception {

        // GET
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1));
        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);


        String expectedResponse = """
                {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                                
                """;

        // WHEN
        when(surveyService.getSurveyById("Survey1")).thenReturn(survey);
        when(surveyService.getQuestionById(survey, "Question1")).thenReturn(question1);


        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // THEN
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println(mvcResult.getResponse().getStatus());

        assertEquals(200, mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(
                expectedResponse,
                mvcResult.getResponse().getContentAsString(),
                true
        );
    }

    @Test
    void test_addNewSurveyQuestion() throws Exception {



        //addNewSurveyQuestion
        // POST
        // body
        // url http://localhost:8080/surveys/Survey1/questions
        // 201
        // location header

        // GET
        String questionID = "123456789";
        String requestBody = """
                   {
                            "description": "Fastest Growing Cloud Platform",
                            "options": [
                                "AWS",
                                "Azure",
                                "Google Cloud",
                                "Oracle Cloud"
                            ],
                        "correctAnswer": "Google Cloud"
                    }
                """;
        String location = "http://localhost:8080/surveys/Survey1/questions/" + questionID;

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post(DEFAULT_QUESTION_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON);

        // WHEN
        when(surveyService.addNewSurveyQuestion(anyString(), any())).thenReturn(questionID);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // THEN
        System.out.println(mvcResult.getResponse().getHeader("Location"));

        assertEquals(201, mvcResult.getResponse().getStatus());
        assertEquals(location, mvcResult.getResponse().getHeader("Location"));


    }


}
