package cla;
import java.sql.*;
import java.rmi.RemoteException;
public class CommonInfoOracle{	
	Connection getConnection() throws RemoteException{
		 			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("드라이버 못찾음");
		}
		Connection conn = null;
		try{
			conn =DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.207:1521:ORCL","SCOTT" ,"TIGER");
			if(conn != null){
//				System.out.println("db 연결성공===========");	
//				return conn;
			}	
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
		return conn;
	}	

	public void close(Connection con, PreparedStatement ps, ResultSet result){
		try {
			if(result != null)	result.close();
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");	
		}
    }
}	