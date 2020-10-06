package common;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil{
	// 첨부파일 저장 경로
	public static String file_dir_notice= "C:/Kimjiyoung_eclipse_source/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/kjy_jsp/file_room/notice";
	public static String file_dir_news= "C:/Kimjiyoung_eclipse_source/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/kjy_jsp/file_room/news";
	// 오늘날짜
	public static String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}
	// 오늘날짜2
		public static String getToday2(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String today = sdf.format(date);
			return today;
		}
	// 날짜 Int로 변환
	public static Integer datetoInt(String date) {
		int i_date = 0;
		
		String year = date.substring(0, 4);
	    String month = date.substring(5, 7);
	    String day = date.substring(8, 10);
		
	    String s_date = year+month+day;
	    
	    i_date = Integer.parseInt(s_date);
	    
		return i_date;
	}
	// 날짜형식 검사
	public static boolean checkDate(String checkDate){
		SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyy-MM-dd");
		boolean checkD = true;
		dateFormat.setLenient(false);
		try{
			dateFormat.parse(checkDate);
		}catch(ParseException e){
			checkD = false;
		}
		return checkD;
	}
	// 오늘날짜 이전,이후 검사
	public static boolean checkToday(String inputDate) {
		boolean checkD = true;
		//String inputDate = "2019-08-29";
		int year = Integer.parseInt(inputDate.substring(0,4));
		int month = Integer.parseInt(inputDate.substring(5,7))-1;
		int day = Integer.parseInt(inputDate.substring(8,10));
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar checkToday = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		checkToday.set(Calendar.HOUR_OF_DAY, 0);
		checkToday.set(Calendar.MINUTE, 0);
		checkToday.set(Calendar.SECOND, 0);
		checkToday.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		checkToday.set(year, month, day);

		if (checkToday.before(today)) {
			checkD = false;
		}
		return checkD;
	}
	//대여일자 검사
	public static boolean checkSRdate(String start, String end) {
	    boolean checkD = true;
	    
	    int year = Integer.parseInt(start.substring(0, 4));
	    int month = Integer.parseInt(start.substring(5, 7)) - 1;
	    int day = Integer.parseInt(start.substring(8, 10));
	    
	    int year2 = Integer.parseInt(end.substring(0, 4));
	    int month2 = Integer.parseInt(end.substring(5, 7)) - 1;
	    int day2 = Integer.parseInt(end.substring(8, 10));
	    
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar checkStart = Calendar.getInstance();
	    Calendar checkEnd = Calendar.getInstance();
	    
	    checkStart.set(11, 0);
	    checkStart.set(12, 0);
	    checkStart.set(13, 0);
	    checkStart.set(14, 0);
	    checkEnd.set(11, 0);
	    checkEnd.set(12, 0);
	    checkEnd.set(13, 0);
	    checkEnd.set(14, 0);
	    
	    checkStart.set(year, month, day);
	    checkEnd.set(year2, month2, day2);
	    
	    if (checkEnd.before(checkStart)) {
	      checkD = false;
	    }
	    return checkD;
	  }
	// 부족한 자리수 만큼 채우기 왼쪽채우기
    public static String getLPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// 부족한 자리수 만큼 채우기 양쪽채우기
    public static String getCPad(String str, int size, String strFillText) {
        int intPadPos = 0;
        for(int i = (str.getBytes()).length; i < size; i++) {
            if(intPadPos == 0) {
                str += strFillText;
                intPadPos = 1;
            } else {
                str = strFillText + str;
                intPadPos = 0;
            }
        }
        return str;
    }
	// 부족한 자리수 만큼 채우기 오른쪽채우기
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }
	// null 검사
	public static String checkNull(String sValue){
		String result="";
		if(sValue != null) result = sValue;
		return result;
	}
	// 페이지
	public static String pageList(int current_page,int totalpage, String list_url){
		int pagenumber;    //화면에 보여질 페이지 인덱스수
		int startpage;     //화면에 보여질 시작 페이지 번호
		int endpage;       //화면에 보여질 마지막 페이지 번호
		int curpage;       //이동하고자 하는 페이지 번호
		
		String strList=""; //리턴될 페이지 인덱스 리스트
		
		pagenumber = 5;   //한 화면의 페이지 인덱스수
		
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
			strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><i class='fa fa-angle-double-left'></i></a>";
		}
						
		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a href='' class='active'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href='"+list_url+"&r_page="+curpage+"'><font color=#666699>"+curpage+"</font></a>";
			}
			curpage++;
		}
		//뒤에 페이지가 더 있는 경우
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href='"+list_url+"&r_page="+curpage+"'><i class='fa fa-angle-double-right'></i></a>";
		}
		
		return strList;
	}	
	public static String pageList2(int current_page,int totalpage){
		int pagenumber;    //화면에 보여질 페이지 인덱스수
		int startpage;     //화면에 보여질 시작 페이지 번호
		int endpage;       //화면에 보여질 마지막 페이지 번호
		int curpage;       //이동하고자 하는 페이지 번호
		
		String strList=""; //리턴될 페이지 인덱스 리스트
		
		pagenumber = 5;   //한 화면의 페이지 인덱스수
		
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
			strList = strList +"<a href=javascript:goPage("+curpage+")><i class='fa fa-angle-double-left'></i></a>";
		}
						
		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a href='' class='active'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href=javascript:goPage("+curpage+")><font color=#666699>"+curpage+"</font></a>";
			}
			curpage++;
		}
		//뒤에 페이지가 더 있는 경우
		if(totalpage > endpage){
			curpage = endpage+1;
			strList = strList + "<a href=javascript:goPage("+curpage+")><i class='fa fa-angle-double-right'></i></a>";
		}
		
		return strList;
	}	
	
}
