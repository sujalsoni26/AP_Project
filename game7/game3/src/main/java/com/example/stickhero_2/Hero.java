package com.example.stickhero_2;

import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Hero {
    private String name;
    private Timeline timelineHero;
    private static int highscore = 0;
    private static int cherries = 0;
    private Controller controller;
    private static boolean flip = false; // false for straight and true for invert
    private boolean isHeroMoving;

    public Hero(String name) {
        this.controller = Controller.getController();
        this.isHeroMoving = false;
        this.name = name;
    }

    public Hero() {
    }


    public Timeline getTimelineHero() {
        return timelineHero;
    }

    public String getName() {
        return name;
    }

    public static int getHighscore() {
        return Hero.highscore;
    }

    public static int getCherries() {
        return Hero.cherries;
    }

    public static void setCherries(int cherries) {
        Hero.cherries = cherries;
    }

    public static boolean isFlip() {
        return Hero.flip;
    }

    public static void setFlip(boolean flip) {
        Hero.flip = flip;
    }

    public boolean isHeroMoving() {
        return isHeroMoving;
    }

    public void setHeroMoving(boolean heroMoving) {
        isHeroMoving = heroMoving;
    }

    public static void addCherries() {
        Hero.cherries++;
    }

    public void subCherries(int n) {
        cherries -= n;
    }

    public static void setHighscore(int highscore) {
        Hero.highscore = highscore;
    }

    private static AnimationTimer collisionTimer;
    private static TranslateTransition walk;

    public static AnimationTimer getCollisionTimer() {
        return Hero.collisionTimer;
    }

    public static TranslateTransition getWalk() {
        return Hero.walk;
    }

    public static void setWalk(TranslateTransition walk) {
        Hero.walk = walk;
    }

    private static TranslateTransition fall;

    public static TranslateTransition getFall() {
        return Hero.fall;
    }

    Cherries c1 = new Cherries();


    public void moveHero() {
        double length = Controller.getController().calculateLength();
        ImageView hero = (ImageView) NewGame.scene.lookup("#hero");
        Rectangle pillar4 = (Rectangle) NewGame.scene.lookup("#firstPillar");
        Rectangle pillar5 = (Rectangle) NewGame.scene.lookup("#secondPillar");
        ImageView cherry = (ImageView) NewGame.scene.lookup("#cherry");
        walk = new TranslateTransition();
        walk.setNode(hero);
        walk.setByX(length + 32);
        walk.setDuration(Duration.millis(1200));
        setHeroMoving(true);
        collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isHeroMoving) {
                    try {
                        c1.checkCollisionWithCherry(hero, cherry);
                        checkCollision(hero, pillar5);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        collisionTimer.start();
        setWalk(walk);

        walk.play();
        walk.setOnFinished(e -> {
            Cherries.setCherryCounted(false);
            setHeroMoving(false);
            collisionTimer.stop();

            if (length > Pillar.getPillarArrayList().get(1).getDistanceFromPrevious() && length < Pillar.getPillarArrayList().get(1).getDistanceFromPrevious() + pillar5.getWidth()) {
                System.out.println("Inside if");
                Controller.p1.shiftPillar();
                Controller.setHeroReachedonNextPillar(false);
                RunningGame currentGame = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
                try {
                    currentGame.increaseScore();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                System.out.println("Inside else");
                ArrayList<Pillar> a = Pillar.getPillarArrayList();
                System.out.println("pillar1 : " +
                        "width = " + pillar4.getWidth() +
                        " getX = " + pillar4.getX() +
                        " getLayoutX = " + pillar4.getLayoutX());
                System.out.println("========================");
                System.out.println("pillar2 : " +
                        "width = " + pillar5.getWidth() +
                        " getX = " + pillar5.getX() +
                        " getLayoutX = " + pillar5.getLayoutX());
                for (int i = 0; i < Pillar.getPillarArrayList().size(); i++) {
                    System.out.println("Distance form previous : " + a.get(i).getDistanceFromPrevious());
                    System.out.println("Width : " + a.get(i).getRectangle().getWidth());

                }
                Controller.setHeroReachedonNextPillar(false);
                try {
                    Pillar.setTransitionCompleted(false);
                    fallHero(hero);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

            public void fallHero(Node hero) throws IOException {
                collisionTimer.stop();
                walk.stop();
                fall = new TranslateTransition();
                fall.setNode(hero);
                fall.setDuration(Duration.millis(900));
                fall.setByY(1000);
                fall.play();
                fall.setOnFinished(e -> {
                    try {
                        RunningGame currentGame = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
                        Controller.getController().gameOverpage((Stage) fall.getNode().getScene().getWindow(), currentGame);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            public void checkCollision(ImageView imageView, Rectangle rectangle) throws IOException {

                Bounds boundsImageView = imageView.getBoundsInParent();
                Bounds boundsRectangle = rectangle.getBoundsInParent();


                if (boundsImageView.intersects(boundsRectangle) && Hero.isFlip()) {
                    fallHero((Node) imageView);

                }
            }

    }
