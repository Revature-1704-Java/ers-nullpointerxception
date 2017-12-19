package com.nullpointerxception.bean;

public class Reimbursement {

	private int reimbursementId;
	private int employeeId;
	private int expense;
	private int status;

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public Reimbursement(int reimbursementId, int employeeId, int expense, int status) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.expense = expense;
		this.status = status;
	}
	
	public Reimbursement(int employeeId, int expense, int status) {
		super();
		this.employeeId = employeeId;
		this.expense = expense;
		this.status = status;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		return "Reimbursement id: " + reimbursementId + " Employee id: " + employeeId + " expense: " + expense
				+ " status: " + status;
	}

}
