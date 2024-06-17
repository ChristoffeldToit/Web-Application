
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
	<main id="main-holder">
		<h1 id="login-header">Login</h1>
		<div id="login-header">
			<p>${errorMessage}</p>
		</div>

		<form action="login" method="post" id="login-form">
			<input type="text" name="username" id="username"
				   	class="login-form-field" placeholder="Username"> 
			<input
				type="password" name="password" id="password" 
					class="login-form-field" placeholder="Password"> 
			<input
				type="submit" value="Login" id="login-form-submit">
				
			<a href="register.jsp" class="button" id="register-form-submit">Create new account</a>
		
		</form>

	</main>
</body>
</html>