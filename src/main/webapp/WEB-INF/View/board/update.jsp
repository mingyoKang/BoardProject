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
    <li class="breadcrumb-item active" aria-current="page">Update</li>
  </ol>
</nav>


<%@page import = "VO.*" %>

<%
	BoardVO vo = (BoardVO) session.getAttribute("BoardVO");
%>

<h1 style="font-weight:bold; color:olive;">Update PAGE</h1>

<form method = "post" action = "/Board/update.do">
	<table class = "table w-75">
		<tr>
			<th>Email</th>
			<td><%=vo.getEmail() %></td>
			<th>RegDate</th>
			<td><%=vo.getRegDate() %></td>
		</tr>
		<tr>
			<th>Title</th>
			<td colspan="3"><input name = "title" value = "<%=vo.getTitle() %>" class = "form-control"></td>
		</tr>
		<tr>
			<th>Uploaded File</th>
			<td colspan="3"><%=vo.getFileName() %></td>
		</tr>
		<tr>
			<td colspan="4" style = "height:300px;"><textarea name = "content" class = "form-control" rows="10" cols="50"><%=vo.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="right">IP : <%=vo.getIp() %> / View : <%=vo.getView() %></td>
		</tr>	
	</table>
	<table class = "table w-75">
		<tr>
			<td align="right">
				<input type = "submit" value = "UPDATE" class = "btn btn-outline-primary">
				<a href = "/Board/list.do" class = "btn btn-outline-danger">CANCEL</a>
			</td>
		</tr>
	</table>
	
	<input type = "hidden" name = "nowPage" value = <%=request.getParameter("nowPage") %>>
	<input type = "hidden" name = "start" value = <%=request.getParameter("start") %>>
	<input type = "hidden" name = "end" value = <%=request.getParameter("end") %>>
</form>
</div>
</body>
</html>