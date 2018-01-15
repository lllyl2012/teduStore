package cn.tedu.store.entity.dict;

import java.io.Serializable;

public class Province implements Serializable {

	private static final long serialVersionUID = -2488787490413553454L;
	private int id;
	private String provinceCode;
	private String provinceName;
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
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName + "]";
	}



}
