package com.tw.bill;

import java.util.ArrayList;
import java.util.List;

public class GoodsBillWithPriceList {
	public GoodsBillWithPriceList(){
		goodsBillWithPriceList = new ArrayList<GoodsBillWithPrice>();
	}

	public List<GoodsBillWithPrice> getGoodsBillWithPriceList() {
		return goodsBillWithPriceList;
	}


	public void setGoodsBillWithPriceList(
			List<GoodsBillWithPrice> goodsBillWithPriceList) {
		this.goodsBillWithPriceList = goodsBillWithPriceList;
	}

	private List<GoodsBillWithPrice> goodsBillWithPriceList;


	public void add(GoodsBillWithPrice goodBillWithPrice){
		this.goodsBillWithPriceList.add(goodBillWithPrice);
	}
	
	public String getFinalOutString()
	{
		StringBuffer sb = new StringBuffer();
		for (GoodsBillWithPrice goodBillWithPrice : goodsBillWithPriceList) {
			sb.append(goodBillWithPrice.getPrintMessage());
		}
		return sb.toString();
	}
	
}
