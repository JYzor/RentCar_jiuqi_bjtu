package com.jiuqi.dna.xz4.zjy.key;

import java.util.List;

public class GetCarInfoByBookingConKey {
	private List<String> brand;
	private int rentDay;
	private int flag;
	private int pageSize;
	private int pageIndex;
	
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	private String status;
	private String orderByRentDay;

	
	public List<String> getBrand() {
		return brand;
	}
	public void setBrand(List<String> brand) {
		this.brand = brand;
	}
	
	
	public int getRentDay() {
		return rentDay;
	}
	public void setRentDay(int rentDay) {
		this.rentDay = rentDay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderByRentDay() {
		return orderByRentDay;
	}
	public void setOrderByRentDay(String orderByRentDay) {
		this.orderByRentDay = orderByRentDay;
	}
	

}
