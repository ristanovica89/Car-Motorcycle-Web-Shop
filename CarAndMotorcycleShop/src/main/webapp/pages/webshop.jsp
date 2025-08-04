<%@page import="models.UserRole"%>
<%@page import="models.User"%>
<%@page import="models.VehicleType"%>
<%@page import="dao.VehicleDao"%>
<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	User user = (User)request.getSession().getAttribute("auth");	
	if (user == null) {
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	    return;
	}
	
	VehicleDao vdao = new VehicleDao();
	ArrayList<Vehicle>vehicles = (ArrayList<Vehicle>)request.getAttribute("result");
	ArrayList<String>carBrand = vdao.getAllBrandNameCars();
	ArrayList<String>motorcycleBrand = vdao.getAllBrandNameMotorcycle();
	
	
%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Webshop Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard/webshop.css">
    
  </head>
  <body>
  	<% if(user.getRole().equals(UserRole.ADMIN)){ %>
	<a href="<%=request.getContextPath()%>/pages/dashboard.jsp" class="text-light top-left">&larr; Back to Dashboard</a>
	<% } %>
   <div class="header">
      <div> 
      <strong>
      	<% if(user.getRole().equals(UserRole.USER)){ %>
        User: <span id="username"><%=user.getUsername()%></span>
        <% } else { %>
        Admin: <span id="username"><%=user.getUsername()%></span>
        <% } %>
      </strong></div>
      <div><a href="<%=request.getContextPath()%>/index.jsp">CARS&MOTORCYCLES</a></div>
      <div><a href="<%=request.getContextPath()%>/auth" class="text-danger text-decoration-none">Logout</a></div>
    </div>
   

    <div class="form-box mt-4">
      <ul class="nav nav-tabs" id="vehicleTabs" role="tablist">
        <li class="nav-item" role="presentation">
          <button class="nav-link active" id="car-tab" data-bs-toggle="tab" data-bs-target="#car" type="button">CARS</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="motorcycle-tab" data-bs-toggle="tab" data-bs-target="#motorcycle" type="button">MOTORCYCLES</button>
        </li>
      </ul>

      <div class="tab-content mt-3" id="vehicleTabsContent">
        
        <div class="tab-pane fade show active" id="car" role="tabpanel">
          <form action="<%=request.getContextPath()%>/webshop" method="post">
            <div class="row">
              <!-- <div class="col-md-3 mb-3">
                <input type="number" class="form-control" name="priceTo" placeholder="Cena (do)">
              </div> -->
              <div class="col-md-3 mb-3">
                <input type="number" class="form-control" name="yearFrom" placeholder="Year (from)">
              </div>
              
              <div class="col-md-3 mb-3">
                <input type="number" class="form-control" name="yearTo" placeholder="Year (to)">
              </div>
              
              <div class="col-md-3 mb-3">
                <select class="form-select" name="brand" id="car-brand">
				  <option value="">-- Brand --</option>
				  <%if(carBrand!=null){for(String brand:carBrand){ %>
				  <option><%= brand %></option>
				  <%} } %>
				</select>
              </div>
              
              <div class="col-md-3 mb-3">
			  	<select class="form-select" name="model" id="car-model" disabled>
				  <option value="">-- Model --</option>
				</select>
			</div>
            </div>
            <button class="btn btn-light w-100" name="type" value="cars">Search Cars</button>
          </form>
        </div>

        
        <div class="tab-pane fade" id="motorcycle" role="tabpanel">
          <form action="<%=request.getContextPath()%>/webshop" method="post">
            <div class="row">
              <!-- <div class="col-md-3 mb-3">
                <input type="number" class="form-control" name="priceTo" placeholder="Cena (do)">
              </div> -->
              <div class="col-md-3 mb-3">
                <input type="number" class="form-control" name="yearFrom" placeholder="Year (from)">
              </div>
              <div class="col-md-3 mb-3">
                <input type="number" class="form-control" name="yearTo" placeholder="Year (to)">
              </div>
              <div class="col-md-3 mb-3">
                <select class="form-select" name="brand" id="motorcycle-brand">
				  <option value="">-- Brand  --</option>
				  <%if(motorcycleBrand!=null){for(String brand:motorcycleBrand){ %>
				  <option><%= brand %></option>
				  <%} } %>
				</select>
              </div>
              <div class="col-md-3 mb-3">
			  	<select class="form-select" name="model" id="motorcycle-model" disabled>
				  <option value="">-- Model --</option>
				</select>
			</div>
            </div>
            <button class="btn btn-light w-100" name="type" value="motorcycles">Search Motorcycles</button>
          </form>
        </div>
      </div>
    </div>

    
    <div class="main-container">
      <div class="table-wrapper">
        <h4 class="mb-3">Search Results</h4>
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>Type</th>
              <th>Price</th>
              <th>Year</th>
              <th>Brand</th>
              <th>Model</th>
              <% if(user.getRole().equals(UserRole.ADMIN)){ %>
			    <th>Action</th>
			  <% } %> 
             
            </tr>
          </thead>
          <tbody>
           <% if(vehicles!=null){for(Vehicle v:vehicles){%>
            <tr>
              <td><%=v.getVehicleId() %></td>
              <td><%=v.getType() %></td>
              <td><%=v.getPrice() %></td>
              <td><%=v.getYear() %></td>
              <td><%=v.getBrand() %></td>
              <td><%=v.getModel() %></td>
            	<% if(user.getRole().equals(UserRole.ADMIN)){ %>
           		 <td>
	        	<a href="<%=request.getContextPath()%>/admin?action=edit&id=<%=v.getVehicleId() %>" class="btn btn-sm btn-warning btn-action">Edit</a>
               	<button class="btn btn-sm btn-danger btn-action" onclick="deleteVehicleById(<%=v.getVehicleId() %>,'<%=request.getContextPath()%>' )" >Delete</button>
	        	</td>
	       	 	<% } %>
		      	
              
            </tr>
           <% } } %>
          </tbody>
        </table>
       
      </div>
    </div>
    
     <a href="<%=request.getContextPath()%>/pages/addNewVehicle.jsp" class="text-light top-left">&larr; Add New Vehicle</a>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
  		window.contextPath = "<%=request.getContextPath()%>";
	</script>
   
  </body>
  <script src="<%=request.getContextPath()%>/js/crudLogic.js"></script>
  <script src="<%=request.getContextPath()%>/js/fM.js"></script>
</html>
