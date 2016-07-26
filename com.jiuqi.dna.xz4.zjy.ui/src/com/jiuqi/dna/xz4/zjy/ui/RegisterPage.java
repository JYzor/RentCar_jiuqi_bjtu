package com.jiuqi.dna.xz4.zjy.ui;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MessageDialog;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.xz4.zjy.task.RegistTask;



public class RegisterPage extends Page{
	Label WarnLbl,WarnLbl1,WarnLblpass,WarnLblpasscheck,Warnmail;
	Text accountTxt,mobileTxt,accountnameTxt,passWordTxt,passWordcheckTxt,cardNoTxt,mailTxt;
	Button tiaokuan,registerbtn,readAgreeBtn;
	Composite userInfoCmp,parentCmp;

	public RegisterPage(Composite parent) {
		super(parent);
		//充满布局
		this.setLayout(new FillLayout());
		// TODO Auto-generated constructor stub
		ScrolledPanel sp = new ScrolledPanel(this);
		//获取滚动条中的容器
		parentCmp =sp.getComposite();
		//将父容器设置为网格布局
		parentCmp.setLayout(new GridLayout());
		initPage();
		
		
	}

	private void initPage() {
		// TODO Auto-generated method stub
		Composite topCmp = new Composite(parentCmp);
		GridData topGd= new GridData();
		topGd.heightHint = 66; 
		topGd.widthHint = 1024;
		topGd.grabExcessHorizontalSpace=true;
		topGd.horizontalAlignment = JWT.CENTER;
		
		topCmp.setLayoutData(topGd);
		//topCmp.setBorder(new CBorder());
		topCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/loginTop.jpg"));
		
		Composite midCmp = new Composite(parentCmp);
		GridData midGd= new GridData();
		midGd.heightHint = 91; 
		midGd.widthHint = 1024;
		midGd.grabExcessHorizontalSpace=true;
		midGd.horizontalAlignment = JWT.CENTER;
		
		midCmp.setLayoutData(midGd);
		//midCmp.setBorder(new CBorder());
		midCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/registTop.jpg"));
		
		userInfoCmp = new Composite(parentCmp);
		GridData userInfoGd= new GridData();
		userInfoGd.heightHint = 550; 
		userInfoGd.widthHint = 1024;
		userInfoGd.grabExcessHorizontalSpace=true;
		userInfoGd.horizontalAlignment = JWT.CENTER;
		userInfoCmp.setLayoutData(userInfoGd);
		//userInfoCmp.setBorder(new CBorder());
		initUserInfocmp();
		
	}

	private void initUserInfocmp() {
		
		Label welcomlab = new Label(userInfoCmp);
		registerbtn = new Button(userInfoCmp);
		welcomlab.setLocation(20, 15);
		welcomlab.setText("欢迎注册");
		welcomlab.setSize(110, 40);
		welcomlab.setFont(new Font(14,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		
		Label loginto = new Label(userInfoCmp,JWT.LINK);
		loginto.setText("如果你已是会员，请点此");
		loginto.setSize(150, 20);
		loginto.setLocation(660, 20);
		
		Button loginBtn = new Button(userInfoCmp,JWT.LINK);
		loginBtn.setText("登录");
		loginBtn.setSize(40, 20);
		loginBtn.setLocation(805, 17);
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				RegisterPage.this.showPage("LoginPage","");
				
			}
		});
		 
		Composite lineCmp= new Composite(userInfoCmp);
		lineCmp.setSize(1000, 1);
		lineCmp.setLocation(18, 55);
		lineCmp.setBorder(new CBorder(1,JWT.BORDER_STYLE_SOLID,0xc0c0c0));
		
		
		Label accountlab = new Label(userInfoCmp);
		accountlab.setText("账号:");
		accountlab.setSize(35, 21);
		accountlab.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		accountlab.setLocation(310, 100);		
		accountTxt = new Text(userInfoCmp);
		accountTxt.setSize(200, 25);
		accountTxt.setLocation(385, 100);
		
