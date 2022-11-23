<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>App Login</title>
</head>
<body>
	<h1>User Sign In</h1>
	<form:form modelAttribute="user" method="post" action="/app/login">
	<table>
	<tr>
		<td><label>Email</label></td>
		<td><input name="email" type='text'/></td>
		<td><form:errors style="color:red" path="email"/></td>
	</tr>
	<tr>
		<td><label>Password:</label></td>
		<td><input name="password" type='password'/></td>
		<td><form:errors style="color:red" path="password"/></td>
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
		<td><input type='submit'/></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="/app/signup">Sign Up</a></td>
	</tr>
	</table>
	<br/>
	</form:form>
</body>
</html>