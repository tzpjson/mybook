<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <h1>我的订单</h1>
    <table bordercolor="gray" border="1px" cellspacing="0">
    <c:forEach items="${orderList }" var="order">
    	<tr bgcolor="gray" background="gray">
			<td colspan="6">
				订单号：${order.oid }成交时间：${order.orderTime }金额：<font color="red">${order.total }</font>
				<c:choose>
					<c:when test="${order.state eq 1}">
						<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }'/>">付款</a>
					</c:when>
					<c:when test="${order.state eq 2}">
						等待发货
					</c:when>
					<c:when test="${order.state eq 3}">
						<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">确认收货</a>
					</c:when>
					<c:when test="${order.state eq 4}">
						交易成功
					</c:when>
				</c:choose>
			</td>    		
    	</tr>
    	
    	<c:forEach items="${order.orderItemList }" var="orderItem">
    	<tr align="center">
			<td width="15%">
				<div><img src="<c:url value='${orderItem.book.image}'/>"/></div>
			</td>    
			<td>书名：${orderItem.book.bname }</td>
			<td>作者：${orderItem.book.author }</td>
			<td>单价：${orderItem.book.price }</td>
			<td>数量：${orderItem.count }</td>
			<td>小计：${orderItem.subtotal }</td>
    	</tr>
    	</c:forEach>
    </c:forEach>
    </table>
  </body>
</html>
