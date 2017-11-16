package cn.tzp.bookstore.user.web.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.user.domain.User;

public class AdminLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		if(user.getUsername().equals("tzp") && user.getPassword().equals("123")) {
			request.getSession().setAttribute("admin_user", user);
			return "f:/adminjsps/admin/main.jsp";
		} else {
			return "f:/adminjsps/login.jsp";
		}
		
		
	}
	
	
	
}
