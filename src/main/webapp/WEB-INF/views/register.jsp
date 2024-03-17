<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>register</title>
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
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #004085;
}

.error {
	color: red;
	font-size: 0.8em;
}
</style>
</head>
<body>
	<div class="container">
		<h2 class="mb-4 text-center">Join PostMe</h2>
		<form action="/register.do" method="post"
			onsubmit="return validateForm()">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control" id="username" name="username" required>
			</div>
			<div class="form-group">
				<label for="name">Name</label> <input type="text"
					class="form-control" id="name" name="name" required>
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email"
					class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password</label> <input
					type="password" class="form-control" id="confirmPassword" required>
			</div>
			<div class="form-group">
				<p id="error-message" class="error"></p>
			</div>
			<button type="submit" class="btn btn-primary w-100">Sign up</button>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
		function validateForm() {
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirmPassword").value;
			if (password !== confirmPassword) {
				document.getElementById("error-message").textContent = "패스워드가 일치하지 않습니다.";
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
