package com.leecottrell.plantestlecture2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.junit.*;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Plantestlecture2ApplicationTests {

	@LocalServerPort
	private int port=8080;

	private URL base;
	@Autowired
	private TestRestTemplate template;



	@Test
	public void testIt() throws Exception{
		this.base = new URL("http://localhost:" + port + "");
		template = new TestRestTemplate();
		final ResponseEntity<String> response = template.getForEntity(base.toString(),
			 String.class);
		String actual = response.getBody();
		String expected = "<h1>Hey, it works!</h1>";

		assertEquals(expected, actual, "Test Body");
	}


}
