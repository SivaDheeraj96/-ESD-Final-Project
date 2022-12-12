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
var printPage = function(e){
/* 	var printScope = document.getElementById('print-content').innerHTML;
	
	var print_window = window.open('', 'my div', 'height=400,width=600');
	print_window.document.write('<html><head><title>Bus Ticket</title>');
	print_window.document.write('<link rel="stylesheet" href="http://localhost:8080/app/static/style.css" type="text/css" />');
	print_window.document.write('</head><body >');
	print_window.document.write(printScope);
	print_window.document.write('</body></html>');
	print_window.print();
	print_window.close(); */
	
	const url='http://localhost:8080/app/booking/print';
	var a = document.createElement("a");
    a.setAttribute("download", "ticket.pdf");
    a.setAttribute("href", url);
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
}

var closePage = function(e){
	window.location.href="http://localhost:8080/app/search";
}

var sendEmail = function(e){
	const url='http://localhost:8080/app/booking/email';
	const Http = new XMLHttpRequest();
	Http.open("GET", url, false);
	Http.send();
	alert("email sent");
}
</script>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div id='print-content'>
	<div class="center-content"  >
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
			<td>Date of Travel:</td>
			<td>${count}</td>
		</tr>
		<tr>
			<td>Total Price:</td>
			<td>USD ${count * trip.getPrice()}</td>
		</tr>
		<tr>
		<td class="ignore-border"><button class="book-btn" onclick="printPage(this)">Print</button><button class="book-btn" onclick="sendEmail(this)">Email</button></td>
		<td class="ignore-border"><button class="book-btn" onclick="closePage(this)">Close</button></td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>