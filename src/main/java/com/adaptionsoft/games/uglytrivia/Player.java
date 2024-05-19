package com.adaptionsoft.games.uglytrivia;

public class Player {
    public String name;

    private Integer place;

    private Integer purses;

    private Boolean inPenaltyBox;

    public Boolean isGettingOutOfPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.place=0;
        this.purses = 0;
        this.inPenaltyBox = false;
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

    public Integer getPurses() {
        return purses;
    }

    public void setPurses(Integer purses) {
        this.purses = purses;
    }

    public Boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setInPenaltyBox(Boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    boolean isWinner() {
        return !(getPurses() == 6);
    }
}
