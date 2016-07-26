package com.jiuqi.dna.xz4.zjy.ui;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.custom.combo.ComboList;
import com.jiuqi.dna.ui.custom.combo.DatePicker;
import com.jiuqi.dna.ui.custom.pageBar.PageChangeListener;
import com.jiuqi.dna.ui.custom.pageBar.Pager;
import com.jiuqi.dna.ui.custom.pageBar.Pager.PagerType;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
//import com.jiuqi.dna.ui.wt.graphics.CBorder;
import com.jiuqi.dna.ui.wt.graphics.DataImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.FileImageDescriptor;
import com.jiuqi.dna.ui.wt.grid2.Grid2;
import com.jiuqi.dna.ui.wt.grid2.GridCell;
import com.jiuqi.dna.ui.wt.grid2.GridModel;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.layouts.GridData;
import com.jiuqi.dna.ui.wt.layouts.GridLayout;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.MessageDialog;
import com.jiuqi.dna.ui.wt.widgets.Page;
import com.jiuqi.dna.ui.wt.widgets.Shell;
import com.jiuqi.dna.ui.wt.widgets.Text;
import com.jiuqi.dna.xz4.zjy.info.CarInfo;
import com.jiuqi.dna.xz4.zjy.key.GetCarInfoBySelectKey;
import com.jiuqi.dna.xz4.zjy.task.CarManageTask;
import com.jiuqi.dna.xz4.zjy.task.CarManageTask.carM;

public class CarManagePage extends Page{
	Composite topCmp,carInfoCmp,btnCmp,carListCmp,PagebarCmp,bottomCmp , pageCmp;
	GridModel gm;
	Grid2 grid2;
	int pageSize = 4 ;
	int pageIndex = 0;
	//Label plateNumLbl,brandLbl,modelLbl,imageLbl,factoryDateLbl,rentDayLbl,statusLbl;
	Text plateNumTxt,brandTxt,modelTxt,rentDayTxt;
	FileChooser imageFc;
	DatePicker factoryDp;
	ComboList statusCb;
	public CarManagePage(Composite parent) {
		
		super(parent);
		// TODO Auto-generated constructor stub
		initPage();
		this.setLayout(new GridLayout());
	}

	private void initPage() {
		// TODO Auto-generated method stub
		topCmp =new Composite(this);		
		GridData topGd= new GridData();
		topGd.heightHint = 66; 
		topGd.widthHint = 1024;
		topGd.grabExcessHorizontalSpace=true;
		topGd.horizontalAlignment = JWT.CENTER;
		
		topCmp.setLayoutData(topGd);
		//topCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		topCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/mainTop.jpg"));
		
		
		//车辆信息录入容器
		carInfoCmp =new Composite(this);
		GridLayout gl1 = new GridLayout(8);
		gl1.horizontalSpacing = 10;
		GridData gd1 = new GridData();
		gd1.widthHint = 1024;
		gd1.heightHint = 70;
		gd1.grabExcessHorizontalSpace = true;
		gd1.horizontalAlignment =JWT.CENTER;
		carInfoCmp.setLayout(gl1);
		carInfoCmp.setLayoutData(gd1);
		//carInfoCmp.setBorder(new CBorder());
		initCarInfoCmp();
		
		//按钮容器
		 btnCmp = new Composite(this);
		 btnCmp.setLayout(gl1);
		 GridData gd2 =new GridData();
		 gd2.widthHint = 1024;
		 gd2.heightHint = 30;
		 gd2.grabExcessHorizontalSpace = true;
		 gd2.horizontalAlignment =JWT.CENTER;
		 btnCmp.setLayoutData(gd2);
		// btnCmp.setBorder(new CBorder());
		 initButton();

		//车辆展示列表容器
		 carListCmp =new Composite(this);
		 carListCmp.setLayout(new FillLayout());
		 GridData gd3 =new GridData();
		 gd3.widthHint = 1024;
		 gd3.heightHint = 450;
		 gd3.grabExcessHorizontalSpace = true;
		 gd3.horizontalAlignment =JWT.CENTER;
		 carListCmp.setLayoutData(gd3);
		// carListCmp.setBorder(new CBorder());
		// initcarListCmp();
		 
		 //分页控件容器
		 PagebarCmp =new Composite(this);
		 PagebarCmp.setLayout(new GridLayout());
		 GridData gd4 =new GridData();
		 gd4.widthHint = 1024;
		 gd4.heightHint = 30;
		 gd4.grabExcessHorizontalSpace = true;
		 gd4.horizontalAlignment =JWT.CENTER;
		 PagebarCmp.setLayoutData(gd4); 
		 // PagebarCmp.setBorder(new CBorder());
		 
		 pageCmp =new Composite(PagebarCmp);
		 pageCmp.setLayout(new FillLayout());
		 GridData gdpage =new GridData();
		 gdpage.widthHint = 1000;
		 gdpage.heightHint = 25;
		 gdpage.grabExcessHorizontalSpace = true;
		 gdpage.horizontalAlignment =JWT.RIGHT;
		 pageCmp.setLayoutData(gdpage); 
		 
		 
		 
		 initcarListCmp();
		 initPageCmp();
		 
		 
		
		bottomCmp = new Composite(this);
		GridData bottomGd= new GridData();
		bottomGd.heightHint = 130; 
		bottomGd.widthHint = 1024;
		bottomGd.grabExcessHorizontalSpace=true;
		bottomGd.horizontalAlignment = JWT.CENTER;		
		bottomCmp.setLayoutData(bottomGd);
		//bottomCmp.setBorder(new CBorder(0, JWT.BORDER_STYLE_SOLID, 0x000000));
		bottomCmp.setBackimage(FileImageDescriptor.createImageDescriptor("com.jiuqi.dna.xz4.zjy.ui", "images/loginBottom.jpg"));
		
	}

	

	

