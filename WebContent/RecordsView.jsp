<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Record Viewer</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<script src="jsFunctions.js"></script>
	<div style="float:right">
		<h3>
			<a href="MakerView.jsp"><button type="submit" class="btn btn-success">Go back?</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</h3>
	</div>
	<!-- Page Header -->
	<div class="page-header container">
		<h2>
		<c:set var="v1" value="Specific Record"></c:set>
		<c:set var="v2" value="All Records"></c:set>
			<c:if test="${view.equals(v1)}">
				<strong>Specific Record View</strong>
			</c:if>
			<c:if test="${view.equals(v2)}">
				<strong>All Records View</strong>
			</c:if>
		</h2>
	</div>
	<div class="container">
		<c:if test="${view.equals(v1)}">
			<br>
			<c:forEach items="${list}" var="record">
			<form class="form-horizontal">
			<div class="form-group"><label for="usr" class="col-sm-3" >Customer Code</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getCustomerCode()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Customer Name</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getCustomerName()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Address Line 1</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getAddress1()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Address Line 2</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getAddress2()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >PIN Code</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getPincode()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Email</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getEmail()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Contact Number</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getContactNumber()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Primary Contact Person</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getPrimaryContactPerson()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Record Status</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getRecordStatus()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Active / Inactive</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getFlag()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Created By</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getCreatedBy()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Create Date</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getCreateDate()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Modified By</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getModifiedBy()}</label></div>
			<div class="form-group"><label for="usr" class="col-sm-3" >Modified Date</label>&nbsp;&nbsp;<label for="usr" class="col-sm-4"  style="color:red">: &nbsp;&nbsp;${record.getModifiedDate()}</label></div>
			</form>
			</c:forEach>
		</c:if>
		<c:if test="${view.equals(v2)}">
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
		</c:if>
		
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