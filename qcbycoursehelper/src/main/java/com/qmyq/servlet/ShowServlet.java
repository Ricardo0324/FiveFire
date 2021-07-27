package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/josn;charset=utf-8");
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("limit");
		String sql="select * from teacher";
		Integer page=Integer.valueOf(pageIndex);
		Integer limit=Integer.valueOf(pageSize);
		if(page!=0) {if(limit!=0) {
			sql+=" limit " +(page-1)*limit+" , " +limit;
		}else {
			sql+=" limit " +0+" , " +limit;
		}
		}
		String[] params= {"id","name","sex","class"};
		String value=MysqlUtil.getJsonBySql(sql,params);
		System.out.println(value);
		response.getWriter().append(value);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
