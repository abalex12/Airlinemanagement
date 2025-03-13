<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <!-- <link rel="stylesheet" href="static/searchResult.css"> -->
</head>
<body>
    <!-- Header -->
    <div class="header">
        <div class="logo"><i class="fas fa-plane"></i> Airline</div>
        <div class="header-buttons">
            <a href="#" class="header-button">Login</a>
            <a href="#" class="header-button">Sign Up</a>
            <a href="#" class="header-button">Logout</a>
        </div>
    </div>

    <!-- Search Results Container -->
    <div class="container">
        <h2>
            <c:if test="${empty flights}">
                No flights available for the selected day
            </c:if>
            <c:if test="${not empty flights}">
                FOUND RESULTS
            </c:if>
        </h2>
        
        <c:if test="${not empty flights}">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>Flight ID</th>
                        <th><i class="fas fa-plane-departure"></i> Departure Airport Code</th>
                        <th><i class="fas fa-plane-departure"></i> Departure City</th>
                        <th><i class="fas fa-plane-arrival"></i> Destination Airport Code</th>
                        <th><i class="fas fa-plane-arrival"></i> Destination City</th>
                        <th><i class="fas fa-calendar-alt"></i> Destination Date</th>
                        <th><i class="far fa-clock"></i> Departure Time</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through the flights and generate table rows dynamically -->
                    <c:forEach var="flight" items="${flights}">
                        <tr>
                            <td><b>${flight.flight_Id}</b></td>
                            <td><b><i class="fas fa-plane-departure"></i> ${flight.departureAirportCode}</b></td>
                            <td><b><i class="fas fa-plane-departure"></i> ${flight.departure_city}</b></td>
                            <td><b><i class="fas fa-plane-arrival"></i> ${flight.arrivalAirportCode}</b></td>
                            <td><b><i class="fas fa-plane-arrival"></i> ${flight.arrival_city}</b></td>
                            <td><b><i class="fas fa-calendar-alt"></i> ${flight.departureDate}</b></td>
                            <td><b><i class="far fa-clock"></i> ${flight.departureTime}</b></td>
                            <td><b><i class="fas fa-dollar-sign"></i> ${flight.price}</b></td>
                            <td>
                                <form action="bookflight.jsp" method="get">
                                    <input type="hidden" name="flightNumber" value="${flight.flight_Id}">
                                    <input type="hidden" name="Departure" value="${flight.departure_city}">
                                    <input type="hidden" name="Destination" value="${flight.arrival_city}">
                                    <input type="hidden" name="DepartureCode" value="${flight.departureAirportCode}">
                                    <input type="hidden" name="DestinationCode" value="${flight.arrivalAirportCode}">
                                    <input type="hidden" name="Date" value="${flight.departureDate}">
                                    <input type="hidden" name="Time" value="${flight.departureTime}">
                                    <input type="hidden" name="Price" value="${flight.price}">
                                    <button type="submit" class="btn btn-book"><i class="fas fa-plane"></i> Book</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

    <!-- Bootstrap JS and Font Awesome JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>