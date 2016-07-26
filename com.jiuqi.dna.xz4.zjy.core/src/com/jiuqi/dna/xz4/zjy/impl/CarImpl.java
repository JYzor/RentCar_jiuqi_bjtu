package com.jiuqi.dna.xz4.zjy.impl;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.xz4.zjy.info.CarInfo;

public class CarImpl implements CarInfo{
	private String plateNum;
	private GUID recid;
	public GUID getRecid() {
		return recid;
	}

	public void setRecid(GUID recid) {
		this.recid = recid;
	}



	private String brand;
	private String model;
	private byte[] image;
	private Date factoryDate;
	private double rentDay;
	private String status;

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public Date getFactoryDate() {
		return factoryDate;
	}


	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}


	public double getRentDay() {
		return rentDay;
	}


	public void setRentDay(double rentDay) {
		this.rentDay = rentDay;
	}


	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
}
