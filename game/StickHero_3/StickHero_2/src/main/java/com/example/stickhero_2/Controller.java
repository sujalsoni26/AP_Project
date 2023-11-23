package com.example.stickhero_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Line stick;
    @FXML
    private Button button;
    @FXML
    private AnchorPane anchorPane;
    private Parent root;

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

    public void horizontalStick(){

    }
    public void openPausepage(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pause-page.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
