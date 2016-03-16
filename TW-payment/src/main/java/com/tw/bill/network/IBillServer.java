package com.tw.bill.network;

import java.util.List;

import com.tw.bill.RuleInfo;

public interface IBillServer {

	/**
	 * 根据传入的端口列表，循环初始化监听服务
	 * @param portNum
	 */
	public void ServerInit(List<Integer> portNum);
	/**
	 * 启动服务，并根据传入的优惠规则处理接收到的消息
	 * @param rulesList
	 */
	public void ServerStart(List<RuleInfo> rulesList);

}
