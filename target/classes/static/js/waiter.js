document.addEventListener("DOMContentLoaded", () => {
    const buttons = document.querySelectorAll('.table-btn');
    const audioContext = new (window.AudioContext || window.webkitAudioContext)();
    let checkInterval;

    // Load table status from localStorage
    function loadTableStatus() {
        const savedTables = localStorage.getItem('restaurantTables');
        if (!savedTables) return;
        
        const tables = JSON.parse(savedTables);
        updateButtonVisibility(tables);
    }

    // Update button visibility based on table status
    function updateButtonVisibility(tables) {
        buttons.forEach(button => {
            const tableId = parseInt(button.getAttribute('data-id'));
            const table = tables.find(t => t.id === tableId);
            
            if (table) {
                if (table.status === 'occupied') {
                    button.classList.remove('available');
                    button.style.opacity = '0.5';
                    button.style.pointerEvents = 'none';
                } else {
                    button.classList.add('available');
                    button.style.opacity = '1';
                    button.style.pointerEvents = 'auto';
                }
            }
        });
    }

    // Sound effects
    function playSelectSound() {
        const oscillator = audioContext.createOscillator();
        const gainNode = audioContext.createGain();
        
        oscillator.type = 'sine';
        oscillator.frequency.setValueAtTime(440, audioContext.currentTime);
        oscillator.frequency.exponentialRampToValueAtTime(880, audioContext.currentTime + 0.1);
        
        gainNode.gain.setValueAtTime(0.1, audioContext.currentTime);
        gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.2);
        
        oscillator.connect(gainNode);
        gainNode.connect(audioContext.destination);
        
        oscillator.start();
        oscillator.stop(audioContext.currentTime + 0.2);
    }

    // Initialize
    loadTableStatus();
    checkInterval = setInterval(loadTableStatus, 3000);

    // Button event listeners
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            playSelectSound();
            
            const tableId = this.getAttribute('data-id');
            const tableNumber = this.querySelector('.table-number').textContent;
            
            // Create glowing effect
            this.style.boxShadow = '0 0 25px #ff0066';
            setTimeout(() => {
                this.style.boxShadow = '0 0 20px rgba(0, 255, 0, 0.5)';
            }, 300);
            
            if (confirm(`Prepare for food at ${tableNumber}?`)) {
                // In a real app, you would update the table status here
                window.location.href = `/waiter/addOrders/Table${tableNumber}`;
            }
        });
        
        // Add hover sound effect
        button.addEventListener('mouseenter', () => {
            if (button.style.pointerEvents !== 'none') {
                const oscillator = audioContext.createOscillator();
                const gainNode = audioContext.createGain();
                
                oscillator.type = 'square';
                oscillator.frequency.setValueAtTime(220, audioContext.currentTime);
                gainNode.gain.setValueAtTime(0.05, audioContext.currentTime);
                gainNode.gain.exponentialRampToValueAtTime(0.01, audioContext.currentTime + 0.1);
                
                oscillator.connect(gainNode);
                gainNode.connect(audioContext.destination);
                
                oscillator.start();
                oscillator.stop(audioContext.currentTime + 0.1);
            }
        });
    });

    // Cleanup on page unload
    window.addEventListener('beforeunload', () => {
        clearInterval(checkInterval);
    });
});