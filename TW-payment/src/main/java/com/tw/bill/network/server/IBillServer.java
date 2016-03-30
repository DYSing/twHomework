package com.tw.bill.network.server;

import java.io.IOException;
import java.util.List;


public interface IBillServer {

	/**
	 * 根据传入的端口列表，循环初始化监听服务并启动服务
	 */
	void ServerStart(List<Integer> portNum) throws IOException;

}
