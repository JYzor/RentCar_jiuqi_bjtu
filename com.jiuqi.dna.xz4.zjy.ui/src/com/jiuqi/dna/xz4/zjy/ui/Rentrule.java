package com.jiuqi.dna.xz4.zjy.ui;


import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.customs.StyledWindow;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.Label;


public class Rentrule extends StyledWindow{

	public Rentrule(String title, Control owner) {
		super(title, owner, JWT.MODAL|JWT.CLOSE, JWT.NONE);

			Label rulelab =new Label(this);
			rulelab.setSize(300, 600);
			rulelab.setLocation(300, 100);
			rulelab.setText("会员服务条款！");
		
	}

	@Override
	protected int handleButtonPressed(int buttonType) {
		// TODO Auto-generated method stub
		return JWT.CLOSE;
	}


	

}
