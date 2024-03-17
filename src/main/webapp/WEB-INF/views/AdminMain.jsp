<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostMe-Admin</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.sidebar {
	position: fixed;
	top: 0; 
	left: 0; 
	width: 250px;
	height: 100%; 
}

.dashboard-container {
	margin-left: 250px;
	flex-grow: 1;
	background-color: #f8f8f8;
	padding: 20px;
	border-radius: 5px;
}

.stats-box {
	padding: 15px;
	margin-bottom: 20px;
	background-color: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
}
</style>
</head>
<body>
	<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0L);
	%>
	<div class="d-flex">
		<jsp:include page="sidebar.jsp" />
		<div class="dashboard-container">
			<div class="stats-box">
				<h3>통계 정보</h3>
				<p>총 사용자 수: ${totalUsers}</p>
				<p>총 게시물 수: ${totalPosts}</p>
				<p>총 댓글 수: ${totalComments}</p>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
