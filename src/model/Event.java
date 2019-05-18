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
		while(line != null && line.length() > 0) {
			line = line.substring(0, line.length()-1);
			String[] data = line.split(",");
			String id = data[0];
			String fn = data[1];
			String ln = data[2];
			String e = data[3];
			String g = data[4];
			String c = data[5];
			String p = data[6];
			String[] bd = data[7].split("/");
			Date d = new Date(Integer.parseInt(bd[0]),
					Integer.parseInt(bd[1]), 
					Integer.parseInt(bd[2]));

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
	
	public Attendee searchParticipant(String id) {
		return searchParticipant(root, id);
	}
	
	private Attendee searchParticipant(Attendee current, String id) {
		if(current == null || id.compareTo(current.getId()) == 0) {
			return current;
		} else {
			return searchParticipant(current.getRight(), id);
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
		if(participants > 0 && preorder.size() > 1) {
			int index = 1+sr.nextInt(preorder.size()-1);
			removeFromBSTAndInsertInLinkedlist(preorder.get(index));
			selectParticipants();
		}
	}

	private void removeFromBSTAndInsertInLinkedlist(Attendee toremove) {
		
		if(toremove.getLeft() != null && toremove.getRight() != null) {
			Attendee successor = successor(toremove);
			toremove.setBirthday(successor.getBirthday());
			toremove.setCountry(successor.getCountry());
			toremove.setEmail(successor.getEmail());
			toremove.setFirstName(successor.getFirstName());
			toremove.setLastName(successor.getLastName());
			toremove.setId(successor.getId());
			toremove.setGender(successor.getGender());
			toremove.setPhoto(successor.getPhoto());
			
			removeFromBSTAndInsertInLinkedlist(successor);
		} else if(toremove.getLeft() != null) {
			if(toremove.getParent().getLeft() == toremove) {
				toremove.getParent().setLeft(toremove.getLeft());
			} else {
				toremove.getParent().setRight(toremove.getLeft());
			}
			//addToLinkedList(toremove.getId(), toremove.getFirstName(), toremove.getLastName(), toremove.getEmail(), toremove.getGender(), toremove.getCountry(), toremove.getPhoto(), toremove.getBirthday().clone());
		} else if(toremove.getRight() != null) {
			if(toremove.getParent().getRight() == toremove) {
				toremove.getParent().setRight(toremove.getRight());
			} else {
				toremove.getParent().setLeft(toremove.getRight());
			}
		} else { //es solo una hoja
			System.out.println(toremove.getParent());
			if(toremove.getParent().getRight() == toremove) {
				toremove.getParent().setRight(null);
			} else {
				toremove.getParent().setLeft(null);
			}
		}
	}
	
	private void addToLinkedList(String id, String fn, String ln, String e, String g, String c, String p, Date d) {
		Attendee addme = new Attendee(id, fn, ln, e, g, c, p, d);
		if(head == null) {
			head = addme;
		} else {
			addme.setRight(head);
			head.setLeft(addme);
			head = addme;
		}
	}

	public Attendee minimum() {
		if(root == null) {
			return root;
		} else {
			return minimum(root);
		}
	}
	
	private Attendee minimum(Attendee current) {
		if(current.getLeft() == null) {
			return current;
		} else {
			return minimum(current.getLeft());
		}
	}
	
	public Attendee successor(Attendee at) {
		if(at.getRight() != null) {
			return minimum(at.getRight());
		} else {
			Attendee current = at;
			while(current != null && current.getParent().getLeft() != current) {
				current = current.getParent();
			}
			return (current!=null)?current.getParent():null;
		}
	}
	
	public Attendee getRoot() {
		return root;
	}

	public Attendee getHead() {
		return head;
	}
}
