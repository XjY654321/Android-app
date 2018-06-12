package com.zjw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zjw.db.DbHelper;
import com.zjw.model.KCInfo;
import com.zjw.model.Lsinfo;
import com.zjw.utils.TimeUtils;

public class LSDAO {
	public static int add (String sid,String sl,String bq,String name,String mobile){
		String sql="insert into lsinfo(time,kcid,sl,bq,name,mobile)values('"+TimeUtils.getNowTime()+"','"+sid+"','"+sl+"','"+bq+"','"+name +"','"+mobile+"')";	

		return DbHelper.executeSql(sql);		
	}
public static List<Lsinfo> getAllInfo(String bq){
		
		String sql="select * from lsinfo";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		List<Lsinfo> list =new ArrayList<Lsinfo>();
		try{
			con = DbHelper.getconnection();
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs!=null){
				while(rs.next()){
					if (bq.equals(rs.getString("bq"))) {
						Lsinfo l=new Lsinfo();
						l.setKcid(rs.getString("kcid"));
						l.setKcInfo(KCDAO.getInfo(l.getKcid()));
						l.setSl(rs.getString("sl"));
						l.setTime(rs.getString("time"));
						l.setName(rs.getString("name"));
						l.setMobile(rs.getString("mobile"));
						list.add(l);
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
public static List<Lsinfo> getAllInfoByID(String bq,String kcid){
	
	String sql="select * from lsinfo";
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	List<Lsinfo> list =new ArrayList<Lsinfo>();
	try{
		con = DbHelper.getconnection();
		st = con.createStatement();
		rs=st.executeQuery(sql);
		if(rs!=null){
			while(rs.next()){
				if (bq.equals(rs.getString("bq")) && kcid.equals(rs.getString("kcid"))) {
					Lsinfo l=new Lsinfo();
					l.setKcid(rs.getString("kcid"));
					l.setKcInfo(KCDAO.getInfo(l.getKcid()));
					l.setSl(rs.getString("sl"));
					l.setTime(rs.getString("time"));
					l.setName(rs.getString("name"));
					l.setMobile(rs.getString("mobile"));
					list.add(l);
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
public static void del(String id){
		String sql="DELETE FROM lsinfo WHERE id ='"+id+"'";
		DbHelper.executeSql(sql);
}
}
