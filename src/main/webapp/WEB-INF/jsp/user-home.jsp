<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> User Homepage</title>
<style>
header{
	height: 10%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
}
.logout{
	float: right;
	padding: 1%
	text-decoration: none;
}
.nav-container{
	display: flex;
	flex-direction: row;
}
.nav-option{
	padding:1%;
	text-decoration: none;
}
</style>
</head>
<body>
	
	<header>
	<nav class="nav-container">
		<a class="nav-option" href="/app/search">Search</a>
		<a class="nav-option" href="/app/bookings">Bookings</a>
	</nav>
	<a class="logout" href="/app/login">Logout</a></header>
	<div>
		<h1>Hi ${user.firstName} ${user.lastName}, Welcome to Bus ticket Booking</h1>
	</div>
</body>
</html>