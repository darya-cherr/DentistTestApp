package com.example.dentisttestapp;

public class Item {
    private int points;
    private String description;

    public Item(int points, String description) {
        this.points = points;
        this.description = description;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
