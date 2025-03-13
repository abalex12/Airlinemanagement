<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Portal - Airline Reservation System</title>
   <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/admin.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <button class="toggle-sidebar-btn" onclick="toggleSidebar()"><i class="fas fa-bars"></i></button>
            <h1>Admin Portal - Airline Reservation System</h1>
            <button class="logout-btn" onclick="logout()"><i class="fas fa-sign-out-alt"></i> Logout</button>
        </div>
        <div class="sidebar">
            <h2>Navigation</h2>
            <button class="button" onclick="toggleSubMenu('addSubMenu')"><i class="fas fa-plus"></i> Add</button>
            <div id="addSubMenu" class="sub-menu">
                <button class="sub-button" onclick="toggleForm('flightForm')"><i class="fas fa-plane"></i> Add Flight</button>
                <button class="sub-button" onclick="toggleForm('airportForm')"><i class="fas fa-building"></i> Add Airport</button>
                <button class="sub-button" onclick="toggleForm('aircraftForm')"><i class="fas fa-plane-departure"></i> Add Aircraft</button>
            </div>
            <button class="button" onclick="toggleSubMenu('displaySubMenu')"><i class="fas fa-eye"></i> Display</button>
            <div id="displaySubMenu" class="sub-menu">
                <button class="sub-button" onclick="toggleForm('displayFlightsForm')"><i class="fas fa-plane-arrival"></i> Display Flights</button>
                <button class="sub-button" onclick="toggleForm('displayAirplanesForm')"><i class="fas fa-plane"></i> Display Airplanes</button>
                <button class="sub-button" onclick="toggleForm('displayAirportsForm')"><i class="fas fa-building"></i> Display Airports</button>
                <button class="sub-button" onclick="toggleForm('displayPassengersForm')"><i class="fas fa-user"></i> Passenger List</button>
            </div>
            <button class="button" onclick="toggleBodypart()"><i class="fas fa-cogs"></i> Manage</button>
            <div id="manageSubMenu" class="sub-menu">
            </div>
        </div>
    
        <div class="content">
            <div id="flightForm" class="form-container">
                <h2>Add New Flight</h2>
                <form name="flightForm" action="addData?action=addFlight" method="post">
                    <label for="departure_airport_code">Departure Airport Code:</label>
                    <input type="text" name="departure_airport_code" placeholder="Departure Airport code" required>
                    <label for="departure_airport_name">Departure Airport Name:</label>
                    <input type="text" name="departure_airport_name" placeholder="Departure Airport name" required>
                    <label for="departure_city">Departure City:</label>
                    <input type="text" name="departure_city" placeholder="Departure city" required>
                    <label for="arrival_airport_code">Arrival Airport Code:</label>
                    <input type="text" name="arrival_airport_code" placeholder="Arrival Airport code" required>
                    <label for="arrival_airport_name">Arrival Airport Name:</label>
                    <input type="text" name="arrival_airport_name" placeholder="Arrival Airport name" required>
                    <label for="arrival_city">Arrival City:</label>
                    <input type="text" name="arrival_city" placeholder="Arrival city" required>
                    <label for="departure_date">Departure Date:</label>
                    <input type="date" name="departure_date" placeholder="Departure date" required>
                    <label for="departure_time">Departure Time:</label>
                    <input type="text" name="departure_time" placeholder=" Departure time" required>
                    <label for="arrival_time">Arrival Time:</label>
                    <input type="text" name="arrival_time" placeholder=" Arrival time" required>
                    <label for="aircraft_code">Aircraft Code:</label>
                    <input type="text" name="aircraft_code" placeholder=" Aircraft code" required>
                    <label for="price">Price:</label> 
                    <input type="text" name="price" placeholder=" Price" required>
                    <input type="submit" value="Submit">
                </form>
            </div>
            <div id="airportForm" class="form-container">
                <h2>Add New Airport</h2>
                <form name="airportForm" action="addData?action=addAirport" method="post">
                    <label for="airportCode">Airport Code:</label>
                    <input type="text" name="airportCode" placeholder="Airport Code" required>
                    <label for="airportName">Airport Name:</label>
                    <input type="text" name="airportName" placeholder="Airport Name" required>
                    <label for="city">City:</label>
                    <input type="text" name="city" placeholder="city" required>
                    <label for="country">country:</label>
                    <input type="text" name="country" placeholder="country" required>
                    <input type="submit" value="Submit">
                </form>
            </div>
            <div id="aircraftForm" class="form-container">
                <h2>Add New Aircraft</h2>
                <form name="aircraftForm" action="addData?action=addAircraft" method="post">
                    <label for="aircraft_code">Aircraft Code:</label>
                    <input type="text" name="aircraftCode" placeholder="Aircraft Code" required>
                    <label for="aircraft_type">Aircraft Model:</label>
                    <input type="text" name="aircraftType" placeholder="Aircraft Type" required>
                    <label for="aircraft_type">Capacity:</label>
                    <input type="text" name="capacity" placeholder="Capacity" required>
                    <input type="submit" value="Submit">
                </form>
            </div>
            <div id="displayFlightsForm" class="form-container">
                <h2>Display Flights</h2>
                <form action="displayData?action=displayFlightsForm" method="post">
                    <label for="date">Select Date:</label>
                    <input type="date" id="date" name="date" required>
                    <input type="submit" value="Display">
                </form>
            </div>
            
            <div id="displayAirplanesForm" class="form-container">
                <h2>Display Airplanes</h2>
                <form action="displayData?action=displayAirplanesForm" method="post">
                    <label for="Airplanes">AirplaneCode:</label>
                    <input type="text" id="AirplaneCode" name="AirplaneCode" placeholder="Enter AirplaneCode" required>
                    <input type="submit" value="Display">
                </form>
            </div>
            
            <div id="displayAirportsForm" class="form-container">
                <h2>Display Airports</h2>
                <form action="displayData?action=displayAirportsForm" method="post">
                    <label for="AirportName">Airport city:</label>
                    <input type="text" id="city" name="city" placeholder="Enter city of the Airport" required>
                    <input type="submit" value="Display">
                </form>
            </div>
            
            <div id="displayPassengersForm" class="form-container">
                <h2>Passenger List</h2>
                <form action="displayData?action=displayPassengersForm" method="post">
                    <label for="flightNumber">Flight Number:</label>
                    <input type="text" id="flightNumber" name="flightNumber" placeholder="Enter Flight Number" required>
                    <input type="submit" value="Display">
                </form>
            </div>
            

        </div>
        <div class="bodypart" style="display: none;">
                  <div class="cont" onclick="toggleForm('flightForm')">
                    <i class="fas fa-plane"></i>
                    <h1>Add Flight</h1>
                  </div >
                  <div class="cont" onclick="toggleForm('airportForm')">
                    <i class="fas fa-building"></i>
                    <h1>Add Airport</h1>
                  </div>
                  <div class="cont" onclick="toggleForm('aircraftForm')">
                    <i class="fas fa-plane-departure"></i>
                    <h1>Add Aircraft</h1>
                  </div>
                  <div class="cont" onclick="toggleForm('displayFlightsForm')">
                    <i class="fas fa-plane-arrival"></i>
                    <h1>Display Flights</h1>
                  </div>
                  <div class="cont" onclick="toggleForm('displayPassengersForm')">
                    <i class="fas fa-user"></i>
                    <h1>Display Passenger List</h1>
                  </div>
                  <div class="cont" onclick="toggleForm('displayAirportsForm')">
                    <i class="fas fa-building"></i>
                    <h1>Display Airports</h1>
                  </div>
        </div>
    </div>
   
    <footer class="footer">
        <div class="container1">
            <p>&copy; 2024 Airline Reservation System</p>
        </div>
    </footer>
    
    <script>
        function toggleSidebar() {
            var sidebar = document.querySelector('.sidebar');
            var content = document.querySelector('.content');
            sidebar.classList.toggle('show-sidebar');
            content.classList.toggle('sidebar-open'); // Add a class to the content area
        }
    
        function toggleForm(formId) {
            var form = document.getElementById(formId);
            var forms = document.querySelectorAll('.form-container'); // Select all form containers
            var sidebar = document.querySelector('.sidebar');
            // Hide all forms except the one clicked
            forms.forEach(function(f) {
                if (f.id !== formId) {
                    f.classList.remove('show');
                }
            });
            
            // Toggle the clicked form
            form.classList.toggle('show');
            sidebar.classList.remove('show-sidebar'); // Close sidebar when form is shown
        }
    
        function toggleSubMenu(subMenuId) {
            var subMenu = document.getElementById(subMenuId);
            subMenu.classList.toggle('show');
            var subMenus = document.querySelectorAll('.sub-menu');
            subMenus.forEach(function(s) {
                if (s.id !== subMenuId && s.classList.contains('show')) {
                    s.classList.remove('show');
                }
            });
        }
    
    function toggleBodypart() {
    var bodypart = document.querySelector('.bodypart');

    if (bodypart.style.display === 'none') {
        bodypart.style.display = 'flex';
        bodypart.style.flexDirection = 'row';
        bodypart.style.flexWrap = 'wrap';
        bodypart.style.marginLeft = '15%';
        bodypart.style.marginBottom = '5%';
    } else {
        bodypart.style.display = 'none';
    }
}

    
        function logout() {
            alert("Logout clicked!");
        }
    </script>
    
    
</body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>

</html>
