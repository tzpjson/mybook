<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'desc.jsp' starting page</title>
    
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
    <table border="1px" width="100%" cellspacing="0" background="black">
    	<tr bgcolor="gray" bordercolor="black">
    		<td colspan="6">
    			订单号：${order.oid}
    			交易时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.orderTime}"/>
    			金额：<font color="red">${order.total}</font>
    		</td>
    	</tr>	
    	
    	<c:forEach items="${order.orderItemList}" var="list">
    	<tr align="center" bordercolor="black">
    		<td width="15%">
    			<div><img src="<c:url value='${list.book.image}'/>"/></div>
    		</td>
    		<td>书名：${list.book.bname}</td>
    		<td>单价：${list.book.price}</td>
    		<td>作者：${list.book.author}</td>
    		<td>数量：${list.count}</td>
    		<td>小计：${list.subtotal}</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
