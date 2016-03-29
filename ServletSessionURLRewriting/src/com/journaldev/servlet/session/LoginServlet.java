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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String userID = "admin";
	private final String password = "password";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		
		if (userID.equals(user) && password.equals(pwd)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", "Ihor");
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			resp.addCookie(userName);
			String encodeURL = resp.encodeRedirectURL("LoginSuccess.jsp");
			resp.sendRedirect(encodeURL);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = resp.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(req, resp);
		}
	}
}