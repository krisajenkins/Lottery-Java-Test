package com.lottery;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetUtils {
	public static HashSet<Integer> setOf(Integer...integers) {
		return new HashSet<Integer>(Arrays.asList(integers));
	}

	public static BigInteger sumOf(Set<Integer> numbers) {
		BigInteger sum = BigInteger.ZERO;
		for (Integer n : numbers) {
			sum = sum.add(BigInteger.valueOf(n));
		};
		return sum;
	}
	
	public static BigInteger productOf(Set<Integer> numbers) {
		BigInteger sum = BigInteger.ONE;
		for (Integer n : numbers) {
			sum = sum.multiply(BigInteger.valueOf(n));
		};
		return sum;
	}
}
