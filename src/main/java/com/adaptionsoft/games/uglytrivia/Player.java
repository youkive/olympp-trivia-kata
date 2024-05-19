package com.adaptionsoft.games.uglytrivia;

public class Player {
    public String name;

    public Integer place;

    public Player(String name) {

        this.name = name;
        this.place=0;
    }

    public String toName() {
        return name;
    }

    public Integer getPlace() {
        return place;
    }
}
