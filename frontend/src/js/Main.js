// Function to send form data
function sendForm(name, phone, email) {
// Convert form data to JSON
let requestData = JSON.stringify({"name" : name, "phone" : phone, "email" : email});
// Send a POST request to the server
fetch('http://localhost:8080/validateUser', {
method: 'POST',
headers: {
'Content-Type': 'application/json'
},
body: requestData
})
.then(response => response.json()) // Parse the response as JSON
.then(data => {
// If the server validates the data, redirect to the User page
if (data.validationIsPassed) {
window.location.href = 'User.html';
}
})
.catch(error => console.error('error:', error.message)); // Log any errors
};
// Add an event listener to the form
document.getElementById('myForm').addEventListener('submit', function(event) {
event.preventDefault(); // Prevent the form from being submittednormally
// Get the form data
var name = document.getElementById('username').value;
var phone = document.getElementById('phone').value;
var email = document.getElementById('email').value;
// Send the form data
sendForm(name, phone, email);
}); 