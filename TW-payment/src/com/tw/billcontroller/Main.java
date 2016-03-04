package com.tw.billcontroller;

import java.util.HashMap;
import java.util.List;



import com.tw.bill.Goods;
import com.tw.bill.GoodsBill;
import com.tw.bill.RuleInfo;
import com.tw.bill.util.BillUtil;



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
		//1.2加载优惠规则列表
		List<RuleInfo> rulesList = b.getRulesListBySAX("configs\\DiscountRules.xml");
		//2.获得输入值
		String inputsString = getInputString();
		//2.1解析输入串，生成商品列表对象
		//因为示例重的输入串虽然说是json，不过没有key，还是按照字符串解析吧
		String[] inputGoods = inputsString.replace("[", "").replace("]", "").split(",");
		HashMap<String,Double> inGoodsMap = new HashMap<String,Double>();
		HashMap<String,Goods> outGoodsMap = new HashMap<String,Goods>();
		for (String string : inputGoods) {
			String tempString =string.replaceAll("\'", "");
			String goodsId = "";
			double goodsNum = 0.0;
			if(tempString.indexOf("-") != -1){
				String[] temp =  tempString.split("-");
				 goodsId = temp[0];
				 goodsNum = Double.parseDouble(temp[1]);
			}else{
				goodsId = tempString;
				goodsNum = 1;
			}
			if(inGoodsMap.get(goodsId) != null){
				inGoodsMap.put(goodsId, (inGoodsMap.get(goodsId))+goodsNum);
			}else{
				inGoodsMap.put(goodsId, goodsNum);
			}
		}
		//System.out.println(goodsMap.size());
		for(String key:inGoodsMap.keySet()){
			double goodsNum = inGoodsMap.get(key);
			Goods g = allGoodsMap.get(key);
			g.setgNum(goodsNum);
			outGoodsMap.put(key,g);
		}
		GoodsBill gb = new GoodsBill();
		gb.setGoodsMap(outGoodsMap);
		//3.处理并打印输出
		MyCore mc = new MyCore();
		mc.action(gb , rulesList);

		
	}

	private static String getInputString() {
		// TODO Auto-generated method stub
		return "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001'," +
				"'ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";
	}

}
