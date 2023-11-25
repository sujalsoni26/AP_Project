package com.example.stickhero_2;

import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

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

    public Hero(){

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
        double length = Controller.getController().calculateLength();
        Node hero = (Node) NewGame.scene.lookup("#hero");
//        Node hero = Controller.getController().getHero();
        TranslateTransition walk = new TranslateTransition();
        walk.setNode(hero);
        walk.setByX(length);
        walk.setDuration(Duration.millis(800));
        walk.play();

        walk.setOnFinished(e-> {
            if (length > Pillar.getPillarArrayList().get(1).getDistanceFromPrevious() && length < Pillar.getPillarArrayList().get(1).getDistanceFromPrevious() + Pillar.getPillarArrayList().get(1).getRectangle().getWidth()){
                System.out.println("Inside if");
                Controller.p1.shiftPillar();
                if (Pillar.isTransitionCompleted()){
                    Controller.setHeroReachedonNextPillar(true);
                    Pillar.setTransitionCompleted(false);
                    Controller.s1.createNewStick();
                }

            }else {
                System.out.println("Inside else");
                ArrayList<Pillar> a = Pillar.getPillarArrayList();
                for(int i=0;i<Pillar.getPillarArrayList().size();i++){
                    System.out.println(a.get(i).getDistanceFromPrevious());
                    System.out.println(a.get(i).getRectangle().getWidth());

                }

//                System.out.println(Pillar.getPillarArrayList().get(1).getDistanceFromPrevious());
//                System.out.println(Pillar.getPillarArrayList().get(1).getDistanceFromPrevious() + Pillar.getPillarArrayList().get(1).getWidth());
                Controller.setHeroReachedonNextPillar(false);
                fallHero(hero);
                return;

            }
        });

    }
    public void fallHero(Node hero){
        TranslateTransition fall = new TranslateTransition();
//        Node hero = Controller.getController().getHero();
        fall.setNode(hero);
        fall.setDuration(Duration.millis(800));
        fall.setByY(1000);
        fall.play();

    }
}
