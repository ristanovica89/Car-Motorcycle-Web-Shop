package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.VehicleDao;

@WebServlet("/api/vehicle")
public class VehicleApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VehicleDao vdao = new VehicleDao();
    public VehicleApiServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    try {
	        BufferedReader reader = request.getReader();
	        Gson gson = new Gson();

	        JsonObject json = gson.fromJson(reader, JsonObject.class);
	        int id = json.get("id").getAsInt();

	        boolean deleted = vdao.deleteVehiclebyId(id);

	        
	        
	        if (deleted) {
	            out.print("{\"status\":\"success\"}");
	        } else {
	        	out.print("{\"status\":\"not-found\"}");
	        }

	        out.flush();

	    } catch (Exception e) {
	    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
	    }
	}
}
