<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>琉璃汀酒店管理系统首页</title>
	<style>

		#gh{
			background:url("../resources/home/images/shouye1.png");
		}
		.ab{
			border-radius:15px  0 0 15px;
			bordre:2px solid rgb(254,184,134);
		}

		.af{
			border-radius:0 15px 15px 0;
			width:35px;
		}
	</style>
<link rel="stylesheet" href="../resources/home/css/bootstrap.min.css" />
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	
<script src="../resources/home/js/jquery-1.9.1.min.js"></script>
<script src="../resources/home/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="row">
				<ul class="nav nav-tabs">
					<!--<li class="active"><a href="#">首页</a></li>-->
					<!--<li><a href="#">简介</a></li>
					<li class="disabled"><a href="#">信息</a></li>-->
					<li><form action="ll" method="post">
						<input type="text" name="as" placeholder="单人间" style="color: gray"  class="ab"><button type="submit" class="af"><span class="fa fa-search"></span></button>
					</form></li>

					<li class="hello pull-right"><a href="index2">首页</a></li>
					<li style="position: relative;left:600px;"><a href="../system/login">管理员登录</a></li>
					<c:if test="${account == null }">
						<li class="hello pull-right"><a href="login2">登录/注册</a></li>
					</c:if>

					<c:if test="${account != null }">

						<li class="dropdown pull-right"><a href="#"
							data-toggle="dropdown" class="dropdown-toggle">下拉<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<!--<li><a href="../system/login" target="_blank">管理员登陆</a></li>-->
								<li><a href="account/index">个人中心</a></li>
								<li class="divider"></li>
								<li><a href="../home/logout">退出</a></li>
							</ul></li>
						<li class="hello pull-right"><a href="account/index">欢迎您：${account.name }</a></li>
					</c:if>

				</ul>
				<div class="carousel slide" id="carousel-415428"
					style="padding-top: 10px;">
					<ol class="carousel-indicators">
						<li data-slide-to="0" data-target="#carousel-415428"></li>
						<li data-slide-to="1" data-target="#carousel-415428"></li>
						<li data-slide-to="2" data-target="#carousel-415428"
							class="active"></li>
					</ol>
					<div class="carousel-inner">
						<div class="item">
							<img alt="" src="../resources/home/images/shouye4.png"
								style="width: 100%; height: 365px" />
								<div class="carousel-caption">
								<h4>hotel management system</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item">
							<img alt="" src="../resources/home/images/shouye3.png"
								style="width: 100%; height: 365px" />
								<div class="carousel-caption">
								<h4>hotel management system</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item active">
							<img alt="" src="../resources/home/images/shouye2.png"
								style="width: 100%; height: 365px" />
								<div class="carousel-caption">
								<h4>hotel management system</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget
									quam. Donec id elit non mi porta gravida at eget metus. Nullam
									id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-415428"
						data-slide="prev"><span
						class="glyphicon-chevron-left">《</span></a> <a
						class="right carousel-control" href="#carousel-415428"
						data-slide="next"><span
						class="glyphicon-chevron-right">》</span></a>
				</div>
				</div>
				<!--<div class="row" id="gh">
					<div class="col-md-9"></div>
					<div class="col=md-3">
						<form action="ll" method="post">
							<input type="text" name="as" class="ab"><button type="submit" class="af"><span class="fa fa-search"></span></button>
						</form>
					</div>
				</div>-->
				<div class="row ">
				<table class="table" id="table">
					<thead>
						<tr>
							<th width="200px">客房</th>
							<th>房型</th>
							<th>可住人数</th>
							<th>床位数</th>
							<th>房价</th>
							<th>房态</th>
							<th>预订</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roomTypeList }" var="roomType">
							<tr>
								<td><a href="#"><img src="../${roomType.photo }" alt=""
										width="200px"></a></td>
								<td align="center" style="vertical-align: middle;">
									<p>${roomType.name }</p>
									<p class="sub_txt">${roomType.remark }</p>
								</td>
								<td style="vertical-align: middle;">${roomType.liveNum }</td>
								<td style="vertical-align: middle;">${roomType.bedNum }</td>
								<td style="color: red; vertical-align: middle;">${roomType.price }<a>￥</a></td>
								<td style="vertical-align: middle;"><c:if
										test="${roomType.status == 0 }">
          							已满房
          						</c:if> <c:if test="${roomType.status == 1 }">
          							可预订
          						</c:if></td>
								<td style="vertical-align: middle;"><c:if
										test="${roomType.status == 0 }">
										<input type="button" class="disable" value="满房"
											style="color: red;">
									</c:if> <c:if test="${roomType.status == 1 }">
										<input type="button" value="预订"
											onclick="window.location.href='account/book_order?roomTypeId=${roomType.id }'">
									</c:if></td>
							</tr>
						</c:forEach>
						<tr>
							<!-- 	<td colspan="1"></td> -->
							<td colspan="7" align="center"><ul class="pagination">
									<li><a href="index2?offset=0" id="firstPage">首页</a></li>
									<li><a href="index2?offset=0" id="two">1</a></li>
									<!--<li><a href="index2?offset=1" id="three">2</a></li>		-->
									<c:if test="${pageUtil.totalPage>2}">
									<!--<li><a href="javascript:void(0);" id="three">。。。</a></li>-->
									<li><a href="index2?offset=${pageUtil.totalPage-2}" id="four">${pageUtil.totalPage-1}</a></li>
									<li><a href="index2?offset=${pageUtil.totalPage-1}" id="five">${pageUtil.totalPage}</a></li>
										<li><a href="index2?offset=${pageUtil.totalPage}"
											   id="totalPage">尾页</a></li>
									</c:if>
									<!--<li><a href="index2?offset=${pageUtil.totalPage}"
										id="totalPage">尾页</a></li>-->
									<li><a href="javascript:void(0);">当前页为：${pageUtil.currentPage+1}</a></li>

								</ul></td>
							<!-- <td colspan="2"></td> -->
						</tr>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>