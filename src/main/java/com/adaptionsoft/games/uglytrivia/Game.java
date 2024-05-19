package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<Player> players = new ArrayList<>();
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean add(String playerName) {


        players.add(new Player(playerName));
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll, PrintStream out) {
        out.println(players.get(currentPlayer).toName() + " is the current player");
        out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
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
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (players.get(currentPlayer).getPlace() == 0) return "Pop";
        if (players.get(currentPlayer).getPlace() == 4) return "Pop";
        if (players.get(currentPlayer).getPlace() == 8) return "Pop";
        if (players.get(currentPlayer).getPlace() == 1) return "Science";
        if (players.get(currentPlayer).getPlace() == 5) return "Science";
        if (players.get(currentPlayer).getPlace() == 9) return "Science";
        if (players.get(currentPlayer).getPlace() == 2) return "Sports";
        if (players.get(currentPlayer).getPlace() == 6) return "Sports";
        if (players.get(currentPlayer).getPlace() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
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
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getPurses() == 6);
    }
}
