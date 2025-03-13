<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        .errorMessage {
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
        <form id="loginForm" action="authenticate?action=login" method="post">
            <input type="text" id="loginUsername" name="username" placeholder="Username" required>
            <input type="password" id="loginPassword" name="password" placeholder="Password" required>
            <% if (request.getAttribute("loginError") != null) { %>
                <div id="loginPasswordError" class="errorMessage"><%= request.getAttribute("loginError") %></div>
            <% } %>
            <input type="submit" value="Login">
        </form>
        <div class="toggle-link">
            <a href="#" onclick="toggleForm('signup')">Don't have an account? Signup</a>
        </div>
    </div>

    <div id="signupContainer" class="container" style="display: none;" >
        <h2>Signup</h2>
        <form id="signupForm" action="authenticate?action=signup" method="post">    
            <input type="text" id="signupUsername" name="username" placeholder="Username" required>
            <% if (request.getAttribute("signupUsernameError") != null) { %>
                <div id="signupUsernameError" class="errorMessage"><%= request.getAttribute("signupUsernameError") %></div>
            <% } %>
            <input type="email" id="signupEmail" name="email" placeholder="Email" required>
            <% if (request.getAttribute("signupEmailError") != null) { %>
                <div id="signupEmailError" class="errorMessage"><%= request.getAttribute("signupEmailError") %></div>
            <% } %>
            <input type="text" id="signupPhone" name="phone" placeholder="Phone number" required>
            <input type="password" id="signupPassword" name="password" placeholder="Password" required>
            <% if (request.getAttribute("signupPasswordError") != null) { %>
                <div id="signupPasswordError" class="errorMessage"><%= request.getAttribute("signupPasswordError") %></div>
            <% } %>
            <input type="password" id="confirmPassword" name="confirm_password" placeholder="Confirm Password" required>
            <% if (request.getAttribute("confirmPasswordError") != null) { %>
                <div id="confirmPasswordError" class="errorMessage"><%= request.getAttribute("confirmPasswordError") %></div>
            <% } %>
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
   </script>
</body>
</html>
