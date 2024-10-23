<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kết quả tìm kiếm</title>
</head>
<body>
	<form
		action="${pageContext.request.contextPath}/Bai07Servlet?action=searchTT"
		method="post">
		<p>
			Tìm bằng mã hoặc tiêu đề: <input type="text" name="maTT">
			<button type="submit">Tìm kiếm</button>
		</p>
	</form>

	<c:choose>
		<c:when test="${found}">
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
					<tr>
						<td>${tinTuc.maTinTuc}</td>
						<td>${tinTuc.tieuDe}</td>
						<td>${tinTuc.noiDung}</td>
						<td>${tinTuc.lienKet}</td>
						<td><a
							href="${pageContext.request.contextPath}/Bai07Servlet?action=deleteTT&maTT=${tinTuc.maTinTuc}">Xóa</a>
						</td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
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
					<c:forEach items="${tinTucs}" var="tinTuc">
						<tr>
							<td>${tinTuc.maTinTuc}</td>
							<td>${tinTuc.tieuDe}</td>
							<td>${tinTuc.noiDung}</td>
							<td>${tinTuc.lienKet}</td>
							<td><a
								href="${pageContext.request.contextPath}/Bai07Servlet?action=deleteTT&maTT=${tinTuc.maTinTuc}">Xóa</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>

	<div
		style="display: flex; align-content: center; justify-content: center;">
		<p>Đặng Bảo Thông - 21014091</p>
	</div>
</body>
</html>
