package gRPC.demo;

import java.io.IOException;
import java.util.logging.Level;
import gRPC.FileHandler.WriteFile;
import java.util.logging.Logger;

public class WriteToFileService extends WriteToFileServiceGrpc.WriteToFileServiceImplBase {
    
    private static final Logger logger = Logger.getLogger(CreateFileService.class.getName());

    @Override
    public void writeToFile(gRPC.demo.WriteRequest request, io.grpc.stub.StreamObserver<gRPC.demo.WriteResponse> responseObserver) {
        
        String studentName = request.getStudentName();
        String regNo = request.getRegNo();
        String course = request.getCourse();

        logger.log(Level.INFO, "Received a request: To write student details. Student Name: "+studentName);

        WriteFile writeToFile = new WriteFile();

        gRPC.demo.WriteResponse response = gRPC.demo.WriteResponse.newBuilder()
                                            .setIsSuccessful(true).build();
        
        try {
            writeToFile.write(studentName, course, regNo);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Some Error Occured. Please see the details.");
            e.printStackTrace(System.err);
            response = gRPC.demo.WriteResponse.newBuilder()
                        .setIsSuccessful(true).build();            
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}