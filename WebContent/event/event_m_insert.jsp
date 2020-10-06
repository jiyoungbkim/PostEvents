<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Event_DAO,dao.Event_M_DAO,dto.Event_DTO,common.CommonUtil,com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/index/common_session_info.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	Event_DAO dao = new Event_DAO();
	Event_M_DAO daoM   = new Event_M_DAO();
	String event_no   = request.getParameter("t_event_no");
	String event_m_no = daoM.getMaxId(event_no);
	
	String title 	 = request.getParameter("title");
	String content 	 = request.getParameter("cont");
	String reg_id    = sessionId;
	String reg_date  = CommonUtil.getToday();

	int result = 0;
	String msg ="등록";

	result = daoM.insertApply(event_no, event_m_no, title, content, reg_id, reg_date);

	/* String query = " insert into a20_track2_home_event_m(event_no, event_m_no, title, content, reg_id, reg_date) "+
			" values ('"+event_no+"','"+event_m_no+"','"+title+"','"+content+"','"+reg_id+"','"+reg_date+"') ";
	out.print(query); */
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