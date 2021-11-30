package com.leecottrell.testwebservicelecture;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@SpringBootTest
class TestwebservicelectureApplicationTests {

	@LocalServerPort
	private int port;
	private URL base;
	
	@Autowired
	private TestRestTemplate template;

	@Test
	void testGet() throws Exception {
		port = 8080;
		this.base = new URL("http://localhost:" + port);
		template = new TestRestTemplate();

		//connect to the service
		//get the response
		//test the response
		final ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String actual = response.getBody();
		String expected = "<h1>Hey, it works</h1>";

		assertEquals(expected, actual, "Testing the get response");

	}

	@Test
	void testPost() throws Exception{
		port = 8080;
		this.base = new URL("http://localhost:" + port);
		template = new TestRestTemplate();
		ResponseEntity<String> response = template.postForEntity(base.toString(), 
			"POST", String.class, "");

		String expected = "501";
		String actual = response.getStatusCode().toString();

		//assertEquals(expected, actual, "POST not implemented test");
		assertTrue(actual.contains(expected), "POST not implemented test");
	}

}
