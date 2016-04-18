package com.daily.bill.domain.page.query;

import com.daily.bill.domain.query.BaseQuery;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 10:44:08 AM
*/
public class PageQuery extends BaseQuery{
	private Integer pageSize;
    private Integer pageNumber;
    
    public Integer getStartIndex(){
    	if(pageSize == null){  
    		return null;
    	}else{
    		return pageNumber == null ? 0 : (pageNumber - 1) * pageSize;
    	}
    }
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
    public void initSizeAndNumber(int number) {
        if (this.getPageNumber() == null) {
            this.setPageNumber(1);
        }
        if (this.getPageSize() == null) {
            int pageSize = number != 0 ? number : 10;
            this.setPageSize(pageSize);
        }
    }

	public void notNeedPage(){
		this.pageNumber = null;
		this.pageSize = null;
	}
}
