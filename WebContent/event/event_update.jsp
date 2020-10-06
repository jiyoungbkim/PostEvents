<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Event_DAO,dto.Event_DTO,common.CommonUtil,com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/index/common_session_info.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	Event_DAO dao = new Event_DAO();
	
	String event_no  = request.getParameter("t_event_no");
	String title 	 = request.getParameter("title");
	String content 	 = request.getParameter("cont");
	String reg_id    = sessionId;
	String reg_date  = request.getParameter("r_date");
	String reg_start = request.getParameter("s_date");
	String reg_end   = request.getParameter("e_date");
	int hit = 0;
	int result = 0;
	String msg ="수정";

	result = dao.updateEvent(event_no,title,content,reg_id,reg_date,reg_start,reg_end);

	/* String query = " update a20_track2_home_event "+
			" set title = '"+title+"', content = '"+content+"', "+
			" reg_id = '"+reg_id+"', reg_date = '"+reg_date+"', reg_start = '"+reg_start+"', "+
			" reg_end = '"+reg_end+"' "+
			" where event_no = '"+event_no+"' ";
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