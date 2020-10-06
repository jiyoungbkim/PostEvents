<%@ page import="common.CommonUtil"%>
<%
	String sessionName = CommonUtil.checkNull((String)session.getAttribute("session_name"));
	
	String sessionId = CommonUtil.checkNull((String)session.getAttribute("session_id"));
	String sessionLevel = CommonUtil.checkNull((String)session.getAttribute("session_level"));
	//out.print("============"+sessionName);
%>

