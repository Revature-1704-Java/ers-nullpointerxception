package com.nullpointerxception.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReimbursementTest {
	
	@Test
	public void noReimbursementIdConstructor() {
	Reimbursement r = new Reimbursement(1, 500, 1);
	assertEquals(1, r.getEmployeeId());
	assertEquals(500, r.getExpense());
	assertEquals(1, r.getStatus());
	}
	
	@Test
	public void withReimbursementIdConstructor() {
	Reimbursement r = new Reimbursement(1, 1, 500, 1);
	assertEquals(1, r.getReimbursementId());
	assertEquals(1, r.getEmployeeId());
	assertEquals(500, r.getExpense());
	assertEquals(1, r.getStatus());
	}
}
