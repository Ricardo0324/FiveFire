package com.qmyq.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qmyq.sql.MysqlUtil;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置返回值
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/josn;charset=utf-8");
				// 接收相关的值
				String userName = request.getParameter("userName");
				String type = request.getParameter("type");
				Integer typeInt=Integer.valueOf(type);
				String password = request.getParameter("password");
				System.out.println(userName + " " +password+" "+typeInt);
				
				//定义返回数据
				String json = "";
				// 以后所有的参数都要判空
				if((userName == null && userName.equals(""))) 
				{
					json = "{\"message\":\"用户名为空\"}";
					response.getWriter().append(json);
					return;
				}else if(password == null && password.equals("")) {
					json = "{\"message\":\"密码为空\"}";
					response.getWriter().append(json);
					return;
				}else if(typeInt == null && typeInt.equals("")) {
					json = "{\"message\":\"type传入为空\"}";
					response.getWriter().append(json);
					return;
				}else {
					String sql = "select * from login where userName='"+userName+"' and  password ='"+password+"' and type="+typeInt+";";
					String[] cStrings =  new String[] {"id","userName","password","type"};
					ArrayList<String[]>  arrayList = MysqlUtil.showUtil(sql, cStrings);
					if (arrayList.size() == 0) {
						json = "{\"message\":\"账号或密码有误\"}";
					}else if (arrayList.size() == 1) 
					{
						if(arrayList.get(0)[3].equals("0")) 
						{
							json = "{\"message\":\"学生\"}";
						}else if(arrayList.get(0)[3].equals("1"))
						{
							//System.out.println("aaa");
							json = "{\"message\":\"管理员\"}";
						}else if(arrayList.get(0)[3].equals("2"))
					    {
					    	json = "{\"message\":\"老师\"}";
					    }
						System.out.println(json);
						
						HttpSession session=request.getSession();
						session.setAttribute("id",arrayList.get(0)[0]);
						//session.setAttribute("userName",arrayList.get(0)[1]);
						System.out.println(arrayList.get(0)[0]+"*******");
						System.out.println(arrayList.get(0)[1]+"*******");
						
						
						//创建cookie
						Cookie userName1=new Cookie("userName",arrayList.get(0)[1]);
						
						//设置cookie的有效期为3天
						//ck1.setMaxAge(3*24*60);
						
						//将cookie返回客户端
						response.addCookie(userName1);
						//response.addCookie(ck1);
						
					}else {
						json = "{\"message\":\"数据库数据错误\"}";
					}
				}
				response.getWriter().append(json); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
