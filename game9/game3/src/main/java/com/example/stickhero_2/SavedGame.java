package com.example.stickhero_2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SavedGame implements Game{
    private Parent root;

    public SavedGame() {
    }

    public void saveGame() throws IOException {
        RunningGame game = (RunningGame) RunningGame.getRunningGameArraylist().get(0);
//        int score = game.getGame().getScore();
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
