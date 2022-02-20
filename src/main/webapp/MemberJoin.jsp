<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<div style = "margin:100px auto; width: 50%;">
<h2 style = "font-family:cursive; text-align:center;">-Create an Account-</h2>
	<form class="row g-3" action = "/MemberJoin.do">
  <div class="col-md-6">
    <label for="inputEmail4" class="form-label">Email</label>
    <input type="email" name = "email" class="form-control" id="inputEmail4">
  </div>
  <div class="col-md-6">
    <label for="inputPassword4" class="form-label">Password</label>
    <input type="password" name = "password" class="form-control" id="inputPassword4">
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Address01</label>
    <input type="text" name = "address01" class="form-control" id="inputAddress" placeholder="Seoul">
  </div>
  <div class="col-12">
    <label for="inputAddress2" class="form-label">Address02</label>
    <input type="text" name = "address02" class="form-control" id="inputAddress2" placeholder="Dong, Apartment">
  </div>
  
  <div class="col-md-4">
    <label for="inputZip" class="form-label">Zipcode</label>
    <input type="text" name = "zipcode" class="form-control" id="inputZip">
  </div>

  <div class="col-12">
    <button type="submit" class="btn btn-success w-100">Create an Account</button>
  </div>
</form>
</div>


</head>
<body>

</body>
</html>