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
	<link rel="stylesheet" href="css/index.css">
	<style type="text/css">
		a{
		text-decoration: none;
		color: white;
		}
	</style>
	
	<script type="text/javascript">
	window.onload = function() {
		$(".list_dt").on("click",function () {
		$('.list_dd').stop();
		$(this).siblings("dt").removeAttr("id");
		if($(this).attr("id")=="open"){
		$(this).removeAttr("id").siblings("dd").slideUp();
		}else{
		$(this).attr("id","open").next().slideDown().siblings("dd").slideUp();
		}
		});
		};
</script>

</head>
<body style="background: gray;">
<dl class="list_dl">
	<dt class="list_dt">
		<span class="_after"></span>
		<p>分类管理</p>
		<i class="list_dt_icon"></i>
	</dt>
	<dd class="list_dd">
		<ul>
			<li class="list_li"><a href="<c:url value='/admin/AdminCategoryServlet?method=findAll'/>" target="frame">查看分类</a></li>
			<li class="list_li"><a href="<c:url value='/adminjsps/admin/category/add.jsp'/>" target="frame">添加分类</a></li>
		</ul>
	</dd>
	<dt class="list_dt">
		<span class="_after"></span>
		<p>图书管理</p>
		<i class="list_dt_icon"></i>
	</dt>
	<dd class="list_dd">
		<ul>
			<li class="list_li"><a href="<c:url value='/admin/AdminBookServlet?method=findAll'/>" target="frame">查询图书</a></li>
			<li class="list_li"><a href="<c:url value='/admin/AdminBookServlet?method=addPre'/>" target="frame">添加图书</a></li>
		</ul>
	</dd>
	<dt class="list_dt">
		<span class="_after"></span>
		<p>订单管理</p>
		<i class="list_dt_icon"></i>
	</dt>
	<dd class="list_dd">
		<ul>
			<li class="list_li">我就不写了</li>
		</ul>
	</dd>
</dl>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>


  </body>
</html>
