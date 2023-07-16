<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>Create Staff Account</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
<!-- Google Fonts Roboto -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
<!-- MDB -->
<link rel="stylesheet" href="css/mdb.min.css" />
<!-- Custom styles -->
<link rel="stylesheet" href="css/admin.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"
	integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw=="
	crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<!-- Sidebar -->
		<nav id="sidebarMenu"
			class="collapse d-lg-block sidebar collapse bg-white">
			<div class="position-sticky">
				<div class="list-group list-group-flush mx-3 mt-4">
					<a href="/BankingSystem/manager-dashboard"
						class="list-group-item list-group-item-action py-2 ripple"> <i
						class="fas fa-chart-area fa-fw me-3"></i><span>Main
							dashboard</span>
					</a> <a href="/BankingSystem/create-staff-account"
						class="list-group-item list-group-item-action py-2 ripple active"><i
						class="fas fa-users fa-fw me-3"></i><span>Create Staff
							Account</span></a> <a href="/BankingSystem/vip-customer"
						class="list-group-item list-group-item-action py-2 ripple"><i
						class="fas fa-building fa-fw me-3"></i><span>VIP Customer
							Management</span></a>
				</div>
			</div>
		</nav>
		<!-- Sidebar -->

		<!-- Navbar -->
		<nav id="main-navbar"
			class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
			<!-- Container wrapper -->
			<div class="container-fluid">
				<!-- Toggle button -->
				<button class="navbar-toggler" type="button"
					data-mdb-toggle="collapse" data-mdb-target="#sidebarMenu"
					aria-controls="sidebarMenu" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-bars"></i>
				</button>

				<!-- Brand -->
				<a class="navbar-brand" href="#"> <img
					src="images/Manager.png"
					height="25" alt="" loading="lazy" />
				</a>
				<!-- Search form -->
				<form class="d-none d-md-flex input-group w-auto my-auto">
					<input autocomplete="off" type="search"
						class="form-control rounded"
						placeholder='Search (ctrl + "/" to focus)'
						style="min-width: 225px" /> <span
						class="input-group-text border-0"><i class="fas fa-search"></i></span>
				</form>

				<!-- Right links -->
				<ul class="navbar-nav ms-auto d-flex flex-row">
					<!-- Notification dropdown -->

					<!-- Icon -->

					<!-- Icon -->
					<li class="nav-item me-3 me-lg-0"><a class="nav-link" href="https://github.com/group1SE17A08/BankingSystem">
							<i class="fab fa-github"></i>
					</a></li>

					<!-- Icon dropdown -->

					<!-- Avatar -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle hidden-arrow d-flex align-items-center"
						href="#" id="navbarDropdownMenuLink" role="button"
						data-mdb-toggle="dropdown" aria-expanded="false"> <img
							src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg"
							class="rounded-circle" height="22" alt="" loading="lazy" />
					</a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item" href="/BankingSystem/logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- Container wrapper -->
		</nav>
		<!-- Navbar -->
	</header>
	<!--Main Navigation-->

	<!--Main layout-->
	<main style="margin-top: 58px">
		<section>
			<div class="container py-5 h-100">
				<strong>Create a new Staff Account</strong>

				<c:if test="${not empty sessionScope.staffUsernameExisted }">
					<div style="color: red">${ sessionScope.staffUsernameExisted}</div>
					<c:remove var="staffUsernameExisted" scope="session" />
				</c:if>

				<c:if test="${not empty sessionScope.insertedStaffAccount }">
					<div style="color: green">${ sessionScope.insertedStaffAccount}</div>
					<c:remove var="insertedStaffAccount" scope="session" />
				</c:if>

				<form method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">Username</label> <input
							type="text" class="form-control" id="staffUsername"
							name="staffUsername" aria-describedby="emailHelp"
							placeholder="Username" required>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" id="staffPassword"
							name="staffPassword" placeholder="Password" required>
					</div>
					<br>
					<button type="submit" name="action" value="addAccount"
						class="btn btn-primary">Submit</button>
				</form>
				<br> <strong>List STAFF Account</strong>
				<c:if test="${not empty sessionScope.removed }">
						<div style="color: red">${ sessionScope.removed}</div>
					<c:remove var="removed" scope="session" />
				</c:if>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Username</th>
							<th scope="col">Password</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="adminUser" varStatus="status"
							items="${sessionScope.listAdminUser }">
							<tr>
								<th scope="row">${1 + status.index }</th>
								<td>${ adminUser.adminUser_username}</td>
								<td>${ adminUser.adminUser_password}</td>
								<td><a href="/BankingSystem/create-staff-account?action=remove&removeUsername=${adminUser.adminUser_username }" data-mdb-toggle="tooltip" title="Remove"><i
										class="fas fa-trash-alt fa-lg text-warning"></i></a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</section>
	</main>
	<!--Main layout-->
	<!-- MDB -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
	<!-- Custom scripts -->
	<script type="text/javascript" src="js/admin.js"></script>
</body>
</html>
