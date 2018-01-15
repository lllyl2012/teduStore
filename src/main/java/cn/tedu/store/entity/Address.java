package cn.tedu.store.entity;

public class Address {
	private Integer id;
	private Integer uid;
	private String recvPerson; 
	private String recvProvince; 
	private String recvCity;
	private String recvArea;
	private String recvDistrict; 
	private String recvAddr;
	private String recvPhone;
	private String recvTel;
	private String recvDddrCode;
	private String recvName;
	private String isDefault;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getRecvPerson() {
		return recvPerson;
	}
	public void setRecvPerson(String recvPerson) {
		this.recvPerson = recvPerson;
	}
	public String getRecvProvince() {
		return recvProvince;
	}
	public void setRecvProvince(String recvProvince) {
		this.recvProvince = recvProvince;
	}
	public String getRecvCity() {
		return recvCity;
	}
	public void setRecvCity(String recvCity) {
		this.recvCity = recvCity;
	}
	public String getRecvArea() {
		return recvArea;
	}
	public void setRecvArea(String recvArea) {
		this.recvArea = recvArea;
	}
	public String getRecvDistrict() {
		return recvDistrict;
	}
	public void setRecvDistrict(String recvDistrict) {
		this.recvDistrict = recvDistrict;
	}
	public String getRecvAddr() {
		return recvAddr;
	}
	public void setRecvAddr(String recvAddr) {
		this.recvAddr = recvAddr;
	}
	public String getRecvPhone() {
		return recvPhone;
	}
	public void setRecvPhone(String recvPhone) {
		this.recvPhone = recvPhone;
	}
	public String getRecvTel() {
		return recvTel;
	}
	public void setRecvTel(String recvTel) {
		this.recvTel = recvTel;
	}
	public String getRecvDddrCode() {
		return recvDddrCode;
	}
	public void setRecvDddrCode(String recvDddrCode) {
		this.recvDddrCode = recvDddrCode;
	}
	public String getRecvName() {
		return recvName;
	}
	public void setRecvName(String recvName) {
		this.recvName = recvName;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", uid=" + uid + ", recvPerson=" + recvPerson + ", recvProvince=" + recvProvince
				+ ", recvCity=" + recvCity + ", recvArea=" + recvArea + ", recvDistrict=" + recvDistrict + ", recvAddr="
				+ recvAddr + ", recvPhone=" + recvPhone + ", recvTel=" + recvTel + ", recvDddrCode=" + recvDddrCode
				+ ", recvName=" + recvName + ", isDefault=" + isDefault + "]";
	}
	
}
