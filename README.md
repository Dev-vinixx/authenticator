# User Validation Server

This project is a user validation server implemented in Java. It uses the Gson library to convert Java Objects into their JSON representation and vice versa. It also uses the Java NIO library to handle HTTP requests and responses. The project is structured using the Maven architecture.

## Classes

### User

The `User` class represents a user with a name, email, and phone number. Each field is private and can be accessed via getter methods.

### Validator

The `Validator` class is responsible for validating the user data. It contains three methods: `validateName`, `validateNumber`, and `validateEmail`. Each method validates its respective field and throws an exception if the validation fails.

### Main

The `Main` class is the entry point of the application. It opens a server socket and listens for incoming connections. It handles two types of requests:

- `POST /validateUser`: This endpoint validates the user data in the request body. The request body should be a JSON object with `name`, `phone`, and `email` fields. The response is a JSON object with a `validationIsPassed` field indicating whether the validation passed.
- `GET /getUserInfo`: This endpoint returns a JSON representation of the last created user.

## Client-side JavaScript

The client-side JavaScript code sends form data to the server and handles the server's responses. It uses the Fetch API to send HTTP requests.

- `sendForm(name, phone, email)`: This function sends a `POST /validateUser` request to the server with the provided name, phone, and email. If the server validates the data, it redirects to the User page.
- Event listener for form submission: This event listener prevents the form from being submitted normally, gets the form data, and calls `sendForm(name, phone, email)`.

## Running the Project

To run the project, you need to have Java and Maven installed on your machine. You can run the server by executing the `Main` class. The server will start listening for connections on `localhost:8080`.

Please note that this is a simple project meant for learning purposes and should not be used in a production environment as it is.

## Dependencies

This project uses the following dependencies:

- Gson: A Java library that can be used to convert Java Objects into their JSON representation and vice versa.
- JetBrains Annotations: Provides a set of annotations that can be used to improve the quality of your Java code.

## Known Issues

This system has some known limitations that are being worked on for future versions. Currently, the system does not support storing more than one user at a time. This means that if a new user is created, the data of the previous user is lost.

Also, the system does not offer the option to choose to connect. This means that once the server is started, it automatically accepts all incoming connections without giving the user the option to accept or reject individual connections.

However, it's important to note that this system was created as a test for a larger project that is being built. The current limitations are being addressed in the context of that larger project and are expected to be resolved in future versions.

## Future Improvements

- Add unit tests to ensure the validation logic works as expected.
- Handle more types of requests and responses.
- Improve error handling and reporting.
- Use a database to store user data instead of keeping it in memory.


                            @..@
                           (----)
                          ( >__< )
                          ^^ ~~ ^^