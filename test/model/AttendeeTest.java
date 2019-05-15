package model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class AttendeeTest {
	private Attendee root;
	
	private void setupScenary1() {
		root = null;
	}
	
	@Test
	public void createAttendeeTest() {
		setupScenary1();
		String id = "43-723618833";
		String fn = "Catelyn";
		String ln = "Tully";
		String e = "catu@gmail.com";
		String g = "Female";
		String c = "Winterfell";
		String p = "https://robohash.org/consequaturaliquamet.jpg?size=50x50&set=set";
		Date bd = new Date(12,11,1018);
		
		root = new Attendee(id, fn, ln, e, g, c, p, bd);
		
		assertTrue("The id does not have the requested value", id.equals(root.getId()));
		assertTrue("The first name does not have the requested value", fn.equals(root.getFirstName()));
		assertTrue("The last name does not have the requested value", ln.equals(root.getLastName()));
		assertTrue("The email does not have the requested value", e.equals(root.getEmail()));
		assertTrue("The gender does not have the requested value", g.equals(root.getGender()));
		assertTrue("The country does not have the requested value", c.equals(root.getCountry()));
		assertTrue("The photo does not have the requested value", p.equals(root.getPhoto()));
		assertTrue("The birthday does not have the requested value", bd == root.getBirthday());
		assertNull("The attendee has just been created so it wouldn't have child nodes", root.getLeft());
		assertNull("The attendee has just been created so it wouldn't have child nodes", root.getRight());
	}
}
