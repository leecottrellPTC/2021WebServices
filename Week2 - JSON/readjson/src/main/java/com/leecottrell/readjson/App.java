package com.leecottrell.readjson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {

    static List<CompanyClass> companyList = new ArrayList<CompanyClass>();
    static int counter = 0;

    public static  void readFileIntoArray(){
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader file;
        String line;
        CompanyClass comp;

        try{
            file = new BufferedReader(new FileReader("c:\\data\\companies.json"));
            line = file.readLine();
            while(line != null){
                companyList.add(new CompanyClass());
                companyList.set(counter, mapper.readValue(line, CompanyClass.class));
                line = file.readLine();
                counter ++;
            }
        }
        catch(FileNotFoundException fnfex){
            System.out.println("Error opening file\n");
            System.exit(404);
        }
        catch(IOException iex){
            System.out.println("Parse error\n");
            System.exit(500);

        }
        
    }//end of read file

    static int doSomethingWithData(){
        int countNA = 0;
        for(int x=0; x < counter; x++){
            if(companyList.get(x).getIndustry().equalsIgnoreCase("n/a")){
                countNA ++;
            }
        }
        return countNA;
    }

    public static void main( String[] args )
    {
        readFileIntoArray();
        System.out.println("Records read " + counter);
        System.out.println("Companies with N/A industry " + doSomethingWithData());
      /*  try{
            //ObjectMapper mapper = new ObjectMapper();
            // CompanyClass comp = mapper.readValue(new File("c:\\data\\acompany.json"), CompanyClass.class );
            // System.out.println(comp.toString());
       }
       catch (IOException ex){
           System.out.println(ex.toString());
           return ;
       }
       */
    }
}
