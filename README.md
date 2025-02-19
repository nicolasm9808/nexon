# Nexon - Social Media Platform

## Overview

Nexon is a modern social media platform built with Spring Boot (backend) and React (frontend). It enables users to register, create posts, like posts, comment, follow users, and receive notifications.

## Technologies Used

- **Backend:** Java, Spring Boot, Spring Security, JPA, PostgreSQL
- **Frontend:** React (Repository: [Nexon Frontend](https://github.com/nicolasm9808/nexon-frontend))
- **Database:** PostgreSQL

## Installation Guide

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### Backend Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/nicolasm9808/nexon-backend.git
   cd nexon-backend
   ```
2. **Create the PostgreSQL database:**
   ```sql
   CREATE DATABASE nexon;
   ```
3. **Update database credentials in **``**:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nexon
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. **Build and run the backend:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

The backend will run on `http://localhost:8081`

### Frontend Setup

1. **Clone the frontend repository:**
   ```sh
   git clone https://github.com/nicolasm9808/nexon-frontend.git
   cd nexon-frontend
   ```
2. **Install dependencies:**
   ```sh
   npm install
   ```
3. **Start the development server:**
   ```sh
   npm start
   ```

The frontend will run on `http://localhost:3000`

## API Endpoints

### **Authentication**

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and obtain a JWT token

### **Users**

- `GET /api/users/{username}` - Get user profile
- `PUT /api/users/{username}` - Update user profile
- `DELETE /api/users/{username}` - Delete user account

### **Posts**

- `POST /api/posts` - Create a new post
- `GET /api/posts/{postId}` - Get a specific post
- `DELETE /api/posts/{postId}` - Delete a post

### **Likes**

- `POST /api/likes/{postId}` - Toggle like/unlike on a post
- `GET /api/likes/{postId}` - Get users who liked a post
- `GET /api/likes/{postId}/liked` - Check if the authenticated user liked a post

### **Comments**

- `POST /api/comments/{postId}` - Add a comment to a post
- `GET /api/comments/{postId}` - Get comments for a post

### **Followers**

- `POST /api/follow/{username}` - Follow/unfollow a user
- `GET /api/followers/{username}` - Get followers of a user
- `GET /api/following/{username}` - Get users followed by a user

### **Notifications**

- `GET /api/notifications` - Get user notifications
- `PUT /api/notifications/{notificationId}/read` - Mark notification as read

## Security & Authentication

- The project uses JWT for authentication.
- Each request requires a valid JWT token in the `Authorization` header: `Bearer {token}`.

## Contribution

Feel free to fork the repository and submit pull requests!

## License

This project is licensed under the MIT License.

## Contact

For any inquiries or issues, contact [Nicolás Méndez](https://github.com/nicolasm9808).

