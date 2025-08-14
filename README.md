

# SimpleEmailServer

A simple client-server based email simulation system built using **Java**, demonstrating **TCP/IP socket programming**, **Object-Oriented Programming (OOP)**, **Dependency Injection**, and **Database operations (SQLite)**. The project allows users to register, login, and send emails, which are stored in a database.

---

## Features

* **Send and receive emails** using a TCP/IP-based custom protocol.
* **User registration and login** stored in an SQLite database.
* **Inbox and Sent email management** using database tables.
* **Modular code structure** with OOP principles and dependency injection.

---

## Technologies Used

| Technology               | Purpose                                              |
| ------------------------ | ---------------------------------------------------- |
| **Java**                 | Core programming language                            |
| **TCP/IP Sockets**       | Network communication between client and server      |
| **SQLite**               | Database to store user credentials and emails        |
| **OOP Concepts**         | Encapsulation, modular design, class-based structure |
| **Dependency Injection** | Decoupling email server from database instance       |

---

## Project Structure

```
SimpleEmailServer/
│
├── src/
│   ├── Main.java                 # Client CLI to register, login, and send emails
│   ├── SimpleEmailServer.java    # Handles sending emails via TCP socket
│   ├── User.java                 # User model
│   ├── Database.java             # SQLite DB handler
│   ├── DatabaseCopy.java         # Wrapper for Database with DI
│   └── EmailReceiverServer.java  # TCP Server to receive emails and store them
│
├── users.db                      # SQLite database storing credentials & emails
│
└── ...
```

---

## How It Works

1. **Registration**

   * User registers with name, email, and password.
   * Credentials are stored in the SQLite database.

2. **Sending an Email**

   * Client collects `from`, `to`, `subject`, and `body`.
   * Sends data using a TCP socket to the server.
   * Email is inserted into **Sent** table and delivered to the receiver.

3. **Receiving an Email**

   * Server listens on port 5000.
   * Accepts email data from client and stores it in the **Inbox** table of the database.

4. **Dependency Injection**

   * `SimpleEmailServer` receives a `DatabaseCopy` instance via its constructor.
   * Promotes loose coupling and easier testing.

---

## Getting Started

### Prerequisites

* Java JDK 8 or higher installed.
* VS Code or any Java IDE.
* SQLite (optional, if you want to inspect the database).

### Compile

```bash
javac *.java
```

### Run the Server

```bash
java EmailReceiverServer
```

### Run the Client (in a separate terminal)

```bash
java Main
```

---

## Example Inbox Output (Database Query)

Querying the **Inbox** table after sending an email:

| mail\_from | name  | subject   | message                        |
| ---------- | ----- | --------- | ------------------------------ |
| Anjali     | Nidhi | Greetings | Hello Nidhi, hope you're well! |

---

## Notes

* This is a simulation and does not send real emails over the internet.
* All data is stored in a local SQLite database.
* Demonstrates **TCP/IP networking**, **OOP**, and **Dependency Injection** concepts.

---

