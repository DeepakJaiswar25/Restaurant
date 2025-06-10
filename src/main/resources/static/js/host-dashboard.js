
let tables = [
    { id: 1, number: 1, status: 'occupied', capacity: 2},
    { id: 2, number: 2, status: 'open', capacity: 2 },
    { id: 3, number: 3, status: 'open', capacity: 2},
    { id: 4, number: 4, status: 'occupied', capacity: 4 },
    { id: 5, number: 5, status: 'open', capacity: 4},
    { id: 6, number: 6, status: 'occupied', capacity: 4},
    { id: 7, number: 7, status: 'open', capacity: 6},
    { id: 8, number: 8, status: 'open', capacity: 6},
];

// DOM Elements
const tablesContainer = document.getElementById('tables-container');
const filterButtons = document.querySelectorAll('.btn-filter');
const searchInput = document.querySelector('.search-box input');
const tableDetailsModal = new bootstrap.Modal('#tableDetailsModal');

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    loadTableStatus();
    renderTables();
    setupEventListeners();
});

// Render tables
function renderTables(filter = 'all', searchTerm = '') {
    tablesContainer.innerHTML = '';
    
    const filteredTables = tables.filter(table => {
        const matchesFilter = filter === 'all' || table.status === filter;
        const matchesSearch = table.number.toString().includes(searchTerm) || 
                            (table.server && table.server.toLowerCase().includes(searchTerm.toLowerCase()));
        return matchesFilter && matchesSearch;
    });
    
    filteredTables.forEach(table => {
        const tableCard = document.createElement('div');
        tableCard.className = `table-card ${table.status}`;
        tableCard.innerHTML = `
            <div class="table-header">
                <span class="table-number">Table ${table.number}</span>
                <span class="table-status">${table.status.toUpperCase()}</span>
            </div>
            <div class="table-details">
                <p>Capacity: ${table.capacity}</p>
                ${table.status === 'occupied' ? `<p>Server: ${table.server}</p>` : ''}
            </div>
            <div class="table-actions">
                <button class="btn btn-primary btn-action" onclick="handleSendCustomer(${table.id})">
                    <i class="fas fa-user-plus"></i> Send
                </button>
                <button class="btn ${table.status === 'occupied' ? 'btn-warning' : 'btn-success'} btn-action" 
                        onclick="handleToggleStatus(${table.id})">
                    ${table.status === 'occupied' ? 'Open' : 'Seat'}
                </button>
            </div>
        `;
        
        tableCard.addEventListener('click', (e) => {
            if (!e.target.closest('button')) {
                showTableDetails(table.id);
            }
        });
        
        tablesContainer.appendChild(tableCard);
    });
}

// Show table details in modal
function showTableDetails(tableId) {
    const table = tables.find(t => t.id === tableId);
    if (!table) return;
    
    document.getElementById('modal-table-number').textContent = table.number;
    document.getElementById('modal-table-status').textContent = table.status.charAt(0).toUpperCase() + table.status.slice(1);
    document.getElementById('modal-table-capacity').textContent = table.capacity;
    document.getElementById('modal-table-server').textContent = table.server || 'None assigned';
    document.getElementById('modal-table-time').textContent = table.timeSeated || 'Not occupied';
    document.getElementById('modal-table-notes').textContent = table.notes || 'None';
    
    const sendBtn = document.getElementById('modal-send-customer');
    const openBtn = document.getElementById('modal-mark-open');
    
    sendBtn.onclick = () => handleSendCustomer(table.id);
    openBtn.onclick = () => handleToggleStatus(table.id);
    
    if (table.status === 'occupied') {
        openBtn.textContent = 'Mark as Open';
        openBtn.className = 'btn btn-warning';
    } else {
        openBtn.textContent = 'Seat Customer';
        openBtn.className = 'btn btn-success';
    }
    
    tableDetailsModal.show();
}

// Event handlers
function handleSendCustomer(tableId) {
    alert(`Customer sent to Table ${tableId}`);
}

function handleToggleStatus(tableId) {
    const table = tables.find(t => t.id === tableId);
    if (!table) return;
    
    if (table.status === 'occupied') {
        table.status = 'open';
        table.server = '';
        table.timeSeated = '';
    } else {
        table.status = 'occupied';
        table.server = 'Host';
        table.timeSeated = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    }
    
    saveTableStatus();
    renderTables();
    tableDetailsModal.hide();
}

// LocalStorage functions
function saveTableStatus() {
    localStorage.setItem('restaurantTables', JSON.stringify(tables));
}

function loadTableStatus() {
    const savedTables = localStorage.getItem('restaurantTables');
    if (savedTables) {
        tables = JSON.parse(savedTables);
    }
}
// Show greeting message
document.getElementById("greetBtn").addEventListener("click", function () {
    const message = document.getElementById("greetMessage");
    message.style.display = "block";

    // Optional: Hide after 5 seconds
    setTimeout(() => {
        message.style.display = "none";
    }, 5000);
});

// Setup event listeners
function setupEventListeners() {
    filterButtons.forEach(button => {
        button.addEventListener('click', () => {
            filterButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            renderTables(button.dataset.filter, searchInput.value);
        });
    });
    
    searchInput.addEventListener('input', () => {
        const activeFilter = document.querySelector('.btn-filter.active').dataset.filter;
        renderTables(activeFilter, searchInput.value);
    });
}