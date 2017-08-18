<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Welcome</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<c:set var="temp1" value="authcorrect"></c:set>
	<c:set var="temp2" value="authfalse"></c:set>
	<c:set var="temp3" value="rejectcorrect"></c:set>
	<c:set var="temp4" value="rejectfalse"></c:set>
	<c:if test="${check.equals(temp1)}">
		<br>
		<div  align="center"><label for="usr">Authorization Successful!!</label></div>
	</c:if>
	<c:if test="${check.equals(temp2)}">
		<br>
		<div  align="center"><label for="usr">Authorization failed.</label></div>
	</c:if>
	<c:if test="${check.equals(temp3)}">
		<br>
		<div  align="center"><label for="usr">Reject Successful!!</label></div>
	</c:if>
	<c:if test="${check.equals(temp4)}">
		<br>
		<div  align="center"><label for="usr">Reject failed.</label></div>
	</c:if>
	<script src="jsFunctions.js"></script>
	<div style="float:right">
		<h3>
			<a href="CheckerController"><button type="submit" style="font-weight: 700; font-size: 13px" class="btn btn-success">View Master Table</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</h3>
	</div>
	<div style="float:right">
		<h3>
			<a href="Logout.jsp"><button type="submit" style="font-weight: 700; font-size: 13px" class="btn btn-danger">Logout</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</h3>
	</div>
	<!-- Page Header -->
	<div class="page-header container">
		<h2>
			<strong>CHECKER'S HOMEPAGE</strong>
		</h2>
	</div>
	<br>
	<br>
	<form action="CheckerController" method="post">
		<input type="hidden" value="Nothing" name="recordRow" id="recordRow"/>
		<div align="center">
			<table class="table table-striped" style="table-layout: fixed">
				<thead>
					<tr>
						<th style="width:150px">Decision</th>
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
							<td style="width: 150px"><div align="center">
									<input onclick="checkOperation(this)" type="submit" class="btn-xs btn-success" id="${record.getCustomerCode()}"
										style="font-weight: 700; font-size: 13px" value="Authorize"
										name="operation">
									<input onclick="checkOperation(this)" type="submit" class="btn-xs btn-warning" id="${record.getCustomerCode()}"
										style="font-weight: 700; font-size: 13px" value="Reject"
										name="operation">
								</div></td>
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
	</form>
</body>
</html>