package cn.tzp.bookstore.category.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.category.service.CategoryService;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = new CategoryService();
	
	/**
	 * 查找所有
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/jsps/left.jsp";
	}
	
}
