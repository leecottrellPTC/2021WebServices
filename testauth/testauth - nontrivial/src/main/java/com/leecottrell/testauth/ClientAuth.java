package com.leecottrell.testauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;

public class ClientAuth {
    public static String buildAuth(String username, String password) {
        String temp = username + ":" + password;
        byte[] encoded = Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encoded);
        return authHeader;
    }

    final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void getClient(){
        HttpGet request = new HttpGet("http://localhost:8080");
        String auth = buildAuth("lee", "hello");
        request.addHeader(HttpHeaders.AUTHORIZATION, auth);
        try{
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            //do something with the response       

            if(response.getStatusLine().getStatusCode() == 202){
                System.out.println("Logged in");
                System.out.println("------------------------");
                //System.out.println(EntityUtils.toString(entity));
                try{
                    ObjectMapper mapper = new ObjectMapper();
                    List<Fiction> charList = new ArrayList<Fiction>();
                    charList = mapper.readValue(EntityUtils.toString(entity), 
                    new TypeReference<List<Fiction>>(){});
                    for (Fiction aChar : charList) {
                        System.out.printf("%s from %s\n", aChar.getCharacter(), 
                        aChar.getSource());
                    }
                }
                catch(JsonMappingException e){
                    System.out.println("JSON Error " + e);
                }
                System.out.println("------------------------");
            }
            else{
                System.out.println("Not logged in");
            }

        }
        catch(IOException ex){
            System.out.println("IO Exception " + ex.toString());
        }
    }// end getClient()

    public static void postClient() {

        String character;
        String source;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a character name ");
        character = keyboard.nextLine();
        System.out.println("Enter where " + character + " is from ");
        source = keyboard.nextLine();
        //localhost:8080?character=Lee&source=Nowhere
        String URL = "Http://localhost:8080?character=" + character + "&source=" + source;
        URL = URL.replace(" ", "%20");    //replace the space with %20
        HttpPost request = new HttpPost(URL);

        String auth = buildAuth("lee", "hello");
        request.addHeader(HttpHeaders.AUTHORIZATION, auth);

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            // do something with the response

            if (response.getStatusLine().getStatusCode() == 202) {
                System.out.println("POST Logged in");
            } else {
                System.out.println("POST Not logged in");
            }
            System.out.println("------------------------");
            System.out.println(EntityUtils.toString(entity));
            System.out.println("------------------------");
        } catch (IOException ex) {
            System.out.println("IO Exception " + ex.toString());
        }

    }

    public static void main(String[] args) {
        //getClient();
        postClient();
        // String character;
        // String source;
        // Scanner keyboard = new Scanner(System.in);
        // System.out.println("Enter a character name ");
        // character = keyboard.nextLine();
        // System.out.println("Enter where " + character + " is from ");
        // source = keyboard.nextLine();
        // //localhost:8080?character=Lee&source=Nowhere
        // //URL postURL = new URL("Http://localhost:8080?character=" + character + "&source=" + source);
        // String query = "?character=" + character + "&source=" + source;
        // String URL = "Http://localhost:8080?character=" + character + "&source=" + source;
        // URL = URL.replace(" ", "%20");    //replace the space with %20
        // System.out.println(URL);
       // String name = "Cottrell";
        //System.out.println(name.substring(0, 4));
    }// end main
}
