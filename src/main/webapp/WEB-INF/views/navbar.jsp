<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="/"> PostMe <c:if
			test="${sessionScope.isAdmin}">
            (Admin)
        </c:if>
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav ml-auto">
			<c:if test="${sessionScope.isAdmin}">
				<li class="nav-item"><a class="nav-link"
					href="/admin/dashboard">관리</a></li>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				<li class="nav-item"><a class="nav-link" href="/dashboard">게시글
						작성</a></li>
				<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			</c:if>
			<c:if test="${empty sessionScope.user}">
				<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="/register">회원가입</a></li>
			</c:if>
		</ul>
	</div>
</nav>