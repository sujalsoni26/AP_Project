package com.example.stickhero_2;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Stick {
    Timeline timelineStick;

    private boolean stickVertical;

    Hero h = new Hero();


    public boolean isStickVertical() {
        return stickVertical;
    }

    public void setStickVertical(boolean stickVertical) {
        this.stickVertical = stickVertical;
    }

    public Stick() {
//        this.controller = controller;
        this.length = 0;
        this.stickVertical = true;
    }

    private int length;

    public int getLength() {
        return length;
    }
    boolean isMousePressed = false;

    public void increaseLength(Button button, Line stick) {
        button.setOnMouseReleased(mouseEvent -> {
            System.out.println("Mouse Released");
            timelineStick.stop();
            if(isStickVertical()) {
                setStickVertical(false);
                horizontalStick(stick);
            }
        });

       button.setOnMousePressed(event -> {


            System.out.println("Mouse pressed");
            System.out.println("stickVertical before condition check: " + isStickVertical());

            if(isStickVertical()) {
                timelineStick = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(stick.scaleYProperty(), stick.getScaleY())),
                        new KeyFrame(Duration.millis(20), ev -> {
                            stick.setEndY(stick.getEndY() - 4);
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

        double halfHeight = stick.getBoundsInLocal().getHeight() / 2;

        // Shift the stick's y-axis downward by half its height
        stick.setLayoutY(stick.getLayoutY() + halfHeight);

        // Shift the stick forward by half its height
        double translationX = halfHeight; // Adjust this value as needed
        TranslateTransition moveForward = new TranslateTransition(Duration.millis(500), stick);
        moveForward.setByX(translationX);

        // Create a rotate transition
        RotateTransition rotate = new RotateTransition(Duration.millis(500), stick);
        rotate.setByAngle(90); // Rotate by 90 degrees


        // Play both animations in parallel
        ParallelTransition parallelTransition = new ParallelTransition(moveForward, rotate);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();

        parallelTransition.setOnFinished(e -> {
            h.moveHero();
        });



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


        AnchorPane anchorPane = (AnchorPane) NewGame.scene.lookup("#anchorPane");
        stick.setId("stick");
        anchorPane.getChildren().add(stick);
        return stick;
    }
}
