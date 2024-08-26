# Blogging Application with Mongo and Spring Boot

A Spring Boot application that provides a complete blogging platform using MongoDB for data storage. The application supports CRUD operations for posts, comments, likes, tags, categories, and users.

## Features

- **Post Management**: Create, update, delete, and retrieve blog posts.
- **User Management**: Manage user profiles and interactions.
- **Category & Tag Management**: Organize posts using categories and tags.
- **Comment & Like Functionality**: Engage with posts via comments and likes.
- **External API Integration**: Integration with external APIs, like JsonPlaceHolder.

## Project Structure

- **`collection/`**: MongoDB collections for entities like `Post`, `User`, `Comment`, etc.
- **`controller/`**: REST controllers for handling HTTP requests related to blog operations.
- **`exception/`**: Custom exceptions for handling errors, including `NotFoundException` and `ApiRequestException`.
- **`externalAPI/`**: Configuration and clients for external API interactions.
- **`service/` & `serviceImpl/`**: Service interfaces and implementations for handling business logic.
- **`resources/`**: Configuration files, including `application.properties`.

## Prerequisites

- Java 17+
- Maven 3.6.3+
- MongoDB

## Setup

1. **Clone the repository:**
    ```bash
    git clone https://github.com/ArjanaaTernava/BloggingAppMongoSpringBoot.git
    ```
2. **Navigate to the project directory:**
    ```bash
    cd BloggingAppMongoSpringBoot
    ```
3. **Build the project:**
    ```bash
    mvn clean install
    ```
4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```


