package gRPC.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    
    public String read(String studentName) throws FileNotFoundException {
        
        try {
            studentName = studentName.replaceAll("\\s", "");
            File studentFile = new File(studentName+".txt");
            Scanner streamData = new Scanner(studentFile);
            String fileData = new String();

            while (streamData.hasNextLine()) {
                fileData = fileData + streamData.nextLine();
                fileData += "\n";
            }
            streamData.close();

            return fileData;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Student File Not Found");
        }
    }
}