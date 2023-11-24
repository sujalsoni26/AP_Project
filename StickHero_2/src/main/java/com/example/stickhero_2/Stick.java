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
//    @FXML
//    Line stick;
//    @FXML
//    Button button;
//    @FXML
//    AnchorPane anchorPane;
//    Controller c1 = new Controller();
    Timeline timelineStick;
//    private Controller controller;

    public Stick() {
//        this.controller = controller;
        this.length = 0;
    }

    private int length;

    public int getLength() {
        return length;
    }
    boolean isMousePressed = false;


    public void increaseLength(Button button, Line stickk) {

        if (button == null){
            System.out.println("Button is null");
        }else {
            button.setOnMousePressed(event -> {
                System.out.println("Mouse pressed");
                isMousePressed = false;
                timelineStick = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(stickk.scaleYProperty(), stickk.getScaleY())),
                        new KeyFrame(Duration.millis(20), ev -> {
                            stickk.setEndY((stickk.getEndY() - 2));
                        })
                );
                timelineStick.setCycleCount(Timeline.INDEFINITE);
                timelineStick.play();
            });
            button.setOnMouseReleased(mouseEvent -> {
                System.out.println("Mouse Released");
                isMousePressed = false;
                timelineStick.stop();
            });
        }
    }

    public void horizontalStick(){
        double halfHeight = Controller.getController().getStick().getBoundsInLocal().getHeight() / 2;

        // Shift the stick's y-axis downward by half its height
        Controller.getController().getStick().setLayoutY(Controller.getController().getStick().getLayoutY() + halfHeight);

        // Shift the stick forward by half its height
        double translationX = halfHeight; // Adjust this value as needed
        TranslateTransition moveForward = new TranslateTransition(Duration.millis(500), Controller.getController().getStick());
        moveForward.setByX(translationX);

        // Create a rotate transition
        RotateTransition rotate = new RotateTransition(Duration.millis(500), Controller.getController().getStick());
        rotate.setByAngle(90); // Rotate by 90 degrees


        // Play both animations in parallel
        ParallelTransition parallelTransition = new ParallelTransition(moveForward, rotate);
        parallelTransition.setCycleCount(1);
        parallelTransition.play();

    }
}
