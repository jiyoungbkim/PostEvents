package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.Event_DTO;

public class Event_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getMaxNo(){
		String query = "select max(event_no) from a20_track2_home_event";
		String result = "";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getMaxNo(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getMaxNo(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getMaxNo() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getMaxNo close Exception "+e.getMessage());
			}
		}
		return result;
	}
	public String getEventNo(){
		String eventNo = getMaxNo();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		
		if(eventNo == null) {
			eventNo = nowYear+"_0001";
		} else {
			String checkYear = eventNo.substring(0,2);
			
			int y =0;
			if(nowYear.equals(checkYear)) {
				y = Integer.parseInt(eventNo.substring(3));
				y++;
				String r = CommonUtil.getLPad(Integer.toString(y), 4, "0");
				eventNo = checkYear+"_"+r;
			} else {
				eventNo = nowYear + "_0001";
			}
		}
		return eventNo;
	}
	public int insertEvent(String event_no, String title, String content, String reg_id, String reg_date, String reg_start, String reg_end, int hit) {
		String query = " insert into a20_track2_home_event(event_no, title, content, reg_id, reg_date, reg_start, reg_end, hit) "+
						" values ('"+event_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"','"+reg_start+"','"+reg_end+"','"+hit+"') ";
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
	public ArrayList<Event_DTO> getEventList(String selValue, String txtValue) {
	    ArrayList<Event_DTO> arrE = new ArrayList<Event_DTO>();
	    String query =  " select event_no, title, to_char(reg_start,'yyyy-mm-dd'), "+
	    				" to_char(reg_end,'yyyy-mm-dd'), content, reg_id, to_char(reg_date,'yyyy-mm-dd'), hit "+ 
	    				" from a20_track2_home_event "+ 
	    				" where "+selValue+" like '%"+txtValue+"%' "+
	    				" order by event_no desc ";
	    
	    try {
	      con = common.getConnection();
	      ps = con.prepareStatement(query);
	      rs = ps.executeQuery();
	      while (rs.next()) {
	        String event_no   = rs.getString(1);
	        String title      = rs.getString(2);
	        String reg_start  = rs.getString(3);
	        String reg_end	  = rs.getString(4);	        
	        String content    = rs.getString(5);
	        String reg_id     = rs.getString(6);
	        String reg_date   = rs.getString(7);
	        int hit			  = rs.getInt(8);
	        
	        Event_DTO Event_DTO = new Event_DTO(event_no, title, reg_start, reg_end, content, reg_id, reg_date, hit);
	        arrE.add(Event_DTO);
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
	    return arrE;
	  }
	//게시물 조회
	public Event_DTO getEventView(String eventNo){

		String query =" select event_no, title, "+
						" to_char(reg_start, 'yyyy-mm-dd'), to_char(reg_end, 'yyyy-mm-dd'), "+
						" content, reg_id, to_char(reg_date, 'yyyy-mm-dd'), hit "+
						" from a20_track2_home_event "+
						" where event_no = '"+eventNo+"'";		
		
		Event_DTO dtoE = null;				
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String event_no 	= rs.getString(1);
				String title 		= rs.getString(2);				
				String reg_start	= rs.getString(3);
				String reg_end 		= rs.getString(4);
				String content 		= rs.getString(5);
				String reg_id 		= rs.getString(6);
				String reg_date 	= rs.getString(7);				
				int hit 			= rs.getInt(8);
				
				dtoE = new Event_DTO(event_no,title,reg_start,reg_end,content,reg_id,reg_date,hit);
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
		return dtoE;
	}
	public int eventHit(String eventNo) {
	    int i = 0;
	    String str = " update a20_track2_home_event  set hit = hit+1 "+
					" where event_no = '" + eventNo + "' ";

	    
	    try {
	      this.con = this.common.getConnection();
	      this.ps = this.con.prepareStatement(str);
	      i = this.ps.executeUpdate();
	    } catch (RemoteException remoteException) {
	      System.out.println(" RemoteException eventHit(): " + remoteException.getMessage());
	    } catch (SQLException sQLException) {
	      System.out.println(" SQLException eventHit(): " + sQLException.getMessage());
	    } catch (Exception exception) {
	      System.out.println(" error : D_2_DAO.eventHit() ");
	    } finally {
	      try {
	        this.common.close(this.con, this.ps);
	      } catch (Exception exception) {
	        System.out.println(" eventHit close Exception " + exception.getMessage());
	      } 
	    } 
	    return i;
	}
	// 글 수정 저장
	public int updateEvent(String event_no,String title,String content,String reg_id,String reg_date, String reg_start, String reg_end) {
		
		
		String query = " update a20_track2_home_event "+
						" set title = '"+title+"', content = '"+content+"', "+
						" reg_id = '"+reg_id+"', reg_date = '"+reg_date+"', reg_start = '"+reg_start+"', "+
						" reg_end = '"+reg_end+"' "+
						" where event_no = '"+event_no+"' ";
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
	
}
