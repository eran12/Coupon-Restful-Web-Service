package com.ee.UserService;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserSessionValidte
 */
@WebFilter("/UserSessionValidte")
public class UserSessionValidte extends UserLogin implements Filter {

	private static final String redirectLogin = "http://localhost:8080/CouponSystemWeb/index.html";

	/**
	 * Default constructor. 
	 */
	public UserSessionValidte() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			System.out.println("UserSessionValidte.doFilter()");
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			StringBuffer url = servletRequest.getRequestURL() ;
			System.out.println(url.toString());
			HttpSession session =  servletRequest.getSession(false);
			if(session.getAttribute("facade") == null){
				System.out.println("UserSessionValidte.doFilter() new session");
				servletResponse.sendRedirect(redirectLogin);
			}
			else{
				System.out.println("UserSessionValidte.doFilter() old session");
				chain.doFilter(servletRequest, servletResponse);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
