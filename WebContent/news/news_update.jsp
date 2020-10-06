<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.News_DAO,dto.News_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp" %>
<%@ page import="com.oreilly.servlet.*,java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");	
	News_DAO dao = new News_DAO();
	
	int sizeLimit = 1024 * 1024 * 1;
	String file_dir = CommonUtil.file_dir_news;
	
	MultipartRequest mpr = new MultipartRequest(request,file_dir,sizeLimit,"UTF-8");
	
	String news_no 		= mpr.getParameter("t_news_no");
	String title 		= mpr.getParameter("t_title");
	String content 		= mpr.getParameter("t_content");
	String reg_id 		= sessionId;
	String reg_date 	= CommonUtil.getToday();
	
	String saveFileName  = "";
	String fileName = mpr.getFilesystemName("fileName_a");
	String delFile = CommonUtil.checkNull(mpr.getParameter("checkbox_del_fileName"));
	if(!delFile.equals("")) {
		File dFa = new File(file_dir,delFile);
		dFa.delete();
	} else {
		saveFileName = mpr.getParameter("ori_fileName_a");
	}
	if(fileName != null) {
		String delFile_1 = mpr.getParameter("ori_fileName_a");
		if(delFile_1 != null) {
			File dFa = new File(file_dir,delFile_1);
			dFa.delete();
		}
		File oldFile = new File(file_dir,fileName);
		File newFile = new File(file_dir,news_no+"-"+fileName);
	
		oldFile.renameTo(newFile);
		saveFileName = newFile.getName();
	}
	int result = dao.updateNews(news_no,title,content,reg_id,reg_date,saveFileName);
%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("수정되었습니다.");
			<% } else {%>
				alert("정상처리되지 못했습니다.");
			<% }%>
			location.href = "news_r.jsp";
		</script>
	</head>
</html>