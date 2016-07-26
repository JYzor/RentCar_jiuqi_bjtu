package com.jiuqi.dna.xz4.zjy.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.xz4.zjy.impl.CarImpl;
import com.jiuqi.dna.xz4.zjy.impl.OrderImpl;
import com.jiuqi.dna.xz4.zjy.info.CarInfo;
import com.jiuqi.dna.xz4.zjy.info.OrderInfo;
import com.jiuqi.dna.xz4.zjy.key.GetCarInfoByBookingConKey;
import com.jiuqi.dna.xz4.zjy.key.GetCarInfoBySelectKey;
import com.jiuqi.dna.xz4.zjy.task.AddOrderTask;
import com.jiuqi.dna.xz4.zjy.task.AddOrderTask.orderManage;
import com.jiuqi.dna.xz4.zjy.task.CarManageTask;
import com.jiuqi.dna.xz4.zjy.task.CarManageTask.carM;


public class CarManageService extends Service{

	protected CarManageService() {
		super("sss");
		// TODO Auto-generated constructor stub
	}
	@Publish
	protected class AddCar extends TaskMethodHandler<CarManageTask,CarManageTask.carM>{

		protected AddCar() {
			//枚举
			super(CarManageTask.carM.add);
		}
	
		@Override
		protected void handle(Context context, CarManageTask task)
				throws Throwable {
			// TODO Auto-generated method stub
			String dnaSql ="define insert addCar(@recid guid,@plateNum string,@brand string,@model string,@image bytes,@factory date,"
					+ " @rentDay double,@status string)"
					+ " begin"
					+ " insert into g_car_info(RECID,PLATE_NUM,BRAND,MODEL,IMAGE,FACTORY_DATE,RENT_DAY,CAR_STATE)"
					+ " values(@recid ,@plateNum ,@brand ,@model ,@image ,@factory , @rentDay ,@status )"
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			dbc.setArgumentValues(context.newRECID(),task.getPlateNum(),task.getBrand(),task.getModel(),task.getImage(),task.getFactoryDate(),
					task.getRentDay(),task.getStatus());
			dbc.executeUpdate();
			dbc.unuse();
			
		}
		
	}

	@Publish
	protected class queryCar extends OneKeyResultListProvider<CarInfo, GetCarInfoBySelectKey>{

		@Override
		protected void provide(Context context, GetCarInfoBySelectKey key,
				List<CarInfo> resultList) throws Throwable {
			// TODO Auto-generated method stub
			String selectSql =" where 1=1";
			if(key.getClass()!=null && !"".equals(key.getPlateNum())){
				selectSql += "  AND g.PLATE_NUM like \'%"+key.getPlateNum()+"%\'";
			}
			if(key.getClass()!=null && !"".equals(key.getBrand())){
				selectSql += "  AND g.BRAND like \'%"+key.getBrand()+"%\'";
			}
			if(key.getClass()!=null && !"".equals(key.getModel())){
				selectSql += "  AND g.MODEL like \'%"+key.getModel()+"%\'";
			}
			String dnaSql ="define query queryCarList()"
					+ " begin"
					+ " select"
					+ " g.RECID as recid,"
					+ " g.PLATE_NUM as plateNum,"
					+ " g.BRAND as brand,"
					+ " g.MODEL as model,"
					+ " g.IMAGE as image,"
					+ " g.FACTORY_DATE as factory,"
					+ " g.RENT_DAY as rentDay,"
					+ " g.CAR_STATE as status"
					+ " from g_car_info as g"
					+ selectSql
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			RecordSet rs = null;
			if(key.getFlag()==0){
				rs =dbc.executeQuery();
			}else if(key.getFlag()==1){
				rs =dbc.executeQueryLimit(key.getPageIndex()*key.getPageSize(), key.getPageSize());
			}
					   
			List<CarInfo> list = new ArrayList<CarInfo>();
			
			while(rs.next()){
				CarImpl impl = new CarImpl();
				impl.setRecid(rs.getFields().get(0).getGUID());
				impl.setPlateNum(rs.getFields().get(1).getString());
				impl.setBrand(rs.getFields().get(2).getString());
				impl.setModel(rs.getFields().get(3).getString());
				impl.setImage(rs.getFields().get(4).getBytes());
				impl.setFactoryDate(new Date(rs.getFields().get(5).getDate()));
				impl.setRentDay(rs.getFields().get(6).getDouble());
				impl.setStatus(rs.getFields().get(7).getString());
				list.add(impl);
				
			}
			resultList.addAll(list);
			dbc.unuse();
			
			
		}
		
	}
	
