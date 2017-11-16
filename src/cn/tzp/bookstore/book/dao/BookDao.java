package cn.tzp.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.tzp.bookstore.book.domain.Book;
import cn.tzp.bookstore.category.domain.Category;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 查询所有
	 * @return
	 */
	public List<Book> findAll() {
		try {
			String sql = "select * from book where del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 通过cid进行查找
	 * @param cid
	 * @return
	 */
	public List<Book> findByCategory(String cid) {
		try {
			String sql = "select * from book where cid=? and del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Book load(String bid) {
		try {
			String sql = "select * from book where bid=? and del=false";
			Map<String,Object> map = qr.query(sql, new MapHandler(), bid);
			Book book = CommonUtils.toBean(map, Book.class);
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCatefory(category);
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int findByCid(String cid) {
		try {
			String sql = "select count(*) from book where cid=? and del=false";
			Number num = (Number)qr.query(sql, new ScalarHandler(), cid);
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Book book) {
		try {
			String sql = "insert into book values(?,?,?,?,?,?)";
			Object[] params = {book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCatefory().getCid()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String bid) {
		try {
			String sql = "update book set del=true where bid=?";
			qr.update(sql, bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void edit(Book book) {
		try {
			String sql = "update book set bname=?,price=?,author=?,image=?,cid=? where bid=?";
			Object[] params = {book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCatefory().getCid(),book.getBid()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}





