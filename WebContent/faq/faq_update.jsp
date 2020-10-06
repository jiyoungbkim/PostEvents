<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Faq_DAO,dto.Faq_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp" %>
<%@ page import="com.oreilly.servlet.*,java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");	
	Faq_DAO dao = new Faq_DAO();

	String faq_no 	= request.getParameter("t_faq_no");	
	String title 		= request.getParameter("title");
	String content 		= request.getParameter("cont");
	String reg_id 		= sessionId;
	String reg_date 	= CommonUtil.getToday();	
	
	int result = dao.updateFaq(faq_no,title,content,reg_id,reg_date);

%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("수정되었습니다.");
			<% } else {%>
				alert("정상처리되지 못했습니다.");
			<% }%>
			location.href = "faq_r.jsp";
		</script>
	</head>
</html>