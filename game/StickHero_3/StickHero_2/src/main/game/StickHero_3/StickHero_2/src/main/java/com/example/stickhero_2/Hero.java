package com.example.stickhero_2;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.util.Duration;

public class Hero {
    private String name;
    private static int highscore = 0;
    private static int cherries = 0;
    private boolean flip = false; // false for straight and true for invert
    private Controller controller;

    public Hero(String name) {
        this.name = name;
    }
    public Hero(){

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

    public void moveHero(Bounds bound, Controller controller){
        double length = controller.calculateLength();
        Node hero = controller.getHero();
        TranslateTransition walk = new TranslateTransition();
        walk.setNode(hero);
        walk.setByX(length);
        walk.setDuration(Duration.millis(800));
        walk.play();
    }

    public void fallHero(){

    }
}
