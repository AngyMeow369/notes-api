# Notes API

A minimal CRUD REST API for managing notes with authentication using Spring Boot and MySQL.

---

## Setup

1. Clone the repository:
```bash
git clone https://github.com/AngyMeow369/notes-API.git
cd notes-API
Configure database in application.properties:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/notesdb
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Run the application:

bash
Copy code
mvn spring-boot:run
Database Schema
Users
id	email	password (hashed)	username
1	neeraj@example.com	$2a$10$...	neeraj

Notes
id	title	content	user_id
1	Neeraj Note 1	Content for Neeraj's note	1

Authentication
Basic Auth (username & password)

Users must register first via /user/register

Authenticated endpoints: all /notes/**

API Endpoints
User
POST /user/register
Register a new user

json
Copy code
Request:
{
  "username": "meow",
  "email": "meow@example.com",
  "password": "password123"
}

Response:
{
  "id": 5,
  "username": "meow",
  "email": "meow@example.com"
}
GET /user/get/me
Get currently authenticated user

json
Copy code
Response:
{
  "id": 5,
  "username": "meow",
  "email": "meow@example.com"
}
Notes
POST /notes
Create a new note for the authenticated user

json
Copy code
Request:
{
  "title": "My Note",
  "content": "This is a note content."
}

Response:
{
  "id": 6,
  "title": "My Note",
  "content": "This is a note content."
}
GET /notes
Get all notes for the authenticated user

json
Copy code
Response:
[
  {
    "id": 2,
    "title": "Meow Note 1",
    "content": "First note for Meow."
  },
  {
    "id": 5,
    "title": "Meow Note 2",
    "content": "Second note for Meow."
  }
]
GET /notes/{id}
Get a note by ID (only if it belongs to the user)

json
Copy code
Response:
{
  "id": 5,
  "title": "Meow Note 2",
  "content": "Second note for Meow."
}
PUT /notes/{id}
Update a note (only if it belongs to the user)

json
Copy code
Request:
{
  "title": "Updated Note",
  "content": "Updated content."
}

Response:
{
  "id": 5,
  "title": "Updated Note",
  "content": "Updated content."
}
DELETE /notes/{id}
Delete a note (only if it belongs to the user)

json
Copy code
Response:
"Note deleted successfully"
Failure Mode & Mitigation
Failure Mode: User tries to access/update/delete another user's note (Unauthorized access).

Mitigation: Service layer checks ownership before returning/modifying/deleting a note. Returns 403 Forbidden if the user is not the owner.

Notes
Passwords are hashed using BCrypt. Users must register to create their own password.

All authenticated endpoints require Basic Auth.

pgsql
Copy code

This is a **complete single README**, covering setup, DB, routes, auth, request/response examples, and a failure mode with mitigation. You can paste it directly into `README.md` without any changes.
