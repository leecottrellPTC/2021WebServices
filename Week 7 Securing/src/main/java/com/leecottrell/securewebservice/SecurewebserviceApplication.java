package com.leecottrell.securewebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.sql.*;
import java.util.*;

import org.apache.tomcat.util.codec.binary.Base64;

@RestController
@SpringBootApplication
public class SecurewebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurewebserviceApplication.class, args);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Fiction>> login(@RequestHeader("Authorization") String auth){

		String userPass = auth.substring(6);	//why 6? - strip BASIC out
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":");	//find location of colon
		String userName = decryptString.substring(0, colon);
		String password = decryptString.substring(colon+1);

		List<Fiction> characters = new ArrayList<Fiction>();

		if(userName.equalsIgnoreCase("lee") && password.equals("5d41402abc4b2a76b9719d911017c592")){

			//put your service code here
			//characters.add(new Fiction("Welcome " + userName, "Accepted"));
			String connectionURL = "jdbc:sqlserver://cottrellsql.database.windows.net:1433;database=cottrell2021;user=fiction@cottrellsql;password=a,plain3;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			try{
				Connection con = DriverManager.getConnection(connectionURL);
				Statement stmt = con.createStatement();
				String SQL = "select * from fiction";
				ResultSet rs = stmt.executeQuery(SQL);
				while(rs.next()){
					characters.add(new Fiction(rs.getString("character"), rs.getString("source")));
				}
				con.close();
			}
			catch(SQLException e){
				characters.add(new Fiction("SQL Error ", e.toString()));
				return new ResponseEntity<List<Fiction>>(characters, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Fiction>>(characters, HttpStatus.ACCEPTED);
		}
		else{		
			characters.add(new Fiction("Not Authorized", "Invalid username or password"));
			return new ResponseEntity<List<Fiction>>(characters, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> post(@RequestHeader("Authorization") String auth, 
		@RequestParam(value="character") String character, 
		@RequestParam(value="source") String source){

		String userPass = auth.substring(6);	//why 6? - strip BASIC out
		byte[] decryptArray = Base64.decodeBase64(userPass);
		String decryptString = new String(decryptArray);
		int colon = decryptString.indexOf(":");	//find location of colon
		String userName = decryptString.substring(0, colon);
		String password = decryptString.substring(colon+1);

		if(userName.equalsIgnoreCase("lee") && password.equals("5d41402abc4b2a76b9719d911017c592")){

			if(source.isEmpty() || character.isEmpty()){
				return new ResponseEntity<String> ("I need both a character and a source " + 
				userName, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String connectionURL = "jdbc:sqlserver://cottrellsql.database.windows.net:1433;database=cottrell2021;user=fiction@cottrellsql;password=a,plain3;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
			try{
				Connection con = DriverManager.getConnection(connectionURL);
				Statement stmt = con.createStatement();
				
				//protecting the lengths
				if(source.length() > 100){
					source = source.substring(0, 100);
				}
				if(character.length() > 50){
					character = character.substring(0, 50);
				}

				//replace special characters
				source = source.replace(";", "");
				source = source.replace("!", "");
				source = source.replace("drop", "");	//kill SQL commands

				character = character.replace(";", "");
				character = character.replace("!", "");
				character = character.replace("drop", "");

				String SQL = String.format("begin tran;insert into fiction values('%s', '%s');commit;", character, source);
				stmt.execute(SQL);
			}
			catch(SQLException ex){
				return new ResponseEntity<String> ("SQL Error\n" +ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			

			return new ResponseEntity<String>(character + " added to fiction table", HttpStatus.ACCEPTED);
		}
		else{		
			return new ResponseEntity<String> ("POST Not authorized, go away " + 
				userName, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}
	}

}
