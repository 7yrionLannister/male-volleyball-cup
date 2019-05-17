package model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AttendeeTest {
	private Attendee root;
	private ArrayList<Attendee> trail;
	
	private void setupScenario1() {
		root = null;
	}
	
	private void setupScenario2() {
		setupScenario1();
		trail = new ArrayList<>();
		
		trail.add(new Attendee("47-9670632","Doralia","Tough","dtough0@list-manage.com","Female","France","https://robohash.org/consequaturaliquamet.jpg?size=50x50&set=set1",new Date(11,14,1974)));
		trail.add(new Attendee("02-5620315","Minor","Fligg","mfligg1@scribd.com","Male","Brazil","https://robohash.org/autveniamconsequatur.jpg?size=50x50&set=set1",new Date(9,28,1999)));
		trail.add(new Attendee("51-7344981","Llewellyn","McGill","lmcgill5@cmu.edu","Male","Uzbekistan","https://robohash.org/possimusquovoluptas.jpg?size=50x50&set=set1",new Date(1,25,1989)));
		trail.add(new Attendee("75-5434535","Stacey","Pickring","spickring2@japanpost.jp","Female","Philippines","https://robohash.org/ducimusfacereea.jpg?size=50x50&set=set1",new Date(5,9,1994)));
		trail.add(new Attendee("71-4480440","Darcie","Staining","dstaining3@aboutads.info","Female","Russia","https://robohash.org/perferendisesteum.bmp?size=50x50&set=set1",new Date(12,8,2008)));
		trail.add(new Attendee("55-5500788","Ignazio","Rayburn","irayburn6@4shared.com","Male","Indonesia","https://robohash.org/etreprehenderitfuga.bmp?size=50x50&set=set1",new Date(6,25,1989)));
		trail.add(new Attendee("73-7717155","Bevvy","Hebborne","bhebborne7@cafepress.com","Female","Dominican Republic","https://robohash.org/voluptatemautassumenda.bmp?size=50x50&set=set1",new Date(3,7,1966)));
		trail.add(new Attendee("78-3054052","Micheal","Geill","mgeill4@rediff.com","Male","Poland","https://robohash.org/quosintveniam.bmp?size=50x50&set=set1",new Date(1,9,1977)));
		trail.add(new Attendee("91-4831242","Augustine","Prop","aprop8@e-recht24.de","Female","France","https://robohash.org/sedrerumeos.png?size=50x50&set=set1",new Date(9,23,1972)));
		trail.add(new Attendee("95-2624090","Pieter","Bealton","pbealton9@oaic.gov.au","Male","Jordan","https://robohash.org/utdolormollitia.bmp?size=50x50&set=set1",new Date(7,17,1980)));
	}
	
	private void setupScenario3preorder() {
		setupScenario2();
		
		root = trail.get(0);
		trail.get(0).setLeft(trail.get(1));
		trail.get(0).setRight(trail.get(2));
		trail.get(2).setRight(trail.get(3));
		trail.get(3).setLeft(trail.get(4));
		trail.get(4).setLeft(trail.get(5));
		trail.get(4).setRight(trail.get(6));
		trail.get(3).setRight(trail.get(7));
		trail.get(7).setRight(trail.get(8));
		trail.get(8).setRight(trail.get(9));
	}
	
	private void setupScenario4inorder() {
		setupScenario3preorder();
	}
	
	@Test
	public void createAttendeeTest() {
		setupScenario1();
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
	
	@Test
	public void preorderTest() {
		setupScenario3preorder();
		ArrayList<Attendee> preorder = new ArrayList<Attendee>();
		root.preorder(preorder);
		
		for(int i = 0; i < preorder.size(); i++) {
			assertTrue("The returned list is not in preorder", preorder.get(i).compareTo(trail.get(i)) == 0);
		}
	}
	
	@Test
	public void inorderTest() {
		setupScenario4inorder();
		ArrayList<Attendee> inorder = new ArrayList<Attendee>();
		root.inorder(inorder);
		for(int i = 1; i < inorder.size(); i++) {
			assertTrue("The returned list is not in preorder", inorder.get(i).compareTo(inorder.get(i-1))>=0);
		}
	}
	
	@Test
	public void addAttendeeTest() {
		setupScenario3preorder();
		root.add(new Attendee("16-2346743", "a", "b", "c@gmail.com", "Male", "Mexico", "photo", new Date(1,2,3)));
		root.add(new Attendee("90-1236574", "d", "e", "f@gmail.com", "Female", "Colombia", "photooooo", new Date(3,2,1)));
		root.add(new Attendee("10-7897895", "g", "h", "i@gmail.com", "Male", "Ecuador", "pppphoto", new Date(1,1,1)));
		root.add(new Attendee("14-3453455", "j", "k", "l@gmail.com", "Female", "Venezuela", "ppphotooo", new Date(2,2,3)));
		root.add(new Attendee("27-1231233", "m", "n", "o@gmail.com", "Male", "Cuba", "phhhoto", new Date(1,3,3)));
		ArrayList<Attendee> inorder = new ArrayList<>();
		root.inorder(inorder);
		
		for(int i = 1; i < inorder.size(); i++) {
			assertTrue("The list does not preserve the order rule when adding elements", inorder.get(i).compareTo(inorder.get(i-1))>=0);
		}
	}
}
