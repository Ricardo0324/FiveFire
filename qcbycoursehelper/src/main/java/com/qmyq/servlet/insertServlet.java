package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;

/**
 * Servlet implementation class insertServlet
 */
@WebServlet("/insertServlet")
public class insertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertServlet() {
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
		String name =request.getParameter("name");
		String sex =request.getParameter("sex");
		String cla =request.getParameter("class");
		System.out.println(name+" "+sex+" "+cla);
		String json="";
		if(name==null&&name.equals("")) {
			json="{\"code\":\"200\",\"message\":\"姓名为空\"}";
			response.getWriter().append(json);
			return;
		}
		if(sex==null&&sex.equals("")) {
			json="{\"code\":\"200\",\"message\":\"性别为空\"}";
			response.getWriter().append(json);
			return;
		}
		if(cla==null&&cla.equals("")) {
			json="{\"code\":\"200\",\"message\":\"班级为空\"}";
			response.getWriter().append(json);
			return;
		}
		String insert="insert into teacher (name,sex,class) values (\""+name+"\",\""+sex+"\",'"+cla+"')";
		int code=MysqlUtil.add(insert);
		if(code==0) {
			json="{\"code\":\"200\",\"message\":\"添加失败\"}";
		}else{
			json="{\"code\":\"200\",\"message\":\"添加成功\"}";
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
