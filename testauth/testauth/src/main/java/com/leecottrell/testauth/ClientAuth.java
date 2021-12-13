package com.leecottrell.testauth;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
    public static String buildAuth(String username, String password){
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
            }
            else{
                System.out.println("Not logged in");
            }
            System.out.println("------------------------");
            System.out.println(EntityUtils.toString(entity));
            System.out.println("------------------------");
        }
        catch(IOException ex){
            System.out.println("IO Exception " + ex.toString());
        }
    }//end getClient()

    public static void postClient(){
        HttpPost request = new HttpPost("Http://localhost:8080");
        String auth = buildAuth("lee", "hello");
        request.addHeader(HttpHeaders.AUTHORIZATION, auth);

        try{
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            //do something with the response       

            if(response.getStatusLine().getStatusCode() == 202){
                System.out.println("POST Logged in");
            }
            else{
                System.out.println("POST Not logged in");
            }
            System.out.println("------------------------");
            System.out.println(EntityUtils.toString(entity));
            System.out.println("------------------------");
        }
        catch(IOException ex){
            System.out.println("IO Exception " + ex.toString());
        }

    }
    public static void main(String [] args){
        getClient();
        //postClient();
    }//end main
}
