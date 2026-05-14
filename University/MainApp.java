package com.University;

import java.util.*;

import java.util.Scanner;

import com.University.controller.AdminMenu;
import com.University.controller.ProfessorMenu;
import com.University.controller.StudentMenu;
import com.University.enums.Role;
import com.University.model.*;
import com.University.service.*;
import com.University.util.sessionManager;
import com.mysql.cj.exceptions.ConnectionIsClosedException;

public class MainApp 
{
	
    public static void main(String[] args) 
    {
    	
        Scanner sc = new Scanner(System.in);
        
        while (true) 
        {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");

            try 
            {
            	int choice = Integer.parseInt(sc.nextLine());
            	
            	AuthenticationService authservice = new AuthenticationService();
                switch (choice) 
                {
                
                	
                    case 1:

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        
                        System.out.println("entr the role: ");
                        String role = sc.nextLine();
                        user usr = null;
                        if(role.equalsIgnoreCase("student"))
                        {
                        	usr = new student(name,email,password,role);
                        }
                        else if(role.equalsIgnoreCase("professor"))
                        {
                        	System.out.print("Enter your department: ");
                        	String department = sc.nextLine();
                        	System.out.println("Enter your specialization: ");
                        	String specialization = sc.nextLine();
                        	
                        	usr = new Professor(name,email,password,department,specialization);
                        }
                        else if(role.equalsIgnoreCase("Admin"))
                        {
                        	System.out.println("Admin signup not allowed");
                        	break;
                        }
                        else 
                        {
                        	System.out.println("Invalid role entered.");
                        	break;
						}
                        authservice.register(usr);
                        System.out.println("Registered Successfully!");
                        break;

                    case 2:
                    	System.out.print("\nenter the email Id: ");
                    	email = sc.nextLine();
                    	System.out.print("Enter the Password: ");
                    	password = sc.nextLine();
                    	
                    	authservice = new AuthenticationService();
                    	usr =  authservice.login(email, password);
                    	sessionManager.setCurrentUser(usr);
                    	if(usr!=null)
                    	{
                    		System.out.printf("\n==== login as %s successful =====\n====== WELCOME ======\n",(String.valueOf(usr.getRole())).toLowerCase());
                    	}
                    	else 
                    	{
                    		break;
						}
                    	if((usr.getRole())==Role.STUDENT)
                    	{
                    		StudentMenu menu = new StudentMenu(); 
                    		while(true)
                    		{
                    			menu.showStudentMenu();
                    			int schoice = Integer.parseInt(sc.nextLine());
                    			menu.studentChoice(schoice,(student)usr);
                    			if(schoice==8)
                    			{
                    				break;
                    			}
                    		}
                    	}
                    	else if((usr.getRole())==Role.PROFESSOR)
                    	{
                    		ProfessorMenu menu = new ProfessorMenu();
                    		while(true)
                    		{
                    			menu.showprofessorMenu();
                    			int pchoice = Integer.parseInt(sc.nextLine());
                    			menu.ProfessorChoice(pchoice, (Professor)usr);
                    			if(pchoice==4)
                    			{
                    				break;
                    			}
                    		}
                    	}
                    	else
                    	{
                    		AdminMenu menu = new AdminMenu();
                    		while(true)
                    		{
                    			menu.showAdminMenu();
                    			int Achoice = Integer.parseInt(sc.nextLine());
                    			menu.adminChoice(Achoice, (Admin)usr);
                    			if(Achoice==7)
                    			{
                    				break;
                    			}
                    		} 		
                    		
                    	}
                                        	
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        sc.close();
                        
                        return;

                    default:
                        System.out.println("Invalid choice!\n");
                }

            } 
            catch (Exception ex) 
            {
                System.out.println(ex);
            }
        }
    }
}