	@Publish
	protected class deleteCar extends TaskMethodHandler<CarManageTask,CarManageTask.carM>{

		protected deleteCar() {
			super(CarManageTask.carM.delete);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void handle(Context context, CarManageTask task)
				throws Throwable {
			// TODO Auto-generated method stub
			
			String dnaSql ="define delete deleteCar(@recid guid)"
					+ " begin"
					+ " delete from g_car_info as g"
					+ " where g.RECID = @recid"
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			dbc.setArgumentValues(task.getRecid());
			dbc.executeUpdate();
			dbc.unuse();
			
		}
		
	}
	@Publish
	protected class updateCar extends TaskMethodHandler<CarManageTask,CarManageTask.carM>{
		protected updateCar() {
			//枚举
			super(CarManageTask.carM.update);
		}
	
		@Override
		protected void handle(Context context, CarManageTask task)
				throws Throwable {
			// TODO Auto-generated method stub
			String dnaSql ="define update updateCar(@plateNum string,@brand string,@model string,@factory date,"
					+ " @rentDay double,@status string,@recid guid)"
					+ " begin"
					+ " update g_car_info as g "
					+ " SET PLATE_NUM=@plateNum, BRAND=@brand,  MODEL=@model , FACTORY_DATE=@factory,"
					+ " RENT_DAY=@rentDay , CAR_STATE=@status"
					+ " where g.RECID=@recid"
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			dbc.setArgumentValues(task.getPlateNum(),task.getBrand(),task.getModel(),task.getFactoryDate(),
					task.getRentDay(),task.getStatus(),task.getRecid());
			dbc.executeUpdate();
			dbc.unuse();
			
		}
	}

	
	//更新状态
	
	@Publish
	protected class updateCarStatus extends TaskMethodHandler<CarManageTask,CarManageTask.carM>{
		protected updateCarStatus() {
			//枚举
			super(CarManageTask.carM.updates);
		}
	
		@Override
		protected void handle(Context context, CarManageTask task)
				throws Throwable {
			// TODO Auto-generated method stub
			String dnaSql ="define update updateCar(@status string,@recid guid)"
					+ " begin"
					+ " update g_car_info as g "
					+ " SET CAR_STATE=@status"
					+ " where g.RECID=@recid"
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			dbc.setArgumentValues(task.getStatus(),task.getRecid());
			dbc.executeUpdate();
			dbc.unuse();
			
		}
	}
	
	
	//查询车辆品牌
	@Publish
	protected class queryCarBrand extends ResultListProvider<CarInfo>{

		@Override
		protected void provide(Context context, 
				List<CarInfo> resultList) throws Throwable {
			// TODO Auto-generated method stub
			String dnaSql ="define query queryBrand()"
					+ " begin"
					+ " select distinct g.brand as brand" 
					+ " from g_car_info as g"
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			RecordSet rs = dbc.executeQuery();
					   
			List<CarInfo> list = new ArrayList<CarInfo>();
			
			while(rs.next()){
				CarImpl impl = new CarImpl();
				impl.setBrand(rs.getFields().get(0).getString());
				list.add(impl);
				
			}
			resultList.addAll(list);
			
			dbc.unuse();
			
			
		}
		
	}
	
	
	@Publish
	protected class queryBookingCar extends OneKeyResultListProvider<CarInfo, GetCarInfoByBookingConKey>{

