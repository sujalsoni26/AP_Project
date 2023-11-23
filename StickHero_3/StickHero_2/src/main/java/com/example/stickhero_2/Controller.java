package com.example.stickhero_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Controller {
    @FXML
    private Line stick;
    @FXML
    private Button button;
    @FXML
    private AnchorPane anchorPane;

    public Line getStick() {
        return stick;
    }

    public Button getButton() {
        return button;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void addPillar(ActionEvent e){
        Pillar p1 = new Pillar();
        p1.generateNextPillar(this);
    }

    public void increaseStickLength(){
        Stick s1 = new Stick(this);
        s1.increaseLength();
    }

}
