<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin page to add Bus Trip</title>
<style type="text/css">
	<%@include file="../style.css" %>
</style>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<form:form modelAttribute="trip" action="/app/admin/trip/add" method="post">
	<div class="search-container">
		<table style="width: 30%;">
			<tr>
				<td><label class="search-label">Bus Route</label></td>
				<td><label class="search-label">Price</label></td>
				<td><label class="search-label">Date</label></td>
			</tr>
			<tr>
				<td>
				<select name="busRoute" style="padding: 4%;width: 95%;" class="search-input" required>
				<c:forEach var="route" items="${routes}">
					<option value="${route.getRouteId()}" >${route.getSourceName()}-${route.getDestinationName()}</option>
                </c:forEach>
				</select></td>
				<td><input type="number" name="price" class="search-input" placeholder="price" required/></td>
				<td><input type="Date" name="tripDate" class="search-input" required/></td>
				<td><input class="search-btn" type="submit" value="Add Trip"/></td>
			</tr>
			<tr>
				<td><form:errors path="busRoute"/></td>
				<td><form:errors path="price" class="search-label"/></td>
				<td><form:errors  path="tripDate" class="search-label"/></td>
			</tr>
		</table>
	</div>
	<div style="display:flex; justify-content:center;"><form:errors path=""></form:errors></div>
	<div style="display:flex; justify-content:center;"><span style="color:red">${tripError}</span></div>
	<div style="display:flex; justify-content:center;"><span style="color:green">${created}</span></div>
	</form:form>
</body>
</html>