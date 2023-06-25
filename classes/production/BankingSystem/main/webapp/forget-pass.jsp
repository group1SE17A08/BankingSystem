<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
</head>
<body style="background-image: linear-gradient(to right, #7F7FD5, #86A8E7, #91EAE4)">
	<div class="card text-center mx-auto" style="width: 500px; height: 100%; margin-top: 5%">
		<div class="card-header h5 text-white bg-primary">Password Reset</div>
		<div class="card-body px-5">
			<p class="card-text py-2">Enter your email address and we'll send
				you an email with instructions to reset your password.</p>
			<span style="color: red">${sessionScope.noEmail }</span>
			<form id="resetForm" action="reset_password" method="post">
				<div class="form-outline">
					<input type="text" id="typeEmail"  name="email" class="form-control my-3" required /> <label
						class="form-label" for="typeEmail" >Email input</label>
				</div>
				<button class="rounded-pill btn btn-primary" type="submit">Send me new password</button>
			</form>
			<div class="d-flex justify-content-between mt-4">
				<a class="" href="sign-in.jsp">Login</a> <a class="" href="sign-up.jsp">Register</a>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$("#resetForm").validate({
				rules : {
					email : {
						required : true,
						email : true
					}
				},

				messages : {
					email : {
						required : "Please enter email",
						email : "Please enter a valid email address"
					}
				}
			});

		});
	</script>
</body>
</html>