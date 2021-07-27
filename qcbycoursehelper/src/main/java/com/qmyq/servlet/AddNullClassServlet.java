package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;

/**
 * Servlet implementation class AddNullClassServlet
 */
@WebServlet("/AddNullClassServlet")
public class AddNullClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNullClassServlet() {
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
		String idString=request.getParameter("id");
		String classString=request.getParameter("class");
		String json="";
		if(classString == null || classString.equals("")) {
			json = "{\"message\":\"班级传入为空\"}";
			response.getWriter().append(json);
			return;
		}
		if(idString == null || idString.equals("")) {
			json = "{\"message\":\"id传入为空\"}";
			response.getWriter().append(json);
			return;
		}
		System.out.println(idString+" "+classString);
		String data="update student set class='"+classString+"' where id="+idString+";";
	    
		int code=MysqlUtil.add(data);
		
		if(code==1) {
			json="{\"message\":\"添加成功\"}";
		}else {
			json="{\"message\":\"添加失败\"}";
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
