package com.daily.bill.service.paybill;

import java.util.List;

import com.daily.bill.common.model.ResultObject;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.IngredientPayWeekBill;
import com.daily.bill.domain.paybill.IngredientPayWeekBillDetail;
import com.daily.bill.domain.paybill.operate.PayBillDetailOperateParam;
import com.daily.bill.domain.paybill.query.IngredientPayBillDetailQuery;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:41:10 PM
*/
public interface DailyBillPayBillDetailService {
	public void save(IngredientPayBillDetail detail);
	
	public ResultObject<List<IngredientPayBillDetailAmount>> getDetailAmountByUserPerWeek(PayBillDetailOperateParam param);
	
	public ResultObject<List<IngredientPayBillDetailAmount>> getDetailAmountByAllUser(PayBillDetailOperateParam param);
	
	public ResultObject<IngredientPayWeekBill> getPayWeekBill(PayBillDetailOperateParam param);
}
