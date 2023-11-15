package com.example.project_deadline1;

public class NewGame implements Game {
    @Override
    public void saveGame() {

    }

    @Override
    public void createHero() {

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
}
