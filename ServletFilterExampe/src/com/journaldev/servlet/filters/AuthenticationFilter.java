package com.journaldev.servlet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
	private ServletContext context;
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Authentication initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resourse::" + uri);
		
		HttpSession session = req.getSession(false);
		
		if (session == null && !(uri.endsWith("html") || uri.endsWith("LoginServlet"))) {
			this.context.log("Unauthorized access request");
			resp.sendRedirect("login.html");
		}
		else chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}
}
