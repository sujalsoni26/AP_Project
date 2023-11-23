package com.example.stickhero_2;

public class Hero {
    private String name;
    private static int highscore = 0;
    private static int cherries = 0;
    private boolean flip = false; // false for straight and true for invert

    public Hero(String name) {
        this.name = name;
    }

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

    public static void addCherries(){
        cherries++;
    }

    public void subCherries(int n){
        cherries -= n;
    }

    public void moveHero(){

    }
    public void fallHero(){

    }
}
