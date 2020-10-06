<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,dao.News_DAO,dto.News_DTO"%>
<%@ include file="/index/common_session_info.jsp"%>
<%
	String news_no = request.getParameter("t_newsNo");
	News_DAO dao = new News_DAO();
	int wHit = dao.newsHit(news_no);
	News_DTO dtoW = dao.getNewsView(news_no);
	String imageUrl = CommonUtil.file_dir_news;
%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-news-1.css" rel="stylesheet">
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
							<li>CONNECT WITH WIDE</li>
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
			<h2><a href="/notice/notice_r.jsp">NOTICE</a></h2>	
			<h2><a href="/event/event_r.jsp"> EVENT</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2 class="color"><a href="/news/news_r.jsp"><i class="fas fa-check"></i> NEWS</a></h2>	
			</div>
			
			<!-- sub-news start-->
			<div class="cont">
			<form name="news">
				<input type="hidden" name="t_work_gubun" value="delete">
				<input type="hidden" name="t_news_no" value="<%=news_no%>">
				<input type="hidden" name="t_fileName" value="<%=dtoW.getFile_name_1()%>">
			</form>
			
				<h3><%=dtoW.getTitle()%><br>
					<span><%=dtoW.getReg_date()%> / <%=dtoW.getReg_id()%> / 뉴스 </span></h3>	
				<img src="/file_room/news/<%=dtoW.getFile_name_1()%>" class="imgS">
				<p class="txt">
				<%=dtoW.getContent()%>.<br></p>

			</div>
						
		<!-- sub button start-->
		
			<div class="list">
				<a href="javascript:history.back();">View list</a>&nbsp;&nbsp;
				<a href="news_u.jsp?t_newsNo=<%=dtoW.getNews_no()%>">Modify</a>&nbsp;&nbsp;
				<a href="javascript:deleteNews()">Delete</a>&nbsp;&nbsp;				
			</div>	
		</div>
						
		<!--  footer start  -->
		<div id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</div>
	<script>
		function deleteNews(){
			var yn = confirm("정말 삭제 하겠습니까? ");
			if(yn) {
				var fm = document.news;
				fm.action = "news_delete.jsp";
				fm.method = "post";
				fm.submit();
			}
		}
	</script>
	</body>
</html>









