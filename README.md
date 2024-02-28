# Social Media Application - Spring Boot Backend

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)

## Introduction
This project is a backend system for a social media application developed using Spring Boot. It includes functionalities for managing users, posts, and comments.

## Features
- User Management: Create, update, and delete user accounts.
- Post Management: Create, retrieve, update, and delete posts.
- Comment System: Add and manage comments on posts.

## Technologies Used
- **Spring Boot:** Backend framework for Java applications.
- **MySQL:** Database management system for data storage.
- **Spring Data JPA:** Simplifies data access with Spring applications.
- **Spring Security:** Authentication and authorization for the application.
- **Maven:** Project management and build tool.

## Setup
1. Clone the repository.
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory.
    ```bash
    cd social-media-application-backend
    ```

## Database Configuration
1. Create a MySQL database for the application.
    ```sql
    CREATE DATABASE social_media_db;
    ```

2. Update the `application.properties` file with the database connection details.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/social_media_db
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    ```

## Running the Application
1. Build the application.
    ```bash
    mvn clean install
    ```

2. Run the application.
    ```bash
    java -jar target/social-media-application.jar
    ```

## API Endpoints
- **User Endpoints:**
  - GET `/api/users`: Get all users.
  - GET `/api/users/{id}`: Get user by ID.
  - POST `/api/users`: Create a new user.
  - PUT `/api/users/{id}`: Update user by ID.
  - DELETE `/api/users/{id}`: Delete user by ID.

- **Post Endpoints:**
  - GET `/api/posts`: Get all posts.
  - GET `/api/posts/{id}`: Get post by ID.
  - POST `/api/posts`: Create a new post.
  - PUT `/api/posts/{id}`: Update post by ID.
  - DELETE `/api/posts/{id}`: Delete post by ID.

- **Comment Endpoints:**
  - GET `/api/comments`: Get all comments.
  - GET `/api/comments/{id}`: Get comment by ID.
  - POST `/api/comments`: Create a new comment.
  - PUT `/api/comments/{id}`: Update comment by ID.
  - DELETE `/api/comments/{id}`: Delete comment by ID.
