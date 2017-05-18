<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="../commons/_head.jspf"%>
</head>
<body>
	<%@include file="../commons/_top.jspf"%>
	<h1>회원가입</h1>
 	<form action="/users/save" method="post">
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
 			<label class="control-label" for="name">이름</label>
 			<div class="controls">
 				<input type="text" name="name" value=""/>
 			</div>
 		</div>
 		<div class="control-group">
 			<label class="control-label" for="email">이메일</label>
 			<div class="controls">
 				<input type="text" name="email" value=""/>
 			</div>
 		</div>
 		<div class="control-group">
 			<div class="controls">
 				<button type="submit" class="btn btn-primary">회원가입</button>
 			</div>
 		</div>
 	</form>

</body>
</html>

<!-- get방식은 주소에 입력값이 나타나므로 보안의 문제와 데이터의 크기 제한 문제가 있다. 따라서  일반적으로 post 방식으로 데이터를 전달. 조회하는 것은 get 방식을 활용해서 처리하고 ... -->