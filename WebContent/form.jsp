<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="../commons/_head.jspf"%>
</head>
<body>
	<%@include file="../commons/_top.jspf"%>
	<div>
	<c:choose>
	<c:when test="${empty user.userId }">
	<h1>회원가입</h1>
	</c:when>
	<c:otherwise>
	<h1>개인정보수정</h1>
	</c:otherwise>
	</c:choose>
	</div>
		<c:set var="actionUrl" value="/users/create"/>
		<c:if test="${not empty user.userId }">
		<c:set var="actionUrl" value="/users/update"/>
		</c:if>
 	<form action=${actionUrl } method="post">
 		<div class="control-group">
 			<label class="control-label" for="userId">사용자 아이디</label>
 			<div class="controls">
	 			<c:choose>
	 			<c:when test="${empty user.userId }">
	 			<input type="text" name="userId" value="${user.userId }"/>
	 			</c:when>
	 			<c:otherwise>
	 			<input type="hidden" name="userId" value="${user.userId }"/>
	 			${user.userId }
	 			</c:otherwise>
	 			</c:choose>
 			</div>
 		</div>
 		<div class="control-group">
 			<label class="control-label" for="password">비밀번호</label>
 			<div class="controls">
 				<input type="password" name="password" value="${user.password }"/>
 			</div>
 		</div>
 		<div class="control-group">
 			<label class="control-label" for="name">이름</label>
 			<div class="controls">
 				<input type="text" name="name" value="${user.name }"/>
 			</div>
 		</div>
 		<div class="control-group">
 			<label class="control-label" for="email">이메일</label>
 			<div class="controls">
 				<input type="text" name="email" value="${user.email }"/>
 			</div>
 		</div>
 		<div class="control-group">
 			<div class="controls">
 				<button type="submit" class="btn btn-primary">
 				<c:choose>
	 			<c:when test="${empty user.userId }">
	 			회원가입
	 			</c:when>
	 			<c:otherwise>
	 			수정
	 			</c:otherwise>
	 			</c:choose>
 				</button>
 			</div>
 		</div>
 	</form>
</body>
</html>

<!-- get방식은 주소에 입력값이 나타나므로 보안의 문제와 데이터의 크기 제한 문제가 있다. 따라서  일반적으로 post 방식으로 데이터를 전달. 조회하는 것은 get 방식을 활용해서 처리하고 ... -->