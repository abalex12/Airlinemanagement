<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SkySearch - Find Your Perfect Flight</title>

    <!-- Font Awesome for icons -->
     <link rel="stylesheet" href="static/css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <header>
        <div class="container">
            <nav>
                <div class="logo">
                    <i class="fas fa-plane"></i> SkySearch
                </div>
                <ul class="nav-links">
                    <li><a href="#"><i class="fas fa-home"></i> Home</a></li>
                    <li><a href="#"><i class="fas fa-tag"></i> Deals</a></li>
                    <li><a href="#"><i class="fas fa-map-marker-alt"></i> Destinations</a></li>
                    <li><a href="#"><i class="fas fa-user"></i> Account</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main class="container">

        <section class="search-container">
            <div class="trip-type">
                <button class="trip-btn active" data-trip="round">Round Trip</button>
                <button class="trip-btn" data-trip="one-way">One Way</button>
                <button class="trip-btn" data-trip="multi-city">Multi-City</button>
            </div>

            <form class="search-form" id="searchForm"  action="searchFlights" method="post">

                <div class="form-group">
                    <label for="from"><i class="fas fa-plane-departure"></i> From</label>
                    <input type="text" id="from" name="from" list="cityList" placeholder="City or airport" required>
                </div>
                
                <div class="form-group">
                    <label for="to"><i class="fas fa-plane-arrival"></i> To</label>
                    <input type="text" id="to" name="to" list="cityList" placeholder="City or airport" required>
                </div>
                
                <datalist id="cityList">
                    <option value="Addis Ababa (ADD)">
                    <option value="New York">
                    <option value="London">
                    <option value="Dubai (DXB)">
                    <option value="Paris (CDG)">
                    <option value="Frankfurt (FRA)">
                    <option value="Tokyo (NRT)">
                    <option value="Sydney (SYD)">
                    <option value="Toronto (YYZ)">
                    <option value="Los Angeles (LAX)">
                    <option value="Beijing (PEK)">
                    <option value="Singapore (SIN)">
                    <option value="Hong Kong (HKG)">
                    <option value="Istanbul (IST)">
                    <option value="Mumbai (BOM)">
                </datalist>
                
                <div class="form-group">
                    <label for="depart"><i class="far fa-calendar-alt"></i> Departure</label>
                    <input type="date" id="depart" name="depart" required>
                </div>
                
                <div class="form-group" id="return-date-group">
                    <label for="return"><i class="far fa-calendar-alt"></i> Return</label>
                    <input type="date" id="return" name="return">
                </div>
                
                
                <div class="traveler-selection">
                    <label for="traveler-dropdown"><i class="fas fa-users"></i> Travelers</label>
                    <div class="traveler-dropdown-container">
                      <button id="traveler-dropdown" class="traveler-toggle">
                        <span id="traveler-summary">1 Adult</span>
                        <i class="fas fa-chevron-down"></i>
                      </button>
                      
                      <div class="traveler-dropdown-content" id="traveler-options">
                        <div class="traveler-type">
                          <div class="traveler-info">
                            <h4>Adults</h4>
                            <p>Age 12+</p>
                          </div>
                          <div class="traveler-counter">
                            <button class="decrement-btn" data-type="adult">-</button>
                            <span id="adult-count">1</span>
                            <button class="increment-btn" data-type="adult">+</button>
                          </div>
                        </div>
                        
                        <div class="traveler-type">
                          <div class="traveler-info">
                            <h4>Children</h4>
                            <p>Ages 2-11</p>
                          </div>
                          <div class="traveler-counter">
                            <button class="decrement-btn" data-type="child">-</button>
                            <span id="child-count">0</span>
                            <button class="increment-btn" data-type="child">+</button>
                          </div>
                        </div>
                        
                        <div class="traveler-type">
                          <div class="traveler-info">
                            <h4>Infants</h4>
                            <p>Under 2</p>
                          </div>
                          <div class="traveler-counter">
                            <button class="decrement-btn" data-type="infant">-</button>
                            <span id="infant-count">0</span>
                            <button class="increment-btn" data-type="infant">+</button>
                          </div>
                        </div>
                        
                        <div class="traveler-actions">
                          <button id="apply-travelers">Apply</button>
                        </div>
                      </div>
                    </div>
                  </div>
                
                <div class="form-group">
                    <label for="class"><i class="fas fa-chair"></i> Class</label>
                    <select id="class">
                        <option value="economy">Economy</option>
                        <option value="premium">Premium Economy</option>
                        <option value="business">Business</option>
                        <option value="first">First Class</option>
                    </select>
                </div>
                
                <button type="submit" class="search-btn"><i class="fas fa-search"></i> Search Flights</button>
            </form>
        </section>

        <div class="loading" id="loading">
            <div class="loading-spinner"></div>
            <p>Searching for the best flights...</p>
        </div>

        <section class="results-container" id="results-container">
            <div class="results-header">
                <div class="results-summary">
                    <div>
                        <div class="route-info">New York (JFK) → London (LHR)</div>
                        <div class="date-info">Mar 15, 2025 • 1 Adult • Economy</div>
                    </div>
                    <div>
                        <span id="results-count">125 results</span>
                    </div>
                </div>
                <div class="filter-options">
                    <button class="filter-btn"><i class="fas fa-sort"></i> Sort by: Best Match</button>
                    <button class="filter-btn"><i class="fas fa-sliders-h"></i> Filters</button>
                    <button class="filter-btn"><i class="fas fa-dollar-sign"></i> Price</button>
                    <button class="filter-btn"><i class="far fa-clock"></i> Duration</button>
                    <button class="filter-btn"><i class="fas fa-plane"></i> Airlines</button>
                </div>
            </div>

            <div class="flight-results" id="flight-results">
                <!-- Sample flight card will be cloned and populated by JS -->
                <div class="flight-card">
                    <div class="airline-info">
                        <img src="https://via.placeholder.com/60" alt="Airline Logo" class="airline-logo">
                        <div class="airline-name">British Airways</div>
                    </div>
                    <div class="flight-info">
                        <div class="flight-time departure">
                            <div class="time">08:30</div>
                            <div class="airport">JFK</div>
                        </div>
                        <div class="flight-path">
                            <span class="flight-duration">8h 15m</span>
                            <i class="fas fa-plane"></i>
                        </div>
                        <div class="flight-time arrival">
                            <div class="time">20:45</div>
                            <div class="airport">LHR</div>
                        </div>
                    </div>
                    <div class="flight-price">
                        <div class="price-amount">$649</div>
                        <button class="select-btn">Select</button>
                    </div>
                </div>
            </div>
        </section>

        <section class="features">
            <div class="feature-box">
                <div class="feature-icon">
                    <i class="fas fa-shield-alt"></i>
                </div>
                <h3>Price Match Guarantee</h3>
                <p>Found a better price? We'll match it and give you an extra 10% off your booking.</p>
            </div>
            <div class="feature-box">
                <div class="feature-icon">
                    <i class="fas fa-globe"></i>
                </div>
                <h3>Worldwide Coverage</h3>
                <p>Over 500+ airlines and 100,000+ routes across the globe to choose from.</p>
            </div>
            <div class="feature-box">
                <div class="feature-icon">
                    <i class="fas fa-headset"></i>
                </div>
                <h3>24/7 Support</h3>
                <p>Our travel experts are available round the clock to assist you with any queries.</p>
            </div>
        </section>

        <section class="deals">
            <div class="section-header">
                <h2>Featured Deals</h2>
                <a href="#" class="view-all">View All <i class="fas fa-arrow-right"></i></a>
            </div>
            <div class="deals-container">
                <div class="deal-card">
                    <div class="deal-img">
                        <img src="https://source.unsplash.com/random/300x200/?paris" alt="Paris">
                    </div>
                    <div class="deal-content">
                        <div class="deal-city">Paris, France</div>
                        <div class="deal-date">Apr 5 - Apr 12</div>
                        <div class="deal-price">
                            <div class="price">$599</div>
                            <button class="book-now">Book Now</button>
                        </div>
                    </div>
                </div>
                <div class="deal-card">
                    <div class="deal-img">
                        <img src="https://source.unsplash.com/random/300x200/?tokyo" alt="Tokyo">
                    </div>
                    <div class="deal-content">
                        <div class="deal-city">Tokyo, Japan</div>
                        <div class="deal-date">May 12 - May 20</div>
                        <div class="deal-price">
                            <div class="price">$799</div>
                            <button class="book-now">Book Now</button>
                        </div>
                    </div>
                </div>
                <div class="deal-card">
                    <div class="deal-img">
                        <img src="https://source.unsplash.com/random/300x200/?dubai" alt="Dubai">
                    </div>
                    <div class="deal-content">
                        <div class="deal-city">Dubai, UAE</div>
                        <div class="deal-date">Jun 8 - Jun 15</div>
                        <div class="deal-price">
                            <div class="price">$749</div>
                            <button class="book-now">Book Now</button>
                        </div>
                    </div>
                </div>
                <div class="deal-card">
                    <div class="deal-img">
                        <img src="https://source.unsplash.com/random/300x200/?bali" alt="Bali">
                    </div>
                    <div class="deal-content">
                        <div class="deal-city">Bali, Indonesia</div>
                        <div class="deal-date">Jul 1 - Jul 10</div>
                        <div class="deal-price">
                            <div class="price">$899</div>
                            <button class="book-now">Book Now</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
 
    <footer>
        <div class="container">
            <div class="footer-content">
                <div class="footer-about">
                    <div class="footer-logo">
                        <i class="fas fa-plane"></i> SkySearch
                    </div>
                    <p>Discover the world with SkySearch. We help millions of travelers find the best flights at the best prices every day.</p>
                    <div class="social-links">
                        <a href="#"><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-instagram"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
                <div class="footer-links">
                    <h4>Company</h4>
                    <ul>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Careers</a></li>
                        <li><a href="#">Press</a></li>
                        <li><a href="#">Blog</a></li>
                    </ul>
                </div>
                <div class="footer-links">
                    <h4>Support</h4>
                    <ul>
                        <li><a href="#">Help Center</a></li>
                        <li><a href="#">Contact Us</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                        <li><a href="#">Terms of Service</a></li>
                    </ul>
                </div>
                <div class="footer-links">
                    <h4>Travel Resources</h4>
                    <ul>
                        <li><a href="#">Travel Guides</a></li>
                        <li><a href="#">Flight Status</a></li>
                        <li><a href="#">Travel Deals</a></li>
                        <li><a href="#">Destinations</a></li>
                    </ul>
                </div>
            </div>
            <div class="footer-bottom">
                <p>&copy; 2025 SkySearch. All rights reserved.</p>
            </div>
        </div>
    </footer>
 
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Trip type selection
            const tripBtns = document.querySelectorAll('.trip-btn');
            const returnDateGroup = document.getElementById('return-date-group');
            
            tripBtns.forEach(btn => {
                btn.addEventListener('click', () => {
                    tripBtns.forEach(b => b.classList.remove('active'));
                    btn.classList.add('active');
                    
                    if (btn.dataset.trip === 'one-way') {
                        returnDateGroup.style.display = 'none';
                    } else {
                        returnDateGroup.style.display = 'block';
                    }
                });
            });
            
            // Calendar functionality
            const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
            const today = new Date();
            let currentMonth = today.getMonth();
            let currentYear = today.getFullYear();
            
            const departInput = document.getElementById('depart');
            const returnInput = document.getElementById('return');
            const departCalendar = document.getElementById('depart-calendar');
            const returnCalendar = document.getElementById('return-calendar');
            const departDates = document.getElementById('depart-dates');
            const returnDates = document.getElementById('return-dates');
            
            // Show departure calendar when clicking on departure input
            departInput.addEventListener('click', () => {
                departCalendar.style.display = 'block';
                returnCalendar.style.display = 'none';
                generateCalendar(currentMonth, currentYear, departDates, 'depart');
            });
            
            // Show return calendar when clicking on return input
            returnInput.addEventListener('click', () => {
                returnCalendar.style.display = 'block';
                departCalendar.style.display = 'none';
                generateCalendar(currentMonth, currentYear, returnDates, 'return');
            });
            
            // Hide calendars when clicking outside
            document.addEventListener('click', (e) => {
                if (!departInput.contains(e.target) && !departCalendar.contains(e.target)) {
                    departCalendar.style.display = 'none';
                }
                if (!returnInput.contains(e.target) && !returnCalendar.contains(e.target)) {
                    returnCalendar.style.display = 'none';
                }
            });
            
            // Navigation for departure calendar
            document.getElementById('prev-month').addEventListener('click', () => {
                currentMonth--;
                if (currentMonth < 0) {
                    currentMonth = 11;
                    currentYear--;
                }
                generateCalendar(currentMonth, currentYear, departDates, 'depart');
            });
            
            document.getElementById('next-month').addEventListener('click', () => {
                currentMonth++;
                if (currentMonth > 11) {
                    currentMonth = 0;
                    currentYear++;
                }
                generateCalendar(currentMonth, currentYear, departDates, 'depart');
            });
            
            // Navigation for return calendar
            document.getElementById('prev-month-return').addEventListener('click', () => {
                currentMonth--;
                if (currentMonth < 0) {
                    currentMonth = 11;
                    currentYear--;
                }
                generateCalendar(currentMonth, currentYear, returnDates, 'return');
            });
            
            document.getElementById('next-month-return').addEventListener('click', () => {
                currentMonth++;
                if (currentMonth > 11) {
                    currentMonth = 0;
                    currentYear++;
                }
                generateCalendar(currentMonth, currentYear, returnDates, 'return');
            });
            
            // Generate calendar
            function generateCalendar(month, year, container, type) {
                const calendarMonth = container.parentElement.querySelector('.calendar-month');
                calendarMonth.textContent = `${months[month]} ${year}`;
                
                container.innerHTML = '';
                
                const firstDay = new Date(year, month, 1).getDay();
                const daysInMonth = new Date(year, month + 1, 0).getDate();
                
                // Create empty cells for days before the first day of the month
                for (let i = 0; i < firstDay; i++) {
                    const dateDiv = document.createElement('div');
                    dateDiv.classList.add('calendar-date', 'inactive');
                    container.appendChild(dateDiv);
                }
                
                // Create cells for each day of the month
                for (let i = 1; i <= daysInMonth; i++) {
                    const dateDiv = document.createElement('div');
                    dateDiv.classList.add('calendar-date');
                    dateDiv.textContent = i;
                    
                    // Check if the date is today
                    if (year === today.getFullYear() && month === today.getMonth() && i === today.getDate()) {
                        dateDiv.classList.add('today');
                    }
                    
                    // Add event listener to select date
                    dateDiv.addEventListener('click', () => {
                        const selectedDate = new Date(year, month, i);
                        const formattedDate = formatDate(selectedDate);
                        
                        if (type === 'depart') {
                            departInput.value = formattedDate;
                            departCalendar.style.display = 'none';
                            
                            // Clear return date if it's before departure date
                            if (returnInput.value) {
                                const returnDate = parseDate(returnInput.value);
                                if (returnDate < selectedDate) {
                                    returnInput.value = '';
                                }
                            }
                        } else {
                            // Only allow return date to be after departure date
                            if (departInput.value) {
                                const departDate = parseDate(departInput.value);
                                if (selectedDate >= departDate) {
                                    returnInput.value = formattedDate;
                                    returnCalendar.style.display = 'none';
                                }
                            }
                        }
                    });
                    
                    container.appendChild(dateDiv);
                }
            }
            
            // Format date to display
            function formatDate(date) {
                const day = date.getDate();
                const month = date.getMonth() + 1;
                const year = date.getFullYear();
                return `${month}/${day}/${year}`;
            }
            
            // Parse date from input
            function parseDate(dateStr) {
                const [month, day, year] = dateStr.split('/').map(Number);
                return new Date(year, month - 1, day);
            }
            
            // Form submission
            const searchForm = document.getElementById('flight-search-form');
            const loadingDiv = document.getElementById('loading');
            const resultsContainer = document.getElementById('results-container');
            const flightResults = document.getElementById('flight-results');
            
            searchForm.addEventListener('submit', (e) => {
                e.preventDefault();
                
                // Validate form
                const from = document.getElementById('from').value;
                const to = document.getElementById('to').value;
                const depart = document.getElementById('depart').value;
                
                if (!from || !to || !depart) {
                    alert('Please fill in all required fields.');
                    return;
                }
                
                // Show loading animation
                loadingDiv.style.display = 'block';
                resultsContainer.style.display = 'none';
                
                // Simulate loading time
                setTimeout(() => {
                    loadingDiv.style.display = 'none';
                    
                    // Generate sample results
                    const fromCode = getAirportCode(from);
                    const toCode = getAirportCode(to);
                    
                    // Update results header
                    document.querySelector('.route-info').textContent = `${from} (${fromCode}) → ${to} (${toCode})`;
                    document.querySelector('.date-info').textContent = `${depart} • ${document.getElementById('travelers').options[document.getElementById('travelers').selectedIndex].text} • ${document.getElementById('class').options[document.getElementById('class').selectedIndex].text}`;
                    
                    // Clear previous results
                    flightResults.innerHTML = '';
                    
                    // Generate random flight results
                    const numberOfResults = Math.floor(Math.random() * 10) + 5;
                    document.getElementById('results-count').textContent = `${numberOfResults} results`;
                    
                    const airlines = ['American Airlines', 'British Airways', 'Delta Air Lines', 'Emirates', 'Lufthansa', 'Singapore Airlines', 'Qatar Airways', 'United Airlines'];
                    
                    for (let i = 0; i < numberOfResults; i++) {
                        const airline = airlines[Math.floor(Math.random() * airlines.length)];
                        const departTime = generateRandomTime();
                        const duration = Math.floor(Math.random() * 10) + 4; // 4-14 hours
                        const arrivalTime = calculateArrivalTime(departTime, duration);
                        const price = Math.floor(Math.random() * 600) + 400; // $400-$1000
                        
                        const flightCard = document.createElement('div');
                        flightCard.classList.add('flight-card');
                        flightCard.innerHTML = `
                            <div class="airline-info">
                                <img src="https://via.placeholder.com/60?text=${airline.charAt(0)}" alt="${airline} Logo" class="airline-logo">
                                <div class="airline-name">${airline}</div>
                            </div>
                            <div class="flight-info">
                                <div class="flight-time departure">
                                    <div class="time">${departTime}</div>
                                    <div class="airport">${fromCode}</div>
                                </div>
                                <div class="flight-path">
                                    <span class="flight-duration">${duration}h ${Math.floor(Math.random() * 55)}m</span>
                                    <i class="fas fa-plane"></i>
                                </div>
                                <div class="flight-time arrival">
                                    <div class="time">${arrivalTime}</div>
                                    <div class="airport">${toCode}</div>
                                </div>
                            </div>
                            <div class="flight-price">
                                <div class="price-amount">$${price}</div>
                                <button class="select-btn">Select</button>
                            </div>
                        `;
                        
                        flightResults.appendChild(flightCard);
                    }
                    
                    // Show results
                    resultsContainer.style.display = 'block';
                    
                    // Add event listeners to select buttons
                    document.querySelectorAll('.select-btn').forEach(btn => {
                        btn.addEventListener('click', () => {
                            alert('Flight selected! This would redirect to the booking page in a real application.');
                        });
                    });
                    
                    // Scroll to results
                    resultsContainer.scrollIntoView({ behavior: 'smooth' });
                }, 2000);
            });
            
            // Helper functions for flight results
            function getAirportCode(city) {
                const codes = {
                    'New York': 'JFK',
                    'London': 'LHR',
                    'Paris': 'CDG',
                    'Tokyo': 'HND',
                    'Sydney': 'SYD',
                    'Dubai': 'DXB',
                    'Singapore': 'SIN',
                    'Los Angeles': 'LAX',
                    'Chicago': 'ORD',
                    'Miami': 'MIA'
                };
                
                for (const [key, value] of Object.entries(codes)) {
                    if (city.toLowerCase().includes(key.toLowerCase())) {
                        return value;
                    }
                }
                
                // Return a random 3-letter code if no match
                return city.substring(0, 3).toUpperCase();
            }
            
            function generateRandomTime() {
                const hours = Math.floor(Math.random() * 24);
                const minutes = Math.floor(Math.random() * 4) * 15; // 0, 15, 30, or 45
                return `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}`;
            }
            
            function calculateArrivalTime(departTime, durationHours) {
                const [hours, minutes] = departTime.split(':').map(Number);
                let arrivalHours = hours + durationHours;
                const arrivalMinutes = minutes;
                
                if (arrivalHours >= 24) {
                    arrivalHours %= 24;
                }
                
                return `${arrivalHours.toString().padStart(2, '0')}:${arrivalMinutes.toString().padStart(2, '0')}`;
            }
            
            // Initialize calendar
            generateCalendar(currentMonth, currentYear, departDates, 'depart');
            generateCalendar(currentMonth, currentYear, returnDates, 'return');
            
            // Deal cards hover effect
            const dealCards = document.querySelectorAll('.deal-card');
            dealCards.forEach(card => {
                card.querySelector('.book-now').addEventListener('click', () => {
                    alert('This would take you to the booking page for this deal in a real application.');
                });
            });
        });
    </script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
  const travelerToggle = document.getElementById('traveler-dropdown');
  const travelerOptions = document.getElementById('traveler-options');
  const travelerSummary = document.getElementById('traveler-summary');
  const applyButton = document.getElementById('apply-travelers');
  
  // Traveler counts
  let counts = {
    adult: 1,
    child: 0,
    infant: 0
  };
  
  // Toggle dropdown
  travelerToggle.addEventListener('click', function() {
    travelerOptions.classList.toggle('show');
  });
  
  // Close dropdown when clicking outside
  document.addEventListener('click', function(event) {
    if (!event.target.closest('.traveler-dropdown-container')) {
      travelerOptions.classList.remove('show');
    }
  });
  
  // Handle increment/decrement buttons
  document.querySelectorAll('.increment-btn, .decrement-btn').forEach(button => {
    button.addEventListener('click', function() {
      const type = this.dataset.type;
      const isIncrement = this.classList.contains('increment-btn');
      
      if (isIncrement) {
        // Set maximum limits
        if ((type === 'adult' && counts.adult < 9) || 
            (type === 'child' && counts.child < 8) || 
            (type === 'infant' && counts.infant < 5 && counts.infant < counts.adult)) {
          counts[type]++;
        }
      } else {
        // Set minimum limits
        if ((type === 'adult' && counts.adult > 1) || 
            (type === 'child' && counts.child > 0) || 
            (type === 'infant' && counts.infant > 0)) {
          counts[type]--;
          
          // Adjust infants if adult count decreases
          if (type === 'adult' && counts.infant > counts.adult) {
            counts.infant = counts.adult;
            document.getElementById('infant-count').textContent = counts.infant;
          }
        }
      }
      
      // Update counter display
      document.getElementById(`${type}-count`).textContent = counts[type];
    });
  });
  
  // Apply button
  applyButton.addEventListener('click', function() {
    updateTravelerSummary();
    travelerOptions.classList.remove('show');
  });
  
  // Update traveler summary text
  function updateTravelerSummary() {
    let summary = [];
    
    if (counts.adult > 0) {
      summary.push(`${counts.adult} ${counts.adult === 1 ? 'Adult' : 'Adults'}`);
    }
    
    if (counts.child > 0) {
      summary.push(`${counts.child} ${counts.child === 1 ? 'Child' : 'Children'}`);
    }
    
    if (counts.infant > 0) {
      summary.push(`${counts.infant} ${counts.infant === 1 ? 'Infant' : 'Infants'}`);
    }
    
    travelerSummary.textContent = summary.join(', ');
  }
});
    </script>
 </body>
 </html>