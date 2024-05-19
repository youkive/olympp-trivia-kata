package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int NB_QUESTIONS = 50;
    ArrayList<Player> players = new ArrayList<>();
    Map<QuestionType, List<Question>> questionsSet = new HashMap<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        questionsSet.put(QuestionType.POP, new ArrayList<>());
        questionsSet.put(QuestionType.SCIENCE, new ArrayList<>());
        questionsSet.put(QuestionType.SPORTS, new ArrayList<>());
        questionsSet.put(QuestionType.ROCK, new ArrayList<>());
        for (int i = 0; i < NB_QUESTIONS; i++) {
            questionsSet.get(QuestionType.POP).add(new Question("Pop Question " + i));
            questionsSet.get(QuestionType.SCIENCE).add(new Question("Science Question " + i));
            questionsSet.get(QuestionType.SPORTS).add(new Question("Sports Question " + i));
            questionsSet.get(QuestionType.ROCK).add(new Question("Rock Question " + i));
        }
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll(int roll, PrintStream out) {
        out.println(players.get(currentPlayer).toName() + " is the current player");
        out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                out.println(players.get(currentPlayer).toName() + " is getting out of the penalty box");
                players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() + roll);
                if (players.get(currentPlayer).getPlace() > 11)
                    players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() - 12);

                out.println(players.get(currentPlayer)
                        + "'s new location is "
                        + players.get(currentPlayer).getPlace());
                out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                out.println(players.get(currentPlayer).toName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() + roll);
            if (players.get(currentPlayer).getPlace() > 11)
                players.get(currentPlayer).setPlace(players.get(currentPlayer).getPlace() - 12);

            out.println(players.get(currentPlayer).toName()
                    + "'s new location is "
                    + players.get(currentPlayer).getPlace());
            out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == QuestionType.POP.getLabel())
            System.out.println(questionsSet.get(QuestionType.POP).remove(0));
        if (currentCategory() == QuestionType.SCIENCE.getLabel())
            System.out.println(questionsSet.get(QuestionType.SCIENCE).remove(0));
        if (currentCategory() == QuestionType.SPORTS.getLabel())
            System.out.println(questionsSet.get(QuestionType.SPORTS).remove(0));
        if (currentCategory() == QuestionType.ROCK.getLabel())
            System.out.println(questionsSet.get(QuestionType.ROCK).remove(0));
    }


    private String currentCategory() {
        if (players.get(currentPlayer).getPlace() == 0) return QuestionType.POP.getLabel();
        if (players.get(currentPlayer).getPlace() == 4) return QuestionType.POP.getLabel();
        if (players.get(currentPlayer).getPlace() == 8) return QuestionType.POP.getLabel();
        if (players.get(currentPlayer).getPlace() == 1) return QuestionType.SCIENCE.getLabel();
        if (players.get(currentPlayer).getPlace() == 5) return QuestionType.SCIENCE.getLabel();
        if (players.get(currentPlayer).getPlace() == 9) return QuestionType.SCIENCE.getLabel();
        if (players.get(currentPlayer).getPlace() == 2) return QuestionType.SPORTS.getLabel();
        if (players.get(currentPlayer).getPlace() == 6) return QuestionType.SPORTS.getLabel();
        if (players.get(currentPlayer).getPlace() == 10) return QuestionType.SPORTS.getLabel();
        return QuestionType.ROCK.getLabel();
    }

    public boolean wasCorrectlyAnswered() {
        if (players.get(currentPlayer).isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                players.get(currentPlayer).setPurses(players.get(currentPlayer).getPurses() + 1);
                System.out.println(players.get(currentPlayer).toName()
                        + " now has "
                        + players.get(currentPlayer).getPurses()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size())
                    currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size())
                    currentPlayer = 0;
                return true;
            }


        } else {
            System.out.println("Answer was corrent!!!!");
            players.get(currentPlayer).setPurses(players.get(currentPlayer).getPurses() + 1);
            System.out.println(players.get(currentPlayer).toName()
                    + " now has "
                    + players.get(currentPlayer).getPurses()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size())
                currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer).toName() + " was sent to the penalty box");
        players.get(currentPlayer).setInPenaltyBox(true);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getPurses() == 6);
    }
}
