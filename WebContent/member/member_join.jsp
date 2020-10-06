<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/join.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="../js/jquery-3.3.1.min.js"></script>
		<script src="/js/common.js"></script> 
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
			<h2><span><i class="fas fa-sign-in-alt"></i> SIGN UP</span></h2>	
				<p>- 회원가입을 위해, 작성해주세요 -</p>
			</div>
			
		<!--join start-->
			<div class="join-box">
			
			<form class="join" name="member">
				<fieldset>
					<legend>회원가입 작성</legend>
					<h2 class="readonly">회원가입</h2>
					
						<ul class="id_pw">
							<li>
								<i id="id" class="fas fa-id-card-alt fa-2x"></i>
								<label for="id"><input class="id_box" type="text" name="t_id" placeholder="아이디"></label>
								<input class="check_id" type="button" value="중복검사" onClick="check_id();">
								<input type="hidden" name="id_check_value">
							</li>
							<li>	
								<i class="fas fa-unlock-alt fa-2x" ></i>
								<label for="pw"><input class="pw" type="password" name="pw" placeholder="비밀번호"></label>
							</li>	
							<li>	
								<i class="fas fa-lock fa-2x"></i>
								<label for="re_pw"><input class="pw" type="password" name="re_pw" placeholder="비밀번호 재확인"></label>
							</li>
						</ul>
						
						<ul class="name_phone">
							<li>						
								<label for="name"><input type="text" name="name" placeholder="이름"></label>
							</li>
							<li>
								<label for="phone"><input type="text" name="phone" placeholder="연락처 ex)01034232534" class="phone"></label>
								<label for="certifi"></label>
								<label for="certifi"></label>
							</li>
							<li>
								<input type="text" name="email1" class="email">&#64;
								<input type="text" name="email2" class="email">
								<select id="email" name="emailtype" class="email" onChange="selectEmail()">
									<option value="직접입력">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
								</select>
							</li>
						</ul>
							
						<ul class="check">
							<p>문자, 이메일을 통한 상품 및 이벤트 정보 수신에 동의 합니다</p>
							<li>
								<label for="agree"><input type="radio" name="agree" id="agree" value="y" checked> 1년 정보유지</label>
								<label for="agree2"><input type="radio" name="agree" id="agree2" value="n"> 탈퇴시까지 정보유지</label>
							</li>
							<li>
							<label for="yak1"><input type="checkbox" name="yak1" id="yak1">이용약관</label>
								<a href="#">전문보기</a>
								
							<label for="yak2"><input type="checkbox" name="yak2" id="yak2">개인정보이용동의</label>
								<a href="#">전문보기</a>
							</li>
						</ul>
						
						<ul class="signup">
						<input type="button" value="✔ SIGN UP" onClick="save();">
						</ul>
				</fieldset>
			</form>
			
				<div class="login_img">
					<li class="photo1"> </li>
					<li class="photo2"> </li>
					<li class="photo3"> </li>
					<li class="photo4"> </li>
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
	
		<script type="text/javascript">
		function selectEmail(){
			var mailcheck = document.member.emailtype.value;
			if(mailcheck == "직접입력"){
				document.member.email2.value = "";
			}else{
				document.member.email2.value = mailcheck;
			}
		}
		
		function check_id() {
			var id = document.member.t_id.value;
			if(id == ""){
				alert("입력된 아이디가 없습니다.");
				document.member.t_id.focus();
			} else {
				window.open("check_id.jsp?t_id="+id,
							"아이디 중복검사",
							"width=400, height=210");
			}
		}
		function save(){
		/* 	var form = document.member; */
			var agree = document.member.agree.value;
			if(!checkEmpty(member.t_id, "아이디 입력!")) return;
			if(member.id_check_value.value == ""){
				
				alert("아이디 중복 체크 하세요.");
				return;
			}
			if(member.t_id.value != member.id_check_value.value){
				alert("아이디 변경 후 중복검사를 하지 않았습니다.");
				member.t_id.focus();
			}
			
			if(!checkEmpty(member.pw, "비밀번호 입력!")) return;
			if(!checkEmpty(member.re_pw, "비밀번호 확인 입력!")) return;
			if(member.pw.value != member.re_pw.value) {
				alert("비밀번호가 일치하지 않습니다.");
				member.pw.focus();
				return;
			}
			if(!checkEmpty(member.name, "이름 입력!")) return;
			
			if(!checkEmpty(member.phone, "전화번호 입력!")) return;
			if(member.phone.value.length != 11) {
				alert("11자리로 입력!");
				member.phone.focus();
				return;
			}
			if(!checkEmpty(member.email1, "e-mail 입력!")) return;
			if(!checkEmpty(member.email2, "e-mail 선택!")) return;
			
			if(!(member.yak1.checked)) {
				alert("이용약관 동의를 체크해주세요");
				member.yak1.focus();
				return;
				}					
			
			if(!(member.yak2.checked)) {
				alert("개인정보 동의를 체크해주세요");
				member.yak2.focus();
				return;											
			}  			
				member.action = "member_proc.jsp";
				member.method = "post";
				member.submit();			
			}
		</script>		
					
	</body>
</html>