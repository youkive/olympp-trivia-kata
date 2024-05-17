package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String name;

    public Player(String name) {

        this.name = name;
    }

    public String toName() {
        return name;
    }

    public Integer getPlace() {
        return 0;
    }
}
