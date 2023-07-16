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
<link rel="stylesheet" href="css/request.css" />
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
						class="list-group-item list-group-item-action py-2 ripple"><i
						class="fas fa-users fa-fw me-3"></i><span>Users account</span></a> <a
						href="/BankingSystem/request-list"
						class="list-group-item list-group-item-action py-2 ripple active"><i
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
					src="images/Staff.png"
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
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card card-white mb-5">
						<div class="card-heading clearfix border-bottom mb-4">
							<h4 class="card-title">Requests</h4>
						</div>
						<div class="card-body">
							<ul class="list-unstyled">

								<c:forEach items="${requestScope.listRequest }" var="request">
									<li class="position-relative booking">
										<div class="media">
											<div class="media-body">
												<h5 class="mb-4">
													${request.requestName }
													<c:choose>
														<c:when test="${request.requestStatus == false }">
															<span class="badge bg-danger mx-3">Pending</span>


														</c:when>
														<c:otherwise>
															<span class="badge bg-success mx-3">Resolved</span>
															<c:choose>
																<c:when test="${request.rejected == true }">
																	<span class="badge bg-danger mx-3">Rejected</span>
																</c:when>
																<c:when test="${request.rejected == false }">
																	<span class="badge bg-success mx-3">Approved</span>
																</c:when>
															</c:choose>
														</c:otherwise>

													</c:choose>
												</h5>
												<div class="mb-3">
													<span class="mr-2 d-block d-sm-inline-block mb-2 mb-sm-0">Request
														Date:</span> <span class="badge bg-light-blue">${request.requestDate }</span>
												</div>
												<div class="mb-3">
													<span class="mr-2 d-block d-sm-inline-block mb-2 mb-sm-0">Request
														Type:</span> <span class="bg-light-blue">${request.requestType }</span>
												</div>
												<div class="mb-3">
													<span class="mr-2 d-block d-sm-inline-block mb-2 mb-sm-0">Content:
													</span><br>
													<c:set var="saving" value="Savings" />
													<c:set var="unlock" value="Unlock Acc." />
													<c:set var="lock" value="Lock Acc." />
													<c:set var="change" value="Request Information Changing" />
													<c:choose>
														<c:when
															test="${request.requestType eq lock or request.requestType eq unlock}">
															<span class="bg-light-blue">${request.requestContent }</span>
														</c:when>
														<c:when test="${request.requestType eq saving}">
															<c:forEach items="${request.requestSavings }" var="value">
																<span class="bg-light-blue">Amount: ${value.key }</span>
																<br>
																<span class="bg-light-blue">Saving Terms:
																	${value.value } months</span>
															</c:forEach>
														</c:when>
														<c:when test="${request.requestType eq change }">
															<c:forEach items="${request.requestInformationChanging }"
																var="value">
																<span class="bg-light-blue">${value.key }: Change
																	to ${value.value }</span>
																<br>
															</c:forEach>
														</c:when>
													</c:choose>

												</div>
												<div class="mb-5">
													<span class="mr-2 d-block d-sm-inline-block mb-1 mb-sm-0">Clients:</span>
													<span class="border-right pr-2 mr-2">${request.requestName }</span> <br>
													<span
														class="border-right pr-2 mr-2">ID:  ${request.reqOwnerId }</span>
												</div>
											</div>
										</div>
										<c:if test="${ request.requestStatus == false}">
					
										<div class="buttons-to-right">
											<a href="/BankingSystem/request-list?action=reject&id=${request.requestId }" class="btn-gray mr-2"><i
												class="far fa-times-circle mr-2"></i> Reject</a> <a href="/BankingSystem/request-list?action=approve&id=${request.requestId }"
												class="btn-gray"><i class="far fa-check-circle mr-2"></i>
												Approve</a>
										</div>

										</c:if>
									</li>
								</c:forEach>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!--Main layout-->
	<!-- MDB -->
	<script type="text/javascript" src="js/mdb.min.js"></script>
	<!-- Custom scripts -->
	<script type="text/javascript" src="js/admin.js"></script>
</body>
</html>
