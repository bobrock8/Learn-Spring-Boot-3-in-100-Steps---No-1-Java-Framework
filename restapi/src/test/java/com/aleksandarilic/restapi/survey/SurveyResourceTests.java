package com.aleksandarilic.restapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyResourceTests {

	private static String QUESTION_URL = "/surveys/Survey1/questions/Question1";

	@Autowired
	private TestRestTemplate template;

	String str = """
			    {
			        "id": "Question1",
			            "description": "Most Popular Cloud Platform Today",
			            "options": [
			        		"AWS",
			                "Azure",
			                "Google Cloud",
			                "Oracle Cloud"
			    		],
			        "correctAnswer": "AWS"
			    }
			""";

	@Test
	void getSurveyQuestion() throws JSONException {
		ResponseEntity<String> responseEntity = template.getForEntity(QUESTION_URL, String.class);
//		[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sun, 19 Feb 2023 17:44:56 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
//		{"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}

		System.out.println(responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
		JSONAssert.assertEquals(str, responseEntity.getBody(), true);
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", Objects.requireNonNull(responseEntity.getHeaders().get("Content-Type")).get(0));
	}

}
