import com.google.gson.Gson;
import com.google.gson.JsonObject;
import user.User;
import validator.Validator;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.Nullable;

public class Main {

// This User object will hold the last created user
    @Nullable
private static User newUser = null;
public static void main(String[] args) throws  IllegalArgumentException {
try {
// Open a new server socket
ServerSocketChannel server = ServerSocketChannel.open();
server.bind(new InetSocketAddress(8080));
while (true) {
// Accept a new client connection
SocketChannel socket = server.accept();
// Read the request from the client
ByteBuffer buffer = ByteBuffer.allocate(64000);
int bytesRead = socket.read(buffer);
buffer.flip();
byte[] bytes = new byte[bytesRead];
buffer.get(bytes);
String httpRequest = new String(bytes, StandardCharsets.UTF_8);
// Split the HTTP request into headers and body
String[] httpRequestParts = httpRequest.split("\r\n\r\n", 2);
String requestBody = httpRequestParts.length > 1 ? httpRequestParts[1] : "";
Gson gson = new Gson();
// Parse the request body as a JSON object
JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
// Initialize the response as an empty JSON object
String jsonResponse = "{}";
// Split the request line into method, URL, and version
String[] requestLineParts = httpRequestParts[0].split(" ", 3);
String httpMethod = requestLineParts[0];
String requestUrl = requestLineParts[1];
// If this is a POST request to /validateUser, validate the user data
if (httpMethod.equals("POST") && requestUrl.equals("/validateUser")) {
if (jsonObject != null) {
Validator valid = new Validator();
boolean validationPassed = valid.validateName(jsonObject.get("name").getAsString()) && valid.validateNumber(jsonObject.get("phone").getAsString()) && valid.validateEmail(jsonObject.get("email").getAsString());
JsonObject jsonResponseObject = new JsonObject();
jsonResponseObject.addProperty("validationIsPassed", validationPassed);
jsonResponse = jsonResponseObject.toString();
// Create a new User object and store it
newUser = new User(jsonObject.get("name").getAsString(), jsonObject.get("email").getAsString(), jsonObject.get("phone").getAsString());
} else {
throw new IllegalArgumentException("Failed to parse request body as JSON");
}
}
// If this is a request to /getUserInfo, return the last created user
if ("/getUserInfo".equals(requestUrl)) {
jsonResponse = gson.toJson(newUser);
}
// Send the response back to the client
String serverResponse = "HTTP/1.1 200 OK\r\n" +
"Access-Control-Allow-Origin: *\r\n" +
"Access-Control-Allow-Headers: Content-Type\r\n" +
"Content-Type: application/json\r\n" +
"Content-Length: " + jsonResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
"\r\n" +
jsonResponse;
ByteBuffer response = ByteBuffer.wrap(serverResponse.getBytes(StandardCharsets.UTF_8));
socket.write(response);
socket.close();
}
} catch (Exception e) {
System.out.println(e.getMessage());}
}
}