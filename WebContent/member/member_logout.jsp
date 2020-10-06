<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/index/common_session_info.jsp"%>
<%
    session.invalidate();
%>
<html>
    <head>
        <script>
                alert("<%=sessionName%> 님 로그아웃되었습니다.");
                location.href = "/index/index.jsp";
        </script>
    </head>
</html>