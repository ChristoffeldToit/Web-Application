
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" href="css/stylesheet.css">
<script defer src="scipt.js"></script>


</head>
<body>
	
	<main id="main-holder">
		<h1 id="login-header">Create new account</h1>
		<div id="login-header">
			<p>${errorMessage}</p>
		</div>

		<form action="register" method="post" id="login-form">
			<input type="text" name="username" id="username"
				   	class="login-form-field" placeholder="Username"> 
			<input
				type="password" name="password" id="password" 
					class="login-form-field" placeholder="Password"> 
			<input
				type="password" name="password" id="password_2" 
					class="login-form-field" placeholder="Password"> 
			<input
				type="submit" value="Register" id="login-form-submit">
				
				<a href="login.html" class="button" id="register-form-submit">Login page</a>
		</form>
	</main>
</body>
