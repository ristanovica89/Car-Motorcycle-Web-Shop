<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	HashMap<String,String> msg = (HashMap<String,String>)request.getAttribute("msg");
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Log In</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
    
  </head>
  <body>
    <a href="<%=request.getContextPath()%>/index.jsp" class="text-light top-left">&larr; Back to Welcome Page</a>
    <div class="form-box text-center">
      <h2>Log In</h2>
	    	<% if(msg!=null && msg.get("global") != null){ %>
		    <div id="globalError" class="text-danger small mt-1"><%= msg.get("global")%></div><br>
		    <% } %>
      <form action="<%=request.getContextPath()%>/auth" method="post">
		  <div class="mb-3">
		    <input type="text" class="form-control" name="username" placeholder="Username" id="username">
		    <% if(msg!=null && msg.get("username") != null){ %>
		    <div id="usernameError" class="text-danger small mt-1"><%= msg.get("username")%></div>
		    <% } %>
		  </div>
		
		  <div class="mb-3">
		    <input type="password" class="form-control" name="password" placeholder="Password" id="password">
		    <% if(msg!=null && msg.get("password") != null){ %>
		    <div id="passwordError" class="text-danger small mt-1"><%= msg.get("password")%></div>
		    <% } %>
		  </div>
		
		  <button type="submit" name="action" value="login" class="btn btn-light w-100">Log In</button>
	  </form>
      <div class="mt-3">
        <a href="<%=request.getContextPath()%>/pages/signup.jsp" class="btn btn-outline-light btn-small">Sign Up</a>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>