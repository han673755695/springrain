// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: grpcTransactionNoticeService.proto

package org.springrain.rpc.grpcauto;

public final class GrpcAutoCreateTransactionNoticeService {
  private GrpcAutoCreateTransactionNoticeService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NoticeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NoticeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_NoticeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_NoticeResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"grpcTransactionNoticeService.proto\"C\n\r" +
      "NoticeRequest\022\014\n\004txId\030\001 \001(\t\022\021\n\ttxGroupId" +
      "\030\002 \001(\t\022\021\n\toperation\030\003 \001(\005\"1\n\016NoticeRespo" +
      "nse\022\017\n\007message\030\001 \001(\t\022\016\n\006result\030\002 \001(\0052O\n\034" +
      "GrpcTransactionNoticeService\022/\n\nsendNoti" +
      "ce\022\016.NoticeRequest\032\017.NoticeResponse\"\000BB\n" +
      "\026com.mzywx.rpc.grpcautoB&GrpcAutoCreateT" +
      "ransactionNoticeServiceP\001b\006proto3"
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
    internal_static_NoticeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_NoticeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NoticeRequest_descriptor,
        new java.lang.String[] { "TxId", "TxGroupId", "Operation", });
    internal_static_NoticeResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_NoticeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_NoticeResponse_descriptor,
        new java.lang.String[] { "Message", "Result", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
