document.addEventListener('DOMContentLoaded', function() {
    // Add animation to flight rows
    const tableRows = document.querySelectorAll('.flight-row');
    
    tableRows.forEach((row, index) => {
      row.style.animation = `fadeIn 0.3s ease forwards ${index * 0.1}s`;
      row.style.opacity = '0';
    });
    
    // Initialize tooltips
    if (typeof bootstrap !== 'undefined') {
      const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
      tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
      });
    }
    
    // Add event listeners to book buttons
    const bookButtons = document.querySelectorAll('.btn-book');
    
    bookButtons.forEach(button => {
      button.addEventListener('click', function() {
        button.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Processing...';
      });
    });
  });
  
  // Animation keyframes
  if (!document.getElementById('animations-css')) {
    const style = document.createElement('style');
    style.id = 'animations-css';
    style.textContent = `
      @keyframes fadeIn {
        from {
          opacity: 0;
          transform: translateY(10px);
        }
        to {
          opacity: 1;
          transform: translateY(0);
        }
      }
    `;
    document.head.appendChild(style);
  }