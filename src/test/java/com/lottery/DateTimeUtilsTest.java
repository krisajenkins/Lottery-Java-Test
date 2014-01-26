package com.lottery;

import static com.lottery.DateTimeUtils.normaliseToMonday;
import static org.joda.time.DateTimeConstants.MONDAY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;

public class DateTimeUtilsTest {
	@Test
	public void testNormaliseToMonday() {
		DateTime aDate = new DateTime();
		long startYear = aDate.getYear();

		while (aDate.getYear() < startYear + 100) {
			DateTime nearestMonday = normaliseToMonday(aDate);
			Days delta = Days.daysBetween(aDate, nearestMonday);

			assertEquals(MONDAY, nearestMonday.getDayOfWeek());
			assertTrue(delta.getDays() <= 3);

			aDate = aDate.plusDays(1);
		}
	}
}
