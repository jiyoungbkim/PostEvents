<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/index/common_session_info.jsp"%>
<!doctype html>
<html lang="ko">
	<title>김지영 Homepage</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/el.css" rel="stylesheet">
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
						<ul class="nav-menu">
							<li><a href="sub1.html">ABOUT EL WIDE</a></li>
							<li><a href="sub2.html">MUSIC</a></li>
							<li><a href="sub3.html">MEDIA</a></li>
							<li><a href="sub4.html">CULTURE</a></li>
							<li><a href="/notice/notice_r.jsp">NOTICE</a></li>
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
		
		<section id="main-visual">
			
				<section class="main-photo">
					<p class="main1"><a href="sub2.html"></a></p>
					<p class="main2"><a href="sub3.html"></a></p>
					<p class="main3"><a href="sub4.html"></a></p>
				</section>
				
				<section class="main-text">
					<div class="references">
						<h2><span>ABO</span>UT EL WIDE</h2>
						<ul class="small-refer1">
							<li>이엘 와이드는 즐거움의 가치를 창조하는 문화콘텐츠 기업으로<br>
							건강하고 유익한 콘텐츠를 통해 대한민국 대중문화를 선도합니다.</li>
							<li class="plus"><a href="sub-notice.html" "alt="공지사항"> Notice &nbsp &nbsp > </a></li>
							<li class="plus"><a href="sub-contact.html" "alt="콘택트"> contact &nbsp &nbsp > </a></li>
						</ul>
					</div> 
					
					<div class="references">
						<div class="padding">
						<h2><span>CON</span>TACT</h2>
						<ul class="small-refer2">
							<li>이엘와이드코퍼레이션</li>
							<li>03924. 서울 마포구 월드컵북로56길 12 트루텍빌딩 10층<br>
								<span class="f-s11">10F, Trutec Building, 12, World Cup buk-ro 56-gil, Mapo-gu, Seoul, South Korea</span><br>
								+82 2 6931 5012</li>
							<li><a href="email" alt="이메일주소">contact@elwide.com</a></li>
						</ul>
						</div>
					</div>
					
					<div class="references">				
						<h2><span>MAP</span></h2>
						<ul class="small-refer3">
							<li><a href="sub-contact.html" alt="지도"></a></li>
						</ul>
					</div>
				</section>
		</section>
		
				<!-- section end  -->
				
		<footer id="footer">
			<div class="footer-text">
				<ul class="sub-logo">
					<li><a href="index.html" alt="서브로고">EL WIDE</a></li>
				</ul>
				
				<ul class="copy">
					<li>Copyright ⓒ EL WIDE. All Rights Reserved.</li>
				</ul>
			</div>
		</footer>
	
	
	
	</body>
</html>









