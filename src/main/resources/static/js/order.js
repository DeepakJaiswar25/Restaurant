const pathSegments = window.location.pathname.split('/');
const tableNumber = pathSegments[pathSegments.length - 1];

console.log(tableNumber);

let cart = [];

  async function loadMenu() {
    const foodMenuSlider = document.getElementById("food-menu-slider");
    foodMenuSlider.innerHTML = "";

    try {
        const response = await fetch('http://localhost:9090/api/getMenu');
        const menuItems = await response.json();
    // Step 1: Group menu items by category
    const menuMap = {};
    menuItems.forEach(item => {
        if (!menuMap[item.category]) {
            menuMap[item.category] = [];
        }
        menuMap[item.category].push(item);
    });

    // Step 2: Loop through grouped categories
    Object.entries(menuMap).forEach(([categoryName, items]) => {
        const categoryDiv = document.createElement("div");
        categoryDiv.classList.add("menu-category");

        const categoryTitle = document.createElement("h3");
        categoryTitle.textContent = categoryName;
        categoryDiv.appendChild(categoryTitle);

        const categorySlider = document.createElement("div");
        categorySlider.classList.add("category-slider");

        items.forEach(item => {
            const foodCard = document.createElement("div");
            foodCard.classList.add("food-card");

            const imageContainer = document.createElement("div");
            imageContainer.classList.add("image-container");

            const foodImage = document.createElement("img");
            foodImage.src = `/${item.imageUrl}`;
            foodImage.alt = item.name;
            foodImage.onerror = function () {
                this.src = '/images/default.jpg';
            };

            const descriptionOverlay = document.createElement("div");
            descriptionOverlay.classList.add("description-overlay");
            descriptionOverlay.textContent = item.desc;

            imageContainer.appendChild(foodImage);
            imageContainer.appendChild(descriptionOverlay);

            foodCard.innerHTML = `
                <h4>${item.name}</h4>
                <p class="price">$${item.price}</p>
                <input type="text" class="dish-comment" placeholder="Special instructions..." maxlength="100">
                <button onclick="addToCart(event, '${item.name.replace(/'/g, "\\'")}', ${item.price})">Add to Cart</button>
            `;

            foodCard.prepend(imageContainer);
            categorySlider.appendChild(foodCard);
        });

        categoryDiv.appendChild(categorySlider);
        foodMenuSlider.appendChild(categoryDiv);
    });
    } catch (error) {
        console.error('Error fetching menu:', error);
        foodMenuSlider.innerHTML = `<p class="error">Failed to load menu. Please try again later.</p>`;
    }
}

// Cart functions remain the same as your original version
function addToCart(event, itemName, price) {
    const foodCard = event.target.closest('.food-card');
    const commentInput = foodCard.querySelector('.dish-comment');
    const comment = commentInput.value.trim();

    const existingItem = cart.find(item => item.name === itemName);
    if (existingItem) {
        existingItem.quantity += 1;
        if (comment) existingItem.comment = comment;
    } else {
        cart.push({ 
            name: itemName, 
            price: price, 
            quantity: 1,
            comment: comment || 'No comments',
			tableNumber: tableNumber
        });
    }

    event.target.textContent = '✓ Added!';
    setTimeout(() => {
        event.target.textContent = 'Add to Cart';
    }, 1500);

    updateCart();
}

function updateCart() {
    const cartItemsDiv = document.getElementById('cart-items');
    cartItemsDiv.innerHTML = '';

    cart.forEach(item => {
        const cartRow = document.createElement('tr');
        cartRow.innerHTML = `
            <td>${item.name}</td>
            <td>$${item.price.toFixed(2)}</td>
            <td class="quantity">
                <button onclick="decreaseQuantity('${item.name.replace(/'/g, "\\'")}')">-</button>
                <span>${item.quantity}</span>
                <button onclick="increaseQuantity('${item.name.replace(/'/g, "\\'")}')">+</button>
            </td>
            <td>$${(item.price * item.quantity).toFixed(2)}</td>
            <td class="comment-cell">${item.comment}</td>
            <td><button class="remove-btn" onclick="removeFromCart('${item.name.replace(/'/g, "\\'")}')">Remove</button></td>
        `;
        cartItemsDiv.appendChild(cartRow);
    });

    const totalPrice = cart.reduce((total, item) => total + item.price * item.quantity, 0);
    document.getElementById('total-price').textContent = `Total: $${totalPrice.toFixed(2)}`;
}

function increaseQuantity(itemName) {
    const item = cart.find(item => item.name === itemName);
    if (item) {
        item.quantity += 1;
        updateCart();
    }
}

function decreaseQuantity(itemName) {
    const item = cart.find(item => item.name === itemName);
    if (item && item.quantity > 1) {
        item.quantity -= 1;
        updateCart();
    }
}

function removeFromCart(itemName) {
    cart = cart.filter(item => item.name !== itemName);
    updateCart();
}

// Navigation functions
function showMenu() {
    document.getElementById('food-menu-slider').style.display = 'block';
    document.getElementById('cart').style.display = 'none';
}

function showCart() {
    document.getElementById('food-menu-slider').style.display = 'none';
    document.getElementById('cart').style.display = 'block';
    updateCart();
}
function updateOrders() {
  // Convert cart object to JSON
  const jsonData = JSON.stringify(cart);
console.log(jsonData)
  // Send data to the backend
  fetch('http://localhost:9090/api/saveorder', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: jsonData
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Failed to submit cart: ' + response.statusText);
    }
   return response.text(); // if backend returns some response
  })
  .then(data => {
    console.log('Cart submitted successfully:', data);
    alert('Order submitted!');
	window.location.href = '/payment';
  })
  .catch(error => {
    console.error('Error submitting cart:', error);
    alert('Failed to submit order.');
  });
}

function updateOrder() {
	let data=JSON.stringify(cart)
	alert(data)
	}

function handleCheckout() {
    // 1. Validate cart
    if (cart.length === 0) {
        // Play error sound (optional)
        const errorSound = new Audio('sounds/error.wav');
        errorSound.play();
        
        // Show game-style alert
        alert("🛑 Inventory Empty! Add some  power-ups first!");
        return;
    }

    // 2. Save cart data to localStorage
    localStorage.setItem('pixelBitesCart', JSON.stringify(cart));
    
    // 3. Play success sound (optional)
    const successSound = new Audio('sounds/checkout.wav');
    successSound.volume = 0.3;
    successSound.play();
    
    // 4. Add loading animation
    const btn = document.getElementById('checkout-btn');
    btn.innerHTML = `<span class="btn-content">Processing <span class="loading-dots">...</span></span>`;
    btn.disabled = true;
    
    // 5. Redirect after 1.5 seconds (simulate processing)
    setTimeout(() => {
        window.location.href = 'payment.html';
    }, 1500);
}

document.addEventListener("DOMContentLoaded", loadMenu);