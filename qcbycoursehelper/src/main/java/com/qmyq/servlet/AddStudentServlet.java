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
        //�û���ӳ���������Ϣ
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
	 // �Ժ����еĲ�����Ҫ�п�
		if(nameString == null || nameString.equals("")) {
			json = "{\"message\":\"��������Ϊ��\"}";
			response.getWriter().append(json);
			return;
		}else if(snoString == null || snoString.equals("")) {
			json = "{\"message\":\"ѧ�Ŵ���Ϊ��\"}";
			response.getWriter().append(json);
			return;
		}else if(classString == null || classString.equals("")) {
			json = "{\"message\":\"�༶����Ϊ��\"}";
			response.getWriter().append(json);
			return;
		}else if(numInt == null || numInt.equals("")) {
			json = "{\"message\":\"��ѧ��ݴ���Ϊ��\"}";
			response.getWriter().append(json);
			return;
		}else if(schoolString == null || schoolString.equals("")) {
			json = "{\"message\":\"��ҵѧУ����Ϊ��\"}";
			response.getWriter().append(json);
			return;
		}else if(sexString == null || sexString.equals("")) {
			json = "{\"message\":\"�Ա���Ϊ��\"}";
			response.getWriter().append(json);
			return;
		}else
		{
			
			String data="INSERT into student (name,sno,class,num,school,sex)VALUES('"+nameString+"','"+snoString+"','"+classString+"',"+numInt+",'"+schoolString+"','"+sexString+"')";
		    
			int code=MysqlUtil.add(data);
			
			if(code==1) {
				json="{\"message\":\"��ӳɹ�\"}";
			}else {
				json="{\"message\":\"���ʧ��\"}";
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
