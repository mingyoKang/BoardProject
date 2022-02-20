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

<div class="page-content p-3">

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/Home.do">Home</a></li>
    <li class="breadcrumb-item"><a href="/Board/list.do">Board</a></li>
    <li class="breadcrumb-item active" aria-current="page">Read</li>
  </ol>
</nav>

<%
	String MSG = (String) request.getAttribute("MSG");

	if(MSG == null){
		MSG="";
	}
%>

<script>
	var msg = "<%=MSG%>";
	
	if(msg !=""){
		alert(msg);
	}
</script>

<%@page import = "VO.*" %>

<%
	BoardVO vo = (BoardVO) session.getAttribute("BoardVO");

	String nowPage = (String) request.getAttribute("nowPage");
	
	int start = (int) request.getAttribute("start");
	int end = (int) request.getAttribute("end");
%>

<h1 style="font-weight:bold; color:olive;">Read PAGE</h1>

<table class = "table w-75">
	<tr>
		<th>Email</th>
		<td><%=vo.getEmail() %></td>
		<th>RegDate</th>
		<td><%=vo.getRegDate() %></td>
	</tr>
	<tr>
		<th>Title</th>
		<td colspan="3"><%=vo.getTitle() %></td>
	</tr>
	<tr>
		<th>Uploaded File</th>
		<%
			// 다운로드 할 파일이 있을 때는 a 태그가 있도록
			if(vo.getFileName().equals("No_Name")){
				%>
				<td colspan="3"><%=vo.getFileName() %></td>
				<% 
			}else{
				%>
				<td colspan="3"><a href="/Board/download.do"><%=vo.getFileName() %></a>(<%=vo.getFileSize()/1024 %> Kbyte)</td>
				<%
			}
		%>	
	</tr>
	<tr>
		<td colspan="4" style = "height:300px;"><textarea class = "form-control" rows="10" cols="50"><%=vo.getContent() %></textarea></td>
	</tr>
	<tr>
		<td colspan="4" align="right">IP : <%=vo.getIp() %> / View : <%=vo.getView() %></td>
	</tr>	
</table>
<table class = "table w-75">
	<tr>
		<td align="right">
			<a href="/Board/list.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end %>" class="btn btn-outline-warning me-3">BACK</a>
			<a href="/Board/updateRequest.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end %>&flag=init" class="btn btn-outline-primary me-3">UPDATE</a>
			<a href="/Board/deleteRequest.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end %>&flag=init" class="btn btn-outline-danger">DELETE</a>
		</td>
	</tr>
</table>

<!-- Comment 기능 추가하기 -->

<script>
	function postReply(){
		
		$.ajax({
			url:"/Board/replypost.do",
			type:"GET",
			datatype:"html",
			data:{"comment":$("#comment").val()},
			success:function(result){
				// alert(result);
				listReply(); // 댓글을 쓰고 난 뒤 바로 내용을 확인할 수 있도록 리스트 가져오는 함수 호출.
			},
			error:function(){
				alert("There is a problem!!");
			}
		});	
	}
	
	function listReply(){
		
		$.ajax({
			url:"/Board/replylist.do",
			type:"GET",
			datatype:"html",
			success:function(result){
				$("#replyDiv").empty();
				$("#replyDiv").append(result);
			},
			error:function(){
				alert("There is a problem!!");
			}
		});
	}
	
	listReply();
</script>

<h3 style = "font-weight:bold; color:olive;">Comment Section</h3>

<div class = "w-75" style = "background-color:lightgray;">
	
	<div class = "row">
		<div class = "col-9 m-2">
			<textarea id="comment" rows="3" class = "form-control"></textarea>
		</div>
		<div class = "col-2 m-2">
			<a class = "btn btn-outline-info w-100 h-100" onclick="postReply()">SEND</a>
		</div>
	</div>
</div>

<!-- 댓글 리스트 표시 위치 -->
<div id="replyDiv" class = "w-75" style = "height: 500px; background-color:lightgray; overflow:auto;">

</div>

</div>
</body>
</html>