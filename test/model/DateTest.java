package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DateTest {
	private Date date;

	private void setupScenary1() {
		date = null;
	}

	@Test
	public void createDateTest() {
		setupScenary1();
		int day = 4;
		int month = 3;
		int year = 2001;
		date = new Date(month, day, year);
		assertTrue("The day has not the requested value", day == date.getDay());
		assertTrue("The month has not the requested value", month == date.getMonth());
		assertTrue("The year has not the requested value", year == date.getYear());

		day = -9;
		month = 14;
		year = 2010;
		try {
			date = new Date(day, month, year);
			fail("The Date should not have been created as the day and month passed to the constructor were not in the domain");
		} catch(IllegalArgumentException iae) {
			assertTrue(true);
		}
	}

}
