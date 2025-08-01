import java.io.*;
import java.net.*;

public class EmailReceiverServer{
    public static void main(String[] args){
        try{
            ServerSocket socketServer=new ServerSocket(5000);
            Socket socket=socketServer.accept();

            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String from=reader.readLine();
            String to=reader.readLine();
            String subject=reader.readLine();
            String body=reader.readLine();

            File inbox=new File("users\\"+to+"\\inbox");

            inbox.createNewFile();
            
            BufferedWriter writer=new BufferedWriter(new FileWriter(inbox,true));
            writer.write("From: "+from);
            writer.newLine();
            writer.write("To: "+to);
            writer.newLine();
            writer.write("Subject: "+subject);
            writer.newLine();
            writer.write(body);
            writer.newLine();
            writer.newLine();
            writer.flush();
            writer.close();

            reader.close();
            socket.close();

            System.out.println("Email received successfully..!");
        }catch(Exception e){
            System.out.println("Error occurred on receiver side: "+e);
        }
    }
}
