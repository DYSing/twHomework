package com.tw.bill.util;

import java.util.HashMap;
import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.RuleInfo;

public class BillUtil {

	/**
	 * 将浮点数转化成字符串，保留两位小数
	 * @param d
	 * @return
	 */
	public static String getStringByDouble(double d){
		//为了方便维护显示精度、四舍五入算法等，
		//将数值格式化功能单独提出来
		return String.format("%.2f", d);
	}
	/**
	 * 通过SAX获取商品List
	 * @return
	 */
	public HashMap<String,Goods> getGoodsMapBySAX(String uri){
		GoodsSaxParseService sax = new GoodsSaxParseService();
		List<Goods> goodsList = null;
		HashMap<String, Goods> goodsMap= new HashMap<String, Goods>();
		try {
			goodsList = sax.getGoods(uri);
			for(Goods goods : goodsList){
				//System.out.println(goods.toString());
				goodsMap.put(goods.getgId(), goods);//商品条码理论上不能重复，如果重复后面的覆盖前面的
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsMap;
	}
	
	public List<RuleInfo> getRulesListBySAX(String uri){
		RulesSaxParseService sax = new RulesSaxParseService();
		try {
			return sax.getRules(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
