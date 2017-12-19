package com.nullpointerxception;

import java.util.ArrayList;
import java.util.Scanner;

import com.nullpointerxception.bean.Employee;
import com.nullpointerxception.bean.Reimbursement;
import com.nullpointerxception.doa.EmployeeDAO;
import com.nullpointerxception.doa.ReimbursementDAO;

public class App {

	static Scanner scanner;
	static EmployeeDAO employeeDAO;
	static ReimbursementDAO reimbursementDAO;
	static Employee currentEmployee;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scanner = new Scanner(System.in);
		employeeDAO = EmployeeDAO.getInstance();
		reimbursementDAO = ReimbursementDAO.getInstance();
		
		logInOrSignUp();
		isManagerCheck();
		
	}
	
	static void logInOrSignUp() {
		
		System.out.println("1. Log In\n2. Sign up");
		while(true) {
			String i = scanner.nextLine();
			if(i.equals("1")) {
				logIn();
				return;
			}else if(i.equals("2")) {
				signUp();
				return;
			}else {
				System.out.println("Invalid Option.\n1.Log In\\n2. Sign up");
			}
		}
		
	}
	
	static void logIn() {
		while(true) {
			System.out.println("Enter Username");
			String username = scanner.nextLine();
			
			System.out.println("Enter Password");
			String password = scanner.nextLine();
			
			Employee e = employeeDAO.getByUsernamePassword(username, password);
			if(e != null) {
				currentEmployee = e;
				return;
			}else {
				System.out.println("No record with the username and password. Please try again.");
			}
		}
		
	}
	
	static void signUp() {
		while(true) {
			System.out.println("Enter Username");
			String username = scanner.nextLine();
			System.out.println("Enter Password");
			String password = scanner.nextLine();
			System.out.println("Enter First Name");
			String firstName = scanner.nextLine();
			System.out.println("Enter Last Name");
			String lastName = scanner.nextLine();
			System.out.println("Are you a manager? Y/N");
			String input = scanner.nextLine();
			boolean isManager;
			if(input.toUpperCase().equals("Y") || input.toUpperCase().equals("N")) {
				isManager = input.toUpperCase().equals("Y") ? true : false;
				
			}else {
				System.out.println("Invalid option. Please try again.");
				continue;
			}
			
			Employee newEmployee = new Employee(username, password, firstName, lastName, isManager);
			
			if(!employeeDAO.exists(newEmployee.getUsername())) {
				employeeDAO.insert(newEmployee);
				currentEmployee = employeeDAO.getByUsernamePassword(newEmployee.getUsername(), newEmployee.getPassword());
				System.out.println("Success!");
				return;
			}else {
				System.out.println("User already exist. Please try again.");
			}
		}
	}
	
	static void isManagerCheck() {
		while(true) {
			if(!currentEmployee.isManager()) {
				employeeView();
				return;
			}
			
			System.out.println("What view would you like to use?\n1. Employee\n2. Manager");
			String i = scanner.nextLine();
			if(i.equals("1")) {
				employeeView();
				return;
			}else if(i.equals("2")) {
				managerView();
				return;
			}else {
				System.out.println("Invalid option. Please try again.");
			}
			
		}
	}
	
	static void employeeView() {
		while(true) {
			System.out.println("What would you like to do?\n1. New Reimbursement Request\n2. View Status of Requests");
			String i = scanner.next();
			if(i.equals("1")) {
				while(true) {
					System.out.println("Enter amount: ");
					int amount = scanner.nextInt();
					if(amount < 0) {
						System.out.println("Amount has to be greater than 0");
						continue;
					}else {
						if(currentEmployee.isManager()) {
							Reimbursement r = new Reimbursement(currentEmployee.getEmployeeId(), amount, "APPROVED");
							reimbursementDAO.insert(r);
						}else {
							Reimbursement r = new Reimbursement(currentEmployee.getEmployeeId(), amount, "PENDING");
							reimbursementDAO.insert(r);
						}
						break;
					}
				}
			}else if(i.equals("2")) {
				ArrayList<Reimbursement> reimbursements = (ArrayList<Reimbursement>) reimbursementDAO.getAllByEmployeeId(currentEmployee.getEmployeeId());
				for(int count = 0; count < reimbursements.size(); count++) {
					System.out.println("ID: " + reimbursements.get(count).getReimbursementId() + " Amount: $" + reimbursements.get(count).getExpense() + " Status: " + reimbursements.get(count).getStatus());
				}
				continue;
			}else {
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	static void managerView() {
		
		while(true) {
			ArrayList<Reimbursement> reimbursements = (ArrayList<Reimbursement>) reimbursementDAO.getAll();
			for(int count = 0; count < reimbursements.size(); count++) {
				System.out.println("ID: " + reimbursements.get(count).getReimbursementId() + " Employee ID: " + reimbursements.get(count).getEmployeeId() + " Amount: $" + reimbursements.get(count).getExpense() + " Status: " + reimbursements.get(count).getStatus());
			}
			System.out.println("Select the ID of the reimbursement you want to approve or deny:");
			int input = scanner.nextInt();
			for (int i = 0; i < reimbursements.size(); i++) {
				if(input == reimbursements.get(i).getReimbursementId()) {
					while(true) {
						System.out.println("1. Approve\n2. Deny\n3. Go Back");
						
						String in = scanner.next();
						if(in.equals("1")) {
							reimbursementDAO.updateReimbursement(input, "APPROVED");
							System.out.println("Success! Reimbursement ID: " +  reimbursements.get(i).getReimbursementId() + " approved.");
							break;
						}else if(in.equals("2")) {
							reimbursementDAO.updateReimbursement(input, "DENIED");
							System.out.println("Success! Reimbursement ID: " +  reimbursements.get(i).getReimbursementId() + " denied.");
							break;
						}else if(in.equals("3")) {
							break;
						}else {
							System.out.println("Invalid option. Try again.");
						}
						
					}
				}
			}
		}
	}

}
