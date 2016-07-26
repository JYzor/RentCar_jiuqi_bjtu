package com.jiuqi.dna.xz4.zjy.info;

import java.util.Date;

import com.jiuqi.dna.core.type.GUID;

public interface OrderInfo {
	
	public String getOrderNum();
	public String getPlateNum();
	public GUID getRentUser();
	public Date getStartTime();
	public Date getEndTime();
	public int getStatus();
	public double getRent();
}
