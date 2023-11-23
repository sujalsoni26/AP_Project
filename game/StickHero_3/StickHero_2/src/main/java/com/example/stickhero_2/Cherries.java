package com.example.stickhero_2;

public class Cherries {
    private boolean aboveStick;
    private int distanceFromPillar;

    public Cherries(boolean aboveStick, int distanceFromPillar) {
        this.aboveStick = aboveStick;
        this.distanceFromPillar = distanceFromPillar;
    }

    public boolean isAboveStick() {
        return aboveStick;
    }

    public void setAboveStick(boolean aboveStick) {
        this.aboveStick = aboveStick;
    }

    public int getDistanceFromPillar() {
        return distanceFromPillar;
    }

    public void setDistanceFromPillar(int distanceFromPillar) {
        this.distanceFromPillar = distanceFromPillar;
    }
}
