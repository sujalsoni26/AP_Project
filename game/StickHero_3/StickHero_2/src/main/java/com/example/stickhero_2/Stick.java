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
    private Controller controller;


    public Stick(Controller controller) {
        this.controller = controller;
        this.length = 0;
    }

    private int length;

    public int getLength() {
        return length;
    }
    boolean isMousePressed = false;


    public void increaseLength() {
        controller.getButton().setOnMouseReleased(mouseEvent -> {
            System.out.println("Mouse Released");
            timelineStick.stop();
            horizontalStick();
        });
        controller.getButton().setOnMousePressed(event -> {
            System.out.println("Mouse pressed");
            timelineStick = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(controller.getStick().scaleYProperty(), controller.getStick().getScaleY())),
                    new KeyFrame(Duration.millis(20), ev -> {
                        controller.getStick().setScaleY(controller.getStick().getScaleY() + 2);
                    })
            );
            timelineStick.setCycleCount(Timeline.INDEFINITE);
            timelineStick.play();
        });
    }


    private void horizontalStick() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), controller.getStick());
        rotateTransition.setToAngle(90); // Rotate to 90 degrees (horizontal)
        rotateTransition.play();
    }


}
