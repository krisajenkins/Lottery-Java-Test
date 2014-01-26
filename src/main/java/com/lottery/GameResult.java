package com.lottery;

import java.math.BigInteger;
import java.util.Set;

import org.joda.time.DateTime;

public class GameResult {
	private DateTime drawDate;
	private Set<Integer> winningNumbers;
	private BigInteger prize;

	public GameResult(DateTime drawDate, Set<Integer> winningNumbers,
			BigInteger prize) {
		this.drawDate = drawDate;
		this.winningNumbers = winningNumbers;
		this.prize = prize;
	}

	@Override
	public String toString() {
		return String.format("%s; %s; %s", DateTimeUtils.format(drawDate),
				winningNumbers, prize);
	}
}
