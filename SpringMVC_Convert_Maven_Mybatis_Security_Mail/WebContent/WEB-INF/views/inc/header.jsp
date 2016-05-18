<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
	
<!-- 경로 설정 작업 -->
<div id="header">
	<div class="top-wrapper">
		<h1 id="logo">
			<a href="${pageContext.request.contextPath}/index.htm"><img src="" alt="로고" /></a>
		</h1>
		<h2 class="hidden">메인메뉴</h2>
		<ul id="mainmenu" class="block_hlist">
			<li><a href="">kosta가이드</a></li>
			<li><a href="">kosta과정</a></li>
			<li><a href="">kosta</a></li>
		</ul>
		<form id="searchform" action="" method="get">
			<fieldset>
				<legend class="hidden"> 과정검색폼 </legend>
				<label for="query">과정검색</label> <input type="text" name="query" />
				<input type="submit" class="button" value="검색" />
			</fieldset>
		</form>
		<h3 class="hidden">로그인메뉴</h3>
		<ul id="loginmenu" class="block_hlist">
			<li><a href="${pageContext.request.contextPath}/index.htm">HOME</a></li>
			
			<!--1. 기존 방법  -->
			<%-- <li><a href="${pageContext.request.contextPath}/joinus/login.htm">로그인</a></li> --%>
	
			<!--2. security의 login/ logout 처리 추가  -->
			<%-- <c:if test="${empty pageContext.request.userPrincipal}">
				<li><a
					href="${pageContext.request.contextPath}/joinus/login.htm">로그인</a></li>
			</c:if>

			<c:if test="${not empty pageContext.request.userPrincipal}">
				<li><a href="${pageContext.request.contextPath}/logout">
						(${pageContext.request.userPrincipal.name})로그아웃</a></li>
			</c:if> --%>
			<!-- 추가 end  -->
			
			<!-- 3. 스프링에서 제공하는 security taglib사용_4.x.x버전시 -->
			<security:authorize access="!hasRole('ROLE_USER')">
				<li><a
					href="${pageContext.request.contextPath}/joinus/login.htm">로그인</a></li>
			</security:authorize>
			<security:authentication property="name" var="LoingUser" />
			<!-- property="name" 은 security-context.xml에서 설정한 security:user name="kkk" 이부분... -->
			<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
				<li><a href="${pageContext.request.contextPath}/logout">
						(${LoingUser})로그아웃</a></li>
			</security:authorize>

			<!--
				[위의 코드 설명]
				<se:authorize ifNotGranted="ROLE_USER"> //if문 사용한다고 생각하면 편함
				     NOT ROLE_USER (로그인한 사용자가 ROLE_USER 그룹이 아니면)
				</se:authorize>
				
				<se:authorize ifAllGranted="ROLE_USER , ROLE_ADMIN">
				      ALL -> AND => ROLE_USER and ROLE_ADMIN  (로그인한 사용자 ..둘다 만족)
				</se:authorize>
				
				<se:authorize ifAnyGranted="ROLE_USER , ROLE_ADMIN">
				      Any -> OR => ROLE_USER OR ROLE_ADMIN  (로그인한 사용자 ..둘 중에 하나만 만족한다면)
				</se:authorize>				
			  -->

			<!-- 추가 end  -->
			<li><a href="${pageContext.request.contextPath}/joinus/join.htm">회원가입</a></li>
		</ul>
		<h3 class="hidden">회원메뉴</h3>
		<ul id="membermenu" class="clear">
			<li><a href="">
				<img src="${pageContext.request.contextPath}/images/menuMyPage.png" alt="마이페이지" /></a>
			</li>
			<li><a href="${pageContext.request.contextPath}/customer/notice.htm">
			    <img src="${pageContext.request.contextPath}/images/menuCustomer.png" alt="고객센터" /></a>
			</li>
		</ul>
	</div>
</div>