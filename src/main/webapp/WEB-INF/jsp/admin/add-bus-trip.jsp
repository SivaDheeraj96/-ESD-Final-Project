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
	<form:form modelAttribute="busRoute" action="/app/admin/trip/add" method="post">
	<div class="search-container">
		<table style="width: 30%;">
			<tr>
				<td><label class="search-label">Bus Route</label></td>
				<td><label class="search-label">Date</label></td>
			</tr>
			<tr>
				<td>
				<select name="busRoute" style="padding: 4%;width: 95%;" class="search-input" required>
				<c:forEach var="route" items="${routes}">
					<option value="${route.getRouteId()}" >${route.getSourceName()}-${route.getDestinationName()}</option>
                </c:forEach>
				</select></td>
				<td><input type="Date" name="date" class="search-input" required/></td>
				<td><input class="search-btn" type="submit" value="Add Trip"/></td>
			</tr>
		</table>
	</div>
	
	<div style="display:flex; justify-content:center;"><span style="color:red">${tripError}</span></div>
	<div style="display:flex; justify-content:center;"><span style="color:green">${created}</span></div>
	</form:form>
</body>
</html>