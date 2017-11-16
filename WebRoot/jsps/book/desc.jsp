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
    
    <title>My JSP 'desc.jsp' starting page</title>
    
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
			height: 200px;
			width: 200px;
		}
	</style>

  </head>
  
  <body>
    <div>
    	<img alt="" src="<c:url value='${book.image}'/>"><br/>
    	<ul>
    		<li>书名：${book.bname}</li>
    		<li>作者：${book.author}</li>
    		<li>单价：${book.price}</li>
    	</ul>
    	<form action="<c:url value='/CartServlet'/>" method="post">
    		<input type="hidden" name="method" value="add"/>
    		<input type="hidden" name="bid" value="${book.bid }"/>
    		<input type="text" name="count" value="1"/>
    		<input type="submit" value="提交购物车"/>
    	</form>
    </div>
  </body>
</html>
