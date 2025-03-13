<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Search</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome CSS for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="static/index.css">
</head>
<body>
    <div class="header">
        <div class="logo">
            <i class="fas fa-plane"></i> Airline
        </div>
        <div class="header-buttons">
            <p><b>Welcome back, <%= request.getAttribute("username") %> !</b></p>
            <a href="authenticate.jsp" class="header-button">logout</a>
        </div>
    </div>
    <div class="center-container">
        <div class="container">
            <h1>Flight Search</h1>
            <form id="searchForm"  action="searchFlights" method="post">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="source"><i class="fas fa-plane-departure"></i> Source:</label>
                        <input type="text" class="form-control" id="source" name="source" placeholder="Departure Airport" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="destination"><i class="fas fa-plane-arrival"></i> Destination:</label>
                        <input type="text" class="form-control" id="destination" name="destination"  placeholder="Destination Airport" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="date"><i class="fas fa-calendar-alt"></i> Date:</label>
                        <input type="date" class="form-control" id="date" name="date" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="adults"><i class="fas fa-user"></i> Adults:</label>
                        <input type="number" class="form-control" id="adults" name="adults" min="1" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="children"><i class="fas fa-child"></i> Children:</label>
                        <input type="number" class="form-control" id="children" name="children" min="0">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="infants"><i class="fas fa-baby"></i> Infants:</label>
                        <input type="number" class="form-control" id="infants" name="infants" min="0">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label><i class="fas fa-plane"></i> Flight Type:</label>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="oneWay" name="flightType" class="custom-control-input" value="one-way" checked>
                            <label class="custom-control-label" for="oneWay">One Way</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" id="return" name="flightType" class="custom-control-input" value="return">
                            <label class="custom-control-label" for="return">Return</label>
                        </div>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="class"><i class="fas fa-money-bill-wave"></i> Class:</label>
                        <select type="option" class="form-group col-md-5" id="class" name="class">
                            <option value="economy">Economy</option>
                            <option value="business">Business</option>
                            <option value="economy-plus">Economy Plus</option>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Search Flights</button>
            </form>
        </div>
    </div> 
    <!-- Bootstrap JS and Font Awesome JS for icons -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
</body>
</html>
