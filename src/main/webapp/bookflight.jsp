<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Booking</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="static/css/flightbook.css">
</head>
<body>
    <div class="header">
        <div class="logo">
            <i class="fas fa-plane"></i> SkyWays
        </div>
        <div class="header-buttons">
            <a href="authenticate.jsp" class="header-button">Log In</a>
            <a href="authenticate.jsp" class="header-button">Sign Up</a>
        </div>
    </div>
    
    <div class="container">
        <h2>Book Your Flight</h2>
        <div class="content">
            <%
                // Extract the flightNumber parameter from the request
                String flightNumber = request.getParameter("flightNumber");
                String Departure = request.getParameter("Departure");
                String Destination = request.getParameter("Destination");
                String Date = request.getParameter("Date");
                String Time = request.getParameter("Time");
                String Price = request.getParameter("Price");
                String DepartureCode = request.getParameter("DepartureCode");
                String DestinationCode = request.getParameter("DestinationCode");
            %>
            
            <form id="bookingForm" action="confirmBooking" method="post">
                <input type="hidden" id="flightNumber" name="flightId" value="<%= flightNumber %>">
                <input type="hidden" id="Departure" name="Departure" value="<%= Departure %>">
                <input type="hidden" id="Departure" name="DepartureCode" value="<%= DepartureCode %>">
                <input type="hidden" id="Departure" name="DestinationCode" value="<%= DestinationCode %>">
                <input type="hidden" id="Destination" name="Destination" value="<%= Destination %>">
                <input type="hidden" id="Date" name="Date" value="<%= Date %>">
                <input type="hidden" id="Time" name="Time" value="<%= Time %>">
                
                <div class="form-section">Passenger Information</div>
                
                <label for="name"><i class="fas fa-user"></i> Full Name</label>
                <input type="text" id="name" name="name" required placeholder="Enter your full name">
                
                <label for="email"><i class="fas fa-envelope"></i> Email Address</label>
                <input type="email" id="email" name="email" required placeholder="Enter your email">
                
                <label for="age"><i class="fas fa-birthday-cake"></i> Age</label>
                <input type="number" id="age" name="age" required min="0" max="120" placeholder="Enter your age">
                
                <label for="gender"><i class="fas fa-venus-mars"></i> Gender</label>
                <select id="gender" name="gender" required>
                    <option value="">Select Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
                
                <label for="dob"><i class="fas fa-calendar-alt"></i> Date of Birth</label>
                <input type="date" id="dob" name="dob" required>
                
                <label for="phone"><i class="fas fa-phone"></i> Phone Number</label>
                <input type="tel" id="phone" name="phone" required placeholder="Enter your phone number">
                
                <div class="form-section">Travel Details</div>
                
                <label for="address"><i class="fas fa-address-card"></i> Address</label>
                <input type="text" id="address" name="address" required placeholder="Enter your address">
                
                <label for="seat"><i class="fas fa-chair"></i> Preferred Seat</label>
                <input type="text" id="seat" name="seat" required placeholder="e.g., Window, Aisle, etc.">
                
                <div class="form-section">Payment Information</div>
                
                <div class="credit-card-container">
                    <label for="creditCard"><i class="fas fa-credit-card credit-card-icon"></i> Card Number</label>
                    <input type="text" id="creditCard" name="creditCard" required placeholder="XXXX XXXX XXXX XXXX">
                    
                    <div class="row">
                        <div class="col-md-6">
                            <label for="expiryDate"><i class="far fa-calendar-alt credit-card-icon"></i> Expiry Date</label>
                            <input type="text" id="expiryDate" name="expiryDate" required placeholder="MM/YY">
                        </div>
                        <div class="col-md-6">
                            <label for="cvv"><i class="fas fa-lock credit-card-icon"></i> CVV</label>
                            <input type="password" id="cvv" name="cvv" required placeholder="XXX">
                        </div>
                    </div>
                    
                    <label for="nameOnCard"><i class="fas fa-signature credit-card-icon"></i> Name on Card</label>
                    <input type="text" id="nameOnCard" name="nameOnCard" required placeholder="Enter name on card">
                </div>
                
                <button type="submit"><i class="fas fa-check-circle"></i> Confirm Booking</button>
            </form>
            
            <div class="flight-details">
                <h3><i class="fas fa-plane"></i> Flight Details</h3>
                <p>
                    <span><i class="fas fa-ticket-alt"></i> Flight Number:</span> 
                    <strong><%= flightNumber %></strong>
                </p>
                <p>
                    <span><i class="fas fa-plane-departure"></i> From:</span> 
                    <strong><%= Departure %> (<%= DepartureCode %>)</strong>
                </p>
                <p>
                    <span><i class="fas fa-plane-arrival"></i> To:</span> 
                    <strong><%= Destination %> (<%= DestinationCode %>)</strong>
                </p>
                <p>
                    <span><i class="fas fa-calendar-alt"></i> Date:</span> 
                    <strong><%= Date %></strong>
                </p>
                <p>
                    <span><i class="far fa-clock"></i> Time:</span> 
                    <strong><%= Time %></strong>
                </p>
                <p>
                    <span><i class="fas fa-dollar-sign"></i> Total Price:</span> 
                    <strong>$<%= Price %></strong>
                </p>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="static/js/bookflight.js"></script>
</body>
</html>