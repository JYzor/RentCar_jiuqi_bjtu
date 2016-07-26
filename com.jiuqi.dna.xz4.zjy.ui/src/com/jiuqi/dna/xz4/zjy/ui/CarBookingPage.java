package com.jiuqi.dna.xz4.zjy.ui;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.DocumentEvent;
import com.jiuqi.dna.ui.wt.events.DocumentListener;
import com.jiuqi.dna.ui.wt.grid2.Grid2;
import com.jiuqi.dna.ui.wt.grid2.GridCell;
import com.jiuqi.dna.ui.wt.grid2.GridModel;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.custom.pageBar.PageChangeListener;
import com.jiuqi.dna.ui.custom.pageBar.Pager;
import com.jiuqi.dna.ui.custom.pageBar.Pager.PagerType;
import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.DataImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.grid2.Grid2;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.Control;
import com.jiuqi.dna.ui.wt.widgets.DateTime;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MessageDialog;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.xz4.zjy.info.CarInfo;
import com.jiuqi.dna.xz4.zjy.info.UserInfo;
import com.jiuqi.dna.xz4.zjy.key.GetCarInfoByBookingConKey;
//import com.jiuqi.dna.xz4.zjy.key.GetCarInfoBySelectKey;
import com.jiuqi.dna.xz4.zjy.task.AddOrderTask;
import com.jiuqi.dna.xz4.zjy.task.CarManageTask;

public class CarBookingPage extends Page {
	Composite topCmp, getBackTimeCmp, brandCmp,rentDayCmp,statusAndOrderCmp,carInfoCmp, brandBtnCmp,btnCmp, carListCmp,
			PagebarCmp, bottomCmp, pageCmp;
	DatePicker getCarDateDp,backCarDateDp;
	DateTime getCarTimeDt,backCarTimeDt;
	Grid2 grid2;
	GridModel gm;
	Button statusBtn,highToLow,lowToHigh,rentBtn,rentBtn1,rentBtn2,rentBtn3,rentBtn4;
	int pageSize = 3 ;
	int pageIndex = 0;
	long timeL;
	int days=0,sy=-1;
	UserInfo userInfo;

	public CarBookingPage(Composite parent,UserInfo userInfo) {
		super(parent);
		// TODO Auto-generated constructor stub
		this.userInfo =userInfo;
		initPage();
		
	}

