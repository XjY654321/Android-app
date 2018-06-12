package com.zjw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zjw.db.DbHelper;
import com.zjw.model.KCInfo;
import com.zjw.model.SrInfo;
import com.zjw.model.UserInfo;
import com.zjw.utils.TimeUtils;

public class SrDAO {
	public  static int add(String sr){
		String sql="insert into srinfo(sr,time)values('"+sr+"','"+TimeUtils.getNowTime()+"')";			
		return DbHelper.executeSql(sql);		
	}
public static List<SrInfo> getAllInfo(){
		
		String sql="select * from srinfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		List<SrInfo> list =new ArrayList<SrInfo>();
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					SrInfo srInfo=new SrInfo();
					srInfo.setId(rs.getString("id"));
					srInfo.setSr(rs.getString("sr"));
					srInfo.setTime(rs.getString("time"));
					list.add(srInfo);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			DbHelper.closeConnectionAndStatement(con,st);
		}
		return list;
	}
}
