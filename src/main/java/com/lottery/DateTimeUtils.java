package com.lottery;

import static org.joda.time.DateTimeConstants.MONDAY;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeUtils {
	private static DateTimeFormatter formatter = DateTimeFormat
			.forPattern("dd/mm/yyyy");

	public static DateTime parse(String string) {
		return formatter.parseDateTime(string);
	}

	public static String format(DateTime date) {
		return formatter.print(date);
	}

	public static DateTime normaliseToMonday(DateTime date) {
		return date.dayOfWeek().setCopy(MONDAY);
	}
}
