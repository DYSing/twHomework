package com.tw.bill;
/**
 * 商品信息类
 * @author DY'sing
 *
 */
public class Goods {
	public enum GoodsType{
		/**
		 * 瓶
		 */
        P("瓶"), 
        /**
         * 个
         */
        G("个"),
        /**
         * 斤
         */
        J("斤");
        private final String value;
        GoodsType(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
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
	 * 优惠的数量
	 */
	private int gDiscountNum =0;
	/**
	 * 在显示打印信息的时候，显示在每行最后的扩展内容
	 * eg:"，节省0.55(元)"
	 */
	private String gExtMessageWhenPrint="";
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
	public void setgDiscountNum(int gDiscountNum) {
		this.gDiscountNum = gDiscountNum;
	}
	public int getgDiscountNum() {
		return gDiscountNum;
	}
	public void setgExtMessageWhenPrint(String gExtMessageWhenPrint) {
		this.gExtMessageWhenPrint = gExtMessageWhenPrint;
	}
	public String getgExtMessageWhenPrint() {
		return gExtMessageWhenPrint;
	}
}
