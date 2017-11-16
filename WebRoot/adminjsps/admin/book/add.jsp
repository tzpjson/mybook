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
    
    <title>My JSP 'add.jsp' starting page</title>
    
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
    <h1>添加图书</h1>
    <p style="font-weight: 900px; color: red;">${msg }</p>
    <form action="<c:url value='/admin/AdminAddBookServlet'/>" method="post" enctype="multipart/form-data">
    	图书名称：<input type="text" name="bname" /><br/>
    	图书图片：<input type="file" name="image" /><br/>
    	图书单价：<input type="text" name="price" /><br/>
    	图书作者：<input type="text" name="author" /><br/>
    	图书分类：<select name="cid">
    				<c:forEach items="${categoryList}" var="c">
    					<option value="${c.cid}">${c.cname}</option>
    				</c:forEach>
    		   </select><br/>
    		   <input type="submit" value="上传"/>
    </form>
  </body>
</html>
