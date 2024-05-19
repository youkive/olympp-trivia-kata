package com.adaptionsoft.games.uglytrivia;

public enum QuestionType {

    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

    private String label;


    QuestionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
