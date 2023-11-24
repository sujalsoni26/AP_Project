package com.example.stickhero_2;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pillar {
    Timeline timelinePillar;
    private int width;
    private Rectangle pillar;
    private Controller controller;
//    @FXML
//    private Rectangle firstPillar;

    private static int height = 267;
    private int distanceFromPrevious;
    private static int totalPillarCrossed;

    public Pillar(Controller c1) {
        this.controller = c1;
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

    public void generateNextPillar(){
        Random random = new Random();
        int randomWidth = random.nextInt((200-40) + 1) + 40;
        System.out.println("Random Number: " + randomWidth);
        Rectangle pillar = new Rectangle();
        Pillar newPillar = new Pillar(pillar, randomWidth, 100);
        pillar.setX(400);
        pillar.setY(288);
        controller.getAnchorPane().getChildren().add(pillar);
    }
}
