package com.lottery;

import static com.lottery.Game.DRAW_NUMBER_COUNT;
import static com.lottery.Game.DRAW_NUMBER_MAX;
import static com.lottery.Game.DRAW_NUMBER_MIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class RandomNumberMachineTest {
	@Test
	public void testDraw() throws NoSuchAlgorithmException,
			NoSuchProviderException {
		RandomNumberMachine machine = new RandomNumberMachine();

		// It's hard to unit test a random number generator convincingly. You
		// can't really black-box test, "are these numbers sufficiently random?"
		// But there are still useful tests to be written.
		for (int i = 0; i < 1000; i++) {
			List<Integer> newDraw = machine.draw();

			assertEquals(DRAW_NUMBER_COUNT, newDraw.size());

			List<Integer> sorted = new ArrayList<Integer>(newDraw);
			Collections.sort(sorted);
			assertTrue(sorted.get(0) >= DRAW_NUMBER_MIN);
			assertTrue(sorted.get(DRAW_NUMBER_COUNT - 1) <= DRAW_NUMBER_MAX);
		}
	}
}
