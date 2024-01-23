<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">PostMe</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <c:if test="${not empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="/logout">로그아웃</a>
                </li>
            </c:if>
            <c:if test="${empty sessionScope.user}">
                <li class="nav-item">
                    <a class="nav-link" href="/login">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">회원가입</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
