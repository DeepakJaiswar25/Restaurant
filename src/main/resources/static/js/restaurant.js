// Show login form
document.getElementById("loginBtn").addEventListener("click", function() {
    document.getElementById("loginForm").classList.add("active");
    document.getElementById("signupForm").classList.remove("active");
});

// Show sign-up form
document.getElementById("signupBtn").addEventListener("click", function() {
    document.getElementById("signupForm").classList.add("active");
    document.getElementById("loginForm").classList.remove("active");
});

// Navigate from login to signup
document.getElementById("toSignup").addEventListener("click", function() {
    document.getElementById("signupForm").classList.add("active");
    document.getElementById("loginForm").classList.remove("active");
});

// Navigate from signup to login
document.getElementById("toLogin").addEventListener("click", function() {
    document.getElementById("loginForm").classList.add("active");
    document.getElementById("signupForm").classList.remove("active");
});

// Submit login form (for demo, you can implement real authentication)
//document.getElementById("submitSignupBtn").addEventListener("click", function(event) {
//	let password = document.getElementById("password").value;
	  //     let confirmPassword = document.getElementById("confirmpassword").value;
           
	   //    if (password !== confirmPassword) {
	    //       alert("Passwords do not match!");
	   //        event.preventDefault(); // Prevent form submission
	      // }
		 //  else{
		//	alert("Registered successfully! Now logging in...");
			  // Transition to login page after registration
		//	  document.getElementById("signupForm").classList.remove("active");
		//	  document.getElementById("loginForm").classList.add("active");
			
		//   }
//});

// Submit sign-up form (for demo, you can implement real registration)
//document.getElementById("submitSignupBtn").addEventListener("click", function() {
   // alert("Registered successfully! Now logging in...");
    // Transition to login page after registration
  //  document.getElementById("signupForm").classList.remove("active");
    //document.getElementById("loginForm").classList.add("active");
//});

document.getElementById("signuppassword").addEventListener("change", function(event) {
    let password = event.target.value;
    let confirmPassword = document.getElementById("confirmpassword").value;

    console.log("Password:", password);
    console.log("Confirm Password:", confirmPassword);

    if (confirmPassword && password !== confirmPassword) {
        console.log("Passwords do not match!");
		alert("Passwords do not match!");
    }
});
//This is to check whether password entered in password and confirm password is same
document.getElementById("confirmpassword").addEventListener("change", function(event) {
    let confirmPassword = event.target.value;
    let password = document.getElementById("signuppassword").value;

    console.log("Password:", password);
    console.log("Confirm Password:", confirmPassword);

    if (password && password !== confirmPassword) {
        console.log("Passwords do not match!");
		alert("Passwords do not match!");
    }
});
// Close login form
document.getElementById("closeLogin").addEventListener("click", function() {
    document.getElementById("loginForm").classList.remove("active");
});

// Close signup form
document.getElementById("closeSignup").addEventListener("click", function() {
    document.getElementById("signupForm").classList.remove("active");
});
