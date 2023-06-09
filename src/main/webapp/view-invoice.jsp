<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include/head.jsp"%>
<title>View Invoice</title>
</head>
<style>
body {
	background: #87CEFA;
}

.card-footer-btn {
	display: flex;
	align-items: center;
	border-top-left-radius: 0 !important;
	border-top-right-radius: 0 !important;
}

.text-uppercase-bold-sm {
	text-transform: uppercase !important;
	font-weight: 500 !important;
	letter-spacing: 2px !important;
	font-size: .85rem !important;
}

.hover-lift-light {
	transition: box-shadow .25s ease, transform .25s ease, color .25s ease,
		background-color .15s ease-in;
}

.justify-content-center {
	justify-content: center !important;
}

.btn-group-lg>.btn, .btn-lg {
	padding: 0.8rem 1.85rem;
	font-size: 1.1rem;
	border-radius: 0.3rem;
}

.btn-dark {
	color: #fff;
	background-color: #1e2e50;
	border-color: #1e2e50;
}

.card {
	position: relative;
	display: flex;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid rgba(30, 46, 80, .09);
	border-radius: 0.25rem;
	box-shadow: 0 20px 27px 0 rgb(0 0 0/ 5%);
}

.p-5 {
	padding: 3rem !important;
}

.card-body {
	flex: 1 1 auto;
	padding: 1.5rem 1.5rem;
}

tbody, td, tfoot, th, thead, tr {
	border-color: inherit;
	border-style: solid;
	border-width: 0;
}

.table td, .table th {
	border-bottom: 0;
	border-top: 1px solid #edf2f9;
}

.table>:not(caption)>*>* {
	padding: 1rem 1rem;
	background-color: var(--bs-table-bg);
	border-bottom-width: 1px;
	box-shadow: inset 0 0 0 9999px var(--bs-table-accent-bg);
}

.px-0 {
	padding-right: 0 !important;
	padding-left: 0 !important;
}

.table thead th, tbody td, tbody th {
	vertical-align: middle;
}

tbody, td, tfoot, th, thead, tr {
	border-color: inherit;
	border-style: solid;
	border-width: 0;
}

.mt-5 {
	margin-top: 3rem !important;
}

.icon-circle[class*=text-] [fill]:not([fill=none]), .icon-circle[class*=text-] svg:not([fill=none]),
	.svg-icon[class*=text-] [fill]:not([fill=none]), .svg-icon[class*=text-] svg:not([fill=none])
	{
	fill: currentColor !important;
}

.svg-icon>svg {
	width: 1.45rem;
	height: 1.45rem;
}
</style>
<body>
	<%@include file="include/logo.jsp"%>
	<div class="container mt-6 mb-7">
		<div class="row justify-content-center">
			<div class="col-lg-12 col-xl-7">
				<div class="card">
					<div class="card-body p-5">
						<h2>Hey ${requestScope.billTo.customerName },</h2>
						<p class="fs-sm">
							This is the receipt for a payment of <strong>$
								${requestScope.currentBill.billAmount }</strong> (USD) you made to our
							services.
						</p>

						<div class="border-top border-gray-200 pt-4 mt-4">
							<div class="row">
								<div class="col-md-6">
									<div class="text-muted mb-2">Invoice ID.</div>
									<strong># ${requestScope.currentBill.billId }</strong>
								</div>
								<div class="col-md-6 text-md-end">
									<div class="text-muted mb-2">Invoice Due Date</div>
									<strong>${requestScope.currentBill.billDueDate }</strong>
								</div>
							</div>
						</div>

						<div class="border-top border-gray-200 mt-4 py-4">
							<div class="row">
								<div class="col-md-6">
									<div class="text-muted mb-2">Client</div>
									<strong> ${requestScope.billTo.customerName } </strong>
									<p class="fs-sm">
										${requestScope.billTo.customerAddress } <br> <a href="#!"
											class="text-purple">${requestScope.billTo.customerEmail }
										</a>
									</p>
								</div>
								<div class="col-md-6 text-md-end">
									<div class="text-muted mb-2">Payment To</div>
									<strong> ${requestScope.billFrom.customerName } </strong>
									<p class="fs-sm">
										${requestScope.billFrom.customerAddress } <br> <a
											href="#!" class="text-purple">${requestScope.billFrom.customerEmail }
										</a>
									</p>
								</div>
							</div>
						</div>

						<table class="table border-bottom border-gray-200 mt-3">
							<thead>
								<tr>
									<th scope="col"
										class="fs-sm text-dark text-uppercase-bold-sm px-0">Description</th>
									<th scope="col"
										class="fs-sm text-dark text-uppercase-bold-sm text-end px-0">Amount</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${requestScope.currentBill.billDetails}" var="entry">
									<tr>
										<td class="px-0">${entry.key }</td>
										<td class="text-end px-0">$ ${entry.value }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div class="d-flex justify-content-end mt-3">
							<h5 class="me-3">Total:</h5>
							<h5 class="text-success">$ ${requestScope.currentBill.billAmount } USD</h5>
						</div>
					</div>
				</div>
				<c:if test="${not empty displayPayButton }">
					<a href="#" onclick="handleConfirmation()"
						class="btn btn-dark btn-lg card-footer-btn justify-content-center text-uppercase-bold-sm hover-lift-light">
						<span class="svg-icon text-white me-2"> <svg
								xmlns="http://www.w3.org/2000/svg" width="512" height="512"
								viewBox="0 0 512 512">
								<title>ionicons-v5-g</title><path
									d="M336,208V113a80,80,0,0,0-160,0v95"
									style="fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px"></path>
								<rect x="96" y="208" width="320" height="272" rx="48" ry="48"
									style="fill:none;stroke:#000;stroke-linecap:round;stroke-linejoin:round;stroke-width:32px"></rect></svg>
					</span> Pay Now
					</a>
				</c:if>
			</div>
		</div>
	</div>

	<script>
  function handleConfirmation() {
    if (confirm('Are you sure you want to proceed?')) {
      // User clicked "OK"
      // Perform the desired action here
      var billId = '${requestScope.currentBill.billId }'; // Replace with the actual bill ID
      console.log(billId);
      // Send AJAX request to the servlet
      
      $.ajax({
    	  url: '/BankingSystem/confirm-payment',
    	  method: 'POST',
    	  data: { billId },
    	  success: function(response) {
    	    window.location.href = '/BankingSystem/homepage';
    	    alert("Invoice payment complete! Thanks for using our services. You are now redirected to Homepage")
    	  },
    	  error: function(xhr, status, error) {
    	    // Handle any error that occurred during the request
    	  }
    	});
    } else {
      
      console.log('Canceled');
    }
  }
</script>

	<%@include file="include/footer.jsp"%>
</body>
</html>