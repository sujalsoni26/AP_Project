package com.example.stickhero_2;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Hero {
    private String name;
    private Timeline timelineHero;
    private static int highscore = 0;
    private static int cherries = 0;
    private Controller controller;
    private boolean flip = false; // false for straight and true for invert
    private boolean isHeroMoving;

    public Hero(String name) {
        this.controller = Controller.getController();
        this.isHeroMoving = false;
        this.name = name;
    }

    public Timeline getTimelineHero() {
        return timelineHero;
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

    public boolean isHeroMoving() {
        return isHeroMoving;
    }

    public void setHeroMoving(boolean heroMoving) {
        isHeroMoving = heroMoving;
    }

    public static void addCherries(){
        cherries++;
    }

    public void subCherries(int n){
        cherries -= n;
    }

    public void moveHero(){
//        timelineHero = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(controller.getHero().sc, controller.getStick().getScaleY())),
//                new KeyFrame(Duration.millis(20), ev -> {
//                    controller.getStick().setEndY((controller.getStick().getEndY() - 2));
//                }));
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), controller.getHero());
        translateTransition.setToX(200);
        translateTransition.setAutoReverse(false);
        translateTransition.play();

    }
    public void fallHero(){



    }
}
