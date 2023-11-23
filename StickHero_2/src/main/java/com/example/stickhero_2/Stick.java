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
import javafx.util.Duration;

public class Stick {
    @FXML
    Line stick;
    @FXML
    Button button;
    @FXML
    AnchorPane anchorPane;
    Timeline timelineStick;

    public Stick() {
        this.length = 0;
    }

    private int length;

    public int getLength() {
        return length;
    }
    boolean isMousePressed = false;


    public void increaseLength() {
//        button.setOnMousePressed(mouseEvent -> {
//            System.out.println("Mouse pressed");
//            // Create a KeyValue for the endY property of the stick
//            KeyValue keyValue = new KeyValue(stick.endYProperty(), stick.getEndY() + 100);
//
//            // Create a KeyFrame with a duration and the KeyValue
//            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
//
//            // Create a Timeline and add the KeyFrame
//            Timeline timeline = new Timeline(keyFrame);
//
//            // Set the cycle count (how many times the timeline should repeat)
//            timeline.setCycleCount(1);
//
//            // Play the Timeline
//            timeline.play();
////            stick.endYProperty().bind(timeline.currentTimeProperty());
//        });
        button.setOnMouseReleased(mouseEvent -> {
            System.out.println("Mouse Released");
            timelineStick.stop();
        });
//        button.setOnMousePressed(event -> {
//            System.out.println("Mouse pressed");
//
//            // Create a ScaleTransition for the stick
//            ScaleTransition transition = new ScaleTransition(Duration.seconds(1), stick);
//
//            // Set the Y-axis scale to increase the length
//            transition.setToY(stick.getScaleY() + 1);
//
//            // Play the transition
//            transition.play();
//        });

        button.setOnMousePressed(event -> {
            System.out.println("Mouse pressed");

            // Create a Timeline for continuous scaling
            timelineStick = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(stick.scaleYProperty(), stick.getScaleY())),
                    new KeyFrame(Duration.millis(20), ev -> {
                        // Update the Y-axis scale to increase the length
                        stick.setScaleY(stick.getScaleY() + 2);
                    })
            );

            // Set the cycle count to indefinite
            timelineStick.setCycleCount(Timeline.INDEFINITE);

            // Play the Timeline
            timelineStick.play();
        });
//        button.setOnMousePressed(mouseEvent -> {
//            System.out.println("Mouse pressed");
//
//            // Create a TranslateTransition for the stick
//            TranslateTransition transition = new TranslateTransition(Duration.seconds(1), stick);
//
//            // Set the change in the Y coordinate to increase the length
//            transition.setByX(stick.getEndX() - 10);
//
//            // Play the transition
//            transition.play();
//        });

//        stick.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                    timeline.play();
//                }
//            });
//        button.setOnMousePressed(event -> {
//
//        });
//        Timeline timeline1 = new Timeline(new KeyFrame(Duration.INDEFINITE));
//        stick.setOnMousePressed(e -> {
//
//        });

//        this.length++;

    }
    public void addPillar(ActionEvent e){
        Pillar p1 = new Pillar();
        p1.generateNextPillar(this);
    }

    public void horizontalStick(){

    }
}
