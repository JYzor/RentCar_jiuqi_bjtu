package com.jiuqi.dna.xz4.zjy.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;








import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.grid2.Grid2;
import com.jiuqi.dna.ui.wt.grid2.GridModel;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
//import com.jiuqi.dna.ui.wt.widgets.List;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.xz4.zjy.info.OrderInfo;
import com.jiuqi.dna.xz4.zjy.info.UserInfo;

public class OrderPage extends Page{
	GUID userId;
	UserInfo userInfo;

	public OrderPage(Composite parent,UserInfo userinfo) {
		super(parent);
		// TODO Auto-generated constructor stub
		this.userInfo= userinfo;
		this.userId = userinfo.getRecid();
		this.setLayout(new GridLayout());
		initPage();
	}

	private void initPage() {
		// TODO Auto-generated method stub
		Composite topCmp = new Composite(this);
		GridData topGd = new GridData();
		topGd.heightHint = 66;
		topGd.widthHint = 1024;
		topGd.grabExcessHorizontalSpace = true;
		topGd.horizontalAlignment = JWT.CENTER;

		topCmp.setLayoutData(topGd);
		// topCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		topCmp.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.jiuqi.dna.xz4.zjy.ui", "images/mainTop.jpg"));
		
		Button orderBtn = new Button(topCmp,JWT.LINK);
		orderBtn.setText("返回车辆预定");
		orderBtn.setSize(120, 29);
		orderBtn.setLocation(800, 20);
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				OrderPage.this.showPage("CarBookingPage", userInfo);				
			}
		});
		
		
		Composite orderListCmp = new Composite(this);
		orderListCmp.setLayout(new FillLayout());
		GridData gd1 = new GridData();
		gd1.heightHint = 500;
		gd1.widthHint = 1024;
		gd1.grabExcessHorizontalSpace = true;
		gd1.horizontalAlignment = JWT.CENTER;

		orderListCmp.setLayoutData(gd1);
		
		Grid2 grid2 = new Grid2(orderListCmp);
		GridModel gm = grid2.getModel();
		gm.setColumnCount(7);
		
		gm.getGridCell(0, 0).setShowText("序号");
		gm.getGridCell(1, 0).setShowText("订单编号");
		gm.getGridCell(2, 0).setShowText("车牌号");
		gm.getGridCell(3, 0).setShowText("开始时间");
		gm.getGridCell(4, 0).setShowText("结束时间");
		gm.getGridCell(5, 0).setShowText("订单状态");
		gm.getGridCell(6, 0).setShowText("总租金");
		gm.setColumnHidden(0, true);
		gm.setColumnWidth(0, 50);
		gm.setColumnWidth(1, 220);
		gm.setColumnWidth(2, 180);
		gm.setColumnWidth(3, 140);
		gm.setColumnWidth(4, 140);
		gm.setColumnWidth(5, 140);
		gm.setColumnWidth(6, 186);
		
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		orderList =getContext().getList(OrderInfo.class,userId);
		gm.setRowCount(1);
		int size = orderList.size();
		if(orderList!=null && size > 0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
			gm.setRowCount( size + 1);
			for(int i=0 ;i<orderList.size();i++){
				gm.getGridCell(1, i+1).setShowText(orderList.get(i).getOrderNum());
				gm.getGridCell(2, i+1).setShowText(orderList.get(i).getPlateNum());
				gm.getGridCell(3, i+1).setShowText(sdf.format(orderList.get(i).getStartTime()));
				gm.getGridCell(4, i+1).setShowText(sdf.format(orderList.get(i).getEndTime()));
				String statusString ="";
				if(orderList.get(i).getStatus()==1){
					statusString = "预约";
				}
				if(orderList.get(i).getStatus()==2){
					statusString = "确认";
				}
				if(orderList.get(i).getStatus()==3){
					statusString = "结束";
				}
				gm.getGridCell(5, i+1).setShowText(statusString);
				gm.getGridCell(6, i+1).setShowText(String.valueOf(orderList.get(i).getRent()));
				
			}
		}
		
	}

}
