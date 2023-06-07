<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login V1</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.1/css/fontawesome.min.css"
	integrity="sha384-QYIZto+st3yW+o8+5OHfT6S482Zsvz2WfOzpFSXMF9zqeLcFV0/wlZpMtyFcZALm"
	crossorigin="anonymous">
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />

<link rel="stylesheet" type="text/css"
	href="vendor/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">

<link rel="stylesheet" type="text/css"
	href="vendor/css-hamburgers/hamburgers.min.css">

<link rel="stylesheet" type="text/css"
	href="vendor/select2/select2.min.css">

<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<meta name="robots" content="noindex, follow">
<script nonce="d60470ef-9ed7-4c1c-8321-bf44690a9d7e">
        (function(w, d) {
            ! function(bg, bh, bi, bj) {
                bg[bi] = bg[bi] || {};
                bg[bi].executed = [];
                bg.zaraz = {
                    deferred: [],
                    listeners: []
                };
                bg.zaraz.q = [];
                bg.zaraz._f = function(bk) {
                    return function() {
                        var bl = Array.prototype.slice.call(arguments);
                        bg.zaraz.q.push({
                            m: bk,
                            a: bl
                        })
                    }
                };
                for (const bm of ["track", "set", "debug"]) bg.zaraz[bm] = bg.zaraz._f(bm);
                bg.zaraz.init = () => {
                    var bn = bh.getElementsByTagName(bj)[0],
                        bo = bh.createElement(bj),
                        bp = bh.getElementsByTagName("title")[0];
                    bp && (bg[bi].t = bh.getElementsByTagName("title")[0].text);
                    bg[bi].x = Math.random();
                    bg[bi].w = bg.screen.width;
                    bg[bi].h = bg.screen.height;
                    bg[bi].j = bg.innerHeight;
                    bg[bi].e = bg.innerWidth;
                    bg[bi].l = bg.location.href;
                    bg[bi].r = bh.referrer;
                    bg[bi].k = bg.screen.colorDepth;
                    bg[bi].n = bh.characterSet;
                    bg[bi].o = (new Date).getTimezoneOffset();
                    if (bg.dataLayer)
                        for (const bt of Object.entries(Object.entries(dataLayer).reduce(((bu, bv) => ({ ...bu[1],
                                ...bv[1]
                            }))))) zaraz.set(bt[0], bt[1], {
                            scope: "page"
                        });
                    bg[bi].q = [];
                    for (; bg.zaraz.q.length;) {
                        const bw = bg.zaraz.q.shift();
                        bg[bi].q.push(bw)
                    }
                    bo.defer = !0;
                    for (const bx of [localStorage, sessionStorage]) Object.keys(bx || {}).filter((bz => bz.startsWith("_zaraz_"))).forEach((by => {
                        try {
                            bg[bi]["z_" + by.slice(7)] = JSON.parse(bx.getItem(by))
                        } catch {
                            bg[bi]["z_" + by.slice(7)] = bx.getItem(by)
                        }
                    }));
                    bo.referrerPolicy = "origin";
                    bo.src = "/cdn-cgi/zaraz/s.js?z=" + btoa(encodeURIComponent(JSON.stringify(bg[bi])));
                    bn.parentNode.insertBefore(bo, bn)
                };
                ["complete", "interactive"].includes(bh.readyState) ? zaraz.init() : bg.addEventListener("DOMContentLoaded", zaraz.init)
            }(w, d, "zarazData", "script");
        })(window, document);
    </script>

</head>
<style>
/* Style the form */
#regForm {
	background-color: #ffffff;
	margin: 0 auto;
	padding: 0px;
	width: 50%;
	min-width: 300px;
}

/* Style the input fields */
input {
	padding: 10px;
	width: 100%;
	font-size: 17px;
	font-family: Raleway;
	border: 1px solid #aaaaaa;
}

/* Mark input boxes that gets an error on validation: */
input.invalid {
	background-color: #ffdddd;
}

/* Hide all steps by default: */
.tab {
	display: none;
}

/* Make circles that indicate the steps of the form: */
.step {
	height: 15px;
	width: 15px;
	margin: 0 2px;
	background-color: #bbbbbb;
	border: none;
	border-radius: 50%;
	display: inline-block;
	opacity: 0.5;
}

/* Mark the active step: */
.step.active {
	opacity: 1;
}

