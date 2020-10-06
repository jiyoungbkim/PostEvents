package cla;
import java.text.*;
import java.io.*; 
import java.util.*;
public class ProcWork{
	//public String notice_dir = "D:/webserver/webapps/ROOT/file_room/";
	public String test(){
		return "aaaaaaaaaaaaa";	
	}	
	

    public boolean chkFileSize(String ff, int size, String dir){
		boolean yn = true;
		File fileSizeLong = new File(dir,ff);
        long fileLength = fileSizeLong.length();
        if(fileLength ==0){
            File dF = new File(dir,ff);
            dF.delete();
            yn= false;

        }
        if(fileLength > 1024 * 1024 * size){
            File dF = new File(dir,ff);
            boolean aa  = dF.delete();
            yn= false;
        }
		return yn;
	}	
	public String replace(String str){
		str = str.replaceAll("\\\'", "\\\\\'");
		return str;
	}	

	public String replace2(String str){
		str = str.replaceAll("-", "");
		return str;
	}
	
	public String checkNull(String sValue){
		String result="";
		if(sValue != null) result = sValue;
		
		return result;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	public String getToday() {
		java.util.Date d = new java.util.Date();
		return sdf.format(d);
	}	
	public String getToday2() {
		java.util.Date d = new java.util.Date();
		return sdf2.format(d);
	}	
	String getTodayTwo() {
		java.util.Date d = new java.util.Date();
		return sdfTwo.format(d);
	}

	
   /**	연도 */
   public int getYear() {
      Calendar cd = new GregorianCalendar(Locale.KOREA);
      int year = cd.get(cd.YEAR);
      return year;
   }	
   /**	월 */
   public int getMonth(){
      Calendar cd = new GregorianCalendar(Locale.KOREA);
      int month = cd.get(cd.MONTH) + 1;
      return month;
   } 
	/**	일 */   
   public int getDay() {
      Calendar cd = new GregorianCalendar(Locale.KOREA);
      int day = cd.get(cd.DAY_OF_MONTH);
      return day;
   } 	
   /**	연도 */
   public String getYearString() {
      Calendar cd = new GregorianCalendar(Locale.KOREA);
      int year = cd.get(cd.YEAR);
      return Integer.toString(year);
   }	
   /**	월 */
   public String getMonthString(){
      Calendar cd = new GregorianCalendar(Locale.KOREA);
      int month = cd.get(cd.MONTH) + 1;
		String rs ="";
		if(month < 10) rs = "0"+Integer.toString(month);
		else rs  = Integer.toString(month);

      return rs;
   } 
	/**	일 */   
   public String getDayString() {
      Calendar cd = new GregorianCalendar(Locale.KOREA);
      int day = cd.get(cd.DAY_OF_MONTH);
		String rs ="";
		if(day < 10) rs = "0"+Integer.toString(day);
		else rs  = Integer.toString(day);

      return rs;
   } 	
	public String pageList(int current_page,int totalpage, String list_url){
		int pagenumber;    //화면에 보여질 페이지 인덱스수
		int startpage;     //화면에 보여질 시작 페이지 번호
		int endpage;       //화면에 보여질 마지막 페이지 번호
		int curpage;       //이동하고자 하는 페이지 번호
		
		String strList=""; //리턴될 페이지 인덱스 리스트
		
		pagenumber = 10;   //한 화면의 페이지 인덱스수
		
		//시작 페이지 번호 구하기
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//마지막 페이지 번호 구하기
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//총페이지수가 계산된 마지막 페이지 번호보다 작을 경우
		//총페이지수가 마지막 페이지 번호가 됨
		
		if(totalpage <= endpage)  endpage = totalpage;
					
		//첫번째 페이지 인덱스 화면이 아닌경우
		if(current_page > pagenumber){
			curpage = startpage -1;  //시작페이지 번호보다 1적은 페이지로 이동
			strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><font color=#666699>[Prev]...</font></a>";
		}
						
		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"&nbsp;<font color=#003366><b>["+current_page+"]</b></font>&nbsp;";
			} else {
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><font color=#666699>["+curpage+"]</font></a>";
			}
			curpage++;
		}
		//뒤에 페이지가 더 있는 경우
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href='"+list_url+"&r_page="+curpage+"'><font color=#666699>...[Next]</font></a>";
		}
		
		return strList;
	}				
}	