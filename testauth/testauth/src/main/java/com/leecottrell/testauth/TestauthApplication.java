package com.leecottrell.testauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.codec.binary.Base64;

@RestController
@SpringBootApplication
public class TestauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestauthApplication.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> login(@RequestHeader("Authorization") String auth){

		String userPass = auth.substring(6);	//why 6???
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":");
		String authUserName = decryptString.substring(0, colon);
		String authPassword = decryptString.substring(colon + 1);

		if(authUserName.equals("lee") && authPassword.equals("hello")){
			return new ResponseEntity<String> ("Welcome " + authUserName, 
				HttpStatus.ACCEPTED);
		}
		else{
			return new ResponseEntity<String> ("Not authorized, go away", 
				HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}		
	}//end GET

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> post(@RequestHeader("Authorization") String auth){

		String userPass = auth.substring(6);	//why 6? - strip BASIC out
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":");	//find location of colon
		String userName = decryptString.substring(0, colon);
		String password = decryptString.substring(colon+1);

		if(userName.equalsIgnoreCase("lee") && password.equals("hello")){

			//put your service code here

			return new ResponseEntity<String>("Welcome to POST " + 
				userName, HttpStatus.ACCEPTED);
		}
		else{		
			return new ResponseEntity<String> ("POST Not authorized, go away " + 
				userName, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}//end POST


}
