<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,dao.News_DAO,dto.News_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp"%>
<%	
	request.setCharacterEncoding("UTF-8");	
	String news_no = request.getParameter("t_newsNo");
	News_DAO dao = new News_DAO();
	
	String selValue = request.getParameter("t_sel");
	String txtValue = request.getParameter("t_search");
	if(selValue == null){
		selValue ="title";
		txtValue ="";
	}
	ArrayList<News_DTO> arrW = dao.getNewsList(selValue,txtValue);

	int wHit = dao.newsHit(news_no);
	//*********page start***********/
		String tdCount ="5";				

		String r_page = CommonUtil.checkNull(request.getParameter("r_page"));		
		int			current_page;					// 현재페이지 번호
		int			n_page=0;
		int			p_page=0;
		int			total_page;						// 총페이지 수
		int			total_count;					// 총레코드 수
		int			for_count;						
		int			list_setup_count = 3;			// 한번에 출력될 List 수
		int			p_no;
		int			v_count;
		int			a_count;
		String		url				= null;	

		// 조회된 건수 구하기  total_count : 설정
		if(arrW == null) total_count =0;
		else total_count = arrW.size(); 


		// 페이지번호가 없으면 1페이지로 간주
		if(r_page.equals("")) current_page = 1;
		else current_page = Integer.parseInt(r_page);
			
		for_count		= list_setup_count;
		p_no			= list_setup_count;				// 페이지수가 10
		total_page = total_count / list_setup_count;		// 전체페이지수 계산 (if 23개 게시물이면 2)
	   
		if(current_page == 1) {
			v_count		= 0;
			a_count		= list_setup_count;
			for_count	= 0;
		} else{
			v_count		= 0;
			a_count		= p_no * current_page;
			for_count	= a_count - list_setup_count;
		}
		if(total_page * list_setup_count != total_count)   total_page = total_page +1;
		
		// 이전, 다음 페이지 
		n_page = current_page +1;
		p_page = current_page -1;
	//*********page end***********/
		
%>
<!doctype html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-news.css" rel="stylesheet">
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
			<h2><a href="/notice/notice_r.jsp">NOTICE</a></h2>	
			<h2><a href="/event/event_r.jsp"> EVENT</a></h2>
			<h2><a href="/faq/faq_r.jsp"> FAQ</a></h2>	
			<h2 class="color"><a href="/news/news_r.jsp"><i class="fas fa-check"></i> NEWS</a></h2>	
			</div>
			
			<!-- sub-news start-->
			<div class="news-box">
				
				<%	if ( total_count > 0 ){
						for(int k = 0 ; k < total_count ; k++ )	{
							if(v_count == for_count){ 

				%> 	
				<ul class="news">
					<li>
						<a href="/news/news_v.jsp?t_newsNo=<%=arrW.get(k).getNews_no()%>">
							<span class="img"><img src="/file_room/news/<%=arrW.get(k).getFile_name_1()%>" alt="뉴스1"></span>
							<p><%=arrW.get(k).getTitle()%></p>
							
							<span class="size-up"></span>
						</a>
					</li>
				</ul>
				
				<%
							v_count = v_count + 1;
							for_count = for_count + 1;
						} else { 
							v_count = v_count + 1;
						}

						if(v_count == a_count)break; 

						}
					}else{	
				%>
					<TR align="center" bgcolor="white" >
						<TD colspan="<%=tdCount%>" >등록된 내용이 없습니다.</TD>
					</TR>
				<%	} %>
					
			</div>
			
			<div class="page-number">
				<%if(current_page != 1) { %>
				<a href="<%="news_r.jsp?t_sel="+selValue+"&t_search="+txtValue+"&r_page="+p_page%>" class="icon"><i class="fas fa-arrow-circle-left fa-lg"></i></a>
				<% } %>
				<%
					url = "news_r.jsp?t_sel="+selValue+"&t_search="+txtValue;		
					out.println(CommonUtil.pageList(current_page, total_page, url));
				%>
				<%if(current_page != total_page) { %>
				<a href="<%="news_r.jsp?t_sel="+selValue+"&t_search="+txtValue+"&r_page="+n_page%>" class="icon"><i class="fas fa-arrow-circle-right fa-lg"></i></a>
				<%} %>
				<a href="/news/news_w.jsp" class="btn-write">글쓰기</a>
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
	
	
	
	</body>
</html>









