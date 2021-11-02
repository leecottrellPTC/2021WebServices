/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leecottrell.writerecipejson;


import java.util.List;
import java.util.Map;
public class Recipe {
    private String RecipeName;
    private int Calories;
    private List <String> Ingredients;
    private Map<String,String> Steps;
    private boolean  AllergyWarning;
    //build setters, getters, constructors
        
    public Recipe() {
    }

    public Recipe(String RecipeName, int Calories, List<String> Ingredients, Map<String, String> Steps, boolean AllergyWarning) {
        this.RecipeName = RecipeName;
        this.Calories = Calories;
        this.Ingredients = Ingredients;
        this.Steps = Steps;
        this.AllergyWarning = AllergyWarning;
    }

    public Map<String, String> getSteps() {
        return Steps;
    }

    public void setSteps(Map<String, String> Steps) {
        this.Steps = Steps;
    }


    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String RecipeName) {
        this.RecipeName = RecipeName;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int Calories) {
        this.Calories = Calories;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<String> Ingredients) {
        this.Ingredients = Ingredients;
    }

    public boolean isAllergyWarning() {
        return AllergyWarning;
    }

    public void setAllergyWarning(boolean AllergyWarning) {
        this.AllergyWarning = AllergyWarning;
    }
    
    
}

