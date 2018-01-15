package cn.tedu.store.entity;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable{
	private static final long serialVersionUID = -4052769034220388654L;
	private Integer id;
	private Integer userId;
	private String recvPerson;
	private String recvPhone;
	private String recvDistrict;
	private String recvAddr;
	private String recvAddrCode;
	private Integer price;
	private Integer status;
	private Date orderTime;
	private Integer goodsCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRecvPerson() {
		return recvPerson;
	}
	public void setRecvPerson(String recvPerson) {
		this.recvPerson = recvPerson;
	}
	public String getRecvPhone() {
		return recvPhone;
	}
	public void setRecvPhone(String recvPhone) {
		this.recvPhone = recvPhone;
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
	public String getRecvAddrCode() {
		return recvAddrCode;
	}
	public void setRecvAddrCode(String recvAddrCode) {
		this.recvAddrCode = recvAddrCode;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsCount == null) ? 0 : goodsCount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((recvAddr == null) ? 0 : recvAddr.hashCode());
		result = prime * result + ((recvAddrCode == null) ? 0 : recvAddrCode.hashCode());
		result = prime * result + ((recvDistrict == null) ? 0 : recvDistrict.hashCode());
		result = prime * result + ((recvPerson == null) ? 0 : recvPerson.hashCode());
		result = prime * result + ((recvPhone == null) ? 0 : recvPhone.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (goodsCount == null) {
			if (other.goodsCount != null)
				return false;
		} else if (!goodsCount.equals(other.goodsCount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (recvAddr == null) {
			if (other.recvAddr != null)
				return false;
		} else if (!recvAddr.equals(other.recvAddr))
			return false;
		if (recvAddrCode == null) {
			if (other.recvAddrCode != null)
				return false;
		} else if (!recvAddrCode.equals(other.recvAddrCode))
			return false;
		if (recvDistrict == null) {
			if (other.recvDistrict != null)
				return false;
		} else if (!recvDistrict.equals(other.recvDistrict))
			return false;
		if (recvPerson == null) {
			if (other.recvPerson != null)
				return false;
		} else if (!recvPerson.equals(other.recvPerson))
			return false;
		if (recvPhone == null) {
			if (other.recvPhone != null)
				return false;
		} else if (!recvPhone.equals(other.recvPhone))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", recvPerson=" + recvPerson + ", recvPhone=" + recvPhone
				+ ", recvDistrict=" + recvDistrict + ", recvAddr=" + recvAddr + ", recvAddrCode=" + recvAddrCode
				+ ", price=" + price + ", status=" + status + ", orderTime=" + orderTime + ", goodsCount=" + goodsCount
				+ "]";
	}
	
}
