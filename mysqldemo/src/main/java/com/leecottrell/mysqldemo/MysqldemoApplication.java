package com.leecottrell.mysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MysqldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqldemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "<h1>Database is AlIvE</h1><a href='/Customer'>/Customer</a>";
	}

}
