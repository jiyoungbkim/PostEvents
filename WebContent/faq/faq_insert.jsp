<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Faq_DAO,dto.Faq_DTO,common.CommonUtil,com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/index/common_session_info.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	Faq_DAO dao = new Faq_DAO();
	String faq_no = dao.getFaqNo();
	
	String title 	= request.getParameter("title");
	String content 	= request.getParameter("cont");
	String reg_id   = sessionId;
	String reg_date = CommonUtil.getToday();
	
	int result = 0;
	String msg ="등록";

	result = dao.insertFaq(faq_no,title,content,reg_id,reg_date);

	 
%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("<%=msg%>"+"되었습니다.");
			<% } else {%>
				alert("<%=msg%> 처리되지 못했습니다.");
			<% }%>
			location.href = "faq_r.jsp";
		</script>
	</head>
</html>