package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

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
			event = new Event("data/testfile.csv");
			fail("The event should not have been created as data/testfile.csv does not exist");
		} catch(IOException e) {
			assertTrue(true);
		}
		try {
			event = new Event("data/event.csv");
		} catch (IOException e) {
			fail("There was a problem reading the file");
		}
	}

	@Test
	public void refreshAttendeesTest() {
		createEventTest();
		try {
			event.refreshAttendees("data/event.csv");
		} catch (IOException e) {
			fail("There was a problem reading the file");
		}
	}
	
	@Test
	public void inorderTest() {
		createEventTest();
		ArrayList<Attendee> inorder = event.inorder();
		for(int i = 1; i < inorder.size(); i++) {
			assertTrue("The returned list is not in preorder", inorder.get(i).compareTo(inorder.get(i-1))>=0);
		}
	}
	
	@Test
	public void searchAttendeeTest() {
		createEventTest();
		String id = "95-0848182";
		Attendee found = event.searchAttendee(id);
		if(found == null) {
			found = event.searchParticipant(id);
		}
		
		assertNotNull("The attendee should have been found as the id was copied and pasted from the csv file", found);
		assertTrue("The id of the found attendee does not correspond to the searched id", found.getId().equals(id));
	}
	
	@Test
	public void addAttendeeTest() {
		createEventTest();
		event.addAttendee("16-2346743", "a", "b", "c@gmail.com", "Male", "Mexico", "photo", new Date(1,2,3));
		event.addAttendee("90-1236574", "d", "e", "f@gmail.com", "Female", "Colombia", "photooooo", new Date(3,2,1));
		event.addAttendee("10-7897895", "g", "h", "i@gmail.com", "Male", "Ecuador", "pppphoto", new Date(1,1,1));
		event.addAttendee("14-3453455", "j", "k", "l@gmail.com", "Female", "Venezuela", "ppphotooo", new Date(2,2,3));
		event.addAttendee("27-1231233", "m", "n", "o@gmail.com", "Male", "Cuba", "phhhoto", new Date(1,3,3));
		ArrayList<Attendee> inorder = event.inorder();
		for(int i = 1; i < inorder.size(); i++) {
			assertTrue("The returned list is not in preorder", inorder.get(i).compareTo(inorder.get(i-1))>=0);
		}
	}
}
