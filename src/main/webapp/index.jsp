<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Entity.Savings"%>
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

		<div class="modal fade" id="savingModal" role="dialog">
			<div class="modal-dialog modal-xl">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">

						<h4 class="modal-title">Your Savings Status</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<form method="post">
						<div class="modal-body">
							<strong>Here is all your current savings</strong><br> <small>You
								can take your Saving back before the maturity day you provide in
								the Saving Request form. By doing that, your current saving is
								complele, and it will be removed from your account.</small>
							<table class="table" id="savingTable">
								<thead>
									<tr>
										<th scope="col">Savings ID</th>
										<th scope="col">Savings Interest</th>
										<th scope="col">Start Date</th>
										<th scope="col">Maturity Date</th>
										<th scope="col">Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${requestScope.saving }">
										<tr>
											<td>${item.savingsId}</td>
											<td>${item.savingsInterest}</td>
											<td>${item.savingsStartDate }</td>
											<td>${item.savingsMaturityDay }</td>
											<td>$ ${item.savingsAmount }</td>
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
							<h6>Starting Amount: <span id="startingAmount"></span>$</h6>
							<h6>Current Amount (If you take out now): <span id="currentAmount"></span>$</h6>
							<h6>Estimated Final Amount: <span id="finalAmount"></span>$</h6>
							<strong>Here is your Total Savings progress</strong><br> <small>Keep
								savings and become our VIP Customer</small>
							<div class="progress">
								<div
									class="progress-bar progress-bar-striped progress-bar-animated"
									role="progressbar" aria-valuenow="75" aria-valuemin="0"
									aria-valuemax="100"
									style="width: ${requestScope.account.getPercentageToVip()}%"></div>
							</div>
							<small>Your progress: ${requestScope.account.accountTotalSavings } / 100.000$</small>
						</div>
						<div class="modal-footer">			
								<button type="button" id="takeSaving" name="action" value="reqTakeSaving"
									class="btn btn-success" data-dismiss="modal" disabled>Take
									out This Saving</button>
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

								<label for="savingAmount">Amount of Savings</label> <input
									type="number" class="form-control" id="savingAmount"
									placeholder="Enter amount of money you want to Make a savings... "
									name="reqAmount"> <label for="maturityDay">Maturity
									Day</label>
									
									 <select name="monthOption" class="form-select" aria-label="Default select example" required>
										<option value="" disabled selected>Saving Terms</option>
										<option value="3">3 Months</option>
										<option value="6">6 Months</option>
										<option value="9">9 Months</option>
									</select> 
								
								<small id="emailHelp" class="form-text text-muted">The
									maturity date is the earliest date that you can withdraw the
									amount of money you put on your savings, including interest.</small> <br>
								<br> <small>Please note that: The interest rate in our bank:</small><br> 
									<small>- 3% Interest Rate for Normal Customer - 4% For VIP Customer in 3-month term</small><br> 
									<small>- 6% Interest Rate for Normal Customer - 7% For VIP Customer in 6-month term</small><br>
									<small>- 9% Interest Rate for Normal Customer - 10.5% For VIP Customer in 9-month term</small>
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

		<div class="modal fade" id="myUnlockModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Unlock account Request</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>

					</div>
					<form method="post">
						<div class="modal-body">
							<div class="form-group">

								<label for="username">Your Username</label> <input type="text"
									class="form-control" id="username" name="username"> <label
									for="email">Enter your email</label> <input type="text"
									class="form-control" id="email" name="email"> <label
									for="reason">Reason</label> <input type="text"
									class="form-control" id="reason" name="reason"> <small
									id="emailHelp" class="form-text text-muted">Please
									enter your personal information here. We'll consider your case
									and reply you as soon as possible.</small> <br> <br> <small>Please
									note that: The more information you provide in Reason field,
									the more chance you will get your account unlocked.</small>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" name="action" value="unlockSubmit"
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
		const startingAmount = document.getElementById('startingAmount');
		const currentAmount = document.getElementById('currentAmount');
		const finalAmount = document.getElementById('finalAmount');
		
        $(document).ready(function() {
            $('#savingTable tr').click(function() {
                var savingId = $(this).find('td:first').text();
                var button = document.getElementById('takeSaving');
                $.ajax({
                    type: 'POST',
                    url: '/BankingSystem/saving-lookup', 
                    data: { savingId },
                    success: function(response) {                    	
                      startingAmount.textContent = response[0];
                      currentAmount.textContent = response[1];
                      finalAmount.textContent = response[2];
                      button.disabled = false;
                    },
                    error: function(xhr, status, error) {
                      console.error(xhr.responseText);
                    }
                  });
                
            });
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
	
	<script>
		$(document).ready(function() {
		  $('#savingTable tr').click(function() {
		    var savingId = $(this).find('td:first').text();
		    $('#takeSaving').data('savingId', savingId);
		  });
		});
		
		$('#takeSaving').click(function() {
			var savingId = $(this).data('savingId');
			
			$.ajax({
			    type: 'POST',
			    url: '/BankingSystem/takeout-saving',
			    data: { savingId },
			    success: function(response) {
			    	 if (response === "success") {
			    	      // Redirect to another page
			    	      toastr.success("Taking out Savings completed. Please check your Account balance.", "Success");
			    	      setTimeout(function() {
			    	    	  window.location.href = "/BankingSystem/homepage";
			    	    	}, 3000);
			    	      
			    	    } else {
			    	    	
			    	    }
			    },
			    error: function(xhr, status, error) {
			      console.log("Error occurred: " + error);
			    }
			  });
			
		});
	
	</script>
	<%
	// Retrieve the session attribute
	String itemValue = (String) session.getAttribute("successReq");
	session.removeAttribute("successReq");
	String itemValue2 = (String) session.getAttribute("failReq");
	session.removeAttribute("failReq");
	%>

	<script>
  // Access the session attribute value in JavaScript
  	var item = '<%=itemValue%>';
	var item2 = '<%=itemValue2%>';
		// Use the item in JavaScript code
		if (item !== null && item !== "null") {
			alert(item);
		}
		else if (item2 !== null && item2 !== "null") {
			alert(item2);
		}
		
	</script>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>	
</body>
</html>