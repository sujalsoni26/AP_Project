package com.example.stickhero_2;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

//every new game will also be saved in SavedGame in index 0.
public class RunningGame extends SavedGame implements Serializable {


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

    public static class RunningGameIterator implements Iterator<Game> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < runningGameArraylist.size();
        }

        @Override
        public Game next() {
            if (this.hasNext()) {
                return runningGameArraylist.get(currentIndex++);
            }
            throw new IndexOutOfBoundsException("No more elements in the list.");
        }
    }

    // Method to get the iterator for the runningGameArraylist
    public static Iterator<Game> getRunningGameIterator() {
        return new RunningGameIterator();
    }


}
