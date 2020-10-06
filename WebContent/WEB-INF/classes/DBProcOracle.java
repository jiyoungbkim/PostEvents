package cla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.rmi.RemoteException;
import java.sql.ResultSetMetaData;
import java.io.*; 
public class DBProcOracle{
	CommonInfoOracle common = new CommonInfoOracle();
//	CommonInfoDB common = new CommonInfoDB();

// 등록, 저장
   public int executeInsert(String sQuery) 
   {
      Connection          con      = null;
      ResultSet	    	  rs	   = null;
      PreparedStatement   ps       = null;
		int result = 0;
      try
		{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery);		
			result = ps.executeUpdate();
				
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
			try{
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("close Exception: " + e.getMessage());
			}
        }
		return result;
	}
// 조회(String)
   public String executeQuerySearch(String sQuery) {
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
		String result = null;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery);		
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}	
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
         try
         {
            common.close(con, ps, rs);
         }
         catch(Exception e)
         {
            System.out.println("close Exception: " + e.getMessage());
         }
      }
		return result;
	}
// 조회( return String[])	
   public String[] executeQuerySearch1(String sQuery) 
   {
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
		String[] result = null ;
		int rc2 =0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery);		
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData(); 
 			int cols = rsmd.getColumnCount(); //컬럼의 총개수
 			//int rc2 =0;

			result = new String[cols];
	
			while (rs.next()) {
				 for (int i = 1; i <= cols; i++) {
					 result[i-1] = rs.getString(i);	
				 }
				 rc2++;
			}
			
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
         try
         {
            common.close(con, ps, rs);
         }
         catch(Exception e)
         {
            System.out.println("close Exception: " + e.getMessage());
         }
      }
		if(rc2 == 0) return null;
		return result;
	}
// 조회( return String[][])
   public String[][] executeQuerySearch2(String sQuery) 
   {
		Connection          con      = null;
		ResultSet	    	  rs	     = null;
		PreparedStatement   ps       = null;
		String[][] result = null;
		int rc2 =0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount(); //컬럼의 총개수
			rs.last(); // 끝으로 와서
			int rowCount = rs.getRow(); // 카운트세고
			rs.beforeFirst(); // 다시 맨앞으로
			result = new String[rowCount][cols];
			while (rs.next()) {
				 for (int i = 1; i <= cols; i++) {
					 result[rc2][i-1] = rs.getString(i);	
				 }
				 rc2++;
			}
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
			try{
			common.close(con, ps, rs);
			}catch(Exception e){
			System.out.println("close Exception: " + e.getMessage());
			}
      }
		if(rc2 == 0) return null;
		return result;
	}
}	











