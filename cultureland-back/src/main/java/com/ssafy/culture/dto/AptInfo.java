package com.ssafy.culture.dto;

//아파트의 대략적인 정보와 위치 정보를 반환합니다.
public class AptInfo {

	private long aptCode;
	private String aptName;
	private double lat, lng;
	private int buildYear;
	private int dealCnt;
	private double avgDealAmount;

	public AptInfo() {
		super();
	}

	public AptInfo(long aptCode, String aptName, double lat, double lng, int buildYear, int dealCnt,
			double avgDealAmount) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.lat = lat;
		this.lng = lng;
		this.buildYear = buildYear;
		this.dealCnt = dealCnt;
		this.avgDealAmount = avgDealAmount;
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public int getDealCnt() {
		return dealCnt;
	}

	public void setDealCnt(int dealCnt) {
		this.dealCnt = dealCnt;
	}

	public double getAvgDealAmount() {
		return avgDealAmount;
	}

	public void setAvgDealAmount(double avgDealAmount) {
		this.avgDealAmount = avgDealAmount;
	}

	@Override
	public String toString() {
		return "AptInfo [aptCode=" + aptCode + ", aptName=" + aptName + ", lat=" + lat + ", lng=" + lng + ", buildYear="
				+ buildYear + ", dealCnt=" + dealCnt + ", avgDealAmount=" + avgDealAmount + "]";
	}
}
