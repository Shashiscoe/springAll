<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>
<title>Spring Data JPA</title>
<body>
	
	<div class="container">
		<br>
		<h2>Add Customer</h2>
		<br>
		<div class="panel-body">
			<form:form action="saveCustomer" cssClass="form-horizontal"
				method="post" modelAttribute="customer">

				<!-- need to associate this data with customer id -->
				<form:hidden path="id" />

				<div class="form-group">
					<label for="firstname" class="col-md-3 control-label">First
						Name</label>
					<div class="col-md-6">
						<form:input path="firstName" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="lastname" class="col-md-3 control-label">Last
						Name</label>
					<div class="col-md-6">
						<form:input path="lastName" cssClass="form-control" />
					</div>
				</div>

				<div class="form-group">
					<label for="email" class="col-md-3 control-label">Email</label>
					<div class="col-md-6">
						<form:input path="email" cssClass="form-control" />
					</div>
				</div>

				<div class="form-group">
					<!-- Button -->
					<div class="col-md-offset-3 col-md-9">
						<form:button class="btn btn-primary">Submit</form:button>
					</div>
				</div>

			</form:form>
		</div>

	</div>
</body>
</html>