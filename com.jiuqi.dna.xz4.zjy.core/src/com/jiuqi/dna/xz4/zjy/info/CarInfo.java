package com.jiuqi.dna.xz4.zjy.info;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

public interface CarInfo {
	public String getPlateNum();

	public String getBrand();

	public String getModel();

	public byte[] getImage();

	public Date getFactoryDate();

	public double getRentDay();

	public String getStatus();
	
	public GUID getRecid();
}
