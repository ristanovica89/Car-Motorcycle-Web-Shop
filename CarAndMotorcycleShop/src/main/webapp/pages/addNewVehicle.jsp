<%@page import="models.UserRole"%>
<%@page import="models.Vehicle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.VehicleDao"%>
<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		User user = (User)request.getSession().getAttribute("auth");	
		if (user == null) {
		    response.sendRedirect(request.getContextPath() + "/index.jsp");
		    return;
		}
		
		VehicleDao vdao = new VehicleDao();
		ArrayList<String>carBrand = vdao.getAllBrandNameCars();
		ArrayList<String>motorcycleBrand = vdao.getAllBrandNameMotorcycle();
		String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Vehicle - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/userNewCarUpload.css">
  </head>
  <body>

    <a href="<%=request.getContextPath()%>/pages/webshop.jsp" class="text-light top-left">&larr; Back to Webshop</a>

    <div class="form-box">
      <h2>Add Vehicle</h2>
	
	<%if(msg!=null){%>
		<h1><%= msg %></h1>
	<%} %>
	
      <!-- Tabs -->
      <ul class="nav nav-tabs" id="vehicleTabs" role="tablist">
        <li class="nav-item" role="presentation">
          <button class="nav-link active" id="car-tab" data-bs-toggle="tab" data-bs-target="#car" type="button" role="tab">CAR</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="motorcycle-tab" data-bs-toggle="tab" data-bs-target="#motorcycle" type="button" role="tab">MOTORCYCLE</button>
        </li>
      </ul>

      <div class="tab-content" id="vehicleTabsContent">
        <!-- CAR FORM -->
        <div class="tab-pane fade show active" id="car" role="tabpanel">
          <form method="post" action="<%=request.getContextPath()%>/vehicle" class="mt-3">
            <input type="hidden" name="type" value="car">

            <div class="mb-3">
              <label class="form-label">Brand:</label>
              <select class="form-select" name="brand" id="car-brand">
                <option value="">-- Select brand --</option>
                <%if(carBrand!=null){for(String brand:carBrand){ %>
				  <option><%= brand %></option>
				<%} } %>
              </select>
              <div id="brandCarError" class="text-danger mt-1"></div>
            </div>
            
            <div class="mb-3">
              <label class="form-label">Model:</label>
              <select class="form-select" name="model" id="car-model" disabled>>
                <option value="">-- Select model --</option>   
              </select>
              <div id="brandCarError" class="text-danger mt-1"></div>
            </div>

            <div class="mb-3">
              <label class="form-label">Price:</label>
              <input type="number" class="form-control" name="price" required>
              <div id="priceCarError" class="text-danger mt-1"></div>
            </div>

            <div class="mb-3">
              <label class="form-label">Year:</label>
              <input type="number" class="form-control" name="year" required>
              <div id="yearCarError" class="text-danger mt-1"></div>
            </div>

            <div class="form-check mb-3">
            <% if(user.getRole().equals(UserRole.ADMIN)){ %>
              <input class="form-check-input" type="checkbox" value="true" id="carApproved" name="isApproved">
              <label class="form-check-label" for="carApproved">Is Approved</label>
              <div id="approvedCarError" class="text-danger mt-1"></div>
            <% } %>
            </div>

            <button type="submit" class="btn btn-light w-100">Add Car</button>
          </form>
        </div>

        <!-- MOTORCYCLE FORM -->
        <div class="tab-pane fade" id="motorcycle" role="tabpanel">
          <form method="post" action="<%=request.getContextPath()%>/vehicle" class="mt-3">
            <input type="hidden" name="type" value="motorcycle">

            <div class="mb-3">
              <label class="form-label">Brand (optional):</label>
              <select class="form-select" name="brand" id="motorcycle-brand">
				  <option value="">-- Brand  --</option>
				  <%if(motorcycleBrand!=null){for(String brand:motorcycleBrand){ %>
				  <option><%= brand %></option>
				  <%} } %>
				</select>
              <div id="brandMotoError" class="text-danger mt-1"></div>
            </div>
            
            <div class="mb-3">
              <label class="form-label">Model:</label>
              <select class="form-select" name="model" id="motorcycle-model" disabled>
                <option value="">-- Select model --</option>   
              </select>
              <div id="brandMotoError" class="text-danger mt-1"></div>
            </div>

            <div class="mb-3">
              <label class="form-label">Price:</label>
              <input type="number" class="form-control" name="price" required>
              <div id="priceMotoError" class="text-danger mt-1"></div>
            </div>

            <div class="mb-3">
              <label class="form-label">Year:</label>
              <input type="number" class="form-control" name="year" required>
              <div id="yearMotoError" class="text-danger mt-1"></div>
            </div>
			
			<div class="form-check mb-3">
			<% if(user.getRole().equals(UserRole.ADMIN)){ %>
              <input class="form-check-input" type="checkbox" value="true" id="motoApproved" name="isApproved">
              <label class="form-check-label" for="motoApproved">Is Approved</label>
              <div id="approvedMotoError" class="text-danger mt-1"></div>
            <% } %>
            </div>
           

            <button type="submit" class="btn btn-light w-100">Add Motorcycle</button>
          </form>
        </div>
      </div>
    </div>
	 <script>
  		window.contextPath = "<%=request.getContextPath()%>";
	</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
  <script src="<%=request.getContextPath()%>/js/fM.js"></script>
</html>
