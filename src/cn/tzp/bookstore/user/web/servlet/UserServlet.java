package cn.tzp.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;
import cn.tzp.bookstore.cart.domain.Cart;
import cn.tzp.bookstore.user.domain.User;
import cn.tzp.bookstore.user.service.UserException;
import cn.tzp.bookstore.user.service.UserService;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	/**
	 * 退出功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		request.getSession().invalidate();
		return "f:/index.jsp";
	}
	
	/**
	 * 登陆功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//封装表单数据
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		//校验表单数据---略
		
		//登陆
		try {
			User user = userService.login(form);
			//保存信息
			request.getSession().setAttribute("session_user", user);
			
			//发车啦
			request.getSession().setAttribute("cart", new Cart());
			return "r:/index.jsp";
		} catch (UserException e) {
			//返回错误信息
			request.setAttribute("msg", e.getMessage());
			//保存form
			request.setAttribute("form", form);
			//打回
			return "f:/jsps/user/login.jsp";
		}
	}
	
	/**
	 * 激活功能
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取验证码
		String code = request.getParameter("code");
		
		//激活
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜，激活成功");
		} catch (UserException e) {
			//保存错误信息
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 注册功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//封装数据
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		
		//表单数据校验
		Map<String,String> errors = new HashMap<String,String>();
		
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		} else if(username.length()<3 || username.length()>10) {
			errors.put("username", "用户名必须在3~10位之间");
		}
		
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		} else if(password.length()<3 || password.length()>10) {
			errors.put("password", "密码必须在3~10位之间");
		}
		
		String email = form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空");
		} else if(!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "Email格式错误");
		}
		
		if(errors.size()>0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		//调用注册功能
		try {
			userService.regist(form);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		//邮箱激活
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
		
		String host = props.getProperty("host");
		String emailname = props.getProperty("emailname");
		String pwd = props.getProperty("password");
		String from = props.getProperty("from");
		String to = form.getEmail();
		String subject = props.getProperty("subject");
		String content = props.getProperty("content");
		content = MessageFormat.format(content, form.getCode());
		
		Session session = MailUtils.createSession(host, emailname, pwd);
		Mail mail = new Mail(from, to, subject, content);
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("msg", "恭喜注册成功，请到邮箱激活");
		return "f:/jsps/msg.jsp";
	}
}
