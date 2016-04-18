package com.daily.bill.dal.paybill.test;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import com.daily.bill.common.enums.IngredientPayBillDetailStatus;
import com.daily.bill.common.util.DateUtils;
import com.daily.bill.dal.BaseTest;
import com.daily.bill.dal.paybill.IngredientPayBillDetailDao;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.query.IngredientPayBillDetailQuery;

import static org.junit.Assert.*;

import java.util.List;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:15:42 PM
*/
public class DailyBillIngredientPayBillDetailDaoTest extends BaseTest{

	@Resource
	private IngredientPayBillDetailDao ingredientPayBillDetailDao;
	
	private IngredientPayBillDetail ingredientPayBillDetail;
	
	@Before
	public void init(){
		ingredientPayBillDetail = new IngredientPayBillDetail();
		ingredientPayBillDetail.setCreateAt(DateUtils.getDateByFillFields(2016, 3, 17, 15, 21, 0).getTime());
		ingredientPayBillDetail.setDuePay(100.05D);
		ingredientPayBillDetail.setPurchaseId(1);
		ingredientPayBillDetail.setStatus(IngredientPayBillDetailStatus.CREATED.getValue());
		ingredientPayBillDetail.setUserId(2);
	}
	
//	@Test
//	public void testSave(){
//		int insertNumber = ingredientPayBillDetailDao.save(ingredientPayBillDetail);
//		assertEquals(1, insertNumber);
//	}
	
	@Test
	public void testGetIngredientPayBillDetailAmount(){
		IngredientPayBillDetailQuery query = new IngredientPayBillDetailQuery();
		query.setCreateEndDate(DateUtils.getDateByFillFields(2016, 3, 18, 15, 21, 0).getTime());
		query.setCreateStartDate(DateUtils.getDateByFillFields(2016, 3, 1, 15, 21, 0).getTime());
		query.setStatus(IngredientPayBillDetailStatus.CREATED.getValue());
		List<IngredientPayBillDetailAmount> amountList = ingredientPayBillDetailDao.getIngredientPayBillDetailAmount(query);
		if(CollectionUtils.isNotEmpty(amountList)){
			for(IngredientPayBillDetailAmount amount: amountList){
				System.out.println(amount.getUserId() + ", " + amount.getUserName() + ", " + amount.getTotalDuePay());
			}
		}
	}
}
