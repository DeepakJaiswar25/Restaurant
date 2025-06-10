// Show greeting message
document.getElementById("greetBtn").addEventListener("click", function () {
    const message = document.getElementById("greetMessage");
    message.style.display = "block";

    // Optional: Hide after 5 seconds
    setTimeout(() => {
        message.style.display = "none";
    }, 5000);
});

// Change table status
function markTable(tableId, status) {
    const table = document.getElementById(tableId);
    const statusText = table.querySelector(".status-text");

    if (status === "occupied") {
        table.classList.remove("open");
        table.classList.add("occupied");
        statusText.textContent = "Occupied";
    } else {
        table.classList.remove("occupied");
        table.classList.add("open");
        statusText.textContent = "Open";
    }
}
