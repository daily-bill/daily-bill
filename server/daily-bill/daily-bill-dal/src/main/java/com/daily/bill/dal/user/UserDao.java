package com.daily.bill.dal.user;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.daily.bill.domain.user.User;
import com.daily.bill.domain.user.query.UserQuery;
/**
*@author Jin Rongquan
*@version Sun Apr 17 14:18:10 CST 2016
*/
@Repository
public interface UserDao {

	public int save(User user);

	public List<User> getListByQuery(UserQuery query);

	public int getNumberByQuery(UserQuery query);
	
	public User getById(Integer id);
}