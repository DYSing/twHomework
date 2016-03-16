package com.tw.bill.rule;

import java.util.ArrayList;
import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBill;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.controlle.ExecutionRules;

public class BaseBillRule extends ExecutionRules{

	public GoodsBillWithPrice getPriceByRule(GoodsBill bill) {
		List<Goods> noDiscountGoodsList = bill.getGoodsList();
		List<Goods> discountGoodsList = new ArrayList<Goods>();
		double totalAmount = 0;
		for (Goods goods : noDiscountGoodsList) {
			double gPrice = goods.getgPrice();
			double gNum = goods.getgNum();
			double gSumPrice = gPrice*gNum;
			goods.setgSumPrice(gSumPrice);
			totalAmount +=gSumPrice;
		}
		GoodsBillWithPrice gbwp = new GoodsBillWithPrice(noDiscountGoodsList,discountGoodsList,totalAmount,0);
		return gbwp;
	}

	public String getRuleInfo() {
		return "根据单价*数量计算，不考虑优惠";
	}
	public String getRuleName() {
		return "基本价格规则";
	}

	public void letGoodsToTwoListByDiscount(GoodsBill old,
			List<Goods> discountGoods, List<Goods> noDiscountGoods) {
		//本规则不涉及优惠，故不需实现此方法		
	}

	public void calculationDiscountGoods(List<Goods> discountGoodsList,
			GoodsBillWithPrice gbwp) {
		//本规则不涉及优惠，故不需实现此方法	
	}

	public String printDiscountMessage() {
		return null;
	}

	public void letGoodsToTwoListByDiscount(List<Goods> old,
			List<Goods> discountGoods, List<Goods> noDiscountGoods) {
		
	}

	public void setRuls(String[] s) {
		
	}

}
