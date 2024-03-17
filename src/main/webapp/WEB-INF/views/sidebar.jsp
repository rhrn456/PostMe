<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="sidebar d-flex flex-column flex-shrink-0 bg-light"
	style="width: 250px; height: 100vh; border-right: 1px solid #ddd;">
	<a href="/admin/dashboard"
		class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none"
		style="margin-top: 10px;"> <strong class="fs-5"
		style="color: black; text-align: center; width: 100%;">ManageMe</strong>
	</a>
	<hr>
	<ul class="nav nav-pills flex-column mb-auto">
		<li><a href="/admin/users"
			class="nav-link btn btn-light text-dark mb-2"> 사용자 관리 </a></li>
		<li><a href="/admin/posts"
			class="nav-link btn btn-light text-dark mb-2"> 게시물 관리 </a></li>
		<li><a href="/admin/settings"
			class="nav-link btn btn-light text-dark mb-2"> 설정 </a></li>
	</ul>
	<hr>
	<a href="/logout" class="btn btn-light w-100 mb-2"
		style="border: 1px solid #ddd;">로그아웃</a> <a href="/"
		class="btn btn-light w-100" style="border: 1px solid #ddd;">PostMe</a>
</div>
<script>
	$(document).ready(function() {
		$(".nav-link").hover(function() {
			$(this).css("opacity", "0.5");
		}, function() {
			$(this).css("opacity", "1");
		});
	});
</script>