/* Mark the steps that are finished and valid: */
.step.finish {
	background-color: #04AA6D;
}
</style>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="/BankingSystem/homepage">VirtualbanK</a>
		</div>
	</nav>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="images/img-01.png" alt="IMG">
				</div>
				<form class="login100-form validate-form" id="regForm"
					action="/BankingSystem/sign-up" method="post">

					<span class="login100-form-title"> Join with us! </span>
					<c:if test="${not empty sessionScope.failPasswordCfm}">
						<span style="color: red">${sessionScope.failPasswordCfm }</span>
					</c:if>
					<c:if test="${not empty sessionScope.failOtpCfm}">
						<span style="color: red">${sessionScope.failOtpCfm }</span>
					</c:if>
					<c:if test="${not empty sessionScope.accNumExisted}">
						<span style="color: red">${sessionScope.accNumExisted }</span>
					</c:if>
					<c:if test="${not empty sessionScope.usernameDuplicated}">
						<span style="color: red">${sessionScope.usernameDuplicated }</span>
					</c:if>
					<c:if test="${not empty sessionScope.emailDuplicated}">
						<span style="color: red">${sessionScope.emailDuplicated }</span>
					</c:if>
					<c:if test="${not empty sessionScope.phoneNumberDuplicated}">
						<span style="color: red">${sessionScope.phoneNumberDuplicated }</span>
					</c:if>
					<c:if test="${not empty sessionScope.successSignUp}">
						<span style="color: red">${sessionScope.successSignUp }</span>
					</c:if>
					<div class="tab">
						<div class="wrap-input100 validate-input"
							data-validate="Name is required">
							<input class="input100" type="text" name="name"
								placeholder="Enter your name"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-user" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Address is required">
							<input class="input100" type="text" name="address"
								placeholder="Enter your address"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-address-card" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Phone number">
							<input pattern="^0\d{9}$"
								title="The phone number must be 10 digits and start with 0!"
								class="input100" type="text" name="phoneNumber"
								placeholder="Enter your phone number"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-phone" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Date of Birth">
							<input title="You have to be 18+ to sign up!" class="input100"
								type="date" name="dateOfBirth" placeholder="Enter your birthday">
							<span class="focus-input100"></span> <span
								class="symbol-input100"> <i class="fa fa-birthday-cake"
								aria-hidden="true"></i>
							</span>
						</div>
					</div>

					<div class="tab">
						<div class="wrap-input100 validate-input"
							data-validate="Email is required">
							<input pattern="^[A-Za-z0-9._%+-]+@gmail\.com$"
								title="Email must end with @gmail.com" class="input100"
								type="email" name="email" placeholder="Enter your email">
							<span class="focus-input100"></span> <span
								class="symbol-input100"> <i class="fa fa-envelope"
								aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Username is required">
							<input pattern="^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$"
								title="Username must be more than 8 letter, including letters, at least 1 capitalized letter, at least 1 number"
								class="input100" type="text" name="username"
								placeholder="Enter your username"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-user" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Password is required">
							<input pattern="^.{8,}$"
								title="Password must be more than 8 letters" class="input100"
								type="password" name="pass" placeholder="Password"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-lock" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Password confirmation is required">
							<input class="input100" type="password" name="confirm_pass"
								placeholder="Password confirmation"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-lock" aria-hidden="true"></i>
							</span>
						</div>

					</div>

					<div class="tab">
						<div class="wrap-input100 validate-input"
							data-validate="A 16-digit bank account number is required">
							<input pattern="^\d{16}$"
								title="The account number must be 16-digit" class="input100"
								type="text" name="accountNumber"
								placeholder="Enter the account number you want"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-credit-card" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="6-digit Smart OTP is required">
							<input pattern="^\d{6}$" title="OTP must be 6-digits"
								class="input100" type="password" name="otp"
								placeholder="Enter your OTP"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-lock" aria-hidden="true"></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input"
							data-validate="Re-enter your OTP for confirmation">
							<input class="input100" type="password" name="confirm_otp"
								placeholder="Smart OTP confirmation"> <span
								class="focus-input100"></span> <span class="symbol-input100">
								<i class="fa fa-lock" aria-hidden="true"></i>
							</span>
						</div>

					</div>

					<div style="overflow: auto;">
						<div style="float: right;">
							<span style="color: green"> You must check validity before
								clicking NEXT!</span><br>
							<button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
							<button type="button" id="validate">Check Validity</button>
							<button type="button" id="nextBtn" onclick="nextPrev(1)"
								name="action" value="signUp" disabled>Next</button>
						</div>
					</div>
				</form>
				<!-- Circles which indicates the steps of the form: -->
				<div style="text-align: center; margin-top: 40px;">
					<span class="step"></span> <span class="step"></span> <span
						class="step"></span>
				</div>
			</div>
		</div>
	</div>
	<%@include file="include/footer.jsp"%>
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

	<script src="vendor/select2/select2.min.js"></script>

	<script src="vendor/tilt/tilt.jquery.min.js"></script>
	<script>
        $('.js-tilt').tilt({
            scale: 1.1
        })
    </script>
	<script>
	var dateOfBirthInput = document.getElementsByName("dateOfBirth")[0];
	dateOfBirthInput.addEventListener("input", function() {
	  var enteredDate = new Date(dateOfBirthInput.value);
	  var limitDate = new Date("2005-01-01"); // Set the desired limit date
	  
	  if (enteredDate >= limitDate) {
	    // Display an error message or perform actions for invalid input
	    alert("Invalid date of birth or must be before 2005");
	    dateOfBirthInput.value = "";
	  } 
	});

	</script>
	<script>
		document.getElementById("validate").addEventListener("click", function(){
			var inputs = document.querySelectorAll("input");
			  var invalidInputs = [];

			  inputs.forEach(function(input) {
			    if (!input.checkValidity()) {
			      invalidInputs.push(input);
			    }
			  });

			  if (invalidInputs.length > 0) {
			    var errorMessage = "Please correct the following errors:\n";
			    invalidInputs.forEach(function(input) {
			      errorMessage += "- " + input.getAttribute("title") + "\n";
			    });

			    alert(errorMessage);
			  } else {
				  var nextBtn = document.getElementById("nextBtn");
				  nextBtn.disabled = false;
			  }
		});
		
		
	</script>
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
        window.dataLayer = window.dataLayer || [];

        function gtag() {
            dataLayer.push(arguments);
        }
        gtag('js', new Date());

        gtag('config', 'UA-23581568-13');
    </script>

	<script src="js/main.js"></script>
	<script defer
		src="https://static.cloudflareinsights.com/beacon.min.js/v52afc6f149f6479b8c77fa569edb01181681764108816"
		integrity="sha512-jGCTpDpBAYDGNYR5ztKt4BQPGef1P0giN6ZGVUi835kFF88FOmmn8jBQWNgrNd8g/Yu421NdgWhwQoaOPFflDw=="
		data-cf-beacon='{"rayId":"7c9b4ee0fef204ff","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2023.4.0","si":100}'
		crossorigin="anonymous"></script>
	<script>
	var currentTab = 0; // Current tab is set to be the first tab (0)
	showTab(currentTab); // Display the current tab

	function showTab(n) {
	  // This function will display the specified tab of the form ...
	  var x = document.getElementsByClassName("tab");
	  x[n].style.display = "block";
	  // ... and fix the Previous/Next buttons:
	  if (n == 0) {
	    document.getElementById("prevBtn").style.display = "none";
	  } else {
	    document.getElementById("prevBtn").style.display = "inline";
	  }
	  if (n == (x.length - 1)) {
		var button =  document.getElementById("nextBtn");
	    button.innerHTML = "Submit";
	    button.disabled = true;
	    button.setAttribute("type", "submit");
	    button.removeAttribute("onclick");
	  } else {
	    document.getElementById("nextBtn").innerHTML = "Next";
	    document.getElementById("nextBtn").disabled = true;
	  }
	  // ... and run a function that displays the correct step indicator:
	  fixStepIndicator(n)
	}

	function nextPrev(n) {
	  // This function will figure out which tab to display
	  var x = document.getElementsByClassName("tab");
	  // Exit the function if any field in the current tab is invalid:
	  if (n == 1 && !validateForm()) return false;
	  // Hide the current tab:
	  x[currentTab].style.display = "none";
	  // Increase or decrease the current tab by 1:
	  currentTab = currentTab + n;
	  // if you have reached the end of the form... :
	  if (currentTab >= x.length) {
	    //...the form gets submitted:
	    document.getElementById("regForm").submit();
	    return false;
	  }
	  // Otherwise, display the correct tab:
	  showTab(currentTab);
	}
	function validateForm() {
		  // This function deals with validation of the form fields
		  var x, y, i, valid = true;
		  x = document.getElementsByClassName("tab");
		  y = x[currentTab].getElementsByTagName("input");
		  // A loop that checks every input field in the current tab:
		  for (i = 0; i < y.length; i++) {
		    // If a field is empty...
		    if (y[i].value == "") {
		      // add an "invalid" class to the field:
		      y[i].className += " invalid";
		      // and set the current valid status to false:
		      valid = false;
		    }
		  }
		  // If the valid status is true, mark the step as finished and valid:
		  if (valid) {
		    document.getElementsByClassName("step")[currentTab].className += " finish";
		  }
		  return valid; // return the valid status
		}

	function fixStepIndicator(n) {
	  // This function removes the "active" class of all steps...
	  var i, x = document.getElementsByClassName("step");
	  for (i = 0; i < x.length; i++) {
	    x[i].className = x[i].className.replace(" active", "");
	  }
	  //... and adds the "active" class to the current step:
	  x[n].className += " active";
	}</script>
</body>
</html>