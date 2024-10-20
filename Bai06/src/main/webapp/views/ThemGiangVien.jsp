<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div {
	margin: 0 auto;
	width: 50%;
	padding: 10px;
	border: 1px solid #ddd;
}
</style>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/Bai06Servlet?action=addGV"
		>
		<p>
			Mã giảng viên: <input type="text" name="maGV" />
		</p>
		<p>
			Họ tên: <input type="text" name="tenGV" />
		</p>
		<p>
			Lĩnh vực nghiên cứu: <input type="text" name="linhVucNghienCuu" />
		</p>
		<p>
			Số điện thoại: <input type="text" name="soDienThoai" />
		</p>
		<p>
			<button type="submit">Thêm</button>
		</p>
		<p>
			<a href="${pageContext.request.contextPath}/Bai06Servlet">Trở về trang chủ</a>
		</p>
	</form>
	<!-- JavaScript to show alert popup -->
	<script>
		<% if (request.getAttribute("success") != null) { %>
			alert('<%= request.getAttribute("success") %>');
		<% } else if (request.getAttribute("error") != null) { %>
			alert('<%= request.getAttribute("error") %>');
		<% } %>
	</script>

</body>
</html>