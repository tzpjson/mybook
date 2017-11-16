package cn.tzp.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.book.domain.Book;
import cn.tzp.bookstore.book.service.BookService;
import cn.tzp.bookstore.cart.domain.Cart;
import cn.tzp.bookstore.cart.domain.CartItem;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 添加购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		//获取书的id
		String bid = request.getParameter("bid");
		//创建书
		Book book = new BookService().load(bid);
		//获取书的数量
		int count = Integer.parseInt(request.getParameter("count"));
		//创建条目对象
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		
		//把条目添加到车中
		cart.add(cartItem);
		
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart car = (Cart)request.getSession().getAttribute("cart");
		car.clear();
		
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 删除购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		
		return "f:/jsps/cart/list.jsp";
	}
}


