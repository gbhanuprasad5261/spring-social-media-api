# Spring Social Media Blog API

## 📌 Overview

Spring Social Media Blog API is a backend RESTful application developed using **Spring Boot**.  
This project simulates a micro-blogging platform where users can register, log in, and create, update, retrieve, and delete messages.

The application follows a layered architecture using Spring MVC and Spring Data JPA for clean separation of concerns.

Developed during the **Revature Pre-Training Program**.

---

## 🚀 Features

- User Registration & Authentication
- Create, Update, Delete Messages
- Retrieve All Messages
- Retrieve Messages by ID
- Retrieve Messages by User
- Proper HTTP Status Code Handling
- Input Validation
- RESTful API Design

---

## 🛠 Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- H2 In-Memory Database
- Maven
- JUnit

---

## 🏗 Architecture

The project follows a layered architecture:

- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Contains business logic
- **Repository Layer** – Handles database operations using JPA
- **Entity Layer** – Represents database tables

This structure improves maintainability, scalability, and testability.

---

## 📂 Database Structure

### Account Table
- accountId (Primary Key)
- username (Unique)
- password

### Message Table
- messageId (Primary Key)
- postedBy (Foreign Key → Account)
- messageText
- timePostedEpoch

---

## 📡 API Endpoints

### Authentication
- `POST /register`
- `POST /login`

### Messages
- `POST /messages`
- `GET /messages`
- `GET /messages/{id}`
- `DELETE /messages/{id}`
- `PATCH /messages/{id}`
- `GET /accounts/{accountId}/messages`

---

## ▶️ How to Run

1. Clone the repository
2. Open in IDE (IntelliJ / VS Code)
3. Run the Spring Boot Application class
4. Server starts at:


---

## 📈 Learning Outcomes

- Gained hands-on experience with Spring Boot
- Implemented RESTful APIs following best practices
- Used dependency injection and Spring annotations
- Applied validation and error handling techniques
- Understood JPA repository pattern

---

## 👨‍💻 Author

Bhanu Prasad G  
Backend Developer | Java & Spring Boot
