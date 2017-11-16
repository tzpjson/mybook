package cn.tzp.bookstore.cart.domain;

import java.math.BigDecimal;

import cn.tzp.bookstore.book.domain.Book;

public class CartItem {
	// 书名
	private Book book;
	// 数量
	private int count;

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	
	/**
	 * 通过bigdecimal
	 * @return
	 */
	public double getSubtotal() {
		BigDecimal b1 = new BigDecimal(book.getPrice() + "");
		BigDecimal b2 = new BigDecimal(count + "");
		return b1.multiply(b2).doubleValue();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
