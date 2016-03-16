package com.tw.bill;

import com.tw.bill.constant.BillConstant.ClubCardType;

public class ClubCard {

	private String clubCardID;
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
	
}
