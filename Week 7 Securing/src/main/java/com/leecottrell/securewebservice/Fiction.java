package com.leecottrell.securewebservice;

public class Fiction {
    private String character;
    private String source;
    public Fiction() {
    }
    public Fiction(String character, String source) {
        this.character = character;
        this.source = source;
    }
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    
    
}
