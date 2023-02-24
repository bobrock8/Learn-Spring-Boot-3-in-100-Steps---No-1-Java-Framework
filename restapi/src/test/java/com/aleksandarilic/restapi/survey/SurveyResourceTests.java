package com.aleksandarilic.restapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SurveyResourceTests {

	private static String QUESTION_1 = "/surveys/Survey1/questions/Question1";
	private static String DEFAULT_QUESTION_URL = "/surveys/Survey1/questions";

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

		HttpEntity<String> httpEntity = new HttpEntity<>(null, createHttpHeaders());

		ResponseEntity<String> responseEntity
				= template.exchange(QUESTION_1, HttpMethod.GET, httpEntity, String.class);

		// ResponseEntity<String> responseEntity = template.getForEntity(QUESTION_1, String.class);
//		[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sun, 19 Feb 2023 17:44:56 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]
//		{"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}

		System.out.println(responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
		JSONAssert.assertEquals(str, responseEntity.getBody(), true);
		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertEquals("application/json", Objects.requireNonNull(responseEntity.getHeaders().get("Content-Type")).get(0));
	}


	@Test
	void addNewSurveyQuestion() {
		// http://localhost:8080/surveys/Survey1/questions/
		// POST
		// header Content-Type application/json
		// 201?
		// check location header http://localhost:8080/surveys/Survey1/questions/1870694470
		String body = """
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

		HttpEntity<String> httpEntity = new HttpEntity<>(body, createHttpHeaders());

		ResponseEntity<String> responseEntity
				= template.exchange(DEFAULT_QUESTION_URL, HttpMethod.POST, httpEntity, String.class);

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		assertTrue(responseEntity.getHeaders().get("Location").get(0).contains("/surveys/Survey1/questions/"));

	}

	private String performingBasicAuthEncoding(String user, String pass) {
		String combined = user + ":" + pass;
		byte[] encodedBytes =
				Base64.getEncoder().encode(combined.getBytes(StandardCharsets.UTF_8));
		return new String(encodedBytes);
	}

	private HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic " + performingBasicAuthEncoding("admin", "123"));
		return headers;
	}

}
