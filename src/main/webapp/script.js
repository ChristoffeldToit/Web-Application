function validate_password() {

	let pass = document.getElementById('password').value;
	let confirm_pass = document.getElementById('password_2').value;
	
	
	
	/* 
	if (userID.trim() === '') {
        alert('Please enter a username.'); // Display an alert if the username is empty
        return; // Stop further validation if the username is empty
    }
    */
	
	if (pass != confirm_pass) {
		document.getElementById('wrong_pass_alert').style.color = 'red';
		document.getElementById('wrong_pass_alert').innerHTML
			= 'Passwords do not match';
		document.getElementById('login-form-submit').disabled = true;
		document.getElementById('login-form-submit').style.opacity = (0.4);
	} else if (pass == confirm_pass && pass != "") {
		document.getElementById('wrong_pass_alert').style.color = 'green';
		document.getElementById('wrong_pass_alert').innerHTML =
			'Password Matched';
		document.getElementById('login-form-submit').disabled = false;
		document.getElementById('login-form-submit').style.opacity = (1);
	}
}


function wrong_pass_alert() {
	
	let userID = document.getElementById('username').value; 
	if (userID.trim() === '') {
	 // Display an alert if the username is empty
	 alert('Please enter a username.'); 
		return;
	}
	else if (document.getElementById('password').value != "" &&
		document.getElementById('password_2').value != ""){
		alert("Response has been submitted");
	} else {
		alert("Please fill all the fields");
	}
}
