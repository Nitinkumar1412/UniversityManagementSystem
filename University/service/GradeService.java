package com.University.service;

import java.util.Iterator;
import java.util.List;

import com.University.DTO.GradeDTO;
import com.University.databaseAccess.GradeDAO;
import com.University.enums.GradeType;

public class GradeService 
{
	 private GradeDAO gradeDAO = new GradeDAO();

	    public void assignGrade(int studentId, int courseId, int semester, String grade) 
	    {

	        // Checking grade exists
	        if (gradeDAO.gradeExists(studentId, courseId)) 
	        {
	            gradeDAO.updateGrade(studentId, courseId, grade);
	        } 
	        else 
	        {
	        	// inserting the grade
	            gradeDAO.insertGrade(studentId, courseId, semester, grade);
	        }
	        
	        
	    }
	    
	    public double CalculateCGPA(int studentId) 
	    {
	        List<GradeDTO> allgrades = gradeDAO.fetchGrade(studentId);
	        
	        double totalCGPA = 0;
	        int count = 0;

	        Iterator<GradeDTO> it = allgrades.iterator();

	        while(it.hasNext()) 
	        {
	            GradeDTO temp = it.next();

	            GradeType gradetype = GradeType.valueOf(temp.getGrade());
	            totalCGPA += gradetype.getValue();
	            count++;
	        }

	        double finalCGPA = totalCGPA / count;
	        return finalCGPA;
	    }
	    
	    public double CalculateSGPA(int studentId,int semester) 
	    {
	        List<GradeDTO> allgrades = gradeDAO.fetchGrade(studentId,semester);
	        
	        double totalSGPA = 0;
	        int count = 0;

	        Iterator<GradeDTO> it = allgrades.iterator();

	        while(it.hasNext()) 
	        {
	            GradeDTO temp = it.next();

	            GradeType gradetype = GradeType.valueOf(temp.getGrade());
	            totalSGPA += gradetype.getValue();
	            count++;
	        }

	        double finalSGPA = totalSGPA / count;
	        return finalSGPA;
	    }
}
