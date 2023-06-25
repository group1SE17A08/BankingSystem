<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="fonts/CREDC___.ttf" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Transfer Money</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />

<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<%@include file="include/head.jsp"%>
</head>
<style>
@font-face {
	font-family: 'Credit';
	src: url(fonts/CREDC___.ttf) format('truetype');
}
</style>
<body
	style="background-image: linear-gradient(to right, #fc5c7d, #6a82fb); min-height: 100vh; display: flex; flex-direction: column">
	<%@include file="include/logo.jsp"%>
	<div class="container-fluid">

		<div class="row">
			<div class="col-4">
				<div class="card"
					style="margin-top: 10%; left: 10%; height: 40%; width: 85%; background-color: #373737;">
					<h4 style="margin-top: 10px; margin-left: 10px; color: white">VirtualbanK</h4>
					<div class="card"
						style="margin-top: 5px; margin-left: 10px; width: 50px; height: 32px; background-color: #CA9D36"></div>
					<div
						style="margin-left: 10px; margin-top: 5px; font-size: 21px; color: white; letter-spacing: 2px; font-family: 'Credit', sans-serif;"
						data-text="4716">${sessionScope.accNumFormatted }</div>
					<img
						style="position: absolute; bottom: 5%; right: 5%; width: 70px;"
						src="images/mastercard.png" />
					<h5 style="position: absolute; bottom: 13%; margin-left: 10px; color: white">
						${sessionScope.user.customerName }
						</h5>
				</div>
				<br>
				<h5 style="margin-left: 10%">Current balance: ${sessionScope.currentAcc.accountBalance } </h5>
				<h5 style="margin-left: 10%">Bank Company: VirtualbanK</h5>
				<br>
				<div class="card" style="margin-left: 10%;height: 20%; width: 85%; background-color: white">
					<c:if test="${not empty sessionScope.transactionErr }">
						<h5 style="color: red">${ sessionScope.transactionErr}</h5>
						<%
					session.removeAttribute("transactionErr");
					%>
					</c:if>
					<c:if test="${not empty sessionScope.successfulTransfer }">
					<h5 style="color: green">Successful Transaction! Click <a href="homepage">here</a> to go to homepage</h5>
					<%
					session.removeAttribute("successfulTransfer");
					%>
					</c:if>
					
				</div>
			</div>

			<div class="mx-auto col-8">
				<h2 style=" text-align: center;">Transfer Money</h2>
				<form method="post" class="form form-control" style="height: 80%">

					<div class="form-outline form-white mb-4">
						<input name="accountReceive" type="text" id="accountReceive"
							class="form-control form-control-lg" /> <label
							class="form-label" for="accountReceive">Account Receive</label>
					</div>

					<div class="form-outline">
						<input name="receiveName" type="text" id="receiveName"
							class="form-control" value="" readonly /> <label
							class="form-label" for="receiveName">Receiver Name</label>
					</div>

					<div class="container-fluid">
						<div class="form-row">
							<div class="col-4" style="padding: 0;">
								<div class="form-outline form-white mb-4">
									<input name="moneyAmount" type="number" id="moneyAmount"
										class="form-control form-control-lg" /> <label
										class="form-label" for="moneyAmount">Money amount</label>
								</div>
							</div>
							<div class="col-4">
								<div class="form-outline form-white mb-4">
									<input name="smartOtp" type="password" id="smartOtp"
										class="form-control form-control-lg" /> <label
										class="form-label" for="smartOtp">PIN code</label>
								</div>
							</div>



						</div>
					</div>

					<div class="form-group">
						<label for="transferContent">Transfer Content</label>
						<textarea name="transactionContent" class="form-control" id="transferContent" rows="4"></textarea>
					</div>
					<br>
					<button type="submit" name="action" value="submitTransaction" class="btn btn-primary" style="float: right; margin-bottom: 1%">Confirm
						Transfer</button>
				</form>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
	<script>
    const accountNumberInput = document.getElementById('accountReceive');
    const userNameInput = document.getElementById('receiveName');

    accountNumberInput.addEventListener('input', () => {
      const accountNumber = accountNumberInput.value;

      $.ajax({
        type: 'POST',
        url: '/BankingSystem/user-lookup', // Update with the actual servlet URL
        data: { accountNumber },
        success: function(response) {
          userNameInput.value = response;
        },
        error: function(xhr, status, error) {
          console.error(xhr.responseText);
        }
      });
    });
  </script>
</body>
</html>