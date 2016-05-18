<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>            
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>
	<!-- title 영역 -->
	<tiles:getAsString name="title"/>
	</title>
	<!-- 스타일 시트 link  -->
	<!-- <link href="notice.css" type="text/css" rel="stylesheet" /> -->
	<link href='<tiles:getAsString name="css"/>' type="text/css" rel="stylesheet" />
</head>
<body>
	<!-- Header 영역  -->
	<tiles:insertAttribute name="header"/>
	<!-- Visual 영역  -->
	<tiles:insertAttribute name="visual"/>
	<!-- Main  영역 Start -->
	<div id="main">
			<div class="top-wrapper clear">
				<!-- content 영역 (notice.jsp) -->
				<tiles:insertAttribute name="content"/>
				<!-- Aside(Navi) 영역 -->
				<tiles:insertAttribute name="aside"/>
			</div>
	</div>		
	<!-- Main  영역 End -->
	<!-- Footer 영역 -->
	<tiles:insertAttribute name="footer"/>
</body>
</html>