package cn.tzp.bookstore.category.service;

import java.util.List;

import cn.tzp.bookstore.book.dao.BookDao;
import cn.tzp.bookstore.category.dao.CategoryDao;
import cn.tzp.bookstore.category.domain.Category;
import cn.tzp.bookstore.category.web.servlet.admin.CateException;

/**
 * 业务逻辑层
 * @author ASUS-PC
 *
 */
public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public void add(Category category) {
		categoryDao.add(category);
	}

	public void delete(String cid) throws CateException {
		int count = bookDao.findByCid(cid);
		
		if(count > 0) throw new CateException("该分类下有图书，不能删除！");
		categoryDao.delete(cid);
	}

	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	public void edit(Category category) {
		categoryDao.edit(category);
	}
	
}	
