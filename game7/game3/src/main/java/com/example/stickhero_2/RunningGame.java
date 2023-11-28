package com.example.stickhero_2;

import java.io.IOException;
import java.util.ArrayList;

//every new game will also be saved in SavedGame in index 0.
public class RunningGame extends SavedGame{


    private Hero hero;
    private NewGame game;
    private static ArrayList<Game> runningGameArraylist = new ArrayList<>();

    public RunningGame(NewGame game, Hero hero) {
        this.hero = hero;
        this.game = game;
        runningGameArraylist.add(this);
    }

    public static ArrayList<Game> getRunningGameArraylist() {
        return runningGameArraylist;
    }

    public void increaseScore() throws IOException {
        this.game.setScore(this.game.getScore()+1);
        Controller.getController().updateScoreText(this);

    }

    public NewGame getGame() {
        return game;
    }


}
