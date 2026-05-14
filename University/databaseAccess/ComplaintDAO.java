package com.University.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.University.model.*;
import com.University.util.DBConnection;

public class ComplaintDAO {
	
	//1. adding complaints by student:
	public boolean addComplaint(int studentId, String description) 
	{

	    try (Connection con = DBConnection.getConnection()) 
	    {

	        String query = "INSERT INTO complaints (student_id, description) VALUES (?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setInt(1, studentId);
	        ps.setString(2, description);

	        int rows = ps.executeUpdate();

	        return rows>0;
	    }
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	    }
	    return false;
	}
	
	//2. for viewing complaints of a particular stduent
	public List<complaint> getComplaintsByStudent(int studentId) 
	{
		
	    List<complaint> list = new ArrayList<>();

	    try (Connection con = DBConnection.getConnection()) {

	        String query = "SELECT * FROM complaints WHERE student_id=?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, studentId);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) 
	        {
	            complaint comp = new complaint();
	            comp.setComplaintId(rs.getInt("complaint_id"));
	            comp.setStudentId(rs.getInt("student_id"));
	            comp.setDescription(rs.getString("description"));
	            comp.setStatus(rs.getString("status"));
	            comp.setResolution(rs.getString("resolution"));

	            list.add(comp);
	        }

	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	//3. Admin: View All Complaints
	public List<complaint> getAllComplaints() {

	    List<complaint> list = new ArrayList<>();

	    try (Connection con = DBConnection.getConnection()) {

	        String query = "SELECT * FROM complaints";
	        PreparedStatement ps = con.prepareStatement(query);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            complaint c = new complaint();
	            c.setComplaintId(rs.getInt("complaint_id"));
	            c.setStudentId(rs.getInt("student_id"));
	            c.setDescription(rs.getString("description"));
	            c.setStatus(rs.getString("status"));
	            c.setResolution(rs.getString("resolution"));
	            list.add(c);
	        }

	    } 
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	    }

	    return list;
	}
	
	// Admin: Resolve Complaint:
	public boolean resolveComplaint(int complaintId, String resolution) 
	{

	    try (Connection con = DBConnection.getConnection()) {

	        String query = "UPDATE complaints SET status='RESOLVED', resolution= ? WHERE complaint_id= ?";
	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, resolution);
	        ps.setInt(2, complaintId);

	        int row = ps.executeUpdate();
	        return row>0;

	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    return false; 
	}
	
}
