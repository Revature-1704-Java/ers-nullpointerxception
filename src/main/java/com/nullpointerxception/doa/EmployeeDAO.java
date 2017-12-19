package com.nullpointerxception.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nullpointerxception.bean.Employee;
import com.nullpointerxception.util.ConnectionUtil;
import com.nullpointerxception.util.Crud;

public class EmployeeDAO implements Crud<Employee>{
	
private static EmployeeDAO employeeDAO;
	
	private EmployeeDAO() {
		
	}
	
	public static EmployeeDAO getInstance() {
		if(employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
			return employeeDAO;
		}else {
			return employeeDAO;
		}
	}

	public List<Employee> getAll() {
		PreparedStatement ps = null;
		Employee e = null;
		List<Employee> employees = new ArrayList<>();
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM EMPLOYEE";
			ps = conn.prepareStatement(sql);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("employeeid");
				String username = rs.getString("username");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				boolean isManager = rs.getBoolean("ismanager");
				
				e = new Employee(id,username,firstName,lastName,isManager);
				employees.add(e);
				
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return employees;
	}

	public Employee getById(int id) {
		PreparedStatement ps = null;
		Employee e = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM EMPLOYEE WHERE employeeid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int employeeid = rs.getInt("employeeid");
				String username = rs.getString("username");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				boolean isManager = rs.getBoolean("ismanager");
				
				e = new Employee(id,username,firstName,lastName,isManager);
				
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.getMessage();
		}
		
		
		return e;
	}
	
	public void insert(Employee e) {
		PreparedStatement ps = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "INSERT INTO employee ( username, password, firstname, lastname, ismanager) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			if(e.isManager()) {
				ps.setString(5, "1");
			}else {
				ps.setString(5, "0");
			}
			//Add any variables to PS
			int rows = ps.executeUpdate();
			if(rows == 0) {
				System.out.println("Could not insert.");
			}
			
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();;
		}
		
	}
	
	public Employee getByUsernamePassword(String eusername, String password) {
		PreparedStatement ps = null;
		Employee e = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM EMPLOYEE WHERE username=? AND password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, eusername);
			ps.setString(2, password);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int employeeid = rs.getInt("employeeid");
				String username = rs.getString("username");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				boolean isManager = rs.getBoolean("ismanager");
				
				e = new Employee(employeeid,username,firstName,lastName,isManager);
				
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.getMessage();
		}
		
		
		return e;
	}
	
	public boolean exists(String username) {
		PreparedStatement ps = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM EMPLOYEE WHERE username=?";
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, username);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			rs.last();
			int count = rs.getRow();
			
			rs.close();
			ps.close();
			
			if(count > 0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
	
	

}
