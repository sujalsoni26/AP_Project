package com.example.stickhero_2;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pillar {
    private Timeline timelinePillar;
    Random random = new Random();
    private int width;
    private Rectangle pillar;
    static Pillar newPillar = new Pillar();


    private static int height = 267;
    private int distanceFromPrevious;
    private static int totalPillarCrossed;

    public Pillar() {
    }

    public Pillar(Rectangle pillarr, int width, int distanceFromPrevious) {
        this.pillar = pillarr;
        pillar.setHeight(height);
        pillar.setWidth(width);
        this.distanceFromPrevious = distanceFromPrevious;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDistanceFromPrevious() {
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


    public void generateNextPillar(AnchorPane anchorPane){

        if (anchorPane == null){
            System.out.println("Anchor pane is null");
        }else {
            int randomWidth = random.nextInt((200 - 40) + 1) + 40;
            int randomDistance = random.nextInt((400 - 200) + 1) + 200;
            System.out.println("Random Width: " + randomWidth);
            System.out.println("Random Dist: " + randomDistance);
            Rectangle pillar = new Rectangle();
            pillar.setFill(Color.BLACK);
            newPillar = new Pillar(pillar, randomWidth, 100);
            pillar.setLayoutX(randomDistance);
            pillar.setLayoutY(291);
            pillar.toFront();
            anchorPane.getChildren().add(pillar);
            pillar.toFront();
        }
    }
}
