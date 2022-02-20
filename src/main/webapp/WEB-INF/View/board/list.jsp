<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 링크경로(Head포함) -->
<%@include file="../../module/link.jsp" %>

<body>

<div class="container-fluid">

<!-- meta_Header -->
<%@include file="../../module/meta_header.jsp" %>
<!-- header -->
<%@include file="../../module/header.jsp" %>
<!-- nav -->
<%@include file="../../module/nav.jsp" %>

</div>

<%
	int totalCount = 0; // 전체 게시물 수
	int numPerPage = 10; // 페이지당 보여줄 게시물 수
	
	int pagePerBlock = 15; // pagination에서 한 블럭당 표시될 페이지 개수
	
	int totalPage = 0; // 전체 페이지 개수
	
	int totalBlock = 0; // pagination에서 모든 블럭 수
	
	int nowPage = 1; // 현재 읽고있는 페이지
	int nowBlock = 1; // 현재 있는 블럭
	
	int start = 0; // 읽을 게시물 시작위치 (DB전달)
	int end = 10; // 읽어들일 행 개수 (DB전달)
	
	// 현재 페이지 받기(BoardListController에서 전달받음)
	if(request.getParameter("nowPage") !=null){
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	
	// 페이지 처리하기
	totalCount = (Integer) request.getAttribute("totalCount");
	totalPage = (int) Math.ceil((double) totalCount / numPerPage);
	
	// 블럭 처리하기
	totalBlock = (int) Math.ceil((double) totalPage / pagePerBlock);
	nowBlock = (int) Math.ceil((double)nowPage / pagePerBlock);
	
%>

<div class="page-content p-3">

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/Home.do">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Board</li>
  </ol>
</nav>

<h1 style="font-weight:bold; color:olive;">Board PAGE</h1>

<!-- 게시판 상단 헤더(현재 페이지/전체 페이지) -->
<table class = "w-75 mt-3 mb-5">
	<tr>
		<td>Page: <span style = "font-family:cursive; font-weight:bold; color:tomato;"><%=nowPage %></span> / <%=totalPage %></td>
	</tr>
</table>

<!-- 게시물 리스트 -->
<table class = "table w-75">
	<tr>
		<td>Number</td>
		<td>Title</td>
		<td>Name</td>
		<td>Date</td>
		<td>View</td>
	</tr>
	
	<!-- 본문 -->
	<%@page import = "java.util.*, VO.*" %>
	<%
		Vector<BoardVO> list = (Vector<BoardVO>) request.getAttribute("list");
		
		for(int i=0; i<list.size(); i++){
			%>
				<tr>
					<td><%=list.get(i).getNumber() %></td>
					<td><a href="javascript:read(<%=list.get(i).getNumber() %>)" style = "text-decoration:none; font-weight:bold; font-family:cursive; color: black;" id="title"><%=list.get(i).getTitle() %></a></td>
					<td><%=list.get(i).getEmail() %></td>
					<td><%=list.get(i).getRegDate() %></td>
					<td><%=list.get(i).getView() %></td>
				</tr>
			<% 
		}
	%>
	
</table>

<!-- 페이지 처리하기(Pagination)과 버튼들 -->

<script>

	/* 현재 페이지를 받을 함수 구현 */
	function paging(page){
		// alert(page);
		
		document.readForm.nowPage.value = page;
		
		var numPerPage = <%=numPerPage%>; // 10
		
		document.readForm.start.value = (page*numPerPage) - numPerPage;
		document.readForm.end.value = numPerPage;
		
		document.readForm.action = "/Board/list.do";
		document.readForm.submit();
	}
	
	/* 블럭 이동 함수 구현 */
	function moveBlock(value){
		// alert(value)

		var numPerPage = <%=numPerPage%>; // 10
		var pagePerBlock = <%=pagePerBlock%>; // 15
		
		document.readForm.nowPage.value = pagePerBlock*(value-1)+1;
		
		var page = pagePerBlock*(value-1)+1;
		
		document.readForm.start.value = (page*numPerPage)-numPerPage;
		document.readForm.end.value = numPerPage;
		
		document.readForm.action = "/Board/list.do";
		document.readForm.submit();
		
	}
	
	/* 제일 처음 블럭으로 가기 함수 구현 */
	function moveFirst(){
		
		document.readForm.nowPage.value = 1;
		
		document.readForm.start.value = 0;
		document.readForm.end.value = 10;
		
		document.readForm.action = "/Board/list.do";
		document.readForm.submit();
	}
	
	/* 게시물 번호 받기 함수 구현 */
	function read(number){
		
		var numPerPage = <%=numPerPage %>;
		
		var page = <%=nowPage %>;
		
		document.readForm.start.value = (page*numPerPage)-numPerPage;
		document.readForm.end.value = numPerPage;
		
		document.readForm.nowPage.value = <%=nowPage %>;
		
		document.readForm.number.value = number;
		document.readForm.action = "/Board/read.do";
		document.readForm.submit();
	}
</script>

<!-- 페이지 관련 정보 전달 form -->
<form name = "readForm" method = "get">
	<input type = "hidden" name = "number">
	<input type = "hidden" name = "start">
	<input type = "hidden" name = "end">
	<input type = "hidden" name = "nowPage">
</form>

<%
	int pageStart = ((nowBlock-1)*pagePerBlock) +1;
	// 삼항 연산자 사용
	int pageEnd = ((pageStart+pagePerBlock)<=totalPage)?(pageStart+pagePerBlock):totalPage+1;
%>

<table class = "table w-75">
	<!-- Pagination -->
	<tr>
	<%
		if(totalPage !=0){
	%>
		<td>
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-end">
			  <!-- 이전 블럭으로 이동 -->
			  <%
			  	if(nowBlock>1){
			  %>
			    <li class="page-item">
			      <a class="page-link" href="javascript:moveBlock(<%=nowBlock-1 %>)" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <%
			  		}
			    %>
			    <!-- 페이지 번호 표시 -->
			    <%
			    	for(int i=pageStart; i<pageEnd; i++){
			    %>
			    <li class="page-item"><a class="page-link" href="javascript:paging(<%=i%>)"><%=i %></a></li>
			    <%
			    	}
			    %>
			    <!-- 다음 블럭으로 이동 -->
			    <%
			    	if(totalBlock>nowBlock){
			    %>
			    <li class="page-item">
			      <a class="page-link" href="javascript:moveBlock(<%=nowBlock+1 %>)" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			    <%
			    	}
			    %>
			  </ul>
			</nav>	
		</td>
		<%
			}
		%>
	
	<!-- Write & Move to First Buttons -->
		<td align = "right">
			<a href = "/Board/write.do?flag=true" class = "btn btn-info me-2">Write</a>
			<a href = "javascript:moveFirst()" class = "btn btn-warning">Move To First</a>
		</td>
	</tr>	
</table>

</div>

</body>

</html>