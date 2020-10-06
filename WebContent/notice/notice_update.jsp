<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page import="dao.Notice_DAO,dto.Notice_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp" %>
<%@ page import="com.oreilly.servlet.*,java.io.*"%>
<%
	request.setCharacterEncoding("UTF-8");	
	Notice_DAO dao = new Notice_DAO();
	
	int sizeLimit = 1024 * 1024 * 1;
	String file_dir = CommonUtil.file_dir_notice;
	
	MultipartRequest mpr = new MultipartRequest(request,file_dir,sizeLimit,"UTF-8");

	String notice_no 	= mpr.getParameter("t_notice_no");	
	String title 		= mpr.getParameter("t_title");
	String content 		= mpr.getParameter("t_content");
	String reg_id 		= sessionId;
	String reg_date 	= CommonUtil.getToday();
	
	String saveFileName  = "";
	String fileName = mpr.getFilesystemName("fileName_a");
	String delFile = CommonUtil.checkNull(mpr.getParameter("checkbox_del_fileName"));
	out.print(delFile);
	
	if(!delFile.equals("")) {
		File dFa = new File(file_dir,delFile);
		dFa.delete();
	} else {
		saveFileName = mpr.getParameter("ori_fileName_a");
	}
	if(fileName != null) {
		String delFile_1 = mpr.getParameter("ori_fileName_a");
		if(delFile_1 != null) {
			File dFa = new File(file_dir,delFile_1);
			dFa.delete();
		}
		File oldFile = new File(file_dir,fileName);
		File newFile = new File(file_dir,notice_no+"-"+fileName);
	
		oldFile.renameTo(newFile);
		saveFileName = newFile.getName();
	}
	out.print(saveFileName);
	Notice_DTO notice_dto = new Notice_DTO(notice_no,title,content,saveFileName,reg_id,reg_date,0);
	int result = dao.updateNotice(notice_dto);
	
	//int result = dao.updateNotice(notice_no,title,content,reg_id,reg_date);
	//int result = 0;
%>
<html>
	<head>
		<script>
			<% if(result > 0) {%>
				alert("수정되었습니다.");
			<% } else {%>
				alert("정상처리되지 못했습니다.");
			<% }%>
			location.href = "notice_r.jsp";
		</script>
	</head>
</html>