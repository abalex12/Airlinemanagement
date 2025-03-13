package servlet;

import model.Passenger;
import model.Reservation;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.io.PrintWriter;
import javax.mail.Message;
// import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
// import javax.mail.Transport;
// import javax.mail.internet.InternetAddress;
// import javax.mail.internet.MimeMessage;
import dao.BookingDAO;
import dao.PassengerDao;

import java.io.IOException;
// import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.sql.Date;
// import java.util.Properties;

@WebServlet("/confirmBooking")
public class bookingServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    Date dob = Date.valueOf(request.getParameter("dob"));
    String phone = request.getParameter("phone");
    String seat = request.getParameter("seat");
    String departure = request.getParameter("Departure");
    String destination = request.getParameter("Destination");
    String date = request.getParameter("Date");
    String time = request.getParameter("Time");
    String departureCode = request.getParameter("DepartureCode");
    String destinationCode = request.getParameter("DestinationCode");
    System.out.println("gender: " + gender);

    int flightid = Integer.valueOf(request.getParameter("flightId"));
    PassengerDao passenger = new PassengerDao();
    int passengerId = (passenger.getLastPassengerId()) + 1;
    Passenger newPassenger = new Passenger(passengerId, name, gender, email, phone, dob);

    passenger.addPassenger(newPassenger);

    // Create object of bookingDAO to access the methods of lastbookingId and addreservaion
    BookingDAO book = new BookingDAO();
    // Retrieve the last booking Id
    int lastBookingId = (book.getLastBookingId()) + 1;
    Reservation reservation = new Reservation(lastBookingId, passengerId, flightid, null, seat);
    boolean result = book.addReservation(reservation);

    if (result) {
        // Send email instead of forwarding to JSP
        boolean emailSent = sendConfirmationEmail(email, name, seat, departure, destination, 
                departureCode, destinationCode, flightid, date, time, lastBookingId);
        
        // Display success message
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Booking Successful</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }");
        out.println(".container { max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; }");
        out.println(".success { color: green; font-size: 24px; margin-bottom: 20px; }");
        out.println(".icon { font-size: 48px; color: green; margin-bottom: 20px; }");
        out.println("a.button { display: inline-block; padding: 10px 20px; background-color: #3366cc; color: white; text-decoration: none; border-radius: 4px; margin-top: 20px; }");
        out.println("</style></head><body>");
        
        out.println("<div class='container'>");
        out.println("<div class='icon'>✓</div>");
        out.println("<h1 class='success'>Booking Successful!</h1>");
        
        if (emailSent) {
            out.println("<p>Your booking has been confirmed and the boarding pass has been sent to: <strong>" + email + "</strong></p>");
            out.println("<p>Please check your email for your boarding pass details.</p>");
        } else {
            out.println("<p>Your booking has been confirmed but we couldn't send the confirmation email.</p>");
            out.println("<p>Please contact customer support if you need your booking details.</p>");
        }
        
        out.println("<a href='index.jsp' class='button'>Return to Home</a>");
        out.println("</div></body></html>");
    } else {
        // Handle reservation failure
        response.sendRedirect("booking-failed.jsp");
    }
    
}

