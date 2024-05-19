package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;

public class Game {
    public static final int NB_QUESTIONS = 50;
    ArrayList<Player> players = new ArrayList<>();
    private final QuestionSet questionSet;

    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        questionSet = new QuestionSet(NB_QUESTIONS);
    }

    public boolean addNewPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        players.add(newPlayer);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        if (currentPlayer == null)
            currentPlayer = newPlayer;
        return true;
    }

    public void roll(int roll, PrintStream out) {
        out.println(currentPlayer.toName() + " is the current player");
        out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                out.println(currentPlayer.toName() + " is getting out of the penalty box");
                currentPlayer.setPlace(currentPlayer.getPlace() + roll);
                if (currentPlayer.getPlace() > 11)
                    currentPlayer.setPlace(currentPlayer.getPlace() - 12);

                out.println(currentPlayer
                        + "'s new location is "
                        + currentPlayer.getPlace());
                out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                out.println(currentPlayer.toName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            currentPlayer.setPlace(currentPlayer.getPlace() + roll);
            if (currentPlayer.getPlace() > 11)
                currentPlayer.setPlace(currentPlayer.getPlace() - 12);

            out.println(currentPlayer.toName()
                    + "'s new location is "
                    + currentPlayer.getPlace());
            out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == QuestionType.POP.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.POP));
        if (currentCategory() == QuestionType.SCIENCE.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.SCIENCE));
        if (currentCategory() == QuestionType.SPORTS.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.SPORTS));
        if (currentCategory() == QuestionType.ROCK.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.ROCK));
    }


    private String currentCategory() {
        if (currentPlayer.getPlace() == 0) return QuestionType.POP.getLabel();
        if (currentPlayer.getPlace() == 4) return QuestionType.POP.getLabel();
        if (currentPlayer.getPlace() == 8) return QuestionType.POP.getLabel();
        if (currentPlayer.getPlace() == 1) return QuestionType.SCIENCE.getLabel();
        if (currentPlayer.getPlace() == 5) return QuestionType.SCIENCE.getLabel();
        if (currentPlayer.getPlace() == 9) return QuestionType.SCIENCE.getLabel();
        if (currentPlayer.getPlace() == 2) return QuestionType.SPORTS.getLabel();
        if (currentPlayer.getPlace() == 6) return QuestionType.SPORTS.getLabel();
        if (currentPlayer.getPlace() == 10) return QuestionType.SPORTS.getLabel();
        return QuestionType.ROCK.getLabel();
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.setPurses(currentPlayer.getPurses() + 1);
                System.out.println(currentPlayer.toName()
                        + " now has "
                        + currentPlayer.getPurses()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer = getNextPlayer();

                return winner;
            } else {
                currentPlayer = getNextPlayer();
                return true;
            }


        } else {
            System.out.println("Answer was corrent!!!!");
            currentPlayer.setPurses(currentPlayer.getPurses() + 1);
            System.out.println(currentPlayer.toName()
                    + " now has "
                    + currentPlayer.getPurses()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer = getNextPlayer();

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.toName() + " was sent to the penalty box");
        currentPlayer.setInPenaltyBox(true);

        currentPlayer = getNextPlayer();
        return true;
    }

    public Player getNextPlayer() {
        int indexOf = players.indexOf(currentPlayer);
        if (indexOf != (players.size() - 1))
            return players.get(indexOf + 1);
        else
            return players.get(0);
    }

    private boolean didPlayerWin() {
        return !(currentPlayer.getPurses() == 6);
    }
}
