<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>Mã tin tức</th>
				<th>Tiêu đề</th>
				<th>Nội dung</th>
				<th>Liên kết</th>
				<th>Quản lý</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${danhMucs}" var="danhmuc">
				<c:forEach items="${tinTucs[danhmuc.maDanhMuc]}" var="tintuc">
					<tr>
						<td>${tintuc.maTinTuc}</td>
						<td>${tintuc.tieuDe}</td>
						<td>${tintuc.noiDung}</td>
						<td>${tintuc.lienKet}</td>
						<td><a
							href="${pageContext.request.contextPath}/Bai07Servlet?action=deleteTT&maTT=${tintuc.maTinTuc}">Xóa</a></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	<div
		style="display: flex; align-content: center; justify-content: center;">
		<p>Đặng Bảo Thông - 21014091</p>
	</div>
</body>
</html>
