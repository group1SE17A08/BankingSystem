<header>
	<!-- Navbar Section  -->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="/BankingSystem/homepage">VirtualbanK</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="/BankingSystem/transfer-money">New
							Transaction</a></li>
				</ul>
				<div class="dropdown" style="margin: 0 5px;">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-bs-toggle="dropdown"
						aria-expanded="false">My Profile</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<li><a class="dropdown-item" href="/BankingSystem/my-profile">View my profile</a></li>
						<li><a class="dropdown-item" href="#">View
								my trasaction history</a></li>
					</ul>
				</div>

				<div class="dropdown" style="margin: 0 5px;">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-bs-toggle="dropdown"
						aria-expanded="false">Make a Request</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<li><a class="dropdown-item" href="#">Lock my account</a></li>
						<li><a class="dropdown-item" href="change-password.jsp">Unlock
								my account</a></li>
						<li><a class="dropdown-item" href="change-password.jsp">Request
								a Savings</a></li>
						<li><a class="dropdown-item" href="change-password.jsp">Request
								a Personal Information Changing</a></li>
					</ul>
				</div>

				<div class="dropdown" style="margin: 0 5px;">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-bs-toggle="dropdown"
						aria-expanded="false">Settings & Privacy</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<li><a class="dropdown-item" href="#">Change Smart OTP</a></li>
						<li><a class="dropdown-item" href="/BankingSystem/change-password">Change
								Password</a></li>
					</ul>
				</div>



				<div class="collapse navbar-collapse justify-content-end"
					id="navbarNav">
					<form method="post">
						<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link"
								style="color: white"><i class="fa fa-user-circle-o"
									aria-hidden="true" style="color: white"> </i>Hello,
									${sessionScope.user.customerName}</a></li>
							<li class="nav-item"><button
									class="btn btn-success text-dark" type="submit" name="action"
									value="logOut">
									<i class="fa fa-sign-out" aria-hidden="true"></i> Log Out
								</button></li>
						</ul>

					</form>
				</div>
			</div>
		</div>
	</nav>


</header>