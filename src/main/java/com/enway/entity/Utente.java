package com.enway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utente {
	@Id
	@GeneratedValue
	int id;
	
	@Column(name="first_name")
	String firstName;

	@Column(name="last_name")
	String lastName;
	
	int age;
	
	float salary;
	
	boolean adult;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public Utente() {
		
	}

	public Utente( String firstName, String lastName, int age, float salary, boolean adult) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.salary = salary;
		this.adult = adult;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + ", age = " + age + ", salary = "
				+ salary + ", adult = " + adult;
	}
	
	
	
}
