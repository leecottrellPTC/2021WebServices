package com.example.readcompanyjson;

import java.util.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import java.io.*;
import java.nio.file.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static List<Company> companyList = new ArrayList<Company>();
    static int counter = 0;

    public static String readAllLines(String path) {
        // https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void printTop(int num){
        for(int x=0; x < num; x++){
            System.out.println(companyList.get(x).toString());
        }
    }

    public static void countNA(){
        //use for each loop just because
        int naCount = 0;
        for(Company aCompany : companyList){
            if(aCompany.getIndustry().equalsIgnoreCase("n/a")){
                naCount ++;
                System.out.println(aCompany.getCompany());
            }//end of if
        }//end of for
        System.out.println("N/A Companies found = " + naCount);
    }
    public static void main( String[] args )
    {
        String json;
        Company aCompany;
        ObjectMapper mapper = new ObjectMapper();
        json = readAllLines("c:\\data\\companies.json");
        //System.out.println(json);
        try {
            companyList = mapper.readValue(json, new TypeReference<List<Company>>(){});
            counter = companyList.size();
            System.out.println("Lines read " + counter);
            //printTop(10);
            //countNA();
        } catch (JsonMappingException e) {

            System.out.println("Mappng exception " + e.toString());
        } catch (JsonProcessingException e) {
  
            System.out.println("Processing exception " + e.toString());
        }
    }
}
