<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/Bai06Servlet?action=addDT">
		<p>
			Mã đề tài: <input type="text" name="maDeTai" />
		</p>
		<p>
			Tên đề tài: <input type="text" name="tenDeTai" />
		</p>
		<p>
			Năm đăng ký: <input type="text" name="namDK" />
		</p>
		<p>
			Mô tả: <input type="text" name="moTa" />
		</p>
		<p>
			Mã GV: <input type="text" name="maGV" />
		</p>
		<p>
			<button type="submit">Thêm</button>
		</p>
		<p>
			<a href="${pageContext.request.contextPath}/Bai06Servlet">Trở về
				trang chủ</a>
		</p>
	</form>
		<script>
		<% if (request.getAttribute("success") != null) { %>
			alert('<%= request.getAttribute("success") %>');
		<% } else if (request.getAttribute("error") != null) { %>
			alert('<%= request.getAttribute("error") %>');
		<% } %>
	</script>
</body>
</html>