		Label accountnamelab = new Label(userInfoCmp);
		accountnamelab.setText("用户名:");
		accountnamelab.setSize(45, 21);
		accountnamelab.setLocation(310, 145);
		accountnamelab.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		accountnameTxt = new Text(userInfoCmp);
		accountnameTxt.setSize(200, 25);
		accountnameTxt.setLocation(385, 145);
		
		
		Label passwordlab = new Label(userInfoCmp);
		passwordlab.setText("密码:");
		passwordlab.setSize(35, 21);
		passwordlab.setLocation(310, 190);
		passwordlab.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		
		passWordTxt = new Text(userInfoCmp,JWT.PASSWORD);
		passWordTxt.setSize(200, 25);
		passWordTxt.setLocation(385, 190);
		
		Label passwordchecklab = new Label(userInfoCmp);
		passwordchecklab.setText("确认密码:");
		passwordchecklab.setSize(55, 21);
		passwordchecklab.setLocation(310, 235);
		passwordchecklab.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		passWordcheckTxt = new Text(userInfoCmp,JWT.PASSWORD);
		passWordcheckTxt.setSize(200, 25);
		passWordcheckTxt.setLocation(385, 235);
		
		
		Label mobile = new Label(userInfoCmp);
		mobile.setText("手机号:");
		mobile.setSize(45, 21);
		mobile.setLocation(310, 280);
		mobile.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		mobileTxt = new Text(userInfoCmp);
		mobileTxt.setSize(200, 25);
		mobileTxt.setLocation(385, 280);
		
		
		Label cardNo = new Label(userInfoCmp);
		cardNo.setText("驾驶证号:");
		cardNo.setSize(55, 21);
		cardNo.setLocation(310, 325);
		cardNo.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		cardNoTxt = new Text(userInfoCmp);
		cardNoTxt.setSize(200, 25);
		cardNoTxt.setLocation(385, 325);
		
		
		Label mail = new Label(userInfoCmp);
		mail.setText("邮箱:");
		mail.setSize(45, 21);
		mail.setLocation(310, 370);
		mail.setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		mailTxt = new Text(userInfoCmp);
		mailTxt.setSize(200, 25);
		mailTxt.setLocation(385, 370);
		
		
		registerbtn.setEnabled(false);
		readAgreeBtn = new Button(userInfoCmp,JWT.CHECK);
		readAgreeBtn.setSize(25, 25);
		readAgreeBtn.setLocation(325, 423);
		readAgreeBtn.addMouseClickListener(new MouseClickListener() {
			
			@Override
			public void click(MouseEvent arg0) {
				
				if(readAgreeBtn.getSelection()){
					registerbtn.setEnabled(true);
				}else{
					registerbtn.setEnabled(false);
				}
				
			}
		});
		
		Label readAgreelab =new Label(userInfoCmp);
		readAgreelab.setText("我已阅读并同意");
		readAgreelab.setSize(125, 30);
		readAgreelab.setLocation(345, 426);
		
		
		
		tiaokuan = new Button(userInfoCmp,JWT.LINK);
		tiaokuan.setText("《会员服务条款》");
		tiaokuan.setSize(190, 25);
		tiaokuan.setLocation(395, 422);
		setFont(new Font(10,"微软雅黑",JWT.FONT_STYLE_PLAIN));
		tiaokuan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				new Rentrule("服务条款",RegisterPage.this);
			
				
			}
		});
		
		
		registerbtn.setSize(200, 40);
		registerbtn.setLocation(385, 460);
		registerbtn.setImage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/registButton.jpg"));
		
		WarnLbl =new Label(userInfoCmp);
		WarnLbl.setSize(200, 25);
		WarnLbl.setForeground(new Color(0xFF0000));
		WarnLbl.setVisible(false);
		
		WarnLbl1 =new Label(userInfoCmp);
		WarnLbl1.setSize(200, 25);
		WarnLbl1.setForeground(new Color(0xFF0000));
		WarnLbl1.setVisible(false);
		
		
		
		WarnLblpass =new Label(userInfoCmp);
		WarnLblpass.setSize(360, 25);
		WarnLblpass.setForeground(new Color(0xFF0000));
		WarnLblpass.setVisible(false);
		
		WarnLblpasscheck =new Label(userInfoCmp);
		WarnLblpasscheck.setSize(260, 25);
		WarnLblpasscheck.setForeground(new Color(0xFF0000));
		WarnLblpasscheck.setVisible(false);
		

		Warnmail =new Label(userInfoCmp);
		Warnmail.setSize(260, 25);
		Warnmail.setForeground(new Color(0xFF0000));
		Warnmail.setVisible(false);
		
