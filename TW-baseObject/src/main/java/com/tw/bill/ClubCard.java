package com.tw.bill;

import com.tw.bill.constant.BillConstant.ClubCardType;

public class ClubCard {

	/**
	 * 会员卡ID
	 */
	private String clubCardID;

	/**
	 * 普通会员卡默认折扣点
	 */
	private double discount = 1;
	/**
	 * 金牌会员默认折扣点
	 */
	private  double goldenDiscount = 1;

	public String getClubCardID() {
		return clubCardID;
	}

	public void setClubCardID(String clubCardID) {
		this.clubCardID = clubCardID;
	}

	public ClubCardType getClubCardType() {
		return clubCardType;
	}

	public void setClubCardType(ClubCardType clubCardType) {
		this.clubCardType = clubCardType;
	}

	private ClubCardType clubCardType;
	
	public ClubCard(){
		
	}
	
	public ClubCard(ClubCardType clubCardType){
		this.clubCardType = clubCardType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getGoldenDiscount() {
		return goldenDiscount;
	}

	public void setGoldenDiscount(double goldenDiscount) {
		this.goldenDiscount = goldenDiscount;
	}
}
