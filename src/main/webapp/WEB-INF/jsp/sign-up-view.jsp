<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Sign Up</title>
<style type="text/css">
	<%@include file="style.css" %>
</style>
</head>
<body>
<div>
<img class="login-background" src='<c:url value="/static/buspic4.png"></c:url>'/>
	<div class="white-background">
	<div class="sign-up-container">
		<h1>Create Account</h1>
		<form:form modelAttribute="user" method="post" action="/app/signup">
			<table>
			<tr>
				<td><label class="label-el">First Name:</label></td>
				<td><input class="input-el" name="firstName" type='text' placeholder="First Name"/></td>
				<td><form:errors class="error-msg" path="email"/></td>
			</tr>
			<tr>
				<td><label class="label-el" >Last Name:</label></td>
				<td><input class="input-el" name="lastName" type='text' placeholder="Last Name"/></td>
				<td><form:errors class="error-msg" path="email"/></td>
			</tr>
			<tr>
				<td><label class="label-el" >Email</label></td>
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
				<td><p style="color:red">${user_already_present}</p></td>
			</tr>
			<tr>
				<td></td>
				<td><p style="color:red">${error}</p></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="sign-up-btn card" type='submit' value="Create Account"/></td>
			</tr>
			<tr>
				<td></td>
				<td><a href="/app/login">Sign In</a></td>
			</tr>
			</table>
		</form:form>
	</div>
	</div>
</div>
</body>
</html>