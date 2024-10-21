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
	<form
		action="${pageContext.request.contextPath}/Bai07Servlet?action=addTT" method="post">
		<p>
			Mã tin tức<input type="text" name="maTT">
		</p>
		<p>
			Tiêu đề<input type="text" name="tieuDe">
		</p>
		<p>
			Nội dung<input type="text" name="noiDung">
		</p>
		<p>
			Liên kết<input type="text" name="lienKet">
		</p>
		<p>
			Mã danh mục<input type="text" name="maDM">
		</p>
		<p>
			<input type="submit" value="Thêm tin tức"> <a
				href="${pageContext.request.contextPath}/Bai07Servlet">Trở về
				trang chủ</a>
		</p>
	</form>
	<script>
		<%if (request.getAttribute("success") != null) {%>
			alert('<%=request.getAttribute("success")%>');
		<%} else if (request.getAttribute("error") != null) {%>
			alert('<%=request.getAttribute("error")%>
		');
	<%}%>
		
	</script>
</body>
</html>