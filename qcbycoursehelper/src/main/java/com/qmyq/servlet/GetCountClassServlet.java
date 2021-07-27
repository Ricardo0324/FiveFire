package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;

/**
 * Servlet implementation class GetCountClassServlet
 */
@WebServlet("/GetCountClassServlet")
public class GetCountClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCountClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
	    response.setContentType("application/json; charset=utf-8");
	    String cla =request.getParameter("class");
	    System.out.println("查询"+cla+"学生总数");
	    String json="";
			
			String sql="select count(*) from student where class = \""+cla+"\"";
		    int count=MysqlUtil.getCount(sql);
		    System.out.println("表中数据有（条）："+count);
		    
		    json="{\"count\":"+count+"}";
		    response.getWriter().append(json);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
