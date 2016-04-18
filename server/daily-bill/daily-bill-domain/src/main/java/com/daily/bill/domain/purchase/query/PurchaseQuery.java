package com.daily.bill.domain.purchase.query;

import java.util.List;

import com.daily.bill.domain.page.query.PageQuery;
import com.google.common.collect.Lists;

/**
*@author Jin Rongquan
*@version Sun Apr 17 14:17:27 CST 2016
*/
public class PurchaseQuery extends PageQuery{
	
	private List<Integer> purchaseIds;

	public List<Integer> getPurchaseIds() {
		return purchaseIds;
	}

	public void setPurchaseIds(List<Integer> purchaseIds) {
		this.purchaseIds = purchaseIds;
	}
	
	public void setPurchaseId(Integer purchaseId){
		if(purchaseId != null){
			this.purchaseIds = Lists.newArrayListWithExpectedSize(1);
			this.purchaseIds.add(purchaseId);
		}
	}
}