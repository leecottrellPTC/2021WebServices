package com.leecottrell.testauth;

public class Fiction {
    private String character;
    private String source;

    public Fiction(String character, String source) {
        this.character = character;
        this.source = source;
    }
    public Fiction() {
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

