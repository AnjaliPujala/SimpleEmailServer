import java.io.*;
import java.net.*;

public class EmailReceiverServer {
    public static void main(String[] args) {
        try {
            ServerSocket socketServer = new ServerSocket(5000);
            Socket socket = socketServer.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String from = reader.readLine();
            String to = reader.readLine();
            String subject = reader.readLine();
            String body = reader.readLine();

            Database database = new Database();
            database.receiveEmail(from, to, subject, body);
            socket.close();
            socketServer.close();

        } catch (Exception e) {
            System.out.println("Error occurred on receiver side: " + e);
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 5d34e8d7758806a5700f0e8e7ee3e54dc8d2064e
