
// This code is a simple email server implementation in Java that allows user registration and sending emails.
// It creates user directories, stores credentials, and manages email sending between users.
import java.util.*;

import java.io.*;
import java.net.*;

// User class
class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

// Database wrapper class
class DatabaseCopy {
    private Database database;

    public DatabaseCopy(Database database) {
        this.database = database;
    }

    public void registerUser(User user) {
        database.addUser(user.getName(), user.getEmail(), user.getPassword());
    }

    public boolean userExists(String email) {
        return database.userExists(email);
    }

    public boolean validateLogin(String email, String password) {
        return database.validateLogin(email, password);
    }

    public void sendMail(String sender, String receiver, String subject, String message) {
        database.sendEmail(sender, receiver, subject, message);
    }
}

// Email server class with Dependency Injection
class SimpleEmailServer {
    private DatabaseCopy db;

    public SimpleEmailServer(DatabaseCopy db) {
        this.db = db;
    }

    private void addTextIntoFiles(BufferedWriter writer, String sender, String receiver, String subject, String body)
            throws IOException {
        writer.write(sender);
        writer.newLine();
        writer.write(receiver);
        writer.newLine();
        writer.write(subject);
        writer.newLine();
        writer.write(body);
        writer.newLine();
        writer.newLine();
    }

    public void sendEmail(String sender, String receiver, String subject, String body) {
        try {
            Socket socket = new Socket("localhost", 5000);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            addTextIntoFiles(writer, sender, receiver, subject, body);
            writer.flush();
            writer.close();

            db.sendMail(sender, receiver, subject, body);
            socket.close();
            System.out.println("Email sent to " + receiver + " successfully!");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public String generateEmailBody(String subject) {
        String body = "";
        try {

            String[] subjectWords = subject.split(" ");
            String[] command = new String[subjectWords.length + 2];
            command[0] = "python";
            command[1] = "ml_models/predict_email.py";
            System.arraycopy(subjectWords, 0, command, 2, subjectWords.length);

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            process.waitFor();
            body = output.toString();

            reader.close();
            process.destroy();
        } catch (Exception e) {
            System.out.println("Error generating email body: " + e);
            body = "not found";
        }

        return body;
    }
}

// Main class
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Database database = new Database();
        DatabaseCopy dbCopy = new DatabaseCopy(database);
        SimpleEmailServer emailServer = new SimpleEmailServer(dbCopy);

        System.out.println("What do you want to do? (register/send)");
        String action = sc.next();

        if (action.equalsIgnoreCase("register")) {
            handleRegistration(dbCopy);
        } else if (action.equalsIgnoreCase("send")) {
            handleSendEmail(dbCopy, emailServer);
        } else {
            System.out.println("Invalid option!");
        }

        database.close();
        sc.close();
    }

    private static void handleRegistration(DatabaseCopy dbCopy) {
        System.out.println("Enter your name:");
        String name = sc.next();
        System.out.println("Enter your email:");
        String email = sc.next();
        System.out.println("Enter your password:");
        String password = sc.next();

        User user = new User(name, email, password);
        dbCopy.registerUser(user);
    }

    private static void handleSendEmail(DatabaseCopy dbCopy, SimpleEmailServer emailServer) {
        System.out.println("Enter your email:");
        String email = sc.next();
        System.out.println("Enter your password:");
        String password = sc.next();

        if (!dbCopy.validateLogin(email, password)) {
            System.out.println("You haven't registered!");
            return;
        }

        System.out.println("Enter receiver's email:");
        String receiver = sc.next();
        if (!dbCopy.userExists(receiver)) {
            System.out.println("This user doesn't exist in our database!");
            return;
        }

        sc.nextLine();
        System.out.println("Enter subject:");
        String subject = sc.nextLine();
        String body = emailServer.generateEmailBody(subject);
        if (!body.equals("not found")) {
            System.out.println("Generated email body: " + body);
            System.out.println("Do you want to use this body? (yes/no)");
            String useBody = sc.next();
            sc.nextLine();

            if (useBody.equalsIgnoreCase("no")) {
                System.out.println("Enter your custom body:");
                body = sc.nextLine();
            }
        }
        System.out.println("Email sending..!");
        emailServer.sendEmail(email, receiver, subject, body);
    }
}
