package com.University;

import java.util.*;
import com.University.controller.*;
import com.University.enums.Role;
import com.University.model.*;
import com.University.service.*;
import com.University.util.sessionManager;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthenticationService authService = new AuthenticationService();

        while (true) {
            System.out.println("\n==== WELCOME TO UNIVERSITY PORTAL ====");
            System.out.println("1. Enter the portal");
            System.out.println("2. Exit application");
            System.out.print("\nEnter your choice: ");

            try {
                int mainChoice = Integer.parseInt(sc.nextLine());

                if (mainChoice == 2) {
                    System.out.println("Exiting application...");
                    sc.close();
                    return;
                } else if (mainChoice == 1) {

                    while (true) {
                        System.out.println("\n======== WELCOME TO UNIVERSITY PORTAL ========");
                        System.out.println("1. Register");
                        System.out.println("2. Login");
                        System.out.println("3. Exit");
                        System.out.print("\nEnter your choice: ");

                        int choice = Integer.parseInt(sc.nextLine());
                        user usr = null;

                        switch (choice) {
                            case 1:
                                System.out.println("\n======= Register Menu ========");

                                System.out.print("Enter Name: ");
                                String name = sc.nextLine();

                                System.out.print("Enter Email: ");
                                String email = sc.nextLine();

                                System.out.print("Enter Password: ");
                                String password = sc.nextLine();

                                System.out.print("Enter Role (Student/Professor): ");
                                String role = sc.nextLine();

                                if (role.equalsIgnoreCase("student")) {
                                    usr = new student(name, email, password, role);
                                } else if (role.equalsIgnoreCase("professor")) {
                                    System.out.print("Enter Department: ");
                                    String department = sc.nextLine();
                                    System.out.print("Enter Specialization: ");
                                    String specialization = sc.nextLine();
                                    usr = new Professor(name, email, password, department, specialization);
                                } else {
                                    System.out.println("Invalid role or Admin signup is not allowed.");
                                    break;
                                }

                                authService.register(usr);
                                System.out.println("Registered Successfully!");
                                break;

                            case 2:
                                System.out.println("\n========= Login Menu ========");
                                System.out.println("1. Login as Student");
                                System.out.println("2. Login as Professor");
                                System.out.println("3. Login as Admin");
                                System.out.print("\nEnter choice: ");
                                int loginChoice = Integer.parseInt(sc.nextLine());

                                System.out.print("\nEnter Email: ");
                                String loginEmail = sc.nextLine();
                                System.out.print("Enter Password: ");
                                String loginPassword = sc.nextLine();

                                usr = authService.login(loginEmail, loginPassword);

                                if (usr == null) {
                                    System.out.println("Invalid credentials.");
                                    break;
                                }

                                sessionManager.setCurrentUser(usr);
                                Role roleType = usr.getRole();

                                System.out.printf("\n==== Login successful as %s ====\n", roleType.toString().toLowerCase());
                                System.out.println("====== WELCOME ======");

                                if (loginChoice == 1 && roleType == Role.STUDENT) {
                                    StudentMenu sMenu = new StudentMenu();
                                    while (true) {
                                        sMenu.showStudentMenu();
                                        int sChoice = Integer.parseInt(sc.nextLine());
                                        sMenu.studentChoice(sChoice, (student) usr);
                                        if (sChoice == 8) break;
                                    }
                                } else if (loginChoice == 2 && roleType == Role.PROFESSOR) {
                                    ProfessorMenu pMenu = new ProfessorMenu();
                                    while (true) {
                                        pMenu.showprofessorMenu();
                                        int pChoice = Integer.parseInt(sc.nextLine());
                                        pMenu.ProfessorChoice(pChoice, (Professor) usr);
                                        if (pChoice == 4) break;
                                    }
                                } else if (loginChoice == 3 && roleType == Role.ADMIN) {
                                    AdminMenu aMenu = new AdminMenu();
                                    while (true) {
                                        aMenu.showAdminMenu();
                                        int aChoice = Integer.parseInt(sc.nextLine());
                                        aMenu.adminChoice(aChoice, (Admin) usr);
                                        if (aChoice == 7) break;
                                    }
                                } else {
                                    System.out.println("Invalid login role selected.");
                                }
                                break;

                            case 3:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid choice. Try again.");
                        }

                        if (choice == 3) break;
                    }

                } else {
                    System.out.println("Invalid main menu choice.");
                }

            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
