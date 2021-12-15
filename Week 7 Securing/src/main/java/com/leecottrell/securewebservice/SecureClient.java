package com.leecottrell.securewebservice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.apache.tomcat.util.codec.binary.Base64;
import java.util.*;

public class SecureClient {
    public static String buildAuth(String username, String password) {
        String authHeader = "";
        String temp = username + ":" + password;
        String encoded = new String(
                Base64.encodeBase64(String.format("%s:%s", username, password).getBytes(StandardCharsets.US_ASCII)));
        authHeader = "Basic " + encoded;
        return authHeader;
    }

    final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) {
        // getClient();
        postClient();
    }

    public static void postClient() {
        // System.out.println("I work!!");
        String username = "lee";
        String password = "hello";
        // input from keyboard later
        String auth = buildAuth(username, password);

        String character;
        String source;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a character name ");
        character = keyboard.nextLine();

        System.out.println("Enter the source for " + character);
        source = keyboard.nextLine();
        //localhost:8080?character=YT&source=Snow

        String URL = "http://localhost:8080?character=" + character + "&source=" + source;
        URL = URL.replace(" ", "%20");

        HttpPost request = new HttpPost(URL);
        request.addHeader(HttpHeaders.AUTHORIZATION, auth);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 202) {
                // logged in correctly
                // make it actually post

                System.out.println(EntityUtils.toString(entity));
            } else {
                System.out.println("NOT logged in :(");
            }

            // System.out.println("-----------------------------------");
            // System.out.println(EntityUtils.toString(entity));
            // System.out.println("-----------------------------------");

        } catch (IOException ex) {
            System.out.println("IO Exception " + ex.toString());
        }
        // System.out.println(auth);
    }// end POST client

    public static void getClient() {
        // System.out.println("I work!!");
        String username = "lee";
        String password = "hello";
        // input from keyboard later
        String auth = buildAuth(username, password);

        HttpGet request = new HttpGet("http://localhost:8080");
        request.addHeader(HttpHeaders.AUTHORIZATION, auth);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 202) {
                // logged in correctly
                // do something with the service response
                // System.out.println("Logged in!");
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    List<Fiction> charList = new ArrayList<Fiction>();
                    charList = mapper.readValue(EntityUtils.toString(entity), new TypeReference<List<Fiction>>() {
                    });
                    for (Fiction aChar : charList) {
                        System.out.printf("%s from %s\n", aChar.getCharacter(), aChar.getSource());
                    }
                } catch (JsonMappingException e) {
                    System.out.println("JSON Error " + e);
                }

            } else {
                System.out.println("NOT logged in :(");
            }

        } catch (IOException ex) {
            System.out.println("IO Exception " + ex.toString());
        }
        // System.out.println(auth);
    }// end get client
}
