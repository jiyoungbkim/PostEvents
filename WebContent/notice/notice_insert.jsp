<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp" %>
<%@ page import="com.oreilly.servlet.*,java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");	
	Notice_DAO dao = new Notice_DAO();
	String notice_no 	= dao.getNoticeNo();
	
	int sizeLimit = 1024 * 1024 * 1;
	String file_dir = CommonUtil.file_dir_notice;
	
	MultipartRequest mpr = new MultipartRequest(request,file_dir,sizeLimit,"UTF-8");
	
	String fileName = mpr.getFilesystemName("fileName_a");

	out.print(fileName);
	
	String saveFileName = "";
	if(fileName != null) {
		File oldFile = new File(file_dir,fileName);
		File newFile = new File(file_dir,notice_no+"-"+fileName);
	
		oldFile.renameTo(newFile);
		saveFileName = newFile.getName();
	} 
		//out.print(saveFileName);

		String title 		= mpr.getParameter("title");
		String content 		= mpr.getParameter("cont");
		//String value 		= mpr.getParameter("t_value");
		String reg_id 		= sessionId;
		String reg_date 	= CommonUtil.getToday();
		
		//int result = 0;
		//int result = dao.insertNotice(notice_no,title,content,reg_id,reg_date);
		//String query = "insert into a20_track2_web_notice(notice_no, title, content, reg_id, reg_date) "+
		//				" values ('"+notice_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"') ";

		Notice_DTO notice_dto = new Notice_DTO(notice_no,title,content,saveFileName,reg_id,reg_date,0);
		int result = dao.insertNotice(notice_dto);
		

%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("등록되었습니다.");
			<% } else {%>
				alert("정상처리되지 못했습니다.");
			<% }%>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>