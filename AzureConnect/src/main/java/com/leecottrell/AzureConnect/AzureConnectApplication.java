package com.leecottrell.AzureConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class AzureConnectApplication {

	@RequestMapping("/")
	public String home(){
		return "<h1>I am alive</h1>";
	}
	public static void main(String[] args) {
		SpringApplication.run(AzureConnectApplication.class, args);
	}

}
