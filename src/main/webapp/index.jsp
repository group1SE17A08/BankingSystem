<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@include file="include/head.jsp"%>
<body data-bs-spy="scroll" data-bs-target="#navbarSupportedContent">

	<c:if test="${empty sessionScope.user}">
		<%@include file="include/header.jsp"%>
	</c:if>

	<c:if test="${not empty sessionScope.user}">
		<%@include file="include/logged-in-header.jsp"%>
	</c:if>
	<!-- Hero Section  -->
	<div class="mid" style="z-index: 1">
		<video autoplay muted loop>
			<source class="embed-responsive" src="assets/videos/1.mp4"
				type="video/mp4" />
		</video>
		<div class="hero text-center">
			<h2 class="text-light display-4 fw-bold">Virtual Banking Made
				Easy</h2>
			<p class="text-light mx-auto">A virtual bank is a bank that
				offers its services only via the Internet, email, and other
				electronic means, often including telephone, online chat, and mobile
				check deposit. A virtual bank has no branch network.</p>
			<a class="text-dark" href="#">Get Started</a>
		</div>
	</div>
	<div class="container">
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">

						<h4 class="modal-title">Lock your account</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<form method="post">
						<div class="modal-body">
							<div class="form-group">
								<label for="exampleInputEmail1">Request Content</label> <input
									type="text" class="form-control" id="exampleInputEmail1"
									placeholder="Give your reason for the request... "
									name="reqReason"> <small id="emailHelp"
									class="form-text text-muted">We'll consider your
									request as soon as possible. The reply email will be sent after
									a few days. Please check this mail for further information.</small>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" name="action" value="reqSubmit"
								class="btn btn-success" data-dismiss="modal">Submit</button>
						</div>
					</form>
				</div>

			</div>
		</div>

		<div class="modal fade" id="myModal2" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Savings Request</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>

					</div>
					<form method="post">
						<div class="modal-body">
							<div class="form-group">

								<label for="savingAmount">Request Content</label> <input
									type="number" class="form-control" id="savingAmount"
									placeholder="Enter amount of money you want to Make a savings... "
									name="reqAmount"> <label for="maturityDay">Maturity
									Day</label> <input type="date" class="form-control" id="maturityDay"
									name="maturityDay"> <small id="emailHelp"
									class="form-text text-muted">The maturity date is the
									earliest date that you can withdraw the amount of money you put
									on your savings, including interest.</small> <br> <br> <small>Please
									note that: The interest rate is fixed in our bank.</small><br> <small>-
									7% API for normal customer.</small><br> <small>- Up to 10%
									API for our VIP customer.</small>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" name="action" value="reqSubmit"
								class="btn btn-success" data-dismiss="modal">Submit</button>
						</div>
					</form>
				</div>

			</div>
		</div>


	</div>
	<!-- About Section  -->
	<section id="about" class="about py-3">
		<div class="row align-items-center container my-3 mx-auto">
			<!-- Left Side Content Area  -->
			<div class="text col-lg-6 col-md-6 col-12 pt-5 pb-5">
				<h6>PREMIUM BANK</h6>
				<h2>Unlimited Transaction with zero fees</h2>
				<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit.
					Dolorum consequatur non delectus ad quasi. Consectetur
					necessitatibus alias eveniet corporis hic, expedita dolore quo eos
					tempore!</p>
				<a href="#">Learn More</a>
			</div>
			<!-- Right Side Image Area  -->
			<div class="img col-lg-6 col-md-6 col-12 pt-5 pb-5">
				<img class="img-fluid" src="assets/images/1.svg" />
			</div>
		</div>
	</section>

	<!-- Discover Section  -->
	<section id="discover" class="discover py-3">
		<div class="row align-items-center container my-3 mx-auto">
			<!-- Left Side Content Area  -->
			<div class="img col-lg-6 col-md-6 col-12 pt-5 pb-5">
				<img class="img-fluid" src="assets/images/2.png" />
			</div>
			<!-- Right Side Image Area  -->
			<div class="text col-lg-6 col-md-6 col-12 pt-5 pb-5">
				<h6>UNLIMITED ACCESS</h6>
				<h2>Login to your account at any time</h2>
				<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit.
					Dolorum consequatur non delectus ad quasi. Consectetur
					necessitatibus alias eveniet corporis hic, expedita dolore quo eos
					tempore!</p>
				<a href="#">Learn More</a>
			</div>
		</div>
	</section>

	<!-- Service Section  -->
	<section id="service" class="service py-2 pb-5">
		<div class="col mx-auto align-items-center my-2">
			<!-- Heading  -->
			<div class="heading text-center pt-5">
				<h2 class="fw-bolder pb-4 text-light">Our Service</h2>
			</div>
			<div
				class="row mx-auto justify-content-center align-items-center text-center container">
				<!-- First Card  -->
				<div class="card col-lg-3 col-md-3 col-12 m-2">
					<img class="img-fluid w-75" src="assets/images/1.svg" />
					<h5 class="fw-bold pt-4">Reduce Expenses</h5>
					<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
						Aspernatur, at exercitationem beatae hic doloremque ea.</p>
				</div>
				<!-- Second Card  -->
				<div class="card col-lg-3 col-md-3 col-12 m-2">
					<img class="img-fluid w-75" src="assets/images/2.png" />
					<h5 class="fw-bold pt-4">Virtual Offices</h5>
					<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
						Aspernatur, at exercitationem beatae hic doloremque ea.</p>
				</div>
				<!-- Third Card  -->
				<div class="card col-lg-3 col-md-3 col-12 m-2">
					<img class="img-fluid w-75" src="assets/images/3.png" />
					<h5 class="fw-bold pt-4">Premium Benefits</h5>
					<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
						Aspernatur, at exercitationem beatae hic doloremque ea.</p>
				</div>
			</div>
		</div>
	</section>

	<!-- Sign Up Section  -->
	<section id="signup" class="signup py-3">
		<div class="row align-items-center container my-3 mx-auto">
			<!-- Left Side Content Area  -->
			<div class="text col-lg-6 col-md-6 col-12 pt-5 pb-5">
				<h6>JOIN OUR TEAM</h6>
				<h2>Creating an account is extremely easy</h2>
				<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit.
					Dolorum consequatur non delectus ad quasi. Consectetur
					necessitatibus alias eveniet corporis hic, expedita dolore quo eos
					tempore!</p>
				<a href="#">Start Now</a>
			</div>
			<!-- Right Side Image Area  -->
			<div class="img col-lg-6 col-md-6 col-12 pt-5 pb-5">
				<img class="img-fluid" src="assets/images/4.png" />
			</div>
		</div>
	</section>

	<!-- Footer Section  -->
	<%@include file="include/footer.jsp"%>

	<!-- Bootstrap JavaScript  -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$('.dropdown-toggle').dropdown();
		});
	</script>

	<script>
		// Get the input element
		const dateInput = document.getElementById('maturityDay');

		// Add an event listener for input changes
		dateInput.addEventListener('input', function() {
			// Get the entered date value
			const enteredDate = new Date(this.value);

			// Get the current date
			const currentDate = new Date();

			// Compare the entered date with the current date
			if (enteredDate < currentDate) {
				// Reset the input value to an empty string
				this.value = '';

				// Display an error message or perform any other validation feedback
				alert('Invalid date. Please enter a date in the future.');
			}
		});
	</script>

	<%
	// Retrieve the session attribute
	String itemValue = (String) session.getAttribute("successReq");
	session.removeAttribute("successReq");
	%>

	<script>
  // Access the session attribute value in JavaScript
  	var item = '<%=itemValue%>';

		// Use the item in JavaScript code
		if (item !== null && item !== "null") {
			alert(item);
		}
	</script>
</body>
</html>