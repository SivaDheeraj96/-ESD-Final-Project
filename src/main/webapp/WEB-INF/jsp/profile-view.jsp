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
	<jsp:include page="header.jsp"></jsp:include>
	<div class="center-content vertical-center">
		<h1>Your Profile</h1>
		<form:form modelAttribute="user" enctype="multipart/form-data" method="post" action="/app/profile">
			<table>
			<tr>
				<td><label class="label-el">Profile Pic:</label></td>
				<td><input class="input-el" name="profile_pic" type='file' placeholder="First Name" accept="image/png, image/jpeg"/></td>
				<td><form:errors class="error-msg" path="email"/></td>
			</tr>
			
			<tr>
				<td><label class="label-el">First Name:</label></td>
				<td><input class="input-el" name="firstName" type='text' placeholder="First Name" value="${user.getFirstName()}"/></td>
				<td><form:errors class="error-msg" path="email"/></td>
			</tr>
			<tr>
				<td><label class="label-el" >Last Name:</label></td>
				<td><input class="input-el" name="lastName" type='text' placeholder="Last Name" value="${user.getLastName()}"/></td>
				<td><form:errors class="error-msg" path="email"/></td>
			</tr>
			<tr>
				<td><label class="label-el" >Email</label></td>
				<td><input class="input-el" name="email" type='text' placeholder="Email" value="${user.getEmail()}"/></td>
				<td><form:errors class="error-msg" path="email"/></td>
			</tr>
			<tr>
				<td><label class="label-el" >Password:</label></td>
				<td><input class="input-el" name="password" type='password' placeholder="Password" value="${user.getPassword()}" /></td>
				<td><form:errors class="error-msg" path="password"/></td>
			</tr>
			<tr>
				<td></td>
				<td><p style="color:green">${success}</p></td>
			</tr>
			<tr>
				<td></td>
				<td><p style="color:red">${error}</p></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="sign-up-btn card" type='submit' value="Update Profile"/></td>
			</tr>
			</table>
		</form:form>
	</div>
	</div>
</div>
</body>
</html>