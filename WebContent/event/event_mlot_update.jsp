<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dto.Event_M_DTO,dao.Event_M_DAO,common.CommonUtil,com.oreilly.servlet.*,java.io.*"%>
<%@ include file="/index/common_session_info.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	Event_M_DAO dao = new Event_M_DAO();

	String event_no  = request.getParameter("t_event_no");
	String[] a = request.getParameterValues("win_id");

	String msg = "당첨 완료";
	int result =  dao.lotEventAll(event_no);	
	
	if(result > 0 && a != null){
		for(int k = 0; k < a.length; k++){
			//out.print("=========="+a[k]+"<br>");
			result = dao.lotEventM(event_no, a[k]);
		}
	}
%>
<html>
	<head>
		<script>
		<% if(result > 0) {%>
			alert("<%=msg%>"+"되었습니다.");
		<% } else {%>
			alert("<%=msg%> 처리되지 못했습니다.");
		<% }%>
			opener.parent.location.href="event_r.jsp";
			window.close();
		</script>
	</head>
</html>