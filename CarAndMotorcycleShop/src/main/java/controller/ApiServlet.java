package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.VehicleDao;


@WebServlet("/api")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VehicleDao vdao = new VehicleDao();
   
    public ApiServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    try {
	        BufferedReader reader = request.getReader();
	        Gson gson = new Gson();

	        JsonObject json = gson.fromJson(reader, JsonObject.class);
	        String brand = json.get("brand").getAsString();

	        ArrayList<String> models = vdao.getAllModelsOfABrand(brand);

	        PrintWriter out = response.getWriter();

	        if (models != null) {
	            String jsonModels = gson.toJson(models);
	            out.print(jsonModels);
	        } else {
	            JsonObject error = new JsonObject();
	            error.addProperty("error", "No models found." + brand);
	            out.print(error.toString());
	        }

	        out.flush();

	    } catch (Exception e) {
	        e.printStackTrace();
	        JsonObject error = new JsonObject();
	        error.addProperty("error", "Server error: " + e.getMessage());
	        response.getWriter().print(error.toString());
	    }
	}
	
	

}
