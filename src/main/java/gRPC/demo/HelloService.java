package gRPC.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.stub.StreamObserver;

public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    private static final Logger logger = Logger.getLogger(HelloService.class.getName());

    @Override
    public void hello(gRPC.demo.HelloRequest request, StreamObserver<gRPC.demo.HelloResponse> responseObserver) {
        
        logger.log(Level.INFO, "Request Received");

        gRPC.demo.HelloResponse response = gRPC.demo.HelloResponse.newBuilder()
                                        .setGreeting("Hello, " + request.getFirstName() + " " + request.getLastName() + ". Welcome!")
                                        .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    
}