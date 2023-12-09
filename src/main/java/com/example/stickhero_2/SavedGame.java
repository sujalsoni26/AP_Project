package com.example.stickhero_2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SavedGame implements Game, Serializable {
    private Parent root;

    public SavedGame() {
        this.highScore = Hero.getHighscore();
        this.cherries = Hero.getCherries();
    }

    private int highScore;
    private int cherries;

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }

    public SavedGame(String s){

    }
    public void saveGame() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("saveGame.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        FileOutputStream fos1 = new FileOutputStream(new File("saveScore.txt"));
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

        SavedGame saveScore = new SavedGame();

        oos1.writeObject(saveScore);
        oos1.close();fos1.close();


        RunningGame game = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
        oos.writeObject(game);

        oos.close();fos.close();
        int score = game.getGame().getScore();
        savedgames.clear();
//        savedgames[1] = score;
        savedgames.add(game);
        RunningGame.getRunningGameArraylist().clear();
        root = FXMLLoader.load(getClass().getResource("playpage.fxml"));
        ImageView hero = (ImageView) NewGame.scene.lookup("#hero");
        Stage stage = (Stage) (hero.getScene().getWindow());
        Controller.getController().cleanup();

        Controller.setScene( new Scene(root));
        stage.setScene(Controller.getScene());
//        HelloApplication.animations(hero.getScene());
        stage.show();
    }

    @Override
    public Hero createHero() {
        return new Hero("Stickman");
    }
//    private static int[] savedgames = {0, 0};

    private static ArrayList<Game> savedgames = new ArrayList<>();

    public static ArrayList<Game> getSavedgames() {
        return savedgames;
    }
    //    public static int[] getSavedgames() {
//        return savedgames;
//    }


}
