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
	
	<script type="text/javascript">
		function setMethod(method) {
			var ele = document.getElementById("method");
			ele.value = method;
		}
	</script>

  </head>
  
  <body>
    <div>
    <form action="<c:url value='/admin/AdminBookServlet'/>" method="post">
    	<input type="hidden" name="method" id="method"/>
    	<input type="hidden" name="bid" value="${book.bid }" />
    	<input type="hidden" name="image" value="${book.image }" />
    	<img alt="" src="<c:url value='${book.image}'/>"><br/>
    	图书名称：<input type="text" name="bname" value="${book.bname}"/><br/>
    	图书单价：<input type="text" name="price" value="${book.price}"/>元<br/>
    	图书作者：<input type="text" name="author" value="${book.author}"/><br/>
    	图书分类：<select style="width: 150px; height: 20px;" name="cid">
    				<c:forEach items="${categoryList}" var="category">
    					<option value="${category.cid}" <c:if test="${category.cid eq book.catefory.cid}">selected="selected"</c:if> >${category.cname}</option>
    				</c:forEach>
    		   </select><br/>
    		   <input type="submit" value="删除" onclick="setMethod('delete')"/>
    		   <input type="submit" value="编辑" onclick="setMethod('edit')"/>
    		   
    </form>
    </div>
  </body>
</html>
