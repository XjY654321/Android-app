package com.zjw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.zjw.db.DbHelper;

import config.Constant;

public class LoginDAO {
	public String checkLogin(String username ,String pwd,String type){
		String sql="select * from userinfo where type = '" + type +"'";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					
					if(username.equals(rs.getString("account")))
					{	
						
						if(pwd.equals(rs.getString("pwd"))){
							
							return rs.getString("id");
							
						}
						else {
							return Constant.RESPONSE_ERROR;
						}
																		
					}
						
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		return Constant.RESPONSE_ERROR;		
	}
	public String checkAdminLogin(String username ,String pwd){
		String sql="select * from admininfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					
					if(username.equals(rs.getString("account")))
					{	
						
						if(pwd.equals(rs.getString("pwd"))){
							return rs.getString("id");
							
						}
						else {
							return Constant.RESPONSE_ERROR;
						}
																		
					}
						
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		return Constant.RESPONSE_ERROR;		
	}
	
}
