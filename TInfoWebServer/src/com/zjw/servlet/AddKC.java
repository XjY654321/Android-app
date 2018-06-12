package com.zjw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjw.dao.KCDAO;
import com.zjw.dao.LSDAO;
import com.zjw.dao.UserInfoDAO;
import com.zjw.model.KCInfo;
import com.zjw.model.UserInfo;

import config.Constant;

public class AddKC extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddKC() {
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
		System.out.println("连接");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String name=new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		String hh=new String(request.getParameter("hh").getBytes("iso-8859-1"),"utf-8");
		String sl=new String(request.getParameter("sl").getBytes("iso-8859-1"),"utf-8");
		String jg=new String(request.getParameter("jg").getBytes("iso-8859-1"),"utf-8");
		String rkjg=new String(request.getParameter("rkjg").getBytes("iso-8859-1"),"utf-8");
		String gys_name=new String(request.getParameter("gys_name").getBytes("iso-8859-1"),"utf-8");
		String gys_mobile=new String(request.getParameter("gys_mobile").getBytes("iso-8859-1"),"utf-8");
		String new_goods=new String(request.getParameter("new_goods").getBytes("iso-8859-1"),"utf-8");
		KCInfo k=new KCInfo();
		k.setHh(hh);
		k.setSl(sl);
		k.setJg(jg);
		k.setName(name);
		k.setRkjg(rkjg);
		k.setNew_goods(new_goods);

		KCDAO.add(k);
		LSDAO.add(KCDAO.getInfoFromName(name).getId(), sl, "入库",gys_name,gys_mobile);	
		out.print(Constant.RESPONSE_SUCCESS);
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
