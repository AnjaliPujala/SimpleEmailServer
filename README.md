

# SimpleEmailServer

A simple client-server based email simulation system built using **Java**, demonstrating **TCP/IP socket programming**, **Object-Oriented Programming (OOP)**, **Dependency Injection**, and **Database operations (SQLite)**. The project allows users to register, login, send emails, and optionally generate email bodies using a **Python ML model**.

---

## Features

* **Send and receive emails** using a TCP/IP-based custom protocol.
* **User registration and login** stored in an SQLite database.
* **Inbox and Sent email management** using database tables.
* **Automatic email body generation** using a Python ML model based on subject keywords.
* **Modular code structure** with OOP principles and dependency injection.

---

## Technologies Used

| Technology               | Purpose                                              |
| ------------------------ | ---------------------------------------------------- |
| **Java**                 | Core programming language                            |
| **TCP/IP Sockets**       | Network communication between client and server      |
| **SQLite**               | Database to store user credentials and emails        |
| **Python (ML)**          | Generates email body based on subject                |
| **OOP Concepts**         | Encapsulation, modular design, class-based structure |
| **Dependency Injection** | Decoupling email server from database instance       |

---

## Project Structure

```
SimpleEmailServer/
│
├
├── Main.java                 # Client CLI for registration, login, and sending emails
│── SimpleEmailServer.java    # Handles sending emails via TCP socket and calls Python ML for email body
│── User.java                 # User model
│── Database.java             # SQLite DB handler
│── DatabaseCopy.java         # Wrapper for Database with DI
│── EmailReceiverServer.java  # TCP Server to receive emails and store them
│
├── ml_models/                    # Python ML model for email body generation
│   ├── predict_email.py
│   ├── email_vectorizer.pkl
|   ├── train_email_model.py
│   └── email_nn_model.pkl
│
├── users.db                      # SQLite database storing credentials & emails
├── README.md                     # Project documentation
└── ...
```

---

## How It Works

1. **Registration**

   * User registers with name, email, and password.
   * Credentials are stored in the SQLite database.

2. **Sending an Email**

   * Client collects `from`, `to`, `subject`, and optionally generates body using Python ML.
   * If ML body is not available or user rejects it, client can enter a custom body.
   * Sends data using a TCP socket to the server.
   * Email is inserted into **Sent** table and delivered to the receiver.

3. **Receiving an Email**

   * Server listens on port 5000.
   * Accepts email data from client and stores it in the **Inbox** table of the database.

4. **Dependency Injection**

   * `SimpleEmailServer` receives a `DatabaseCopy` instance via its constructor.
   * Promotes loose coupling and easier testing.

5. **ML Email Body Generation**

   * Python script `ml_models/predict_email.py` uses a pre-trained model to generate a suggested email body based on subject keywords.
   * User can accept or override the generated email body.

---

## Getting Started

### Prerequisites

* Java JDK 8 or higher installed.
* VS Code or any Java IDE.
* SQLite (optional, to inspect the database).
* Python 3.x with `scikit-learn` and `pickle` installed (for ML model).

### Compile Java Code

```bash
javac src/*.java
```

### Run the Server

```bash
java -cp <path> EmailReceiverServer
```

### Run the Client (in a separate terminal)

```bash
java -cp <path> Main
```

---

## Example Inbox Output (Database Query)

Querying the **Inbox** table after sending an email:

| mail\_from | name  | subject   | message                     |
| ---------- | ----- | --------- | --------------------------- |
| Anjali     | Nidhi | Greetings | Hi, hope you're doing well! |
| Anjali     | John  | Welcome   | Welcome to our platform!    |

---

## Notes

* This is a simulation and does not send real emails over the internet.
* All data is stored in a local SQLite database.
* Demonstrates **TCP/IP networking**, **OOP**, **Dependency Injection**, and **Python ML integration** for generating email bodies.

---