	//初始化CarinfoCmp
	private void initCarInfoCmp() {
		// TODO Auto-generated method stub
		Label plateNumLbl,brandLbl,modelLbl,imageLbl,factoryDateLbl,rentDayLbl,statusLbl;
		
		plateNumLbl = new Label(carInfoCmp);
		plateNumLbl.setText("车牌号：");
		plateNumTxt = new Text(carInfoCmp);
		
		brandLbl = new Label(carInfoCmp);
		brandLbl.setText("品牌：");
		brandTxt = new Text(carInfoCmp);
		
		modelLbl = new Label(carInfoCmp);
		modelLbl.setText("型号：");
		modelTxt = new Text(carInfoCmp);
						
		rentDayLbl = new Label(carInfoCmp);
		rentDayLbl.setText("日租金：");
		rentDayTxt = new Text(carInfoCmp);
		
		factoryDateLbl = new Label(carInfoCmp);
		factoryDateLbl.setText("出厂时间：");
		factoryDp = new DatePicker(carInfoCmp);
		factoryDp.setDate(new Date());
		
		//Composite imageCmp =new Composite(carInfoCmp);
		imageLbl = new Label(carInfoCmp);
		imageLbl.setText("图片：");
		imageFc = new FileChooser(carInfoCmp);
		
		statusLbl = new Label(carInfoCmp);
		statusLbl.setText("状态：");
		statusCb = new ComboList(carInfoCmp);
		statusCb.addItem("空闲");
		statusCb.addItem("预约");
		statusCb.addItem("运营");
		statusCb.addItem("停用");
		
		Composite deleteCmp =new Composite(carInfoCmp);
		//deleteCmp.setBorder(new CBorder());
		Button resetBtn = new Button(deleteCmp);
		resetBtn.setLocation(1, 1);
		resetBtn.setSize(60, 25);
		resetBtn.setText("重置");
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//Text plateNumTxt,brandTxt,modelTxt,rentDayTxt;
				plateNumTxt.setText("");
				brandTxt.setText("");
				modelTxt.setText("");
				rentDayTxt.setText("");
				factoryDp.setDate(new Date());
				statusCb.setText("");
			}
		});
		
		
		
	}
	/**
	 * 初始化按钮控件
	 */
	private void initButton() {
		// TODO Auto-generated method stub
		
		Button addBtn = new Button(btnCmp);
		addBtn.setText("新增");
		//新增事件
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String plateNum ="";
				if(plateNumTxt.getText()!=null||"".equals(plateNumTxt.getText())){
					plateNum = plateNumTxt.getText();
					
				}else{
					MessageDialog.alert("车牌号不能为空！");
					return;
				}
				String brand ="";
				if(brandTxt.getText()!=null||"".equals(brandTxt.getText())){
					brand = brandTxt.getText();
					
				}else{
					MessageDialog.alert("品牌不能为空！");
					return;
				}
				
				String model ="";
				if(modelTxt.getText()!=null||"".equals(modelTxt.getText())){
					model = modelTxt.getText();
					
				}else{
					MessageDialog.alert("型号不能为空！");
					return;
				}
				//Double rent =0.0;
				String rent ="";
				if(rentDayTxt.getText()!=null||"".equals(rentDayTxt.getText())){
					rent = rentDayTxt.getText();
					
				}else{
					MessageDialog.alert("日租金不能为空！");
					return;
				}
				
				Date date;
				
				if(factoryDp.getDate()!=null||"".equals(factoryDp.getText())){
					date = factoryDp.getDate();
					
				}else{
					MessageDialog.alert("出厂日期不能为空！");
					return;
				}
				
				String status="";
				if(statusCb.getText()!=null||"".equals(statusCb.getText())){
					status = statusCb.getText();
					
				}else{
					MessageDialog.alert("状态不能为空！");
					return;
				}
				
				
				
				CarManageTask task =new CarManageTask();
				task.setPlateNum(plateNum);
				task.setBrand(brand);
				task.setModel(model);
				task.setRentDay(Double.valueOf(rent));
				task.setFactoryDate(date);
				task.setStatus(status);
				
				byte[] temp =new byte[512];
				InputStream is =null;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				String fileName =imageFc.getFileName();
				try{
					is =imageFc.getInputStream(fileName);
					//int rc =0;
					while((is.read(temp,0,512))>0){
						baos.write(temp, 0, 512);
						
					}
					task.setImage(baos.toByteArray());
				}catch (IOException e){
					e.printStackTrace();
				}
				try{
					is.close();
				} catch(IOException e){
					e.printStackTrace();
				}
				
				//通过枚举来调用任务处理器
				getContext().handle(task,CarManageTask.carM.add);
				
				
				//刷新界面
				plateNumTxt.setText("");
				brandTxt.setText("");
				modelTxt.setText("");
				rentDayTxt.setText("");
				factoryDp.setDate(null);
				statusCb.setText("");
				//initCarInfoCmp();
//				List<CarInfo> carList = initCarBySelect(1);
//				initCarList(carList);
				initPageCmp();
				carListCmp.layout();				
				
			}
			
		});
		
		//设置关联
		imageFc.setRelativeTarget(addBtn, ActionListener.class);
		
		
		Button queryBtn = new Button(btnCmp);
		queryBtn.setText("查询");
		queryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				List<CarInfo> carList = initCarBySelect(1);
