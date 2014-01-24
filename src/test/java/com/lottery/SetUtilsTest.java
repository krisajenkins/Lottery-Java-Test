package com.lottery;

import static com.lottery.SetUtils.productOf;
import static com.lottery.SetUtils.sumOf;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SetUtilsTest {

	@Test
	public void testSumAndProductOf() {
		Set<Integer> numbers = new HashSet<Integer>();

		BigInteger sum = BigInteger.ZERO;
		BigInteger product = BigInteger.ONE;
		for (int i = 0; i < 100; i++) {
			sum = sum.add(BigInteger.valueOf(i));
			product = product.multiply(BigInteger.valueOf(i));
			numbers.add(i);
			assertEquals(sum, sumOf(numbers));
			assertEquals(product, productOf(numbers));
		}
	}
}
