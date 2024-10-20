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
	<div>
		<a
			href="${pageContext.request.contextPath}/Bai06Servlet?action=themGV">Thêm
			Giảng Viên</a> <a
			href="${pageContext.request.contextPath}/Bai06Servlet?action=themDeTai">Thêm
			Đề Tài</a>
		<table>
			<thead>
				<tr>
					<th>Faculty ID</th>
					<th>Full Name</th>
					<th>Research Area</th>
					<th>Telephone Number</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${giangViens}" var="giangvien">
					<tr>
						<td>${giangvien.maGV}</td>
						<td>${giangvien.tenGV}<p>Danh sách đề tài:</p> <c:choose>
								<c:when test="${ not empty dsDeTai[giangvien.maGV]}">
									<ul>
										<c:forEach items="${dsDeTai[giangvien.maGV]}" var="detai">
											<li>${detai.tenDeTai}</li>
										</c:forEach>
									</ul>
								</c:when>
							</c:choose>
						</td>
						<td>${giangvien.linhVucNghienCuu}</td>
						<td>${giangvien.soDienThoai}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>