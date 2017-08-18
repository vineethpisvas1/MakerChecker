<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Redirect</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<c:set var="temp1" value="right">
	</c:set>
	<c:set var="temp2" value="correct">
	</c:set>
	<c:set var="temp3" value="right">
	</c:set>
	<c:if test="${check.equals(temp1)}">
		<script>
			setTimeout(function() {
				window.location.href = 'MakerView.jsp'
			}, 3000);
		</script>
	</c:if>
	<c:if test="${check.equals(temp2)}">
		<script>
			setTimeout(function() {
				window.location.href = 'CheckerView.jsp'
			}, 3000);
		</script>
	</c:if>
	<c:if test="${!check.equals(temp1) && !check.equals(temp2)}">
		<script>
			setTimeout(function() {
				window.location.href = 'index.jsp'
			}, 3000);
		</script>
	</c:if>
	<!-- Page Header -->
	<br>
	<br>
	<div class="page-header container" align="center">
		<h4>
			<strong>Operation has been successful. Please wait while you
				are redirected...</strong>
		</h4>
	</div>
	<!-- Script -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>