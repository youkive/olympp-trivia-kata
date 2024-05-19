package com.adaptionsoft.games.uglytrivia;

public class Player {
    public String name;

    private Integer place;

    private Integer purses;

    private Boolean inPenaltyBox;

    private Boolean isGettingOutOfPenaltyBox;

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

    public Boolean getGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(Boolean gettingOutOfPenaltyBox) {
        isGettingOutOfPenaltyBox = gettingOutOfPenaltyBox;
    }

    void move(int roll) {
        this.place = place + roll;
        if (this.place > 11)
           this.place = this.place - 12;
    }
}
