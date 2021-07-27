package com.qmyq.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qmyq.sql.MysqlUtil;


/**
 * Servlet implementation class ShowStudentServlet
 */
@WebServlet("/ShowStudentServlet")
public class ShowStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//չʾѧ����Ϣ ��ʵ�ַ�ҳ
				response.setCharacterEncoding("utf-8");
			    response.setContentType("application/json; charset=utf-8");
			    
			    //���巵������
				String json = "";
				
				//session�ж��Ƿ��¼
				/*
				 * HttpSession session=request.getSession(); String id=(String)
				 * session.getAttribute("id"); if(id==null) { json="{\"message\":\"δ��¼\"}";
				 * System.out.println("δ��¼"); response.getWriter().append(json); }else {
				 * 
				 * }
				 */
			    	
			    	//String userNameString=request.getParameter("userName");
			    	String pageIndex=request.getParameter("page");    //��ǰҳ��
				    String pageSize=request.getParameter("limit");    //ÿҳ��������
				    System.out.println(pageIndex+"  "+pageSize);

					// �Ժ����еĲ�����Ҫ�п�
					if(pageIndex == null || pageIndex.equals("")) {
						json = "{\"message\":\"��ǰҳ������Ϊ��\"}";
						response.getWriter().append(json);
						return;
					}else if(pageSize == null || pageSize.equals("")) {
						json = "{\"message\":\"��ҳ������Ϊ��\"}";
						response.getWriter().append(json);
						return;
					}else {
					
						
						int page=Integer.parseInt(pageIndex);
						int limit=Integer.parseInt(pageSize);
						
						String sql="select * from student ";
						if(limit!=0)
						{
							if(page!=0)
							{
								sql+="limit "+limit+" offset "+(page-1)*limit;
							}else {
								sql+="limit 0,"+limit;
							}
						}
					    String[] colums= {"id","name","sno","class","num","school","sex"};
					    
					    json=MysqlUtil.getJsonBySql(sql, colums);
					    System.out.println(json);
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
