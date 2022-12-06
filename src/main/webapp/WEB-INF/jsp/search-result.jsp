<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search For Buses</title>
<style type="text/css">
	<%@include file="style.css" %>
</style>
<script type="text/javascript">
var showConfirmationPage = function(e){
	console.log(e);
	const url='http://localhost:8080/app/booking/confirmation?'+'tripId='+e.dataset.tripid;
	window.location.href = url;
}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="center-content vertical-center" >
	<p>Available Trips between the <b>${route.getSourceName()}</b> to <b>${route.getDestinationName()}</b></p>
	<div style="width: 50vw;">
		<table class="table-border" style="width: 100%;">
		<c:forEach var="trip" items="${trips}">
			<tr>
			<td>${trip.getStringTripDate()}</td>
			<td><button class="book-btn" onClick="showConfirmationPage(this)" data-tripId="${trip.getTripId()}">Book</button></td>
			</tr>
		</c:forEach>
		
		</table>
	</div>
	</div>
</body>
</html>