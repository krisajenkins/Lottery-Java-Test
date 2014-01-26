package com.lottery;

import static com.lottery.CollectionUtils.productOf;
import static com.lottery.CollectionUtils.sumOf;
import static java.lang.String.format;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

public class Game {
	public static final int DRAW_NUMBER_MIN = 1;
	public static final int DRAW_NUMBER_MAX = 60;
	public static final int DRAW_NUMBER_COUNT = 6;

	private NumberGenerator generator;

	public Game(NumberGenerator generator) {
		this.generator = generator;
	}

	public List<DrawResult> runGamePeriod(DateTime endDate,
			Collection<Integer> chosenNumbers) {
		assert chosenNumbers.size() == DRAW_NUMBER_COUNT;

		DateTime drawDate = endDate.minusWeeks(25);
		List<DrawResult> results = new ArrayList<DrawResult>();

		while (drawDate.compareTo(endDate) <= 0) {
			List<Integer> winningNumbers = generator.draw();
			BigInteger prize = calculatePrize(drawDate, winningNumbers,
					chosenNumbers);

			DrawResult result = new DrawResult(drawDate, winningNumbers, prize);
			results.add(result);

			drawDate = drawDate.plusWeeks(1);
		}

		return results;
	}

	public static BigInteger calculatePrize(DateTime drawDate,
			Collection<Integer> winningNumbers,
			Collection<Integer> chosenNumbers) {

		BigInteger rawPrize = calculateRawPrize(drawDate, winningNumbers,
				chosenNumbers);
		BigInteger multiplier = calculateMultiplier(drawDate);

		return rawPrize.multiply(multiplier);
	}

	private static BigInteger calculateMultiplier(DateTime drawDate) {
		/*
		 * "During the month of February on a leap year, all winnings are
		 * doubled and if a draw falls on Monday the 29th of February they are
		 * tripled."
		 */
		if (drawDate.monthOfYear().isLeap()) {
			if (drawDate.getDayOfMonth() == 29) {
				return BigInteger.valueOf(3);
			}

			return BigInteger.valueOf(2);
		}

		return BigInteger.ONE;
	}

	private static BigInteger calculateRawPrize(DateTime drawDate,
			Collection<Integer> winningNumbers,
			Collection<Integer> chosenNumbers) {
		Set<Integer> correctlyChosenNumbers = new HashSet<Integer>(
				winningNumbers);
		correctlyChosenNumbers.retainAll(chosenNumbers);

		Set<Integer> missedNumbers = new HashSet<Integer>(winningNumbers);
		missedNumbers.removeAll(chosenNumbers);

		switch (correctlyChosenNumbers.size()) {
		case 0:
		case 1:
		case 2:
			return sumOf(winningNumbers);
		case 3:
		case 4:
		case 5:
			return BigInteger.valueOf(1000 * correctlyChosenNumbers.size())
					.add(productOf(missedNumbers));
		case 6:
			return sumOf(winningNumbers).multiply(BigInteger.valueOf(10000));
		default:
			
		}
		
		throw new IllegalStateException("Programmer error. This class has been changed and is no longer self-consistent.");
	}
}
