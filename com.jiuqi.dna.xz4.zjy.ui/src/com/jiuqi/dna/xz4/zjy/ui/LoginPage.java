package com.jiuqi.dna.xz4.zjy.ui;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.Font;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MessageDialog;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.xz4.zjy.info.UserInfo;
import com.jiuqi.dna.xz4.zjy.key.Loginkey;

public class LoginPage extends Page{
	Composite infoCmp;
	String registAcc;
	Text accountTxt,passWordTxt;
//	Label WarnLbl,WarnLblpass;

	public LoginPage(Composite parent,String account) {
		super(parent);
		this.registAcc= account;
		initPage();
		this.setLayout(new GridLayout());

	}

	private void initPage() {
		Composite topCmp = new Composite(this);
		GridData topGd= new GridData();
		topGd.heightHint = 66; 
		topGd.widthHint = 1024;
		topGd.grabExcessHorizontalSpace=true;
		topGd.horizontalAlignment = JWT.CENTER;
		
		topCmp.setLayoutData(topGd);
		//topCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		topCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/loginTop.jpg"));
		
		Composite midCmp = new Composite(this);
		GridData midGd= new GridData();
		midGd.heightHint = 360; 
		midGd.widthHint = 1024;
		midGd.grabExcessHorizontalSpace=true;
		midGd.horizontalAlignment = JWT.CENTER;
		
		midCmp.setLayoutData(midGd);
		midCmp.setBorder(new CBorder());
		midCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/loginMiddle.jpg"));
		
		infoCmp = new Composite(midCmp);
		GridData infoGd= new GridData();
		infoGd.heightHint = 290; 
		infoGd.widthHint = 350;
		infoCmp.setLocation(631, 36);
		infoCmp.setSize(340, 280);		
		infoCmp.setLayoutData(infoCmp);
		//infoCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		initInfocmp();
		
		Composite bottomCmp = new Composite(this);
		GridData bottomGd= new GridData();
		bottomGd.heightHint = 130; 
		bottomGd.widthHint = 1024;
		bottomGd.grabExcessHorizontalSpace=true;
		bottomGd.horizontalAlignment = JWT.CENTER;		
		bottomCmp.setLayoutData(bottomGd);
		//bottomCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		bottomCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/loginBottom.jpg"));
		
		
		
		
	}
	private void initInfocmp(){
		Label welcomlab = new Label(infoCmp);
		welcomlab.setLocation(20, 30);
		welcomlab.setText("��ӭ��¼");
		welcomlab.setSize(110, 40);
		welcomlab.setFont(new Font(12,"΢���ź�",JWT.FONT_STYLE_PLAIN));
		
		Button registBtn = new Button(infoCmp,JWT.LINK);
		registBtn.setText("���ע��");
		registBtn.setSize(60, 20);
		registBtn.setLocation(261, 28);
		registBtn.setFont(new Font(9,"΢���ź�",JWT.FONT_STYLE_PLAIN));
		registBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LoginPage.this.showPage("RegisterPage");
				
			}
		});
		
		Label accountlab = new Label(infoCmp);
		accountlab.setText("�˺�:");
		accountlab.setSize(35, 21);
		accountlab.setFont(new Font(10,"΢���ź�",JWT.FONT_STYLE_PLAIN));
		accountlab.setLocation(39, 94);
		
		accountTxt = new Text(infoCmp);
		accountTxt.setSize(200, 25);
		accountTxt.setLocation(80, 92);
		//Ϊ�û������Ͳ�����
		accountTxt.setText(registAcc);
		
		Label passwordlab = new Label(infoCmp);
		passwordlab.setText("����:");
		passwordlab.setSize(35, 21);
		passwordlab.setFont(new Font(10,"΢���ź�",JWT.FONT_STYLE_PLAIN));
		passwordlab.setLocation(39, 129);
		
		passWordTxt = new Text(infoCmp,JWT.PASSWORD);
		passWordTxt.setSize(200, 25);
		passWordTxt.setLocation(80, 127);
		
//		WarnLbl =new Label(infoCmp);
//		WarnLbl.setSize(200, 25);
//		WarnLbl.setForeground(new Color(0xFF0000));
//		WarnLbl.setVisible(false);
//		
//		WarnLblpass =new Label(infoCmp);
//		WarnLblpass.setSize(200, 25);
//		WarnLblpass.setForeground(new Color(0xFF0000));
//		WarnLblpass.setVisible(false);
		
		Button loginbtn = new Button(infoCmp);
		loginbtn.setSize(200, 40);
		loginbtn.setLocation(80, 182);
		loginbtn.setImage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/loginButton.jpg"));
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//��ȡ�û���д���û���������
				
				if(accountTxt.getText()==null||"".equals(accountTxt.getText())){
//					WarnLbl.setText("�û�������Ϊ��");
//					WarnLbl.setLocation(75, 54);
//					WarnLbl.setVisible(true);
					MessageDialog.alert("�û�������Ϊ��");
					return;
					
				}
				//�ж�����ǿ�
				if(passWordTxt.getText()==null||"".equals(passWordTxt.getText())){
//					WarnLblpass.setText("���벻��Ϊ��");
//					WarnLblpass.setLocation(280, 160);
//					WarnLblpass.setVisible(true);
					MessageDialog.alert("���벻��Ϊ��");
					return;					
				}
				
				String loginAcc = accountTxt.getText();
				String loginPass = passWordTxt.getText();
				String MD5loginPass =GUID.MD5Of(loginPass).toString();
				//����ṩ����ͨ��һ������������ѯ���ݿ��е����ݣ���Ҫ����Entity(������Ҫ�����ݰ�װ)��Key����ѯ����Ҫ��������
				Loginkey loginkey = new Loginkey();
				loginkey.setLoginAcc(loginAcc);
				//ͨ������ṩ����ȥ���ݿ��е�ֵ
				UserInfo userInfo = getContext().get(UserInfo.class,loginkey);
				String ormPass = userInfo.getPass();
				if(MD5loginPass!=null&&MD5loginPass.equals(ormPass)){
					//MessageDialog.alert("��¼�ɹ�");
					LoginPage.this.showPage("CarBookingPage", userInfo);
				}else{
					MessageDialog.alert("��¼ʧ��");
				}
				
				
			}
		});
		
		
		
		
		
	}
	
	

}
