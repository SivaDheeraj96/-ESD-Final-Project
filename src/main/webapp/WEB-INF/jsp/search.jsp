<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search For Buses</title>
<style type="text/css">
	<%@include file="style.css" %>
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<form:form modelAttribute="busRoute" action="/app/search" method="post">
	<div class="search-container">
		<table style="width: 30%;">
			<tr>
				<td><label class="search-label">Source</label></td>
				<td><label class="search-label">Destination</label></td>
			</tr>
			<tr>
				<td><input type="text" name="sourceName" class="search-input" required placeholder="Source"/></td>
				<td><input type="text" name="destinationName" class="search-input" required placeholder="Destination"/></td>
				<td><input class="search-btn" type="submit" value="Search"/></td>
			</tr>
			<tr>
				<td><form:errors style="color:red" path="sourceName"/></td>
				<td><form:errors style="color:red" path="destinationName"/></td>
			</tr>
			<tr>
				<td/>
				<td></td>
			</tr>
		</table>
	</div>
	<div style="display:flex; justify-content:center;"><span style="color:red">${routeError}</span></div>
	</form:form>
</body>
</html>