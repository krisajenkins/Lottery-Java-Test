package com.lottery;

import static com.lottery.SetUtils.productOf;
import static com.lottery.SetUtils.sumOf;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Game {
	public static BigInteger valueResult(
		Set<Integer> winningNumbers,
		Set<Integer> chosenNumbers
	) {
		Set<Integer> correctlyChosenNumbers = new HashSet<Integer>(winningNumbers);
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
			return BigInteger.valueOf(1000 * correctlyChosenNumbers.size()).add(productOf(missedNumbers)); 
		case 6:
			return sumOf(winningNumbers).multiply(BigInteger.valueOf(10000)); 
		default:
			// TODO Testme.
			throw new IllegalStateException();
		}		
	}
}
