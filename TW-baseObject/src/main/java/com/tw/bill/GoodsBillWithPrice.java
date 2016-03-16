package com.tw.bill;

import java.util.ArrayList;
import java.util.List;

import com.tw.bill.util.BillUtil;

/**
 * 计算商品价格以后的账单
 * @author DY'sing
 *
 */
public class GoodsBillWithPrice {
	private String discountMessage="";
	/**
	 * 没有优惠的商品包含小计金额的商品列表
	 */
	private List<Goods> noDiscountGoodsListWithSumPrice = new ArrayList<Goods>();
	/**
	 * 包含优惠的商品包含小计金额的商品列表
	 */
	private List<Goods> discountGoodsListWithSumPrice  = new ArrayList<Goods>();
	/**
	 * 总价格
	 */
	private double totalAmount;
	/**
	 * 总优惠金额
	 */
	private double discountTotalAmount;
	/**
	 * 向包含优惠的商品列表里增加优惠信息
	 * @param goods	商品列表
	 * @param total 总金额
	 * @param discount 优惠金额
	 */
	public void addGoodsToDiscountGoodsListWithSumPrice(List<Goods> goods,
			double total, double discount) {
		discountGoodsListWithSumPrice.addAll(goods);
		this.totalAmount += total;
		this.discountTotalAmount += discount;
	}
	/**
	 * 向包含优惠的商品列表里增加优惠信息
	 * @param goods	商品
	 * @param total 总金额
	 * @param discount 优惠金额
	 */
	public void addGoodsToDiscountGoodsListWithSumPrice(Goods goods,
			double total, double discount) {
		discountGoodsListWithSumPrice.add(goods);
		this.totalAmount += total;
		this.discountTotalAmount += discount;
	}
	/**
	 * 向不包含优惠的商品列表里增加商品信息
	 * @param goods	商品列表
	 * @param total 总金额
	 */
	public void addGoodsToNoDiscountGoodsListWithSumPrice(List<Goods> goods,double total){
		noDiscountGoodsListWithSumPrice.addAll(goods);
		this.totalAmount += total;
	}


	/**
	 * 构造商品销售单对象
	 * @param noDiscountGoodsListWithSumPrice	没优惠
	 * @param discountGoodsListWithSumPrice		有优惠
	 * @param totalAmount						总价格
	 * @param discountTotalAmount				优惠价格
	 */
	public GoodsBillWithPrice(List<Goods> noDiscountGoodsListWithSumPrice,
			List<Goods> discountGoodsListWithSumPrice, double totalAmount,
			double discountTotalAmount) {
		super();
		this.noDiscountGoodsListWithSumPrice = noDiscountGoodsListWithSumPrice;
		this.discountGoodsListWithSumPrice = discountGoodsListWithSumPrice;
		this.totalAmount = totalAmount;
		this.discountTotalAmount = discountTotalAmount;
	}

	public GoodsBillWithPrice(){}
	
	public void setGoodsListWithSumPrice(List<Goods> goodsListWithSumPrice) {
		this.noDiscountGoodsListWithSumPrice = goodsListWithSumPrice;
	}

	/**
	 * 不含优惠的商品列表
	 * @return
	 */
	public List<Goods> getNoDiscountGoodsListWithSumPrice() {
		return noDiscountGoodsListWithSumPrice;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setDiscountTotalAmount(double discountTotalAmount) {
		this.discountTotalAmount = discountTotalAmount;
	}

	public double getDiscountTotalAmount() {
		return discountTotalAmount;
	}

	public void setDiscountGoodsListWithSumPrice(
			List<Goods> discountGoodsListWithSumPrice) {
		this.discountGoodsListWithSumPrice = discountGoodsListWithSumPrice;
	}

	public List<Goods> getDiscountGoodsListWithSumPrice() {
		return discountGoodsListWithSumPrice;
	}

	@Override
	public String toString() {
		return "计算商品价格后的商品清单 [不享受优惠的商品数量="
				+ noDiscountGoodsListWithSumPrice.size()
				+ ", 享受优惠的商品数量="
				+ discountGoodsListWithSumPrice.size() + ", 总金额="
				+ totalAmount + ", 总优惠金额=" + discountTotalAmount
				+ "]";
	}
	public void setDiscountMessage(String discountMessage) {
		this.discountMessage += discountMessage;
	}
	public String getDiscountMessage() {
		return discountMessage;
	}
	
	/**
	 * 最后的凭条显示内容
	 */
	public String getPrintMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("***<没钱赚商店>购物清单***\n");
		//先循环显示所有商品列表
		for (Goods goods : this.noDiscountGoodsListWithSumPrice) {
			sb.append("名称：");
			sb.append(goods.getgName());
			sb.append("，数量：");
			sb.append(BillUtil.getStringByDouble(goods.getgNum()));
			sb.append(goods.getgType().getValue());
			sb.append("，单价：");
			sb.append(BillUtil.getStringByDouble(goods.getgPrice()));
			sb.append("（元），小计");
			sb.append(BillUtil.getStringByDouble(goods.getgSumPrice()));
			sb.append("\n");
		}
		for (Goods goods : this.discountGoodsListWithSumPrice) {
			sb.append("名称：");
			sb.append(goods.getgName());
			sb.append("，数量：");
			sb.append(BillUtil.getStringByDouble(goods.getgNum()));
			sb.append(goods.getgType().getValue());
			sb.append("，单价：");
			sb.append(BillUtil.getStringByDouble(goods.getgPrice()));
			sb.append("（元），小计");
			sb.append(BillUtil.getStringByDouble(goods.getgSumPrice()));
			sb.append("（元）");
			sb.append(goods.getgExtMessageWhenPrint()+"\n");
		}
		sb.append("----------------------");
		if(getDiscountMessage() != null && getDiscountMessage().length()>1){
			sb.append(getDiscountMessage());
			sb.append("----------------------\n");
			sb.append("总计：");
			sb.append(BillUtil.getStringByDouble(totalAmount));
			sb.append("（元）\n");
			sb.append("节省：");
			sb.append(BillUtil.getStringByDouble(discountTotalAmount));
			sb.append("（元）");
		}else{
			sb.append("----------------------\n");
			sb.append("总计：");
			sb.append(BillUtil.getStringByDouble(totalAmount));
			sb.append("（元）");
		}
		return sb.toString();
	}

}
