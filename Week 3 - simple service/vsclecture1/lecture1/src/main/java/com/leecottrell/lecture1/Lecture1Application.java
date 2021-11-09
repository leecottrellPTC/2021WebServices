package com.leecottrell.lecture1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class Lecture1Application {

	public static void main(String[] args) {
		SpringApplication.run(Lecture1Application.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getResponse(){
		return "<h1>Woo Hoo! It Works</h1>";
	}

	@RequestMapping(value="/", method=RequestMethod.PUT)
	@ResponseStatus(code=HttpStatus.FORBIDDEN,reason="No update allowed")
	public String putResponse(){
		return "<h1>Put is not ready yet...</h1>";
	}

}
