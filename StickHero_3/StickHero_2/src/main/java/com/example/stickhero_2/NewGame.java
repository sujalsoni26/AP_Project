package com.example.stickhero_2;

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

import java.io.IOException;

public class NewGame implements Game {
    static Parent root;

    @Override
    public void saveGame() {
        SavedGame.getSavedgames()[0] = this.score;
    }

    @Override
    public void createHero() {
        Hero newHero = new Hero("Stickman");
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
        root = FXMLLoader.load(getClass().getResource("running-game-scene.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Game newGame = createNewGame();

        stage.show();
    }
}
