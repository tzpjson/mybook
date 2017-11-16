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
    
    <title>My JSP 'left.jsp' starting page</title>
    
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
    <div style="text-align: center;"><br/><br/><br/>
    	<a href="<c:url value='/BookServlet?method=findAll'/>" target="view_frame">全部分类</a><br/><br/>
    	
    	<c:forEach items="${categoryList}" var="category">
    		<a href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid}'/>" target="view_frame">${category.cname}</a><br/><br/>
    	</c:forEach>
    </div>
  </body>
</html>
