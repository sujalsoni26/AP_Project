package com.example.stickhero_2;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
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
        ImageView hero = (ImageView) NewGame.scene.lookup("#hero");
        Line stick = (Line) NewGame.scene.lookup("#stick");
        Rectangle pillar1 = (Rectangle) NewGame.scene.lookup("#firstPillar");
        Rectangle pillar2 = (Rectangle) NewGame.scene.lookup("#secondPillar");
        System.out.println("pillar1 : " +
                "width = " + pillar1.getWidth() +
                " getX = " + pillar1.getX() +
                " getLayoutX = " + pillar1.getLayoutX());
        System.out.println("========================");
        System.out.println("pillar2 : " +
                "width = " + pillar2.getWidth() +
                " getX = " + pillar2.getX() +
                " getLayoutX = " + pillar2.getLayoutX());
        double x = pillar1.getX() + pillar1.getWidth() - pillar2.getX() - pillar2.getWidth();
        System.out.println("Value of X = " + x);
//        KeyValue hero1 = new KeyValue(hero.translateXProperty(), )
        KeyValue keyValue = new KeyValue(pillar2.xProperty(), pillar2.getX() + x);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
        timelinePillar = new Timeline(keyFrame);
        timelinePillar.setCycleCount(1);

        timelinePillar.setOnFinished(e->{
            System.out.println("Ani finished.");
        });



//        Timeline moveHero1Timeline = new Timeline(
//                new KeyFrame(Duration.millis(500),
//                        e -> {
//                            double destinationX = (pillar2.getX() + pillar2.getWidth()) - hero.getX() - 32;
//                            hero.setTranslateX(destinationX);
//                        }
//                )
//        );

        // Create a Timeline for the second hero and pillar movements
//        Timeline moveHero2PillarTimeline = new Timeline(
//                new KeyFrame(Duration.ZERO),
//                new KeyFrame(Duration.millis(1000),
//                        e -> {
//                            pillar2.setTranslateX(x);
//                            hero.setTranslateX(hero.getTranslateX() + x);
//
//                            // The rest of your code after the movement
//                            System.out.println("Second Pillar after 1: X = " + pillar2.getX() + " Width = " + pillar2.getWidth());
//                            pillar2.setId("firstPillar");
//                            pillar2.setX(pillar2.getX() + x);
//                            pillar2.setFill(Color.BLUEVIOLET);
//                            System.out.println("Second Pillar after 2: X = " + pillar2.getX() + " Width = " + pillar2.getWidth());
//                            transitionCompleted = true;
//                        }
//                )
//        );

//        moveHero1Timeline.setOnFinished(e -> {
//            moveHero2PillarTimeline.setCycleCount(Timeline.INDEFINITE);
//            moveHero2PillarTimeline.play();
//        });

        // Start the animation
//        moveHero1Timeline.play();
//        TranslateTransition movePillar = new TranslateTransition(Duration.millis(500), pillar2);
        TranslateTransition moveHero1 = new TranslateTransition(Duration.millis(500), hero);
        TranslateTransition moveHero2 = new TranslateTransition(Duration.millis(500), hero);
//
        moveHero1.setToX((pillar2.getX() +pillar2.getWidth()) - hero.getX()- 32);
        moveHero1.play();
//
//        movePillar.setToX(x);
        moveHero2.setByX(x);
//        pillar2.setFill(Color.RED);
//
        moveHero1.setOnFinished(e->{
//            timelinePillar.play();
            ParallelTransition parallelTransition = new ParallelTransition(moveHero2, timelinePillar);
            parallelTransition.play();
            parallelTransition.setOnFinished(g-> {

                AnchorPane anchorPane = (AnchorPane) NewGame.scene.lookup("#anchorPane");
//                Rectangle pillar4 = (Rectangle) NewGame.scene.lookup("#firstPillar");
                anchorPane.getChildren().remove(pillar1);
                anchorPane.getChildren().remove(stick);
                pillarArrayList.remove(0);
//                Rectangle pillar3 = (Rectangle) NewGame.scene.lookup("#secondPillar");
                System.out.println("Second PIllar after 1: X = " + pillar2.getX() + " Width = " + pillar2.getWidth());
                pillar2.setId("firstPillar");
//                Rectangle pillar5 = (Rectangle) NewGame.scene.lookup("#firstPillar");
//                pillar2.setX(pillar2.getX() + x);
//                pillar2.setFill(Color.BLUEVIOLET);
                System.out.println("Second PIllar after 2: X = " + pillar2.getX() + " Width = " + pillar2.getWidth());
                Controller.s1.createNewStick();
                transitionCompleted = true;
                Controller.setHeroReachedonNextPillar(true);
            });
//
//
        });
//




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
            pillar.setX(randomDistance);
            pillar.setHeight(height);
            pillar.setY(291);
            double distFromPrevious = calculateDistance(pillarArrayList.get(0).rectangle, pillar);
            pillar.toFront();
            pillar.setId("secondPillar");
            Rectangle pillar3 = (Rectangle) NewGame.scene.lookup("#firstPillar");
            newPillar = new Pillar(pillar3, distFromPrevious);
//            pillarArrayList.add(newPillar);
            System.out.println("Distance from previous pillar  =  " + distFromPrevious);
            anchorPane.getChildren().add(pillar);
        }
    }

    private double calculateDistance(Rectangle pillar1, Rectangle pillar2) {
        double endX1 = pillar1.getX() + pillar1.getWidth(); // X-coordinate of the right edge of the first pillar
        double startX2 = pillar2.getX(); // X-coordinate of the left edge of the second pillar

        System.out.println("X2= " + startX2);
        System.out.println("X1= " + endX1);

        return Math.abs(startX2 - endX1);
    }
}