<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Member_DAO,dto.Member_DTO,common.CommonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");	
	Member_DAO dao = new Member_DAO();
	
	String id = request.getParameter("t_id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email_1 = request.getParameter("email1");
	String email_2 = request.getParameter("email2");
	String agree = request.getParameter("agree");
	String status = "";
	
    int result = 0;
	result = dao.insertMember(id, pw, name, phone, email_1, email_2, agree, status);

%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("회원가입 되었습니다.");
			<% } else {%>
				alert("회원가입 실패했습니다.");
			<% }%>
			location.href = "/member/member_join.jsp";
		</script>
	</head>
</html>