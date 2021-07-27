package com.qmyq.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static void main(String[] args) {

	}
	//鏉╃偞甯撮弫鐗堝祦鎼存挾娈戦崶娑滎洣缁憋拷
	String driver = "com.mysql.jdbc.Driver";//鏉╃偞甯撮弫鐗堝祦閻ㄥ嫰鈹嶉崝锟�
	String url = "jdbc:mysql://118.195.166.217:3306/subject?useUnicode=true&characterEncoding=utf-8";
	String user = "root";
	String password = "123456";
	public Connection conn;
	
	public DBConnection() {

		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, user, password);//

			// if(!conn.isClosed())
			// System.out.println("Succeeded connecting to the Database!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
