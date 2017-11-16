package cn.tzp.bookstore.book.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.book.domain.Book;
import cn.tzp.bookstore.book.service.BookService;
import cn.tzp.bookstore.category.domain.Category;
import cn.tzp.bookstore.category.service.CategoryService;

public class AdminBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	/**
	 * 加载图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
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
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		bookService.delete(request.getParameter("bid"));
		return findAll(request,response);
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCatefory(category);
		bookService.edit(book);
		return findAll(request,response);
	}
}
