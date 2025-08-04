package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.VehicleType;

import java.io.IOException;

import dao.VehicleDao;


@WebServlet("/vehicle")
public class VehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VehicleDao vdao = new VehicleDao(); 
    
    public VehicleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stype = request.getParameter("type");
		
	    String model = request.getParameter("model");
	    String priceS = request.getParameter("price");
	    String yearS = request.getParameter("year");
		
	    double price = (priceS != null && !priceS.isEmpty()) ? Double.parseDouble(priceS) : 0.0;
	    int year = (yearS != null && !yearS.isEmpty()) ? Integer.parseInt(yearS) : 0;
	    int modelId = vdao.getModelIdFromModelName(model);
	    System.out.println(modelId);
	    System.out.println(price + " " + year + " " + stype);
	    boolean success = false;
	    
		if(stype==null || stype=="") {
			response.sendRedirect("pages/webshop.jsp");
		} else {
			if(stype.equals("car")) {
				VehicleType type = VehicleType.CAR;
				
				success = vdao.insertNewVehicle(type, modelId, year, price);
				System.out.println(success);
				if(!success) {
					request.setAttribute("msg", "Error!");
					request.getRequestDispatcher("pages/addNewVehicle.jsp").forward(request, response);
				}
				
				response.sendRedirect("pages/addNewVehicle.jsp?msg=success");
				
				
			} else if (stype.equals("motorcycle")) {
				VehicleType type = VehicleType.MOTORCYCLE;
			
				success = vdao.insertNewVehicle(type, modelId, year, price);
				System.out.println(success);
				if(!success) {
					request.setAttribute("msg", "Error!");
					request.getRequestDispatcher("pages/addNewVehicle.jsp").forward(request, response);
				}
				
				response.sendRedirect("pages/addNewVehicle.jsp?msg=success");
			}
			
		}
	}

}
