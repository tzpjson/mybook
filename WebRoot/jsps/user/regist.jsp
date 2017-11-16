<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'regist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	div{
		position: absolute;
		left:40%;
	}
	</style>

</head>

<body>

	<div>
		<h1>注册界面</h1>
		<span style="color: red; font-size: 15px;">${msg}</span>
		<form action="<c:url value='/UserServlet'/>" method="post">
			<input type="hidden" name="method" value="regist" /> 用户名：<input
				type="text" name="username" value="${form.username}" /> <span
				style="color: red; font-size: 15px;">${errors.username}</span><br />
			<br /> 密 码：<input type="password" name="password"
				value="${form.password}" /> <span
				style="color: red; font-size: 15px;">${errors.password}</span><br />
			<br /> 邮 箱：<input type="text" name="email" value="${form.email}" />
			<span style="color: red; font-size: 15px;">${errors.email}</span><br />
			<br /> <input type="submit" value="提交" /><br />
		</form>
	</div>
</body>
</html>
