package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import models.UserRole;
import models.Vehicle;
import models.VehicleStatus;
import validator.Validator;

import java.io.IOException;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDao;
import dao.VehicleDao;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao udao = new UserDao();
    VehicleDao vdao = new VehicleDao();
	
    public AdminServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action== null || action == "") {
			response.sendRedirect("pages/dashboard.jsp");
		}else if(action.equals("init")) {
			vdao.initDb();
			response.sendRedirect("pages/dashboard.jsp");
		}else if(action.equals("empty")) {
			vdao.empty();
			response.sendRedirect("pages/dashboard.jsp");
		}else if(action.equals("edit")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				
				Vehicle vehicle = new Vehicle();
				vehicle = vdao.getVehicleById(id);
				
				if(vehicle == null) {	
					response.sendRedirect("pages/webshop.jsp");
				}
				
				request.setAttribute("vehicle", vehicle);
				request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
				
				
				}catch(NullPointerException e){
					e.printStackTrace();
				}catch(NumberFormatException e) {
					e.printStackTrace();
				}		
		}else if(action.equals("delete")) {
			//
//			try {
//			int id = Integer.parseInt(request.getParameter("id"));
//			
//			if(id!=0) {
//				vdao.deleteVehiclebyId(id);
//			}
//			
//			response.sendRedirect("pages/webshopadmin.jsp");
//			
//			}catch(NullPointerException e){
//				e.printStackTrace();
//			}catch(NumberFormatException e) {
//				e.printStackTrace();
//			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action== null || action == "") {
			response.sendRedirect("pages/dashboard.jsp");
		}else if(action.equals("addUser")) {
			addUser(request, response);
		}else if(action.equals("edit")) {
			editVehicle(request, response);
		}
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		
		boolean isAdmin = request.getParameter("active")!=null;
		
		HashMap<String, String> msg = new HashMap<String, String>();
		
		msg = Validator.validSignUp(username, retypePassword, password);
		
		if(udao.checkUserByUsername(username)) {
			msg.put("global", "User allredy exists! Please login.");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/addNewUser.jsp").forward(request, response);
			return;
		}
		
		if(msg.isEmpty()) {
			User user = new User();
			user.setUsername(username);
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(hashedPassword);
			
			if(isAdmin) {
				user.setRole(UserRole.ADMIN);
			}else {
				user.setRole(UserRole.USER);
			}

			user = udao.addUser(user); 
			
			if(user == null) {
				msg.put("global", "SignUp error!");
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("pages/addNewUser.jsp").forward(request, response);
				return;
			}
			
			response.sendRedirect("pages/dashboard.jsp");
			
		}else {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/addNewUser.jsp").forward(request, response);
			return;
		}
	}
	private void editVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idS = request.getParameter("id");
		String priceS = request.getParameter("price");
		String yearS = request.getParameter("year");
		boolean isApproved = request.getParameter("isApproved")!=null;
		VehicleStatus status = isApproved ? VehicleStatus.APPROVED : VehicleStatus.PENDING; 
		HashMap<String, String> msg = new HashMap<String, String>();
			
		int id = Integer.parseInt(idS);
		Vehicle vehicle = vdao.getVehicleById(id);
		request.setAttribute("vehicle", vehicle);
		
		if(!priceS.matches("\\d+(\\.\\d+)?")){ 
			msg.put("price", "Input must contain digits only");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
			return;
		}
		
		if(!yearS.matches("\\d*")){
			msg.put("year", "Please input a valid year");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
			return;
		}
		
		try {
		
			
			int year = Integer.parseInt(yearS);
			double price = Double.parseDouble(priceS);
			
			if(year < 1990 || year >= 2025){
				msg.put("year", "Please input a valid year");
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
				return;
			}
			
			if(price < 1){
				msg.put("price", "Please input a valid price");
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
				return;
			}
			
		if(msg.isEmpty()) {
				
			boolean updated = vdao.updateVehicleById(id, year, price, status);
				
				if(updated) {
					response.sendRedirect("pages/webshop.jsp");
					return;
				}else {
					msg.put("global", "Update error.");
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
					return;
				}			
			
		}else {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/editVehicle.jsp").forward(request, response);
			return;
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
		
}
