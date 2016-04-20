package com.daily.bill.service.paybill.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.daily.bill.common.enums.IngredientPayBillDetailStatus;
import com.daily.bill.common.model.ResultObject;
import com.daily.bill.common.util.DateUtils;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.IngredientPayWeekBill;
import com.daily.bill.domain.paybill.operate.PayBillDetailOperateParam;
import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.service.BaseTest;
import com.daily.bill.service.paybill.DailyBillPayBillDetailService;

/**
*@Author Jin Rongquan
*@Version Apr 19, 2016 9:54:45 AM
*/
public class DailyBillPayBillServiceTest extends BaseTest{
	
	@Resource
	private DailyBillPayBillDetailService dailyBillPayBillDetailService;
	
	private PayBillDetailOperateParam param;
	
	private IngredientPayBillDetail detail;
	
	@Before
	public void init(){
		param = new PayBillDetailOperateParam();
		param.setCreateStartDate(DateUtils.getDateByFillFields(2016, 3, 1, 0, 0, 0).getTime());
		param.setCreateEndDate(DateUtils.getDateByFillFields(2016, 3, 19, 0, 0, 0).getTime());
		param.setStatus(IngredientPayBillDetailStatus.CREATED.getValue());
		
	
		detail = new IngredientPayBillDetail();
		detail.setCreateAt(DateUtils.getDateByFillFields(2016, 3, 18, 0, 0, 0).getTime());
		detail.setDuePay(20.16);
		detail.setPurchaseId(1);
		detail.setStatus(IngredientPayBillDetailStatus.CREATED.getValue());
		detail.setUserId(1);
	}
	
	@Test
	public void testGetDetailAmountByUserPerWeek(){
		ResultObject<List<IngredientPayBillDetailAmount>> result = dailyBillPayBillDetailService.getDetailAmountByUserPerWeek(param);
		if(result.isSuccess()){
			List<IngredientPayBillDetailAmount> list = result.getData();
			for(IngredientPayBillDetailAmount d : list){
				System.out.println(d);
			}
		}else{
			System.out.println(result.getMessage());
		}
	}
	
//	@Test
//	public void testSave(){
//		dailyBillPayBillDetailService.save(detail);
//	}
	
//	@Test
//	public void testGetDetailAmountByAllUser(){
//		ResultObject<List<IngredientPayBillDetailAmount>> result = dailyBillPayBillDetailService.getDetailAmountByAllUser(param);
//		if(result.isSuccess()){
//			List<IngredientPayBillDetailAmount> list = result.getData();
//			System.out.println(list);
//		}else{
//			System.out.println(result.getMessage());
//		}		
//	}
	
//	@Test
//	public void testPayWeekBill(){
//		ResultObject<IngredientPayWeekBill> result = dailyBillPayBillDetailService.getPayWeekBill(param);
//		if(result.isSuccess()){
//			IngredientPayWeekBill bill = result.getData();
//			List<Purchase> list = bill.getPurchaseList();
////			Map<Integer, Double> map = bill.getUserDuePayMap();
//			System.out.println(bill.getHasNext());
//			System.out.println(bill.getHasPrevious());
//			System.out.println(list);
////			System.out.println(map.keySet());
////			System.out.println(map.values());
//		}else{
//			System.out.println(result.getMessage());
//		}
//	}
	
}
