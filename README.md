# Online Tools

This project provides a set of online tools via RESTful APIs.

## Technologies Used

*   Spring Boot
*   Java 17
*   Maven
*   Spring Actuator
*   SpringDoc OpenAPI (Swagger)

## API Endpoints

### Hashing Tools

*   `POST /api/hash/md5` (input: text, output: MD5 hash)
*   `POST /api/hash/sha256` (input: text, output: SHA-256 hash)
*   `POST /api/hash/sha512` (input: text, output: SHA-512 hash)
*   `POST /api/hash/keccak256` (input: text, output: Keccak-256 hash)

### Encoding/Decoding Tools

*   `POST /api/encode/base64` (input: text, output: Base64 string)
*   `POST /api/decode/base64` (input: Base64 string, output: decoded text)
*   `POST /api/encode/url` (input: text, output: URL encoded string)
*   `POST /api/decode/url` (input: URL encoded string, output: decoded text)

### Formatting Tools

*   `POST /api/format/json` (input: raw JSON string, output: pretty-printed JSON)
*   `POST /api/format/xml` (input: raw XML string, output: pretty-printed XML)

### Generation Tools

*   `GET /api/generate/uuid` (output: new UUID)
*   `GET /api/generate/qrcode?text=<text>` (output: image/png)
    *   Optional parameter: `&format=base64` to get the image as a Base64 string.

## High Availability

*   The API is designed to be stateless, meaning that it does not store any session-specific data on the server. This allows the application to be easily scaled horizontally by adding more instances.
*   Global exception handling is implemented to ensure that the API returns structured and meaningful error responses.
*   Spring Actuator is used to provide health checks and application information, which can be used to monitor the application's health and performance.

## High Performance

*   The hashing, encoding/decoding, and formatting tools use standard and efficient libraries.
*   The QR code generation is optimized to minimize processing time.

## Running the Application

### Locally with Maven

1.  Clone the repository.
2.  Navigate to the project directory.
3.  Run `mvn spring-boot:run`.

### With Docker

1.  Clone the repository.
2.  Navigate to the project directory.
3.  Run `docker-compose up --build`.

## Scaling on Kubernetes

The application can be easily scaled on Kubernetes by creating a deployment and a service. The deployment will manage the number of instances of the application, and the service will provide a stable endpoint for accessing the application.

To scale the application, simply increase the number of replicas in the deployment. Kubernetes will automatically create new instances of the application and distribute traffic to them.
