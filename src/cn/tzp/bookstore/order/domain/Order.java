package cn.tzp.bookstore.order.domain;

import java.util.Date;
import java.util.List;

import cn.tzp.bookstore.user.domain.User;

public class Order {
	private String oid;
	private Date orderTime;// 下单时间
	private double total;// 合计
	private int state;// 状态:1.未付款 2.付款未发货 3.发货未确认 4.确认
	private User owner;// 所属用户
	private String address;// 地址

	private List<OrderItem> orderItemList;// 当下所有订单条目

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String oid, Date orderTime, double total, int state,
			User owner, String address, List<OrderItem> orderItemList) {
		super();
		this.oid = oid;
		this.orderTime = orderTime;
		this.total = total;
		this.state = state;
		this.owner = owner;
		this.address = address;
		this.orderItemList = orderItemList;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderTime=" + orderTime + ", total="
				+ total + ", state=" + state + ", owner=" + owner
				+ ", address=" + address + ", orderItemList=" + orderItemList
				+ "]";
	}

}
