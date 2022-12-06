<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Confirmation</title>
<style type="text/css">
	<%@include file="style.css" %>
</style>
<script type="text/javascript">
var showConfirmationPage = function(e){
	console.log(e);
	const url='http://localhost:8080/app/booking/success';
	window.location.href = url;
}
</script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div class="center-content" >
<h3>Confirm the Trip Details</h3>
<table class="table-border">
	<tr>
		<td>First Name:</td>
		<td>${user.getFirstName()}</td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td>${user.getLastName()}</td>
	</tr>
	<tr>
		<td>Email:</td>
		<td>${user.getEmail()}</td>
	</tr>
	<tr>
		<td>Source Location:</td>
		<td>${route.getSourceName()}</td>
	</tr>
	<tr>
		<td>Destination Location:</td>
		<td>${route.getDestinationName()}</td>
	</tr>
	<tr>
		<td>Date of Travel:</td>
		<td>${trip.getStringTripDate()}</td>
	</tr>
	<tr>
	<td class="ignore-border"><button class="book-btn" onclick="window.history.back()">Back</button></td>
	<td class="ignore-border"><button class="book-btn" onclick="showConfirmationPage(this)">Confirm</button></td>
	</tr>
</table>
</div>
</body>
</html>