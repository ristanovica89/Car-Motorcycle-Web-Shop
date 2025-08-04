<%@page import="models.Vehicle"%>
<%@page import="dao.VehicleDao"%>
<%@page import="models.UserRole"%>
<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	User admin = (User)request.getSession().getAttribute("auth");
	if (admin == null || !admin.getRole().equals(UserRole.ADMIN)) {
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	    return;
	}
 
	UserDao udao = new UserDao();
 	VehicleDao vdao = new VehicleDao();
	
 	ArrayList<User>users = udao.getAllUsersForAdmin();
 	ArrayList<Vehicle>vehicles = vdao.getAllVehiclesForAdmin();
%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Admin Panel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard/dashboard.css">
</head>
<body>

  <div class="header">
    <div>Welcome: <strong>Admin</strong></div>
    <div><a href="<%=request.getContextPath()%>/auth" class="text-danger text-decoration-none">Logout</a></div>
  </div>

  <div class="btn-group-custom">
    <a href="<%=request.getContextPath()%>/pages/addNewUser.jsp" class="btn btn-outline-light btn-custom">Users</a>
    <form action="<%=request.getContextPath()%>/admin" method="get">
      <button type="submit" class="btn btn-outline-success btn-custom" name="action" value="init">InitDB</button>
    </form>
    <form action="<%=request.getContextPath()%>/admin" method="get">
      <button type="submit" class="btn btn-outline-danger btn-custom" name="action" value="empty" onclick="return confirm('Are you sure you want to empty the database?')">EmptyDB</button>
    </form>
    <a href="<%=request.getContextPath()%>/pages/webshop.jsp" class="btn btn-outline-info btn-custom">Webshop</a>
  </div>

  <div class="table-wrapper">
    <ul class="nav nav-tabs" id="adminTabs" role="tablist">
      <li class="nav-item" role="presentation">
        <button class="nav-link active" id="users-tab" data-bs-toggle="tab" data-bs-target="#users" type="button">USERS</button>
      </li>
      <li class="nav-item" role="presentation">
        <button class="nav-link" id="vehicles-tab" data-bs-toggle="tab" data-bs-target="#vehicles" type="button">VEHICLES</button>
      </li>
    </ul>

    <div class="tab-content mt-4" id="adminTabsContent">
      
      <div class="tab-pane fade show active" id="users" role="tabpanel">
        <h4 class="mb-3">Users</h4>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Role</th>
            </tr>
          </thead>
          <tbody>
            <% if(users!=null){ for(User user:users){%>
            <tr>
              <td><%=user.getUserId() %></td>
              <td><%=user.getUsername() %></td>
              <td><%=user.getRole() %></td>
              <!-- <td>
                <a href="edituser.html?id=1" class="btn btn-sm btn-warning btn-action">Edit</a>
                <button class="btn btn-sm btn-danger btn-action">Delete</button>
              </td> -->
            </tr>
            <% } } %>
          </tbody>
        </table>
      </div>

      <div class="tab-pane fade" id="vehicles" role="tabpanel">
        <h4 class="mb-3">Vehicles</h4>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Type</th>
              <th>Brand</th>
              <th>Model</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <% if(vehicles!=null){ for(Vehicle vehicle:vehicles){%>
            <tr>
              <td><%=vehicle.getVehicleId() %></td>
              <td><%=vehicle.getType() %></td>
              <td><%=vehicle.getBrand() %></td>
              <td><%=vehicle.getModel() %></td>
              <td><%=vehicle.getStatus() %></td>
              <!-- <td>
                <a href="editvehicle.html?id=101" class="btn btn-sm btn-warning btn-action">Edit</a>
                <button class="btn btn-sm btn-danger btn-action">Delete</button>
              </td> -->
            </tr>
             <% } } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
