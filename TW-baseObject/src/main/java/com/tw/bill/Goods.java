package com.tw.bill;

import com.tw.bill.constant.BillConstant.GoodsType;

/**
 * 商品信息类
 * @author DY'sing
 *
 */
public class Goods {

	private String gId;
	private String gName;
	private double gNum;
	private double gPrice;
	private double gSumPrice;
	/**
	 * 折扣名称
	 */
	private String discountName;
	/**
	 * 商品单位（瓶、个、斤等）
	 */
	private GoodsType gType;
	/**
	 * 商品享受到优惠的数量
	 */
	private double gDiscountNum =0.0;
	/**
	 * 在显示打印信息的时候，显示在每行最后的扩展内容
	 * eg:"，节省0.55(元)"
	 */
	private String gExtMessageWhenPrint="";
	
	/**
	 * 本商品的优惠金额
	 */
	private double gDiscount = 0.00;
	public double getgDiscount() {
		return gDiscount;
	}
	public void setgDiscount(double gDiscount) {
		this.gDiscount = gDiscount;
	}
	public Goods(){}
	/**
	 * 商品构造方法
	 * @param gId	商品ID
	 * @param gName	商品名称
	 * @param gNum	商品数量
	 * @param gPrice	商品价格
	 * @param gType 商品单位（瓶、个、斤等）
	 */
	public Goods(String gId, String gName, double gNum, double gPrice,GoodsType gType) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gNum = gNum;
		this.gPrice = gPrice;
		this.gType = gType;
	}

	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public double getgNum() {
		return gNum;
	}
	public void setgNum(double gNum) {
		this.gNum = gNum;
	}
	public double getgPrice() {
		return gPrice;
	}
	public void setgPrice(double gPrice) {
		this.gPrice = gPrice;
	}
	public double getgSumPrice() {
		return gSumPrice;
	}
	public void setgSumPrice(double gSumPrice) {
		this.gSumPrice = gSumPrice;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
	public String getgId() {
		return gId;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getDiscountName() {
		return discountName;
	}
	@Override
	public String toString() {
		return "Goods [gId=" + gId + ", gName=" + gName + ", gNum=" + gNum
				+ ", gPrice=" + gPrice + ", gSumPrice=" + gSumPrice
				+ ", discountName=" + discountName + ", gType=" + gType + "]";
	}
	public GoodsType getgType() {
		return gType;
	}
	public void setgType(GoodsType gType) {
		this.gType = gType;
	}
	public void setgDiscountNum(double gDiscountNum) {
		this.gDiscountNum = gDiscountNum;
	}
	public void setgDiscountNum(int gDiscountNum) {
		this.gDiscountNum = gDiscountNum;
	}
	public double getgDiscountNum() {
		return gDiscountNum;
	}
	public void setgExtMessageWhenPrint(String gExtMessageWhenPrint) {
		this.gExtMessageWhenPrint = gExtMessageWhenPrint;
	}
	public String getgExtMessageWhenPrint() {
		return gExtMessageWhenPrint;
	}
}
