package com.University.service;

import java.util.List;

import com.University.databaseAccess.CourseDAO;
import com.University.model.Professor;
import com.University.model.course;
import com.University.model.student;

public class CourseService {

    private CourseDAO courseDAO = new CourseDAO();

    //Get all courses
    public List<course> getAllCourses() 
    {
        return courseDAO.getAllCourses();
    }

    //Get courses by semester
    public List<course> getCoursesBySemester(int semester) 
    {
        return courseDAO.getCoursesBySemester(semester);
    }

    public List<course> getCoursesForStudent(student student) 
    {

        int semester = student.getSemester();
        
        return courseDAO.getCoursesBySemester(semester);
    }
    
    public List<course> getCoursesForProfessor(Professor prof) 
    {

        return courseDAO.viewCourseAsProfId(prof);
    }
    
    // Fetches the data and prints it as a formatted table
    public void displayEnrolledCoursesForStudent(int studentId) {
        List<course> courses = courseDAO.getEnrolledCoursesForStudent(studentId);
        
        if (courses.isEmpty()) {
            System.out.println("\n[!] This student is not currently registered for any courses.");
            return;
        }
        
        System.out.println("\n--- Registered Courses for Student ID: " + studentId + " ---");
        System.out.printf("| %-15s | %-40s | %-10s | %-20s |%n", "COURSE CODE", "TITLE", "SEMESTER", "PROFESSOR");
        System.out.println("+-----------------+------------------------------------------+------------+----------------------+");
        for (course c : courses) {
            System.out.printf("| %-15s | %-40s | %-10d | %-20s |%n", 
                c.getId(), c.getCourseName(), c.getSemester(), c.getProfessor());
        }
        System.out.println("+-----------------+------------------------------------------+------------+----------------------+\n");
    }
    
}
