package com.daily.bill.dal.purchase;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.domain.purchase.PurchaseWeekAmount;
import com.daily.bill.domain.purchase.query.PurchaseQuery;
/**
*@author Jin Rongquan
*@version Sun Apr 17 14:17:44 CST 2016
*/
@Repository
public interface PurchaseDao {

	public int save(Purchase purchase);

	public List<Purchase> getListByQuery(PurchaseQuery query);

	public int getNumberByQuery(PurchaseQuery query);
	
	public Purchase getLastPurchase();
	
	public List<PurchaseWeekAmount> getPurchaseGroupByWeek(PurchaseQuery query);
	
	public Purchase getOldestPurchase();
}