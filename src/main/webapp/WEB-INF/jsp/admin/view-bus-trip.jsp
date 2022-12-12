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
	<%@include file="../style.css" %>
</style>
<script type="text/javascript">
var deleteTrip = function(e){
	console.log(e);
	const url='http://localhost:8080/app/admin/trip?'+'tripId='+e.dataset.tripid;
	const Http = new XMLHttpRequest();
	Http.open("DELETE", url, true);
	Http.send();
	window.location.href = 'http://localhost:8080/app/admin/trip/view';
}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="center-content vertical-center" >
	<p>Available Trips and Routes</p>
	<div style="width: 50vw;overflow-y: scroll">
		<table class="table-border" style="width: 100%">
			<tr>
				<td><b>Source</b></td>
				<td><b>Destination</b></td>
				<td><b>Trip Date</b></td>
				<td><b>Price</b></td>
				<td/>
			</tr>
		<c:forEach var="trip" items="${trips}">
			<tr>
				<td>${trip.getBusRoute().getSourceName() }</td>
				<td>${trip.getBusRoute().getDestinationName() }</td>
				<td>${trip.getStringTripDate() }</td>
				<td>${trip.getPrice() }</td>
				<td><img class="delete-icon" data-tripId="${trip.getTripId()}" onclick="deleteTrip(this)" src='<c:url value="/static/remove.png"></c:url>'/></td>
			</tr>
		</c:forEach>
		
		</table>
	</div>
	</div>
</body>
</html>