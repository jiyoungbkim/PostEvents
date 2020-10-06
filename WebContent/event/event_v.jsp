<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,dao.Event_DAO,dto.Event_DTO,dao.Event_M_DAO,dto.Event_M_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp"%>
<%
	String event_no = request.getParameter("t_eventNo");
	String event_m_no = request.getParameter("t_eventNom");


	Event_DAO dao = new Event_DAO();
	Event_M_DAO daoM = new Event_M_DAO();
	
	int nHit = dao.eventHit(event_no);
	Event_DTO dtoE = dao.getEventView(event_no);
	
	String reg_id    = sessionId;
	Event_M_DTO dtoM = daoM.getApplyView(reg_id);
	int count = daoM.getMemberCount(event_no, reg_id);
	int e_count = daoM.getEventCount(event_no);
	
	
	int reg_date  = Integer.parseInt(CommonUtil.getToday2());
%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/notice-cont.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		
	<body>
		<!-- skip navigation -->
		<dl id="access">
			<dt>바로가기 및 건너띄기 링크</dt>
			<dd><a href="#contents">본문바로가기</a></dd>
			<dd><a href="#navigation">주메뉴 바로가기</a></dd>
		</dl>
		<hr>
		
		<div id="big-box">
			<header>
			<div id="header">
				<div class="nav">
					<h2 class="readonly">주메뉴</h2>
						<ul class="nav-menu">
						<li><a href="sub1.html">ABOUT EL WIDE</a></li>
						<li><a href="sub2.html">MUSIC</a></li>
						<li><a href="sub3.html">MEDIA</a></li>
						<li><a href="sub4.html">CULTURE</a></li>
						</ul>
				<div class="logo">
					<h1 class="el-logo"><a href="/index/index.jsp"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<%if(!sessionName.equals("")) { %>
							<li><a href="/member/member_logout.jsp"><i class="fas fa-arrow-alt-circle-right"></i></a></li>
							<%} else {%><li><a href="/member/member_login.jsp"><i class="fas fa-user"></i></a></li><%} %>
						</ul>
					</div>
					<div class="side-text">
						 <ul>
							<%if(!sessionName.equals("")) { %>
							<li>[<%=sessionName%>]님 welcome</li>
							<%} else {%><li>CONNECT WITH WIDE</li><%} %>
						 </ul>
					</div>
				</div>
				</div>
			</div>
			</header>
		</div>
		
		<!--  header end -->
				
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2><a href="/notice/notice_r.jsp"> NOTICE</a></h2>	
			<h2 class="color"><a href="/event/event_r.jsp"><i class="fas fa-check"></i> EVENT</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2><a href="/news/news_r.jsp"> NEWS</a></h2>
			</div>
			
		<!-- cont start-->
		<div class="cont-box">
		<form name="notice" >
			<input type="hidden" name="t_work_gubun" value="delete">
			<input type="hidden" name="t_event_no" value="<%=event_no%>">
			<input type="hidden" name="t_event_m_no">
		</form>
		
			<h3><%=dtoE.getTitle()%><br>
				<span><%=dtoE.getReg_date()%> / <%=dtoE.getReg_id()%> / 이벤트 </span></h3>			
			
			<table class="table">
				<caption>공지사항 글쓰기</caption>
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
		
				<input type="hidden" name="t_date">

				<tr>
					<th><label for="date">이벤트 기간</label></th>
					<td class ="date" name="s_date" class="e_date">&nbsp;&nbsp;<%=dtoE.getReg_start() %> ~ <%=dtoE.getReg_end()%>				
				</tr>
				
				<tr>
					<th><label for="cont">내용</label></th>
					<td colspan="2" name="cont"><textarea type="cont" id="cont" class="cont" readonly><%=dtoE.getContent()%></textarea>
				</tr>
				
									
			</table>									
			<br>
			<br>
			<br>
			<%if(count != 0) {%>	
			<table class="table">
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>		

				<tr>
					<th><label for="title">제목</label></th>
					<td colspan="2"><input type="text" name="title" id="title" class="title" value="<%=dtoM.getTitle()%>"></td>
				</tr>
				<tr>
					<th><label for="title">신청내용</label></th>
					<td colspan="2"><input type="text" name="content" id="content" class="content" value="<%=dtoM.getContent()%>"></td>
				</tr>
				<tr>
					<td colspan="3">
					<div class="list2">	
					<%if(count != 0 && reg_date <= CommonUtil.datetoInt(dtoE.getReg_end())){ %>	
						<a href="event_mu.jsp?t_eventNo=<%=dtoM.getEvent_no()%>&t_eventNom=<%=dtoM.getEvent_m_no()%>">Modify</a>&nbsp;&nbsp;				
						<a href="javascript:deleteApply('<%=dtoM.getEvent_m_no()%>')">Cancel</a>&nbsp;&nbsp;
						
					<% } %>
					</div>
					</td>
				</tr>
					
			</table>
			<% }%>
			<div>

			</div>				
		</div>			
		<!-- sub button start-->
		
		<div class="list">			
			<a href="javascript:history.back();">View list</a>&nbsp;&nbsp;
			<%if(count == 0 && reg_date <= CommonUtil.datetoInt(dtoE.getReg_end())){ %>
				<a href="event_mw.jsp?t_eventNo=<%=dtoE.getEvent_no()%>">Apply</a>&nbsp;&nbsp;
			<% } %>
			<%if(sessionId.equals("manager")) {%>
				<a href="event_u.jsp?t_eventNo=<%=dtoE.getEvent_no()%>">Modify</a>&nbsp;&nbsp;
			<% } %>
			<%if(sessionId.equals("manager") && e_count == 0 && reg_date <= CommonUtil.datetoInt(dtoE.getReg_end())){ %>
				<a href="javascript:deleteNotice()">Delete</a>&nbsp;&nbsp;
			<% } %>
			
		</div>
				
		</div>
		
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="/index/index.jsp" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
		</div>	
	<script>
		function deleteApply(emNo){
			var yn = confirm("정말 삭제 하겠습니까? ");
			if(yn) {
				var fm = document.notice;
				fm.t_event_m_no.value = emNo;
				fm.action = "event_m_delete.jsp";
				//fm.action = "notice_proc.jsp";
				fm.method = "post";
				fm.submit();
			}
		}
	</script>
	</body>
	
</html>









