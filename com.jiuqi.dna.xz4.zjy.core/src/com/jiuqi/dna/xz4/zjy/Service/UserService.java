package com.jiuqi.dna.xz4.zjy.Service;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.DBCommand;
import com.jiuqi.dna.core.da.RecordSet;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.xz4.zjy.impl.UserImpl;
import com.jiuqi.dna.xz4.zjy.info.UserInfo;
import com.jiuqi.dna.xz4.zjy.key.Loginkey;
import com.jiuqi.dna.xz4.zjy.task.RegistTask;

public class UserService extends Service{

	protected UserService() {
		super("UserService Start!");
		// TODO Auto-generated constructor stub
		
	}
	//注册逻辑
	@Publish
	protected class Regist extends SimpleTaskMethodHandler<RegistTask> {

		
		protected void handle(Context context, RegistTask task) throws Throwable {
			// TODO Auto-generated method stub
			
			String dnaSqlCheck = "define query checkAccount(@account string)"
					+ " begin"
					+ " select g.account from g_user_info as g"
					+ " where g.account = @account"
					+ " end";
			DBCommand dbcCheck = context.prepareStatement(dnaSqlCheck);
			dbcCheck.setArgumentValues(task.getAccout());
			//查询所有
			RecordSet rs = dbcCheck.executeQuery();
			//得到查询的所有的结果集条数
			int accCount =rs.getRecordCount();
			if(accCount>0){
				task.setMessage("该账号已注册，请登录！");							
				return;
			}else{
				
			String dnaSql = "define insert insertUser( @recid guid,@account string,@accountname string,@pass string,@mobile string,@cardNo string,@mail string)"
					+" begin"
					+" insert into g_user_info(recid,account,accountname,pass,mobile,cardNo,mail)"
					+" values(@recid,@account,@accountname,@pass,@mobile,@cardNo,@mail)"
					+" end";
			DBCommand dbc = context.prepareStatement(dnaSql);
			dbc.setArgumentValues(context.newRECID(),task.getAccout(),task.getAccountname(),
					task.getPass(),task.getMobile(),task.getCardNo(),task.getMail());
			dbc.executeUpdate();
			task.setMessage("注册完毕，请登录！");	
			dbc.unuse();
			}
		}
		
	}
	//登录逻辑
	@Publish
	protected class Login extends OneKeyResultProvider<UserInfo, Loginkey>{

		@Override
		protected UserInfo provide(Context context, Loginkey key)
				throws Throwable {
			
			String dnaSql = "define query checkAccount(@account string)"
					+ " begin"
					+ " select g.pass as pass,g.accountname as name,g.recid as recid from g_user_info as g"
					+ " where g.account = @account"
					+ " end";
			DBCommand dbc= context.prepareStatement(dnaSql);
			dbc.setArgumentValues(key.getLoginAcc());
			RecordSet rs = dbc.executeQuery();
			UserImpl impl = new UserImpl();
			while(rs.next()){
				impl.setPass(rs.getFields().get(0).getString());
				impl.setAccountname(rs.getFields().get(1).getString());
				impl.setRecid(rs.getFields().get(2).getGUID());
				
			}
			
			// TODO Auto-generated method stub
			return impl;
		}
		
	}

}
