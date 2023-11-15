package com.example.project_deadline1;

public class Hero {
    private String name;
    private int highscore;
    private int cherries;
    private boolean flip; // false for straight and true for invert

    public String getName() {
        return name;
    }

    public int getHighscore() {
        return highscore;
    }

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }

    public boolean isFlip() {
        return flip;
    }

    public void FlipHero(boolean flip) {
        this.flip = flip;
    }

    public void addCherries(){

    }
    public void subCherries(){

    }
    public void moveHero(){

    }
    public void fallHero(){

    }
}
