package com.lottery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RandomNumberMachineTest {

	@Test
	public void testDraw() throws NoSuchAlgorithmException, NoSuchProviderException {
		RandomNumberMachine machine = new RandomNumberMachine();
		
		// It's hard to unit test a random number generator convincingly. You
		// can't really black-box test, "are these numbers sufficiently random?" But
		// there are still useful tests to be written.
		for (int i = 0; i < 1000; i++ ) {
			Set<Integer> newDraw = machine.draw();
			
			assertEquals(6, newDraw.size());
			
			List<Integer> sorted = new ArrayList<Integer>(newDraw);
			Collections.sort(sorted);
			assertTrue(sorted.get(0) >= 1);
			assertTrue(sorted.get(5) <= 60);
		}
	}

}
