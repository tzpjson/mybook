package cn.tzp.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.tzp.bookstore.book.domain.Book;
import cn.tzp.bookstore.order.domain.Order;
import cn.tzp.bookstore.order.domain.OrderItem;

/**
 * 持久层
 * @author ASUS-PC
 *
 */
public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Order order) {
		try {
			String sql = "insert into orders values(?,?,?,?,?,?)";
			Timestamp timestamp = new Timestamp(order.getOrderTime().getTime());
			
			qr.update(sql, order.getOid(),timestamp,order.getTotal(),order.getState(),order.getOwner().getUid(),order.getAddress());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 批量添加订单条目
	 * @param orderItemlist
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		try {
			String sql = "insert into orderitem values(?,?,?,?,?)";
			Object[][] params = new Object[orderItemList.size()][];
			
			for(int i=0; i<orderItemList.size(); i++) {
				OrderItem orderItem = orderItemList.get(i);
				params[i] = new Object[]{orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getOrder().getOid(),orderItem.getBook().getBid()};
				
			}
			
			qr.batch(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		try {
			//得到所有order
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
			
			//为每个order加载信息
			for(Order order : orderList ) {
				loadOrderItems(order);
			}
			
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 加载订单条目
	 * @param order
	 * @throws SQLException 
	 */
	private void loadOrderItems(Order order) throws SQLException {
		//查询两张表
		String sql = "SELECT * FROM orderitem AS i JOIN book AS b ON oid=? AND i.bid=b.bid";
		
		//将数据保存到map中
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
		
		//将map变成orderitem
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		
		//设置
		order.setOrderItemList(orderItemList);
	}
	
	/**
	 * 将map转换成两个对象,对一堆进行操作
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for(Map<String,Object> map : mapList) {
			OrderItem orderItem = toOrderItem(map);
			orderItemList.add(orderItem);
		}
		
		return orderItemList;
	}
	
	/**
	 * 对每一行进行操作
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}
	
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try {
			//得到所有order
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			
			//为每个order加载信息
			loadOrderItems(order);
			
			return order;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 回去订单状态
	 * @param oid
	 * @return
	 */
	public int getStateByOid(String oid) {
		try {
			String sql = "select state from orders where oid = ?";
			return qr.query(sql, new ScalarHandler<Integer>(),oid);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 修改订单状态
	 * @param oid
	 * @param state
	 */
	public void updateState(String oid, int state) {
		try {
			String sql = "update orders set state = ? where oid=?";
			qr.update(sql, state, oid);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
}



















