package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import models.UserRole;
import validator.Validator;

import java.io.IOException;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDao;


@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao udao = new UserDao();   
    
    public AuthServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth")!=null) {
			request.getSession().invalidate();
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
				
		if(action==null || action=="") {
			response.sendRedirect("index.jsp");
		} else {
			if(action.equals("signup")) {
				signup(request, response);
				
			} else if (action.equals("login")) {
				login(request,response);
			}
		}
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		HashMap<String, String> msg = new HashMap<String, String>();
		
		msg = Validator.validSignUp(username, retypePassword, password);
		
		if(udao.checkUserByUsername(username)) {
			msg.put("global", "User allredy exists! Please login.");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
			return;
		}
		
		if(msg.isEmpty()) {
			User user = new User();
			user.setUsername(username);
			
			
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(hashedPassword);
			
			
			user.setRole(UserRole.USER);
			
			user = udao.signup(user);
			
			if(user == null) {
				msg.put("global", "SignUp error!");
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
				return;
			}
			
			HttpSession session = request.getSession();	
			session.setAttribute("auth", user);
			response.sendRedirect("home");
			
		}else {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
			return;
		}
		
		
		
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    HashMap<String, String> msg = new HashMap<String, String>();
	    msg = Validator.validLogin(username, password);

	    if (msg.isEmpty()) {
	        User userFromDb = udao.getUserByUsername(username);

	        if (userFromDb == null) {
	            msg.put("global", "User does not exist! Please signup.");
	            request.setAttribute("msg", msg);
	            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	            return;
	        }

	        if (BCrypt.checkpw(password, userFromDb.getPassword())) {
	            HttpSession session = request.getSession();
	            session.setAttribute("auth", userFromDb);
	            response.sendRedirect("home");
	        } else {
	            msg.put("global", "Incorrect password!");
	            request.setAttribute("msg", msg);
	            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	        }

	    } else {
	        request.setAttribute("msg", msg);
	        request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	    }
	}
}
