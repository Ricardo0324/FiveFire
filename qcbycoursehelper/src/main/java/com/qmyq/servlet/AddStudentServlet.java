package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qmyq.sql.MysqlUtil;



/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        //用户添加车辆数据信息
		response.setCharacterEncoding("utf-8");
	    response.setContentType("application/json; charset=utf-8");
	    
	    //String userNameString=request.getParameter("userName");
	    String nameString=request.getParameter("name");
	    String snoString=request.getParameter("sno");
	    String classString=request.getParameter("class");
	    String numInt=request.getParameter("num");
	    String schoolString=request.getParameter("school");
	    String sexString=request.getParameter("sex");
	    
	    System.out.println(nameString+" "+snoString+" "+classString+" "+numInt+" "+schoolString+" "+sexString);
	    String json="";
	 // 以后所有的参数都要判空
		if(nameString == null || nameString.equals("")) {
			json = "{\"message\":\"姓名传入为空\"}";
			response.getWriter().append(json);
			return;
		}else if(snoString == null || snoString.equals("")) {
			json = "{\"message\":\"学号传入为空\"}";
			response.getWriter().append(json);
			return;
		}else if(classString == null || classString.equals("")) {
			json = "{\"message\":\"班级传入为空\"}";
			response.getWriter().append(json);
			return;
		}else if(numInt == null || numInt.equals("")) {
			json = "{\"message\":\"入学年份传入为空\"}";
			response.getWriter().append(json);
			return;
		}else if(schoolString == null || schoolString.equals("")) {
			json = "{\"message\":\"毕业学校传入为空\"}";
			response.getWriter().append(json);
			return;
		}else if(sexString == null || sexString.equals("")) {
			json = "{\"message\":\"性别传入为空\"}";
			response.getWriter().append(json);
			return;
		}else
		{
			
			String data="INSERT into student (name,sno,class,num,school,sex)VALUES('"+nameString+"','"+snoString+"','"+classString+"',"+numInt+",'"+schoolString+"','"+sexString+"')";
		    
			int code=MysqlUtil.add(data);
			
			if(code==1) {
				json="{\"message\":\"添加成功\"}";
			}else {
				json="{\"message\":\"添加失败\"}";
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
