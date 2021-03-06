package cn.tzp.bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.tzp.bookstore.user.domain.User;

/**
 * Servlet Filter implementation class AdminLoginServlet
 */
public class AdminLoginServlet implements Filter {

	public void destroy() {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest)request;
		User user = (User)httprequest.getSession().getAttribute("admin_user");
		
		if(user != null) {
			chain.doFilter(request, response);
		} else {
			httprequest.getRequestDispatcher("/adminjsps/login.jsp").forward(httprequest, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {}

}
