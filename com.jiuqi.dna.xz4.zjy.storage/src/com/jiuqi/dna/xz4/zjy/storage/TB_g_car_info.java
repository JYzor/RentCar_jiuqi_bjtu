package com.jiuqi.dna.xz4.zjy.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_g_car_info extends TableDeclarator {

	public static final String TABLE_NAME ="g_car_info";

	public final TableFieldDefine f_PLATE_NUM;
	public final TableFieldDefine f_BRAND;
	public final TableFieldDefine f_MODEL;
	public final TableFieldDefine f_IMAGE;
	public final TableFieldDefine f_FACTORY_DATE;
	public final TableFieldDefine f_RENT_DAY;
	public final TableFieldDefine f_CAR_STATE;

	public static final String FN_PLATE_NUM ="PLATE_NUM";
	public static final String FN_BRAND ="BRAND";
	public static final String FN_MODEL ="MODEL";
	public static final String FN_IMAGE ="IMAGE";
	public static final String FN_FACTORY_DATE ="FACTORY_DATE";
	public static final String FN_RENT_DAY ="RENT_DAY";
	public static final String FN_CAR_STATE ="CAR_STATE";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_g_car_info() {
		super(TABLE_NAME);
		TableFieldDeclare field;
		this.f_PLATE_NUM = field = this.table.newField(FN_PLATE_NUM, TypeFactory.VARCHAR(10));
		field.setTitle("���ƺ�");
		this.f_BRAND = field = this.table.newField(FN_BRAND, TypeFactory.VARCHAR(20));
		field.setTitle("Ʒ��");
		this.f_MODEL = field = this.table.newField(FN_MODEL, TypeFactory.VARCHAR(50));
		field.setTitle("�ͺ�");
		this.f_IMAGE = field = this.table.newField(FN_IMAGE, TypeFactory.BLOB);
		field.setTitle("ͼƬ");
		this.f_FACTORY_DATE = field = this.table.newField(FN_FACTORY_DATE, TypeFactory.DATE);
		field.setTitle("��������");
		this.f_RENT_DAY = field = this.table.newField(FN_RENT_DAY, TypeFactory.DOUBLE);
		field.setTitle("�����");
		this.f_CAR_STATE = field = this.table.newField(FN_CAR_STATE, TypeFactory.VARCHAR(10));
		field.setTitle("����״̬");
	}

}
