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
		// ѧ��ѡ��༶����
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		String classString = request.getParameter("class");
		String userNameString = request.getParameter("userName");
		System.out.println(classString + " " + userNameString);
		String json = "";
		/*
		 * // �Ժ����еĲ�����Ҫ�п� if(idInt == null || idInt.equals("")) { json =
		 * "{\"message\":\"id����Ϊ��\"}"; response.getWriter().append(json); return; }else
		 * if(nameString == null || nameString.equals("")) { json =
		 * "{\"message\":\"��������Ϊ��\"}"; response.getWriter().append(json); return; }else
		 * if(snoString == null || snoString.equals("")) { json =
		 * "{\"message\":\"ѧ�Ŵ���Ϊ��\"}"; response.getWriter().append(json); return; }else
		 * if(classString == null || classString.equals("")) { json =
		 * "{\"message\":\"�༶����Ϊ��\"}"; response.getWriter().append(json); return; }else
		 * if(numInt == null || numInt.equals("")) { json =
		 * "{\"message\":\"��ѧ��ݴ���Ϊ��\"}"; response.getWriter().append(json); return;
		 * }else if(schoolString == null || schoolString.equals("")) { json =
		 * "{\"message\":\"��ҵѧУ����Ϊ��\"}"; response.getWriter().append(json); return;
		 * }else if(sexString == null || sexString.equals("")) { json =
		 * "{\"message\":\"�Ա���Ϊ��\"}"; response.getWriter().append(json); return; }else
		 * {
		 * 
		 * }
		 */

		String sqlString="update student set class='"+classString+"' where name='"+userNameString+"';";
	    int code=MysqlUtil.update(sqlString);      
	    if(code==0) {
	    	json="{\"code\":\"200\",\"data\":\"�޸�ʧ��\"}";
	    }else {
	    	json="{\"code\":\"200\",\"data\":\"�޸ĳɹ�\"}";
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
