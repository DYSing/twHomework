package com.tw.bill.controlle;

import java.util.ArrayList;
import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.IBillRule;

public abstract class ExecutionRules implements IBillRule {
	
	protected List<Goods> noDiscountGoodsList = new ArrayList<Goods>();
	protected List<Goods> discountGoodsList = new ArrayList<Goods>();
	
	
	public GoodsBillWithPrice getPriceByRule(List<Goods> bill,GoodsBillWithPrice gbwp,boolean isLastRuls) {
		if(gbwp==null){
			gbwp = new GoodsBillWithPrice();
		}
		letGoodsToTwoListByDiscount(bill,discountGoodsList,noDiscountGoodsList);
		calculationNoDiscountGoods(noDiscountGoodsList,gbwp,isLastRuls);
		calculationDiscountGoods(discountGoodsList,gbwp);
		return gbwp;
	}


	/**
	 * 处理不含有优惠的商品
	 * @param noDiscountGoodsList
	 * @param goodsBillWithPrice
	 */
	public void calculationNoDiscountGoods(List<Goods> noDiscountGoodsList, GoodsBillWithPrice goodsBillWithPrice,boolean isLastRuls){
		if(isLastRuls){//执行最后一条规则的时候，写入总金额
			double noDiscountTotalAmount = 0;
			for (Goods goods : noDiscountGoodsList) {
				double gPrice = goods.getgPrice();
				double gNum = goods.getgNum();
				double gSumPrice = gPrice*gNum;
				goods.setgSumPrice(gSumPrice);
				noDiscountTotalAmount +=gSumPrice;
			}
		goodsBillWithPrice.addGoodsToNoDiscountGoodsListWithSumPrice(noDiscountGoodsList, noDiscountTotalAmount);
		}else{//不是最后一条规则时不写入总金额，因为下一条规则还会将这个list取出来重新处理
			goodsBillWithPrice.addGoodsToNoDiscountGoodsListWithSumPrice(noDiscountGoodsList, 0);
		}
	}
	


	/**
	 * 判断商品是否享受对应的优惠措施
	 * @param good
	 * @param rule
	 * @return
	 */
	public Boolean isGoodsInRule(Goods good,String[] rule){
		for (String r : rule) {
			return r.equalsIgnoreCase(good.getgId())?true:false;
		}
		return false;
	}



	public List<Goods> getNoDiscountGoodsList() {
		return noDiscountGoodsList;
	}



	public void setNoDiscountGoodsList(List<Goods> noDiscountGoodsList) {
		this.noDiscountGoodsList = noDiscountGoodsList;
	}



	public List<Goods> getDiscountGoodsList() {
		return discountGoodsList;
	}



	public void setDiscountGoodsList(List<Goods> discountGoodsList) {
		this.discountGoodsList = discountGoodsList;
	}

}
class DiscountGoodsTotalAmount{
	public double totalAmount;
	public DiscountGoodsTotalAmount(double totalAmount, double discountAmount) {
		super();
		this.totalAmount = totalAmount;
		this.discountAmount = discountAmount;
	}
	public double discountAmount;
	
}