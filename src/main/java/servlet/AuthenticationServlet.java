package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoAuthentication;

@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {
            handleLogin(request, response);
        } else if (action != null && action.equals("signup")) {
            handleSignup(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDaoAuthentication userDao = new UserDaoAuthentication();
        String hashedPasswordRetrieved = userDao.getPasswordHashByUsername(username);
        String hashedPassword = userDao.hashPassword(password);
        
if (username.equals("admins")){
        if (hashedPasswordRetrieved != null && hashedPassword != null && hashedPasswordRetrieved.equals(hashedPassword)) {
            // Passwords match, redirect the user to index.jsp
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }

    }
else{
        if (hashedPasswordRetrieved != null && hashedPassword != null && hashedPasswordRetrieved.equals(hashedPassword)) {
                // Passwords match, redirect the user to index.jsp
                request.setAttribute("username", username);
                RequestDispatcher dispatcher = request.getRequestDispatcher("loginIndex.jsp");
                dispatcher.forward(request, response);
        } 
        else {
            // Passwords do not match, set an error message to be displayed on the login page
            request.setAttribute("loginError", "Incorrect username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("authenticate.jsp");
            dispatcher.forward(request, response);
        
    }
}
}

    private void handleSignup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String phone = request.getParameter("phone");

        boolean isValid = true;

        // Check username criteria (e.g., length)
        if (username.length() < 6 || username.length() > 20) {
            request.setAttribute("signupUsernameError", "Username must be between 6 and 20 characters.");
            isValid = false;
        }

        // Check password criteria (e.g., length)
        if (password.length() < 8) {
            request.setAttribute("signupPasswordError", "Password must be at least 8 characters long.");
            isValid = false;
        }

        // Check if password and confirmation match
        if (!password.equals(confirmPassword)) {
            request.setAttribute("confirmPasswordError", "Password and Confirm Password must match.");
            isValid = false;
        }
         // Set flag to show signup form


        if (isValid) {
            UserDaoAuthentication userDao = new UserDaoAuthentication();
            boolean userCreated = userDao.createUser(username, email, phone, password);

            if (userCreated) {
                // User created successfully, redirect to authenticate.jsp
                response.sendRedirect("authenticate.jsp");
                return;
            } else {
                // Error occurred during signup, set an error message to be displayed on the signup page
                request.setAttribute("signupError", "Error occurred during signup. Please try again.");
            }
        }

        // Forward the request back to the signup page with error messages
        RequestDispatcher dispatcher = request.getRequestDispatcher("authenticate.jsp");
        request.setAttribute("showSignupForm", true); // Set attribute to display the signup section
        dispatcher.forward(request, response);
    }
}
