import java.util.*;
import java.io.*;
import java.net.*;

class SimpleEmailServer{

    public void registerUser(User user){
        try{
            File folder=new File("users\\"+user.name);
            
            if(!folder.exists()){     
                folder.mkdirs();
                File inbox=new File(folder+"\\inbox");
                inbox.createNewFile();
                File sent=new File(folder+"\\sent");
                sent.createNewFile();
                File file=new File("credentials.txt");
                file.createNewFile();
                BufferedWriter writer=new BufferedWriter(new FileWriter(file,true));
                writer.write(user.getUser());
                writer.newLine();
                writer.flush();
                writer.close();
                
                System.out.println("User added successfully!");
             
            }else{
                System.out.println("User name already exists");
            }
            
        }catch(Exception e){
            System.out.println("Exception while adding user: "+e);
        }
    }
    private void addTextIntoFiles(BufferedWriter writer, User sender, User reciever, String subject, String body) throws Exception{
        writer.write(sender.name);
        writer.newLine();
        writer.write(reciever.name);
        writer.newLine();
        writer.write(subject);
        writer.newLine();
        writer.write(body);
        writer.newLine();
        writer.newLine();
    }
    public void sendEmail(User sender, User reciever, String subject, String body){
        try{
            Socket socket=new Socket("localhost",5000);
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            addTextIntoFiles(writer,sender,reciever,subject,body);
            writer.flush();
            writer.close();
            File sent=new File("users\\"+sender.name+"\\sent");
            sent.createNewFile();
            BufferedWriter fileWriter=new BufferedWriter(new FileWriter(sent,true));
            addTextIntoFiles(fileWriter,sender,reciever,subject,body);
            fileWriter.flush();
            fileWriter.close();

            socket.close();
            System.out.println("Email sent to "+reciever+" successfully..!");
        }catch(Exception e){
            System.out.println("Error occured: "+e);
        }
    }
}

class User{
    String name;
    String password;
    User(String name, String password){
        this.name=name;
        this.password=password;
    }

    public String getUser(){
        return name+","+password;
    }
}

public class Main{
    public static void main(String[] args){
        SimpleEmailServer emailServer=new SimpleEmailServer();
        User sender=new User("Anjali","anjali123");
        emailServer.registerUser(sender);
        User receiver=new User("Nidhi","nidhi123");
        emailServer.registerUser(receiver);
        emailServer.sendEmail(sender, receiver, "Greetings", "Hello Nidhi, how are you?");
    }
}
