# SimpleEmailServer

A simple client-server based email simulation system built using **Java**, demonstrating **TCP/IP socket programming**, **Object-Oriented Programming (OOP)**, and **File I/O operations**. The project allows users to register, login, and send emails, which are stored in file-based inboxes.

---

## Features

- **Send and receive emails** using a TCP/IP-based custom protocol.
- **User registration and login** (username & password) handled via files.
- **Inbox management** using file system storage (no database used).
- **Modular code structure** with OOP principles.

---

## Technologies Used

| Technology          | Purpose                                               |
|---------------------|-------------------------------------------------------|
| **Java**            | Core programming language                             |
| **TCP/IP Sockets**  | Network communication between client and server       |
| **File I/O**        | Reading/writing user credentials and emails           |
| **OOP Concepts**    | Encapsulation, modular design, class-based structure  |

---

## Project Structure

```
SimpleEmailServer/
│
├── src/
│   ├── EmailReceiverServer.java    # TCP Server to receive emails and store them
│   ├── EmailClientSender.java      # TCP Client to send emails
│  
│
├── users/
│   ├── [username]/
│   │   └── inbox                   # Text file storing received emails
│   └── [username]/
│       └── inbox                   # Inbox for each registered user
│
├──....
```

---

## How It Works

1. **Registration**  
   - User registers with a name and password.  
   - Credentials are saved in a file named `credentials.txt`.

3. **Sending an Email**  
   - Client collects `from`, `to`, `subject`, and `body` fields.  
   - Sends data using a TCP socket to the server.

4. **Receiving an Email**  
   - Server listens on port 5000.  
   - Accepts email data and stores it in the `to` user’s inbox file.

---

## Getting Started

### Prerequisites

- Java JDK 8 or higher installed.
- VS Code.

### Compile

```bash
javac EmailReceiverServer.java
javac Main.java
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

## Example Inbox Output

Example contents of `users/Anjali/inbox` after receiving an email:

```
From: Anjali
To: Nidhi
Subject: Greetings
Hello Nidhi, hope you're doing well!
```

---

## Notes

- This is a simulation and does not send real emails.
- Data is stored in plain text for simplicity.

---

