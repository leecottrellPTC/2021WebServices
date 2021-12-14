package com.leecottrell.securewebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.codec.binary.Base64;

@RestController
@SpringBootApplication
public class SecurewebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurewebserviceApplication.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> login(@RequestHeader("Authorization") String auth){

		String userPass = auth.substring(6);	//why 6? - strip BASIC out
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":");	//find location of colon
		String userName = decryptString.substring(0, colon);
		String password = decryptString.substring(colon+1);

		if(userName.equalsIgnoreCase("lee") && password.equals("hello")){

			//put your service code here

			return new ResponseEntity<String>("Welcome " + userName, HttpStatus.ACCEPTED);
		}
		else{		
			return new ResponseEntity<String> ("Not authorized, go away " + userName, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}

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
	}

}
