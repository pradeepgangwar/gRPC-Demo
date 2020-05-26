package gRPC.FileHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public boolean write(String studentName, String course, String regNo) throws IOException {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(studentName+".txt")); 
            String studentInfo = "Student Name: " + studentName + "\nReg. No: " + regNo + "\nCourse: " + course + "\n";
            fileWriter.write(studentInfo);
            fileWriter.close();
            return true;
        } 
        catch (IOException e) { 
           throw new IOException("Some Error Occoured"); 
        } 
    }
}
