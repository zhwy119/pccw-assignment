# PCCW Assignment

## Project Description

This project is an implementation of a technical assignment for PCCW. It is built using a Spring Boot microservices architecture and includes the following components: user registration, modification, query, and deletion back-end APIs, as well as front-end pages.
- **Eureka Server**: Service registry for microservices.
- **Zuul Gateway**: API gateway for routing and load balancing.
- **User Service**: Manages user-related operations.
- **Email Service**: Handles email sending and notifications.
- **UI**: UI to register, edit, deactivate, show existing registered users and view all emails that
  were sent

## Technology Stack

- **Spring Boot 2.1.5.RELEASE**: Framework version used.
- **MySQL**: Database used.
- **MyBatis-Plus 3.1.2**: Database operation framework.
- **JUnit & Mockito**: Testing frameworks for unit and integration tests.
- **Docker**: Containerization platform for deploying the application.
- **Maven**: Build automation tool for managing project dependencies and build lifecycle.
- **HTML, Bootstrap, Axios, jQuery**: Frontend technologies for building the user interface.
- **JDK 1.8**: Java Development Kit version used.

## Project Structure

The `pccw-assignment` directory includes:
- **common/**: Shared code and utilities
- **email-interface/**: Email service interface
- **email-service/**: Email service implementation
- **eureka-server/**: Eureka server
- **user-service/**: User service
- **zuul/**: API gateway and frontend pages
- **zz_apidocs/**: API documentation
- **zz_dbscripts/**: Database scripts
- **pom.xml**: Maven configuration
- **README.md**: Project documentation
- **docker_deploy.sh**: Docker deployment script

Key


