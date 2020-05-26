package gRPC.demo;

import io.grpc.ServerBuilder;
import io.grpc.Server;
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AppServer {

    private static final Logger logger = Logger.getLogger(AppServer.class.getName());
    private Server server;

    private void start() throws IOException {

        int port = 8000;
        server = ServerBuilder.forPort(port)
                    .addService(new CreateFileService())
                    .addService(new WriteToFileService())
                    .addService(new ReadFileService())
                    .build()
                    .start();
        logger.log(Level.INFO, "Server started at port " + port);
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
           
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("Shutting down gRPC server since JVM is shutting down");
                try {
                    AppServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** Server shut down");
            }

        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    
    // This server starts from Command Line
    public static void main(String[] args) throws IOException, InterruptedException {
        
        final AppServer server = new AppServer();
        server.start();
        server.blockUntilShutdown();

    }
}