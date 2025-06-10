// Configuration
let orders = [];

// DOM Elements
const ordersTableBody = document.getElementById('orders-table-body');
const statusFilter = document.getElementById('status-filter');
const priorityFilter = document.getElementById('priority-filter');
const soundToggle = document.getElementById('sound-toggle');
const orderDetailsModal = new bootstrap.Modal('#orderDetailsModal');



function setupEventListeners() {
    statusFilter.addEventListener('change', filterOrders);
    priorityFilter.addEventListener('change', filterOrders);
    soundToggle.addEventListener('click', toggleSound);
}



function renderOrders() {
    ordersTableBody.innerHTML = '';
    
    const filteredOrders = orders.filter(order => {
        const statusMatch = statusFilter.value === 'all' || order.status === statusFilter.value;
        const priorityMatch = priorityFilter.value === 'all' || order.priority == priorityFilter.value;
        return statusMatch && priorityMatch;
    });
    
    filteredOrders.forEach(order => {
        const row = document.createElement('tr');
        row.className = `status-${order.status.toLowerCase().replace(' ', '-')} priority-${order.priority}`;
        
        row.innerHTML = `
            <td>${order.tableNumber}</td>
            <td>${order.items.reduce((sum, item) => sum + item.quantity, 0)} items</td>
            <td class="text-capitalize">${order.status}</td>
            <td>
                <select class="form-select form-select-sm priority-select" 
                        onchange="updatePriority('${order.id}', this.value)">
                    <option value="0" ${order.priority == 0 ? 'selected' : ''}>Normal</option>
                    <option value="1" ${order.priority == 1 ? 'selected' : ''}>High</option>
                    <option value="2" ${order.priority == 2 ? 'selected' : ''}>Urgent</option>
                </select>
            </td>
            <td class="comment-cell" data-full-comment="${order.comments || 'No comments'}">
                <span class="comment-text">${order.comments ? order.comments.substring(0, 20) + (order.comments.length > 20 ? '...' : '') : '-'}</span>
            </td>
            <td>
                ${order.status === 'Pending' ? 
                    `<button class="btn btn-warning btn-sm" 
                            onclick="updateStatus('${order.id}', 'In-Progress')">
                        Start
                    </button>` : ''}
                ${order.status === 'In-Progress' ? 
                    `<button class="btn btn-success btn-sm" 
                            onclick="updateStatus('${order.id}', 'Done')">
                        Complete
                    </button>` : ''}
            </td>
        `;
        ordersTableBody.appendChild(row);
    });
}

function filterOrders() {
    renderOrders();
}

async function showOrderDetails(orderId) {
    const order = orders.find(o => o.id === orderId);
    if (!order) return;
    
    const detailsContent = document.getElementById('order-details-content');
    detailsContent.innerHTML = `
        <div class="mb-3">
            <h6>Order #${order.id}</h6>
            <p>Table: ${order.tableNumber}</p>
            <p>Status: <span class="badge bg-${getStatusBadgeColor(order.status)}">${order.status}</span></p>
            <p>Priority: <span class="badge bg-${getPriorityBadgeColor(order.priority)}">${getPriorityText(order.priority)}</span></p>
            <p class="fw-bold">Comments: ${order.comments || 'None'}</p>
            <p>Order Time: ${new Date(order.timestamp).toLocaleString()}</p>
        </div>
        
        <h5>Items:</h5>
        <table class="table">
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Special Instructions</th>
                </tr>
            </thead>
            <tbody>
                ${order.items.map(item => `
                    <tr>
                        <td>${item.name}</td>
                        <td>$${item.price.toFixed(2)}</td>
                        <td>${item.quantity}</td>
                        <td>$${(item.price * item.quantity).toFixed(2)}</td>
                        <td>${item.comment || '-'}</td>
                    </tr>
                `).join('')}
            </tbody>
        </table>
    `;
    
    orderDetailsModal.show();
}

// Helper functions
function getStatusBadgeColor(status) {
    switch(status) {
        case 'Pending': return 'danger';
        case 'In-Progress': return 'warning';
        case 'Done': return 'success';
        default: return 'secondary';
    }
}

function getPriorityBadgeColor(priority) {
    switch(parseInt(priority)) {
        case 0: return 'secondary';
        case 1: return 'warning';
        case 2: return 'danger';
        default: return 'light';
    }
}

function getPriorityText(priority) {
    switch(parseInt(priority)) {
        case 0: return 'Normal';
        case 1: return 'High';
        case 2: return 'Urgent';
        default: return 'Unknown';
    }
}

// API simulation functions
function updateStatus(orderId, updateStatus) {
      	setTimeout(function(){
      		fetch('http://localhost:9090/orders/update-status/' + orderId + '/' + updateStatus, {
               method: 'PUT'
           }).then(() => location.reload());
      	},1000)	
       }

       function updatePriority(orderId, updatePriority) {
       	setTimeout(function(){
           fetch('http://localhost:9090/orders/update-priority/' + orderId + '/' + updatePriority, {
               method: 'PUT'
           }).then(() => location.reload());
       },1000)
       }

