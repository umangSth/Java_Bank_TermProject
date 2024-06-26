<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script"
	rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<head>
<meta charset="UTF-8">
<title>Registration</title>
<style>
body {
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

a {
	text-decoration: none !important;
}

h2, h3 {
	font-family: 'Kaushan Script', cursive;
}

.d-flex {
	gap: 10px;
}

.myform {
	position: relative;
	display: -ms-flexbox;
	display: flex;
	padding: 1rem;
	-ms-flex-direction: column;
	flex-direction: column;
	width: 100%;
	pointer-events: auto;
	background-clip: padding-box;
	outline: 0;
	max-width: 500px;
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

.tx-tfm {
	text-transform: uppercase;
}

.mybtn {
	border-radius: 50px;
	background-color: #333333;
	color: #fff;
}

.login-or {
	position: relative;
	color: #aaa;
	margin-top: 10px;
	margin-bottom: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.span-or {
	display: block;
	position: absolute;
	left: 50%;
	top: -2px;
	margin-left: -25px;
	background-color: #fff;
	width: 50px;
	text-align: center;
}

.hr-or {
	height: 1px;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
}

.google {
	color: #666;
	width: 100%;
	height: 40px;
	text-align: center;
	outline: none;
	border: 1px solid lightgrey;
}

form .error {
	color: #ff0000;
}

.alert {
	padding: 15px;
	border: 1px solid transparent;
	border-radius: 4px;
	margin-bottom: 20px;
}

.alert-warning {
	color: #856404;
	background-color: #fff3cd;
	border-color: #ffeeba;
}
</style>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-5 mx-auto">
				<div id="first">
					<div class="myform form ">
						<div class="logo mb-3">
							<div class="d-flex align-items-center gap-3">
								<img
									src="https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_232,h_232/https://loanscanada.ca/wp-content/uploads/2020/06/Canada%E2%80%99s-Five-Major-Banks-1.png"
									class="rounded-circle shadow-4" style="width: 70px;"
									alt="Avatar" />
								<h1>Welcome to Bank</h1>
							</div>
							<div class="col-md-12 text-center">
								<h2 style="margin-top: 15px;">Registration</h2>
								<div id="errorContainer"></div>
							</div>
						</div>
						<form:form method="post" action="saveUser">
							<div class="form-group">
								<label for="name">Name</label>
								<form:input type="text" path="name" class="form-control"
									id="name" aria-describedby="emailHelp"
									placeholder="Enter full name" />
							</div>
							<div class="form-group">
								<label for="email">Email</label>
								<form:input type="text" path="email" class="form-control"
									id="email" aria-describedby="emailHelp"
									placeholder="Enter email" />
							</div>
							<div class="form-group">
								<label for="phone">Phone</label>
								<form:input type="number" path="phone" class="form-control"
									id="phone" aria-describedby="emailHelp"
									placeholder="Enter phone" />
							</div>
							<div class="form-group">
								<label for="address">Address</label>
								<form:input type="text" path="address" id="address"
									class="form-control" placeholder="Enter Address" />
							</div>
							<div class="form-group">
								<label for="password">Password</label>
								<form:input type="password" path="password" id="password"
									class="form-control" placeholder="Enter Password"/>
							</div>
							<div class="form-group">
								<label for="userType">User Type:</label>
								<form:select id="userType" path="user_type" class="form-control">
								<form:option value="CLIENT">Client</form:option>
									<form:option value="ADMIN">Admin</form:option>
									
								</form:select>
							</div>

							<div class="col-md-12 text-center ">
								<input type="submit" class=" btn btn-block mybtn tx-tfm"
									value="Register" />
							</div>
							<div class="col-md-12 ">
								<div class="login-or">
									<hr class="hr-or">

								</div>
							</div>

							<div class="form-group">
								<p class="text-center">
									<a href="/TermProject_Bank/" id="signup">Back to Login Page</a>
								</p>
							</div>
						</form:form>

					</div>
				</div>

			</div>
		</div>
	</div>
	<script>
   window.onload = function() {
   	
       var errorContainer = document.getElementById("errorContainer");
       var urlParams = new URLSearchParams(window.location.search);
       var errorMessage = urlParams.get('error');

       if (errorMessage) {
           var alertDiv = document.createElement("div");
           alertDiv.classList.add("alert", "alert-warning");
           alertDiv.textContent = errorMessage;
           errorContainer.appendChild(alertDiv);

        // Hide the alert after 5 seconds
           setTimeout(() => {
               alertDiv.style.display = 'none';
               // Remove the error parameter from the URL
               const newUrl = window.location.pathname + window.location.search.replace(/[\?&]error=[^&]+/, '');
               history.replaceState({}, '', newUrl);
           }, 3000);
       }
   };
   
   </script>
</body>
</html>
