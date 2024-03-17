<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management</title>
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

.user-management-container {
	margin-left: 250px;
	flex-grow: 1;
	background-color: #f8f8f8;
	padding: 20px;
	border-radius: 5px;
}

.user-container {
	background-color: white;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.user-details {
	flex-grow: 1;
}

#pagination {
	display: flex;
	justify-content: center;
	padding-top: 20px;
}

#pagination a {
	color: #007bff;
	text-decoration: none;
	padding: 5px 10px;
	border: 1px solid #ddd;
	margin: 0 2px;
}

#pagination a:hover {
	background-color: #f8f8f8;
}

#pagination span {
	padding: 5px 10px;
	border: 1px solid #ddd;
	margin: 0 2px;
	background-color: #007bff;
	color: white;
}

.modal-content {
	padding: 20px;
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
		<div class="user-management-container">
			<h1>사용자 관리</h1>
			<c:forEach items="${usersList}" var="user" varStatus="status">
				<div class="user-container">
					<div class="user-details">
						<p>아이디: ${user.username}</p>
						<p>이름: ${user.name}</p>
						<p>이메일: ${user.email}</p>
					</div>
					<div>
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#editUserModal-${user.id}">수정</button>
						<button class="btn btn-danger" onclick="deleteUser('${user.id}')">삭제</button>
						<input type="checkbox" id="adminCheck-${user.id}"
							${user.isAdmin ? 'checked' : ''}
							onclick="confirmAdminPromotion(${user.id})"> 관리자
					</div>
				</div>
				<div class="modal fade" id="editUserModal-${user.id}" tabindex="-1"
					role="dialog" aria-labelledby="editUserModalLabel-${user.id}"
					aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="editUserModalLabel-${user.id}">사용자
									정보 수정</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="/admin/users/update/${user.id}" method="post">
									<div class="form-group">
										<label for="name-${user.id}">이름</label> <input type="text"
											class="form-control" id="name-${user.id}" name="name"
											value="${user.name}">
									</div>
									<div class="form-group">
										<label for="email-${user.id}">이메일</label> <input type="email"
											class="form-control" id="email-${user.id}" name="email"
											value="${user.email}">
									</div>
									<button type="submit" class="btn btn-primary">저장</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<nav id="pagination">
				<c:forEach begin="1" end="${totalPages}" var="pageNum">
					<c:choose>
						<c:when test="${pageNum == currentPage}">
							<span class="active-page">${pageNum}</span>
						</c:when>
						<c:otherwise>
							<a href="?page=${pageNum}" class="page-link">${pageNum}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</nav>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
	function confirmAdminPromotion(userId) {
	    var checkBox = document.getElementById('adminCheck-' + userId);
	    var confirmMessage = checkBox.checked ? 
	                         '이 사용자를 관리자로 변경하시겠습니까?' : 
	                         '이 사용자를 일반 사용자로 돌리시겠습니까?';
	    var confirmPromotion = confirm(confirmMessage);

	    if (confirmPromotion) {
	        $.ajax({
	            url: '/admin/users/promote/' + userId,
	            type: 'POST',
	            data: { adminStatus: checkBox.checked },
	            success: function(response) {
	                alert("사용자의 관리자 상태가 변경되었습니다.");
	            },
	            error: function(error) {
	                alert("오류가 발생했습니다.");
	                checkBox.checked = !checkBox.checked; 
	            }
	        });
	    } else {
	        checkBox.checked = !checkBox.checked;
	    }
	}
	function deleteUser(userId) {
	    if(confirm('이 사용자를 삭제하시겠습니까?')) {
	        $.ajax({
	            url: '/admin/users/delete/' + userId,
	            type: 'POST',
	            success: function(response) {
	                alert('사용자가 삭제되었습니다.');
	                location.reload();
	            },
	            error: function(error) {
	                alert('오류가 발생했습니다.');
	            }
	        });
	    }
	}
	</script>
</body>
</html>
