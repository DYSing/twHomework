package com.tw.bill.network;


public class BillServerFactory {

	private static BillUdpServer billUdpServer;
	
	public static BillUdpServer getUdpServer(){
		if(billUdpServer == null){
			billUdpServer = new BillUdpServer();
		}
		return billUdpServer;
	}

	public static IBillServer getTcpServer() {
		return null;
	}

}
