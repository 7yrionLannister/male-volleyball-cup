package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {

	private Attendee root;
	private Attendee head;

	private int participants;
	private SecureRandom sr;

	public Event(String path) throws IOException {
		sr = new SecureRandom();
		refreshAttendees(path);
	}

	public void refreshAttendees(String path) throws IOException {
		root = null;
		head = null;
		participants = 0;
		load(path);
	}

	private void load(String path) throws IOException {
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] data = line.split(",");
			String id = data[0];
			String fn = data[1];
			String ln = data[2];
			String e = data[3];
			String g = data[4];
			String c = data[5];
			String p = data[6];
			String[] bd = data[7].split("/");
			Date d = new Date(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));

			addAttendee(id, fn, ln, e, g, c, p, d);
			line = br.readLine();
			participants++;
		}
		br.close();
		fr.close();

		participants /= 2;
		selectParticipants();
	}

	public void addAttendee(String id, String fn, String ln, String e, String g, String c, String p, Date d) {
		Attendee addme = new Attendee(id, fn, ln, e, g, c, p, d);
		if(root == null) {
			root = addme;
		} else {
			root.add(addme);
		}
	}

	public Attendee searchAttendee(String id) {
		return searchAttendee(root, id);
	}

	private Attendee searchAttendee(Attendee current, String id) {
		if(current == null || id.compareTo(current.getId()) == 0) {
			return current;
		} else if(id.compareTo(current.getId()) < 0) {
			return searchAttendee(current.getLeft(), id);
		} else {
			return searchAttendee(current.getRight(), id);
		}
	}

	public ArrayList<Attendee> preorder() {
		ArrayList<Attendee> atds = new ArrayList<>();
		if(root != null) {
			root.preorder(atds);
		}
		return atds;
	}

	public ArrayList<Attendee> inorder() {
		ArrayList<Attendee> atds = new ArrayList<>();
		if(root != null) {
			root.inorder(atds);
		}
		return atds;
	}

	private void selectParticipants() {
		participants-=1;
		ArrayList<Attendee> preorder = preorder();
		//System.out.println(preorder.size());
		if(participants > 0 && preorder.size() > 1) {
			int index = 2+sr.nextInt(preorder.size()-1);
			removeFromBSTAndInsertInLinkedlist(preorder.get(index));
			selectParticipants();
		}
	}

	private void removeFromBSTAndInsertInLinkedlist(Attendee toremove) {
		//TODO implementar llamando a addToLinkedList
	}
	
	//returns the parent node of the node to be deleted
	private Attendee addToLinkedList(Attendee atld) {
		//TODO implementar
	}

	public Attendee getRoot() {
		return root;
	}

	public Attendee getHead() {
		return head;
	}

	public int getParticipants() {
		return participants;
	}
}
