package com.journaldev.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String userID = "Ihor";
	private final String password = "journaldev";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		
		if (userID.equals(user) && password.equals(pwd)) {
			Cookie loginCookie = new Cookie("user", user);
			loginCookie.setMaxAge(30*60);
			resp.addCookie(loginCookie);
			resp.sendRedirect("LoginSuccess.jsp");
		} 
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = resp.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(req, resp);
		}
	}
}
