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

	//不可调用该构造方法.当前类只能由框架实例化.
	private TB_g_user_info() {
		super(TABLE_NAME);
		this.table.setCategory("基础数据主体");
		TableFieldDeclare field;
		this.f_account = field = this.table.newField(FN_account, TypeFactory.VARCHAR(20));
		field.setTitle("账号");
		this.f_accountname = field = this.table.newField(FN_accountname, TypeFactory.VARCHAR(20));
		field.setTitle("用户名");
		this.f_pass = field = this.table.newField(FN_pass, TypeFactory.VARCHAR(50));
		field.setTitle("密码");
		this.f_mobile = field = this.table.newField(FN_mobile, TypeFactory.VARCHAR(11));
		field.setTitle("手机号码");
		this.f_cardNo = field = this.table.newField(FN_cardNo, TypeFactory.VARCHAR(50));
		field.setTitle("驾驶证号");
		this.f_mail = field = this.table.newField(FN_mail, TypeFactory.VARCHAR(20));
		field.setTitle("邮箱");
	}

}
