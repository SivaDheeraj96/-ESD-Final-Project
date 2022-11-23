<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Sign Up</title>
</head>
<body>
	<h1>User Sign Up</h1>
	<form:form modelAttribute="user" method="post" action="/app/signup">
		<table>
		<tr>
			<td><label>First Name:</label></td>
			<td><input name="firstName" type='text'/></td>
			<td><form:errors style="color:red" path="email"/></td>
		</tr>
		<tr>
			<td><label>Last Name:</label></td>
			<td><input name="lastName" type='text'/></td>
			<td><form:errors style="color:red" path="email"/></td>
		</tr>
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
			<td><p style="color:red">${user_already_present}</p></td>
		</tr>
		<tr>
			<td></td>
			<td><p style="color:red">${error}</p></td>
		</tr>
		<tr>
			<td></td>
			<td><input type='submit'/></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="/app/login">Sign In</a></td>
		</tr>
		</table>
	</form:form>
</body>
</html>