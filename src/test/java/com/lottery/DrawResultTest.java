package com.lottery;

import static com.lottery.DateTimeUtils.parse;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

public class DrawResultTest {
	@Test
	public void testToString() {
		assertEquals("10/04/2003; 1, 2, 3, 4, 5, 6; 12345", new DrawResult(
				parse("10/04/2003"), Arrays.asList(1, 2, 3, 4, 5, 6),
				BigInteger.valueOf(12345)).toString());
		assertEquals(
				"29/02/2008; 60, 59, 58, 57, 56, 55; 54321",
				new DrawResult(parse("29/02/2008"), Arrays.asList(60, 59, 58,
						57, 56, 55), BigInteger.valueOf(54321)).toString());
	}
}
