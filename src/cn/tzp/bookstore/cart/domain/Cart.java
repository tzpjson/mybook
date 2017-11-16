package cn.tzp.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	
	/**
	 * 求和
	 * @return
	 */
	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		for(CartItem car : map.values()) {
			BigDecimal b2 = new BigDecimal(car.getSubtotal()+"");
			total =total.add(b2);
		}
		return total.doubleValue();
	}
	
	/**
	 * 添加功能
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		if(map.containsKey(cartItem.getBook().getBid())) {
			//返回原条目
			CartItem cart = map.get(cartItem.getBook().getBid());
			//设置新的条目
			cart.setCount(cart.getCount()+cartItem.getCount());
			//回写
			map.put(cartItem.getBook().getBid(), cart);
		} else {
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	/**
	 * 清空所有
	 */
	public void clear() {
		map.clear();
	}
	
	/**
	 * 删除指定
	 * @param bid
	 */
	public void delete(String bid) {
		map.remove(bid);
	}
	
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
