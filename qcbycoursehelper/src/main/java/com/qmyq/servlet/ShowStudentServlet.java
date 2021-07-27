package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qmyq.sql.MysqlUtil;


/**
 * Servlet implementation class ShowStudentServlet
 */
@WebServlet("/ShowStudentServlet")
public class ShowStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//展示学生信息 并实现分页
				response.setCharacterEncoding("utf-8");
			    response.setContentType("application/json; charset=utf-8");
			    
			    //定义返回数据
				String json = "";
				
				//session判断是否登录
				/*
				 * HttpSession session=request.getSession(); String id=(String)
				 * session.getAttribute("id"); if(id==null) { json="{\"message\":\"未登录\"}";
				 * System.out.println("未登录"); response.getWriter().append(json); }else {
				 * 
				 * }
				 */
			    	
			    	//String userNameString=request.getParameter("userName");
			    	String pageIndex=request.getParameter("page");    //当前页数
				    String pageSize=request.getParameter("limit");    //每页数据条数
				    System.out.println(pageIndex+"  "+pageSize);

					// 以后所有的参数都要判空
					if(pageIndex == null || pageIndex.equals("")) {
						json = "{\"message\":\"当前页数传入为空\"}";
						response.getWriter().append(json);
						return;
					}else if(pageSize == null || pageSize.equals("")) {
						json = "{\"message\":\"分页数传入为空\"}";
						response.getWriter().append(json);
						return;
					}else {
					
						
						int page=Integer.parseInt(pageIndex);
						int limit=Integer.parseInt(pageSize);
						
						String sql="select * from student ";
						if(limit!=0)
						{
							if(page!=0)
							{
								sql+="limit "+limit+" offset "+(page-1)*limit;
							}else {
								sql+="limit 0,"+limit;
							}
						}
					    String[] colums= {"id","name","sno","class","num","school","sex"};
					    
					    json=MysqlUtil.getJsonBySql(sql, colums);
					    System.out.println(json);
					    response.getWriter().append(json);			
					}	
			    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
