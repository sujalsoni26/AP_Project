package com.example.stickhero_2;

import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Pillar {
    private Timeline timelinePillar;
    Random random = new Random();
    private int width;
    private Rectangle rectangle;
    static Pillar newPillar = new Pillar();


    private static ArrayList<Pillar> pillarArrayList = new ArrayList<>();


    public static ArrayList<Pillar> getPillarArrayList() {
        return pillarArrayList;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    private static int height = 267;
    private double distanceFromPrevious;
    private static int totalPillarCrossed;

    public Pillar() {
    }

    public Pillar(Rectangle pillarr, double distanceFromPrevious) {
        this.rectangle = pillarr;
        System.out.println("In constructor " + this.rectangle.getWidth());
        this.distanceFromPrevious = distanceFromPrevious;
        System.out.println("Dist : " + distanceFromPrevious);
        pillarArrayList.add(this);
        System.out.println(pillarArrayList.get(0).getWidth());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getDistanceFromPrevious() {
        return distanceFromPrevious;
    }

    public void setDistanceFromPrevious(int distanceFromPrevious) {
        this.distanceFromPrevious = distanceFromPrevious;
    }

    public static int getTotalPillarCrossed() {
        return totalPillarCrossed;
    }

    public static void setTotalPillarCrossed(int totalPillarCrossed) {
        Pillar.totalPillarCrossed = totalPillarCrossed;
    }

    public Timeline getTimelinePillar() {
        return timelinePillar;
    }

    private static boolean transitionCompleted = false;

    public static boolean isTransitionCompleted() {
        return transitionCompleted;
    }

    public static void setTransitionCompleted(boolean transitionCompleted) {
        Pillar.transitionCompleted = transitionCompleted;
    }


    public void shiftPillar(){
        Node hero = (Node) NewGame.scene.lookup("#hero");
        Line stick = (Line) NewGame.scene.lookup("#stick");
        double x = pillarArrayList.get(0).rectangle.getLayoutX() + pillarArrayList.get(0).rectangle.getWidth() - pillarArrayList.get(1).rectangle.getLayoutX() - pillarArrayList.get(1).rectangle.getWidth();
        TranslateTransition movePillar = new TranslateTransition(Duration.millis(500), pillarArrayList.get(1).rectangle);
        TranslateTransition moveHero1 = new TranslateTransition(Duration.millis(500), hero);
        TranslateTransition moveHero2 = new TranslateTransition(Duration.millis(500), hero);
        movePillar.setToX(x);
        moveHero1.setToX((pillarArrayList.get(1).rectangle.getLayoutX() +pillarArrayList.get(1).rectangle.getWidth()) - hero.getLayoutX()- 32);
        moveHero2.setByX(x);
        moveHero1.play();
        moveHero1.setOnFinished(e->{
            Controller.setHeroReachedonNextPillar(false);
            ParallelTransition parallelTransition = new ParallelTransition(moveHero2, movePillar);
            parallelTransition.play();
            parallelTransition.setOnFinished(g-> {
                Controller.setHeroReachedonNextPillar(true);
                AnchorPane anchorPane = (AnchorPane) NewGame.scene.lookup("#anchorPane");
                anchorPane.getChildren().remove(pillarArrayList.get(0).rectangle);
                anchorPane.getChildren().remove(stick);
                pillarArrayList.remove(0);
                transitionCompleted = true;
            });

        });





    }

    public void generateNextPillar(AnchorPane anchorPane){

        if (anchorPane == null){
            System.out.println("Anchor pane is null");
        }else {
            int randomWidth = random.nextInt((200 - 40) + 1) + 40;
            int randomDistance = random.nextInt((400 - 200) + 1) + 200;
            System.out.println("Random Width: " + randomWidth);
            System.out.println("Random Dist: " + randomDistance);
            Rectangle pillar = new Rectangle();
            pillar.setWidth(randomWidth);
            pillar.setFill(Color.BLACK);
            pillar.setLayoutX(randomDistance);
            pillar.setHeight(height);
            pillar.setLayoutY(291);
            double distFromPrevious = calculateDistance(pillarArrayList.get(0).rectangle, pillar);
            pillar.toFront();
            newPillar = new Pillar(pillar, distFromPrevious);
//            pillarArrayList.add(newPillar);
            System.out.println("Distance from previous pillar  =  " + distFromPrevious);
            anchorPane.getChildren().add(pillar);
        }
    }

    private double calculateDistance(Rectangle pillar1, Rectangle pillar2) {
        double endX1 = pillar1.getLayoutX() + pillar1.getWidth(); // X-coordinate of the right edge of the first pillar
        double startX2 = pillar2.getLayoutX(); // X-coordinate of the left edge of the second pillar

        System.out.println("X2= " + startX2);
        System.out.println("X1= " + endX1);

        return Math.abs(startX2 - endX1);
    }
}