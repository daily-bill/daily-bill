package com.daily.bill.domain.paybill.query;

import java.util.Date;
import java.util.List;

import com.daily.bill.domain.page.query.PageQuery;

/**
*@author Jin Rongquan
*@version Sun Apr 17 14:14:34 CST 2016
*/
public class IngredientPayBillDetailQuery extends PageQuery{
	private Long createStartDate;
	private Long createEndDate;
	private Integer status;
	private List<Integer> userIds;
	private String groupByType;
	private Integer isRelationToPurchase;
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
	public String getGroupByType() {
		return groupByType;
	}
	public void setGroupByType(String groupByType) {
		this.groupByType = groupByType;
	}
	public Integer getIsRelationToPurchase() {
		return isRelationToPurchase;
	}
	public void setIsRelationToPurchase(Integer isRelationToPurchase) {
		this.isRelationToPurchase = isRelationToPurchase;
	}
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	
}