package com.lottery;

import static com.lottery.CollectionUtils.toCSV;

import java.math.BigInteger;
import java.util.List;

import org.joda.time.DateTime;

// Just a struct to hold the results.
public class DrawResult {
	private DateTime drawDate;
	private List<Integer> winningNumbers;
	private BigInteger prize;

	public DrawResult(DateTime drawDate, List<Integer> winningNumbers,
			BigInteger prize) {
		this.drawDate = drawDate;
		this.winningNumbers = winningNumbers;
		this.prize = prize;
	}

	public DateTime getDrawDate() {
		return drawDate;
	}

	public List<Integer> getWinningNumbers() {
		return winningNumbers;
	}

	public BigInteger getPrize() {
		return prize;
	}

	@Override
	public String toString() {
		return String.format("%s; %s; %s", DateTimeUtils.format(drawDate),
				toCSV(winningNumbers), prize);
	}
}
