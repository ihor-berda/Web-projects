package com.journaldev.servlet.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestLoggingFilter implements Filter {
	private ServletContext servletContext;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.servletContext = fConfig.getServletContext();
		this.servletContext.log("RequestLoggingFilter initialized");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Enumeration<String> params = req.getParameterNames();
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = request.getParameter("name");
			this.servletContext.log
			(req.getRemoteAddr() + "::Request Params:: { " + name + "=" + value + "}");
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				this.servletContext.log
				(req.getRemoteAddr() + "::Cookie:{" + cookie.getName() + ", " + cookie.getValue() + "}");
				
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}