// Fetch user information from the server
fetch('http://localhost:8080/getUserInfo', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json'
    },
})
.then(response => response.json()) // Parse the response as JSON
.then(user => {

    // Get the HTML elements to display the user data
    let userName = document.getElementById('nameUser');
    let email = document.getElementById('email');
    let phone = document.getElementById('phone');

    // Update the HTML elements with the user data
    userName.innerHTML = user.name;
    email.innerHTML = user.email;
    phone.innerHTML = user.number;
})
.catch(error => console.error('error:', error.message)); // Log any errors that occur during the fetch