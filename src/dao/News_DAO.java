package dao;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import common.DBConnectionOracle;
import dto.News_DTO;
import common.*;

public class News_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public String getMaxNo(){
		String query = "select max(news_no) from a20_track2_web_news";
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
	public String getNewsNo(){
		String newsNo = getMaxNo();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		
		if(newsNo == null) {
			newsNo = nowYear+"_0001";
		} else {
			String checkYear = newsNo.substring(0,2);
			
			int y =0;
			if(nowYear.equals(checkYear)) {
				y = Integer.parseInt(newsNo.substring(3));
				y++;
				String r = CommonUtil.getLPad(Integer.toString(y), 4, "0");
				newsNo = checkYear+"_"+r;
			} else {
				newsNo = nowYear + "_0001";
			}
		}
		return newsNo;
	}
	public int insertNews(String news_no, String title, String content, String reg_id, String reg_date, String file_name_1) {
		String query = " insert into a20_track2_web_news(news_no, title, content, reg_id, reg_date, file_name_1) "+
						" values ('"+news_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"','"+file_name_1+"')";
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
		} catch (RemoteException me){
			System.out.println(" RemoteException insertNews(): " + me.getMessage());
		} catch (SQLException se){
			System.out.println(" SQLException insertNews(): " + se.getMessage());
		} catch (Exception e) {
			System.out.println(" 오류 : D_2_DAO.insertNews() ");
		} finally {
			try {
				common.close(con, ps);
			} catch (Exception e) {
				System.out.println(" insertNews close Exception " + e.getMessage());
			}
		}
		return result;
	}
	// 인덱스 조회
	public ArrayList<News_DTO> getNewsIndex(){
		ArrayList<News_DTO> arrW = new ArrayList<News_DTO>();
		String query = " select news_no, substr(title, 1,6), substr(content, 1,15), to_char(reg_date, 'yyyy-mm-dd') "+
						" from (select news_no, title, content, reg_date "+
						" from a20_track2_web_news "+
						" order by reg_date desc) "+
						" where rownum <= 5 ";
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()){
				String news_no = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String reg_date = rs.getString(4);
				
				News_DTO dto = new News_DTO(news_no,title,content,reg_date);
				arrW.add(dto);
			}
		} catch(RemoteException me) {
			System.out.println(" RemoteException getNewsIndex(): "+me.getMessage());
		} catch(SQLException se) {
			System.out.println(" SQLException getNewsIndex(): "+se.getMessage());
		} catch(Exception e) {
			System.out.println(" 오류 : D_2_DAO.getNewsIndex() ");
		} finally {
			try{
				common.close(con, ps, rs);
			} catch(Exception e) {
				System.out.println(" getNewsIndex close Exception "+e.getMessage());
			}
		}				
		
		return arrW;
	}
	public ArrayList<News_DTO> getNewsList(String selValue, String txtValue) {
    ArrayList<News_DTO> arrW = new ArrayList<News_DTO>();
    String query = " select w.news_no, w.title, w.content, m.name, to_char(w.reg_date, 'yyyy-mm-dd'), w.file_name_1, w.hit "+
				" from a20_track2_web_news w, a20_track2_web_member m "+
				" where w.reg_id = m.id "+
				" and "+selValue+" like '%"+txtValue+"%' "+
				" order by w.news_no desc ";
    
    try {
      con = common.getConnection();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        String news_no = rs.getString(1);
        String title = rs.getString(2);
        String content = rs.getString(3);
        String reg_id = rs.getString(4);
        String reg_date = rs.getString(5);
        String file_name_1 = rs.getString(6);
        int hit = rs.getInt(7);
        
        News_DTO news_DTO = new News_DTO(news_no, title, content, reg_id, reg_date, file_name_1, hit);
        arrW.add(news_DTO);
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
    return arrW;
  }
  public News_DTO getNewsView(String newsNo) {
    String query =" select w.news_no, w.title, w.content, m.name, to_char(w.reg_date, 'yyyy-mm-dd'), w.file_name_1, w.hit "+
				" from a20_track2_web_news w, a20_track2_web_member m "+
				" where w.reg_id = m.id "+
				" and w.news_no = '" + newsNo + "' ";

    
    News_DTO news_DTO = null;
    try {
      con = common.getConnection();
      ps = con.prepareStatement(query);
      rs = ps.executeQuery();
      while (rs.next()) {
        String news_no = rs.getString(1);
        String title = rs.getString(2);
        String content = rs.getString(3);
        String reg_id = rs.getString(4);
        String reg_date = rs.getString(5);
		String file_name_1 = rs.getString(6);
        int hit = rs.getInt(7);
        
        news_DTO = new News_DTO(news_no, title, content, reg_id, reg_date, file_name_1, hit);
      } 
    } catch (RemoteException remoteException) {
      System.out.println(" RemoteException getNewsView(): " + remoteException.getMessage());
    } catch (SQLException sQLException) {
      System.out.println(" SQLException getNewsView(): " + sQLException.getMessage());
    } catch (Exception exception) {
      System.out.println(" error : D_2_DAO.getNewsView() ");
    } finally {
      try {
        common.close(con, ps, rs);
      } catch (Exception exception) {
        System.out.println(" getNewsView close Exception " + exception.getMessage());
      } 
    } 
    
    return news_DTO;
  }
  public int newsHit(String newsNo) {
    int i = 0;
    String str = " update a20_track2_web_news  set hit = hit+1 "+
				" where news_no = '" + newsNo + "' ";

    
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(str);
      i = this.ps.executeUpdate();
    } catch (RemoteException remoteException) {
      System.out.println(" RemoteException newsHit(): " + remoteException.getMessage());
    } catch (SQLException sQLException) {
      System.out.println(" SQLException newsHit(): " + sQLException.getMessage());
    } catch (Exception exception) {
      System.out.println(" error : D_2_DAO.newsHit() ");
    } finally {
      try {
        this.common.close(this.con, this.ps);
      } catch (Exception exception) {
        System.out.println(" newsHit close Exception " + exception.getMessage());
      } 
    } 
    return i;
  }
  public int updateNews(String news_no,String title,String content,String reg_id,String reg_date,String file_name_1) {
    String query = "";
    if(!file_name_1.equals("null")){
		query = " update a20_track2_web_news "+
				" set title = '" + title + "', content = '" + content + "', "+
				" reg_id = '" + reg_id + "', reg_date = '" + reg_date + "', file_name_1 = '"+file_name_1+"' "+
				" where news_no = '" + news_no + "' ";
    } else {
		query = " update a20_track2_web_news "+
				" set title = '" + title + "', content = '" + content + "', "+
				" reg_id = '" + reg_id + "', reg_date = '" + reg_date + "', file_name_1 = null "+
				" where news_no = '" + news_no + "' ";
	}
    int i = 0;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(query);
      i = this.ps.executeUpdate();
    }
    catch (RemoteException remoteException) {
      System.out.println(" RemoteException updateNews(): " + remoteException.getMessage());
    } catch (SQLException sQLException) {
      System.out.println(" SQLException updateNews(): " + sQLException.getMessage());
    } catch (Exception exception) {
      System.out.println(" error : D_2_DAO.updateNews() ");
    } finally {
		
      try {
        this.common.close(this.con, this.ps);
      } catch (Exception exception) {
        System.out.println(" updateNews close Exception " + exception.getMessage());
      } 
    } 
    return i;
  }
  public int deleteNews(String newsNo) {
    String str = " delete from a20_track2_web_news "+
				" where news_no = '" + newsNo + "' ";
   
    int i = 0;
    try {
      this.con = this.common.getConnection();
      this.ps = this.con.prepareStatement(str);
      i = this.ps.executeUpdate();
    }
    catch (RemoteException remoteException) {
      System.out.println(" RemoteException deleteNotice(): " + remoteException.getMessage());
    } catch (SQLException sQLException) {
      System.out.println(" SQLException deleteNotice(): " + sQLException.getMessage());
    } catch (Exception exception) {
      System.out.println(" error : D_2_DAO.deleteNotice() ");
    } finally {
      try {
        this.common.close(this.con, this.ps);
      } catch (Exception exception) {
        System.out.println(" deleteNotice close Exception " + exception.getMessage());
      } 
    } 
    return i;
  }
}