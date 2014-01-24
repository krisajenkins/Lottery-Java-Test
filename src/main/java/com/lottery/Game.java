package com.lottery;

import static com.lottery.SetUtils.productOf;
import static com.lottery.SetUtils.sumOf;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

public class Game {
	public static BigInteger calculatePrize(DateTime drawDate,
			Set<Integer> winningNumbers, Set<Integer> chosenNumbers) {

		BigInteger rawPrize = calculateRawPrize(drawDate, winningNumbers, chosenNumbers);
		BigInteger multiplier = calculateMultiplier(drawDate);

		return rawPrize.multiply(multiplier);
	}

	private static BigInteger calculateMultiplier(DateTime drawDate) {
		// "There are a few special cases. During the month of February on a
		// leap year, all winnings are doubled and if a draw falls on Monday the
		// 29th of February they are tripled."
		if (drawDate.monthOfYear().isLeap()) {
			if (drawDate.getDayOfMonth() == 29) {
				return BigInteger.valueOf(3);
			}
			
			return BigInteger.valueOf(2);
		}

		return BigInteger.ONE;
	}

	private static BigInteger calculateRawPrize(DateTime drawDate,
			Set<Integer> winningNumbers, Set<Integer> chosenNumbers) {
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
			// TODO Testme.
			throw new IllegalStateException();
		}
	}
}
