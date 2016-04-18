package com.daily.bill.domain.page;

import java.io.Serializable;
import java.util.List;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 10:43:11 AM
*/
public class Page<E> implements Serializable{
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalRow;
	private Integer startIndex;
	private List<E> list;

	public Page(Integer totalRow , Integer pageNumber , Integer pageSize){
		this.totalRow = totalRow;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public int getTotalPage(){
		return (int)Math.ceil(totalRow * 1.0 / getPageSize());
	}
	
    public Page<E> goFirst(){
    	this.startIndex = 0;
    	this.pageNumber = 1;
    	return this;
    }
    
    public Page<E> goEnd(){
    	this.startIndex = (this.getTotalPage() - 1) * pageSize;
    	this.pageNumber = this.getTotalPage();
    	return this;
    }
	
    public Page<E> goPrevious(int pageNumber){
    	if(hasPrevious()){
    		this.startIndex = (pageNumber - 1) * pageSize;
    		this.pageNumber--;
    	}else{
    	    this.startIndex = 0;
    	    this.pageNumber = 1;
    	}
    	return this;
    }
    
    public boolean hasPrevious(){
    	return pageNumber < 1 ? false : true ;
    }
    
    public Page<E> goNext(int pageNumber){
    	if(hasNext()){
    	    this.startIndex = pageNumber * pageSize;
    	    this.pageNumber++;
    	}else{
        	this.startIndex = (this.getTotalPage() - 1) * pageSize;
        	this.pageNumber = this.getTotalPage();
    	}
    	return this;
    }
    
    public boolean hasNext(){
    	return pageNumber >= this.getTotalPage() ? false : true;
    }
    
	public Integer getPageNumber() {
		return pageNumber == null ? 1 : pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize == null ? 10 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalRow() {
		return totalRow == null ? 0 : totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	public Integer getStartIndex() {
		return startIndex == null ? 0 : startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}
}
