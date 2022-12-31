package com.ssafy.culture.dto;

//특정 아파트의 거래 내역 입니다.
public class AptDeal {
	private String dealNo;
	private String aptCode;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private double area;
	private int floor;

	public AptDeal() {
		super();
	}

	public AptDeal(String dealNo, String aptCode, String dealAmount, int dealYear, int dealMonth, int dealDay,
			double area, int floor) {
		super();
		this.dealNo = dealNo;
		this.aptCode = aptCode;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
	}

	public String getDealNo() {
		return dealNo;
	}

	public void setDealNo(String dealNo) {
		this.dealNo = dealNo;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "AptDeal [dealNo=" + dealNo + ", aptCode=" + aptCode + ", dealAmount=" + dealAmount + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor
				+ "]";
	}

}
