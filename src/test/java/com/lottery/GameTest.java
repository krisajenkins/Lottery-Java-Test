package com.lottery;

import static com.lottery.CollectionUtils.asSet;
import static com.lottery.CollectionUtils.sumOf;
import static com.lottery.DateTimeUtils.normaliseToMonday;
import static com.lottery.DateTimeUtils.parse;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

public class GameTest {
	@Test
	public void testRunGamePeriod() {
		Game game = new Game(new NumberGenerator() {
			@Override
			public List<Integer> draw() {
				return Arrays.asList(1, 2, 3, 4, 5, 6);
			}
		});

		DateTime endDate = parse("01/01/2001");
		List<DrawResult> alwaysWins = game.runGamePeriod(endDate,
				asSet(1, 2, 3, 4, 5, 6));
		assertEquals(26, alwaysWins.size());
		assertEquals(endDate, alwaysWins.get(25).getDrawDate());
	}

	@Test
	public void testValueResultExample() {
		DateTime drawDate = normaliseToMonday(parse("01/01/2000"));
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

			Set<Integer> winningNumbers = asSet(1, 2, 3, 4, 5, 6);

			// Losing rule.
			checkSingleGame(drawDate, sumOf(winningNumbers), multiplier,
					winningNumbers, 11, 12, 13, 14, 15, 16);
			checkSingleGame(drawDate, sumOf(winningNumbers), multiplier,
					winningNumbers, 1, 12, 13, 14, 15, 16);
			checkSingleGame(drawDate, sumOf(winningNumbers), multiplier,
					winningNumbers, 1, 2, 13, 14, 15, 16);

			// Middle-prize rule.
			checkSingleGame(drawDate, (3 * 1000) + (4 * 5 * 6), multiplier,
					winningNumbers, 1, 2, 3, 14, 15, 16);
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

	@Test
	public void testValueResultSimulation() {
		// A few constraint-checks.
		RandomNumberGenerator machine = new RandomNumberGenerator();
		DateTime drawDate = DateTimeUtils.parse("01/01/2000");

		for (int i = 0; i < 5000; i++) {
			List<Integer> winningNumbers = machine.draw();
			List<Integer> chosenNumbers = machine.draw();
			Set<Integer> matches = new HashSet<Integer>(winningNumbers);
			matches.retainAll(chosenNumbers);

			BigInteger prize = Game.calculatePrize(drawDate, winningNumbers,
					chosenNumbers);

			switch (matches.size()) {
			case 0:
			case 1:
			case 2:
				assertTrue(prize.compareTo(BigInteger.valueOf(1 + 2 + 3 + 4 + 5
						+ 6)) >= 0);
				assertTrue(prize.compareTo(BigInteger.valueOf(60 + 59 + 58 + 57
						+ 56 + 55)) <= 0);
				break;
			case 3:
				assertTrue(prize.compareTo(BigInteger.valueOf((3 * 1000)
						+ (1 * 2 * 3))) >= 0);
				assertTrue(prize.compareTo(BigInteger.valueOf((3 * 1000)
						+ (60 * 59 * 58))) <= 0);
				break;
			case 4:
				assertTrue(prize.compareTo(BigInteger.valueOf((4 * 1000)
						+ (1 * 2 * 3 * 4))) >= 0);
				assertTrue(prize.compareTo(BigInteger.valueOf((4 * 1000)
						+ (60 * 59))) <= 0);
				break;
			case 5:
				assertTrue(prize.compareTo(BigInteger.valueOf((5 * 1000)
						+ (1 * 2 * 3 * 4 * 5))) >= 0);
				assertTrue(prize.compareTo(BigInteger
						.valueOf((5 * 1000) + (60))) <= 0);
				break;
			case 6:
				assertTrue(prize.compareTo(BigInteger.valueOf(10000 * (1 + 2
						+ 3 + 4 + 5 + 6))) >= 0);
				assertTrue(prize.compareTo(BigInteger.valueOf(10000 * (60 + 59
						+ 58 + 57 + 56 + 55))) <= 0);
				break;
			default:
				throw new IllegalStateException();
			}
		}
	}

	private void checkSingleGame(DateTime drawDate, BigInteger expectedPrize,
			Integer multiplier, Set<Integer> winningNumbers,
			Integer... chosenNumbers) {
		assertEquals(expectedPrize.multiply(BigInteger.valueOf(multiplier)),
				Game.calculatePrize(drawDate, winningNumbers,
						asSet(chosenNumbers)));
	}

	private void checkSingleGame(DateTime drawDate, Integer expectedPrize,
			Integer multiplier, Set<Integer> winningNumbers,
			Integer... chosenNumbers) {
		checkSingleGame(drawDate, BigInteger.valueOf(expectedPrize),
				multiplier, winningNumbers, chosenNumbers);
	}
}
