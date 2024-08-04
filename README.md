# PCCW Assignment

## Project Description

This project is an implementation of a technical assignment for PCCW. It is built using a Spring Boot microservices architecture and includes the following components: user registration, modification, query, and deletion back-end APIs, as well as front-end pages.
- **Eureka Server**: Service registry for microservices.
- **Zuul Gateway**: API gateway for routing and load balancing.
- **User Service**: Manages user-related operations.
- **Email Service**: Handles email sending and notifications.
- **UI**: UI to register, edit, deactivate, show existing registered users and view all emails that
  were sent.

## Technology Stack

- **JDK 1.8**: Java Development Kit version used.
- **Spring Boot 2.1.5.RELEASE**: Framework version used.
- **MySQL 8.0**: Database used.
- **MyBatis-Plus 3.1.2**: Database operation framework.
- **JUnit & Mockito**: Testing frameworks for unit and integration tests.
- **Docker**: Containerization platform for deploying the application.
- **Maven**: Build automation tool for managing project dependencies and build lifecycle.
- **HTML, Bootstrap, Axios, jQuery**: Frontend technologies for building the user interface.

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

## Key Resources

- **Frontend Code**: The frontend code is located in zuul, `pccw-assignment/zuul/src/main/resources/static`
- **Database Scripts**: Available in the `pccw-assignment/zz_dbscripts` directory.
- **Docker Deployment**: The deployment script is located in `pccw-assignment/docker_deploy.sh`.
- **Docker Profile**: In `pccw-assignment/module/src/main/docker` for every module.
- **API Documentation**: Available in the `pccw-assignment/zz_apidocs` directory.

## How to Run

1. **Install Prerequisites**:
  - **Java 1.8**: Ensure JDK 1.8 is installed.
  - **MySQL 8.0**: Install and configure MySQL 8.0. Run database scripts in `pccw-assignment/zz_dbscripts`, update database configurations in user-service and email-service.
  - **Maven**: Ensure maven is installed.
  - **Docker**: Ensure docker is installed.

2. **Download the Code**:
  - Clone the repository:
    ```bash
    git clone https://github.com/zhwy119/pccw-assignment/
    cd pccw-assignment
    ```

3. **Run Docker Deployment**:
  - Execute the deployment script:
    ```bash
    ./docker_deploy.sh
    ```

   The script will build Docker images and start containers for the microservices.

      **Troubleshooting:**
- **Permission Issues**: If you encounter a permission error with `docker_deploy.sh`, ensure the script has executable permissions. You can set the permissions using:
  ```bash
  chmod +x docker_deploy.sh
  ```
- **openjdk:8 Download Issues**: If you encounter issues pulling the `openjdk:1.8` image, ensure that your Docker daemon is configured with a working registry mirror. You can configure Docker to use a mirror by editing `/etc/docker/daemon.json` with:
  ```json
  {
    "registry-mirrors": ["https://registry.docker-cn.com"]
  }
  ```
  After editing the file, reload the systemd configuration and restart Docker with:
  ```bash
  sudo systemctl daemon-reload
  sudo systemctl restart docker
  ```
4. **Access the Services**:
  - **Eureka Server**: http://127.0.0.1:9094
  - **Frontend UI**: http://127.0.0.1:9095/index.html
  - **API Calling(Zuul Gateway)**: http://127.0.0.1:9095/{apipath}
  - **User Service** and **Email Service**: Access through the Zuul gateway routing.

