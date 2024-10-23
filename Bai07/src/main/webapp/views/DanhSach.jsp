<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}
</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/Bai07Servlet">Danh sách
		tin tức</a>
	<a href="${pageContext.request.contextPath}/Bai07Servlet?action=add">Thêm
		tin tức</a>
	<a href="${pageContext.request.contextPath}/Bai07Servlet?action=manage">Chức
		năng quản lý</a>
		<a href="${pageContext.request.contextPath}/Bai07Servlet?action=search">Chức
		năng tìm kiếm</a>
	<table>
		<thead>
			<tr>
				<th>Mã danh mục</th>
				<th>Tên danh mục</th>
				<th>Người quản lý</th>
				<th>Ghi chú</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${danhMucs}" var="danhmuc">
				<tr>
					<td>${danhmuc.maDanhMuc}</td>
					<td>${danhmuc.tenDanhMuc}<p>Danh sách tin tức:</p> <c:choose>
							<c:when test="${not empty tinTucs[danhmuc.maDanhMuc]}">
								<ul>
									<c:forEach items="${tinTucs[danhmuc.maDanhMuc]}" var="tintuc">
										<li>${tintuc.maTinTuc}
											<p>${tintuc.tieuDe}</p>
											<p>${tintuc.noiDung}</p>
											<p>${tintuc.lienKet}</p>
										</li>
									</c:forEach>
								</ul>
							</c:when>

						</c:choose></td>

					<td>${danhmuc.nguoiQuanLy}</td>
					<td>${danhmuc.ghiChu}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>