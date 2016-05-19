<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<div id="content">
	<h2>비밀번호변경_이메일</h2>
	<h3 class="hidden">방문페이지 로그</h3>
	<ul id="breadscrumb" class="block_hlist clear">
		<li>HOME</li>
		<li>회원가입</li>
		<li>로그인</li>
	</ul>
	<h3 class="hidden">회원가입 폼</h3>
	<div id="join-form" class="join-form margin-large">
		
	
		<form action="" method="post">
		<!-- 추가 end-->
		
		<!-- 일반적인 form태그 action를 통해 로그인  -->
		<%-- <form action="${pageContext.request.contextPath}/login" method="post"> --%>
			<fieldset>
				<legend class="hidden">비밀번호변경</legend>
				<h3>
					<img src="images/txtTitle.png" />
				</h3>
				<ul id="loginBox">
				
					<li>
						<label for="uid">아이디</label>
						<input name="userid" class="text"/>
					</li>
					<li>
						<label for="email"> 이메일</label>
						<input name="email" class="text"  placeholder="가입하신 이메일주소를 입력해주세요"/>
					</li>
					
				</ul>
				<p>
					<input type="submit"  value="확인메일전송" />
				</p>
				
			</fieldset>
		</form>
	</div>

</div>
