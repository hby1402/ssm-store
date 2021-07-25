<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购物商城-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="fkjava.ico" rel="shortcut icon" type="image/x-icon" />
<!-- main.css是购物商城主样式 -->
<link rel=stylesheet type="text/css" href="css/main.css" />
<!-- <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script> -->
<!-- 使用jQuery-UI的样式来设置tab页
		<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.20.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="js/jquery-ui.js"></script>
		<script type="text/javascript" src="js/pager.js"></script>
		 -->
<!-- header.js输出头部信息 -->
<script type="text/javascript" src="js/header.js"></script>
<script type="text/javascript">

	if (window.location != parent.window.location) {
		parent.window.location = window.location;
	}
	window.onload = function() {
		/** tabs标签页 */
		$('#tabs').tabs();

		/** 分页标签 */
		/* 	fkjava.pager("pager", { pageIndex : "1",
					pageSize : "8",
					pageCount : "32",
					submitUrl : '/fk_ec/index.action?pageIndex={0}&typecode=0001&keyword='});	 */

		/** 获取所有的li为特定的li绑定事件 */
		var arrays = document.getElementsByTagName("li");
		for (var i = 0; i < arrays.length; i++) {
			if (arrays[i].id != "" && arrays[i].id.indexOf('selbgc1') == 0) {
				arrays[i].onmouseover = function() {
					this.className = "selbgc1";
				};
				arrays[i].onmouseout = function() {
					this.className = "";
				};
			}
		}
		/** 设置选的下拉列表选项 */
		var select = document.getElementById("typecode");
		for (var i = 0; i < select.options.length; i++) {
			var typecode = "${typecode}";
			if (select.options[i].value == typecode) {
				select.options[i].selected = true;
			}
		}

	};

	//下拉框中数据发生改变时触发该函数
	function reloadIndex(val) {
		var keyword = $("#keyword").val();

		window.location = "${pageContext.request.contextPath}/index?typecode="
				+ val + "&keyword=" + keyword;
	}
