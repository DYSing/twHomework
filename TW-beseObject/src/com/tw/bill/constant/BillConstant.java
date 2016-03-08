package com.tw.bill.constant;

public class BillConstant {
	/**
	 * 商品规格枚举
	 * @author DY'sing
	 *
	 */
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
	
	/**
	 * 协议类型枚举
	 * @author DY'sing
	 *
	 */
	public enum AGREEMENT{
		UDP("UDP"),TCP("TCP");
		private final String value;
		AGREEMENT(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
}
