package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Vehicle;
import models.VehicleType;

import java.io.IOException;
import java.util.ArrayList;

import dao.VehicleDao;


@WebServlet("/webshop")
public class WebshopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VehicleDao vdao = new VehicleDao();
	
    public WebshopServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stype = request.getParameter("type");
		
		String sYearFrom = request.getParameter("yearFrom");
	    String sYearTo = request.getParameter("yearTo");
	    String brand = request.getParameter("brand");
	    String model = request.getParameter("model");
		
	    int yearFrom = (sYearFrom != null && !sYearFrom.isEmpty()) ? Integer.parseInt(sYearFrom) : 0;
	    int yearTo = (sYearTo != null && !sYearTo.isEmpty()) ? Integer.parseInt(sYearTo) : 0;
	    
		if(stype==null || stype=="") {
			response.sendRedirect("pages/webshop.jsp");
		} else {
			if(stype.equals("cars")) {
				VehicleType type = VehicleType.CAR;
				ArrayList<Vehicle>result = vdao.searchForVehiclesForAdmin(type,yearFrom,yearTo,brand,model);
				request.setAttribute("result", result);
				
			} else if (stype.equals("motorcycles")) {
				VehicleType type = VehicleType.MOTORCYCLE;
				ArrayList<Vehicle>result = vdao.searchForVehiclesForAdmin(type,yearFrom,yearTo,brand,model);
				request.setAttribute("result", result);
			}
			
			request.getRequestDispatcher("pages/webshop.jsp").forward(request, response);
		}
	}

}
