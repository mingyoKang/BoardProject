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
    <li class="breadcrumb-item active" aria-current="page">Notice</li>
  </ol>
</nav>

<h1 style="font-weight:bold; color:aqua;">Notice PAGE</h1>

<!-- Role_ADMIN만 들어갈 수 있는 a tag -->
<a href = "/Notice/post.do" style = "text-decoration:none; font-family:cursive; font-weight: bold;">Go to Post</a>

</div>

</body>
</html>