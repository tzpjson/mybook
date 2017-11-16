package cn.tzp.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.tzp.bookstore.order.dao.OrderDao;
import cn.tzp.bookstore.order.domain.Order;

/**
 * 业务逻辑层
 * @author ASUS-PC
 *
 */
public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void confirm(String oid) throws OrderException {
		//检查订单状态
		int state = orderDao.getStateByOid(oid);
		//检查
		if(state != 3) throw new OrderException("确认收货失败，你是坏人！");
		
		//修改订单
		orderDao.updateState(oid, 4);
	}
	
	/**
	 * 添加订单
	 * @param order
	 */
	public void add(Order order) {
		try {
			//开启事物
			JdbcUtils.beginTransaction();
			
			//插入订单
			orderDao.addOrder(order);
			//插入订单中的条目
			orderDao.addOrderItemList(order.getOrderItemList());
			
			//提交事务
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				//回滚事务
				JdbcUtils.rollbackTransaction();
			} catch (SQLException ee) {
				throw new RuntimeException(ee);
			}
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	public Order load(String oid) {
		return orderDao.load(oid);
	}
}









