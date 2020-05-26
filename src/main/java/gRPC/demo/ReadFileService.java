package gRPC.demo;

import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import gRPC.FileHandler.ReadFile;

public class ReadFileService extends ReadFileServiceGrpc.ReadFileServiceImplBase {
    
    private static final Logger logger = Logger.getLogger(CreateFileService.class.getName());

    @Override
    public void readFile(gRPC.demo.ReadRequest request, io.grpc.stub.StreamObserver<gRPC.demo.ReadResponse> responseObserver) {

        String studentName = request.getStudentName();
        logger.log(Level.INFO, "Received request: Will fetch details for "+studentName);

        ReadFile readFile = new ReadFile();

        gRPC.demo.ReadResponse response = gRPC.demo.ReadResponse.newBuilder().setDataFound(true).setStudentData("").build();

        String studentData = new String();
        try {
            studentData = readFile.read(studentName);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The record of student you requested doesn't exist");
            response =  gRPC.demo.ReadResponse.newBuilder().setDataFound(false).setStudentData("").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        response =  gRPC.demo.ReadResponse.newBuilder().setDataFound(false).setStudentData(studentData).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}