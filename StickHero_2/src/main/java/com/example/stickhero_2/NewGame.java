package com.example.stickhero_2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class NewGame implements Game {
    static Parent root;
    Timeline timelineNewGame = new Timeline();
    Scene scene;


    @Override
    public void saveGame() {
        SavedGame.getSavedgames()[0] = this.score;
    }

    @Override
    public Hero createHero() {
        return new Hero("Stickman");
    }

    public NewGame() {
        this.score = 0;
        this.currentlyActive = true;
        this.hasEnded = false;
        this.savedIndex = 0;
        this.saveGame();
    }

    public Game createNewGame(){
        Game newGame = new NewGame();
        return newGame;
    }
    private int score;
    private boolean currentlyActive;
    private boolean hasEnded;
    private int savedIndex;

    public int getSavedIndex() {
        return savedIndex;
    }

    public void setSavedIndex(int savedIndex) {
        this.savedIndex = savedIndex;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public boolean isCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    Ellipse newGameEllipse;
    Text newGameText;

    public void changeScreenToPlayPage(MouseEvent e) throws IOException {
        //root = FXMLLoader.load(getClass().getResource("running-game-scene.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("running-game-scene.fxml"));
//        Parent root = loader.load();
        //YourControllerClass controller = loader.getController();
//        Controller controller = root.getController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("running-game-scene.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        Controller controller = loader.getController();

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        AnchorPane anchorPane = (AnchorPane) scene.lookup("#anchorPane");
        stage.setScene(scene);


        Game newGame = createNewGame();
        Game runningGame = new RunningGame(newGame, createHero());
        Controller.setHeroReachedonNextPillar(true);
        timelineNewGame = new Timeline(
                new KeyFrame(Duration.seconds(2), event -> {
                    if (Controller.isHeroReachedonNextPillar()) {
//                    Controller.getController().addPillar(anchorPane);
                        controller.addPillar(anchorPane);
                        Controller.setHeroReachedonNextPillar(false);
                    }
                })
        );
        timelineNewGame.setCycleCount(Timeline.INDEFINITE);
        timelineNewGame.play();
        stage.show();
    }
}
