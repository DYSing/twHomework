package com.tw.billcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.RuleInfo;
import com.tw.bill.constant.BillConstant.AGREEMENT;
import com.tw.bill.network.BillServerFactory;
import com.tw.bill.network.IBillServer;
import com.tw.bill.util.BillUtil;
import com.tw.bill.util.InputMessageHandle;



public class Main {

	/**
	 * 程序从此开始
	 * 通过输入购买的商品列表生成最终的凭条
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BillUtil b = new BillUtil();
		
		//1.初始化-加载配置文件
		//1.1加载商品目录
		HashMap<String,Goods> allGoodsMap =  b.getGoodsMapBySAX("configs\\AllGoods.xml");
		InputMessageHandle.setAllGoodsMap(allGoodsMap);
		//1.2加载优惠规则列表
		List<RuleInfo> rulesList = b.getRulesListBySAX("configs\\DiscountRules.xml");
		//2.获得输入值
		//String inputsString = getInputString();
		//2.1解析输入串，生成商品列表对象
		//GoodsBill gb = InputMessageHandle.getGoodsBill(allGoodsMap, inputsString);
		//3.处理并打印输出
		//MyCore mc = new MyCore();
//		mc.action(gb , rulesList);

		AGREEMENT agreementVersion = AGREEMENT.UDP;//TODO 这里直接指定UDP，下一步从配置文件读取
		
		IBillServer bs;
		switch(agreementVersion){
		case UDP:
			bs= BillServerFactory.getUdpServer();break;
		case TCP:
			bs= BillServerFactory.getTcpServer();break;
		default:
			bs= BillServerFactory.getUdpServer();
		}
			List<Integer> portList = new ArrayList<Integer>();
			//TODO 下一步从配置文件获取监听端口列表
			/*实际上生效的还是一个端口
			 *用列表是如果端口被占用就尝试下一个
			 *客户端拥有相同的列表，初始化客户端的时候逐一尝试
			 */
			portList.add(8081);
			bs.ServerInit(portList);
			bs.ServerStart(rulesList);

	}

	

//	private static String getInputString() {
//		// TODO Auto-generated method stub
//		return "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001'," +
//				"'ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";
//	}

}