	private void initPage() {
		// TODO Auto-generated method stub
		this.setLayout(new GridLayout());
		topCmp = new Composite(this);
		GridData topGd = new GridData();
		topGd.heightHint = 66;
		topGd.widthHint = 1024;
		topGd.grabExcessHorizontalSpace = true;
		topGd.horizontalAlignment = JWT.CENTER;

		topCmp.setLayoutData(topGd);
		// topCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		topCmp.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.jiuqi.dna.xz4.zjy.ui", "images/mainTop.jpg"));
		Label welcomeLbl = new Label(topCmp);
		welcomeLbl.setText("欢迎您，"+userInfo.getAccountname());
		welcomeLbl.setLocation(750, 25);
		welcomeLbl.setSize(100, 25);
		Button orderBtn = new Button(topCmp,JWT.LINK);
		orderBtn.setText("我的订单");
		orderBtn.setSize(70, 28);
		orderBtn.setLocation(880, 20);
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CarBookingPage.this.showPage("OrderPage", userInfo);
				
			}
		});
		
		//取车还车控件容器
		getBackTimeCmp = new Composite(this);
		GridLayout gl1 = new GridLayout(8);
		gl1.horizontalSpacing = 10;
		GridData gd1 = new GridData();
		gd1.widthHint = 1024;
		gd1.heightHint = 40;
		gd1.grabExcessHorizontalSpace = true;
		gd1.horizontalAlignment = JWT.CENTER;
		getBackTimeCmp.setLayout(gl1);
		getBackTimeCmp.setLayoutData(gd1);
		//getBackTimeCmp.setBorder(new CBorder());
		initgetBackTimeCmp();
		// initCarInfoCmp();
		
		//车辆品牌容器

		brandCmp = new Composite(this);
		GridLayout gl2 = new GridLayout(2);
		gl2.horizontalSpacing =10;		
		GridData gd2 = new GridData();
		gd2.widthHint = 1024;
		gd2.heightHint = 30;
		gd2.grabExcessHorizontalSpace = true;
		gd2.horizontalAlignment = JWT.CENTER;
		gd2.verticalAlignment =JWT.BEGINNING;
		brandCmp.setLayout(gl2);
		brandCmp.setLayoutData(gd2);
		//brandCmp.setBorder(new CBorder());
		
		initBrandCmp();
		// initButton();

		
		//日租金容器
		rentDayCmp = new Composite(this);
		GridLayout gl3 = new GridLayout(2);
		gl3.horizontalSpacing =10;		
		GridData gd3 = new GridData();
		gd3.widthHint = 1024;
		gd3.heightHint = 35;
		gd3.grabExcessHorizontalSpace = true;
		gd3.horizontalAlignment = JWT.CENTER;
		gd3.verticalAlignment =JWT.BEGINNING;
		rentDayCmp.setLayout(gl3);
		rentDayCmp.setLayoutData(gd3);
		//rentDayCmp.setBorder(new CBorder());
		initRentDayCmp();
		
		//状态选择容器
		statusAndOrderCmp = new Composite(this);
		GridLayout gl4 = new GridLayout(2);
		gl4.horizontalSpacing =10;		
		GridData gd4 = new GridData();
		gd4.widthHint = 1024;
		gd4.heightHint = 30;
		gd4.grabExcessHorizontalSpace = true;
		gd4.horizontalAlignment = JWT.CENTER;
		gd4.verticalAlignment =JWT.BEGINNING;
		statusAndOrderCmp.setLayout(gl4);
		statusAndOrderCmp.setLayoutData(gd4);
		//statusAndOrderCmp.setBorder(new CBorder());
		
		 initStatusAndOrderCmp();
		
		
		// 车辆展示列表容器
		carListCmp = new Composite(this);
		carListCmp.setLayout(new FillLayout());
		GridData gd5 = new GridData();
		gd5.widthHint = 1024;
		gd5.heightHint = 400;
		gd5.grabExcessHorizontalSpace = true;
		gd5.horizontalAlignment = JWT.CENTER;
		carListCmp.setLayoutData(gd5);
		//carListCmp.setBorder(new CBorder());
		

		// 分页控件容器
		PagebarCmp = new Composite(this);
		PagebarCmp.setLayout(new GridLayout());
		GridData gd6 = new GridData();
		gd6.widthHint = 1024;
		gd6.heightHint = 30;
		gd6.grabExcessHorizontalSpace = true;
		gd6.horizontalAlignment = JWT.CENTER;
		PagebarCmp.setLayoutData(gd6);
		//PagebarCmp.setBorder(new CBorder());

		pageCmp = new Composite(PagebarCmp);
		pageCmp.setLayout(new FillLayout());
		GridData gdpage = new GridData();
		gdpage.widthHint = 1000;
		gdpage.heightHint = 25;
		gdpage.grabExcessHorizontalSpace = true;
		gdpage.horizontalAlignment = JWT.RIGHT;
		pageCmp.setLayoutData(gdpage);

		initcarListCmp();
		initPageCmp();

		bottomCmp = new Composite(this);
		GridData bottomGd = new GridData();
		bottomGd.heightHint = 130;
		bottomGd.widthHint = 1024;
		bottomGd.grabExcessHorizontalSpace = true;
		bottomGd.horizontalAlignment = JWT.CENTER;
		bottomCmp.setLayoutData(bottomGd);
		// bottomCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID,
		// 0x000000));
		bottomCmp.setBackimage(FileImageDescriptor.createImageDescriptor(
				"com.jiuqi.dna.xz4.zjy.ui", "images/loginBottom.jpg"));

	}
	private void initgetBackTimeCmp(){
		Label getTimeLbl = new Label(getBackTimeCmp);
		getTimeLbl.setText("取车时间：");
				
		getCarDateDp =new DatePicker(getBackTimeCmp);
		getCarDateDp.setDate(new Date());
		getCarDateDp.addDocumentListener(new DocumentListener() {
			
			@Override
			public void documentUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if(getCarDateDp.getDate().after(backCarDateDp.getDate())){
					MessageDialog.alert("取车时间必须在还车时间之前");
					return;
				}
				
			}
		});
		
		
		
		
		getCarTimeDt = new DateTime(getBackTimeCmp,JWT.TIME);
		
		Label backTimeLbl = new Label(getBackTimeCmp);
		backTimeLbl.setText("还车时间：");
		backCarDateDp =new DatePicker(getBackTimeCmp);
		backCarDateDp.setDate(new Date());
		backCarDateDp.addDocumentListener(new DocumentListener() {
			
			@Override
			public void documentUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				if(getCarDateDp.getDate().after(backCarDateDp.getDate())){
					MessageDialog.alert("取车时间必须在还车时间之前");
					return;
				}else{
					List<CarInfo> carList = new ArrayList<CarInfo>();
					carList = getCarInfoBySelectCon(1);
					timeL = (backCarDateDp.getDate().getTime()-getCarDateDp.getDate().getTime())/(1000*3600*24);
					 days += (int)timeL;
					 
					for(int i=0; i<carList.size();i++){
						if(days==0){
							gm.getGridCell(5, i+1).setShowText(String.valueOf(carList.get(i).getRentDay()));
						}else{
							gm.getGridCell(5, i+1).setShowText(String.valueOf(carList.get(i).getRentDay()*days));
						}
						
					}
					
				}
				
			}
		});
		
		
		
		backCarTimeDt = new DateTime(getBackTimeCmp,JWT.TIME);
		
	}

	private void initBrandCmp(){
		Label brandLbl = new Label(brandCmp);
		brandLbl.setText("车辆品牌");
		
		brandBtnCmp = new Composite(brandCmp);
		brandBtnCmp.setLayout(new FillLayout());
		//从数据库中取出数据
		List<CarInfo> list = new ArrayList<CarInfo>();
		list =getContext().getList(CarInfo.class);
		
		for(int i=0; i<list.size();i++){
			Button brandBtn =new Button(brandBtnCmp,JWT.CHECK);
			brandBtn.setSize(70, 30);
			brandBtn.setText(list.get(i).getBrand());
			brandBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//initCarList();
//					List<CarInfo> carList = getCarInfoBySelectCon(1);
//					initCarList(carList);
					pageIndex =0;
					initPageCmp();
					carListCmp.layout();
				}
			});
		}
		
	}
	
	private void initRentDayCmp(){
		Label rentDayLbl = new Label(rentDayCmp);
		rentDayLbl.setText("日租金  ");
		
		Composite rentCmp = new  Composite(rentDayCmp) ;
		GridLayout rentGl = new GridLayout(5);
		rentGl.horizontalSpacing = 80;
		rentCmp.setLayout(rentGl);
		
		rentBtn = new Button(rentCmp,JWT.CHECK);
		rentBtn.setText("全部");
		rentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(rentBtn.getSelection()){
					rentBtn1.setEnabled(false);
					rentBtn1.setSelection(false);
					rentBtn2.setEnabled(false);
					rentBtn2.setSelection(false);
					rentBtn3.setEnabled(false);
					rentBtn3.setSelection(false);
					rentBtn4.setEnabled(false);	
					rentBtn4.setSelection(false);
				}else{
					rentBtn1.setEnabled(true);
					rentBtn2.setEnabled(true);
					rentBtn3.setEnabled(true);
					rentBtn4.setEnabled(true);	
				}
				
				//initCarList();
//				List<CarInfo> carList = getCarInfoBySelectCon(1);
//				initCarList(carList);
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		
		rentBtn1 = new Button(rentCmp,JWT.RADIO);
		rentBtn1.setText("0-150");
		
		rentBtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				initCarList();
//				List<CarInfo> carList = getCarInfoBySelectCon(1);
//				initCarList(carList);
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		rentBtn2 = new Button(rentCmp,JWT.RADIO);
		rentBtn2.setText("150-300");
		
		rentBtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//initCarList();
//				List<CarInfo> carList = getCarInfoBySelectCon(1);
//				initCarList(carList);
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		
		rentBtn3 = new Button(rentCmp,JWT.RADIO);
		rentBtn3.setText("300-500");
		rentBtn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//initCarList();
//				List<CarInfo> carList = getCarInfoBySelectCon(1);
//				initCarList(carList);
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		rentBtn4 = new Button(rentCmp,JWT.RADIO);
		
		rentBtn4.setText("500以上");
		rentBtn4.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//initCarList();
//				List<CarInfo> carList = getCarInfoBySelectCon(1);
//				initCarList(carList);
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
				
			}
		});
		
		
		
		
	}

	private void initStatusAndOrderCmp(){
		statusBtn  =new  Button(statusAndOrderCmp,JWT.CHECK);
		statusBtn.setText("只看可租车辆");
		statusBtn.setSize(150, 50);
		statusBtn.setLocation(10, 5);
		
		statusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//initCarList();
//				List<CarInfo> carList = getCarInfoBySelectCon(1);
//				initCarList(carList);
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		
		lowToHigh = new Button(statusAndOrderCmp);
		lowToHigh.setText("租金由低到高");
		lowToHigh.setSize(120, 25);
		lowToHigh.setLocation(150, 7);
		lowToHigh.setVisible(true);
		lowToHigh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(sy==0){
					lowToHigh.setText("租金由高到低");
					sy =1;
				}else{
					lowToHigh.setText("租金由低到高");
					sy =0;
				}
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		
		highToLow = new Button(statusAndOrderCmp);
		highToLow.setText("租金由高到低");
		highToLow.setSize(120, 25);
		highToLow.setLocation(150, 7);
		highToLow.setVisible(false);
		highToLow.setEnabled(false);
		highToLow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pageIndex =0;
				initPageCmp();
				carListCmp.layout();
			}
		});
		
		
	}
	private void initcarListCmp(){
		grid2 = new Grid2(carListCmp);
		gm = grid2.getModel();
		gm.setColumnCount(7);
		
		gm.getGridCell(0, 0).setShowText("序号");
		gm.getGridCell(1, 0).setShowText("汽车预览");
		gm.getGridCell(2, 0).setShowText("品牌");
		gm.getGridCell(3, 0).setShowText("车辆状态");
		gm.getGridCell(4, 0).setShowText("日租金");
		gm.getGridCell(5, 0).setShowText("总租金");
		gm.getGridCell(6, 0).setShowText("操作");
		gm.setColumnHidden(0, true);
		gm.setColumnWidth(0, 50);
		gm.setColumnWidth(1, 220);
		gm.setColumnWidth(2, 180);
		gm.setColumnWidth(3, 140);
		gm.setColumnWidth(4, 140);
		gm.setColumnWidth(5, 140);
		gm.setColumnWidth(6, 186);
		pageIndex =0;
		initPageCmp();
		
	}
	
	
	private void initCarList(List<CarInfo> carList){


		if(carList != null &&carList.size()>0){
			gm.setRowCount(carList.size()+1);
			for(int i =0; i < carList.size(); i++){
				gm.setRowHeight(i+1, 100);
				Composite picCmp = new Composite(grid2);
				if(carList.get(i).getImage()!=null){
					picCmp.setBackimage(DataImageDescriptor.createImageDescriptor(carList.get(i).getImage()));	
				}
				gm.getGridCell(1, i+1).setControl(picCmp);
				gm.getGridCell(2, i+1).setShowText(carList.get(i).getBrand()+"   --    "+carList.get(i).getModel());
				gm.getGridCell(3, i+1).setShowText(carList.get(i).getStatus());
				gm.getGridCell(4, i+1).setShowText(String.valueOf(carList.get(i).getRentDay()));
				if(days==0){
					gm.getGridCell(5, i+1).setShowText(String.valueOf(carList.get(i).getRentDay()));
					
				}else{
					gm.getGridCell(5, i+1).setShowText(String.valueOf(carList.get(i).getRentDay()*days));
				}
				
				
				Composite orderBtnCmp =new Composite(grid2);
				Button orderBtn  =new Button(orderBtnCmp);
				if("空闲".equals(carList.get(i).getStatus())){
					orderBtn.setText("预约");
					orderBtn.setSize(100, 30);
					orderBtn.setLocation(45, 35);
					orderBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(days==0){
								MessageDialog.alert("租车时间最短一天！");
								return;
							}
							

							CarInfo carInfo = getSelectCarInfo();
							//MessageDialog.alert("选中的品牌"+carInfo.getBrand());
							AddOrderTask task = new AddOrderTask();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
							String orderNum = sdf.format(new Date());
							
							task.setOrderNum(orderNum);
							task.setPlateNum(carInfo.getPlateNum());
							task.setRentUser(userInfo.getRecid());
							task.setStartTime(getCarDateDp.getDate());
							task.setEndTime(backCarDateDp.getDate());
							task.setRent(carInfo.getRentDay()*days); //天数未加
							task.setStatus(1);

							getContext().handle(task, AddOrderTask.orderManage.add);
							
							GUID carId =carInfo.getRecid();
							
							CarManageTask carManageTask = new CarManageTask();
							carManageTask.setRecid(carId);
							carManageTask.setStatus("预约");
							MessageDialog.alert("预约成功，你可以在订单中心查看详细信息！");
							//updateCarStatus
							getContext().handle(carManageTask, CarManageTask.carM.updates);
							pageIndex =0;
							initPageCmp();
							
							
						}
					});
					
					
				}
				gm.getGridCell(6, i+1).setControl(orderBtnCmp);	
				

				
				//setData
				gm.getGridCell(1, i+1).setData(carList.get(i));
				 Object obj = gm.getGridCell(1, i+1).getData();
			}
			
		}
		carListCmp.layout();
		
	}
	
	
	private List<CarInfo> getCarInfoBySelectCon(int flag){
		
		final GetCarInfoByBookingConKey key  =new  GetCarInfoByBookingConKey();
		
		Control[] brands = brandBtnCmp.getChildren();
		
		List<String> brandlist = new ArrayList<String>();
		
		for(int i=0; i< brands.length;i++){
			Button brandBtn  = (Button) brands[i];
			if(brandBtn.getSelection()){
				brandlist.add(brandBtn.getText());
			}
		}
		key.setBrand(brandlist);

		key.setFlag(flag);
		key.setPageSize(pageSize);
		key.setPageIndex(pageIndex);
		
		
		if(rentBtn.getSelection()){
			key.setRentDay(0);
		}else if(rentBtn1.getSelection()){
			key.setRentDay(1);
		}else if(rentBtn2.getSelection()){
			key.setRentDay(2);
		}else if(rentBtn3.getSelection()){
			key.setRentDay(3);
		}else if(rentBtn4.getSelection()){
			key.setRentDay(4);
		}
		if(statusBtn.getSelection()){
			key.setStatus("空闲")	;
		}
		
		
		if(sy ==0){
			key.setOrderByRentDay("desc");;
			
		}else if(sy ==1){
			key.setOrderByRentDay("asc");
		}
	//	key.setOrderByRentDay("asc");
		
				
		List<CarInfo> carList = new ArrayList<CarInfo>();
		carList =getContext().getList(CarInfo.class,key);				
		return carList;
	}
	private CarInfo getSelectCarInfo(){
		//获取选中的行记录
		GridCell gc = grid2.getCurrentCell();
		//选择的网格坐标
		int rowIndenx = gc.getRowIndex();
		//获取数据
		Object obj = gm.getGridCell(1, rowIndenx).getData();
		CarInfo carInfo = null;
		if(obj!=null){
			carInfo = (CarInfo) obj;
		}
		return carInfo;
	}
	private void initPageCmp(){
		if(pageCmp!=null){
			pageCmp.clear();
		}
		Pager pager = new Pager(pageCmp, PagerType.DEFAULT);
		List<CarInfo> allCar  =getCarInfoBySelectCon(0);
		
		int rowCount =allCar.size();
		pager.setPageCount(rowCount/pageSize + ((rowCount % pageSize == 0)?0:1));
		List<CarInfo> carPagerList = getCarInfoBySelectCon(1);
		initCarList(carPagerList);
		
		pager.addPageChangeListener(new PageChangeListener() {
			
			@Override
			public void onPageChanged(Pager pager, int index) {
				// TODO Auto-generated method stub
				pageIndex =index;
				List<CarInfo> carPagerList = getCarInfoBySelectCon(1);
				initCarList(carPagerList);
				
			}
		});
		pageCmp.layout();
		
	}
}
