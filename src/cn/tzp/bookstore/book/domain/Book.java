package cn.tzp.bookstore.book.domain;

import cn.tzp.bookstore.category.domain.Category;

public class Book {
	private String bid;
	private String bname;
	private double price;
	private String author;
	private String image;
	private Category catefory;
	private boolean del;

	public boolean isDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String bid, String bname, double price, String author,
			String image, Category catefory) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.price = price;
		this.author = author;
		this.image = image;
		this.catefory = catefory;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCatefory() {
		return catefory;
	}

	public void setCatefory(Category catefory) {
		this.catefory = catefory;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price
				+ ", author=" + author + ", image=" + image + ", catefory="
				+ catefory + "]";
	}

}
