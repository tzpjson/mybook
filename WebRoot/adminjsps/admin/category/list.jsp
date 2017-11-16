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
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1px" cellspacing="0" cellpadding="0" width="500px">
    	<tr bordercolor="rgb(78,78,78)">
    		<th>分类名称</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${categoryList}" var="c">
    	<tr bordercolor="rgb(78,78,78)" align="center">
    		<td>${c.cname }</td>
    		<td>
    			<a target="frame" href="<c:url value='/admin/AdminCategoryServlet?method=editPer&cid=${c.cid }'/>">修改</a>
    			<a onclick="return confirm('要删除？')" target="frame" href="<c:url value='/admin/AdminCategoryServlet?method=delete&cid=${c.cid }'/>">删除</a>
    		</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
