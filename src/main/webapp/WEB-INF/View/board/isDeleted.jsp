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
    <li class="breadcrumb-item active" aria-current="page">Confirm before Delete</li>
  </ol>
</nav>

<h1 style="font-weight:bold; color:olive;">Password Confirm PAGE</h1>

<form method = "post" action = "/Board/deleteRequest.do">
	<table class = "table w-50 mt-4">
		<tr>
			<td style = "font-weight:bold; font-family:cursive;">Input Password</td>
			<td><input type = "password" name = "password" class = "form-control"></td>
		</tr>
		<tr>
			<td colspan="2"><input type = "submit" value = "CONFIRM" class = "btn btn-outline-info w-100"></td>
		</tr>
	</table>
	
	<input type = "hidden" name = "nowPage" value = <%=request.getParameter("nowPage") %>>
	<input type = "hidden" name = "start" value = <%=request.getParameter("start") %>>
	<input type = "hidden" name = "end" value = <%=request.getParameter("end") %>>
</form>

</div>
</body>
</html>