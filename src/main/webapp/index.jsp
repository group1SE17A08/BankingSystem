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
	<div class="mid">
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

	<script>
		$(document).ready(function() {
			$('.dropdown-toggle').dropdown();
		});
	</script>
</body>
</html>