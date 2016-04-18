package com.daily.bill.domain.paybill;
import java.io.Serializable;
/**
*@author Jin Rongquan
*@version Sun Apr 17 14:14:34 CST 2016
*/
public class IngredientPayBillDetail implements Serializable {
	private Integer id;
	private Integer purchaseId;
	private Integer status;
	private Double duePay;
	private Long createAt;
	private Integer userId;
	private String userName;
	private Long payAt;
	private Double purchaseRealPay;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getDuePay() {
		return duePay;
	}
	public void setDuePay(Double duePay) {
		this.duePay = duePay;
	}
	public Long getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Long createAt) {
		this.createAt = createAt;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getPayAt() {
		return payAt;
	}
	public void setPayAt(Long payAt) {
		this.payAt = payAt;
	}
	public Double getPurchaseRealPay() {
		return purchaseRealPay;
	}
	public void setPurchaseRealPay(Double purchaseRealPay) {
		this.purchaseRealPay = purchaseRealPay;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		IngredientPayBillDetail other = (IngredientPayBillDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}