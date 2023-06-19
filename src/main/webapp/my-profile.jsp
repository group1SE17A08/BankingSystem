<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include/head.jsp"%>
<title>My Profile</title>
</head>
<body>
	<%@include file="include/logo.jsp"%>
	<section style="background-color: #eee;">
		<div class="container py-5">


			<div class="row">
				<div class="col-lg-4">
					<div class="card mb-4">
						<div class="card-body text-center">
							<img
								src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
								alt="avatar" class="rounded-circle img-fluid"
								style="width: 150px;">
							<h5 class="my-3">${sessionScope.user.customerName }</h5>
							<p class="text-muted mb-1">ID: ${sessionScope.user.customerId }</p>
							<p class="text-muted mb-4">${sessionScope.user.customerAddress }</p>

							<form method="post">

								<div class="d-flex justify-content-center mb-2">
									<button type="button" id="changeButton" class="btn btn-danger">Request a
										Personal Information Change</button>
								</div>

							</form>

						</div>
					</div>

				</div>
				<div class="col-lg-8">
				<form method="post">
					<div class="card mb-4">
						<div class="card-body">
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Full Name</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0 changableP">${sessionScope.user.customerName }</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Email</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0 changableP">${sessionScope.user.customerEmail }</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Phone</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0 changableP">${sessionScope.user.customerPhoneNumber }</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Date of Birth</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0 changableP">${sessionScope.user.customerDob }</p>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-3">
									<p class="mb-0">Address</p>
								</div>
								<div class="col-sm-9">
									<p class="text-muted mb-0 changableP">${sessionScope.user.customerAddress }</p>
								</div>
							</div>
						</div>
					</div>

					<c:if test="${not empty sessionScope.requestSuccess }">
					<h5 style="color: green">Successful Request! Please wait the admin to approve. Click <a href="homepage">here</a> to go to homepage</h5>
					<%
					session.removeAttribute("requestSuccess");
					%>
					</c:if>
					
					<div id="buttonContainer" style="display: none">
						<button type="submit" name="action" value="submitRequest"
							class="btn btn-success" style="float: right">Submit</button>
					</div>
				</form>
				</div>
			</div>
		</div>
	</section>
	<div style="position: fixed; bottom: 0; width: 100%">
		<%@include file="include/footer.jsp"%>
	</div>
	<script>
	function changeParagraphsToInputs() {
		  var paragraphs = document.querySelectorAll('p.changableP');

		  paragraphs.forEach(function(paragraph) {
		    var input = document.createElement('input');
		    input.placeholder = paragraph.textContent;
		    input.classList.add("form-control");
		    input.setAttribute('required', 'required');
		    input.setAttribute('name', 'inputInfor[]');
		    paragraph.parentNode.replaceChild(input, paragraph);
		  });

		  var container = document.getElementById('buttonContainer');
		  container.style.display = 'block';
		}

		var changeButton = document.getElementById('changeButton');
		changeButton.addEventListener('click', changeParagraphsToInputs);

	</script>
</html>