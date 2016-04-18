package com.daily.bill.dal.purchase.test;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import com.daily.bill.common.util.DateUtils;
import com.daily.bill.dal.BaseTest;
import com.daily.bill.dal.purchase.PurchaseDao;
import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.domain.purchase.PurchaseWeekAmount;
import com.daily.bill.domain.purchase.query.PurchaseQuery;

import static org.junit.Assert.*;

import java.util.List;
/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 2:48:56 PM
*/
public class DailyBillPurchaseDaoTest extends BaseTest{

	@Resource
    private PurchaseDao purchaseDao;
	
	private Purchase purchase;
	
	@Before
	public void init(){
		purchase = new Purchase();
		purchase.setPayAt(DateUtils.getDateByFillFields(2016, 3, 18, 15, 0, 0).getTime());
		purchase.setTotalRealPay(100.05D);
	}
	
//	@Test
//	public void testSave(){
//		int insertNumber = purchaseDao.save(purchase);
//		assertEquals(1, insertNumber);
//	}
	
	@Test
	public void testGetPurchaseGroupByWeek(){
		PurchaseQuery query = new PurchaseQuery();
		List<PurchaseWeekAmount> list = purchaseDao.getPurchaseGroupByWeek(query);
		if(CollectionUtils.isNotEmpty(list)){
			for(PurchaseWeekAmount amount: list){
				System.out.println(DateUtils.formatToTimeStr(DateUtils.longToDate(amount.getPayStartDate())) + ", " + DateUtils.formatToTimeStr(DateUtils.longToDate(amount.getPayEndDate())) + ", " + amount.getTotalPay());
			}
		}
	}
	
}
