package com.leecottrell.writerecipejson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.put;

public class App {

    public static void main(String[] args) {
        // TODO code application logic here
        ObjectMapper mapper = new ObjectMapper();

        //create variable of type Recipe
        Recipe pbj = new Recipe();
        pbj.setRecipeName("Peanut Butter and Jelly");
        pbj.setCalories(189);
        pbj.setAllergyWarning(true);
        pbj.setIngredients(Arrays.asList("2 Pieces of Bread", "Peanut Butter", "Jelly"));
        Map<String, String> theSteps = new HashMap() {
            {
                put("Step1", "Put bread on plate");
                put("Step2", "Spread jelly on one slice of bread");
                put("Step3", "Spread peanut butter on one slice of bread");
                put("Step4", "Put the two pieces of bread together");
            }
        };
        pbj.setSteps(theSteps);

        try {
            //build a JSON string
            //String JSON = mapper.writeValueAsString(pbj);
            String PrettyJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pbj);
            System.out.println(PrettyJSON);
        } catch (JsonProcessingException ex) {
            System.out.println("JSON String failed: " + ex.toString());
        }

        try {
            mapper.writeValue(new File("c:\\data\\vorhees.json"), pbj);
        } catch (IOException ex) {
            System.out.println("JSON Write failed: " + ex.toString());
        }
        
        
    }

}
