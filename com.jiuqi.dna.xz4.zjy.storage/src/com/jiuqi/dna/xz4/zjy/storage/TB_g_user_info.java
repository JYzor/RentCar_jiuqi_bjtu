package com.jiuqi.dna.xz4.zjy.storage;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_g_user_info extends TableDeclarator {

	public static final String TABLE_NAME ="g_user_info";

	public final TableFieldDefine f_account;
	public final TableFieldDefine f_accountname;
	public final TableFieldDefine f_pass;
	public final TableFieldDefine f_mobile;
	public final TableFieldDefine f_cardNo;
	public final TableFieldDefine f_mail;

	public static final String FN_account ="account";
	public static final String FN_accountname ="accountname";
	public static final String FN_pass ="pass";
	public static final String FN_mobile ="mobile";
	public static final String FN_cardNo ="cardNo";
	public static final String FN_mail ="mail";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_g_user_info() {
		super(TABLE_NAME);
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_account = field = this.table.newField(FN_account, TypeFactory.VARCHAR(20));
		field.setTitle("�˺�");
		this.f_accountname = field = this.table.newField(FN_accountname, TypeFactory.VARCHAR(20));
		field.setTitle("�û���");
		this.f_pass = field = this.table.newField(FN_pass, TypeFactory.VARCHAR(50));
		field.setTitle("����");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.VARCHAR(11));
		field.setTitle("�ֻ�����");
		this.f_cardNo = field = this.table.newField(FN_cardNo, TypeFactory.VARCHAR(50));
		field.setTitle("��ʻ֤��");
		this.f_mail = field = this.table.newField(FN_mail, TypeFactory.VARCHAR(20));
		field.setTitle("����");
	}

}
