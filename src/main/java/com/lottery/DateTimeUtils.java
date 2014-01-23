package com.lottery;

import static org.joda.time.DateTimeConstants.MONDAY;

import org.joda.time.DateTime;

public class DateTimeUtils {
	public static DateTime normaliseToMonday(DateTime date) {
		return date.dayOfWeek().setCopy(MONDAY);
	}
}
