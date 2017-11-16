package cn.tzp.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.cart.domain.Cart;
import cn.tzp.bookstore.cart.domain.CartItem;
import cn.tzp.bookstore.order.domain.Order;
import cn.tzp.bookstore.order.domain.OrderItem;
import cn.tzp.bookstore.order.service.OrderException;
import cn.tzp.bookstore.order.service.OrderService;
import cn.tzp.bookstore.user.domain.User;

/**
 * web层
 * @author ASUS-PC
 *
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			orderService.confirm(request.getParameter("oid"));
			request.setAttribute("msg", "恭喜，交易成功");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 *加载订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}
	
	/**
	 * 生成订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session中获取车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		//创建订单order
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//设置id
		order.setOrderTime(new Date());//设置订单时间
		order.setTotal(cart.getTotal());//设置小计
		order.setState(1);//设置状态
		User user = (User)request.getSession().getAttribute("session_user");
		order.setOwner(user);//设置用户
		
		//创建订单条目集合
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cart.getCartItems()) {
			//创建新的条目
			OrderItem oi = new OrderItem();
			
			oi.setIid(CommonUtils.uuid());//设置id
			oi.setCount(cartItem.getCount());//设置数量
			oi.setSubtotal(cartItem.getSubtotal());//设置小计
			oi.setOrder(order);//设置所属订单
			oi.setBook(cartItem.getBook());
			
			//将条目添加到订单集合中
			orderItemList.add(oi);
		}
		
		//把集合添加到订单中
		order.setOrderItemList(orderItemList);
		
		//清空购物车
		cart.clear();
		
		//向数据库添加订单
		orderService.add(order);
		
		//保存到ruquest中
		request.setAttribute("order", order);
		
		return "f:/jsps/order/desc.jsp";
	}
	
	/**
	 * 显示当前用户订单列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户
		User user = (User)request.getSession().getAttribute("session_user");
		//获取用户uid
		String uid = user.getUid();
		//调用service方法，得到order集合
		List<Order> orderList = orderService.myOrders(uid);
		//保存到request中
		request.setAttribute("orderList", orderList);
		
		return "f:/jsps/order/list.jsp";
	}
}
