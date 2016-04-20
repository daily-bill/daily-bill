package com.daily.bill.domain.paybill.operate;

import java.util.List;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 9:49:13 AM
*/
public class PayBillDetailOperateParam {
	private Long createStartDate;
	private Long createEndDate;
	private Integer status;
	private List<Integer> userIds;
	public Long getCreateStartDate() {
		return createStartDate;
	}
	public void setCreateStartDate(Long createStartDate) {
		this.createStartDate = createStartDate;
	}
	public Long getCreateEndDate() {
		return createEndDate;
	}
	public void setCreateEndDate(Long createEndDate) {
		this.createEndDate = createEndDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	@Override
	public String toString() {
		return "PayBillDetailOperateParam [createStartDate=" + createStartDate + ", createEndDate=" + createEndDate
				+ ", status=" + status + ", userIds=" + userIds + "]";
	}
	
}
