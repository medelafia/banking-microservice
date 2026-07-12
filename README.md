# 🏦 Banking Microservice System

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-Latest-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)
![Kafka](https://img.shields.io/badge/Apache-Kafka-black)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-green)

A cloud-native banking system built using the **Microservices Architecture** with **Spring Boot**, **Spring Cloud**, **Docker**, **Kafka**, and **MySQL**.

The project demonstrates how a modern banking platform can be decomposed into independent services that communicate through REST APIs and asynchronous messaging, while leveraging centralized configuration, service discovery, and an API Gateway.

---

# ✨ Features

- 👤 User Management
- 💳 Bank Account Management
- 💸 Deposit & Withdraw Operations
- 🔄 Transaction Management
- 📩 Notification Service
- 🌐 API Gateway
- 🔍 Service Discovery (Eureka)
- ⚙️ Centralized Configuration Server
- 🐳 Dockerized Deployment
- 📡 Event-driven communication with Kafka
- 🗄️ MySQL Database
- RESTful APIs

---

# 🏗️ Architecture

```text
                    +----------------------+
                    |      API Gateway     |
                    +----------+-----------+
                               |
        ---------------------------------------------------
        |          |            |              |           |
        ▼          ▼            ▼              ▼           ▼
+---------------+ +---------------+ +----------------+ +----------------+
| User Service  | |Account Service| |Transaction Svc | |Notification Svc|
+---------------+ +---------------+ +----------------+ +----------------+
        |                |                  |
        +----------------+------------------+
                         |
                   Apache Kafka
                         |
                Notification Events

        ▲
        |
+-------------------+
| Config Server     |
+-------------------+

        ▲
        |
+-------------------+
| Eureka Discovery  |
+-------------------+

Each service owns its own database and is independently deployable.
```

---

# 📂 Project Structure

```
banking-microservice/
│
├── gateway-service/
├── discovery-service/
├── config-service/
├── user-service/
├── account-service/
├── transaction-service/
├── notification-service/
│
├── docker-compose.yaml
└── pom.xml
```

---

# 🧩 Microservices

## Gateway Service

Acts as the single entry point for clients.

Responsibilities:

- Request routing
- Load balancing
- Service forwarding
- Centralized API access

---

## Discovery Service

Netflix Eureka Server responsible for:

- Service registration
- Dynamic discovery
- Health monitoring

---

## Config Service

Centralized configuration management for every microservice.

Benefits:

- Externalized configuration
- Environment separation
- Easier maintenance

---

## User Service

Responsible for customer management.

Features:

- Create users
- Retrieve users
- Update users
- Delete users

---

## Account Service

Handles banking accounts.

Features:

- Create account
- Get account information
- Deposit money
- Withdraw money
- Balance management

---

## Transaction Service

Responsible for recording every financial operation.

Examples:

- Deposits
- Withdrawals
- Transfers
- Transaction history

---

## Notification Service

Consumes Kafka events and sends notifications after banking operations.

Examples:

- Deposit completed
- Withdrawal completed
- Transaction successful

---

# ⚙️ Technologies

Backend

- Java 17
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Spring Web
- Spring Cloud Gateway

Cloud

- Eureka Discovery Server
- Spring Config Server

Messaging

- Apache Kafka

Database

- MySQL

Containerization

- Docker
- Docker Compose

Build Tool

- Maven

---

# 🚀 Getting Started

## Clone the repository

```bash
git clone https://github.com/medelafia/banking-microservice.git

cd banking-microservice
```

---

## Prerequisites

- Java 17+
- Maven
- Docker
- Docker Compose
- MySQL
- Kafka

---

## Run using Docker

```bash
docker compose up --build
```

---

## Run manually

Start the services in the following order:

1. Discovery Service
2. Config Service
3. Gateway Service
4. User Service
5. Account Service
6. Transaction Service
7. Notification Service

---

# 🔄 Typical Workflow

```
Create User
      │
      ▼
Create Account
      │
      ▼
Deposit / Withdraw
      │
      ▼
Transaction Service
      │
      ▼
Kafka Event
      │
      ▼
Notification Service
```

---

# 📡 Communication

The project combines:

### Synchronous Communication

- REST APIs

### Asynchronous Communication

- Apache Kafka Events

This hybrid approach improves scalability and decouples services.

---

# 📁 API Overview

| Service | Description |
|----------|-------------|
| User Service | Customer management |
| Account Service | Bank account management |
| Transaction Service | Banking operations |
| Notification Service | Event notifications |
| Gateway Service | API routing |
| Discovery Service | Service registry |
| Config Service | Centralized configuration |

---

# 🐳 Docker Support

The project includes a `docker-compose.yaml` file that allows the complete banking system to be started with a single command.

```bash
docker compose up
```

---

# 📈 Future Improvements

- JWT Authentication
- Spring Security
- Distributed Tracing (Zipkin)
- OpenTelemetry
- Prometheus & Grafana
- Kubernetes Deployment
- CI/CD Pipeline
- Redis Caching
- Circuit Breaker (Resilience4j)
- Swagger/OpenAPI Documentation
- Account-to-Account Transfers
- Email & SMS Notifications

---

# 📚 Learning Objectives

This project demonstrates:

- Microservice architecture
- Service discovery
- API Gateway pattern
- Centralized configuration
- Event-driven architecture
- RESTful API development
- Docker containerization
- Kafka messaging
- Distributed systems fundamentals

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a feature branch

```bash
git checkout -b feature/my-feature
```

3. Commit your changes

```bash
git commit -m "Add new feature"
```

4. Push to your branch

```bash
git push origin feature/my-feature
```

5. Open a Pull Request.

---

# 👨‍💻 Author

**Mohamed El Afia**

GitHub: https://github.com/medelafia

---

# ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.