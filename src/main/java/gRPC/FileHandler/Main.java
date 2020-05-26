package gRPC.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args)
    {
        System.out.println("Here we will create Student records:");
        System.out.println("Select the option from below:");
        System.out.println("1. Create a new Student Record");
        System.out.println("2. Enter the record related to a student");
        System.out.println("3. Read records of a student");
        System.out.println("4. Exit");

        Scanner ipt = new Scanner(System.in);

        while (true)
        {
            int choice = Integer.parseInt(ipt.nextLine());

            if (choice == 1) {
                System.out.print("Enter the student name to create record file: ");
                String studentName = ipt.nextLine();
                studentName = studentName.replaceAll("\\s", "");

                CreateFile createNewFile = new CreateFile();
                try {
                    createNewFile.create(studentName+".txt");
                } catch (FileAlreadyExistsException e) {
                    System.out.println("File already Exists");
                } catch (Exception e) {
                    System.out.println("Some error Occured");
                }
            }

            else if (choice == 2) {
                System.out.println("\nReady to enter student details.");
                System.out.print("Enter Student Name: ");
                String studentName = ipt.nextLine();
                System.out.print("Student's Registration Number: ");
                String regNo = ipt.nextLine();
                System.out.print("Course: ");
                String course = ipt.nextLine();

                WriteFile writeToFile = new WriteFile();
                try {
                    if (writeToFile.write(studentName, course, regNo)) {
                        System.out.println("Details saved successfully");
                    }
                } catch (IOException e) {
                    System.out.println("Some Error Occured. Try Again");
                }
            }

            else if (choice == 3) {
                System.out.print("\nType the name of student whose data you want to read: ");
                String studentName = ipt.nextLine();

                ReadFile readStudentFile = new ReadFile();
                try {
                    String studentData = readStudentFile.read(studentName);
                    System.out.println(studentData);
                } catch (FileNotFoundException e) {
                    System.out.println("Student File doesn't exist");
                }
            }

            else
                break;
        }

        ipt.close();
    }
}