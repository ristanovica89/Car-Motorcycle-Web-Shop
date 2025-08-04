<%@page import="models.UserRole"%>
<%@page import="models.User"%>
<%@page import="java.util.HashMap"%>
<%@page import="models.VehicleStatus"%>
<%@page import="models.VehicleType"%>
<%@page import="models.Vehicle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User admin = (User)request.getSession().getAttribute("auth");
	if (admin == null || !admin.getRole().equals(UserRole.ADMIN)) {
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	    return;
	}


	Vehicle v = (Vehicle)request.getAttribute("vehicle");
	HashMap<String,String>msg = (HashMap<String,String>)request.getAttribute("msg");
%>    
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Vehicle - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard/editVehicle.css">
  </head>
  <body>
    <a href="<%=request.getContextPath()%>/pages/webshop.jsp" class="text-light top-left">&larr; Back to WebShop</a>
	
    <div class="form-box">
      <h2>Edit Vehicle</h2>
      <% if(v!=null){ %>
      	<% if(msg!=null && msg.get("global") != null){%>
      	<div id="globalError" class="text-danger small mt-1"><%=msg.get("global") %></div>
      	<% } %>
      <form action="<%=request.getContextPath()%>/admin" method="post" >
        <input type="hidden" name="id" value="<%= v.getVehicleId()%>">
		
		<div class="mb-3">
          <label class="form-label">Brand:</label>
          <input type="text" class="form-control text-muted" name="brand" value="<%=v.getBrand() %>" disabled>
        </div>
        
        <div class="mb-3">
          <label class="form-label">Model:</label>
          <input type="text" class="form-control text-muted" name="model" value="<%=v.getModel() %>" disabled>
        </div>
		
        <div class="mb-3">
          <label class="form-label">Price:</label>
          <input type="number" class="form-control" name="price" value="<%=v.getPrice() %>" required>
          <% if(msg!=null && msg.get("price") != null){%>
          <div id="priceError" class="text-danger small mt-1"><%=msg.get("price") %></div>
          <% } %>
        </div>

        <div class="mb-3">
          <label class="form-label">Year:</label>
          <input type="number" class="form-control" name="year" value="<%=v.getYear() %>" required>
          <% if(msg!=null && msg.get("year") != null){%>
          <div id="yearError" class="text-danger small mt-1"><%=msg.get("year") %></div>
          <% } %>
        </div>

        <%-- <div class="form-check mb-3">
          <input class="form-check-input" type="checkbox" 
          <% if(v.getStatus().equals(VehicleStatus.APPROVED)){ %>
          	value="true" 
          <%}else{ %>
          	value="false"
          <%} %>
          id="approved" name="isApproved" checked>
          <label class="form-check-label" for="approved">Is Approved</label>
        </div> --%>
        <div class="form-check mb-3">
		  <input class="form-check-input" type="checkbox" id="approved" name="isApproved" value="true"
		         <% if(v.getStatus().equals(VehicleStatus.APPROVED)){ %>
		             checked
		         <% } %>>
		  <label class="form-check-label" for="approved">Is Approved</label>
		</div>

        <button type="submit" name="action" value="edit" class="btn btn-light w-100">Update Vehicle</button>
      </form>
      <% }%>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>