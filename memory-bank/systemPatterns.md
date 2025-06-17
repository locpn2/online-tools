# System Patterns

**System Architecture:**

*   The project is a Spring Boot application.
*   The application uses a layered architecture with controllers, services, and repositories.

**Key technical decisions:**

*   Using Maven for dependency management and building the project.
*   Using Spring Boot for rapid application development.

**Design patterns in use:**

*   RESTful API design.

**Component relationships:**

*   Controllers handle incoming requests and delegate to services.
*   Services implement the business logic and interact with repositories.
*   Repositories handle data access.

**Critical implementation paths:**

*   The `OnlineToolsApplication` class is the entry point for the application.
*   The controllers in the `com.example.onlinetools.controller` package handle API requests.
