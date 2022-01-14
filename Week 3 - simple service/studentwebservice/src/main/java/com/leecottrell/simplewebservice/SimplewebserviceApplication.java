package com.leecottrell.simplewebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SimplewebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplewebserviceApplication.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> getResponse(){
		String response = "<h1>It works</h1>";

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> postResponse(){
		String response = "not implemented yet";

		return new ResponseEntity<String>(response, HttpStatus.NOT_IMPLEMENTED);
	}

}
