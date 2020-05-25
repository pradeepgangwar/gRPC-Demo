package gRPC.demo;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;

public class AppClient {

    private final static Logger logger = Logger.getLogger(AppClient.class.getName());

    private final HelloServiceGrpc.HelloServiceBlockingStub stub;

    public AppClient (Channel channel) {
        stub = HelloServiceGrpc.newBlockingStub(channel);
    }


    private void sayHello() {
        gRPC.demo.HelloRequest request = gRPC.demo.HelloRequest.newBuilder().setFirstName("Pradeep")
                                            .setLastName("Gangwar")
                                            .build();
        
        gRPC.demo.HelloResponse response;
        try {
            response = stub.hello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.log(Level.INFO, "Greeting: " + response.getGreeting());
    }

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8000")
                                        .usePlaintext()
                                        .build();

        try {
            AppClient client = new AppClient(channel);
            client.sayHello();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }

    }
}