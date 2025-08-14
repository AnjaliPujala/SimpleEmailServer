<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private String url;
    Connection conn;

    public Database() {
        url = "jdbc:sqlite:users.db";
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connected to the database!");
                Statement stmt = conn.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (email TEXT PRIMARY KEY, name TEXT, password TEXT)";
                String createSentTable = "CREATE TABLE IF NOT EXISTS sent(name TEXT, mail_to TEXT, subject TEXT, message TEXT)";
                String createInboxTable = "CREATE TABLE IF NOT EXISTS inbox(name TEXT, mail_from TEXT, subject TEXT, message TEXT)";
                stmt.execute(createTableSQL);
                stmt.execute(createSentTable);
                stmt.execute(createInboxTable);
                // stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String name, String email, String password) {
        try {
            if (conn != null && !userExists(email)) {
                String insertSQL = "INSERT INTO users (email, name, password) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                pstmt.setString(1, email);
                pstmt.setString(2, name);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                System.out.println("User added successfully!");

            } else {
                System.out.println("User with email " + email + " already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String email) {
        try {
            if (conn != null) {
                String querySQL = "SELECT 1 FROM users WHERE email = ?";
                PreparedStatement pstmt = conn.prepareStatement(querySQL);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                // pstmt.close();
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateLogin(String email, String password) {
        try {
            if (conn != null) {
                String querySQL = "SELECT 1 FROM users WHERE email=? AND password=?";
                PreparedStatement pstmt = conn.prepareStatement(querySQL);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet res = pstmt.executeQuery();
                // pstmt.close();
                return res.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendEmail(String sender, String receiver, String subject, String message) {
        try {
            if (conn != null) {
                String queString = "INSERT INTO sent (name,mail_to,subject,message) VALUES (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(queString);
                stmt.setString(1, sender);
                stmt.setString(2, receiver);
                stmt.setString(3, subject);
                stmt.setString(4, message);
                stmt.executeUpdate();
                System.out.println("Email sent successfully!");
                // stmt.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveEmail(String sender, String receiver, String subject, String message) {
        try {
            if (conn != null) {
                String queString = "INSERT INTO inbox (name,mail_from,subject,message) VALUES (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(queString);
                stmt.setString(1, receiver);
                stmt.setString(2, sender);
                stmt.setString(3, subject);
                stmt.setString(4, message);
                stmt.executeUpdate();
                System.out.println("Email received successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private String url;
    Connection conn;

    public Database() {
        url = "jdbc:sqlite:users.db";
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connected to the database!");
                Statement stmt = conn.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (email TEXT PRIMARY KEY, name TEXT, password TEXT)";
                String createSentTable = "CREATE TABLE IF NOT EXISTS sent(name TEXT, mail_to TEXT, subject TEXT, message TEXT)";
                String createInboxTable = "CREATE TABLE IF NOT EXISTS inbox(name TEXT, mail_from TEXT, subject TEXT, message TEXT)";
                stmt.execute(createTableSQL);
                stmt.execute(createSentTable);
                stmt.execute(createInboxTable);
                // stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String name, String email, String password) {
        try {
            if (conn != null && !userExists(email)) {
                String insertSQL = "INSERT INTO users (email, name, password) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                pstmt.setString(1, email);
                pstmt.setString(2, name);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                System.out.println("User added successfully!");

            } else {
                System.out.println("User with email " + email + " already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userExists(String email) {
        try {
            if (conn != null) {
                String querySQL = "SELECT 1 FROM users WHERE email = ?";
                PreparedStatement pstmt = conn.prepareStatement(querySQL);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                // pstmt.close();
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateLogin(String email, String password) {
        try {
            if (conn != null) {
                String querySQL = "SELECT 1 FROM users WHERE email=? AND password=?";
                PreparedStatement pstmt = conn.prepareStatement(querySQL);
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet res = pstmt.executeQuery();
                // pstmt.close();
                return res.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendEmail(String sender, String receiver, String subject, String message) {
        try {
            if (conn != null) {
                String queString = "INSERT INTO sent (name,mail_to,subject,message) VALUES (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(queString);
                stmt.setString(1, sender);
                stmt.setString(2, receiver);
                stmt.setString(3, subject);
                stmt.setString(4, message);
                stmt.executeUpdate();
                System.out.println("Email sent successfully!");
                // stmt.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveEmail(String sender, String receiver, String subject, String message) {
        try {
            if (conn != null) {
                String queString = "INSERT INTO inbox (name,mail_from,subject,message) VALUES (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(queString);
                stmt.setString(1, receiver);
                stmt.setString(2, sender);
                stmt.setString(3, subject);
                stmt.setString(4, message);
                stmt.executeUpdate();
                System.out.println("Email received successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
>>>>>>> 5d34e8d7758806a5700f0e8e7ee3e54dc8d2064e
}