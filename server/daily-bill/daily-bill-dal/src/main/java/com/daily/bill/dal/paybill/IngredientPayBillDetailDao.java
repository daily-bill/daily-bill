package com.daily.bill.dal.paybill;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.daily.bill.domain.paybill.IngredientPayBillDetail;
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.query.IngredientPayBillDetailQuery;
/**
*@author Jin Rongquan
*@version Sun Apr 17 14:11:53 CST 2016
*/
@Repository
public interface IngredientPayBillDetailDao {

	public int save(IngredientPayBillDetail ingredientPayBillDetail);

	public List<IngredientPayBillDetail> getListByQuery(IngredientPayBillDetailQuery query);

	public int getNumberByQuery(IngredientPayBillDetailQuery query);
	
	public List<IngredientPayBillDetailAmount> getIngredientPayBillDetailAmount(IngredientPayBillDetailQuery query);
}