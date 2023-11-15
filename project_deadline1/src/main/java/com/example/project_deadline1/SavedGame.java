package com.example.project_deadline1;

import java.util.ArrayList;

public class SavedGame implements Game{
    @Override
    public void saveGame() {

    }

    @Override
    public void createHero() {

    }
    private static ArrayList<Integer> savedgames = new ArrayList<>();

    public static ArrayList<Integer> getSavedgames() {
        return savedgames;
    }
    public void loadGame(){

    }
    public void deleteGame(){

    }
}
