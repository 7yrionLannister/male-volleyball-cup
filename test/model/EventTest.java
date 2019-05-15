package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class EventTest {
	private Event event;
	
	private void setupScenary1() {
		event = null;
	}
	
	@Test
	public void createEventTest() {
		setupScenary1();
		try {
			event = new Event("data/test.csv");
		} catch (IOException e) {
			fail("There was a problem reading the file");
		}
	}

	@Test
	public void refreshAttendeesTest() {
		createEventTest();
		try {
			event.refreshAttendees("data/test.csv");
		} catch (IOException e) {
			fail("There was a problem reading the file");
		}
	}
	
	@Test
	public void searchAttendeeTest() {
		createEventTest();
		String id = "56-2906064";
		Attendee found = event.searchAttendee(id);
		assertNotNull("The attendee should have been found as the id was copied and pasted from the csv file", found);
		assertTrue("The id of the found attendee does not correspond to the searched id", found.getId().equals(id));
	}
}
