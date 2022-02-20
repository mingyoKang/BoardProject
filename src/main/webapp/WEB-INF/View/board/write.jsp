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
    <li class="breadcrumb-item active" aria-current="page">Write</li>
  </ol>
</nav>

<h1 style="font-weight:bold; color:olive;">Write PAGE</h1>

<%@page import = "VO.*" %>

<%
	MemberVO vo = (MemberVO) session.getAttribute("vo");
%>

<form method = "post" action = "/Board/write.do?flag=false" enctype = "multipart/form-data">
	<table class = "table w-75">
		<tr>
			<td style = "font-family:cursive; font-weight:bold;">ID</td>
			<td><input name = "email" class = "form-control" value = "<%=vo.getEmail() %>" disabled></td>
		</tr>
		<tr>
			<td style = "font-family:cursive; font-weight:bold;">Title</td>
			<td><input name = "title" class = "form-control"></td>
		</tr>
		<tr>
			<td style = "font-family:cursive; font-weight:bold;">Content</td>
			<td><textarea name = "content" rows = "10" cols = "50" class = "form-control"></textarea></td>
		</tr>
		<tr>
			<td style = "font-family:cursive; font-weight:bold;">Password</td>
			<td><input name = "password" type = "password" class = "form-control"></td>
		</tr>
		<tr>
			<td style = "font-family:cursive; font-weight:bold;">Upload File</td>
			<td><input name = "anonyFile" type = "file" class = "form-control"></td>
		</tr>
		<tr>
			<td colspan = "2" align = "right">
				<input type = "submit" value = "POST" class = "btn btn-success">
				<input type = "reset" value = "RESET" class = "btn btn-warning">
				<input type = "button" value = "BACK" class = "btn btn-secondary" onClick = "history.back()">
				<!-- on 시리즈에는 javascript가 내장되어있다. 그래서 javascript를 쓰지 않고도 내장객체를 사용가능하다. -->
			</td>
		</tr>										
	</table>
</form>

</div>
</body>
</html>