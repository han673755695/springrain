// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpcCommonService.proto

package org.springrain.rpc.grpcauto;

public final class GrpcAutoCreateService {
  private GrpcAutoCreateService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommonRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommonRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommonResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommonResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027grpcCommonService.proto\"3\n\rCommonReque" +
      "st\022\021\n\tserialize\030\001 \001(\005\022\017\n\007request\030\002 \001(\014\"\"" +
      "\n\016CommonResponse\022\020\n\010response\030\001 \001(\0142F\n\021Gr" +
      "pcCommonService\0221\n\014commonHandle\022\016.Common" +
      "Request\032\017.CommonResponse\"\000B1\n\026com.mzywx." +
      "rpc.grpcautoB\025GrpcAutoCreateServiceP\001b\006p" +
      "roto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_CommonRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommonRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommonRequest_descriptor,
        new java.lang.String[] { "Serialize", "Request", });
    internal_static_CommonResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CommonResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommonResponse_descriptor,
        new java.lang.String[] { "Response", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
