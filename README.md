# Workcloud Task Management – MyWork API

[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blueviolet)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
[![Java Version](https://img.shields.io/badge/java-17-blue.svg)](https://www.java.com)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com)
[![Test Coverage](https://img.shields.io/badge/coverage-95%25-brightgreen.svg)](https://github.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**An enterprise-grade API for the Workcloud Task Management ecosystem, built with Spring Boot and meticulously designed using Hexagonal Architecture.**

This project serves as a blueprint for building robust, scalable, and highly maintainable backend services. It demonstrates professional software engineering practices, including a decoupled architecture, a comprehensive multi-layered testing strategy, standardized error handling, and production-ready operational features.

---

## 📖 Table of Contents

1.  [**About The Project**](#-about-the-project)
    *   [Key Features](#key-features)
2.  [**🏛️ Core Architecture: The Hexagonal Approach**](#️-core-architecture-the-hexagonal-approach)
    *   [Architectural Principles](#architectural-principles)
    *   [Project Structure Mapping](#project-structure-mapping)
3.  [**🛠️ System Design & Engineering Practices**](#️-system-design--engineering-practices)
    *   [Robust Error Handling Strategy](#robust-error-handling-strategy)
    *   [Comprehensive Testing Pyramid](#comprehensive-testing-pyramid)
    *   [Security First Design](#security-first-design)
    *   [Configuration Management](#configuration-management)
    *   [Logging & Observability](#logging--observability)
4.  [**🚀 Getting Started**](#-getting-started)
    *   [Prerequisites](#prerequisites)
    *   [Local Setup & Installation](#local-setup--installation)
5.  [**🏗️ Build & Deployment (CI/CD)**](#️-build--deployment-cicd)
    *   [Running Local Builds](#running-local-builds)
    *   [CI/CD Pipeline Overview](#cicd-pipeline-overview)
6.  [**📜 API Endpoints & Specification**](#-api-endpoints--specification)
7.  [**🗺️ Future Roadmap**](#️-future-roadmap)
8.  [**🤝 Contributing**](#-contributing)
9.  [**📄 License**](#-license)
10. [**📧 Contact**](#-contact)

---

## 📍 About The Project

The MyWork API is the primary interface for workforce-facing applications within the Workcloud ecosystem. It empowers employees by giving them access to critical tasks, communication feeds, and performance surveys. The core design goal is to provide a clean, decoupled, and highly maintainable codebase that can evolve over time without accumulating technical debt.

### Key Features

*   **Feeds Management**: Create, list, update, and delete feed items.
*   **Feed Notes**: Add and manage notes associated with specific feed items.
*   **Feed Operations**: Perform state-changing actions like `CLAIM`, `ACKNOWLEDGE`, and `COMPLETE`.
*   **Surveys**: Create and submit responses for workforce surveys.
*   **Real-Time Management (RTM)**: Execute real-time operations like broadcasting messages.
*   **User Management**: Manage user profiles and assets.
*   **Session Tracking**: Log user login and logout events.

---

## 🏛️ Core Architecture: The Hexagonal Approach

This project is built using a **Hexagonal Architecture** (also known as Ports and Adapters). This pattern puts the core business logic and domain at the center of the application, isolated from external technologies and delivery mechanisms.

### Architectural Principles

*   **The Core is King**: The central hexagon contains pure business logic, domain models, and port interfaces. It is framework-independent and represents the heart of the application.
*   **Ports as Explicit Contracts**: Simple Java interfaces (Ports) define how the core communicates with the outside world.
*   **Adapters as Implementation Details**: Adapters implement the ports, connecting the core to external technologies like web frameworks (Spring MVC), databases (JPA/Hibernate), and message brokers.

This decoupling ensures the core business logic can be tested in complete isolation and that external technologies can be swapped out with minimal impact on the application's core.

### Project Structure Mapping

The directory structure directly reflects the hexagonal concepts:

```text
src/main/java/com/mywork/
├── Application.java               // Spring Boot entry point
├── core/                          // THE HEXAGON (No framework dependencies)
│   ├── domain/                    // - Core domain objects (Feed, Survey, User)
│   ├── exception/                 // - Custom business exceptions
│   ├── port/                      // - Port interfaces (contracts)
│   │   ├── in/                    //   - Inbound ports (driven by primary adapters)
│   │   └── out/                   //   - Outbound ports (driving secondary adapters)
│   └── service/                   // - Core application services and use cases
├── adapters/                      // ADAPTERS (Framework-dependent implementations)
│   ├── in/                        // - Primary / Driving Adapters
│   │   └── web/                   //   - Spring MVC Controllers
│   └── out/                       // - Secondary / Driven Adapters
│       ├── persistence/           //   - JPA/Hibernate repository implementations
│       └── messaging/             //   - (Example) RabbitMQ/Kafka producer implementations
└── config/                        // Spring configuration, bean wiring, security setup



---

## 🛠️ System Design & Engineering Practices

This project emphasizes professional software engineering practices for building resilient and maintainable systems.

### Robust Error Handling Strategy

Our error handling provides a predictable, consistent experience for API consumers and aligns perfectly with the hexagonal design:

1.  **Business Exceptions in the Core**: The core application logic defines and throws specific, custom exceptions (e.g., `ResourceNotFoundException`, `InvalidFeedOperationException`) when a business rule is violated.
2.  **Global Exception Handling in the Web Adapter**: A centralized `@ControllerAdvice` intercepts all exceptions. It translates business exceptions into standardized, client-friendly JSON error responses with appropriate HTTP status codes, while logging server-side errors for investigation.

**Example Standardized Error Response (`404 Not Found`):**
```json
{
  "timestamp": "2026-01-29T14:24:35.418Z",
  "status": 404,
  "error": "Not Found",
  "message": "Feed item with ID '123-abc-456' could not be found.",
  "path": "/mywork/v1/feeds/123-abc-456"
}
```

### Comprehensive Testing Pyramid

We enforce a strict, multi-layered testing strategy to ensure code quality and correctness.

1.  **Unit Tests (Fastest, ~70% of tests)**
    *   **Purpose**: Validate the core business logic in complete isolation.
    *   **Scope**: Services, domain models, and business rules within the `core` module.
    *   **Technology**: JUnit 5, Mockito. No Spring context needed.

2.  **Integration Tests (~20% of tests)**
    *   **Purpose**: Verify that adapters correctly implement their ports and integrate with external technologies.
    *   **Scope**: Persistence adapters (`@DataJpaTest`), Web adapters (`@WebMvcTest`), etc.
    *   **Technology**: JUnit 5, Spring Test, MockMvc, H2/Testcontainers.

3.  **End-to-End Tests (Slowest, ~10% of tests)**
    *   **Purpose**: Validate complete application flows from the API endpoint to the database and back.
    *   **Scope**: A small number of critical user journeys.
    *   **Technology**: `@SpringBootTest` with a full application context.

### Security First Design

Security is a primary concern, implemented via **Spring Security**.

*   **Authentication**: The API is secured using **OAuth2 / JWT Bearer Tokens**, which must be provided in the `Authorization` header.
*   **Authorization**: Endpoint access is controlled by granular scopes. Access is enforced declaratively using `@PreAuthorize` annotations (e.g., `@PreAuthorize("hasAuthority('SCOPE_mywork.write')")`).
*   **Configuration**: All security rules, CORS policies, and public endpoints (like `/actuator/health`) are configured centrally in a dedicated `SecurityConfig` class.
*   **Stateless by Design**: As a REST API, the service is stateless, with CSRF protection disabled as it is not needed for non-browser-based clients.

### Configuration Management

The application uses **Spring Profiles** to manage environment-specific configurations.

*   `application.properties`: Contains common properties shared across all environments.
*   `application-local.properties`: Properties for local development (e.g., local database credentials, disabling security for easy testing).
*   `application-prod.properties`: Properties for the production environment (e.g., production database host, secure credentials managed via environment variables).

To run with a specific profile, use the `-Dspring.profiles.active=local` JVM argument.

### Logging & Observability

To ensure the system is transparent and supportable in production:

*   **Structured Logging**: The application is configured (via `logback-spring.xml`) to output logs in **JSON format**. This allows logs to be easily ingested, parsed, and queried by log aggregation platforms like Splunk or the ELK Stack.
*   **Application Metrics**: **Spring Boot Actuator** and **Micrometer** are used to expose critical application metrics (JVM health, HTTP request latency, error rates) via the `/actuator/prometheus` endpoint for monitoring with a Prometheus/Grafana stack.
*   **Health Checks**: The `/actuator/health` endpoint provides a simple up/down status check, essential for load balancers and container orchestration systems.

---

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

*   Java JDK 17 or later
*   Maven 3.6 or later
*   (Optional) Docker for running a PostgreSQL database instance.

### Local Setup & Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name
    ```

2.  **Configure local properties:**
    Create a file named `src/main/resources/application-local.properties` and add your local database configuration:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/myworkdb
    spring.datasource.username=user
    spring.datasource.password=password
    ```

3.  **Build the project:**
    This command will compile the code and run all the unit and integration tests.
    ```sh
    mvn clean install
    ```

4.  **Run the application:**
    Activate the `local` profile to use your local configuration.
    ```sh
    java -jar -Dspring.profiles.active=local target/mywork-api-1.1.0.jar
    ```
    The API will be available at `http://localhost:8080`.

---

## 🏗️ Build & Deployment (CI/CD)

### Running Local Builds

*   **Run all tests (Unit & Integration):**
    ```sh
    mvn test
    ```
*   **Skip tests and build quickly:**
    ```sh
    mvn clean package -DskipTests
    ```



## 📜 API Endpoints & Specification

The full API contract is defined using **OpenAPI 3.1.0**. The specification file serves as the single source of truth for all API interactions.

*   **[openapi.yaml](./openapi.yaml)**

It can also be viewed interactively via Swagger UI at `http://localhost:8080/swagger-ui.html` when the application is running.
