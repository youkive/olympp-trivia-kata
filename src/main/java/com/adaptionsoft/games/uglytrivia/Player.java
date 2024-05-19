package com.adaptionsoft.games.uglytrivia;

public class Player {
    public String name;

    private Integer place;

    public Integer purses;

    public Player(String name) {

        this.name = name;
        this.place=0;
        this.purses = 0;
    }

    public String toName() {
        return name;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }
}
