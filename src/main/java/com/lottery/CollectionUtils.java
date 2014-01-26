package com.lottery;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CollectionUtils {
	public static HashSet<Integer> asSet(Integer... integers) {
		return new HashSet<Integer>(Arrays.asList(integers));
	}

	public static BigInteger sumOf(Collection<Integer> numbers) {
		BigInteger sum = BigInteger.ZERO;
		for (Integer n : numbers) {
			sum = sum.add(BigInteger.valueOf(n));
		}
		return sum;
	}

	public static BigInteger productOf(Collection<Integer> numbers) {
		BigInteger sum = BigInteger.ONE;
		for (Integer n : numbers) {
			sum = sum.multiply(BigInteger.valueOf(n));
		}
		return sum;
	}

	public static String toCSV(Collection<?> collection) {
		String result = "";
		Iterator<?> iterator = collection.iterator();
		if (iterator.hasNext()) {
			result = result.concat(iterator.next().toString());
		}
		while (iterator.hasNext()) {
			result = result.concat(", ");
			result = result.concat(iterator.next().toString());
		}
		return result;
	}
}
