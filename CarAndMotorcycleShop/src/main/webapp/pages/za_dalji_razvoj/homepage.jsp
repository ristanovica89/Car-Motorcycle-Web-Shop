<%-- <%@page import="models.UserRole"%>
<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	User user = (User)request.getSession().getAttribute("auth");
	if (user == null || !user.getRole().equals(UserRole.USER)) {
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	    return;
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home - Car & Motorcycle Web Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/homepage.css">
  </head>
  <body>
   
    <nav class="navbar navbar-expand-lg px-4">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">CARS&MOTORCYCLES</a>
      <div class="ms-auto nav-user">
        User: <span id="username"><%=user.getUsername()%></span>
      </div>
      &nbsp;<a href="<%=request.getContextPath()%>/auth">logout</a>
    </nav>

    
    <div class="search-container text-white">
      <ul class="nav nav-tabs mb-3" id="searchTabs" role="tablist">
        <li class="nav-item" role="presentation">
          <button class="nav-link active" id="cars-tab" data-bs-toggle="tab" data-bs-target="#cars" type="button" role="tab">CARS</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="motorcycles-tab" data-bs-toggle="tab" data-bs-target="#motorcycles" type="button" role="tab">MOTORCYCLES</button>
        </li>
      </ul>

      <div class="tab-content" id="searchTabContent">
        
        <div class="tab-pane fade show active" id="cars" role="tabpanel">
          <form>
            <div class="mb-3">
              <label for="carPrice" class="form-label">Cena (do):</label>
              <input type="number" class="form-control" id="carPrice" placeholder="Unesite maksimalnu cenu">
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="carYearFrom" class="form-label">Godište (od):</label>
                <input type="number" class="form-control" id="carYearFrom">
              </div>
              <div class="col-md-6 mb-3">
                <label for="carYearTo" class="form-label">Godište (do):</label>
                <input type="number" class="form-control" id="carYearTo">
              </div>
            </div>
            <div class="mb-3">
              <label for="carBrand" class="form-label">Marka:</label>
              <select class="form-select" id="carBrand">
                <option value="">-- Odaberite marku --</option>
                <option>Audi</option>
                <option>BMW</option>
                <option>Yugo</option>
              </select>
            </div>
            <button type="submit" class="btn btn-light w-100">Search</button>
          </form>
        </div>

       
        <div class="tab-pane fade" id="motorcycles" role="tabpanel">
          <form>
            <div class="mb-3">
              <label for="motoPrice" class="form-label">Cena (do):</label>
              <input type="number" class="form-control" id="motoPrice" placeholder="Unesite maksimalnu cenu">
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="motoYearFrom" class="form-label">Godište (od):</label>
                <input type="number" class="form-control" id="motoYearFrom">
              </div>
              <div class="col-md-6 mb-3">
                <label for="motoYearTo" class="form-label">Godište (do):</label>
                <input type="number" class="form-control" id="motoYearTo">
              </div>
            </div>
            <div class="mb-3">
              <label for="motoBrand" class="form-label">Marka:</label>
              <select class="form-select" id="motoBrand">
                <option value="">-- Odaberite marku --</option>
                <option>Honda</option>
                <option>Suzuki</option>
              </select>
            </div>
            <button type="submit" class="btn btn-light w-100">Search</button>
          </form>
        </div>
      </div>
    </div>
    
    <div class="container mt-5" id="results">
	  <h4 class="text-white mb-4">Your Search Results</h4>
	  <div class="row" id="itemsContainer">
	    
	    <!--
	    <div class="col-md-4 mb-4">
	      <div class="card bg-light">
	        <img src="vehicle-image.jpg" class="card-img-top" alt="Vehicle Image">
	        <div class="card-body">
	          <h5 class="card-title">BMW 320</h5>
	          <p class="card-text mb-1">Cena: 9000€</p>
	          <p class="card-text mb-0">Godište: 2016</p>
	        </div>
	      </div>
	    </div>
	    -->
	  </div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>

 --%>