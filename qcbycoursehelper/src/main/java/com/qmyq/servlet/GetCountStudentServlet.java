package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;



/**
 * Servlet implementation class GetCountStudentServlet
 */
@WebServlet("/GetCountStudentServlet")
public class GetCountStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCountStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   //查询并获取学生表中数据条数
				response.setCharacterEncoding("utf-8");
			    response.setContentType("application/json; charset=utf-8");
			    
				/*
				 * String userNameString=request.getParameter("userName");
				 * System.out.println("用户名为： "+userNameString);
				 */
			    
			    String json="";
				/*
				 * if(userNameString == null || userNameString.equals("")) { json =
				 * "{\"message\":\"用户名传入为空\"}"; response.getWriter().append(json); return; }else
				 * {
				 * 
				 * }
				 */
					
					String sql="select count(*) from student";
				    int count=MysqlUtil.getCount(sql);
				    System.out.println("表中数据有（条）："+count);
				    
				    json="{\"count\":"+count+"}";
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
