<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login and Signup</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url(static/img/login_background.jpg);
            background-repeat: no-repeat;
            background-size: cover;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background:linear-gradient(45deg,#fff,#a19999);
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(194, 69, 69, 0.9);
        }
        h1{
            text-align: center;
            color: #ffffff;
        }
        h2 {
            text-align: center;
        }
        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .toggle-link {
            text-align: center;
            margin-top: 20px;
        }
        .toggle-link a {
            text-decoration: none;
            color: #007bff;
            cursor: pointer;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }

        /* Popup modal styles */
        .popup {
            display: none;
            position: fixed;
            z-index: 999;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: rgb(0, 0, 0);
            padding: 20px;
            border-radius: 5px;
            color: white;
        }
    </style>
</head>
<body>

    <h1>Welcome to the Airline</h1>
    <div id="loginContainer" class="container">
        
        <h2>Login</h2>
        <form id="loginForm" action="authenticate?action=login" method="post" onsubmit="return validateLoginForm()">
            <input type="text" id="loginUsername" name="username" placeholder="Username" required>
            <div id="loginUsernameError" class="error-message"></div>
            <input type="password" id="loginPassword" name="password" placeholder="Password" required>
            <div id="loginPasswordError" class="error-message"></div>
            <input type="submit" value="Login">
        </form>
        <div class="toggle-link">
            <a href="#" onclick="toggleForm('signup')">Don't have an account? Signup</a>
        </div>
    </div>

    <div id="signupContainer" class="container" style="display: none;">
        <h2>Signup</h2>
        <form id="signupForm" action="authenticate?action=signup" method="post" onsubmit="return validateSignupForm()">    
            <input type="text" id="signupUsername" name="username" placeholder="Username" required>
            <div id="signupUsernameError" class="error-message"></div>
            <input type="email" id="signupEmail" name="email" placeholder="Email" required>
            <div id="signupEmailError" class="error-message"></div>
            <input type="text" id="signupPhone" name="phone" placeholder="Phone number" required>
            <input type="password" id="signupPassword" name="password" placeholder="Password" required>
            <div id="signupPasswordError" class="error-message"></div>
            <input type="password" id="confirmPassword" name="confirm_password" placeholder="Confirm Password" required>
            <div id="confirmPasswordError" class="error-message"></div>
            <input type="submit" value="Signup">
        </form>
        <div class="toggle-link">
            <a href="#" onclick="toggleForm('login')">Already have an account? Login</a>
        </div>
    </div>
    <div id="successPopup" class="popup">
        <p>You have successfully signed up!</p>
    </div>
   
    <script>
        function toggleForm(form) {
            if (form === 'login') {
                document.getElementById('loginContainer').style.display = 'block';
                document.getElementById('signupContainer').style.display = 'none';
            } else if (form === 'signup') {
                document.getElementById('loginContainer').style.display = 'none';
                document.getElementById('signupContainer').style.display = 'block';
            }
        }
        
        function validateSignupForm() {
            var username = document.getElementById("signupUsername").value;
            var email = document.getElementById("signupEmail").value;
            var password = document.getElementById("signupPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            var isValid = true;

            // Check username criteria (e.g., length)
            if (username.length < 6 || username.length > 20) {
                document.getElementById("signupUsernameError").innerText = "Username must be between 6 and 20 characters.";
                isValid = false;
            } else {
                document.getElementById("signupUsernameError").innerText = "";
            }

            // Check password criteria (e.g., length)
            if (password.length < 8) {
                document.getElementById("signupPasswordError").innerText = "Password must be at least 6 characters long.";
                isValid = false;
            } else {
                document.getElementById("signupPasswordError").innerText = "";
            }

            // Check if password and confirmation match
            if (password !== confirmPassword) {
                document.getElementById("confirmPasswordError").innerText = "Password and Confirm Password must match.";
                isValid = false;
            } else {
                document.getElementById("confirmPasswordError").innerText = "";
            }

            if (isValid) {
                // Show success popup
                document.getElementById("successPopup").style.display = "block";
                setTimeout(function() {
                document.getElementById("signupForm").submit();
            }, 100000);
            }

            return isValid; // Submit the form if all criteria are met
        }

        function validateLoginForm() {
            var username = document.getElementById("loginUsername").value;
            var password = document.getElementById("loginPassword").value;
            var errorMessage = '<%= request.getAttribute("errorMessage") %>';
            var isValid = true;

            // Check username criteria (e.g., length)
            if (username.length < 6 || username.length > 20) {
                document.getElementById("loginUsernameError").innerText = "Username must be between 6 and 20 characters.";
                isValid = false;
            } else {
                document.getElementById("loginUsernameError").innerText = "";
            }

            // Check password criteria (e.g., length)
            if (password.length < 8) {
                document.getElementById("loginPasswordError").innerText = "Password must be at least 6 characters long.";
                
                isValid = false;

            }else if(errorMessage && errorMessage.trim() !== ''){
                document.getElementById("loginPasswordError").innerText = "Wrong Password or Username";
                isvalid = false;
            }
            else {
                document.getElementById("loginPasswordError").innerText = "";
            }
            return isValid; 
        }
    
    </script>
   
</body>
</html>
