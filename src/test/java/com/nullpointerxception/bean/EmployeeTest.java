package com.nullpointerxception.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EmployeeTest {
	@Test
	public void noEmployeeIdConstructorTest() {
		Employee e = new Employee("username", "password", "Bob", "Joe", true);
		assertEquals("username", e.getUsername());
		assertEquals("password", e.getPassword());
		assertEquals("Bob", e.getFirstName());
		assertEquals("Joe", e.getLastName());
		assertTrue(e.isManager());
	}
	
	@Test
	public void withEmployeeIdConstructorTest() {
		Employee e = new Employee(1, "username", "Bob", "Joe", true);
		assertEquals(1, e.getEmployeeId());
		assertEquals("username", e.getUsername());
		assertEquals("Bob", e.getFirstName());
		assertEquals("Joe", e.getLastName());
		assertTrue(e.isManager());
	}

}
