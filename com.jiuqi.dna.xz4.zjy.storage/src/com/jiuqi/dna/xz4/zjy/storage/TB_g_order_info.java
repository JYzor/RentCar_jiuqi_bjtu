package com.jiuqi.dna.xz4.zjy.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_g_order_info extends TableDeclarator {

	public static final String TABLE_NAME ="g_order_info";

	public final TableFieldDefine f_ORDER_NUM;
	public final TableFieldDefine f_PLATE_NUM;
	public final TableFieldDefine f_RENT_USER;
	public final TableFieldDefine f_START_TIME;
	public final TableFieldDefine f_END_TIME;
	public final TableFieldDefine f_ORDER_STATE;
	public final TableFieldDefine f_RENT;

	public static final String FN_ORDER_NUM ="ORDER_NUM";
	public static final String FN_PLATE_NUM ="PLATE_NUM";
	public static final String FN_RENT_USER ="RENT_USER";
	public static final String FN_START_TIME ="START_TIME";
	public static final String FN_END_TIME ="END_TIME";
	public static final String FN_ORDER_STATE ="ORDER_STATE";
	public static final String FN_RENT ="RENT";

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_g_order_info() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_ORDER_NUM = field = this.table.newField(FN_ORDER_NUM, TypeFactory.VARCHAR(100));
		field.setTitle("订单编号");
		this.f_PLATE_NUM = field = this.table.newField(FN_PLATE_NUM, TypeFactory.VARCHAR(30));
		field.setTitle("车牌号");
		this.f_RENT_USER = field = this.table.newField(FN_RENT_USER, TypeFactory.GUID);
		field.setTitle("租用人ID");
		this.f_START_TIME = field = this.table.newField(FN_START_TIME, TypeFactory.DATE);
		field.setTitle("开始时间");
		this.f_END_TIME = field = this.table.newField(FN_END_TIME, TypeFactory.DATE);
		field.setTitle("结束时间");
		this.f_ORDER_STATE = field = this.table.newField(FN_ORDER_STATE, TypeFactory.INT);
		field.setTitle("订单状态");
		this.f_RENT = field = this.table.newField(FN_RENT, TypeFactory.DOUBLE);
		field.setTitle("租金");
	}

}
