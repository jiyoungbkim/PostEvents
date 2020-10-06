<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dto.Event_M_DTO,dao.Event_M_DAO,common.CommonUtil,com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/index/common_session_info.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	Event_M_DAO dao = new Event_M_DAO();
	
	String event_no  = request.getParameter("t_event_no");
	String event_m_no  = request.getParameter("t_event_m_no");
	String title 	 = request.getParameter("title");
	String content 	 = request.getParameter("cont");
	String reg_id    = sessionId;
	String reg_date  = CommonUtil.getToday();
	String msg ="수정";

	int result = dao.updateEventM(event_no,event_m_no,title,content,reg_id,reg_date);
	
%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("<%=msg%>"+"되었습니다.");
			<% } else {%>
				alert("<%=msg%> 처리되지 못했습니다.");
			<% }%>
			location.href = "event_r.jsp";
		</script>
	</head>
</html>