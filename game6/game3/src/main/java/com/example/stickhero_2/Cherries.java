package com.example.stickhero_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class Cherries {
    private boolean aboveStick;
    private int distanceFromPillar;
    private Timeline timelineCherry;

    public Cherries(boolean aboveStick, int distanceFromPillar) {
        this.aboveStick = aboveStick;
        this.distanceFromPillar = distanceFromPillar;
    }

    public Cherries(){

    }

    public boolean isAboveStick() {
        return aboveStick;
    }

    public void setAboveStick(boolean aboveStick) {
        this.aboveStick = aboveStick;
    }

    public int getDistanceFromPillar() {
        return distanceFromPillar;
    }

    public void setDistanceFromPillar(int distanceFromPillar) {
        this.distanceFromPillar = distanceFromPillar;
    }

    private static boolean cherryCounted = false;

    public static boolean isCherryCounted() {
        return cherryCounted;
    }

    public static void setCherryCounted(boolean cherryCounted) {
        Cherries.cherryCounted = cherryCounted;
    }

    public double randomNumber(){
        double x;
        Random random = new Random();
        x = random.nextDouble();
        return x;
    }

    public double xCoordinateOfCherry(Rectangle secondPillar){
        int startXofPillar = (int)secondPillar.getX() - 20;
        int endOfFirstPillar = 161 + 5;
        Random random = new Random();
        double xCoordinate = random.nextInt((startXofPillar - endOfFirstPillar) + 1) + endOfFirstPillar;

        return xCoordinate;
    }

    public void generateCherries(Rectangle secondPillar) {
        double r = randomNumber();
        ImageView img = (ImageView) NewGame.scene.lookup("#cherry");
        AnchorPane anchorPane = (AnchorPane) NewGame.scene.lookup("#anchorPane");
        if (img != null){
            anchorPane.getChildren().remove(img);
        }
        if (r > 0.4) {
            ImageView cherry = new ImageView(new Image("imgbin_cherry-png.png"));
            cherry.setPreserveRatio(true);
            cherry.setFitWidth(19);
            cherry.setFitHeight(32);
            cherry.setY(295);
            cherry.setX(xCoordinateOfCherry(secondPillar));
            cherry.setId("cherry");
            anchorPane.getChildren().add(cherry);
        }
        else{
            System.out.println("R ===== " + r);
        }
    }



    public void checkCollisionWithCherry(ImageView hero, ImageView cherry) throws IOException {


        if (cherry !=null){
            Bounds boundsHero = hero.getBoundsInParent();
            Bounds boundsCherry = cherry.getBoundsInParent();
            if (boundsHero.intersects(boundsCherry) && Hero.isFlip() && !cherryCounted) {
                cherryCounted = true;
//            ImageView img = (ImageView) NewGame.scene.lookup("#cherry");
                AnchorPane anchorPane = (AnchorPane) NewGame.scene.lookup("#anchorPane");
                anchorPane.getChildren().remove(cherry);
                Hero.addCherries();
                Controller.getController().updateCherryText();


            }
        }




    }
}
