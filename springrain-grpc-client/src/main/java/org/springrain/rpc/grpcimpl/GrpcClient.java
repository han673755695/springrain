package org.springrain.rpc.grpcimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.rpc.grpcauto.CommonRequest;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc;
import org.springrain.rpc.grpcauto.GrpcTransactionNoticeServiceGrpc;
import org.springrain.rpc.grpcauto.NoticeRequest;
import org.springrain.rpc.grpcauto.NoticeResponse;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc.GrpcCommonServiceBlockingStub;
import org.springrain.rpc.grpcauto.GrpcTransactionNoticeServiceGrpc.GrpcTransactionNoticeServiceBlockingStub;
import org.springrain.rpc.util.FstSerializeUtils;

import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Grpc的客户端
 * 
 * @author caomei
 *
 */

public class GrpcClient {
	private static final Logger logger = LoggerFactory.getLogger(GrpcClient.class);

	/**
	 * 处理 gRPC 请求
	 */
	public static GrpcCommonResponse commonHandle(GrpcCommonServiceBlockingStub blockingStub, GrpcCommonRequest grpcRequest) {

		ByteString bytes = FstSerializeUtils.serialize(grpcRequest);

		CommonRequest request = CommonRequest.newBuilder().setRequest(bytes).build();
		CommonResponse response = null;
		try {
			response = blockingStub.commonHandle(request);

			GrpcCommonResponse grpcResponse = FstSerializeUtils.deserialize(response);
			return grpcResponse;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// response = getBlockingStub(rpcHost, rpcPort).handle(request);
			return null;
		}

	}

	/**
	 * 获取grpc连接
	 * 
	 * @param rpcHost
	 * @param rpcPort
	 * @return
	 */
	public static GrpcCommonServiceBlockingStub getCommonServiceBlockingStub(String rpcHost, Integer rpcPort) {
		if (rpcHost == null || "".equals(rpcHost) || rpcPort == null || rpcPort <= 0) {
			return null;
		}

		ManagedChannel channel = ManagedChannelBuilder.forAddress(rpcHost, rpcPort).usePlaintext().build();
		GrpcCommonServiceBlockingStub blockingStub = GrpcCommonServiceGrpc.newBlockingStub(channel);

		// blockingStub.withDeadlineAfter(100, TimeUnit.MILLISECONDS);

		return blockingStub;

	}

	/**
	 * 获取grpc连接
	 * 
	 * @param rpcHost
	 * @param rpcPort
	 * @return
	 */
	public static GrpcTransactionNoticeServiceBlockingStub getTransactionNoticeBlockingStub(String rpcHost,
			Integer rpcPort) {
		if (rpcHost == null || "".equals(rpcHost) || rpcPort == null || rpcPort <= 0) {
			return null;
		}

		ManagedChannel channel = ManagedChannelBuilder.forAddress(rpcHost, rpcPort).usePlaintext().build();
		GrpcTransactionNoticeServiceBlockingStub stub = GrpcTransactionNoticeServiceGrpc.newBlockingStub(channel);

		// stub.withDeadlineAfter(100, TimeUnit.MILLISECONDS);

		return stub;

	}

	/**
	 * 处理 gRPC 请求
	 */
	public static NoticeResponse sendNotice(GrpcTransactionNoticeServiceBlockingStub stub,
			NoticeRequest noticeRequest) {
		try {

			NoticeResponse response = stub.sendNotice(noticeRequest);

			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}

	}

}
