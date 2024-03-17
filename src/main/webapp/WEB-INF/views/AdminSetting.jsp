<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 설정</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.settings-container {
	flex-grow: 1;
	background-color: #f8f8f8;
	padding: 20px;
	border-radius: 5px;
}

.reset-button {
	background-color: #dc3545;
	color: white;
}

.reset-button:hover {
	background-color: #c82333;
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
		<div class="settings-container">
			<h1>설정</h1>
			<h2>데이터베이스 관련</h2>
			<form action="/admin/deleteAllData" method="post">
				<div class="form-group">
					<h3>초기화</h3>
					<p>이 버튼을 누르면 전체 데이터가 초기화됩니다. 이 작업은 되돌릴 수 없습니다.</p>
					<button type="submit" class="btn reset-button">전체 초기화</button>
				</div>
			</form>
			<form action="/admin/insertSampleData" method="post">
				<div class="form-group">
					<h3>샘플 추가</h3>
					<p>이 버튼을 누르면 테스트용 샘플 데이터가 추가됩니다.</p>
					<button type="submit" class="btn btn-success">데이터 추가</button>
				</div>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
