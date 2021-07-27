package com.qmyq.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MysqlUtil {

	public static void main(String[] args) {
		
		String[] col = {"id","username","realname","available"};
		String sql = "select * from t_table where id in (1,2,3)";
		String strJson = MysqlUtil.getJsonBySql(sql, col);
		System.out.println(strJson);
	}
	
	/**
	 * 鐢ㄤ簬鎻掑叆鏁版嵁
	 * @param sql insert璇彞
	 * @return
	 */
	public static int add(String sql) {
		
        int i=0;
        //鏁版嵁搴撹繛鎺�
        DBConnection db = new DBConnection();
        try {        
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.executeUpdate();
            preStmt.close();
            db.close();//鍏抽棴杩炴帴 
            i = 1;
           // System.out.println("鏁版嵁鎻掑叆鎴愬姛锛宻ql璇彞鏄細" + sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;//杩斿洖褰卞搷鐨勮鏁帮紝1涓烘墽琛屾垚鍔�;
    }
    //
    public static ArrayList<String[]> showUtil( String sql, String[] colums){
        
    	 ArrayList<String[]>  result = new  ArrayList<String[]>();
         DBConnection db = new DBConnection();
         try {
            Statement stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
           
            while(rs.next()){
               String[] dataRow = new String[colums.length];
               for( int i = 0; i < dataRow.length; i++ ) {
            	   dataRow[i] = rs.getString( colums[i] );
               }
               result.add(dataRow);
            }
            rs.close();
            db.close();//
        } catch (SQLException e) {
            e.printStackTrace();
        } 
         
         return result;
    }
    
    public static int getCount(String sql) {
		int sum = 0;
		DBConnection db = new DBConnection();
		try {
			Statement stmt = (Statement) db.conn.createStatement();
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
            	sum += rs.getInt(1);
            	}
            rs.close();
            db.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sum;
	}
    
    public static String getJsonBySql( String sql, String[] colums){
        
        System.err.println("传入sql语句为:" + sql);
      	 ArrayList<String[]>  result = new  ArrayList<String[]>();
           DBConnection db = new DBConnection();
           try {
              Statement stmt = (Statement) db.conn.createStatement();
              ResultSet rs = (ResultSet) stmt.executeQuery(sql);
              while(rs.next()){
                 String[] dataRow = new String[colums.length];
                 for( int i = 0; i < dataRow.length; i++ ) {
              	   dataRow[i] = rs.getString( colums[i] );
                 }
                 result.add(dataRow);
              }
              rs.close();
              db.close();//
          } catch (SQLException e) {
              e.printStackTrace();
          } 
           
           //return listToJson(result,colums);
           return listToJsonLayui(result, colums);  //layui输出专属
      }

    public static int update(String sql) {
        int i =0;
        DBConnection db = new DBConnection();
        try {
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(sql);
            preStmt.executeUpdate();
            preStmt.close();
            db.close();
            i = 1;
            System.out.println("鏁版嵁鏇存柊鎴愬姛锛宻ql璇彞鏄細" + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    

    /**
     *  鏌ヨ鏁版嵁
     * @param sql select * from 琛�
     * @param params [id,name,sex,age] 鎴戜滑瑕佹煡璇㈢殑鍒楀悕鐨勪竴涓瓧绗︿覆鏁扮粍
     * @return
     */
    public static String show(String sql, String[] params){
    	
    	List< Map<String,String> > listmap = new ArrayList<>();
    	
         DBConnection db = new DBConnection();
         ResultSet rs = null;
         try {
            Statement stmt = (Statement) db.conn.createStatement();
            rs = (ResultSet) stmt.executeQuery(sql);
            while(rs.next()){
            	Map<String,String> map = new HashMap<String,String>();
            	for(int i = 0; i < params.length; i++) {
            		map.put(params[i], rs.getString(params[i]));
            	}
            	listmap.add(map);
            }
            rs.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
		return mapToJson(listmap); 
         
     
    }
    
  
    
  
    public static int del(String delstr) {
        int i=0;
        DBConnection db = new DBConnection();
        try {    
            PreparedStatement preStmt = (PreparedStatement) db.conn.prepareStatement(delstr);
            preStmt.executeUpdate();
            
            preStmt.close();
            db.close();
            i = 1;
            System.out.println("鏁版嵁鍒犻櫎鎴愬姛锛宻ql璇彞鏄細" + delstr);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    
    /**
     * map杞寲涓簀son鏁版嵁瀛楃涓�
     * @param maplist
     * @return
     */
    public static String mapToJson( List<Map<String,String>> maplist ) {
    	String jsonData = "{ \"data\":[";
		for(int i = 0; i < maplist.size(); i++) {
			String outstr = "[\"" ;
			int size = 0;
			for(String value : maplist.get(i).values()){
				size += 1;
				outstr += value;
				if( size < maplist.get(i).values().size() ) {
				     outstr += "\",\"";
				}
			}
		    outstr += "\"]";
		    
		    if(i < maplist.size() -1) {
		    	outstr += ",";
		    }
			jsonData += outstr;
			
		}
		jsonData += "]}";
		
		return jsonData;
    }
    
    
    
    public static String listToJsonLayui( ArrayList<String[]> list,String[] colums) {

    	String jsonStr = "[{\"status\":0}, {\"message\": \"鎴愬姛\" }, {\"count\": 1000},{\"rows\":{\"item\":[";
    			for(int i = 0; i < list.size(); i++) {
    				String arr = "{";
    				for( int j = 0; j < list.get(0).length; j++) {
    					
    					if( list.get(i)[j] == null || "NULL".equals(list.get(i)[j])) {
    						arr += "\"\"";
    					}else {
    						arr += "\"" + colums[j] + "\""+":" ;
    						arr +=  "\"" + list.get(i)[j].replace("\"","\\\"") + "\"";
    					}
    					
    					if( j < list.get(0).length - 1 ) {
    						arr += ",";
    					}
    				}
    				arr += "}";
    				if( i < list.size() - 1 ) {
						arr += ",";
					}
    				
    				jsonStr += arr;
    			}
    			jsonStr += "]}}]";
    	
    	return jsonStr;
    }
    

    public static String listToJson( ArrayList<String[]> list,String[] colums) {

    	String jsonStr = "{ \"data\":[";
    			for(int i = 0; i < list.size(); i++) {
    				String arr = "{";
    				for( int j = 0; j < list.get(0).length; j++) {
    					
    					if( list.get(i)[j] == null || "NULL".equals(list.get(i)[j])) {
    						arr += "\"\"";
    					}else {
    						arr += "\"" + colums[j] + "\""+":" ;
    						arr +=  "\"" + list.get(i)[j].replace("\"","\\\"") + "\"";
    					}
    					
    					if( j < list.get(0).length - 1 ) {
    						arr += ",";
    					}
    				}
    				arr += "}";
    				if( i < list.size() - 1 ) {
						arr += ",";
					}
    				
    				jsonStr += arr;
    			}
    			jsonStr += "]}";
    	
    	return jsonStr;
    }

}
