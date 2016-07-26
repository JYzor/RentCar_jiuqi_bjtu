package com.jiuqi.dna.xz4.zjy.task;

import com.jiuqi.dna.core.invoke.SimpleTask;


public class RegistTask extends SimpleTask{
	
//	String accout =accountTxt.getText();
//	String mobile =mobileTxt.getText();
//	String accountname =accountnameTxt.getText();
//	String pass =passWordTxt.getText();
//	String mail =mailTxt.getText();
//	String cardNo =cardNoTxt.getText();
	private String accout;
	private String mobile;
	private String accountname;
	private String pass;
	private String mail;
	private String cardNo;
	
	//提示信息
	private String  message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccout() {
		return accout;
	}
	public void setAccout(String accout) {
		this.accout = accout;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	

}
