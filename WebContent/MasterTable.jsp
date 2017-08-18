<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Master Table</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<script src="jsFunctions.js"></script>
	<div style="float:right">
		<form action="CheckerController" method="post">
			<h3>
				<input type="submit" class="btn btn-success" value="Go back?">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</h3>
		</form>
	</div>
	<!-- Page Header -->
	<div class="page-header container">
		<h2>
			<strong>Master Table</strong>
		</h2>
	</div>
	<div class="container">
			<div align="center">
				<table class="table table-striped" style="table-layout: fixed">
					<thead>
						<tr>
							<th style="width:130px">Customer Code</th>
							<th style="width:150px">Customer Name</th>
							<th style="width:240px">Address Line 1</th>
							<th style="width:150px">Address Line 2</th>
							<th style="width:100px">PIN Code</th>
							<th style="width:270px">Email</th>
							<th style="width:150px">Contact Number</th>
							<th style="width:200px">Primary Contact Person</th>
							<th style="width:150px">Record Status</th>
							<th style="width:100px">Active / Inactive</th>
							<th style="width:100px">Created By</th>
							<th style="width:100px">Create Date</th>
							<th style="width:100px">Modified By</th>
							<th style="width:100px">Modified Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="record">
							<tr>
								<td style="width:130px">${record.getCustomerCode()}</td>
								<td style="width:150px">${record.getCustomerName()}</td>
								<td style="width:240px">${record.getAddress1()}</td>
								<td style="width:150px">${record.getAddress2()}</td>
								<td style="width:100px">${record.getPincode()}</td>
								<td style="width:270px">${record.getEmail()}</td>
								<td style="width:150px">${record.getContactNumber()}</td>
								<td style="width:200px">${record.getPrimaryContactPerson()}</td>
								<td style="width:150px">${record.getRecordStatus()}</td>
								<td style="width:100px">${record.getFlag()}</td>
								<td style="width:100px">${record.getCreatedBy()}</td>
								<td style="width:100px">${record.getCreateDate()}</td>
								<td style="width:100px">${record.getModifiedBy()}</td>
								<td style="width:100px">${record.getModifiedDate()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	<br>
	<br>

	<!-- Script -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>