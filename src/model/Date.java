package model;

public class Date {
	private int day;
	private int month;
	private int year;
	
	public Date(int month, int day, int year) throws IllegalArgumentException {
		if(day <= 0 || day > 31 || month <= 0 || month > 12) {
			throw new IllegalArgumentException("Invalid date: (" + day + ", " + month + ", " + year + ")");
		}
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	@Override
	public String toString() {
		return month+"/"+day+"/"+year;
	}
	
	@Override
	public Date clone() {
		return new Date(month, day, year);
	}
}
