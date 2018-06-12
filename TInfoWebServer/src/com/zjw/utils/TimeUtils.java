package com.zjw.utils;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	   public static long getDaySub(String beginDateStr,String endDateStr)
	    {	//日期相减
	        long day=0;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
	        Date beginDate;
	        Date endDate;
	        try
	        {
	            beginDate = format.parse(beginDateStr);
	            endDate= format.parse(endDateStr);    
	            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);    
	            System.out.println("相隔的天数="+day);   
	        } catch (ParseException e)
	        {
	            // TODO 自动生成 catch 块
	            e.printStackTrace();
	        }   
	        return day;
	    }
	   public static String addtime(String return_time,int month){
		   //增加一个月时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
			    Date myDate = df.parse(return_time);
			    Calendar c = Calendar.getInstance();
			    c.setTime(myDate);
			    c.add(Calendar.MONTH, month);
			    myDate = c.getTime();
			    return_time = df.format(myDate);
		
			} catch (ParseException e1) {
			    e1.printStackTrace();
			}
			return return_time;
	   }
	   public static String getNowTime(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowtime =df.format(new Date());
			return nowtime;
	   }
}
