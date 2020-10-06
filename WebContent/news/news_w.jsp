<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/index/common_session_info.jsp"%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/noticewrite.css" rel="stylesheet">
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
			<h2><span><i class="fas fa-edit"></i> NEWS-write</span></h2>	
			</div>
			
			<div class="notice-write">
			
			<form name="write" enctype="multipart/form-data">
					<h2 class="readonly">제목, 첨부파일, 내용을 작성합니다</h2>
				
					<fieldset>
						<legend>공지사항 글쓰기</legend>
						
						<table class="table">
							<caption>공지사항 글쓰기</caption>
							<colgroup>
								<col width="20%">
								<col width="*">
							</colgroup>
							
							<tr>
								<th><label for="title">제목</label></th>
								<td><input type="text" name="title" id="title" class="title" placeholder="제목을 입력해주세요"></td>
							</tr>
							
							<tr>
								<th><label for="cont">내용</label></th>
								<td><textarea type="cont" name="cont" id="cont" class="cont" placeholder="내용을 입력해주세요"></textarea>
							</tr>
							
							<tr>
								<th><label for="file">파일 첨부</label></th>
								<td><input type="file" name="fileName_a" class="file" id="file"></label></td>
							</tr>
							
							<tr>
								<td colspan="2">
								<input type="button" onClick="javascript:noticecheck();" value="전송" class="btn" >
								<input type="button" onClick="javascript:history.back();" value="목록" class="btn">
								</td>
							</tr>

							</table>
					</fieldset>
				</form>
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
			function noticecheck() {
				var fm = document.write;
				if(fm.title.value==""){
					alert("제목을 입력하세요");
					fm.title.focus();
					return ;
				}
				if(fm.cont.value==""){
					alert("내용을 입력하세요");
					fm.cont.focus();
					return ;
				}	
			
				var file = fm.fileName_a;
				if(file.value != "") {
					var fileName = file.value;
					
					var pathFileName = fileName.lastIndexOf(".")+1; // 확장자명 제외한 경로+파일명
					var extension = (fileName.substr(pathFileName)).toLowerCase(); // 확장자명
					//파일명 . 확장자
					if(extension != "jpg" && extension != "gif" && extension != "png"){
						alert(extension +"형식 파일은 업로드 안됩니다. 이미지 파일만 가능!");
						return;
					}
					
					//alert(file.value);
					var position = file.value.lastIndexOf("\\");
					//alert("position :"+position);
					
					//var len = file.value.length;
					var fName = file.value.substr(position+1);
					var len = fName.length;
					//alert("len : "+len);
					if(len > 20) {
						alert("첨부파일명 길이 20자리 이내로"+len);
						return;
					}
					// 사이즈체크
					var maxSize  = 1 * 1024 * 1024    //2MB
					var fileSize = 0;

					// 브라우저 확인
					var browser=navigator.appName;
					
					// 익스플로러일 경우
					if (browser=="Microsoft Internet Explorer")
					{
						var oas = new ActiveXObject("Scripting.FileSystemObject");
						fileSize = oas.getFile(file.value).size;
					}
					// 익스플로러가 아닐경우
					else
					{
						fileSize = file.files[0].size;
					}


					//alert("파일사이즈 : "+ fileSize);

					if(fileSize > maxSize)
					{
						alert("첨부파일 사이즈는 2MB 이내로 등록 가능합니다.");
						return;
					}
				}
				fm.action = "news_insert.jsp";
				fm.method = "post";
				fm.submit();
			}
		</script>
	
	</body>
</html>