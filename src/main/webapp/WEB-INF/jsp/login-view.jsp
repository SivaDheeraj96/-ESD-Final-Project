<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>App Login</title>
<style type="text/css">
	<%@include file="style.css" %>
</style>
</head>
<body>
<div >
<img class="login-background" src='<c:url value="/static/buspic4.png"></c:url>'/>
<div class="white-background">
	<div class="sign-in-container">
		<h1>Sign In</h1>
		<form:form modelAttribute="user" method="post" action="/app/login">
		<table>
		<tr>
			<td><label class="label-el" >Email:</label></td>
			<td><input class="input-el" name="email" type='text' placeholder="Email"/></td>
			<td><form:errors class="error-msg" path="email"/></td>
		</tr>
		<tr>
			<td><label class="label-el" >Password:</label></td>
			<td><input class="input-el" name="password" type='password' placeholder="Password"/></td>
			<td><form:errors class="error-msg" path="password"/></td>
		</tr>
		<tr>
			<td></td>
			<td><p style="color:red">${user_not_present}</p></td>
		</tr>
		<tr>
			<td></td>
			<td><p style="color:green">${user_registered}</p></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="sign-in-btn card" type='submit' value="Sign In"/></td>
		</tr>
		<tr>
			<td></td>
			<td>Don't have an account? <a class="sign-up-link" href="/app/signup">Sign Up</a></td>
		</tr>
		</table>
		<br/>
		</form:form>
	</div>
	</div>
</div>
</body>
</html>