package application;

import java.util.Date;

public class Martyr implements Comparable<Martyr> {
	private String Name;
	private char Gender;
	private Date dateOfMartyr;
	private int Age;
	private String Eventlocation;


	public Martyr(String name, int age,String eventlocation, Date dateOfMartyr,char gender) {
		super();
		Name = name;
		Gender = gender;
		this.dateOfMartyr = dateOfMartyr;
		Age = age;
		Eventlocation = eventlocation;
	}

	public Martyr(String name) {
		super();
		Name = name;
	}

	public Martyr(int age) {
		super();
		Age = age;
	}

	public void setGender(char gender) {
		if (gender == 'M' || gender == 'F') {
			this.Gender = gender;}
		else { 
			System.out.println("pleas enter M OR f");
		}
		
	}

	public Martyr(Date dateOfMartyr) {
		this.dateOfMartyr = dateOfMartyr;
	}

	public Martyr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public char getGender() {
		return Gender;
	}

	public Martyr(char gender) {
		super();
		Gender = gender;
	}

	public Date getDateOfMartyr() {
		return dateOfMartyr;
	}

	public void setDateOfMartyr(Date dateOfMartyr) {
		this.dateOfMartyr = dateOfMartyr;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getEventlocation() {
		return Eventlocation;
	}

	public void setEventlocation(String eventlocation) {
		Eventlocation = eventlocation;
	}

	@Override
	public String toString() {
		return "Martyr [Name=" + Name + ", Gender=" + Gender + ", dateOfMartyr=" + dateOfMartyr + ", Age=" + Age
				+ ", Eventlocation=" + Eventlocation + "]";
	}

	@Override
	public int compareTo(Martyr o) {
		if (o.getName().equals(this.getName())) {
			return 0;
		}

		else if (o.getAge() == (this.getAge())) {
			return 0;
		} else if (o.getGender() == (this.getGender())) {
			return 0;
		} else if (o.getDateOfMartyr().equals(this.getDateOfMartyr())) {
			return 0;
		} else {
			return 1;
		}
	}
}
