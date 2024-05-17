
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.io.PrintStream;
import java.util.Random;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		PrintStream out = System.out;
		Random rand = new Random();
		run(out, rand.nextInt(5), rand.nextInt(9));
	}

	public static void run(PrintStream out, int randInt, int secondRandom) {
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		do {

			aGame.roll(randInt + 1, out);

			if (secondRandom == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}



		} while (notAWinner);
	}
}
