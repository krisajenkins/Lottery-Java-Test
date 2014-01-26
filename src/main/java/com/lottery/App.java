package com.lottery;

import static com.lottery.DateTimeUtils.parse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

public class App {
	public static void main(String[] args) {
		// Parse out the command line arguments. Perhaps we should spin this out
		// into a GameConfig class, but that feels like over-engineering.
		DateTime endDate = parse(args[0]);

		Set<Integer> chosenNumbers = new HashSet<Integer>();
		for (int i = 1; i < args.length; i++) {
			Integer number = Integer.parseInt(args[i]);
			chosenNumbers.add(number);
		}

		Game game = new Game();
		List<GameResult> results = game.runGamePeriod(endDate, chosenNumbers);
		for (GameResult result : results) {
			System.out.println(result);
		}
	}
}
