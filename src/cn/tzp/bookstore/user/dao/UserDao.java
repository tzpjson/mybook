package cn.tzp.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.tzp.bookstore.user.domain.User;

/**
 * User持久层
 * @author ASUS-PC
 *
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 * 按用户名进行查找
	 * @param username
	 * @return
	 */
	public User fingByUsername(String username) {
		try {
			String sql = "select * from b_user where username=?";
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 按Email查找
	 * @param email
	 * @return
	 */
	public User findByEmai(String email) {
		try {
			String sql = "select * from b_user where email=?";
			return qr.query(sql, new BeanHandler<User>(User.class),email);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 按激活码查找
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		try {
			String sql = "select * from b_user where code=?";
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 更改激活状态
	 * @param uid
	 * @param flag
	 */
	public void updateState(String uid,boolean flag) {
		try {
			String sql = "update b_user set state=? where uid=?";
			qr.update(sql, flag, uid);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user) {
		try {
			String sql = "insert into b_user values(?,?,?,?,?,?)";
			Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getCode(),user.isState()};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
