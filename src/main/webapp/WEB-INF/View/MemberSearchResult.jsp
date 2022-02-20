<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 링크경로(Head포함) -->
<%@include file="../module/link.jsp" %>

<body>

<div class="container-fluid">

<!-- meta_Header -->
<%@include file="../module/meta_header.jsp" %>
<!-- header -->
<%@include file="../module/header.jsp" %>
<!-- nav -->
<%@include file="../module/nav.jsp" %>

</div>

<div class="page-content p-3">

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/Home.do">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Search</li>
  </ol>
</nav>

<h1 style="font-weight:bold; color:aqua">Search</h1>

<%@page import = "VO.*" %>

<div style = "border:2px solid black; display:inline-block;">
<%
	MemberVO vo = (MemberVO) request.getAttribute("vo");

	out.println("EMAIL: " + vo.getEmail() + "<br>");
	out.println("PW: " + vo.getPassword() + "<br>");
	out.println("ADDR01: " + vo.getAddress01() + "<br>");
	out.println("ADDR02: " + vo.getAddress02() + "<br>");
	out.println("ZIPCODE: " + vo.getZipcode() + "<br>");
	out.println("ROLE: " + "<strong style = 'color: tomato;'>" + vo.getRole() + "</strong>" + "<br>");
%>
</div>

</div>

</body>
</html>