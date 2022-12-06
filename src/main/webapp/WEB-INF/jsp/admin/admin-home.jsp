<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Admin Homepage</title>
<style type="text/css">
	<%@include file="../style.css" %>
</style>
</head>
<body>
	
	<jsp:include page="header.jsp"/>
	<div>
		<h1>Hi ${user.firstName} ${user.lastName}(Admin), Welcome to Bus ticket Booking</h1>
	</div>
</body>
</html>