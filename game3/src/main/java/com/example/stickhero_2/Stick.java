package com.example.stickhero_2;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Stick {
//    @FXML
//    Line stick;
//    @FXML
//    Button button;
//    @FXML
//    AnchorPane anchorPane;
//    Controller c1 = new Controller();
    Timeline timelineStick;
//    private Controller controller;

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



    public void increaseLength(Button button, Line stick) {
        button.setOnMouseReleased(mouseEvent -> {
            System.out.println("Mouse Released");
            timelineStick.stop();
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

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(rotation.angleProperty(), 0)),
                new KeyFrame(Duration.millis(500), new javafx.animation.KeyValue(rotation.angleProperty(), rotationAngle))
        );

        timeline.play();
        timeline.setOnFinished(e-> h.moveHero());

    }
}