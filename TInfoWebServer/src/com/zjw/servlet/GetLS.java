package com.zjw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.zjw.dao.KCDAO;
import com.zjw.dao.LSDAO;
import com.zjw.dao.UserInfoDAO;
import com.zjw.model.KCInfo;
import com.zjw.model.Lsinfo;

public class GetLS extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetLS() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");

		String bq=new String(request.getParameter("bq").getBytes("iso-8859-1"),"utf-8");
		String type=new String(request.getParameter("type"));
		if(Integer.parseInt(type) == 0){
			out.print(JSONArray.fromObject(LSDAO.getAllInfo(bq)));
		}else if(Integer.parseInt(type) == 1){
			String hh=new String(request.getParameter("hh").getBytes("iso-8859-1"),"utf-8");
			String gg=new String(request.getParameter("gg").getBytes("iso-8859-1"),"utf-8");
			KCInfo  kcInfo= KCDAO.getInfoFromGGandHH(gg,hh);
			if(null == kcInfo.getId() || "".equals(kcInfo.getId())){
				List<Lsinfo> list = new ArrayList();
				out.print(JSONArray.fromObject(list));
			}else {
				out.print(JSONArray.fromObject(LSDAO.getAllInfoByID(bq,kcInfo.getId())));
				
			}
			
		}
		
		
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
