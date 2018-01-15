package cn.tedu.store.entity.dict;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = -5310118359041099344L;
	private int id;
	private String provinceCode;
	private String cityCode;
	private String cityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CIty [id=" + id + ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", cityName=" + cityName
				+ "]";
	}

}
