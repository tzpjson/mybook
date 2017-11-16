<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base>
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		a{
			text-decoration: none;
		}
	</style>

  </head>
  
  <body>
    <center><h1>CCUT书店</h1></center>
    <br/>
    <div style="line-height:10px">
    	<c:choose>
    		<c:when test="${empty sessionScope.session_user}">
    			<a href="<c:url value='/jsps/user/login.jsp'/>" target="_blank">登陆</a>
    			<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_blank">注册</a>
    			
    		</c:when>
    		<c:otherwise>
    			你好，${sessionScope.session_user.username}
    			<a href="<c:url value='/jsps/cart/list.jsp'/>" target="view_frame">我的购物车</a>
    			<a href="<c:url value='/OrderServlet?method=myOrders'/>" target="view_frame">我的订单</a>
    			<a href="<c:url value='/UserServlet?method=quit'/>" target="_blank">退出</a>
    		</c:otherwise>
    	</c:choose>
    </div>
  </body>
</html>
