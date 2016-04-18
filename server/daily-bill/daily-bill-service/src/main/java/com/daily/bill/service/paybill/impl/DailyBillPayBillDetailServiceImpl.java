package com.daily.bill.service.paybill.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.daily.bill.common.constants.PayBillDetailGroupByTypeConstants;
import com.daily.bill.common.enums.IngredientPayBillDetailStatus;
import com.daily.bill.common.enums.LogicType;
import com.daily.bill.common.model.ResultCodeConstants;
import com.daily.bill.common.model.ResultObject;
import com.daily.bill.common.model.ResultObjectBuilder;
import com.daily.bill.common.util.DateUtils;
import com.daily.bill.common.util.MathUtils;
import com.daily.bill.common.util.MoneyCalUtils;
import com.daily.bill.dal.paybill.IngredientPayBillDetailDao;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.IngredientPayWeekBill;
import com.daily.bill.domain.paybill.IngredientPayWeekBillDetail;
import com.daily.bill.domain.paybill.operate.PayBillDetailOperateParam;
import com.daily.bill.domain.paybill.query.IngredientPayBillDetailQuery;
import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.domain.purchase.query.PurchaseQuery;
import com.daily.bill.domain.user.User;
import com.daily.bill.service.paybill.DailyBillPayBillDetailService;
import com.daily.bill.service.purchase.DailyBillPurchaseService;
import com.daily.bill.service.user.DailyBillUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:42:49 PM
*/
@Service("dailyBillPayBillDetailService")
public class DailyBillPayBillDetailServiceImpl implements DailyBillPayBillDetailService {

	@Resource
	private IngredientPayBillDetailDao ingredientPayBillDetailDao;
	
	@Resource
	private DailyBillUserService dailyBillUserService;
	
	@Resource
	private DailyBillPurchaseService dailyBillPurchaseService;
	
	@Override
	public void save(IngredientPayBillDetail detail) {
		ingredientPayBillDetailDao.save(detail);
	}

