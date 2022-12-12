<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> User Homepage</title>
<style type="text/css">
	<%@include file="style.css" %>
</style>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	<div class="center-content vertical-center">
		<h1>Hi ${user.firstName} ${user.lastName}, Welcome to Bus ticket Booking</h1>
	</div>
</body>
</html>