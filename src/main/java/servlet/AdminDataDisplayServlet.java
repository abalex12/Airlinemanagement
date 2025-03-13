package servlet;
import model.Aircraft;
import model.Airport;
import model.Flight;
import model.Passenger;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AdminSearch;

import java.io.IOException;
import java.util.List;

@WebServlet("/displayData")
public class AdminDataDisplayServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "displayFlightsForm":
                    displayFlights(request, response);
                    break;
                case "displayAirplanesForm":
                    displayAirplanes(request, response);
                    break;
                case "displayAirportsForm":
                    displayAirports(request, response);
                    break;
                case "displayPassengersForm":
                    displayPassengers(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid form submission");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form ID parameter is missing");
        }
    }

    private void displayFlights(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve date from the form
        String date = request.getParameter("date");
        AdminSearch newflight=new AdminSearch();
        List<Flight> flights=newflight.getFlightsByDate(date);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Search Results</title>");
        out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">");
        out.println("<link rel=\"stylesheet\" href=\"static/searchResult.css\">");
        out.println("</head>");
        out.println("<body>");

        // Header
        out.println("<div class=\"header\">");
        out.println("<div class=\"logo\"><i class=\"fas fa-plane\"></i> Airline</div>");
        out.println("<div class=\"header-buttons\">");
        out.println("<a href=\"#\" class=\"header-button\">Login</a>");
        out.println("<a href=\"#\" class=\"header-button\">Sign Up</a>");
        out.println("<a href=\"#\" class=\"header-button\">Logout</a>");
        out.println("</div>");
        out.println("</div>");

        // Search Results Container
        out.println("<div class=\"container\">");
        out.println("<h2>FOUND RESULTS</h2>");
        out.println("<table class=\"table\">");
        out.println("<thead class=\"thead-dark\">");
        out.println("<tr>");
        out.println("<th>Flight ID</th>");
        out.println("<th><i class=\"fas fa-plane-departure\"></i> Departure Airport Code</th>");
        out.println("<th><i class=\"fas fa-plane-departure\"></i> Departure City</th>");
        out.println("<th><i class=\"fas fa-plane-arrival\"></i> Destination Airport Code</th>");
        out.println("<th><i class=\"fas fa-plane-arrival\"></i> Destination City</th>");
        out.println("<th><i class=\"fas fa-calendar-alt\"></i> Destination Date</th>");
        out.println("<th><i class=\"far fa-clock\"></i> Departure Time</th>");
        out.println("<th> Price  </th>");
        out.println("<th>Edit</th>");
        out.println("<th>Delete</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        // Loop through the flights and generate table rows dynamically
        for (Flight flight : flights) {
            out.println("<tr>");
            out.println("<td><b>" + flight.getFlight_Id() + "</b></td>");
            out.println("<td><b><i class=\"fas fa-plane-departure\"></i> " + flight.getDepartureAirportCode() + "</b></td>");
            out.println("<td><b><i class=\"fas fa-plane-departure\"></i> " + flight.getDeparture_city() + "</b></td>");
            out.println("<td><b><i class=\"fas fa-plane-arrival\"></i> " + flight.getArrivalAirportCode() + "</b></td>");
            out.println("<td><b><i class=\"fas fa-plane-arrival\"></i> " + flight.getArrival_city() + "</b></td>");
            out.println("<td><b><i class=\"fas fa-calendar-alt\"></i> " + flight.getDepartureDate() + "</b></td>");
            out.println("<td><b><i class=\"far fa-clock\"></i> " + flight.getDepartureTime() + "</b></td>");
            out.println("<td><b><i class=\"fas fa-dollar-sign\"></i> " + flight.getprice() + "</b></td>");
            out.println("<td>");
            out.println("<form action=\"editFlight.jsp\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"flightNumber\" value=\"" + flight.getFlight_Id() + "\">");
            out.println("<input type=\"hidden\" name=\"Departure\" value=\"" + flight.getDeparture_city() + "\">");
            out.println("<input type=\"hidden\" name=\"Destination\" value=\"" + flight.getArrival_city() + "\">");
            out.println("<input type=\"hidden\" name=\"Date\" value=\"" + flight.getDepartureDate() + "\">");
            out.println("<input type=\"hidden\" name=\"Time\" value=\"" + flight.getDepartureTime() + "\">");
            out.println("<input type=\"hidden\" name=\"Price\" value=\"" + flight.getprice() + "\">");
            out.println("<button type=\"submit\" class=\"btn btn-book\"><i class=\"fas fa-plane\"></i>Edit</button>");
            out.println("</form>");
            out.println("</td>");
            out.println("<td>");
            out.println("<form action=\"deleteflight.jsp\" method=\"post\">"); 
            out.println("<input type=\"hidden\" name=\"flightNumber\" value=\"" + flight.getFlight_Id() + "\">");
            out.println("<button type=\"submit\" class=\"btn btn-book\"><i class=\"fas fa-plane\"></i>Delete</button>");
            out.println("</form>");
            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");

        // Bootstrap JS and Font Awesome JS
        out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>");
        out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }

    private void displayAirplanes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve airplane code from the form
        String airplaneCode = request.getParameter("AirplaneCode");
        AdminSearch newAdmin=new AdminSearch();
        // Query database for airplanes with the specified code
        List<Aircraft> aircrafts=newAdmin.getAircraftByCode(airplaneCode);
        
        // Send response with airplane information
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>  Aircrafts  </title>");
            out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"static/searchResult.css\">"); // Assuming you have a CSS file for styling
            out.println("</head>");
            out.println("<body>");

            // Header
            out.println("<div class=\"header\">");
            out.println("<div class=\"logo\"><i class=\"fas fa-plane\"></i> Airline</div>");
            out.println("<div class=\"header-buttons\">");
            out.println("<a href=\"#\" class=\"header-button\">Login</a>");
            out.println("<a href=\"#\" class=\"header-button\">Sign Up</a>");
            out.println("<a href=\"#\" class=\"header-button\">Logout</a>");
            out.println("</div>");
            out.println("</div>");

            // Search Results Container
            out.println("<div class=\"container\">");
            out.println("<h2>Airport by the code " + airplaneCode + "</h2>");
            out.println("<table class=\"table\">");
            out.println("<thead class=\"thead-dark\">");
            out.println("<tr>");
            out.println("<th>Aircraft Code</th>");
            out.println("<th>Aircraft Model</th>");
            out.println("<th>Capacity</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Loop through the airports and generate table rows dynamically
            for (Aircraft aircraft : aircrafts) {
                out.println("<tr>");
                out.println("<td><b>" + aircraft.getAircraftCode() + "</b></td>");
                out.println("<td><b>" + aircraft.getModel() + "</b></td>");
                out.println("<td><b>" + aircraft.getCapacity() + "</b></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            // Bootstrap JS and Font Awesome JS
            out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>");
            out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
            out.println("</body>");
            out.println("</html>");

    }

    private void displayAirports(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve airport name from the form
        String city = request.getParameter("city");
        AdminSearch newAdmin=new AdminSearch();
        List<Airport> airports=newAdmin.getAirportsByCity(city);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Airports in " + city + "</title>");
            out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"static/searchResult.css\">"); // Assuming you have a CSS file for styling
            out.println("</head>");
            out.println("<body>");

            // Header
            out.println("<div class=\"header\">");
            out.println("<div class=\"logo\"><i class=\"fas fa-plane\"></i> Airline</div>");
            out.println("<div class=\"header-buttons\">");
            out.println("<a href=\"#\" class=\"header-button\">Login</a>");
            out.println("<a href=\"#\" class=\"header-button\">Sign Up</a>");
            out.println("<a href=\"#\" class=\"header-button\">Logout</a>");
            out.println("</div>");
            out.println("</div>");

            // Search Results Container
            out.println("<div class=\"container\">");
            out.println("<h2>Airports in " + city + "</h2>");
            out.println("<table class=\"table\">");
            out.println("<thead class=\"thead-dark\">");
            out.println("<tr>");
            out.println("<th>Airport Code</th>");
            out.println("<th>Airport Name</th>");
            out.println("<th>City</th>");
            out.println("<th>Country</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Loop through the airports and generate table rows dynamically
            for (Airport airport : airports) {
                out.println("<tr>");
                out.println("<td><b>" + airport.getAirportCode() + "</b></td>");
                out.println("<td><b>" + airport.getName() + "</b></td>");
                out.println("<td><b>" + airport.getCity() + "</b></td>");
                out.println("<td><b>" + airport.getCountry() + "</b></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            // Bootstrap JS and Font Awesome JS
            out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>");
            out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
            out.println("</body>");
            out.println("</html>");

        
        
    }

    private void displayPassengers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve flight number from the form
        String flightNumber = request.getParameter("flightNumber");

        AdminSearch newAdmin=new AdminSearch();
        List<Passenger>passengers=newAdmin.getPassengersByFlightNumber(flightNumber); 
        PrintWriter out = response.getWriter();
        
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Passenger Details</title>");
            out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css\" rel=\"stylesheet\">");
            out.println("<link rel=\"stylesheet\" href=\"static/searchResult.css\">"); // Assuming you have a CSS file for styling
            out.println("</head>");
            out.println("<body>");

            // Header
            out.println("<div class=\"header\">");
            out.println("<div class=\"logo\"><i class=\"fas fa-plane\"></i> Airline</div>");
            out.println("<div class=\"header-buttons\">");
            out.println("<a href=\"#\" class=\"header-button\">Login</a>");
            out.println("<a href=\"#\" class=\"header-button\">Sign Up</a>");
            out.println("<a href=\"#\" class=\"header-button\">Logout</a>");
            out.println("</div>");
            out.println("</div>");

            // Passenger Details Container
            out.println("<div class=\"container\">");
            out.println("<h2>Passenger Details</h2>");
            out.println("<table class=\"table\">");
            out.println("<thead class=\"thead-dark\">");
            out.println("<tr>");
            out.println("<th>Passenger ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Email</th>");
            out.println("<th>Phone Number</th>");
            out.println("<th>Date of Birth</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Loop through the passengers and generate table rows dynamically
            for (Passenger passenger : passengers) {
                out.println("<tr>");
                out.println("<td><b>" + passenger.getPassengerId() + "</b></td>");
                out.println("<td><b>" + passenger.getName() + "</b></td>");
                out.println("<td><b>" + passenger.getGender() + "</b></td>");
                out.println("<td><b>" + passenger.getEmail() + "</b></td>");
                out.println("<td><b>" + passenger.getPhoneNumber() + "</b></td>");
                out.println("<td><b>" + passenger.getDateOfBirth() + "</b></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            // Bootstrap JS and Font Awesome JS
            out.println("<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js\"></script>");
            out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
            out.println("</body>");
            out.println("</html>");

    }
}
