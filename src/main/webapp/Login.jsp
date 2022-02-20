<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="Resources/css/common.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String MSG = (String) request.getAttribute("MSG");
	System.out.println("MSG: " + MSG);
	
	if(MSG == null){
		MSG = "";
	}
%>

<script>
	var msg = "<%=MSG%>";
	
	if(msg !=""){
		alert(msg);
	}
</script>


<div style = "width:300px; height:300px; border:1px solid black; margin:150px auto; text-align:center;">
	<h3 style = "font-weight:bold; font-family:cursive;">LOGIN</h3>
	<form method = "post" action = "LoginProc.do">
		<div class = "m-2 mt-3">
			<input name = "email" placeholder = "example@gmail.com" class = "form-control">
		</div>
		<div class = "m-2">
			<input name = "password" type = "password" placeholder = "Input password" class = "form-control">
		</div>
		<div class = "ms-2 me-2 mt-4 mb-4">
			<input name = "idCheck" type = "checkbox" class = "form-check-input">Save ID
			<input name = "pwCheck" type = "checkbox" class = "form-check-input">Save PW
		</div>
		<div class = "m-2">
			<input type = "submit" value = "Login" class = "btn btn-danger w-100">
		</div>
		<div class = "m-2">
			<a href = "MemberJoin.jsp" class = "btn btn-warning w-100">Create an Account</a>
		</div>
	</form>
</div>

</body>
</html>