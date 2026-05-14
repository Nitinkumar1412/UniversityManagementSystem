package com.University.service;

import java.sql.Connection;
import java.util.List;

import com.University.databaseAccess.ComplaintDAO;
import com.University.model.complaint;

public class ComplaintService 
{
	
	ComplaintDAO complaintdao = new ComplaintDAO();
	public void viewMyComplaints(int studentId) 
	{
		
	    List<complaint> list=null;
		try 
		{
			list = complaintdao.getComplaintsByStudent(studentId);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Table Header
		System.out.printf("+--------------+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------+%n");
		System.out.printf("| %-12s | %-100s | %-100s |%n","Complaint ID","Description","Resolution");
		System.out.printf("+--------------+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------+%n");
			
		
		for (complaint com : list) 
		    {
			System.out.printf("| %-12d | %-100s | %-100s |%n",com.getComplaintId(),com.getDescription(),(com.getResolution()==null?"No Response yet" : com.getResolution()));
		    }
		
		//Table footer:
		System.out.printf("+--------------+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------+%n");
	}
	
	public void filecomplaint(int studentId, String description)
	{
		if(complaintdao.addComplaint(studentId, description))
		{
			System.out.println("Complaint filed Successfully ");
		}
		else
		{
			System.out.println("Student Id/Input type is not correct");
		}
	}
	
	public void resolveComplaint(int studentId, String resolutionText)
	{
		if(complaintdao.resolveComplaint(studentId, resolutionText))
		{
			System.out.println("Resolution saved into record");
		}
		else
		{
			System.out.println("Invalid studentId");
		}
	}
	
	public void viewAllComplaint()
	{
		List<complaint> allComplaint = null;
		try
		{
			allComplaint = complaintdao.getAllComplaints();
			if(allComplaint.isEmpty())
			{
				System.out.println("---Complaint Box is empty ---");
				return;
			}
			System.out.printf("+--------------+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------+%n");
			System.out.printf("| %-12s | %-100s | %-100s |%n","Complaint ID","Description","Resolution");
			System.out.printf("+--------------+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------+%n");
				
			
			for (complaint com : allComplaint) 
			    {
				System.out.printf("| %-12d | %-100s | %-100s |%n",com.getComplaintId(),com.getDescription(),(com.getResolution()==null?"No Response yet" : com.getResolution()));
			    }
			
			//Table footer:
			System.out.printf("+--------------+----------------------------------------------------------------------------------------------------+----------------------------------------------------------------------------------------------------+%n");

					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
