package com.tw.bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 未计算价格的商品列表
 * @author DY'sing
 *
 */
public class GoodsBill {

	private HashMap<String,Goods> goodsMap;

	public GoodsBill(){}
	
	/**
	 * 通过商品ID获取商品信息
	 * @param name
	 * @return
	 */
	public Goods getGoodsByGoodsID(String id){
		return goodsMap.get(id);
	}

	
	public List<Goods> getGoodsList(){
		List<Goods> goodsList = new ArrayList<Goods>();
		goodsList.addAll(goodsMap.values());
		return goodsList;
	}
	public void setGoodsMap(HashMap<String,Goods> goodsMap) {
		this.goodsMap = goodsMap;
	}

	public HashMap<String,Goods> getGoodsMap() {
		return goodsMap;
	}
}
