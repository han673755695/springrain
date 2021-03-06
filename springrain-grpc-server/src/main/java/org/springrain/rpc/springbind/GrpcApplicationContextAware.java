package org.springrain.rpc.springbind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springrain.rpc.grpcimpl.CommonGrpcService;
import org.springrain.rpc.grpcimpl.GrpcServer;
import org.springrain.rpc.grpcimpl.NoticeGrpcService;

/**
 * 获取applicationContext,启动grpcServer
 * 
 * @author caomei
 *
 */
@Component
public class GrpcApplicationContextAware implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	private static final Logger logger = LoggerFactory.getLogger(GrpcApplicationContextAware.class);

	/**
	 * PRC 服务调用
	 */
	@Bean("commonGrpcService")
	public CommonGrpcService commonGrpcService() {
		return new CommonGrpcService(applicationContext);
	}

	/**
	 * PRC 服务调用
	 */
	@Bean("noticeGrpcService")
	public NoticeGrpcService noticeGrpcService() {
		return new NoticeGrpcService();
	}

	/**
	 * RPC 服务端,启动rpc服务
	 */
	// @Bean
	// @ConditionalOnMissingBean(GrpcServer.class)
	public GrpcServer grpcServer() throws Exception {
		GrpcServer server = new GrpcServer();
		server.addService(commonGrpcService()).addService(noticeGrpcService());
		server.start();
		return server;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.applicationContext = applicationContext;

		if (RpcStaticVariable.isRpcServer) {
			try {
				grpcServer();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

}
