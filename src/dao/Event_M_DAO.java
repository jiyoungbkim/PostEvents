package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.Event_DTO;
import dto.Event_M_DTO;
import dto.Notice_DTO;

public class Event_M_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
			
	public String getMaxId(String event_no){
		int maxId = 0;
		String query = " select max(event_m_no) from a20_track2_home_event_m "
				+ "where event_no = '"+event_no+"'";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				maxId = rs.getInt(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMaxId: "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMaxId: "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMaxId() ");
		}
		try{
			common.close(con, ps, rs);
		} catch(Exception e) {
			System.out.println(" close Exception "+e.getMessage());
		}
		if(maxId == 0) maxId = 001;
		else maxId += 1;
		return Integer.toString(maxId);
	}
	public int insertApply(String event_no, String event_m_no, String title, String content, String reg_id, String reg_date) {
		String query = " insert into a20_track2_home_event_m(event_no, event_m_no, title, content, reg_id, reg_date) "+
						" values ('"+event_no+"','"+event_m_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"') ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println(" RemoteException insertEvent(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException insertEvent(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertEvent() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertEvent close Exception " + e.getMessage());
			}
		}
		return result;
	}
	public int getEventCount(String event_no) {
		String query ="select count(*) from a20_track2_home_event_m where event_no = '"+event_no+"' ";
		int count = 0;
		try {
		      con = common.getConnection();
		      ps = con.prepareStatement(query);
		      rs = ps.executeQuery();
		      while (rs.next()) {
		    	  count	= rs.getInt(1);
		      } 
		    } catch (RemoteException remoteException) {
		      System.out.println(" RemoteException getEventList(): " + remoteException.getMessage());
		    } catch (SQLException sQLException) {
		      System.out.println(" SQLException getEventList(): " + sQLException.getMessage());
		    } catch (Exception exception) {
		      System.out.println(" error : D_2_DAO.getEventList() ");
		    } finally {
		      try {
		        common.close(con, ps, rs);
		      } catch (Exception exception) {
		        System.out.println(" getEventList close Exception " + exception.getMessage());
		      } 
		    } 
		    return count;
	}
	public int getMemberCount(String event_no, String reg_id) {
		String query ="select count(*) from a20_track2_home_event_m where event_no = '"+event_no+"' and reg_id = '"+reg_id+"' ";
		int count = 0;
		try {
		      con = common.getConnection();
		      ps = con.prepareStatement(query);
		      rs = ps.executeQuery();
		      while (rs.next()) {
		    	  count	= rs.getInt(1);
		      } 
		    } catch (RemoteException remoteException) {
		      System.out.println(" RemoteException getEventList(): " + remoteException.getMessage());
		    } catch (SQLException sQLException) {
		      System.out.println(" SQLException getEventList(): " + sQLException.getMessage());
		    } catch (Exception exception) {
		      System.out.println(" error : D_2_DAO.getEventList() ");
		    } finally {
		      try {
		        common.close(con, ps, rs);
		      } catch (Exception exception) {
		        System.out.println(" getEventList close Exception " + exception.getMessage());
		      } 
		    } 
		    return count;
	}
	public Event_M_DTO getApplyView(String id){

		String query =" select event_no, event_m_no, title, content, reg_id, reg_date "+ 
					"from a20_track2_home_event_m "+ 
					"where reg_id = '"+id+"' ";		
		
		Event_M_DTO dtoM = null;				
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String event_no 	= rs.getString(1);			
				String event_m_no 	= rs.getString(2);			
				String title		= rs.getString(3);
				String content 		= rs.getString(4);
				String reg_id 		= rs.getString(5);
				String reg_date 	= rs.getString(6);				
				
				dtoM = new Event_M_DTO(event_no,event_m_no,title,content,reg_id,reg_date);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getFaqView(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getFaqView(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getFaqView() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getFaqView close Exception "+e.getMessage());
			}
		}						
		return dtoM;
	}
	public int updateEventM(String event_no, String event_m_no, String title, String content, String reg_id, String reg_date) {
		String query = " update a20_track2_home_event_m "+
						" set title = '"+title+"', content = '"+content+"', "+
						" reg_id = '"+reg_id+"', reg_date='"+reg_date+"' "+
						" where event_no = '"+event_no+"' and event_m_no = '"+event_m_no+"' ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException updateEvent(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException updateEvent(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.updateEvent() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" updateEvent close Exception " + e.getMessage());
			}
		}
		return result;
	}
	public int deleteEventM(String event_no, String event_m_no) {
		
		String query = " delete from a20_track2_home_event_m "+ 
						"where event_no = '"+event_no+"' "+ 
						"and event_m_no ='"+event_m_no+"' ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException deleteFaq(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException deleteFaq(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.deleteFaq() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" deleteFaq close Exception " + e.getMessage());
			}
		}
		return result;
	}
	public ArrayList<Event_M_DTO> getPrizeList(String no){
		ArrayList<Event_M_DTO> arrM = new ArrayList<Event_M_DTO>();
		String query = " select event_no, event_m_no, title, content, reg_id, reg_date, decode(status,'n','탈락','당첨') "+ 
						" from a20_track2_home_event_m "+
						" where event_no = '"+no+"' ";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String event_no 	= rs.getString(1);
				String event_m_no   = rs.getString(2);
				String title 		= rs.getString(3);
				String content 		= rs.getString(4);
				String reg_id  		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				String status 		= rs.getString(7);
				
				Event_M_DTO dto = new Event_M_DTO(event_no,event_m_no,title,content,reg_id,reg_date,status);
				arrM.add(dto);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getNoticeList(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getNoticeList(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getNoticeList() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getNoticeList close Exception "+e.getMessage());
			}
		}				
		
		return arrM;
	}
	public ArrayList<Event_M_DTO> getApplyList(String selValue, String txtValue) {
	    ArrayList<Event_M_DTO> arrM = new ArrayList<Event_M_DTO>();
	    String query =  " select event_no, event_m_no, title, content, reg_id, reg_date " + 
	    				" from a20_track2_home_event_m "+ 
	    				" where "+selValue+" like '%"+txtValue+"%' "+
	    				" order by event_no desc ";
	    
	    try {
	      con = common.getConnection();
	      ps = con.prepareStatement(query);
	      rs = ps.executeQuery();
	      while (rs.next()) {
	    	    String event_no 	= rs.getString(1);
				String event_m_no   = rs.getString(2);
				String title 		= rs.getString(3);
				String content 		= rs.getString(4);
				String reg_id  		= rs.getString(5);
				String reg_date 	= rs.getString(6);
				
			    Event_M_DTO dto = new Event_M_DTO(event_no,event_m_no,title,content,reg_id,reg_date);
				arrM.add(dto);
	      } 
	    } catch (RemoteException remoteException) {
	      System.out.println(" RemoteException getEventList(): " + remoteException.getMessage());
	    } catch (SQLException sQLException) {
	      System.out.println(" SQLException getEventList(): " + sQLException.getMessage());
	    } catch (Exception exception) {
	      System.out.println(" error : D_2_DAO.getEventList() ");
	    } finally {
	      try {
	        common.close(con, ps, rs);
	      } catch (Exception exception) {
	        System.out.println(" getEventList close Exception " + exception.getMessage());
	      } 
	    } 
	    return arrM;
	  }
	
	public int lotEventAll(String event_no) {
		
		String query = " update a20_track2_home_event_m "+
						" set status = 'n' "+
						" where event_no = '"+event_no+"' ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException lotEventAll(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException lotEventAll(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.lotEventAll() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" lotEventAll close Exception " + e.getMessage());
			}
		}
		return result;
	}	
	
	public int lotEventM(String event_no, String id) {
	
		String query = " update a20_track2_home_event_m "+
						" set status = 'y' "+
						" where event_no = '"+event_no+"' "+
						" and reg_id = '"+id+"' ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException updateEventM(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException updateEventM(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.updateEventM() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" updateEventM close Exception " + e.getMessage());
			}
		}
		return result;
	}
	public int lotStatus(String event_no){

		String query =" select count(*) from a20_track2_home_event_m "+ 
				"where event_no = '"+event_no+"' "+ 
				"and status ='y' ";		
		
		int result = 0;				
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result 	= rs.getInt(1);			
			

			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getFaqView(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getFaqView(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getFaqView() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getFaqView close Exception "+e.getMessage());
			}
		}						
		return result;
	}
}

