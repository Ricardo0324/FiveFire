package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;

/**
 * Servlet implementation class StudentChooseClassServlet
 */
@WebServlet("/StudentChooseClassServlet")
public class StudentChooseClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentChooseClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 学生选择班级加入
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		String classString = request.getParameter("class");
		String userNameString = request.getParameter("userName");
		System.out.println(classString + " " + userNameString);
		String json = "";
		/*
		 * // 以后所有的参数都要判空 if(idInt == null || idInt.equals("")) { json =
		 * "{\"message\":\"id传入为空\"}"; response.getWriter().append(json); return; }else
		 * if(nameString == null || nameString.equals("")) { json =
		 * "{\"message\":\"姓名传入为空\"}"; response.getWriter().append(json); return; }else
		 * if(snoString == null || snoString.equals("")) { json =
		 * "{\"message\":\"学号传入为空\"}"; response.getWriter().append(json); return; }else
		 * if(classString == null || classString.equals("")) { json =
		 * "{\"message\":\"班级传入为空\"}"; response.getWriter().append(json); return; }else
		 * if(numInt == null || numInt.equals("")) { json =
		 * "{\"message\":\"入学年份传入为空\"}"; response.getWriter().append(json); return;
		 * }else if(schoolString == null || schoolString.equals("")) { json =
		 * "{\"message\":\"毕业学校传入为空\"}"; response.getWriter().append(json); return;
		 * }else if(sexString == null || sexString.equals("")) { json =
		 * "{\"message\":\"性别传入为空\"}"; response.getWriter().append(json); return; }else
		 * {
		 * 
		 * }
		 */

		String sqlString="update student set class='"+classString+"' where name='"+userNameString+"';";
	    int code=MysqlUtil.update(sqlString);      
	    if(code==0) {
	    	json="{\"code\":\"200\",\"data\":\"修改失败\"}";
	    }else {
	    	json="{\"code\":\"200\",\"data\":\"修改成功\"}";
	    }
	    response.getWriter().append(json);
		 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
