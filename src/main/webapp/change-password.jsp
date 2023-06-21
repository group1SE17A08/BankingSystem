<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Change password</title>
</head>
<style>
.mainDiv {
    display: flex;
    min-height: 100%;
    align-items: center;
    justify-content: center;
    font-family: 'Open Sans', sans-serif;
  }
 .cardStyle {
    width: 500px;
    border-color: white;
    background: #fff;
    padding: 36px 0;
    border-radius: 4px;
    margin: 30px 0;
    box-shadow: 0px 0 2px 0 rgba(0,0,0,0.25);
  }
.formTitle{
  font-weight: 600;
  margin-top: 20px;
  color: #2F2D3B;
  text-align: center;
}
.inputLabel {
  font-size: 12px;
  color: #555;
  margin-bottom: 6px;
  margin-top: 24px;
}
  .inputDiv {
    width: 70%;
    display: flex;
    flex-direction: column;
    margin: auto;
  }
input {
  height: 40px;
  font-size: 16px;
  border-radius: 4px;
  border: none;
  border: solid 1px #ccc;
  padding: 0 11px;
}
input:disabled {
  cursor: not-allowed;
  border: solid 1px #eee;
}
.buttonWrapper {
  margin-top: 40px;
}
  .submitButton {
    width: 70%;
    height: 40px;
    margin: auto;
    display: block;
    color: #fff;
    background-color: #065492;
    border-color: #065492;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
    box-shadow: 0 2px 0 rgba(0, 0, 0, 0.035);
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;
  }

</style>
<body style="background-image: linear-gradient(to right, #7F7FD5, #86A8E7, #91EAE4)">

	<%@include file="include/logo.jsp" %>
	<div class="mainDiv">
		<div class="cardStyle">
			<form action="/BankingSystem/change-password" method="post" name="signupForm" id="signupForm">
				<h3 class="formTitle">Change your password</h3>
				<span style="color: red; margin-left: 5%'">${sessionScope.failChanging} </span>
				<c:if test="${not empty requestScope.redirectToHome }">
					<span style="color: green; margin-left: 5%">You have changed your password! Please <a href="sign-in.jsp"> login</a> again</span>
				</c:if>
				<div class="inputDiv">
					<label class="inputLabel" for="currentPassword">Current Password</label> <input
						type="password" id="password" name="currentPass" required>
				</div>

				<div class="inputDiv">
					<label class="inputLabel" for="password">New Password</label> <input
						type="password" id="password" name="newPass" required>
				</div>

				<div class="inputDiv">
					<label class="inputLabel" for="confirmPassword">Confirm
						Password</label> <input type="password" id="confirmPass"
						name="confirmPass">
				</div>

				<div class="buttonWrapper">
					<button type="submit" id="submitButton" name="action" value="submitChangePass" 
						class="submitButton pure-button pure-button-primary">
						<span>Continue</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>