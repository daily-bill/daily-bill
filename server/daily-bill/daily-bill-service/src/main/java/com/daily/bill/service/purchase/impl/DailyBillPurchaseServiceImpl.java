package com.daily.bill.service.purchase.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daily.bill.common.enums.IngredientPayBillDetailStatus;
import com.daily.bill.common.model.ResultCodeConstants;
import com.daily.bill.common.model.ResultObject;
import com.daily.bill.common.model.ResultObjectBuilder;
import com.daily.bill.common.util.MathUtils;
import com.daily.bill.dal.purchase.PurchaseDao;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.domain.purchase.operate.PurchaseApplyParam;
import com.daily.bill.domain.purchase.query.PurchaseQuery;
import com.daily.bill.domain.user.User;
import com.daily.bill.domain.user.query.UserQuery;
import com.daily.bill.service.paybill.DailyBillPayBillDetailService;
import com.daily.bill.service.purchase.DailyBillPurchaseService;
import com.daily.bill.service.user.DailyBillUserService;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:38:34 PM
*/
@Service("dailyBillPurchaseService")
public class DailyBillPurchaseServiceImpl implements DailyBillPurchaseService {

	@Resource
	private PurchaseDao purchaseDao;
	
	@Resource
	private DailyBillPayBillDetailService dailyBillPayBillDetailService;
	
	@Resource
	private DailyBillUserService dailyBillUserService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public ResultObject<String> addPurchase(PurchaseApplyParam param) {
		String message = checkApplyParam(param);
		if(StringUtils.isEmpty(message)){
			Purchase purchase = new Purchase();
			purchase.setPayAt(param.getPayAt());
			purchase.setTotalRealPay(param.getTotalRealPay());
			purchaseDao.save(purchase);
			purchase = purchaseDao.getLastPurchase();
			
			List<Integer> userIds = param.getUserIds();
			UserQuery userQuery = new UserQuery();
			userQuery.setUserIds(userIds);
			List<User> userList = dailyBillUserService.getListByQuery(userQuery);
			Double duePay = MathUtils.div(param.getTotalRealPay(), Double.valueOf(userIds.size()));
			for(User user : userList){
				IngredientPayBillDetail ingredientPayBillDetail = new IngredientPayBillDetail();
				ingredientPayBillDetail.setCreateAt(param.getPayAt());
				ingredientPayBillDetail.setDuePay(duePay);
				ingredientPayBillDetail.setPurchaseId(purchase.getId());
				ingredientPayBillDetail.setStatus(IngredientPayBillDetailStatus.CREATED.getValue());
				ingredientPayBillDetail.setUserId(user.getId());
				ingredientPayBillDetail.setUserName(user.getName());
				dailyBillPayBillDetailService.save(ingredientPayBillDetail);
			}
			return ResultObjectBuilder.successWithCode(purchase.getId().toString());
		}
		return ResultObjectBuilder.error(ResultCodeConstants.INCORRECT_PARAMETER, message);
	}

	private String checkApplyParam(PurchaseApplyParam param) {
		if(param.getTotalRealPay() == null){
			return "费用合计不能为空";
		}
		if(param.getPayAt() == null){
			return "费用日期不能为空";
		}
		if(CollectionUtils.isEmpty(param.getUserIds())){
			return "参与人不能为空";
		}
		return null;
	}

	@Override
	public List<Purchase> getListByQuery(PurchaseQuery query) {
		return purchaseDao.getListByQuery(query);
	}

	@Override
	public Purchase getLastPurchase() {
		return purchaseDao.getLastPurchase();
	}

	@Override
	public Purchase getOldestPurchase() {
		return purchaseDao.getOldestPurchase();
	}

}
