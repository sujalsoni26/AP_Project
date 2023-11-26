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
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class NewGame implements Game {
    static Parent root;
    static Timeline timelineNewGame = new Timeline();
    static Scene scene;


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

    private boolean firstpillarflag = true;

    Ellipse newGameEllipse;
    Text newGameText;

    public void changeScreenToPlayPage(MouseEvent e) throws IOException {

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
//        Controller.setHeroReachedonNextPillar(true);

        Rectangle pill = (Rectangle) NewGame.scene.lookup("#firstPillar");
        Pillar p1 = new Pillar(pill, 0);
        Pillar.getPillarArrayList().add(p1);

        timelineNewGame = new Timeline(
                new KeyFrame(Duration.millis(5), event -> {
                    if ((Controller.isHeroReachedonNextPillar()) || firstpillarflag) {
                        firstpillarflag = false;
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
