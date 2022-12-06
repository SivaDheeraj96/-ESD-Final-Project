<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin page to View Bus Route</title>
<style type="text/css">
	<%@include file="../style.css" %>
</style>
<script type="text/javascript">
var deleteRoute = function(e){
	console.log(e);
	const Http = new XMLHttpRequest();
	const url='http://localhost:8080/app/admin/route/delete?'+'routeId='+e.dataset.routeid;
	Http.open("DELETE", url, true);
	Http.send();
	window.location.href = 'http://localhost:8080/app/admin/route/view';
}
</script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="flex-container">
		<c:forEach var="route" items="${routes}">
			<div class="card">
				<label >
					<b>${route.getSourceName()}</b> -> <b>${route.getDestinationName()}</b>
				</label>
				<img class="delete-icon" data-routeId="${route.getRouteId()}" onclick="deleteRoute(this)" src='<c:url value="/static/remove.png"></c:url>'/>
			</div>
         </c:forEach>
	</div>
</body>
</html>