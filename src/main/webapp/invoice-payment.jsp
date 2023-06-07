<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include/head.jsp"%>
<title>Invoice Payment</title>
</head>
<body>
	<%@include file="include/logo.jsp"%>
	<br>
	<h4 style="text-align: center">Invoice Payment Gate</h4>
	<div class="container-fluid">
		<div class="row">
			<div class="col-4">
				<section class="w-100 p-4 text-center">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Invoice You have to pay:</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${requestScope.listBillPaid }" var="bill">
									<tr>
										<td class="px-0">Bill ID: <a
											href="view-invoice?billId=${bill.billId }&isMyBill=false">${bill.billId }</a></td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</section>
			</div>

			<div class="col-8" style="margin-top: 2%">
				<form method="post">
					<h3 style="color: green">
						<strong>You can also pay invoices for you family, or
							friends!</strong>
					</h3>
					<h5>You just need to enter the bill ID they gave you!</h5>
					<label for="inputPassword" class="col-form-label">Enter
						Bill ID (a Unique string of 16-alphanumeric letters)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputBillId"
							name="billId" placeholder="Bill ID..."> <br>
						<button style="float: right" type="submit" class="btn btn-success"
							name="action" value="submitPayForOther">Proceed Payment</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div style="position: fixed; bottom: 0; width: 100%">
		<%@include file="include/footer.jsp"%>
	</div>
</body>
</html>