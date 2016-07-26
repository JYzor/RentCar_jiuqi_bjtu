package com.jiuqi.dna.xz4.zjy.impl;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.xz4.zjy.info.OrderInfo;

public class OrderImpl implements OrderInfo{
	private String orderNum;
	private String plateNum;
	private GUID rentUser;
	private Date startTime;
	private Date endTime;
	private int status;
	private double rent;
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public GUID getRentUser() {
		return rentUser;
	}
	public void setRentUser(GUID rentUser) {
		this.rentUser = rentUser;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}

	

}
