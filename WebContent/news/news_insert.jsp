<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.News_DAO,dto.News_DTO,common.CommonUtil,com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/index/common_session_info.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	News_DAO dao = new News_DAO();
	String news_no = dao.getNewsNo();
	
	int sizeLimit = 1024 * 1024 * 1;
	String file_dir = CommonUtil.file_dir_news;
	
	MultipartRequest mpr = new MultipartRequest(request,file_dir,sizeLimit,"UTF-8");
	
	String fileName = mpr.getFilesystemName("fileName_a");
	
	//out.print(fileName);
	
	String saveFileName = "";
	if(fileName != null) {
		File oldFile = new File(file_dir,fileName);
		File newFile = new File(file_dir,news_no+"-"+fileName);
	
		oldFile.renameTo(newFile);
		saveFileName = newFile.getName();
	}
	
	
	String title 	= mpr.getParameter("title");
	String content 	= mpr.getParameter("cont");
	String reg_id = sessionId;
	String reg_date 	= CommonUtil.getToday();
	

	int result = 0;
	String msg ="등록";

	result = dao.insertNews(news_no,title,content,reg_id,reg_date,saveFileName);

	 
%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("<%=msg%>"+"되었습니다.");
			<% } else {%>
				alert("<%=msg%> 처리되지 못했습니다.");
			<% }%>
			location.href = "news_r.jsp";
		</script>
	</head>
</html>