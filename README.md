# VeriMed-backend

VeriMed-backend is a Java-based backend service designed for secure, scalable chat and user management in healthcare or medical communication platforms. Built with Spring Boot, it powers real-time messaging, robust user operations, and integrates seamlessly with AI models for advanced functionalities.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Setup & Installation](#setup--installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Integrations](#integrations)
- [Contributing](#contributing)
- [License](#license)
- [Links](#links)

---

## Features

- **User Management:** Create, update, search, and delete users securely. JWT-based authentication ensures protected endpoints.
- **Chat Management:** Start, update, or delete chat sessions between users. Group and individual chat support.
- **Message Handling:** Persistent, relational storage of messages using JPA repositories.
- **Real-Time Communication:** WebSocket support for instant message delivery.
- **Role-Based Access:** Integration with Spring Security for user authorization.
- **CORS Support:** Configured for secure cross-origin requests from verified frontends.
- **Extendable:** Ready for integration with external AI models and services.

## Architecture

- **Spring Boot (Java)**
- **RESTful API Layer**
- **WebSocket for real-time**
- **JPA (Hibernate) for persistence**
- **JWT Authentication**
- **Service/Repository Pattern**

## Setup & Installation

### Prerequisites

- Java 17+
- Maven or Gradle
- PostgreSQL or other compatible DB

### Steps

1. **Clone the repository**
   ```sh
   git clone https://github.com/dhruv-15-03/VeriMed-backend.git
   cd VeriMed-backend
   ```

2. **Configure Environment Variables**
   - Database connection settings (`application.properties`):
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/verimed
     spring.datasource.username=YOUR_DB_USER
     spring.datasource.password=YOUR_DB_PASSWORD
     ```
   - JWT secret and other keys as needed.

3. **Install dependencies**
   ```sh
   mvn install
   ```

4. **Run the application**
   ```sh
   mvn spring-boot:run
   ```
   By default, the backend runs on `http://localhost:8080`.

## Configuration

- **CORS Allowed Origins:**
  - `http://localhost:8081`
  - `http://localhost:3000`
  - `https://dhr-social.vercel.app`
  - `http://localhost:5173`
  - `*` (for development; restrict for production!)

- **Security:** All `/api/**` endpoints are JWT authenticated.

## API Endpoints

- **User APIs:** `/api/user`
  - Register, login, update, delete, search, get by ID/JWT
- **Chat APIs:** `/api/chat`
  - Create chat, get chats for user, update, delete (by ID/user), find by user
- **WebSocket Endpoint:** `/ws`
  - STOMP/WebSocket for real-time messaging

_Refer to source code for full endpoint details and payloads._

## Integrations

### AI Model Integration

This backend is designed to work with external AI models for advanced healthcare features. For details and the latest AI link, see:

- **VeriMed-AI Repository:**  
  [VeriMed-AI by dhruv-15-03](https://github.com/dhruv-15-03/VeriMed-AI)

You can connect backend chat/message data to the AI model for analysis, recommendations, or automation.

## Contributing

Pull requests and suggestions are welcome!  
Please see [issues](https://github.com/dhruv-15-03/VeriMed-backend/issues) for bugs and feature requests.

## License

_The current repository does not specify a license. Please clarify usage rights with the owner._

## Links

- **Backend:** [VeriMed-backend](https://github.com/dhruv-15-03/VeriMed-backend)
- **AI Model:** [VeriMed-AI](https://github.com/dhruv-15-03/VeriMed-AI-)

---

_Professional backend for healthcare communication, ready for intelligent integrations._
