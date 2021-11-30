package com.leecottrell.testwebservicelecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TestwebservicelectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestwebservicelectureApplication.class, args);
	}

	@RequestMapping(value = "/", method= RequestMethod.GET)
	public ResponseEntity<String> getResponse(){
		return new ResponseEntity<String>("<h1>Hey, it works</h1>", HttpStatus.OK);
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> postResponse(){
		return new ResponseEntity<String>("Not Configured", HttpStatus.NOT_IMPLEMENTED);
		
	}

}
