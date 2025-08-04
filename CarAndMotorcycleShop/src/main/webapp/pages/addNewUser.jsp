<%@page import="models.UserRole"%>
<%@page import="models.User"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	User admin = (User)request.getSession().getAttribute("auth");
	if (admin == null || !admin.getRole().equals(UserRole.ADMIN)) {
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	    return;
	}
	HashMap<String,String> msg = (HashMap<String,String>)request.getAttribute("msg");
	
%>    

  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add User - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard/addNewUser.css">
 
  </head>
  <body>
    <a href="<%=request.getContextPath()%>/pages/dashboard.jsp" class="text-light top-left">&larr; Back to Dashboard</a>

    <div class="form-box">
      <h2>Add New User</h2>
      	  <% if(msg!=null && msg.get("global") != null){ %>
          <div id="globalError" class="text-danger small mt-1"><%= msg.get("global")%></div>
          <% } %>
      <form method="post" action="<%=request.getContextPath()%>/admin"> 
        <div class="mb-3">
          <input type="text" class="form-control" placeholder="Username" id="username" name="username" required>
          <% if(msg!=null && msg.get("username") != null){ %>
          <div id="usernameError" class="text-danger small mt-1"><%= msg.get("username")%></div>
          <% } %>
        </div>

        <div class="mb-3">
          <input type="password" class="form-control" placeholder="Password" id="password" name="password" required>
          <% if(msg!=null && msg.get("username") != null){ %>
          <div id="passwordError" class="text-danger small mt-1"><%= msg.get("password")%></div>
          <% } %>
        </div>

        <div class="mb-3">
          <input type="password" class="form-control" placeholder="Retype Password" id="retypePassword" name="retypePassword" required>
          <% if(msg!=null && msg.get("retypePassword") != null){ %>
          <div id="retypePasswordError" class="text-danger small mt-1"><%= msg.get("retypePassword")%></div>
          <% } %>
        </div>

        <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" id="isAdmin" name="active">
          <label class="form-check-label" for="isAdmin">
            Is Admin
          </label>
        </div>

        <button type="submit" name="action" value="addUser" class="btn btn-light w-100">Add User</button>
      </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>