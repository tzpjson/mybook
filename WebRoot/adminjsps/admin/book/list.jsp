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
	<style type="text/css">
		div{
			padding:20px;
			width: 180px; 
			height:200px;
			float:left;
			text-align: center;
		}
		div a{
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>
    <c:forEach items="${bookList}" var="book">
    	<div>
    		<a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid}'/>"><img alt="" src="<c:url value='${book.image}'/>"><br/>${book.bname}</a>
    	</div>
    </c:forEach>
  </body>
</html>
