<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>Admin Homepage</title>
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
					<a href="/BankingSystem/staff-dashboard"
						class="list-group-item list-group-item-action py-2 ripple"> <i
						class="fas fa-chart-area fa-fw me-3"></i><span>Main
							dashboard</span>
					</a> <a href="/BankingSystem/user-list"
						class="list-group-item list-group-item-action py-2 ripple active"><i
						class="fas fa-users fa-fw me-3"></i><span>Users account</span></a> <a
						href="/BankingSystem/request-list"
						class="list-group-item list-group-item-action py-2 ripple"><i
						class="fas fa-building fa-fw me-3"></i><span>Requests list</span></a>
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
					src="https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.png"
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
					<li class="nav-item me-3 me-lg-0"><a class="nav-link" href="#">
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
							<li><a class="dropdown-item" href="#">My profile</a></li>
							<li><a class="dropdown-item" href="#">Settings</a></li>
							<li><a class="dropdown-item" href="#">Logout</a></li>
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
		<section class="vh-100 gradient-custom-2">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-md-12 col-xl-10">
						<div class="card mask-custom">
							<div class="card-body p-4 text-white">
								<div class="text-center pt-3 pb-2">
									<img
										src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-todo-list/check1.webp"
										alt="Check" width="60" />
									<h2 class="my-4">User List</h2>
								</div>

								<table class="table text-white mb-0">
									<thead>
										<tr>
											<th scope="col">ID</th>
											<th scope="col">Full Name</th>
											<th scope="col">Phone Number</th>
											<th scope="col">Customer type</th>
											<th scope="col">Birth Day</th>
											<th scope="col">Account number</th>
											<th scope="col">Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${requestScope.listCustomer }"
											var="customer">
											<tr class="fw-normal">
												<th><span class="ms-2">${customer.customerId }</span></th>
												<td class="align-middle"><span>${customer.customerName }</span></td>
												<td class="align-middle"><span>${customer.customerPhoneNumber }</span></td>

												<c:choose>
													<c:when test="${customer.vipStatus == true }">
														<td class="align-middle">
															<h6 class="mb-0">
																<span class="badge bg-success">High priority</span>
															</h6>
														</td>
													</c:when>
													<c:otherwise>
														<td class="align-middle">
															<h6 class="mb-0">
																<span class="badge bg-danger">Low priority</span>
															</h6>
														</td>
													</c:otherwise>
												</c:choose>



												<td class="align-middle"><span>${customer.customerDob }</span></td>
												<td class="align-middle"><span>${customer.customerBankAccount }</span></td>
												<td class="align-middle"><a href="#!"
													data-mdb-toggle="tooltip" title="Remove"><i
														class="fas fa-trash-alt fa-lg text-warning"></i></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
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
