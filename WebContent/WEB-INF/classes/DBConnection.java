package cla;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class DBConnection{
	Connection getConnection() throws RemoteException{
		 			
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("드라이버 못찾음");
		}
		Connection conn = null;
		try{
			conn =DriverManager.getConnection("jdbc:mysql://127.0.0.1/ictdb?useSSL=true&verifyServerCertificate=false","ictuser" ,"ict1234");

			if(conn != null){
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