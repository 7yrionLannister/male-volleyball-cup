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
	private Attendee parent;

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

	public Attendee getLeft() {
		return left;
	}

	public void setLeft(Attendee left) {
		this.left = left;
	}

	public Attendee getRight() {
		return right;
	}

	public void setRight(Attendee right) {
		this.right = right;
	}

	public Attendee getParent() {
		return parent;
	}

	public void setParent(Attendee parent) {
		this.parent = parent;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void add(Attendee addme) {
		if(compareTo(addme) >= 0) {
			if(left != null) {
				addme.setParent(left);
				left.add(addme);
			} else {
				left = addme;
			}
		} else {
			if(right != null) {
				addme.setParent(right);
				right.add(addme);
			} else {
				right = addme;
			}
		}
	}

	public void preorder(ArrayList<Attendee> atds) {
		atds.add(this);
		if(left != null) {
			left.preorder(atds);
		}
		if(right != null) {
			right.preorder(atds);
		}
	}

	public void inorder(ArrayList<Attendee> atds) {
		if(left != null) {
			left.inorder(atds);
		}
		atds.add(this);
		if(right != null) {
			right.inorder(atds);
		}
	}

	@Override
	public int compareTo(Attendee o) {
		return id.compareTo(o.id);
	}
	
	@Override
	public Attendee clone() {
		return new Attendee(id, firstName, lastName, email, gender, country, photo, birthday.clone());
	}
}
