<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<title>index</title>
		<link href="index.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<!-- Header  영역  -->
	<tiles:insertAttribute name="header" />
	<!-- Main 영역 -->
	<div id="main">
		<div class="top-wrapper clear">
			<tiles:insertAttribute name="content" />
		</div>	
	</div>
	<!-- Footer  영역  -->
	<tiles:insertAttribute name="footer" />	
</body>
</html>