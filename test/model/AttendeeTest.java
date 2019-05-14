package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AttendeeTest {
	private Attendee attendee;
	
	private void setupScenary1() {
		attendee = null;
	}
	
	@Test
	public void reateAttendeeTest() {
		setupScenary1();
		String id = "43-723618833";
		String fn = "Catelyn";
		String ln = "Tully";
		String e = "catu@gmail.com";
		String g = "Female";
		String c = "Winterfell";
		String p = "https://robohash.org/consequaturaliquamet.jpg?size=50x50&set=set";
		Date bd = new Date(12,11,1018);
		
		attendee = new Attendee(id, fn, ln, e, g, c, p, bd);
		
		assertTrue("", id.equals(attendee.getId()));
		assertTrue("", fn.equals(attendee.getFirstName()));
		assertTrue("", fn.equals(attendee.getLastName()));
		assertTrue("", e.equals(attendee.getEmail()));
		assertTrue("", g.equals(attendee.getGender()));
		assertTrue("", c.equals(attendee.getCountry()));
		assertTrue("", p.equals(attendee.getPhoto()));
		assertTrue("", bd == attendee.getBirthday());
	}

}
