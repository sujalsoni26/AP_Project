package com.example.project_deadline1;

public class Pillar {
    private int width;
    private int distanceFromPrevious;
    private static int totalPillarCrossed;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDistanceFromPrevious() {
        return distanceFromPrevious;
    }

    public void setDistanceFromPrevious(int distanceFromPrevious) {
        this.distanceFromPrevious = distanceFromPrevious;
    }

    public static int getTotalPillarCrossed() {
        return totalPillarCrossed;
    }

    public static void setTotalPillarCrossed(int totalPillarCrossed) {
        Pillar.totalPillarCrossed = totalPillarCrossed;
    }

    public void generateNextPillar(){

    }
}