</script>
<!-- bootstrap框架 -->
<!-- <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<script src="css/bootstrap/jquery-3.6.0.min.js"></script>
<script src="css/bootstrap/bootstrap.min.js"></script> -->
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- header部分 -->
	<div id="shortcut">
		<script type="text/javascript">
			header("${session_user.name}");
		</script>
		﻿
		<div class="nav">
			<div class="w960 center">
				<ul>
					<li><a title="首页" href="index">首页</a></li>
					<c:forEach items="${firstArticleTypes}" var="firstArticleType">
						<li><a title="${firstArticleType.name}"
							href="${ctx}/index?typecode=${firstArticleType.code}">${firstArticleType.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
<%-- 	<nav class="nav navbar navbar-inverse" role="navigation">
		<div>
			<ul class="nav navbar-nav">
				<li><a title="首页" href="index">首页</a></li>
				<c:forEach items="${firstArticleTypes}" var="firstArticleType">
				<li><a title="${firstArticleType.name}"
					href="${ctx}/index?typecode=${firstArticleType.code}">${firstArticleType.name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</nav> --%>		
	<!--header end-->
	<!-- middle part -->
	<div style="positon: relative; width: 1300px; margin: 0px auto;">
		<!-- 左边物品类型列表 -->
		<div id="booksort" style="float: left; width: 190px;">
			<!-- <div class="mt" style="height: 50px; font-size: 14px;">
				<h3><strong>物品分类</strong></h3>
			</div> -->
			<div class="btn-primary btn-lg" style="text-shadow: black 5px 3px 3px;font-size: 25px;">
			物品分类 <span class="glyphicon glyphicon-search"></span>
			</div>    
			<div class="mc">
				<c:forEach items="${firstArticleTypes}" var="firstArticleType">
				<div class="dropdown" align="center" >
					<div class="btn btn-link btn-lg" style="width:100px">
						<a href="${ctx}/index?typecode=${firstArticleType.code}">${firstArticleType.name}</a>
					</div>
					<button type="button" class="btn btn-default dropdown-toggle" id="dropdownMenu1"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
						<c:forEach items="${firstArticleType.secondArticleType}" var="secondArticleType">
					        <li role="presentation" class="btn-lg"><a role="menuitem" href="${ctx}/index?typecode=${secondArticleType.code}">${secondArticleType.remark}</a></li>
						</c:forEach>	
					</ul>
<%-- 					<div class="btn dropdown-toggle">
							<b>&gt;</b><a href="/index?typecode=${firstArticleType.code}">·${firstArticleType.name}</a>
						</div> --%>
				</div>
				</c:forEach>
			</div>
		</div>
		<!-- 右边对应物品列表 float: left;  -->
		<div style="width: 1300px; text-align: center;">
			<div>
				<form action="index" method="post" name="search">
					<%-- 物品类型： <select name="typecode" id="typecode"
						onchange="reloadIndex(this.value)">
						<option value="${firstArticleType.code}">${firstArticleType.name}</option>
						<c:forEach items="${allSecondArticleTypes}"
							var="secondArticleType">
							<option value="${secondArticleType.code}">----${secondArticleType.name}</option>
						</c:forEach>
					</select>  --%>
					<input name="keyword" id="keyword" type="text" value="${keyword}"
						size="50" />
					<button type="submit">搜索</button>
				</form>
			</div>
			<!-- 显示所有商品 -->
			<div id="tabs" style="Width: 1300px; background-color: white;">
<%-- 				<ul>
					<li><a href="tabs-1">${firstArticleType.name }</a></li>
				</ul>
				<div class="sales-queue" id="tabs-1"
					style="background-color: white; margin-top: -25px;">
					<ul class="goods-queue3">
						<c:forEach items="${articles}" var="article">
							<li id="selbgc11">
								<dl class="item-des">
									<dt>
										<a href="item?id=${article.id}" title="${article.title}"
											target="_self"> <img
											src="images/article/${article.image}" width="132" height="96" /></a>
									</dt>
									<dd>
										<s>¥:<fmt:formatNumber value="${article.price}"
												pattern="0.00"></fmt:formatNumber>
										</s> <strong>¥:<fmt:formatNumber
												value="${article.price* article.discount}" pattern="0.00"></fmt:formatNumber>
										</strong>
									</dd>
									<dd>
										<h2>
											<a href="item?id=${article.id}" title="${article.title}"
												target="_self">${article.title}</a>
										</h2>
									</dd>
								</dl>
							</li>
						</c:forEach>
					</ul> --%>
					<ul>
					<li><a href="tabs-1">${firstArticleType.name }</a></li>
				</ul>
				<div class="row" id="tabs-1">
					<c:forEach items="${articles}" var="article">
						<div class="col-sm-4 col-md-3"  style="width:270px">
							<dl class="item-des">
								<dt>
									<a href="item?id=${article.id}" title="${article.title}"
										target="_self" class="btn btn-default"> 
									<img src="images/article/${article.image}" width="180" height="180"/></a>
								</dt>
								<dd>
									<s>¥:<fmt:formatNumber value="${article.price}"
											pattern="0.00"></fmt:formatNumber>
									</s> 
									<strong>¥:<fmt:formatNumber
											value="${article.price* article.discount}" pattern="0.00"></fmt:formatNumber>
									</strong>
								</dd>
								<dd style="height:110px">
										<a href="item?id=${article.id}" title="${article.title}"
											target="_self">${article.title}</a>
								</dd>
							</dl>
						</div>
					</c:forEach>
					<!-- 分页标签 pagebottom -->
					<%-- 				 <div class="pagination" id="pager" style="clear:both;">
						
						【总共${pageInfo.pages}页,当前第${pageInfo.pageNum}页,总共${pageInfo.total}条记录】
							<a href="${pageContext.request.contextPath}/index?pageNum=1" >首页</a> 
							<a href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.prePage}">上一页</a> 
							<a href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.nextPage}">下一页</a> 
							<a href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.pages}">尾页</a>
						
					</div>  --%>
					<div class="container" id="pager" style="clear:both;width:600px">
						<p class="text-info">【总共${pageInfo.pages}页,当前第${pageInfo.pageNum}页,总共${pageInfo.total}条记录】</p>
						<div class="btn-group btn-group-justified"><!--  style="width: 50%; margin: 0 auto; text-align: center" -->
							<a href="${pageContext.request.contextPath}/index?pageNum=1&&keyword=${keyword}" class="btn btn-primary">首页</a>
							<a href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.prePage}&&keyword=${keyword}" class="btn btn-primary">上一页</a>
							<a href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.nextPage}&&keyword=${keyword}" class="btn btn-primary">下一页</a>
							<a href="${pageContext.request.contextPath}/index?pageNum=${pageInfo.pages}&&keyword=${keyword}" class="btn btn-primary">尾页</a>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
	<!---- middle end----->

	<!--bottom part-->
	<div style="width: 1332px; margin: 0px auto;">
		<img src="${pageContext.request.contextPath}/images/step.jpg" />
	</div>
</body>
</html>