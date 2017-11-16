<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <c:choose>
  	<c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0}">购物车空空如也</c:when>
  	<c:otherwise>
  	
    <table border="1px" cellspacing="0px" width="670px">
    	<tr>
    		<td colspan="7" align="right"><a href="<c:url value='/CartServlet?method=clear'/>">清空购物车</a></td>
    	</tr>
    	
    	<tr>
    		<th width="160px">图片</th>
    		<th width="120px">书名</th>
    		<th width="70px">作者</th>
    		<th width="70px">单价</th>
    		<th width="70px">数量</th>
    		<th width="70px">小计</th>
    		<th>操作</th>
    	</tr>
    	
    	<c:forEach items="${sessionScope.cart.cartItems }" var="car">
    		<tr>
    			<td>
    				<div style="width: 120px">
    				<img alt="" src="<c:url value='${car.book.image }'/>">
    				</div>
    			</td>
    			<td>${car.book.bname }</td>
    			<td>${car.book.author }</td>
    			<td>${car.book.price }元</td>
    			<td>${car.count }</td>
    			<td>${car.subtotal }</td>
    			<td><a href="<c:url value='/CartServlet?method=delete&bid=${car.book.bid}'/>">删除</a></td>
    		</tr>
    	</c:forEach>
    	
    	<tr>
    		<td colspan="7" align="right">合计：${sessionScope.cart.total}元</td>
    	</tr>
    	<tr>
    		<td colspan="7" align="right"><a href="<c:url value='/OrderServlet?method=add'/>">购买</a></td>
    	</tr>
    </table>
    </c:otherwise>
  </c:choose>
  </body>
</html>
