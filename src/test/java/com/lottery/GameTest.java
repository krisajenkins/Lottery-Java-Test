package com.lottery;

import static com.lottery.SetUtils.setOf;
import static com.lottery.SetUtils.sumOf;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Set;

import org.junit.Test;

public class GameTest {

	@Test
	public void testValueResult() {
		Set<Integer> winningNumbers = setOf(1, 2, 3, 4, 5, 6);

		assertEquals(sumOf(winningNumbers), Game.valueResult(winningNumbers, setOf(11, 12, 13, 14, 15, 16)));
		assertEquals(sumOf(winningNumbers), Game.valueResult(winningNumbers, setOf(1, 12, 13, 14, 15, 16)));
		assertEquals(sumOf(winningNumbers), Game.valueResult(winningNumbers, setOf(1, 2, 13, 14, 15, 16)));
		assertEquals(BigInteger.valueOf((4 * 1000) + (5 * 6)), Game.valueResult(winningNumbers, setOf(1, 2, 3, 4, 15, 16)));
		assertEquals(BigInteger.valueOf((5 * 1000) + 6), Game.valueResult(winningNumbers, setOf(1, 2, 3, 4, 5, 16)));
		assertEquals(BigInteger.valueOf(10000 * (1 + 2 + 3 + 4 + 5 + 6)), Game.valueResult(winningNumbers, setOf(1, 2, 3, 4, 5, 6)));
	}
}
