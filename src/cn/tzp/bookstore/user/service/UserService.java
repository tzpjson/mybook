package cn.tzp.bookstore.user.service;

import cn.tzp.bookstore.user.dao.UserDao;
import cn.tzp.bookstore.user.domain.User;

/**
 * User业务层
 * @author ASUS-PC
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * 注册功能
	 * @param form
	 * @throws UserException 
	 */
	public void regist(User form) throws UserException {
		User user = new User();
		
		//校验用户名
		user = userDao.fingByUsername(form.getUsername());
		if(user != null) throw new UserException("用户名已经被注册！");
		
		//校验email
		user = userDao.findByEmai(form.getEmail());
		if(user != null) throw new UserException("Email已经被注册！");
		
		//插入用户
		userDao.add(form);
	}
	
	/**
	 * 激活功能
	 * @param code
	 * @throws UserException
	 */
	public void active(String code) throws UserException {
		User user = new User();
		
		//验证是否有激活码
		user = userDao.findByCode(code);
		if(user == null) throw new UserException("无效验证码");
		
		if(user.isState()) throw new UserException("你已经激活了，滚蛋吧");
		
		userDao.updateState(user.getUid(), true);
	}
	
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public User login(User form) throws UserException {
		User user = new User();
		
		user = userDao.fingByUsername(form.getUsername());
		//检查用户名
		if(user == null) throw new UserException("用户名不存在");
		//检查密码
		if(!form.getPassword().equals(user.getPassword())) throw new UserException("密码错误");
		//检查激活状态
		if(!user.isState()) throw new UserException("您的账号未激活");
		
		return user;
	}
}




