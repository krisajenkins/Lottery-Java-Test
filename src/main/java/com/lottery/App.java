package com.lottery;

import static com.lottery.DateTimeUtils.parseDateTime;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

public class App {
	public static void main(String[] args) {
		// Parse out the command line arguments. Perhaps we should spin this out
		// into a GameConfig class, but that feels like over-engineering.
		DateTime endDate = parseDateTime(args[0]);

		Set<Integer> selection = new HashSet<Integer>();
		for (int i = 1; i < args.length; i++) {
			Integer number = Integer.parseInt(args[i]);
			selection.add(number);
		}

		System.out.format("Date was: %s\n", endDate);
		System.out.format("Selection was: %s\n", selection);
	}
}
