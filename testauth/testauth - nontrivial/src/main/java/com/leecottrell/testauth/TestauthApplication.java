package com.leecottrell.testauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

import java.util.*;

import org.apache.tomcat.util.codec.binary.Base64;

@RestController
@SpringBootApplication
public class TestauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestauthApplication.class, args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Fiction>> login(@RequestHeader("Authorization") String auth) {

		String userPass = auth.substring(6); // why 6???
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":");
		String authUserName = decryptString.substring(0, colon);
		String authPassword = decryptString.substring(colon + 1);

		List<Fiction> characters = new ArrayList<Fiction>();

		if (authUserName.equals("lee") && authPassword.equals("hello")) {

			//characters.add(new Fiction("Welcome Lee", "Accepted"));
			String connectionURL = "Your Connection String";
			try {
				Connection con = DriverManager.getConnection(connectionURL);
				Statement stmt = con.createStatement();
				String SQL = "Select * from fiction";
				ResultSet rs = stmt.executeQuery(SQL);
				while (rs.next()) {
					characters.add(new Fiction(rs.getString("character"),
						rs.getString("source")));
				}
			} catch (SQLException e) {
				characters.add(new Fiction("SQL Error", e.toString()));
				return new ResponseEntity<List<Fiction>>(characters, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Fiction>>(characters, HttpStatus.ACCEPTED);

		} else {
			characters.add(new Fiction("Not Authorized", "Invalid username or password"));
			return new ResponseEntity<List<Fiction>>(characters, 
					HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}// end GET

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestHeader("Authorization") String auth, 
		@RequestParam(value="character") String character, 
		@RequestParam(value="source") String source) {

		String userPass = auth.substring(6); // why 6? - strip BASIC out
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":"); // find location of colon
		String userName = decryptString.substring(0, colon);
		String password = decryptString.substring(colon + 1);

		if (userName.equalsIgnoreCase("lee") && password.equals("hello")) {

			if(source.isEmpty() || character.isEmpty()){
				return new ResponseEntity<String>("Character and source needed " +
					userName, HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
			//protect sizes
			if (source.length() > 100){
				source = source.substring(0, 100);
			}
			if(character.length()> 50){
				character = character.substring(0, 50);
			}

			//strip special chars like ; ! 
			source = source.replace(";", "");
			source = source.replace("!", "");
			source = source.replace("drop", "");
			
			character = character.replace(";", "");
			character = character.replace("!", "");
			character = character.replace("drop", "");


			String connectionURL = "Your Connection String";;
			try {
				Connection con = DriverManager.getConnection(connectionURL);
				Statement stmt = con.createStatement();

				String SQL = String.format("begin tran;insert into fiction values('%s', '%s');commit;",
					 character, source);

				stmt.execute(SQL);
			}
			catch (SQLException e) {
				
				return new ResponseEntity<String>(character + " not added\n" + e.toString(), 
					HttpStatus.INTERNAL_SERVER_ERROR);
			}
			// put your service code here
			return new ResponseEntity<String>(character + " added to database " + userName, HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("POST Not authorized, go away " +
					userName, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}// end POST

}
