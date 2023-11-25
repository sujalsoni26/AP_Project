package com.example.stickhero_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private Line stick;
    @FXML
    private Button button;
    private MethodHandles scene;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Rectangle firstPillar;
    @FXML
    private ImageView hero;

    private Parent root;



    private static boolean heroReachedonNextPillar = false;

    public static boolean isHeroReachedonNextPillar() {
        return heroReachedonNextPillar;
    }

    public static void setHeroReachedonNextPillar(boolean heroReachedonNextPillar) {
        Controller.heroReachedonNextPillar = heroReachedonNextPillar;
    }

    static Pillar p1 = new Pillar();
    static Stick s1 = new Stick();

    private static Controller controller = new Controller();

    public static Controller getController() {
        return controller;
    }

    public ImageView getHero() {
        return hero;
    }

    public Line getStick() {
        return stick;
    }

    public Button getButton() {
        return button;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void addPillar(AnchorPane anchorPane){
        if (anchorPane == null) {
            System.out.println("It is nullllll");
        }
        p1.generateNextPillar(anchorPane);

    }

    public void openPausepage(MouseEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("pause-page.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        javafx.scene.Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public double calculateLength(){
        Line stik = (Line) NewGame.scene.lookup("#stick");
        double length = Math.abs(stik.getEndY() - stik.getStartY());
        System.out.println(length);
        return length;

    }
    public void increaseStickLength(){
        Line stik = (Line) NewGame.scene.lookup("#stick");
        s1.increaseLength(button, stik);
    }

}
