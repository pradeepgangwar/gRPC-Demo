package gRPC.demo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
import gRPC.FileHandler.CreateFile;

public class CreateFileService extends CreateFileServiceGrpc.CreateFileServiceImplBase {

    private static final Logger logger = Logger.getLogger(CreateFileService.class.getName());

    @Override
    public void createFile(gRPC.demo.FileRequest request, io.grpc.stub.StreamObserver<gRPC.demo.FileResponse> responseObserver) {
        
        String studentName = request.getStudentName();
        logger.log(Level.INFO, "Request Received: To create a new student record for " + studentName);
        studentName.replaceAll("\\s", "");
        
        CreateFile createNewFile = new CreateFile();

        gRPC.demo.FileResponse response = gRPC.demo.FileResponse.newBuilder().setIsSuccessful(true).build();
        
        try {
            createNewFile.create(studentName);
        } catch (FileAlreadyExistsException e) {
            logger.log(Level.WARNING, "Student Data file already exists. Please consider inserting new records or update this student.");
            response = gRPC.demo.FileResponse.newBuilder().setIsSuccessful(false).build();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Something went wrong! Please check the following log.");
            e.printStackTrace(System.err);
            response = gRPC.demo.FileResponse.newBuilder().setIsSuccessful(false).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
}