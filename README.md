# Task Management System

[GitHub Repository](https://github.com/meehirchoudhary9167/Task-Management-System)

A full-stack **Task Management System** built using **Spring Boot**, **Java**, **MySQL**, **React (Vite)**, and **Bootstrap**. The system allows users to manage their tasks with CRUD operations, track due dates, and receive email reminders via **Ethereal**.  

---

## Features

### User Management
- Secure sign up, login, and logout.
- Each user can only access their own tasks.

### Task Management
- Create, edit, delete, and view tasks.
- Task fields include:
  - **Title** (required)
  - **Description** (optional)
  - **Due Date & Time** (optional)
  - **Completion Status** (pending/completed)
- Tasks are stored in a MySQL database.
- Frontend displays tasks in a simple list/table.
- Mark tasks as completed.

### Email Reminders
- Users receive email reminders **30 minutes before the task due time**.
- Emails are sent via **Ethereal SMTP** for testing purposes.
- Background processing ensures reminders do not block the main app flow.

---

## Technology Stack
- **Backend:** Spring Boot, Java, Spring Data JPA, Spring Security
- **Frontend:** React, Vite, Bootstrap
- **Database:** MySQL
- **Email:** Ethereal SMTP
- **Tools:** VS Code, IntelliJ IDEA

---

## Getting Started

### Prerequisites
- Java 17
- Node.js & npm
- MySQL
- VS Code / IntelliJ IDEA

### Backend Setup
1. Clone the repository:  
   ```bash
   git clone https://github.com/meehirchoudhary9167/Task-Management-System.git
Navigate to the backend folder:


cd todo-Backend
Configure MySQL database in application.properties:


spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
Run the backend using IntelliJ or Maven:


mvn spring-boot:run

Frontend Setup
Navigate to the frontend folder:

cd task-frontend
Install dependencies:


npm install
Start the frontend:


npm run dev

Open the frontend in your browser (default: http://localhost:5173).

SMTP Configuration (Ethereal)
Ethereal is used to send test emails for reminders:


spring.mail.host=smtp.ethereal.email
spring.mail.port=587
spring.mail.username=YOUR_ETHEREAL_EMAIL
spring.mail.password=YOUR_ETHEREAL_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
Check your messages on the Ethereal Messages Page.

Usage
Sign up as a new user.

Log in using your credentials.

Create tasks with title, description, and optional due date/time.

View your tasks and mark them as completed.

Receive email reminders before tasks are due.

Notes
The project uses background scheduling to send email reminders every 60 seconds.

Frontend and backend are decoupled for a modern full-stack architecture.

Ethereal is used for email testing; no real emails are sent.

Author
Meehir Choudhary
Email: meeirchoudhary9167@gmail.com
GitHub: https://github.com/meehirchoudhary9167