private boolean sendConfirmationEmail(String email, String name, String seat, String departure, 
                                    String destination, String departureCode, String destinationCode, 
                                    int flightid, String date, String time, int bookingId) {
    
    // Email configuration
    final String username = "abr099684@gmail.com"; 
    final String password = "*****************";
    
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    
    // Create session
    Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    
    try {
        // Create message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Your Flight Booking Confirmation - " + departure + " to " + destination);
        
        // Calculate boarding time (30 minutes before departure)
        String boardingTime = calculateBoardingTime(time);
        
        // Create HTML email content with exact CSS styling
        String emailContent = 
        "<!DOCTYPE html>" +
        "<html lang='en'>" +
        "<head>" +
        "    <meta charset='utf-8'>" +
        "    <meta name='viewport' content='width=device-width, initial-scale=1'>" +
        "    <title>Your Boarding Pass</title>" +
        "    <link href='https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap' rel='stylesheet'>" +
        "    <style>" +
        "        * { margin: 0; padding: 0; box-sizing: border-box; }" +
        "        body { font-family: 'Roboto', sans-serif; background-color: #f5f5f5; color: #333; line-height: 1.6; }" +
        "        .email-container { max-width: 600px; margin: 0 auto; background: #ffffff; }" +
        "        .header { background: #18265B; color: white; padding: 20px; text-align: center; }" +
        "        .header h1 { font-size: 24px; font-weight: 700; margin-bottom: 10px; }" +
        "        .header p { font-size: 16px; opacity: 0.9; }" +
        "        .message { padding: 20px; text-align: center; background: white; }" +
        "        .message h2 { color: #18265B; margin-bottom: 10px; }" +
        "        .message p { margin-bottom: 15px; color: #555; }" +
        
        "        .boarding-pass { margin: 20px auto; max-width: 450px; background: white; " +
        "                         border-radius: 15px; overflow: hidden; box-shadow: 0 10px 30px rgba(0,0,0,0.1); }" +
        
        "        .boarding-pass__header { background: linear-gradient(135deg, #4A90E2, #18265B); color: white; padding: 20px; position: relative; }" +
        "        .logo { text-align: center; margin-bottom: 15px; }" +
        "        .logo span { font-size: 18px; font-weight: 700; letter-spacing: 1px; text-transform: uppercase; }" +
        "        .logo img { height: 40px; }" +
        
        "        .flight-info { display: flex; justify-content: space-between; align-items: center; margin-top: 10px; }" +
        "        .airport { text-align: center; }" +
        "        .airport-code { font-size: 32px; font-weight: 700; }" +
        "        .airport-name { font-size: 12px; opacity: 0.9; }" +
        
        "        .route-line { flex-grow: 1; margin: 0 15px; position: relative; height: 4px; background: rgba(255,255,255,0.3); }" +
        "        .route-line::before, .route-line::after { content: ''; display: block; width: 8px; height: 8px; " +
        "                                                 background: white; border-radius: 50%; position: absolute; top: -2px; }" +
        "        .route-line::before { left: 0; }" +
        "        .route-line::after { right: 0; }" +
        "        .route-line .plane { position: absolute; font-size: 20px; top: -13px; left: 50%; transform: translateX(-50%) rotate(90deg); }" +
        
        "        .flight-details { display: flex; justify-content: space-between; padding: 20px; background: #f9f9f9; border-bottom: 1px dashed #ddd; }" +
        "        .detail-group { text-align: center; }" +
        "        .detail-label { font-size: 10px; text-transform: uppercase; color: #888; letter-spacing: 0.5px; margin-bottom: 5px; }" +
        "        .detail-value { font-size: 16px; font-weight: 700; color: #333; }" +
        
        "        .passenger-details { padding: 20px; border-bottom: 1px dashed #ddd; display: flex; justify-content: space-between; }" +
        "        .qr-code { background: url('https://api.qrserver.com/v1/create-qr-code/?size=100x100&data=BP" + bookingId + "') no-repeat center center; " +
        "                  width: 100px; height: 100px; background-size: contain; }" +
        
        "        .boarding-info { padding: 20px; background: #f9f9f9; display: flex; justify-content: space-between; }" +
        "        .barcode { height: 50px; margin-top: 15px; background: repeating-linear-gradient(90deg, #000, #000 2px, #fff 2px, #fff 4px); }" +
        
        "        .footer { text-align: center; padding: 20px; background: #18265B; color: white; }" +
        "        .footer p { font-size: 12px; opacity: 0.8; margin-bottom: 5px; }" +
        
        "        /* Responsive adjustments */" +
        "        @media (max-width: 500px) {" +
        "            .boarding-pass { margin: 10px; }" +
        "            .airport-code { font-size: 26px; }" +
        "            .flight-details, .passenger-details { flex-direction: column; gap: 15px; }" +
        "            .detail-group { margin-bottom: 10px; text-align: left; display: flex; justify-content: space-between; }" +
        "            .detail-value { text-align: right; }" +
        "            .boarding-info { flex-direction: column; }" +
        "            .qr-code { margin: 0 auto; }" +
        "        }" +
        "    </style>" +
        "</head>" +
        "<body>" +
        "    <div class='email-container'>" +
        "        <div class='header'>" +
        "            <h1>Your Boarding Pass is Ready</h1>" +
        "            <p>Flight #" + flightid + " - " + date + "</p>" +
        "        </div>" +
        "        <div class='message'>" +
        "            <h2>Ready for Takeoff!</h2>" +
        "            <p>Your flight from " + departure + " to " + destination + " is confirmed.</p>" +
        "        </div>" +
        "        <div class='boarding-pass'>" +
        "            <div class='boarding-pass__header'>" +
        "                <div class='logo'>" +
        "                    <span>Boarding Pass</span>" +
        "                </div>" +
        "                <div class='flight-info'>" +
        "                    <div class='airport'>" +
        "                        <div class='airport-code'>" + departureCode + "</div>" +
        "                        <div class='airport-name'>" + departure + "</div>" +
        "                    </div>" +
        "                    <div class='route-line'>" +
        "                        <span class='plane'>✈</span>" +
        "                    </div>" +
        "                    <div class='airport'>" +
        "                        <div class='airport-code'>" + destinationCode + "</div>" +
        "                        <div class='airport-name'>" + destination + "</div>" +
        "                    </div>" +
        "                </div>" +
        "            </div>" +
        "            <div class='flight-details'>" +
        "                <div class='detail-group'>" +
        "                    <div class='detail-label'>Flight</div>" +
        "                    <div class='detail-value'>" + flightid + "</div>" +
        "                </div>" +
        "                <div class='detail-group'>" +
        "                    <div class='detail-label'>Date</div>" +
        "                    <div class='detail-value'>" + date + "</div>" +
        "                </div>" +
        "                <div class='detail-group'>" +
        "                    <div class='detail-label'>Boarding</div>" +
        "                    <div class='detail-value'>" + boardingTime + "</div>" +
        "                </div>" +
        "                <div class='detail-group'>" +
        "                    <div class='detail-label'>Departure</div>" +
        "                    <div class='detail-value'>" + time + "</div>" +
        "                </div>" +
        "            </div>" +
        "            <div class='passenger-details'>" +
        "                <div>" +
        "                    <div class='detail-group'>" +
        "                        <div class='detail-label'>Passenger</div>" +
        "                        <div class='detail-value'>" + name + "</div>" +
        "                    </div>" +
        "                    <div class='detail-group'>" +
        "                        <div class='detail-label'>Seat</div>" +
        "                        <div class='detail-value'>" + seat + "</div>" +
        "                    </div>" +
        "                    <div class='detail-group'>" +
        "                        <div class='detail-label'>Gate</div>" +
        "                        <div class='detail-value'>B5</div>" +
        "                    </div>" +
        "                </div>" +
        "                <div class='qr-code'></div>" +
        "            </div>" +
        "            <div class='boarding-info'>" +
        "                <div>" +
        "                    <div class='detail-group'>" +
        "                        <div class='detail-label'>Booking Reference</div>" +
        "                        <div class='detail-value'>" + bookingId + "</div>" +
        "                    </div>" +
        "                </div>" +
        "            </div>" +
        "            <div class='barcode'></div>" +
        "        </div>" +
        "        <div class='message'>" +
        "            <p>Please arrive at the airport at least 2 hours before departure time.</p>" +
        "            <p>Have a safe and pleasant journey!</p>" +
        "        </div>" +
        "        <div class='footer'>" +
        "            <p>This is an automated email, please do not reply.</p>" +
        "            <p>© 2025 Your Airline. All rights reserved.</p>" +
        "        </div>" +
        "    </div>" +
        "</body>" +
        "</html>";
        // Set content type to HTML
        message.setContent(emailContent, "text/html; charset=utf-8");
        
        // Send message
        Transport.send(message);
        System.out.println("Confirmation email sent to " + email);
        return true;
        
    } catch (MessagingException e) {
        e.printStackTrace();
        System.out.println("Failed to send confirmation email: " + e.getMessage());
        return false;
    }
}

