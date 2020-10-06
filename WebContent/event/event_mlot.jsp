<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,dao.Event_DAO,dao.Event_M_DAO,dto.Event_DTO,dto.Event_M_DTO,common.CommonUtil"%>
<%@ include file="/index/common_session_info.jsp"%>
<%	
	request.setCharacterEncoding("UTF-8");	
	String event_no = request.getParameter("t_eventNo");

	Event_DAO dao = new Event_DAO();
	Event_M_DAO daoM = new Event_M_DAO();
	ArrayList<Event_M_DTO> arrM2 = new ArrayList<Event_M_DTO>();
	
	String selValue = request.getParameter("t_sel");
	String txtValue = request.getParameter("t_search");
	if(selValue == null){
		selValue ="title";
		txtValue ="";
	}
	
	ArrayList<Event_M_DTO> arrM = daoM.getPrizeList(event_no);
	
	arrM2 = daoM.getApplyList(selValue,txtValue);			

	int reg_date  = Integer.parseInt(CommonUtil.getToday2());
	int status = daoM.lotStatus(event_no);
	
//*********page start***********/
	String tdCount ="8";				

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
	if(arrM == null) total_count =0;
	else total_count = arrM.size(); 


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
<!DOCTYPE html>
<html lang="ko">
	<title>project1</title>
		<meta charset="utf-8">
		<link href="/css/common.css" rel="stylesheet">
		<link href="/css/sub-notice.css" rel="stylesheet">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.9/css/all.css">
		<script src="/js/jquery-3.3.1.min.js"></script>
		
	<body>
		
		<!-- sub page start -->
		<div class="notice">
			<div class="sub-notice">
			<h2 class="color"><a href="/event/event_r.jsp">EVENT</a></h2>
		</div>
<script>
	function formSearch(){
		var fm = document.notice;
		fm.action = "event_r.jsp";
		fm.method = "post";
		
		//alert(fm.t_sel.value);
		//alert(fm.t_search.value);
		fm.submit();
	}
	function winner(){
		var fm = document.notice;

		/* var check = "";
		if($("input:checkbox[name=win_id]").is(":checked") == true) {
		
			check = 'y';
		} else {
	
			check = 'n';
		}
		fm.check.value = check; */
		
		fm.action = "event_mlot_update.jsp";
		fm.method = "post";
		fm.submit();			
		
	}


</script>
		<form name="notice">
			<div class="search">
				<select name="t_sel">
					<option value="title" <%if(selValue.equals("title")) out.print("selected");%>>&nbsp;제 목</option>
					<option value="content" <%if(selValue.equals("content")) out.print("selected");%>>&nbsp;내 용</option>
				</select>
				<input type="text" value="<%=txtValue%>" name="t_search" style="height:30px">
				<input type="button" class="btn-search" onClick="javascript:formSearch()" value=" 검 색 " style="width: 60px"/>
			</div>
			<input type="hidden" name="t_event_no" value="<%=event_no%>">
			<!-- table start-->
			
			<div class="table-box">
				<table class="table">
					<caption>공지사항 - 번호, 제목, 첨부, 작성일, 조회수</caption>
					<colgroup>
						<col width="10%">
						<col width="*">
						<col width="15%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col">신청번호</th>
							<th scope="col">제목</th>
							<th scope="col">내용</th>
							<th scope="col">신청자</th>
							<th scope="col">신청일</th>
							<th scope="col">당첨여부</th>
						</tr>
					</thead>
					
					<tbody>
					<%  if ( total_count > 0 ){
						for(int k = 0 ; k < total_count ; k++ )	{
							if(v_count == for_count){ 
					%> 	
					 <tr>
						<td><%=arrM.get(k).getEvent_m_no()%></td>
						<td class="title"><%=arrM.get(k).getTitle()%></td>
						<td><%=arrM.get(k).getContent()%></td>						
						<td><%=arrM.get(k).getReg_id()%></td>
						<td><%=arrM.get(k).getReg_date()%></td>
						<td>
						<input type="checkbox" id="win_id" name="win_id" value="<%=arrM.get(k).getReg_id()%>" <%if(arrM.get(k).getStatus().equals("당첨")) out.print("checked");%>> 당첨
						</td>
					 </tr>

					 <%
							v_count = v_count + 1;
							for_count = for_count + 1;
						} else { 
							v_count = v_count + 1;
						}

						if(v_count == a_count)break; 

						}
					} else {	
					%>
						<TR align="center" bgcolor="white" >
							<TD colspan="<%=tdCount%>" >신청자가 없습니다.</TD>
						</TR>
					<%	} %>
				</table>
			</div>
			</form>	
			<div class="page-number">
				<%if(current_page != 1) { %>
				<a href="<%="event_mlot.jsp?t_sel="+selValue+"&t_search="+txtValue+"&r_page="+p_page%>" class="icon"><i class="fas fa-arrow-circle-left fa-lg"></i></a>
				<% } %>
				<%
					url = "event_mlot.jsp?t_sel="+selValue+"&t_search="+txtValue;		
					out.println(CommonUtil.pageList(current_page, total_page, url));
				%>
				<%if(current_page != total_page) { %>
				<a href="<%="event_mlot.jsp?t_sel="+selValue+"&t_search="+txtValue+"&r_page="+n_page%>" class="icon"><i class="fas fa-arrow-circle-right fa-lg"></i></a>
				<%} %>
				<a href="javascript:winner()" class="btn-write">당첨</a>
			</div>	
		</div>		


	</body>
</html>