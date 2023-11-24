package com.example.stickhero_2;

//every new game will also be saved in SavedGame in index 0.
public class RunningGame extends SavedGame{

    private Scene scene;
    private Hero hero;
    private Game game;

    public RunningGame(Game game, Hero hero) {
        this.hero = hero;
        this.game = game;
    }

    public void pause(){

    }
    public void retry(){

    }
    public void revive(){

    }
    public void resume(){

    }
    public void save(){

    }
    public void home(){

    }
}
