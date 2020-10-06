<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Event_M_DAO,common.CommonUtil"%>
<%@ page import="com.oreilly.servlet.*,java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");	
	Event_M_DAO dao = new Event_M_DAO();
	
	String event_no = request.getParameter("t_event_no");
	
	String event_m_no 	= request.getParameter("t_event_m_no");	

	
	int result = dao.deleteEventM(event_no, event_m_no);
	
	String query = " delete from a20_track2_home_event_m "+ 
			"where event_no = '"+event_no+"' "+ 
			"and event_m_no ='"+event_m_no+"' ";
	out.print(query);
%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("삭제되었습니다.");
			<% } else {%>
				alert("삭제처리되지 못했습니다.");
			<% }%>
			//location.href = "event_r.jsp";
		</script>
	</head>
</html>