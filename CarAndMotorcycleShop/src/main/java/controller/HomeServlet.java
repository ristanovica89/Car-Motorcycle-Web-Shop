package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import models.UserRole;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); 

		if (session == null || session.getAttribute("auth") == null) {
		    response.sendRedirect("login.jsp");
		    return;
		}

		User user = (User) session.getAttribute("auth");

		if (user.getRole().equals(UserRole.ADMIN)) {
		    response.sendRedirect("pages/dashboard.jsp");
		} else {
		    response.sendRedirect("pages/webshop.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
