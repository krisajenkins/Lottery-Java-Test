package com.lottery;

import static com.lottery.DateTimeUtils.normaliseToMonday;
import static com.lottery.DateTimeUtils.parseDateTime;
import static com.lottery.SetUtils.setOf;
import static com.lottery.SetUtils.sumOf;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

public class GameTest {

	@Test
	public void testValueResultSimple() {
		DateTime drawDate = normaliseToMonday(parseDateTime("01/01/2000"));
		Integer multiplier;

		while (drawDate.getYear() < 2100) {
			if (drawDate.monthOfYear().isLeap()) {
				if (drawDate.getDayOfMonth() == 29) {
					multiplier = 3;
				} else {
					multiplier = 2;
				}
			} else {
				multiplier = 1;
			}

			Set<Integer> winningNumbers = setOf(1, 2, 3, 4, 5, 6);

			// Losing rule.
			checkSingleGame(drawDate, sumOf(winningNumbers), multiplier,
					winningNumbers, 11, 12, 13, 14, 15, 16);
			checkSingleGame(drawDate, sumOf(winningNumbers), multiplier,
					winningNumbers, 1, 12, 13, 14, 15, 16);
			checkSingleGame(drawDate, sumOf(winningNumbers), multiplier,
					winningNumbers, 1, 2, 13, 14, 15, 16);

			// Middle-prize rule.
			checkSingleGame(drawDate, (4 * 1000) + (5 * 6), multiplier,
					winningNumbers, 1, 2, 3, 4, 15, 16);
			checkSingleGame(drawDate, (5 * 1000) + 6, multiplier,
					winningNumbers, 1, 2, 3, 4, 5, 16);

			// Winning rule.
			checkSingleGame(drawDate, 10000 * (1 + 2 + 3 + 4 + 5 + 6),
					multiplier, winningNumbers, 1, 2, 3, 4, 5, 6);

			drawDate = drawDate.plusWeeks(1);
		}
	}

	private void checkSingleGame(DateTime drawDate, BigInteger expectedPrize,
			Integer multiplier, Set<Integer> winningNumbers,
			Integer... chosenNumbers) {
		assertEquals(
				expectedPrize.multiply(BigInteger.valueOf(multiplier)),
				Game.calculatePrize(drawDate, winningNumbers, setOf(chosenNumbers)));
	}

	private void checkSingleGame(DateTime drawDate, Integer expectedPrize,
			Integer multiplier, Set<Integer> winningNumbers,
			Integer... chosenNumbers) {
		checkSingleGame(drawDate, BigInteger.valueOf(expectedPrize),
				multiplier, winningNumbers, chosenNumbers);
	}
}
