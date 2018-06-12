package com.zjw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zjw.db.DbHelper;
import com.zjw.model.UserInfo;
import com.zjw.servlet.GetUserInfo;

public class UserInfoDAO {
	public  static int addUser (UserInfo userInfo){
		String sql="insert into userinfo(account,pwd)values('"+userInfo.getAccount()+"','"+userInfo.getPwd()+"')";			
		return DbHelper.executeSql(sql);		
	}
	public static int add (UserInfo userInfo){
		String sql="insert into userinfo(account,pwd,tel,addr,name,type)values('"+userInfo.getAccount()+"','"+userInfo.getPwd()+"','"+userInfo.getTel()+"','"+userInfo.getAddr()+"','"+userInfo.getName()+"','"+userInfo.getType()+"')";			
		return DbHelper.executeSql(sql);		
	}
	public static List<UserInfo> getAllInfo(){
		
		String sql="select * from userinfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		List<UserInfo> list =new ArrayList<UserInfo>();
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					UserInfo userInfo =new UserInfo();
					userInfo.setName(rs.getString("name"));
					userInfo.setId(rs.getString("id"));
					userInfo.setAddr(rs.getString("addr"));
					userInfo.setIdcard(rs.getString("idcard"));
					userInfo.setTel(rs.getString("tel"));
					userInfo.setAccount(rs.getString("account"));
					userInfo.setMoney(rs.getString("money"));
					userInfo.setType(rs.getString("type"));
					list.add(userInfo);
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
	public List<UserInfo> getAllInfo(String id){
		if(id==null){
			return null;
		}
		String sql="select * from userinfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		List<UserInfo> list =new ArrayList<UserInfo>();
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					if(id.equals(rs.getString("id"))){
						UserInfo userInfo =new UserInfo();
						userInfo.setName(rs.getString("name"));
						userInfo.setId(id);
						userInfo.setAddr(rs.getString("addr"));
						userInfo.setIdcard(rs.getString("idcard"));
						userInfo.setTel(rs.getString("tel"));
						userInfo.setAccount(rs.getString("account"));
						userInfo.setMoney(rs.getString("money"));
						userInfo.setType(rs.getString("type"));
					list.add(userInfo);
					}
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
public static UserInfo getUserInfo(String id){
		
		String sql="select * from userinfo where id ='"+id+"'";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		//List<UserInfo> list =new ArrayList<UserInfo>();
		UserInfo userInfo =new UserInfo();

		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					userInfo.setName(rs.getString("name"));
					userInfo.setId(id);
					userInfo.setAddr(rs.getString("addr"));
					userInfo.setIdcard(rs.getString("idcard"));
					userInfo.setTel(rs.getString("tel"));
					userInfo.setAccount(rs.getString("account"));
					userInfo.setMoney(rs.getString("money"));
					userInfo.setType(rs.getString("type"));
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			DbHelper.closeConnectionAndStatement(con,st);
		}
		
		return userInfo;
	}

public static List<UserInfo>  getUserInfoByType(String type){
	
	String sql="select * from userinfo where type ='"+type+"'";
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	List<UserInfo> list =new ArrayList<UserInfo>();
	

	try{
		con = DbHelper.getconnection();
		st = con.createStatement();
		rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){
				UserInfo userInfo =new UserInfo();
				userInfo.setName(rs.getString("name"));
				userInfo.setId(rs.getString("id"));
				userInfo.setAddr(rs.getString("addr"));
				userInfo.setIdcard(rs.getString("idcard"));
				userInfo.setTel(rs.getString("tel"));
				userInfo.setAccount(rs.getString("account"));
				userInfo.setMoney(rs.getString("money"));
				userInfo.setType(rs.getString("type"));
				list.add(userInfo);
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
public static UserInfo getAdminInfo(String id){
	
	String sql="select * from admininfo where id ='"+id+"'";
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	//List<UserInfo> list =new ArrayList<UserInfo>();
	UserInfo userInfo =new UserInfo();

	try{
		con = DbHelper.getconnection();
		st = con.createStatement();
		rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){
				userInfo.setName(rs.getString("name"));
				userInfo.setId(id);
				userInfo.setAddr(rs.getString("addr"));
				userInfo.setIdcard(rs.getString("idcard"));
				userInfo.setTel(rs.getString("tel"));
				userInfo.setAccount(rs.getString("account"));
				userInfo.setMoney(rs.getString("money"));
				userInfo.setType(rs.getString("type"));
			}
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
	finally {
		DbHelper.closeConnectionAndStatement(con,st);
	}
	
	return userInfo;
}
	public static Boolean isExistUserName(String param){

		String sql="select * from userinfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					
					if(param.equals(rs.getString("account")))
					{					
						return true;						
					}
						
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		return false;		
	}

	public static int UpdateUserInfo(UserInfo userInfo){
	
		String sql="update userinfo set tel='"+userInfo.getTel()+"'"
				+",name='"+userInfo.getName()+"'"
				+",addr='"+userInfo.getAddr()+"'"
				+",idcard='"+userInfo.getIdcard()+"'"
				+ " where id ='"+userInfo.getId()+"'";
		return DbHelper.executeSql(sql);
		
	}
	public String getUserIDFromUserName(String username){
		String sql="select id from userinfo where username ='"+username+"'";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				rs.next();
				return rs.getString("id");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	public String getUserNameFromid(String id){
		String sql="select * from userinfo where id ='"+id+"'";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				rs.next();
				return rs.getString("username");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
		
	}
	   public void UpdatePwd(String userid,String pwd){
			String sql="update userinfo set pwd='"+pwd+"'where id ='"+userid+"'";
			DbHelper.executeSql(sql);
	   }

	   public static void UpdateMoney(String userid,String mon){
		    int m =Integer.parseInt(getUserInfo(userid).getMoney());
		    int t =m+Integer.parseInt(mon);
			String sql="update userinfo set money='"+t+"'where id ='"+userid+"'";
			DbHelper.executeSql(sql);
	   }
	   public static void UpdateAdminMoney(String userid,String mon){
		    int m =Integer.parseInt(getAdminInfo(userid).getMoney());
		    int t =m+Integer.parseInt(mon);
			String sql="update admininfo set money='"+t+"'where id ='"+userid+"'";
			DbHelper.executeSql(sql);
	   }
	   public static void del(String userid){
			String sql="DELETE FROM userinfo WHERE id ='"+userid+"'";
			DbHelper.executeSql(sql);
	   }

	
}
