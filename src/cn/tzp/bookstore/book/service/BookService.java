package cn.tzp.bookstore.book.service;

import java.util.List;

import cn.tzp.bookstore.book.dao.BookDao;
import cn.tzp.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	
	/**
	 * 加载图书信息
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		return bookDao.load(bid);
	}

	public void add(Book book) {
		bookDao.add(book);
	}
	
	public void delete(String bid) {
		bookDao.delete(bid);
	}

	public void edit(Book book) {
		bookDao.edit(book);
	}
}
