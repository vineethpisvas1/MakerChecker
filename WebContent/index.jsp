<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>SignIn</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<script src="jsFunctions.js"></script>
	<div class="container"
		style="padding-left: 350px; padding-right: 350px">
		<!-- Page Header -->
		<br>
		<br>
		<div class="page-header" align="center">
			<h2>
				<strong>LOGIN</strong>
			</h2>
		</div>
		<br>
		<br>

		<form action="MakerCheckerController" method="post" name="LoginForm">
			<!-- Text Input Boxes -->
			<div class="form-group">
				<label for="usr">Username:</label> <input type="text"
					class="form-control" name="username"
					onkeypress="return isAlphanumeric(event)" required />
			</div>
			<div class="form-group">
				<label for="usr">Password:</label> <input type="password"
					class="form-control" name="password" required />
			</div>
			<!-- <div class="form-group">
					<label class="radio-inline"><input type="radio" name="optradio1" value="Maker" required>Maker</label>
					<label class="radio-inline"><input type="radio" name="optradio1" value="Checker" required>Checker</label>
				</div><br> -->
			<!-- Submit Button Box -->
			<div align="center">
				<input type="submit" class="btn btn-primary"
					style="font-weight: 700; font-size: 13px" id="signin"
					value="Sign In" name="operation"> <input type="reset"
					class="btn btn-warning" style="font-weight: 700; font-size: 13px"
					style="display:inline" value="Reset" id="reset">
			</div>
		</form>
		<c:set var="value1" value="failed"></c:set>
		<c:set var="value2" value="logout"></c:set>
		<c:if test="${login.equals(value1)}">
			<h3 align="center">
				Login Failed!!
			</h3>
		</c:if>
		<c:if test="${login.equals(value2)}">
			<h3 align="center">
				Successfully logged out!!
			</h3>
		</c:if>
	</div>

	<!-- Script -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>