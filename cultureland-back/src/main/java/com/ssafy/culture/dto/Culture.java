package com.ssafy.culture.dto;

//문화공간의 정보를 담고있습니다.
public class Culture {

	private long culture_id;
	private String c_category;
	private String content;
	private String address;
	private double lat;
	private double lng;
	private String tel;
	private String url;
	private String time;
	private String pay;
	private String holiday;
	private String img_url;
	private String etc;
	private String intro;
	private String freeYn;
	private String transport;

	public Culture() {
		super();
	}

	public Culture(long culture_id, String c_category, String content, String address, double lat, double lng,
			String tel, String url, String time, String pay, String holiday, String img_url, String etc, String intro,
			String freeYn, String transport) {
		super();
		this.culture_id = culture_id;
		this.c_category = c_category;
		this.content = content;
		this.address = address;
		this.lat = lat;
		this.lng = lng;
		this.tel = tel;
		this.url = url;
		this.time = time;
		this.pay = pay;
		this.holiday = holiday;
		this.img_url = img_url;
		this.etc = etc;
		this.intro = intro;
		this.freeYn = freeYn;
		this.transport = transport;
	}

	public long getCulture_id() {
		return culture_id;
	}

	public void setCulture_id(long culture_id) {
		this.culture_id = culture_id;
	}

	public String getC_category() {
		return c_category;
	}

	public void setC_category(String c_category) {
		this.c_category = c_category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getFreeYn() {
		return freeYn;
	}

	public void setFreeYn(String freeYn) {
		this.freeYn = freeYn;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	@Override
	public String toString() {
		return "Culture [culture_id=" + culture_id + ", c_category=" + c_category + ", content=" + content
				+ ", address=" + address + ", lat=" + lat + ", lng=" + lng + ", tel=" + tel + ", url=" + url + ", time="
				+ time + ", pay=" + pay + ", holiday=" + holiday + ", img_url=" + img_url + ", etc=" + etc + ", intro="
				+ intro + ", freeYn=" + freeYn + ", transport=" + transport + "]";
	}

}
