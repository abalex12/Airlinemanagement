document.addEventListener('DOMContentLoaded', function() {
    // Add animation classes
    document.getElementById('bookingForm').classList.add('form-animate');
    
    // Credit card input formatting
    const creditCardInput = document.getElementById('creditCard');
    if (creditCardInput) {
      creditCardInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        let formattedValue = '';
        
        for (let i = 0; i < value.length; i++) {
          if (i > 0 && i % 4 === 0) {
            formattedValue += ' ';
          }
          formattedValue += value[i];
        }
        
        e.target.value = formattedValue.substring(0, 19); // Limit to 16 digits + spaces
      });
    }
    
    // Expiry date formatting (MM/YY)
    const expiryInput = document.getElementById('expiryDate');
    if (expiryInput) {
      expiryInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        
        if (value.length > 2) {
          value = value.substring(0, 2) + '/' + value.substring(2, 4);
        }
        
        e.target.value = value.substring(0, 5); // MM/YY format (5 chars)
      });
    }
    
    // Form validation
    const bookingForm = document.getElementById('bookingForm');
    if (bookingForm) {
      bookingForm.addEventListener('submit', function(e) {
        // Basic validation example - you can expand this
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const creditCard = document.getElementById('creditCard').value;
        
        if (!validateEmail(email)) {
          e.preventDefault();
          alert('Please enter a valid email address');
          return;
        }
        
        if (!validatePhone(phone)) {
          e.preventDefault();
          alert('Please enter a valid phone number');
          return;
        }
        
        if (creditCard.replace(/\s+/g, '').length < 16) {
          e.preventDefault();
          alert('Please enter a valid credit card number');
          return;
        }
        
        // Add loading state to button
        const submitButton = bookingForm.querySelector('button[type="submit"]');
        submitButton.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Processing...';
        submitButton.disabled = true;
      });
    }
    
    // Simple email validation
    function validateEmail(email) {
      const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return re.test(email);
    }
    
    // Simple phone validation
    function validatePhone(phone) {
      const re = /^[0-9]{10,15}$/;
      return re.test(phone.replace(/[^0-9]/g, ''));
    }
  });