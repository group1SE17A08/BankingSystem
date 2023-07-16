<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body
	style="background-image: linear-gradient(to right, #fc5c7d, #6a82fb)">
	<section class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card bg-dark text-white" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">

							<div class="mb-md-5 mt-md-4 pb-5">

								<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
								<p class="text-white-50 mb-5">Please enter your login and
									password as a Staff.</p>
								<p class="text-white-50 mb-5">This account will only be
									provided by the company. You cannot create one for login</p><br>
								<span style="color:red">${sessionScope.failAdminLogin }</span>
								<form method="post">
									<div class="form-outline form-white mb-4">
										<input name="username" type="text" id="typeEmailX"
											class="form-control form-control-lg" /> <label
											class="form-label" for="typeEmailX">Username</label>
									</div>

									<div class="form-outline form-white mb-4">
										<input name="password" type="password" id="typePasswordX"
											class="form-control form-control-lg" /> <label
											class="form-label" for="typePasswordX">Password</label>
									</div>

									<!-- <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p> -->

									<button name="action" value="submit" class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>