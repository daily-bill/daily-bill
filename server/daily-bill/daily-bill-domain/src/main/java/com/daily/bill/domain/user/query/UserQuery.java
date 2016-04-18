package com.daily.bill.domain.user.query;

import java.util.List;

import com.daily.bill.domain.page.query.PageQuery;
import com.google.common.collect.Lists;

/**
*@author Jin Rongquan
*@version Sun Apr 17 14:18:22 CST 2016
*/
public class UserQuery extends PageQuery{
	private List<Integer> userIds;

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	
	public void setUserId(Integer userId){
		if(userId != null){
			this.userIds = Lists.newArrayListWithExpectedSize(1);
			this.userIds.add(userId);
		}
	}
}