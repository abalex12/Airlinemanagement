package servlet;

import model.Aircraft;
import model.Airport;
import model.Flight;
import dao.AdminAddDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/addData")
public class AdminServlet extends HttpServlet {

    private AdminAddDao flightDao;

    @Override
    public void init() throws ServletException {
        super.init();
        flightDao = new AdminAddDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && !action.isEmpty()) {
            switch (action) {
                case "addFlight":
                    addFlight(request, response);
                    break;
                case "addAirport":
                    addAirport(request, response);
                    break;
                case "addAircraft":
                    addAircraft(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }

    private void addFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String departure_airport_code=request.getParameter("departure_airport_code");
      String departure_airport_name=request.getParameter("departure_airport_name");
      String departure_city=request.getParameter("departure_city");
      String arrival_airport_code=request.getParameter("arrival_airport_code");
      String arrival_airport_name=request.getParameter("arrival_airport_name");
      String arrival_city=request.getParameter("arrival_city");
      Date departureDate = Date.valueOf(request.getParameter("departure_date"));
      String departure_time=request.getParameter("departure_time");
      String arrival_time=request.getParameter("arrival_time");
      String aircraft_code=request.getParameter("aircraft_code");
      Double price=Double.valueOf(request.getParameter("price"));
      int flightId=flightDao.getLastFlightId();
      flightId+=1;
Flight newflight=new Flight();
          newflight.setFlight_Id(flightId);
          newflight.setDepartureAirportCode(departure_airport_code);
          newflight.setDepartureAirportName(departure_airport_name);
          newflight.setDeparture_city(departure_city);
          newflight.setArrivalAirportCode(arrival_airport_code);
          newflight.setArrivalAirportName(arrival_airport_name);
          newflight.setArrival_city(arrival_city);
          newflight.setDepartureDate(departureDate);
          newflight.setDepartureTime(departure_time);
          newflight.setArrivalTime(arrival_time);
          newflight.setAircraftCode(aircraft_code);
          newflight.setprice(price);
boolean result=flightDao.addFlight(newflight);
if(result){
System.out.println("successfull");
PrintWriter out = response.getWriter();
out.println("successfull");
}
else{
System.out.println("error");
}
}
    private void addAirport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String airportCode = request.getParameter("airportCode");
        String airportName = request.getParameter("airportName");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        // Create Airport object
        AdminAddDao Airport=new AdminAddDao();
        Airport newAirport = new Airport();
        newAirport.setAirportCode(airportCode);
        newAirport.setName(airportName);
        newAirport.setCity(city);
        newAirport.setCountry(country);

        // Call AirportDao to add the airport
        boolean result = Airport.addAirport(newAirport);

        // Send response based on the result
        PrintWriter out = response.getWriter();
        if (result) {
            out.println("Airport added successfully");
        } else {
            out.println("Error adding airport");
        }
    }

    private void addAircraft(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String aircraftCode = request.getParameter("aircraftCode");
        String aircraftType = request.getParameter("aircraftType");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        // Create Aircraft object
        AdminAddDao aircraft=new AdminAddDao();
        Aircraft newAircraft = new Aircraft();
       
        newAircraft.setAircraftCode(aircraftCode);
        newAircraft.setModel(aircraftType);
        newAircraft.setCapacity(capacity);

        // Call AircraftDao to add the aircraft
        boolean result = aircraft.addAircraft(newAircraft);

        // Send response based on the result
        PrintWriter out = response.getWriter();
        if (result) {
            out.println("Aircraft added successfully");
        } else {
            out.println("Error adding aircraft");
        }
    }
}




