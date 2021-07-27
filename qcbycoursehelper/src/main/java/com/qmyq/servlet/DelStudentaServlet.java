package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;



/**
 * Servlet implementation class DelStudentaServlet
 */
@WebServlet("/DelStudentaServlet")
public class DelStudentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelStudentaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   //管理员删除学生信息
		
				response.setCharacterEncoding("utf-8");
			    response.setContentType("application/json; charset=utf-8");
			    
			    String idInt=request.getParameter("id");
			    System.out.println("id: "+idInt);
			    
			    String json="";
			    if(idInt == null || idInt.equals("")) {
					json = "{\"message\":\"id未传入\"}";
					response.getWriter().append(json);
					return;
			    }else {
			    	
			    	String del="delete from student where id= "+idInt;
				    
				    int code=MysqlUtil.del(del);
				    if(code==0) {
				    	json="{\"code\":\"200\",\"data\":\"删除失败\"}";
				    }else {
				    	json="{\"code\":\"200\",\"data\":\"删除成功\"}";
				    }	    
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
