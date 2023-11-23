package com.example.stickhero_2;

import java.util.ArrayList;

public class SavedGame implements Game{
    @Override
    public void saveGame() {

    }

    @Override
    public void createHero() {

    }
    private static int[] savedgames = {0, 0};

    public static int[] getSavedgames() {
        return savedgames;
    }

    public void loadGame(){

    }
    public void deleteGame(){

    }
}
