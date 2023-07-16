<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction History</title>
<%@include file="include/head.jsp"%>
</head>
<body
	style="background-image: linear-gradient(to right, #7F7FD5, #86A8E7, #91EAE4)">
	<%@include file="include/logo.jsp"%>
	<h3 style="text-align: center; margin-top: 1%">Balance
		Fluctuations</h3>
	<div class="container-fluid">
		<div class="row">
			<div class="col-9">
				<canvas id="transactionChart" style="width: 80%; max-height: 500px"></canvas>
			</div>
			<div class="col-3">

				<h5>Account number: ${requestScope.accNumFormatted }</h5>
				<h5>Account balance: $ ${requestScope.currentAcc.accountBalance }</h5>

			</div>
		</div>
	</div>
	<br>
	<h3 style="text-align: center">Transaction History</h3>
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" class="col-2">Transaction With</th>
							<th scope="col" class="col-2">Transaction Type</th>
							<th scope="col" class="col-3"></th>
							<th scope="col" class="col-3">Transaction Date</th>
							<th scope="col" class="col-2">Amount</th>
						</tr>
					<thead>
					<tbody>
						<c:forEach var="trans" items="${requestScope.listTrans}">
							<tr>
								<c:choose>
									<c:when
										test="${trans.fromAccount == sessionScope.user.customerBankAccount }">
										<td>${trans.toAccount }</td>
									</c:when>
									<c:when
										test="${trans.toAccount == sessionScope.user.customerBankAccount }">
										<td>${trans.fromAccount }</td>
									</c:when>
								</c:choose>



								<td>${trans.transactionType }</td>


								<c:choose>
									<c:when
										test="${trans.fromAccount == sessionScope.user.customerBankAccount }">
										<td><i class="fa-solid fa-arrow-down"
											style="color: #ea0606;"></i><span style="color: #ea0606">
												Transfer</span></td>
									</c:when>
									<c:when
										test="${trans.toAccount == sessionScope.user.customerBankAccount }">
										<td><i class="fa-solid fa-arrow-up"
											style="color: #1fea37;"></i><span style="color: #1fea37">
												Receive</span></td>
									</c:when>
								</c:choose>


								<td>${trans.date }</td>


								
								<c:choose>
									<c:when
										test="${trans.amount < 0 }">
										<td>${-1 * trans.amount } $</td>
									</c:when>
									<c:when
										test="${trans.amount >= 0 }">
										<td>${trans.amount } $</td>
									</c:when>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<script>
    // Get the canvas element
    const ctx = document.getElementById('transactionChart').getContext('2d');

    // Initialize the transaction data (replace with your own data)
    const transactions = ${data};

    // Calculate the account balance at each transaction
    const accountBalances = transactions.reduce((balances, transaction, index) => {
      const prevBalance = index === 0 ? 0 : balances[index - 1];
      const balance = prevBalance + transaction.amount;
      balances.push(balance);
      return balances;
    }, []);

    // Create the chart data
    const chartData = {
      labels: Array.from({ length: accountBalances.length }, (_, index) => (index + 1).toString()),
      datasets: [{
        data: accountBalances,
        fill: false,
        borderColor: 'blue',
        backgroundColor: 'blue',
        borderWidth: 2,
        pointRadius: 4,
        pointBackgroundColor: 'blue',
        pointBorderColor: 'blue',
        pointHoverRadius: 6,
        pointHoverBackgroundColor: 'blue',
        pointHoverBorderColor: 'blue'
      }]
    };

    // Create the chart options
    const chartOptions = {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        x: {
          display: false
        },
        y: {
          display: true,
          title: {
            display: true,
            text: 'Account Balance'
          },
          ticks: {
            maxTicksLimit: 7,
            stepSize: 15000,
            callback: function(value, index, values) {
              return '$' + value.toLocaleString();
            }
          }
        }
      },
      plugins: {
        legend: {
          display: false
        }
      }
    };

    // Create the line chart
    const chart = new Chart(ctx, {
      type: 'line',
      data: chartData,
      options: chartOptions
    });
  </script>
</body>
</html>