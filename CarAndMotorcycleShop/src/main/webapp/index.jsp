<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to our SHOP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/welcome.css">

</head>
<body>
	 <div class="welcome-box">
      <p class="subtitle">WELCOME</p>
      <h1 class="title">CAR & MOTORCYCLE WEBSHOP</h1>
      <div class="mt-4">
        <a href="pages/signup.jsp" class="btn btn-outline-light btn-custom">Sign Up</a>
        <a href="pages/login.jsp" class="btn btn-light btn-custom">Log In</a>
      </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</html>