		@Override
		protected void provide(Context context, GetCarInfoByBookingConKey key,
				List<CarInfo> resultList) throws Throwable {
			// TODO Auto-generated method stub
			
			String brand ="";
			String brandSql = "";
			if(key.getBrand()!=null && (key.getBrand().size())>0){
				for(int i = 0;i<key.getBrand().size();i++){
					if(i==0){
						brand = "\'"+key.getBrand().get(i)+"\'";
					}else{
						brand += ",\'"+key.getBrand().get(i)+"\'";
					}
				}
				brandSql = " and g.brand in(" + brand +")";
			}
			//String rentDay = "";
			String rentDaySql= "";
			if(key.getClass()!=null && !"".equals(key.getRentDay())){
				
				if(key.getRentDay()==0){
					rentDaySql = " and 1 =1";
				}
				if(key.getRentDay()==1){
					rentDaySql = " and g.RENT_DAY <= 150";
				}
				if(key.getRentDay()==2){
					rentDaySql = " and g.RENT_DAY > 150 and g.RENT_DAY <= 300";
				}
				if(key.getRentDay()==3){
					rentDaySql = " and g.RENT_DAY > 300 and g.RENT_DAY <= 500";
				}
				if(key.getRentDay()==4){
					rentDaySql = " and g.RENT_DAY > 500";
				}
				
			}

			String rentEnableSql="";
			if(key.getStatus()!=null && !"".equals(key.getStatus())){
				if("空闲".equals(key.getStatus())){
					rentEnableSql = " and g.CAR_STATE ='空闲'";	
				}else{
					rentEnableSql =" and 1 =1";
				}
				
			}
			String orderBySql="";
			if(key.getOrderByRentDay()!=null && !"".equals(key.getOrderByRentDay())){
				
					orderBySql =" order by g.rent_day "
							+ key.getOrderByRentDay()+ " ";
				
				
			}
			
			
			String dnaSql ="define query queryCarList()"
					+ " begin"
					+ " select"
					+ " g.RECID as recid,"
					+ " g.PLATE_NUM as plateNum,"
					+ " g.BRAND as brand,"
					+ " g.MODEL as model,"
					+ " g.IMAGE as image,"
					+ " g.FACTORY_DATE as factory,"
					+ " g.RENT_DAY as rentDay,"
					+ " g.CAR_STATE as status"
					+ " from g_car_info as g"
					+ " where 1=1"
					+ brandSql
					+ rentDaySql
					+ rentEnableSql
					+ orderBySql
					+ " end";
			
			
//			DBCommand dbc = context.prepareStatement(dnaSql);
//			RecordSet rs =dbc.executeQuery();
//
//			
//			
//			
//			
//			List<CarInfo> list = new ArrayList<CarInfo>();
//			
//			while(rs.next()){
//				CarImpl impl = new CarImpl();
//				impl.setRecid(rs.getFields().get(0).getGUID());
//				impl.setPlateNum(rs.getFields().get(1).getString());
//				impl.setBrand(rs.getFields().get(2).getString());
//				impl.setModel(rs.getFields().get(3).getString());
//				impl.setImage(rs.getFields().get(4).getBytes());
//				impl.setFactoryDate(new Date(rs.getFields().get(5).getDate()));
//				impl.setRentDay(rs.getFields().get(6).getDouble());
//				impl.setStatus(rs.getFields().get(7).getString());
//				list.add(impl);
//				
//			}
//			resultList.addAll(list);
//			dbc.unuse();
			
			
			DBCommand dbc = context.prepareStatement(dnaSql);
			RecordSet rs = null;
			if(key.getFlag()==0){
				rs =dbc.executeQuery();
			}else if(key.getFlag()==1){
				rs =dbc.executeQueryLimit(key.getPageIndex()*key.getPageSize(), key.getPageSize());
			}
					   
			List<CarInfo> list = new ArrayList<CarInfo>();
			
			while(rs.next()){
				CarImpl impl = new CarImpl();
				impl.setRecid(rs.getFields().get(0).getGUID());
				impl.setPlateNum(rs.getFields().get(1).getString());
				impl.setBrand(rs.getFields().get(2).getString());
				impl.setModel(rs.getFields().get(3).getString());
				impl.setImage(rs.getFields().get(4).getBytes());
				impl.setFactoryDate(new Date(rs.getFields().get(5).getDate()));
				impl.setRentDay(rs.getFields().get(6).getDouble());
				impl.setStatus(rs.getFields().get(7).getString());
				list.add(impl);
				
			}
			resultList.addAll(list);
			dbc.unuse();
			
			
		}
		
	}
	
