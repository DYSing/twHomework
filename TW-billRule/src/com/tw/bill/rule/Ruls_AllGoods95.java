package com.tw.bill.rule;

import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.controlle.ExecutionRules;
import com.tw.bill.util.BillUtil;

public class Ruls_AllGoods95 extends ExecutionRules {
	public String[] MYRULS ={};
	public void setRuls(String[] s) {
		this.MYRULS = s;
	}
	public String getRuleName() {
		return "95折";
	}
	public String getRuleInfo() {
		return "“95折”是指，在计算小计的时候按单价的95%计算每个商品。";
	}

	public void letGoodsToTwoListByDiscount(List<Goods> goodsList,
			List<Goods> discountGoods, List<Goods> noDiscountGoods) {
		if(MYRULS.length==0){
			//没有规则时，全部进入noDiscountGoods
			noDiscountGoods.addAll(goodsList);
			return;
		}
		for (Goods goods : goodsList) {
			Boolean getIt = false;
			for (String r : MYRULS) {
				//当ID在规则范围内
				if((goods.getgId()).equalsIgnoreCase(r)){
					discountGoods.add(goods);
					getIt = true;
					break;
				}
			}
			if(!getIt){
				noDiscountGoods.add(goods);
			}
		}
	}

	public void calculationDiscountGoods(List<Goods> discountGoodsList,
			GoodsBillWithPrice gbwp) {
		double totalAmount = 0;
		for (Goods goods : discountGoodsList) {
			double gPrice = goods.getgPrice();
			double gNum = goods.getgNum();
			double truePrice = gPrice*gNum;//未优惠金额
			double gSumPrice = truePrice*0.95;//95折
			double gDiscount = truePrice-gSumPrice;
			goods.setgSumPrice(gSumPrice);
			totalAmount +=gSumPrice;
			gbwp.addGoodsToDiscountGoodsListWithSumPrice(goods, gSumPrice, gDiscount);
			goods.setgExtMessageWhenPrint("，节省"+BillUtil.getStringByDouble(gDiscount)+"(元)");
		}
	}

	public String printDiscountMessage() {
		return null;
	}

}
