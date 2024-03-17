<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: #f4f4f4;
	color: #333;
}

.container {
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-group label {
	font-weight: bold;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
	margin-bottom: 10px;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #004085;
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
	<div class="container">
		<h2 class="mb-4 text-center">PostMe</h2>
		<form action="/login.do" method="post">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<button type="submit" class="btn btn-primary w-100 mb-2">Sign
				in</button>
			<a href="/register" class="btn btn-primary w-100">Sign up</a>
		</form>
		<div id='error-message'></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
		let error = "${flash.error}";

		let errorMessageElement = document.getElementById("error-message");
		errorMessageElement.innerHTML = error;
	</script>
	<script>
		history.pushState(null, null, location.href);
		window.onpopstate = function() {
			history.go(1);
		};
	</script>
</body>
</html>