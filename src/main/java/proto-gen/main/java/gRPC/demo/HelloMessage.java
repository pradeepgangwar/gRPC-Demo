// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hello_message.proto

package gRPC.demo;

public final class HelloMessage {
  private HelloMessage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_gRPC_demo_HelloRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_gRPC_demo_HelloRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_gRPC_demo_HelloResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_gRPC_demo_HelloResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023hello_message.proto\022\tgRPC.demo\"3\n\014Hell" +
      "oRequest\022\021\n\tfirstName\030\001 \001(\t\022\020\n\010lastName\030" +
      "\002 \001(\t\"!\n\rHelloResponse\022\020\n\010greeting\030\001 \001(\t" +
      "2J\n\014HelloService\022:\n\005hello\022\027.gRPC.demo.He" +
      "lloRequest\032\030.gRPC.demo.HelloResponseB\r\n\t" +
      "gRPC.demoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_gRPC_demo_HelloRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_gRPC_demo_HelloRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_gRPC_demo_HelloRequest_descriptor,
        new java.lang.String[] { "FirstName", "LastName", });
    internal_static_gRPC_demo_HelloResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_gRPC_demo_HelloResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_gRPC_demo_HelloResponse_descriptor,
        new java.lang.String[] { "Greeting", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
