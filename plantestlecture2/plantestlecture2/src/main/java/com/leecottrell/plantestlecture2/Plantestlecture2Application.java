package com.leecottrell.plantestlecture2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@SpringBootApplication
public class Plantestlecture2Application {

	public static void main(String[] args) {
		SpringApplication.run(Plantestlecture2Application.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getRespond(){
		return "<h1>Hey, it works!</h1>";
	}

}