//				initCarList(carList);
				initPageCmp();
				carListCmp.layout();
				
			}
		});
		
		Button updateBtn = new Button(btnCmp);
		updateBtn.setText("变更");
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				// TODO Auto-generated method stub
				
				//变更
				
				String plateNum ="";
				if(plateNumTxt.getText()!=null||"".equals(plateNumTxt.getText())){
					plateNum = plateNumTxt.getText();
					
				}else{
					MessageDialog.alert("车牌号不能为空！");
					return;
				}
				String brand ="";
				if(brandTxt.getText()!=null||"".equals(brandTxt.getText())){
					brand = brandTxt.getText();
					
				}else{
					MessageDialog.alert("品牌不能为空！");
					return;
				}
				
				String model ="";
				if(modelTxt.getText()!=null||"".equals(modelTxt.getText())){
					model = modelTxt.getText();
					
				}else{
					MessageDialog.alert("型号不能为空！");
					return;
				}
				//Double rent =0.0;
				String rent ="0.0";
				if(rentDayTxt.getText()!=null||"".equals(rentDayTxt.getText())){
					rent = rentDayTxt.getText();
					
				}else{
					MessageDialog.alert("日租金不能为空！");
					return;
				}
				
				Date date;
				
				if(factoryDp.getDate()!=null||"".equals(factoryDp.getText())){
					date = factoryDp.getDate();
					
				}else{
					MessageDialog.alert("出厂日期不能为空！");
					return;
				}
				
				String status="";
				if(statusCb.getText()!=null||"".equals(statusCb.getText())){
					status = statusCb.getText();
					
				}else{
					MessageDialog.alert("状态不能为空！");
					return;
				}
				
				
				CarManageTask updateTask = new CarManageTask();
				//CarManageTask task =new CarManageTask();
				updateTask.setPlateNum(plateNum);
				updateTask.setBrand(brand);
				updateTask.setModel(model);
				updateTask.setRentDay(Double.valueOf(rent));
				updateTask.setFactoryDate(date);
				updateTask.setStatus(status);
				

																				
				CarInfo carInfo = getSelectCarInfo();
				GUID recid = carInfo.getRecid();

				updateTask.setRecid(recid);
				
				
				getContext().handle(updateTask, CarManageTask.carM.update);

				//刷新界面
				plateNumTxt.setText("");
				brandTxt.setText("");
				modelTxt.setText("");
				rentDayTxt.setText("");
				factoryDp.setDate(null);
				statusCb.setText("");

				initPageCmp();
				carListCmp.layout();	
				
			}
		});
		

		
		Button deleteBtn = new Button(btnCmp);
		deleteBtn.setText("删除");
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//CarInfo
				CarInfo carInfo = getSelectCarInfo();
				//
				GUID recid = carInfo.getRecid();
				
				CarManageTask deleteTask = new CarManageTask();
				deleteTask.setRecid(recid);
				getContext().handle(deleteTask, CarManageTask.carM.delete);
				
				plateNumTxt.setText("");
				brandTxt.setText("");
				modelTxt.setText("");
				rentDayTxt.setText("");
				factoryDp.setDate(null);
				statusCb.setText("");

				initPageCmp();
				carListCmp.layout();	
			}
		});
		
		
		
	}
	/**
	 * 初始化车辆列表
	 */
	private void initcarListCmp() {
		// TODO Auto-generated method stub
		
		grid2 = new Grid2(carListCmp);
		gm = grid2.getModel();
		gm.setColumnCount(8);
		gm.getGridCell(0, 0).setShowText("选择");
		gm.getGridCell(1, 0).setShowText("汽车预览");
		gm.getGridCell(2, 0).setShowText("车牌号");
		gm.getGridCell(3, 0).setShowText("品牌");
		gm.getGridCell(4, 0).setShowText("型号");
		gm.getGridCell(5, 0).setShowText("出厂日期");
		gm.getGridCell(6, 0).setShowText("日租金");
		gm.getGridCell(7, 0).setShowText("车辆状态");
		gm.setColumnWidth(1, 220);
		gm.setColumnWidth(5, 180);
		
		initPageCmp();

		
	}
	private void initCarList(List<CarInfo> carList){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");

		if(carList != null &&carList.size()>0){
			gm.setRowCount(carList.size()+1);
			for(int i =0; i < carList.size(); i++){
				gm.setRowHeight(i+1, 100);
				Composite picCmp = new Composite(grid2);
				if(carList.get(i).getImage()!=null){
					picCmp.setBackimage(DataImageDescriptor.createImageDescriptor(carList.get(i).getImage()));	
				}
				gm.getGridCell(1, i+1).setControl(picCmp);
				gm.getGridCell(2, i+1).setShowText(carList.get(i).getPlateNum());
				gm.getGridCell(3, i+1).setShowText(carList.get(i).getBrand());
				gm.getGridCell(4, i+1).setShowText(carList.get(i).getModel());
				gm.getGridCell(5, i+1).setShowText(sdf.format(carList.get(i).getFactoryDate()));
				gm.getGridCell(6, i+1).setShowText(String.valueOf(carList.get(i).getRentDay()));
				gm.getGridCell(7, i+1).setShowText(carList.get(i).getStatus());
				
				//setData
				gm.getGridCell(1, i+1).setData(carList.get(i));
				Object obj = gm.getGridCell(1, i+1).getData();
			}
			
		}
		carListCmp.layout();
		
	}
	//通过填写的条件查询汽车列表
	private List<CarInfo> initCarBySelect(int flag){
		GetCarInfoBySelectKey key = new GetCarInfoBySelectKey();
		String plateNum ="";
		String brand="";
		String model="";
		
		if(plateNumTxt.getText()!=null||"".equals(plateNumTxt.getText())){
			plateNum =plateNumTxt.getText();			
		}
		key.setPlateNum(plateNum);
		
		key.setFlag(flag);
		key.setPageSize(pageSize);
		key.setPageIndex(pageIndex);
		if(brandTxt.getText()!=null||"".equals(brandTxt.getText())){
			brand =brandTxt.getText();			
		}
		key.setBrand(brand);
		
		
		if(modelTxt.getText()!=null||"".equals(modelTxt.getText())){
			model =modelTxt.getText();			
		}
		key.setModel(model);
		
		
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
		List<CarInfo> allCar  =initCarBySelect(0);
		
		int rowCount =allCar.size();
		pager.setPageCount(rowCount/pageSize + ((rowCount % pageSize == 0)?0:1));
		List<CarInfo> carPagerList = initCarBySelect(1);
		initCarList(carPagerList);
		
		pager.addPageChangeListener(new PageChangeListener() {
			
			@Override
			public void onPageChanged(Pager pager, int index) {
				// TODO Auto-generated method stub
				pageIndex =index;
				List<CarInfo> carPagerList = initCarBySelect(1);
				initCarList(carPagerList);
				
			}
		});
		pageCmp.layout();
		
	}
	
}