//		WarnLblreg = new Label(userInfoCmp);
//		WarnLblreg.setSize(200, 25);
//		WarnLblreg.setForeground(new Color(0xFF0000));
//		WarnLblreg.setVisible(false);
		
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// TODO Auto-generated method stub
				//正则表达式判断用户名非空
				if(accountTxt.getText()==null||"".equals(accountTxt.getText())){
					WarnLbl.setText("用户名不能为空");
					WarnLbl.setLocation(615, 100);
					WarnLbl.setVisible(true);
					return;
					
				}else{
					WarnLbl.setVisible(false);
				}
				//判断密码非空
				String CheckPassword ="^[a-zA-Z]\\w{5,17}$";
				if(passWordTxt.getText()==null||"".equals(passWordTxt.getText())){
					WarnLblpass.setText("密码不能为空");
					WarnLblpass.setLocation(615, 190);
					WarnLblpass.setVisible(true);
					return;
					
				}else if(!passWordTxt.getText().matches(CheckPassword)){
					WarnLblpass.setText("密码格式错误，正确格式为：以字母开头，长度在6-18之间");
					WarnLblpass.setLocation(615, 190);
					WarnLblpass.setVisible(true);
					return;
					
				}else{
					WarnLblpass.setVisible(false);
				}
				//判断两次输入的密码是否匹配
				if(passWordcheckTxt.getText()==null||"".equals(passWordcheckTxt.getText())){
					WarnLblpasscheck.setText("确认密码不能为空");
					WarnLblpasscheck.setLocation(615, 235);
					WarnLblpasscheck.setVisible(true);
					return;
					
				}else if(!passWordTxt.getText().equals(passWordcheckTxt.getText())){
					WarnLblpasscheck.setText("两次输入的密码不一致！");
					WarnLblpasscheck.setLocation(615, 235);
					WarnLblpasscheck.setVisible(true);
					return;									
				}else{
					WarnLblpasscheck.setVisible(false);
				}
				
				//判断手机号码是否合法
				String CheckPhone = "^1[3,4,5,7,8]\\d{9}";
				if(mobileTxt.getText()==null||"".equals(mobileTxt.getText())){
					WarnLbl1.setText("手机号码不能为空");
					WarnLbl1.setLocation(615, 280);
					WarnLbl1.setVisible(true);
					return;
					
				}else if(!mobileTxt.getText().matches(CheckPhone)){
					WarnLbl1.setText("号码格式不正确");
					WarnLbl1.setLocation(615, 280);
					WarnLbl1.setVisible(true);
					return;
					
				}else{
					WarnLbl1.setVisible(false);
				}
				//判断邮箱是否合法
				String Checkmail ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
				if(mailTxt.getText()==null||"".equals(mailTxt.getText())){
					Warnmail.setText("邮箱不能为空");
					Warnmail.setLocation(615, 370);
					Warnmail.setVisible(true);
					return;
					
				}else if(!mailTxt.getText().matches(Checkmail)){
					Warnmail.setText("邮箱格式不正确");
					Warnmail.setLocation(615, 370);
					Warnmail.setVisible(true);
					return;
					
				}else{
					Warnmail.setVisible(false);
				}
				
				
				
				//注册逻辑
				String account =accountTxt.getText();
				String mobile =mobileTxt.getText();
				String accountname =accountnameTxt.getText();
				String pass =passWordTxt.getText();
				String MD5Pass =GUID.MD5Of(pass).toString();  
				String mail =mailTxt.getText();
				String cardNo =cardNoTxt.getText();
								
				RegistTask registTask = new RegistTask();
				registTask.setAccout(account);
				registTask.setMobile(mobile);
				registTask.setAccountname(accountname);
				registTask.setPass(MD5Pass);
				registTask.setMail(mail);
				registTask.setCardNo(cardNo);
				
				//调用任务处理器
				
				getContext().handle(registTask);
				String message =registTask.getMessage();
				//System.out.println(message);
				MessageDialog.alert(message);

				
				 
				//跳转到登录界面
				RegisterPage.this.showPage("LoginPage",account);
																
			}
						
		});
				
		 
	}

}
