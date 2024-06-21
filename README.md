# Microservice Project

## Overview

This project is a collection of microservices built using Java Spring Boot. The project includes the following components:

- *Company Microservice*
- *Job Microservice*
- *Review Microservice*
- *Cloud Gateway*
- *Config Server (hosted on GitHub)*
- *Zipkin for Distributed Tracing*

## Architecture

![Architecture Diagram](path-to-your-architecture-diagram.png)

## Microservices

### Company Microservice

Handles company-related operations.

### Job Microservice

Manages job postings and related functionalities.

### Review Microservice

Handles reviews related to jobs and companies.

### Cloud Gateway

Acts as the entry point for all the microservices, handling routing and authentication.

### Config Server

Externalizes configuration management for different environments.

### Zipkin

Used for distributed tracing to track the flow of requests across microservices.

## Communication Between Microservices

### Synchronous Communication

Synchronous communication between the Job Microservice and the Company Microservice is done via HTTP calls using OpenFeign client.

### Asynchronous Communication

Asynchronous communication between the Review Microservice and the Job Microservice is achieved using RabbitMQ.

## Technologies Used

- *Java Spring Boot*
- *Spring Cloud Gateway*
- *Spring Cloud Config*
- *OpenFeign*
- *RabbitMQ*
- *Zipkin*

## Getting Started

### Prerequisites

- Java 11+
- Maven
- RabbitMQ
- Docker (optional, for containerization)
- Git

### Installation

1. *Clone the Repository*

    sh
    git clone https://github.com/yourusername/your-repo.git
    cd your-repo
    

2. *Build the Project*

    sh
    mvn clean install
    

3. *Run Config Server*

    sh
    cd config-server
    mvn spring-boot:run
    

4. *Run Zipkin*

    sh
    docker run -d -p 9411:9411 openzipkin/zipkin
    

5. *Run Microservices*

    sh
    cd company-service
    mvn spring-boot:run

    cd ../job-service
    mvn spring-boot:run

    cd ../review-service
    mvn spring-boot:run

    cd ../cloud-gateway
    mvn spring-boot:run
    

## Usage

- *Access Cloud Gateway*: [http://localhost:8080](http://localhost:8080)
- *Access Zipkin Dashboard*: [http://localhost:9411](http://localhost:9411)

## Configuration

Configurations are managed by the Config Server and stored in a GitHub repository. Ensure you have the correct configuration files in your config repo.

## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push to the branch.
5. Open a pull request.


## Contact

If you have any questions, feel free to reach out:

- Email: edwinlinsonpaul@gmail.com
- GitHub: [yourusername](https://github.com/edwinlinsonpaul)
