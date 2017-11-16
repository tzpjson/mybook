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
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="margin: 0px; padding: 0px;">
    <table border="0px" cellspacing="0" align="center" style="width: 600px; background: #; height: 600px;">
    	<tr style=" height: 120px; background-color: ;">
    		<td colspan="2" align="center">
    			<iframe src="<c:url value='/adminjsps/admin/top.jsp'/>" name="top" width="900px" height="115px" frameborder="1"> </iframe>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			<iframe src="<c:url value='/adminjsps/admin/left.jsp'/>" name="left"  height="500px" width="180px" frameborder="1"></iframe>
    		</td>
    		<td>
    			<iframe src="<c:url value='/adminjsps/admin/body.jsp'/>" name="frame" height="500px" width="715px" frameborder="1"></iframe>
    		</td>
    	</tr>
    </table>
  </body>
</html>










