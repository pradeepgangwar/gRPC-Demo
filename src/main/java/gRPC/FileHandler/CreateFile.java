package gRPC.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import gRPC.FileHandler.CreateFile;

public class CreateFile {
    
    public boolean create(String fileName) throws FileAlreadyExistsException, IOException
    {
        try {
            File newFile = new File(fileName+".txt");
            if (newFile.createNewFile()) {
                return true;
            } else {
                throw new FileAlreadyExistsException(fileName);
            }
        } catch (FileAlreadyExistsException e) {
            throw new FileAlreadyExistsException(fileName);
        } catch (IOException e) {
            throw new IOException("Some Error Occured");
        }
    }

}