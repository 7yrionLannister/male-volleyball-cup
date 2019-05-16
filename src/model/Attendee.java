package model;

import java.util.ArrayList;

public class Attendee implements Comparable<Attendee>{
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	private String photo;
	private Date birthday;
	
	private Attendee left;
	private Attendee right;
	
	public Attendee(String id, String firstName, String lastName, String email, String gender, String country, String photo,
			Date birthday) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.country = country;
		this.photo = photo;
		this.birthday = birthday;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}
	
	public String getCountry() {
		return country;
	}

	public String getPhoto() {
		return photo;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	//TODO eliminame si al final no me usas
	public Attendee getLeft() {
		return left;
	}

	//TODO eliminame si al final no me usas
	public void setLeft(Attendee left) {
		this.left = left;
	}

	//TODO eliminame si al final no me usas
	public Attendee getRight() {
		return right;
	}

	//TODO eliminame si al final no me usas
	public void setRight(Attendee right) {
		this.right = right;
	}

	public void add(Attendee addme) {
		if(compareTo(addme) >= 0) {
			if(left != null) {
				left.add(addme);
			} else {
				left = addme;
			}
		} else {
			if(right != null) {
				right.add(addme);
			} else {
				right = addme;
			}
		}
	}
	
	public void preorder(ArrayList<Attendee> sts) {
		sts.add(this);
		if(left != null) {
			left.preorder(sts);
		}
		if(right != null) {
			right.preorder(sts);
		}
	}

	//TODO Borrame plox
	public void print(ArrayList<String> cdcd) {
		if(left != null) {
			left.print(cdcd);
		}
		System.out.println(id);
		cdcd.add(id);
		if(right != null) {
			right.print(cdcd);
		}
	}

	@Override
	public int compareTo(Attendee o) {
		return id.compareTo(o.id);
	}
}
