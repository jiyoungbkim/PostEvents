<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/common_session_info.jsp"%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/login.css" rel="stylesheet">
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
					<h1 class="el-logo"><a href="index.html"><img src="/images/elwide-logo.svg" width="88" height="88"></a></h1>
				</div>
				<div class="side-bar">
					<div class="side-menu">
						<ul>
							<li><a href="http://www.facebook.com/elmusickorea" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="https://blog.naver.com/elmusicstudio" target="_blank"><i class="fab fa-blogger-b"> </i></a></li>
							<li><a href="https://www.youtube.com/channel/UCkoJ_TsGn-WqDVWEzGnhfcA"target="_blank"
							><i class="fab fa-youtube"> </i></a></li>
							<li><a href="login.html"><i class="fas fa-user"></i></a></li>
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
			<h2><span><i class="fas fa-sign-in-alt"></i> LOGIN </span></h2>	
				<p>- 로그인이 필요합니다 -</p>
			</div>
			
		<!--login start-->
			<div class="login-box">
			<form name="loginform">
				<fieldset>
					<legend>로그인</legend>
					<div class="left-box">
						<p><label for="id" class="readonly">아이디</label>ID &nbsp;&nbsp;&nbsp;<input type="text" class="txt" id="id" name="id" onkeypress="if(event.keyCode==13){go_password()}" autofocus="autofocus" placeholder="&nbsp;&nbsp;아이디를 입력하세요"></p>
						<p><label for="password" class="readonly">비밀번호</label>PW &nbsp;<input type="password" class="txt" id="password" name="pw" onkeypress="if(event.keyCode==13){logincheck()}" placeholder="&nbsp;&nbsp;비밀번호를 입력하세요"></p>
					</div>
					
					<div class="right-box">
						<input type="button" value="LOGIN" class="log" onClick="logincheck();">
					</div>
					
					<div class="checksave">
						<input type="checkbox" value="1" id="idsave" name="idsave"><label for="idsave">&nbsp;&nbsp;&nbsp;아이디 저장</label>
				
						<input type="checkbox" value="1" id="pwsave" name="pwsave" class="margin"><label for="pwsave">&nbsp;&nbsp;&nbsp;비밀번호 저장</label>
					</div>
						
						<p class="btn">
						<a href="#">ID/PW찾기</a>
						<a href="/member/member_join.jsp">회원가입</a>
						</p>
						
				</fieldset>
			</form>
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
		</div>
	
	
		<script>
		
			function logincheck() {
				
				if(loginform.id.value=="") {
				alert("아이디를 입력하세요");
				loginform.id.focus();
				return;
				}
				if(loginform.password.value=="") {
				alert("비밀번호를 입력하세요");
				loginform.password.focus();
				return;
				}
				
				loginform.method = "post";
				loginform.action = "member_login_proc.jsp";				
				loginform.submit();
			}
			function go_password(){
				document.loginform.pw.focus();
			}
		</script>
		
	</body>
</html>