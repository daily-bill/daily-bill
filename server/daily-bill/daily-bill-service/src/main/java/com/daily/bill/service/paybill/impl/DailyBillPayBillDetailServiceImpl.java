package com.daily.bill.service.paybill.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.daily.bill.domain.page.Page;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.IngredientPayWeekBill;
import com.daily.bill.domain.paybill.IngredientPayWeekBillDetail;
import com.daily.bill.domain.paybill.UserDuePayBill;
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
import com.google.common.collect.Sets;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:42:49 PM
*/
@Service("dailyBillPayBillDetailService")
public class DailyBillPayBillDetailServiceImpl implements DailyBillPayBillDetailService {

	private static final Logger logger = LoggerFactory.getLogger(DailyBillPayBillDetailServiceImpl.class);
	
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
		if(param.getStatus() == null || !IngredientPayBillDetailStatus.CREATED.equalsValue(param.getStatus())){
			return "缴款单状态参数为空或者不是创建状态";
		}
		if(param.getCreateStartDate() == null || param.getCreateEndDate() == null){
			return "周账单的时间范围不能为空";
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
			query.setIsRelationToPurchase(LogicType.TRUE.getValue());
			if(query.getCreateStartDate() == null || query.getCreateEndDate() == null){ //默认查询当前日期
				Date firstDayOfCurWeek = DateUtils.getStartOfCurrentWeek();
				query.setCreateStartDate(firstDayOfCurWeek.getTime());
				query.setCreateEndDate(DateUtils.add(firstDayOfCurWeek, Calendar.DATE, 7).getTime());
			}
			Map<Integer, Set<User>> userPurchaseMap = Maps.newHashMap();
			Map<Integer, Double> userDuePayMap = Maps.newHashMap();
			List<IngredientPayBillDetail> billDetailList = ingredientPayBillDetailDao.getListByQuery(query);
			if(CollectionUtils.isNotEmpty(billDetailList)){
				for(IngredientPayBillDetail billDetail : billDetailList){
					//周账单参与人
					Set<User> userList = (Set<User>) userPurchaseMap.get(billDetail.getPurchaseId());
					if(CollectionUtils.isEmpty(userList)){
						userList = Sets.newHashSet(dailyBillUserService.getById(billDetail.getUserId()));
						userPurchaseMap.put(billDetail.getPurchaseId(), userList);
					}else{
						userList.add(dailyBillUserService.getById(billDetail.getUserId()));
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
						purchase.setUserList(new ArrayList<User>(userPurchaseMap.get(purchase.getId())));
					}
				}
				payWeekBill.setPurchaseList(purchaseList);
				setUserDuePayList(payWeekBill, userDuePayMap);
			}
			setPreviousPayWeekBill(payWeekBill, new Date(param.getCreateStartDate()));
			setNextPayWeekBill(payWeekBill, new Date(param.getCreateEndDate()));
			return ResultObjectBuilder.successWithCode(payWeekBill);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private void setUserDuePayList(IngredientPayWeekBill payWeekBill, Map<Integer, Double> userDuePayMap) {
		if(userDuePayMap != null && userDuePayMap.size() != 0 ){	
			List<UserDuePayBill> list = Lists.newArrayListWithExpectedSize(userDuePayMap.size());
			Set<Entry<Integer, Double>> entrySet = userDuePayMap.entrySet();
			for(Entry<Integer, Double> entry : entrySet){
				User user = dailyBillUserService.getById(entry.getKey());
				UserDuePayBill userDuePayBill = new UserDuePayBill();
				userDuePayBill.setUserId(user.getId());
				userDuePayBill.setUserName(user.getName());
				userDuePayBill.setDuePay(entry.getValue());
				list.add(userDuePayBill);
			}
			payWeekBill.setUserDuePayList(list);
		}		
	}

	private void setNextPayWeekBill(IngredientPayWeekBill payWeekBill, Date startDate) {
		Purchase lastPurchase = dailyBillPurchaseService.getLastPurchase();
		if(lastPurchase != null){
			Date lastWeekEndDay = DateUtils.add(DateUtils.getStartOfWeekByDate(new Date(lastPurchase.getPayAt())), Calendar.DATE, 6);
			if(startDate.before(lastWeekEndDay)){
				payWeekBill.setHasNext(LogicType.TRUE.getValue());
			}else{
				payWeekBill.setHasNext(LogicType.FALSE.getValue());
			}
		}
	}

	private void setPreviousPayWeekBill(IngredientPayWeekBill payWeekBill, Date endDate) {
		Purchase oldestPurchase = dailyBillPurchaseService.getOldestPurchase();
		if(oldestPurchase != null){
			Date oldestWeekStartDay = DateUtils.getStartOfWeekByDate(new Date(oldestPurchase.getPayAt()));
			if(endDate.after(oldestWeekStartDay)){
				payWeekBill.setHasPrevious(LogicType.TRUE.getValue());
			}else{
				payWeekBill.setHasPrevious(LogicType.FALSE.getValue());
			}
		}	
	}

	private String checkGetPayWeekBillParam(PayBillDetailOperateParam param) {
		if(!(param.getCreateStartDate() == null && param.getCreateEndDate() == null
				||param.getCreateStartDate() != null && param.getCreateEndDate() != null)){
			return "周账单的时间范围不能为空";
		}
		return null;
	}

	@Override
	public ResultObject<Page<IngredientPayBillDetailAmount>> getDetailAmountByWeek(IngredientPayBillDetailQuery query) {
		String message = checkDetailByWeekQuery(query);
		if(StringUtils.isEmpty(message)){
			int pageNumber = query.getPageNumber();
			int pageSize = query.getPageSize();
			query.notNeedPage();
			query.setGroupByType(PayBillDetailGroupByTypeConstants.WEEK);
			List<IngredientPayBillDetailAmount> list = ingredientPayBillDetailDao.getIngredientPayBillDetailAmount(query);
			int totalCount = list.size();
			query.setPageSize(pageSize);
			query.setPageNumber(pageNumber);
			list = ingredientPayBillDetailDao.getIngredientPayBillDetailAmount(query);
			Page<IngredientPayBillDetailAmount> page = new Page<IngredientPayBillDetailAmount>(totalCount, pageNumber, pageSize);
			page.setList(list);
			page.setTotalRow(totalCount);
			return ResultObjectBuilder.successWithCode(page);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private String checkDetailByWeekQuery(IngredientPayBillDetailQuery query) {
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public ResultObject<String> confirmPayBillDetailByParam(PayBillDetailOperateParam param) {
		String message = checkConfirmOperateParam(param);
		if(StringUtils.isEmpty(message)){
			int updateNumber = ingredientPayBillDetailDao.confirmPayBillDetail(param.getUserIds(), param.getCreateStartDate(), param.getCreateEndDate());
			if(updateNumber > 0){
				return ResultObjectBuilder.successWithCode("确认缴款成功");
			}else{
				logger.error("确认缴款失败, param=[userIds=" + param.getUserIds() + ", createStartDate=" + param.getCreateStartDate() + ", createEndDate=" + param.getCreateEndDate() +"]");
				return ResultObjectBuilder.error(ResultCodeConstants.SYSTEM_ERROR, "确认缴款失败");
			}
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private String checkConfirmOperateParam(PayBillDetailOperateParam param) {
		if(CollectionUtils.isEmpty(param.getUserIds())){
			return "用户ID列表为空";
		}
		return null;
	}
	
	
}
