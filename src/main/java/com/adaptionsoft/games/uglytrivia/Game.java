package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;

public class Game {
    public static final int NB_QUESTIONS = 50;
    ArrayList<Player> players = new ArrayList<>();
    private final QuestionSet questionSet;

    Player currentPlayer;

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
                currentPlayer.setGettingOutOfPenaltyBox(true);
                out.println(currentPlayer.toName() + " is getting out of the penalty box");
               play(roll, out);
            } else {
                out.println(currentPlayer.toName() + " is not getting out of the penalty box");
                currentPlayer.setGettingOutOfPenaltyBox(false);
            }

        } else {
            play(roll, out);
        }

    }

    private void play(int roll, PrintStream out) {
        currentPlayer.move(roll);
        out.println(currentPlayer.toName() +"'s new location is " + currentPlayer.getPlace());
        out.println("The category is " + currentPlayer.getCategoryAccordingPlace().getLabel());
        askQuestion();
    }

    private void askQuestion() {
        if (currentPlayer.getCategoryAccordingPlace().getLabel() == QuestionType.POP.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.POP));
        if (currentPlayer.getCategoryAccordingPlace().getLabel() == QuestionType.SCIENCE.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.SCIENCE));
        if (currentPlayer.getCategoryAccordingPlace().getLabel() == QuestionType.SPORTS.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.SPORTS));
        if (currentPlayer.getCategoryAccordingPlace().getLabel() == QuestionType.ROCK.getLabel())
            System.out.println(questionSet.removeQuestionFor(QuestionType.ROCK));
    }


    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (currentPlayer.getGettingOutOfPenaltyBox()) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.setPurses(currentPlayer.getPurses() + 1);
                System.out.println(currentPlayer.toName()
                        + " now has "
                        + currentPlayer.getPurses()
                        + " Gold Coins.");

                boolean winner = currentPlayer.isWinner();
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

            boolean winner = currentPlayer.isWinner();
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
}
