<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Post Details</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
.post-detail-container, .comments-section {
	background-color: #f8f8f8;
	padding: 15px;
	border-radius: 5px;
	margin-bottom: 20px;
}

.comment {
	border-bottom: 1px solid #ddd;
	padding: 10px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container mt-3">
		<div class="post-detail-container">
			<h3>${post.title}</h3>
			<p>${post.content}</p>
			<p>
				<strong>첨부파일 : </strong><a href="/download/file/${fileInfo.id}">${fileInfo.filename}</a>
			</p>
			<small>작성자: ${post.userId}</small> <small>작성일:
				${post.createdAt}</small>
			<c:if
				test="${post.userId == sessionScope.loggedInUserId or sessionScope.isAdmin}">
				<button class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#editPostModal">수정</button>
				<a href="${pageContext.request.contextPath}/posts/delete/${post.id}"
					class="btn btn-danger btn-sm">삭제</a>
			</c:if>
		</div>
		<!-- 댓글 섹션 -->
		<div class="comments-section">
			<h5>댓글</h5>
			<c:forEach items="${commentsList}" var="comment">
				<div class="comment">
					<p>${comment.content}</p>
					<small>작성자: ${comment.userId}, 작성일: ${comment.createdAt}</small>
					<c:if
						test="${comment.userId == sessionScope.loggedInUserId or sessionScope.isAdmin}">
						<!-- 댓글 수정 및 삭제 버튼 -->
						<button class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#editCommentModal-${comment.id}">수정</button>
						<a
							href="${pageContext.request.contextPath}/comments/delete/${comment.id}?postId=${post.id}"
							class="btn btn-danger btn-sm">삭제</a>
					</c:if>
				</div>
			</c:forEach>

			<!-- 댓글 작성 폼 -->
			<form action="${pageContext.request.contextPath}/comments/add"
				method="post" class="mt-3" id=PostCommentForm onsubmit="return validatePostCommentForm()">
				<textarea name="content" class="form-control"
					placeholder="댓글을 입력하세요"></textarea>
				<input type="hidden" name="postId" value="${post.id}"> <input
					type="hidden" name="userId" value="${sessionScope.loggedInUserId}">
				<button type="submit" class="btn btn-primary mt-2">댓글 작성</button>
			</form>
		</div>
	</div>

	<!-- 게시글 수정 모달 -->
	<div class="modal fade" id="editPostModal" tabindex="-1" role="dialog"
		aria-labelledby="editPostModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="editPostModalLabel">게시글 수정</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath}/posts/update/${post.id}"
						id="editPostForm" method="post" enctype="multipart/form-data"
						 onsubmit="return validatePostForm()">
						<input type="text" class="form-control mb-2" name="title"
							value="${post.title}">
						<textarea class="form-control" name="content">${post.content}</textarea>
						<div class="form-group">
							<input type="file" class="form-control-file" id="file"
								name="file">
						</div>
						<button type="submit" class="btn btn-primary mt-2">저장</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 댓글 수정 모달 -->
	<c:forEach items="${commentsList}" var="comment">
		<c:if
			test="${comment.userId == sessionScope.loggedInUserId or sessionScope.isAdmin}">
			<div class="modal fade" id="editCommentModal-${comment.id}"
				tabindex="-1" role="dialog"
				aria-labelledby="editCommentModalLabel-${comment.id}"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="editCommentModalLabel-${comment.id}">댓글
								수정</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form
								action="${pageContext.request.contextPath}/comments/update/${comment.id}"
								method="post" id="editCommentForm" onsubmit="return validateEditCommentForm()" >
								<textarea class="form-control" name="content">${comment.content}</textarea>
								<input type="hidden" name="postId" value="${post.id}">
								<button type="submit" class="btn btn-primary mt-2">저장</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</c:forEach>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
	function validateEditCommentForm() {
        var editcommentField = document.querySelector('#editCommentForm textarea[name="content"]');
        if (editcommentField.value.trim() === '') {
            alert('댓글을 입력하세요.');
            return false;
        }
        return true;
    }
    function validatePostForm() {
        var titleField = document.querySelector('#editPostForm input[name="title"]');
        var contentField = document.querySelector('#editPostForm textarea[name="content"]');
        if (titleField.value.trim() === '' || contentField.value.trim() === '') {
            alert('제목과 내용을 입력하세요.');
            return false;
        }
        return true;
    }
    function validatePostCommentForm() {
        var commentField = document.querySelector('#PostCommentForm textarea[name="content"]');
        if (commentField.value.trim() === '') {
            alert('댓글을 입력하세요.');
            return false;
        }
        return true;
    }
	</script>	
</body>
</html>
