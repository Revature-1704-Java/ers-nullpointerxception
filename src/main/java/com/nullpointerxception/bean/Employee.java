package com.nullpointerxception.bean;

public class Employee {

	private int employeeId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean isManager;

	public Employee(String username, String password, String firstName, String lastName, boolean isManager) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isManager = isManager;
	}

	public Employee(int employeeId, String username, String firstName, String lastName, boolean isManager) {
		super();
		this.employeeId = employeeId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isManager = isManager;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String toString() {
		return "ID: " + employeeId + " " + "Name: " + firstName + " " + lastName + " " + "Is Manager: " + isManager;
	}

}
