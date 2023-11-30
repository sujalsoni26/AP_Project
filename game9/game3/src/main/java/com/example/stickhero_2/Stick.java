package com.example.stickhero_2;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Stick {

    Timeline timelineStick;

    private static boolean stickVertical;

    Hero h = new Hero();


    public static boolean isStickVertical() {
        return stickVertical;
    }

    public static void setStickVertical(boolean stickVertical) {
        Stick.stickVertical = stickVertical;
    }

    public Stick() {
        this.length = 0;
        this.stickVertical = true;
    }

    private int length;

    public int getLength() {
        return length;
    }
    boolean isMousePressed = false;

    private static Timeline timelineHorizontal;

    public static Timeline getTimelineHorizontal() {
        return Stick.timelineHorizontal;
    }

    public Line createNewStick(){
        Line stick = new Line();
        stick.setStartX(141);
        stick.setEndX(141);
        stick.setStartY(248);
        stick.setEndY(248);
        stick.setLayoutY(42);
        stick.setLayoutX(20);
        stick.setStrokeWidth(3);
        setStickVertical(true);

        AnchorPane anchorPane = (AnchorPane) NewGame.scene.lookup("#anchorPane");
        stick.setId("stick");
        anchorPane.getChildren().add(stick);
        return stick;
    }


    private int flag1= 0;
    private int flag2= 5;
    private boolean f1small = true;
    private boolean f2small = false;
    public void increaseLength(Button button, Line stick) {
        ImageView hero = (ImageView) NewGame.scene.lookup("#hero");
        button.setOnMouseReleased(mouseEvent -> {
            System.out.println("Mouse Released");
            timelineStick.stop();
            hero.setImage(new Image("hero.png"));
            if(isStickVertical()) {
                setStickVertical(false);
                Line stik = (Line) NewGame.scene.lookup("#stick");
                horizontalStick(stik);
            }
        });

       button.setOnMousePressed(event -> {
            System.out.println("Mouse pressed");
            System.out.println("stickVertical before condition check: " + isStickVertical());
            Line stik = (Line) NewGame.scene.lookup("#stick");

            if(isStickVertical()) {
                timelineStick = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(stik.scaleYProperty(), stik.getScaleY())),
                        new KeyFrame(Duration.millis(20), ev -> {
                            stik.setEndY(stik.getEndY() - 4);
                            if (flag1 > flag2){
                                f2small = true;
                                f1small = false;
                                hero.setImage(new Image("hero.png"));
                                flag2++;
                            }else if (flag1 < flag2){
                                hero.setImage(new Image("hero1.png"));
                                flag1++;
                            } else if (flag2 == flag1 && f1small && !f2small) {
                                flag1 += 5;
                                f1small = false;
                                f2small = true;
                            } else if (flag2 == flag1 && f2small && !f1small) {
                                flag2 += 5;
                                f2small = false;
                                f1small = true;
                            }
                        })
                );
                timelineStick.setCycleCount(Timeline.INDEFINITE);
                timelineStick.play();
            }
        });

    }

    private void horizontalStick(Line stick) {
        setStickVertical(false);
        System.out.println("vertical on mouse release "+ isStickVertical());
        Line stik = (Line) NewGame.scene.lookup("#stick");


        double pivotX = stik.getStartX(); // X coordinate of the bottom end
        double pivotY = stik.getStartY();

        double rotationAngle = 90 ;

        Rotate rotation = new Rotate(0,pivotX,pivotY);
        stick.getTransforms().add(rotation);

        timelineHorizontal = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.millis(500), new KeyValue(rotation.angleProperty(), rotationAngle))
        );

        timelineHorizontal.play();
        timelineHorizontal.setOnFinished(e-> h.moveHero());

    }
}