// Helper method to calculate boarding time (30 minutes before departure)
private String calculateBoardingTime(String departureTime) {
    try {
        // Parse the departure time
        String[] parts = departureTime.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        
        // Subtract 30 minutes
        minutes -= 30;
        if (minutes < 0) {
            minutes += 60;
            hours -= 1;
            if (hours < 0) {
                hours += 24;
            }
        }
        
        // Format back to string
        return String.format("%02d:%02d", hours, minutes);
    } catch (Exception e) {
        System.out.println("Error calculating boarding time: " + e.getMessage());
        return departureTime; // Return original time if there's an error
    }
}
}
        /*if (result){
            PrintWriter out=response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Booking Confirmation</title>");
            out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"static/searchResult.css\">");
            out.println("<style>");
            out.println(".confirmation-container {");
            out.println("  background-color: #f8f9fa;");
            out.println("  border-radius: 10px;");
            out.println("  box-shadow: 0 0 10px rgba(0, 0, 0, 0.9);");
            out.println("  padding: 20px;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Centered Container
            out.println("<div class=\"container d-flex justify-content-center align-items-center\" style=\"height: 100vh;\">");
            out.println("<div class=\"text-center confirmation-container\">"); // Added confirmation-container class
            out.println("<h1><i class=\"fas fa-check-circle text-success\"></i> Successfully Booked</h1>");
            out.println("<p>Please review your booking details:</p>");

            // Display Booking Details
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\"><b>Booking Id:</b></div>");
            out.println("<div class=\"col\">" + lastBookingId + "</div>");
            out.println("</div>");
            out.println("<div class=\"row\">");
            out.println("<div class=\"col\"><b>Flight ID:</b></div>");
            out.println("<div class=\"col\">" + flightid + "</div>");
            out.println("</div>");

            out.println("<div class=\"row\">");
            out.println("<div class=\"col\"><b>Name:</b></div>");
            out.println("<div class=\"col\">" + name + "</div>");
            out.println("</div>");

            out.println("<div class=\"row\">");
            out.println("<div class=\"col\"><b>Seat:</b></div>");
            out.println("<div class=\"col\">" + seat + "</div>");
            out.println("</div>");

            out.println("</div>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");


        }  
        
    }
}
*/