package com.daily.bill.service.purchase;

import java.util.List;

import com.daily.bill.common.model.ResultObject;
import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.domain.purchase.operate.PurchaseApplyParam;
import com.daily.bill.domain.purchase.query.PurchaseQuery;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:04:36 PM
*/
public interface DailyBillPurchaseService {
	public ResultObject<String> addPurchase(PurchaseApplyParam param);
	
	public List<Purchase> getListByQuery(PurchaseQuery query);
	
	public Purchase getLastPurchase();
	
	public Purchase getOldestPurchase();
}