	@Publish
	protected class AddOder extends TaskMethodHandler<AddOrderTask, AddOrderTask.orderManage>{

		protected AddOder() {
			super(AddOrderTask.orderManage.add);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void handle(Context context, AddOrderTask task) throws Throwable {
			// TODO Auto-generated method stub
			String dnaSql = "define insert addOrder(@recid guid,@orderNUm string,@plateNum string,"
					+ "@rentUser guid,@start date,@end date,@status int,@rent double)"
					+ " begin"
					+ " insert into g_order_info(recid,ORDER_NUM,PLATE_NUM,RENT_USER,START_TIME,"
						+ "END_TIME,ORDER_STATE,RENT)"
					+ " values(@recid , @orderNUm ,@plateNum ,@rentUser ,@start ,@end ,@status ,@rent )"
					+ " end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			dbc.setArgumentValues(context.newRECID(),task.getOrderNum(),task.getPlateNum(),task.getRentUser(),task.getStartTime(),
					task.getEndTime(),task.getStatus(),task.getRent());
			dbc.executeUpdate();
			dbc.unuse();
			
		}
	}
	
	@Publish
	protected class queryOrder extends OneKeyResultListProvider<OrderInfo, GUID>{

		@Override
		protected void provide(Context context, GUID userId, List<OrderInfo> resultList)
				throws Throwable {
			// TODO Auto-generated method stub
			//recid,ORDER_NUM,PLATE_NUM,RENT_USER,START_TIME,"+ "END_TIME,ORDER_STATE,RENT
			String dnaSql="define query queryOrder(@userId guid)"
					+ " begin"
					+ " select"
					+ " g.ORDER_NUM as ORDER_NUM,"
					+ " g.PLATE_NUM as PLATE_NUM,"
					+ " g.RENT_USER as RENT_USER,"
					+ " g.START_TIME as START_TIME,"
					+ " g.END_TIME as END_TIME,"
					+ " g.ORDER_STATE as ORDER_STATE,"
					+ " g.RENT as RENT"
					+ " from g_order_info as g"
					+ " where g.RENT_USER=@userId"
					+ " end";
			DBCommand dbc =context.prepareStatement(dnaSql);
			dbc.setArgumentValues(userId);
			RecordSet rs = dbc.executeQuery();
			
			List<OrderInfo> list = new ArrayList<OrderInfo>();
			while(rs.next()){
				
				OrderImpl impl =  new OrderImpl();
				impl.setOrderNum(rs.getFields().get(0).getString());
				impl.setPlateNum(rs.getFields().get(1).getString());
				impl.setRentUser(rs.getFields().get(2).getGUID());
				impl.setStartTime(new  Date( rs.getFields().get(3).getDate()));
				impl.setEndTime(new  Date( rs.getFields().get(4).getDate()));
				impl.setStatus(rs.getFields().get(5).getInt());
				impl.setRent(rs.getFields().get(6).getDouble());
				list.add(impl);
			}
			resultList.addAll(list);
			dbc.unuse();
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
		
}
	


