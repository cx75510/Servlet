<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<%@include file="../commons/_head.jspf"%>
</head>
<body>
	<%@include file="../commons/_top.jspf"%>
	
	<h1>로그인입니다.</h1>
	<form action="/users/login" method="post">
		<c:if test="${not empty errorMessage }">
			<div class="control-group">
	 			<label class="error" for="userId">${errorMessage }</label>
	 		</div>
		</c:if>
 		<div class="control-group">
 			<label class="control-label" for="userId">사용자 아이디</label>
 			<div class="controls">
 				<input type="text" name="userId" value=""/>
 			</div>
 		</div>
 		<div class="control-group">
 			<label class="control-label" for="password">비밀번호</label>
 			<div class="controls">
 				<input type="password" name="password" value=""/>
 			</div>
 		</div>
 		<div class="control-group">
 			<div class="controls">
 				<button type="submit" class="btn btn-primary">로그인</button>
 			</div>
 		</div>
 	</form>
</body>
</html>