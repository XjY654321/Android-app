package com.zjw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zjw.db.DbHelper;
import com.zjw.model.KCInfo;
import com.zjw.model.UserInfo;
import com.zjw.utils.TimeUtils;

public class KCDAO {
	public static int add (KCInfo info){
		String sql="insert into kcinfo(hh,sl,jg,name,time,rkjg,new_goods)values('"+info.getHh()+"','"+info.getSl()+"','"+info.getJg()+"','"+info.getName()+"','"+TimeUtils.getNowTime()+"','"+info.getRkjg()+"','"+info.getNew_goods()+"')";			
		return DbHelper.executeSql(sql);		
	}
public static List<KCInfo> getAllInfo(){
		
		String sql="select * from kcinfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		List<KCInfo> list =new ArrayList<KCInfo>();
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					KCInfo k=new KCInfo();
					k.setHh(rs.getString("hh"));
					k.setId(rs.getString("id"));
					k.setJg(rs.getString("jg"));
					k.setName(rs.getString("name"));
					k.setTime(rs.getString("time"));
					k.setSl(rs.getString("sl"));
					k.setRkjg(rs.getString("rkjg"));
					k.setNew_goods(rs.getString("new_goods"));
					list.add(k);
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
public static void del(String id){
		String sql="DELETE FROM kcinfo WHERE id ='"+id+"'";
		DbHelper.executeSql(sql);
}
public static KCInfo getInfo(String id){
	
	String sql="select * from kcinfo where id ='"+id+"'";
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	//List<UserInfo> list =new ArrayList<UserInfo>();
	KCInfo k=new KCInfo();

	try{
		con = DbHelper.getconnection();
		st = con.createStatement();
		rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){

				k.setHh(rs.getString("hh"));
				k.setId(rs.getString("id"));
				k.setJg(rs.getString("jg"));
				k.setName(rs.getString("name"));
				k.setTime(rs.getString("time"));
				k.setSl(rs.getString("sl"));
				k.setRkjg(rs.getString("rkjg"));
				k.setNew_goods(rs.getString("new_goods"));
			}
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
	finally {
		DbHelper.closeConnectionAndStatement(con,st);
	}
	
	return k;
}
public static KCInfo getInfoFromName(String name){
	
	String sql="select * from kcinfo where name ='"+name+"'";
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	//List<UserInfo> list =new ArrayList<UserInfo>();
	KCInfo k=new KCInfo();

	try{
		con = DbHelper.getconnection();
		st = con.createStatement();
		rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){

				k.setHh(rs.getString("hh"));
				k.setId(rs.getString("id"));
				k.setJg(rs.getString("jg"));
				k.setName(rs.getString("name"));
				k.setTime(rs.getString("time"));
				k.setSl(rs.getString("sl"));
				k.setRkjg(rs.getString("rkjg"));
				k.setNew_goods(rs.getString("new_goods"));
			}
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
	finally {
		DbHelper.closeConnectionAndStatement(con,st);
	}
	
	return k;
}

public static KCInfo getInfoFromGGandHH(String name,String hh){
	
	String sql="select * from kcinfo where name ='"+name+"' and hh = '"+hh+"'";
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	//List<UserInfo> list =new ArrayList<UserInfo>();
	KCInfo k=new KCInfo();

	try{
		con = DbHelper.getconnection();
		st = con.createStatement();
		rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){

				k.setHh(rs.getString("hh"));
				k.setId(rs.getString("id"));
				k.setJg(rs.getString("jg"));
				k.setName(rs.getString("name"));
				k.setTime(rs.getString("time"));
				k.setSl(rs.getString("sl"));
				k.setRkjg(rs.getString("rkjg"));
				k.setNew_goods(rs.getString("new_goods"));
			}
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}
	finally {
		DbHelper.closeConnectionAndStatement(con,st);
	}
	
	return k;
}

	public static void UpdateSL(String id,String sl){
		    int s=Integer.parseInt(sl);
		    int kcs=Integer.parseInt(getInfo(id).getSl());
		    int j=kcs-s;
		   
			String sql="update kcinfo set sl='"+j+"'  where id ='"+id+"'";
			DbHelper.executeSql(sql);
	}
}
