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
	<p>Bookings for: <b>${user.getFirstName()} ${user.getLastName()}</b></p>
	<div style="width: 50vw;overflow-y: scroll;">
		<table class="table-border" style="width: 100%;">
			<tr>
				<td><b>Source</b></td>
				<td><b>Destination</b></td>
				<td><b>Trip Date</b></td>
				<td><b>Number of Tickets</b></td>
				<td><b>Book On</b></td>
			</tr>
		<c:forEach var="booking" items="${bookings}">
			<tr>
				<td>${booking.getTrip().getBusRoute().getSourceName() }</td>
				<td>${booking.getTrip().getBusRoute().getDestinationName() }</td>
				<td>${booking.getTrip().getStringTripDate() }</td>
				<td>${booking.getCount() }</td>
				<td>${booking.getStringBookingDate() }</td>
			</tr>
		</c:forEach>
		
		</table>
	</div>
	</div>
</body>
</html>