	@Override
	public ResultObject<List<IngredientPayBillDetailAmount>> getDetailAmountByUserPerWeek(PayBillDetailOperateParam param) {
		String message = checkUserPerWeekOperateParam(param);
		if(StringUtils.isEmpty(message)){
			IngredientPayBillDetailQuery query = new IngredientPayBillDetailQuery();
			query.setGroupByType(PayBillDetailGroupByTypeConstants.USER_PER_WEEK);
			query.setCreateStartDate(param.getCreateStartDate());
			query.setCreateEndDate(param.getCreateEndDate());
			query.setStatus(param.getStatus());
			List<IngredientPayBillDetailAmount> list = ingredientPayBillDetailDao.getIngredientPayBillDetailAmount(query);
			return ResultObjectBuilder.successWithCode(list);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private String checkUserPerWeekOperateParam(PayBillDetailOperateParam param) {
		if(param.getCreateEndDate() == null || param.getCreateStartDate() ==  null){
			return "创建日期范围参数为空";
		}
		if(param.getStatus() == null || !IngredientPayBillDetailStatus.CREATED.equalsValue(param.getStatus())){
			return "缴款单状态参数为空或者不是创建状态";
		}
		return null;
	}

	@Override
	public ResultObject<List<IngredientPayBillDetailAmount>> getDetailAmountByAllUser(PayBillDetailOperateParam param) {
		String message = checkAllUserOperateParam(param);
		if(StringUtils.isEmpty(message)){
			IngredientPayBillDetailQuery query = new IngredientPayBillDetailQuery();
			query.setGroupByType(PayBillDetailGroupByTypeConstants.USER_ALL);
			query.setStatus(param.getStatus());
			List<IngredientPayBillDetailAmount> list = ingredientPayBillDetailDao.getIngredientPayBillDetailAmount(query);
			return ResultObjectBuilder.successWithCode(list);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private String checkAllUserOperateParam(PayBillDetailOperateParam param) {
		if(param.getStatus() == null || !IngredientPayBillDetailStatus.CREATED.equalsValue(param.getStatus())){
			return "缴款单状态参数为空或者不是创建状态";
		}
		return null;
	}

	@Override
	public ResultObject<IngredientPayWeekBill> getPayWeekBill(PayBillDetailOperateParam param) {
		String message = checkGetPayWeekBillParam(param);
		if(StringUtils.isEmpty(message)){
			IngredientPayWeekBill payWeekBill = new IngredientPayWeekBill();
			IngredientPayBillDetailQuery query = new IngredientPayBillDetailQuery();
			query.setCreateStartDate(param.getCreateStartDate());
			query.setCreateEndDate(param.getCreateEndDate());
			if(query.getCreateStartDate() == null || query.getCreateEndDate() == null){ //默认查询当前日期
				Date firstDayOfCurWeek = DateUtils.getStartOfCurrentWeek();
				query.setCreateStartDate(firstDayOfCurWeek.getTime());
				query.setCreateEndDate(DateUtils.add(firstDayOfCurWeek, Calendar.DATE, 7).getTime());
			}
			List<IngredientPayBillDetail> billDetailList = ingredientPayBillDetailDao.getListByQuery(query);
			Map<Integer, List<User>> userPurchaseMap = Maps.newHashMap();
			Map<Integer, Double> userDuePayMap = Maps.newHashMap();
			for(IngredientPayBillDetail billDetail : billDetailList){
				//周账单参与人
				List<User> userList = (List<User>) userPurchaseMap.get(billDetail.getPurchaseId());
				if(CollectionUtils.isEmpty(userList)){
					userList = Lists.newArrayList(dailyBillUserService.getById(billDetail.getUserId()));
					userPurchaseMap.put(billDetail.getPurchaseId(), userList);
				}
				else if(!userList.contains(billDetail)){
					userList.add(dailyBillUserService.getById(billDetail.getId()));
				}		
				//周账单参与人的未缴款额
				if(IngredientPayBillDetailStatus.CREATED.equalsValue(billDetail.getStatus())){
					Double duePay = (Double) userDuePayMap.get(billDetail.getUserId());
					if(duePay == null){
						userDuePayMap.put(billDetail.getUserId(), billDetail.getDuePay());
					}else {
						duePay = MoneyCalUtils.add(duePay, billDetail.getDuePay());
						userDuePayMap.put(billDetail.getUserId(), duePay);
					}
				}	
			}
			PurchaseQuery purchaseQuery = new PurchaseQuery();
			purchaseQuery.setPurchaseIds(Lists.newArrayList(userPurchaseMap.keySet()));
			List<Purchase> purchaseList = dailyBillPurchaseService.getListByQuery(purchaseQuery);
			if(CollectionUtils.isNotEmpty(purchaseList)){
				for(Purchase purchase : purchaseList){
					purchase.setUserList(userPurchaseMap.get(purchase.getId()));
				}
			}
			payWeekBill.setPurchaseList(purchaseList);
			payWeekBill.setUserDuePayMap(userDuePayMap);
			setPreviousPayWeekBill(payWeekBill);
			setNextPayWeekBill(payWeekBill);
			return ResultObjectBuilder.successWithCode(payWeekBill);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private void setNextPayWeekBill(IngredientPayWeekBill payWeekBill) {
		Purchase lastPurchase = dailyBillPurchaseService.getLastPurchase();
		Date lastWeekEndDay = DateUtils.add(DateUtils.getStartOfWeekByDate(new Date(lastPurchase.getPayAt())), Calendar.DATE, 6);
		List<Purchase> purchaseList = Lists.newArrayList();
		Collections.reverse(purchaseList);
		Purchase curPurchase = purchaseList.get(0);
		Date curWeekEndDay = DateUtils.add(DateUtils.getStartOfWeekByDate(new Date(curPurchase.getPayAt())), Calendar.DATE, 6);
		if(curWeekEndDay.before(lastWeekEndDay)){
			payWeekBill.setHasNext(LogicType.TRUE.getValue());
		}else{
			payWeekBill.setHasNext(LogicType.FALSE.getValue());
		}
	}

	private void setPreviousPayWeekBill(IngredientPayWeekBill payWeekBill) {
		Purchase oldestPurchase = dailyBillPurchaseService.getOldestPurchase();
		Date oldestWeekStartDay = DateUtils.getStartOfWeekByDate(new Date(oldestPurchase.getPayAt()));
		List<Purchase> purchaseList = Lists.newArrayList();
		Collections.sort(purchaseList);
		Purchase curPurchase = purchaseList.get(0);
		Date curWeekStartDay = DateUtils.getStartOfWeekByDate(new Date(curPurchase.getPayAt()));
		if(curWeekStartDay.after(oldestWeekStartDay)){
			payWeekBill.setHasPrevious(LogicType.TRUE.getValue());
		}else{
			payWeekBill.setHasPrevious(LogicType.FALSE.getValue());
		}	
	}

	private String checkGetPayWeekBillParam(PayBillDetailOperateParam param) {
		if(!(param.getCreateStartDate() == null && param.getCreateEndDate() == null) 
				|| !(param.getCreateStartDate() != null && param.getCreateEndDate() != null)){
			return "周账单的时间范围不能为空";
		}
		return null;
	}
}
