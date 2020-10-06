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
import dto.Faq_DTO;

public class Faq_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getMaxNo(){
		String query = "select max(faq_no) from a20_track2_home_faq";
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
	public String getFaqNo(){
		String faqNo = getMaxNo();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		
		if(faqNo == null) {
			faqNo = nowYear+"_0001";
		} else {
			String checkYear = faqNo.substring(0,2);
			
			int y =0;
			if(nowYear.equals(checkYear)) {
				y = Integer.parseInt(faqNo.substring(3));
				y++;
				String r = CommonUtil.getLPad(Integer.toString(y), 4, "0");
				faqNo = checkYear+"_"+r;
			} else {
				faqNo = nowYear + "_0001";
			}
		}
		return faqNo;
	}
	public int insertFaq(String faq_no, String title, String content, String reg_id, String reg_date) {
		String query = " insert into a20_track2_home_faq(faq_no, title, content, reg_id, reg_date) "+
						" values ('"+faq_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"') ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println(" RemoteException insertFaq(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException insertFaq(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertFaq() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertFaq close Exception " + e.getMessage());
			}
		}
		return result;
	}
	public ArrayList<Faq_DTO> getFaqList(String selValue, String txtValue) {
	    ArrayList<Faq_DTO> arrF = new ArrayList<Faq_DTO>();
	    String query =  " select faq_no, title, content, reg_id, reg_date "+ 
	    				" from a20_track2_home_faq "+ 
	    				" where "+selValue+" like '%"+txtValue+"%' "+
	    				" order by faq_no desc ";
	    
	    try {
	      con = common.getConnection();
	      ps = con.prepareStatement(query);
	      rs = ps.executeQuery();
	      while (rs.next()) {
	        String faq_no   = rs.getString(1);
	        String title    = rs.getString(2);
	        String content  = rs.getString(3);
	        String reg_id   = rs.getString(4);
	        String reg_date = rs.getString(5);
	        
	        Faq_DTO Faq_DTO = new Faq_DTO(faq_no, title, content, reg_id, reg_date);
	        arrF.add(Faq_DTO);
	      } 
	    } catch (RemoteException remoteException) {
	      System.out.println(" RemoteException getNewsList(): " + remoteException.getMessage());
	    } catch (SQLException sQLException) {
	      System.out.println(" SQLException getNewsList(): " + sQLException.getMessage());
	    } catch (Exception exception) {
	      System.out.println(" error : D_2_DAO.getNewsList() ");
	    } finally {
	      try {
	        common.close(con, ps, rs);
	      } catch (Exception exception) {
	        System.out.println(" getNewsList close Exception " + exception.getMessage());
	      } 
	    } 
	    return arrF;
	  }
	//게시물 조회
		public Faq_DTO getFaqView(String faqNo){

			String query =" select faq_no, title, content, reg_id, to_char(reg_date, 'yyyy-mm-dd') "+
							" from a20_track2_home_faq "+
							" where faq_no = '"+faqNo+"'";		
			
			Faq_DTO dtoF = null;				
			try{
				con = common.getConnection();
				ps  = con.prepareStatement(query);
				rs  = ps.executeQuery();
				while(rs.next()){
					String faq_no = rs.getString(1);
					String title = rs.getString(2);
					String content = rs.getString(3);
					String reg_id = rs.getString(4);
					String reg_date = rs.getString(5);
					
					dtoF = new Faq_DTO(faq_no,title,content,reg_id,reg_date);
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
			return dtoF;
		}	
	// 글 수정 저장
	public int updateFaq(String faq_no,String title,String content,String reg_id,String reg_date) {
		
		
		String query = " update a20_track2_home_faq "+
						" set title = '"+title+"', content = '"+content+"', "+
						" reg_id = '"+reg_id+"', reg_date = '"+reg_date+"' "+
						" where faq_no = '"+faq_no+"' ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException updateNotice(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException updateNotice(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.updateNotice() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" updateNotice close Exception " + e.getMessage());
			}
		}
		return result;
	}
	// 글 수정 삭제
	public int deleteFaq(String faq_no) {
		
		String query = " delete from a20_track2_home_faq "+
						" where faq_no = '"+faq_no+"' ";
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
}
