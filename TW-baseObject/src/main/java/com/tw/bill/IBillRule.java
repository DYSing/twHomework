package com.tw.bill;

import java.util.List;

public interface IBillRule {

	/**
	 * 设置规则
	 * @param s
	 */
	public void setRuls(String[] s);
	/**
	 * 返回当前规则的名称
	 */
	public String getRuleName();
	/**
	 * 返回当前规则的说明
	 * @return
	 */
	public String getRuleInfo();
	
	/**
	 * 根据商品列表和价格规则计算商品价格并生成账单
	 * @param bill
	 * @param GoodsBillWithPrice 
	 * @param isLastRuls 当执行最后一条优惠规则时，此处为true
	 * @return
	 */
	public GoodsBillWithPrice getPriceByRule(List<Goods> bill,GoodsBillWithPrice gbwp,boolean isLastRuls );
	
	/**
	 * 将商品列表进行拆分，拆成涉及优惠和不涉及优惠两类
	 * @param old				原始商品列表
	 * @param discountGoods		优惠商品列表
	 * @param noDiscountGoods	无优惠商品列表
	 */
	public void letGoodsToTwoListByDiscount(List<Goods> goodsList,List<Goods> discountGoods,List<Goods> noDiscountGoods);
	
	/**
	 * 处理含有优惠的商品价格
	 * @param discountGoodsList
	 * @param gbwp 
	 * @return
	 */
	public void calculationDiscountGoods(List<Goods> discountGoodsList, GoodsBillWithPrice gbwp);
	
	/**
	 * 优惠信息打印内容
	 * eg:
	 * 	买二赠一商品：
	 *	名称：可口可乐，数量：1瓶
	 *	名称：羽毛球，数量：1个
	 * @return
	 */
	public String printDiscountMessage();
	
}
