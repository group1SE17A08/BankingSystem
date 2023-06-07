<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include/head.jsp"%>
<title>Invoice Maker</title>
</head>
<style>
@import
	url('https://fonts.googleapis.com/css?family=Open+Sans|Rock+Salt|Shadows+Into+Light|Cedarville+Cursive')
	;

.sig2 {
	font-family: 'Rock Salt', cursive;
}

.papers {
	padding: 20px;
	position: relative;
	background: #fff;
	border: 1px solid #ccc;
	-webkit-box-shadow: 1px 1px 0 rgba(0, 0, 0, .1), 3px 3px 0 #fff, 4px 4px
		0 rgba(0, 0, 0, .125), 6px 6px 0 #fff, 7px 7px 0 rgba(0, 0, 0, .15);
	-moz-box-shadow: 1px 1px 0 rgba(0, 0, 0, .1), 3px 3px 0 #fff, 4px 4px 0
		rgba(0, 0, 0, .125), 6px 6px 0 #fff, 7px 7px 0 rgba(0, 0, 0, .15);
	box-shadow: 1px 1px 0 rgba(0, 0, 0, .1), 3px 3px 0 #fff, 4px 4px 0
		rgba(0, 0, 0, .125), 6px 6px 0 #fff, 7px 7px 0 rgba(0, 0, 0, .15);
}
</style>
<body>
	<%@include file="include/logo.jsp"%>

	<div class="container-fluid" style="margin-top: 2%">
		<div class="row">
			<div class="col-9" style="color: black">
				<h4 style="text-align: center">Invoice Generator</h4>
				<form method="post">
					<div class="papers" style="height: 100%">
						<div class="row">
							<div class="col-3">
								<h2>VirtualbanK</h2>
							</div>

							<div class="col-6">
								<img src="images/invoiceIcon.png"
									style="display: flex; justify-content: center">
							</div>
							<div class="col-3">
								<h2>Online Invoice</h2>
								<div>Auto-generated ID</div>
								<div class="input-group" style="width: 90%">
									<span class="input-group-text">ID</span> <input
										class="form-control ng-pristine ng-valid ng-touched"
										value="${sessionScope.creatingBill.billId }" readonly>
								</div>

							</div>
						</div>

						<div class="row">
							<div class="contact to col-4">
								<div class="field">
									<label for="billTo">Bill To</label>
								</div>
								<div class="value">
									<div class="expandingText" style="position: relative;">
										<input id="billTo" name="billTo"
											class="form-control ng-pristine ng-untouched expanding-init ng-invalid ng-invalid-required"
											placeholder="Bank account number required" required
											style="position: absolute; resize: none;"></input>

										<pre class="textareaClone"
											style="visibility: hidden; border: 0.8px solid; white-space: pre-wrap; line-height: 20px; text-decoration: none solid rgb(49, 53, 56); font-family: NeueHaasUnica, Helvetica, Arial, sans-serif; text-align: start; overflow-wrap: break-word; padding: 6px 12px; margin-bottom: 0px;">
										 
									</pre>

									</div>
								</div>
								<div>
									Bill receiver name: <span id="receiverName"></span>
								</div>
								<div class="field">
									<label for="paidTo">Paid To</label>
								</div>
								<div class="value">
									<div class="expandingText" style="position: relative;">
										<textarea id="paidTo" name="paidTo"
											class="form-control ng-pristine ng-untouched expanding-init ng-invalid ng-invalid-required"
											placeholder="${sessionScope.user.customerName } - ${sessionScope.currentAcc.accountNumber}"
											readonly
											style="position: absolute; height: 100%; resize: none;"></textarea>
										<pre class="textareaClone"
											style="visibility: hidden; border: 0.8px solid; white-space: pre-wrap; line-height: 20px; text-decoration: none solid rgb(49, 53, 56); font-family: NeueHaasUnica, Helvetica, Arial, sans-serif; text-align: start; overflow-wrap: break-word; padding: 6px 12px; margin-bottom: 0px;">
										 <div></div>
									</pre>
									</div>
								</div>
								<div class="input-group addon-input">

									<div class="input-group-addon">
										<label for="dueDate">Due Date</label> <input type="date"
											name="dueDate"
											class="form-control datepicker date ng-pristine ng-untouched ng-valid hasDatepicker"
											id="dueDate">
									</div>
								</div>
							</div>



							<div class="col-8">
								<table class="table border-bottom border-gray-200 mt-3">
									<thead>
										<tr>
											<th scope="col"
												class="fs-sm text-dark text-uppercase-bold-sm px-0">Content</th>
											<th scope="col"
												class="fs-sm text-dark text-uppercase-bold-sm text-end px-0">Amount</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="px-0"><input type="text" name="billContent"
												placeholder="Bill content"></td>
											<td class="text-end px-0"><input type="number"
												name="billAmount" placeholder="Amount of money"></td>
										</tr>
									</tbody>
								</table>
								<br>
								<div class="row">
									<div class="col-6">
										<h5 style="text-align: center">Signature</h5>
										<br> <br>
										<h5 class="sig2" style="text-align: center">
											${sessionScope.user.customerName }</h5>
									</div>

									<div class="col-6">
										<button style="float: right; margin-top: 35%"
											class="btn btn-success" type="submit" name="action"
											value="submitBill">Create Invoice</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<br>
				<c:if test="${not empty sessionScope.generateInvErr }">
					<h5 style="color: red">${ sessionScope.generateInvErr}</h5>
					<%
					session.removeAttribute("generateInvErr");
					%>
				</c:if>
				<c:if test="${not empty sessionScope.successfulGenerateInv }">
					<h5 style="color: green">
						Successful Genarate Invoice! Click <a href="homepage">here</a> to
						go to homepage
					</h5>
					<%
					session.removeAttribute("successfulGenerateInv");
					%>
				</c:if>

			</div>

			<div class="col-3" style="color: blue">
				<table class="table border-bottom border-gray-200"
					style="height: 45%">
					<thead>
						<tr>
							<th scope="col"
								class="fs-sm text-dark text-uppercase-bold-sm px-0">Your
								Invoice</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.listBillCreated }" var="bill">
							<tr>
								<td class="px-0">Bill ID: <a href="view-invoice?billId=${bill.billId }&isMyBill=true">${bill.billId }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<table class="table border-bottom border-gray-200">
					<thead>
						<tr>
							<th scope="col"
								class="fs-sm text-dark text-uppercase-bold-sm px-0">
								Invoice you have to pay</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.listBillPaid }" var="bill">
							<tr>
								<td class="px-0">Bill ID: <a href="view-invoice?billId=${bill.billId }&isMyBill=false">${bill.billId }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div style="position: fixed; bottom: 0; width: 100%">
		<%@include file="include/footer.jsp"%>
	</div>
	<script>
    const accountNumberInput = document.getElementById('billTo');
    const receiver = document.getElementById('receiverName');

    accountNumberInput.addEventListener('input', () => {
      const accountNumber = accountNumberInput.value;

      $.ajax({
        type: 'POST',
        url: '/BankingSystem/user-lookup', // Update with the actual servlet URL
        data: { accountNumber },
        success: function(response) {
          receiver.textContent = response;
        },
        error: function(xhr, status, error) {
          console.error(xhr.responseText);
        }
      });
    });
  </script>

	<script>
  // Get the input element
  const dateInput = document.getElementById('dueDate');

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
</body>
</html>