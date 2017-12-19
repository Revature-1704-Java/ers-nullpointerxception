package com.nullpointerxception.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nullpointerxception.bean.Employee;
import com.nullpointerxception.bean.Reimbursement;
import com.nullpointerxception.util.ConnectionUtil;
import com.nullpointerxception.util.Crud;

public class ReimbursementDAO implements Crud<Reimbursement> {
	
	private static ReimbursementDAO reimbursementDAO;
	
	private ReimbursementDAO() {
		
	}
	
	public static ReimbursementDAO getInstance() {
		if(reimbursementDAO == null) {
			reimbursementDAO = new ReimbursementDAO();
			return reimbursementDAO;
		}else {
			return reimbursementDAO;
		}
	}

	@Override
	public List<Reimbursement> getAll() {
		PreparedStatement ps = null;
		Reimbursement reimbursement = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM REIMBURSEMENT";
			ps = conn.prepareStatement(sql);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("reimbursementid");
				int employeeId = rs.getInt("employeeid");
				int expense = rs.getInt("expense");
				int status = rs.getInt("status");
				
				reimbursement = new Reimbursement(id, employeeId, expense, status);
				reimbursements.add(reimbursement);
				
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return reimbursements;
	}
	
	public List<Reimbursement> getAllByEmployeeId(int id) {
		PreparedStatement ps = null;
		Reimbursement reimbursement = null;
		List<Reimbursement> reimbursements = new ArrayList<>();
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM REIMBURSEMENT WHERE employeeid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("reimbursementid");
				int employeeId = rs.getInt("employeeid");
				int expense = rs.getInt("expense");
				int status = rs.getInt("status");
				
				reimbursement = new Reimbursement(rid, employeeId, expense, status);
				reimbursements.add(reimbursement);
				
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return reimbursements;
	}

	@Override
	public Reimbursement getById(int id) {
		PreparedStatement ps = null;
		Reimbursement reimbursement = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM REIMBURSEMENT WHERE reimbursementid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			//Add any variables to PS
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int reimbursementId = rs.getInt("reimbursementid");
				int employeeId = rs.getInt("employeeid");
				int expense = rs.getInt("expense");
				int status = rs.getInt("status");
				
				reimbursement = new Reimbursement(reimbursementId, employeeId, expense, status);
				
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			ex.getMessage();
		}
		return reimbursement;
	}

	@Override
	public void insert(Reimbursement r) {
		PreparedStatement ps = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "INSERT INTO reimbursement (employeeid, expense, status) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getEmployeeId());
			ps.setInt(2, r.getExpense());
			ps.setInt(3, r.getStatus());
			//Add any variables to PS
			int rows = ps.executeUpdate();
			if(rows == 0) {
				System.out.println("Could not insert.");
			}
			System.out.println("Reimbursement submitted.");
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void updateReimbursement(int id, int status) {
		
		PreparedStatement ps = null;
		ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
		try(Connection conn = connectionUtil.getConnection()){
			String sql = "UPDATE reimbursement SET status=? WHERE reimbursementid=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, id);
			//Add any variables to PS
			int rows = ps.executeUpdate();
			if(rows == 0) {
				System.out.println("Could not insert.");
			}
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	

	

}
