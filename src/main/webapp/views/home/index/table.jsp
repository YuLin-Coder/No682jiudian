<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
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
					<input type="button" class="disable" value="满房" style="color: red;">
				</c:if> <c:if test="${roomType.status == 1 }">
					<input type="button" value="预订"
						onclick="window.location.href='account/book_order?roomTypeId=${roomType.id }'">
				</c:if></td>
		</tr>
	</c:forEach>
	<tr>
		<!-- 	<td colspan="1"></td> -->
		<td colspan="7" align="center"><ul class="pagination">
				<li><a href="#" id="one">首页</a></li>
				<li><a href="#">${pageUtil.nextPage}</a></li>
				<li><a href="#">${pageUtil.nextPage+1}</a></li>
				<li><a href="#">${pageUtil.nextPage+2}</a></li>
				<li><a href="#">${pageUtil.nextPage+3}</a></li>
				<li><a href="#">${pageUtil.nextPage+4}</a></li>
				<li><a href="#" id="wei">尾页</a></li>
				<li><a href="#">当前页为：${pageUtil.currentPage+1}</a></li>
			</ul></td>
		<!-- <td colspan="2"></td> -->
	</tr>
</tbody>