package dao;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import common.DBConnectionOracle;
import dto.Notice_DTO;
import common.*;

public class Notice_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public String getMaxNo() {
		String query = " select max(notice_no) from a20_track2_web_notice ";
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
	public String getNoticeNo() {
		String noticeNo = getMaxNo();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		
		if(noticeNo == null) {
			noticeNo = nowYear+"_0001";
		} else {
			String checkYear = noticeNo.substring(0,2);
			
			int y = 0;
			if(nowYear.equals(checkYear)) {
				y = Integer.parseInt(noticeNo.substring(3));
				y++;
				String r = CommonUtil.getLPad(Integer.toString(y), 4, "0");
				noticeNo = checkYear+"_"+r;
			} else {
				noticeNo = nowYear + "_0001";
			}	
		}
		return noticeNo;
	}
	// 공지사항 등록
	public int insertNotice(String notice_no,String title,String content,String reg_id,String reg_date) {
		String query = "insert into a20_track2_web_notice(notice_no, title, content, reg_id, reg_date) "+
					" values ('"+notice_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"') ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println(" RemoteException insertNotice(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException insertNotice(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertNotice() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertNotice close Exception " + e.getMessage());
			}
		}
		return result;
	}
	// 공지사항 등록 dto
	public int insertNotice(Notice_DTO dto) {
		
		String query = "insert into a20_track2_web_notice(notice_no, title, content, file_name_1, reg_id, reg_date) "+
					" values ('"+dto.getNotice_no()+"','"+dto.getTitle()+"','"+dto.getContent()+"', '"+dto.getFile_name_1()+"', "+
					" '"+dto.getReg_id()+"','"+dto.getReg_date()+"') ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException insertNotice(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException insertNotice(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertNotice() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertNotice close Exception " + e.getMessage());
			}
		}
		return result;
	}
	// 목록 조회
	public ArrayList<Notice_DTO> getNoticeList(String selValue, String txtValue){
		ArrayList<Notice_DTO> arrN = new ArrayList<Notice_DTO>();
		String query = " select n.notice_no, n.title, n.content, n.file_name_1, m.name, to_char(n.reg_date, 'yyyy-mm-dd'), n.hit "+
					" from a20_track2_web_notice n, a20_track2_home_member m "+
					" where n.reg_id = m.id "+
					" and n."+selValue+" like '%"+txtValue+"%' "+
					" order by n.notice_no desc ";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String notice_no = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String file_name_1 = rs.getString(4);
				String reg_id = rs.getString(5);
				String reg_date = rs.getString(6);
				int hit = rs.getInt(7);
				
				Notice_DTO dto = new Notice_DTO(notice_no,title,content,file_name_1,reg_id,reg_date,hit);
				arrN.add(dto);
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
		
		return arrN;
	}
	// 인덱스 조회
	public ArrayList<Notice_DTO> getNoticeIndex(){
		ArrayList<Notice_DTO> arrN = new ArrayList<Notice_DTO>();
		String query = " select notice_no, substr(title, 1,6), substr(content, 1,15), to_char(reg_date, 'yyyy-mm-dd') "+
						" from (select notice_no, title, content, reg_date "+
						" from a20_track2_web_notice "+
						" order by reg_date desc) "+
						" where rownum <= 5 "+
						" order by notice_no desc ";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String notice_no = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String reg_date = rs.getString(4);
				
				Notice_DTO dto = new Notice_DTO(notice_no,title,content,reg_date);
				arrN.add(dto);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getNoticeIndex(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getNoticeIndex(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getNoticeIndex() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getNoticeIndex close Exception "+e.getMessage());
			}
		}				
		
		return arrN;
	}
	//게시물 조회
	public Notice_DTO getNoticeView(String noticeNo){

		String query =" select n.notice_no, n.title, n.content, n.file_name_1, m.name, to_char(n.reg_date, 'yyyy-mm-dd'), n.hit "+
						" from a20_track2_web_notice n, a20_track2_home_member m "+
						" where n.reg_id = m.id "+
						" and n.notice_no = '"+noticeNo+"'";		
		
		Notice_DTO dtoN = null;				
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String notice_no = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String file_name_1 = rs.getString(4);
				String reg_id = rs.getString(5);
				String reg_date = rs.getString(6);
				int hit = rs.getInt(7);
				
				dtoN = new Notice_DTO(notice_no,title,content,file_name_1,reg_id,reg_date,hit);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getNoticeView(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getNoticeView(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getNoticeView() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getNoticeView close Exception "+e.getMessage());
			}
		}						
		return dtoN;
	}	
	//조회수 증가
	public int noticeHit(String noticeNo) {
		int result = 0;
		String query=" update a20_track2_web_notice "+
					" set hit = hit+1 "+
					" where notice_no = '"+noticeNo+"' ";
					
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println(" RemoteException noticeHit(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException noticeHit(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.noticeHit() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" noticeHit close Exception " + e.getMessage());
			}
		}
		return result;			
	}
	// 글 수정 저장
	public int updateNotice(String notice_no,String title,String content,String reg_id,String reg_date,String file_name_1) {
		
		
		String query = " update a20_track2_web_notice "+
						" set title = '"+title+"', content = '"+content+"', "+
						" reg_id = '"+reg_id+"', reg_date = '"+reg_date+"', file_name_1 = '"+file_name_1+"' "+
						" where notice_no = '"+notice_no+"' ";
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
	// 글 수정 저장 dto
	public int updateNotice(Notice_DTO dto) {
		
		String query = " update a20_track2_web_notice "+
						" set title = '"+dto.getTitle()+"', content = '"+dto.getContent()+"', "+
						" reg_id = '"+dto.getReg_id()+"', reg_date = '"+dto.getReg_date()+"', file_name_1 = '"+dto.getFile_name_1()+"' "+
						" where notice_no = '"+dto.getNotice_no()+"' ";
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
	public int deleteNotice(String notice_no) {
		
		String query = " delete from a20_track2_web_notice "+
						" where notice_no = '"+notice_no+"' ";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		} catch (RemoteException me){
			System.out.println(" RemoteException deleteNotice(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException deleteNotice(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.deleteNotice() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" deleteNotice close Exception " + e.getMessage());
			}
		}
		return result;
	}
}