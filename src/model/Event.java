package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Event {

	private Attendee root;
	private Attendee head;
	
	public Event(String path) throws IOException {
		refreshAttendees(path);
	}
	
	public void refreshAttendees(String path) throws IOException {
		root = null;
		head = null;
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
		}
		br.close();
		fr.close();
	}

	public void addAttendee(String id, String fn, String ln, String e, String g, String c, String p, Date d) {
		Attendee addme = new Attendee(id, fn, ln, e, g, c, p, d);
		if(root == null) {
			root = addme;
		} else {
			root.add(addme);
		}
	}
	
	public ArrayList<Attendee> preorder() {
		ArrayList<Attendee> atds = new ArrayList<>();
		if(root != null) {
			root.preorder(atds);
		}
		return atds;
	}

	//TODO borrame plox
	public void printBST(ArrayList<String> cdcd) {
		root.print(cdcd);
	}
}
