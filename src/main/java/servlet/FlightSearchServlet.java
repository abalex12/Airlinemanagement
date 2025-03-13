package servlet;


import model.Flight;
import dao.FlightSearchDao;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchFlights")
public class FlightSearchServlet extends HttpServlet {
    private final FlightSearchDao flightSearchService = new FlightSearchDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String source = request.getParameter("from");
        String destination = request.getParameter("to");
        String date = request.getParameter("depart");
        System.out.println("date"+date);
        System.out.println("source"+source);
        System.out.println("destination"+destination);
        List<Flight> flights = flightSearchService.usersearchFlights(source, destination, date);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Flight Search Results</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\" rel=\"stylesheet\">");
        out.println("<link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap\" rel=\"stylesheet\">");
        out.println("<link rel=\"stylesheet\" href=\"static/css/searchresult.css\">");
        out.println("</head>");
        out.println("<body>");
        
        // Header
        out.println("<div class=\"header\">");
        out.println("<div class=\"logo\"><i class=\"fas fa-plane\"></i> SkySearch</div>");
        out.println("<div class=\"header-buttons\">");
        out.println("<a href=\"login.jsp\" class=\"header-button\">Log In</a>");
        out.println("<a href=\"signup.jsp\" class=\"header-button\">Sign Up</a>");
        out.println("<a href=\"logout\" class=\"header-button\">Logout</a>");
        out.println("</div>");
        out.println("</div>");
        
        // Search Results Container
        out.println("<div class=\"container\">");
        
        if (flights.isEmpty()) {
            out.println("<div class=\"no-results\">");
            out.println("<i class=\"fas fa-search\"></i>");
            out.println("<h2>No flights available</h2>");
            out.println("<p>We couldn't find any flights matching your search criteria. Please try different dates or destinations.</p>");
            out.println("<a href=\"search.jsp\" class=\"btn btn-book\"><i class=\"fas fa-search\"></i> New Search</a>");
            out.println("</div>");
        } else {
            out.println("<h2>Available Flights</h2>");
            out.println("<div class=\"results-card\">");
            out.println("<table class=\"table\">");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Flight</th>");
            out.println("<th>From</th>");
            out.println("<th>To</th>");
            out.println("<th>Date</th>");
            out.println("<th>Time</th>");
            out.println("<th>Price</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
        
            // Loop through the flights and generate table rows dynamically
            for (Flight flight : flights) {
                out.println("<tr class=\"flight-row\">");
                
                // Flight ID column
                out.println("<td>");
                out.println("<div class=\"flight-info\">");
                out.println("<span class=\"flight-id\">" + flight.getFlight_Id() + "</span>");
                out.println("</div>");
                out.println("</td>");
                
                // Departure column
                out.println("<td>");
                out.println("<div class=\"flight-info\">");
                out.println("<i class=\"fas fa-plane-departure text-primary\"></i>");
                out.println("<div>");
                out.println("<div class=\"city-name\">" + flight.getDeparture_city() + "</div>");
                out.println("<div class=\"airport-code\">" + flight.getDepartureAirportCode() + "</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</td>");
                
                // Arrival column
                out.println("<td>");
                out.println("<div class=\"flight-info\">");
                out.println("<i class=\"fas fa-plane-arrival text-primary\"></i>");
                out.println("<div>");
                out.println("<div class=\"city-name\">" + flight.getArrival_city() + "</div>");
                out.println("<div class=\"airport-code\">" + flight.getArrivalAirportCode() + "</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</td>");
                
                // Date column
                out.println("<td>");
                out.println("<div class=\"flight-info\">");
                out.println("<i class=\"fas fa-calendar-alt text-secondary\"></i>");
                out.println("<span>" + flight.getDepartureDate() + "</span>");
                out.println("</div>");
                out.println("</td>");
                
                // Time column
                out.println("<td>");
                out.println("<div class=\"flight-info\">");
                out.println("<i class=\"far fa-clock text-secondary\"></i>");
                out.println("<span>" + flight.getDepartureTime() + "</span>");
                out.println("</div>");
                out.println("</td>");
                
                // Price column
                out.println("<td>");
                out.println("<div class=\"price\">$" + flight.getprice() + "</div>");
                out.println("</td>");
                
                // Action column
                out.println("<td>");
                out.println("<form action=\"bookflight.jsp\" method=\"get\">");
                out.println("<input type=\"hidden\" name=\"flightNumber\" value=\"" + flight.getFlight_Id() + "\">");
                out.println("<input type=\"hidden\" name=\"Departure\" value=\"" + flight.getDeparture_city() + "\">");
                out.println("<input type=\"hidden\" name=\"Destination\" value=\"" + flight.getArrival_city() + "\">");
                out.println("<input type=\"hidden\" name=\"DepartureCode\" value=\"" + flight.getDepartureAirportCode() + "\">");
                out.println("<input type=\"hidden\" name=\"DestinationCode\" value=\"" + flight.getArrivalAirportCode() + "\">");
                out.println("<input type=\"hidden\" name=\"Date\" value=\"" + flight.getDepartureDate() + "\">");
                out.println("<input type=\"hidden\" name=\"Time\" value=\"" + flight.getDepartureTime() + "\">");
                out.println("<input type=\"hidden\" name=\"Price\" value=\"" + flight.getprice() + "\">");
                out.println("<button type=\"submit\" class=\"btn btn-book\" data-bs-toggle=\"tooltip\" title=\"Book this flight\"><i class=\"fas fa-ticket-alt\"></i> Book</button>");
                out.println("</form>");
                out.println("</td>");
                
                out.println("</tr>");
            }
        
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            
            // Additional information
            out.println("<div class=\"text-center mt-4\">");
            out.println("<p class=\"text-muted\">Showing " + flights.size() + " flights. Prices include taxes and fees.</p>");
            out.println("<a href=\"search.jsp\" class=\"btn btn-book\"><i class=\"fas fa-search\"></i> New Search</a>");
            out.println("</div>");
        }
        
        out.println("</div>");
        
        // Scripts
        out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>");
        out.println("<script src=\"static/js/search.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }
}
    