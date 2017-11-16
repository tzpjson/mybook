package cn.tzp.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.category.domain.Category;
import cn.tzp.bookstore.category.service.CategoryService;

public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService = new CategoryService();
	
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		categoryService.edit(category);
		
		return findAll(request,response);
	}
	
	public String editPer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("catrgory", categoryService.load(cid));
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String cid = request.getParameter("cid");
			try {
				categoryService.delete(cid);
				return findAll(request,response);
			} catch (CateException e) {
				request.setAttribute("msg", e.getMessage());
				return "f:/adminjsps/msg.jsp";
			}
	}
	
	/**
	 * 添加
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request,response);
	}
	
	/**
	 * 查询所有
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
}
