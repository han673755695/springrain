package org.springrain.rpc.springbind;

public class RemoteRpcTxDto {

	private String rpcHost = null;
	private Integer rpcPort = null;
	private String txId;
	private String txGroupId;
	// 版本的编号,用于处理不同的版本
	private Integer versionCode;

	// 超时时间,超时之后,事务回滚
	private Integer timeout;

	// 事务自动提交,默认true,如果是false就需要等待入口通知提交.
	private Boolean autocommit;

	public String getRpcHost() {
		return rpcHost;
	}

	public Integer getRpcPort() {
		return rpcPort;
	}

	public String getTxId() {
		return txId;
	}

	public String getTxGroupId() {
		return txGroupId;
	}

	public void setRpcHost(String rpcHost) {
		this.rpcHost = rpcHost;
	}

	public void setRpcPort(Integer rpcPort) {
		this.rpcPort = rpcPort;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public void setTxGroupId(String txGroupId) {
		this.txGroupId = txGroupId;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Boolean getAutocommit() {
		return autocommit;
	}

	public void setAutocommit(Boolean autocommit) {
		this.autocommit = autocommit;
	}
}
