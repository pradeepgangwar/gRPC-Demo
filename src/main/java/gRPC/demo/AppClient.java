package gRPC.demo;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class AppClient {

    private final static Logger logger = Logger.getLogger(AppClient.class.getName());

    private final CreateFileServiceGrpc.CreateFileServiceBlockingStub createFileStub;
    private final WriteToFileServiceGrpc.WriteToFileServiceBlockingStub writeToFileStub;
    private final ReadFileServiceGrpc.ReadFileServiceBlockingStub readFileStub;

    public AppClient (Channel channel) {
        createFileStub = CreateFileServiceGrpc.newBlockingStub(channel);
        writeToFileStub = WriteToFileServiceGrpc.newBlockingStub(channel);
        readFileStub = ReadFileServiceGrpc.newBlockingStub(channel);
    }


    private void createFile(String studentName) {
        gRPC.demo.FileRequest request = gRPC.demo.FileRequest.newBuilder()
                                            .setStudentName(studentName)
                                            .build();
        
        gRPC.demo.FileResponse response;
        try {
            response = createFileStub.createFile(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.log(Level.INFO, "Is File cereated: " + response.getIsSuccessful());
    }

    private void writeToFile(String studentName, String course, String regNo) {
        gRPC.demo.WriteRequest request = gRPC.demo.WriteRequest.newBuilder().setStudentName(studentName)
                                            .setCourse(course)
                                            .setRegNo(regNo)
                                            .build();
        gRPC.demo.WriteResponse response;

        try {
            response = writeToFileStub.writeToFile(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.log(Level.INFO, "Is File written: " + response.getIsSuccessful());
    }

    private void readFile(String studentName) {
        gRPC.demo.ReadRequest request = gRPC.demo.ReadRequest.newBuilder()
                                            .setStudentName(studentName)
                                            .build();

        gRPC.demo.ReadResponse response;

        try {
            response = readFileStub.readFile(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.log(Level.INFO, "Student Data is: \n\n" + response.getStudentData());
    }

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8000")
                                        .usePlaintext()
                                        .build();

        try {
            AppClient client = new AppClient(channel);

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

                    client.createFile(studentName);
                }

                else if (choice == 2) {
                    System.out.println("\nReady to enter student details.");
                    System.out.print("Enter Student Name: ");
                    String studentName = ipt.nextLine();
                    System.out.print("Student's Registration Number: ");
                    String regNo = ipt.nextLine();
                    System.out.print("Course: ");
                    String course = ipt.nextLine();

                    client.writeToFile(studentName, course, regNo);
                }

                else if (choice == 3) {
                    System.out.print("\nType the name of student whose data you want to read: ");
                    String studentName = ipt.nextLine();

                    client.readFile(studentName);
                }

                else
                    break;
            }

            ipt.close();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }

    }
}