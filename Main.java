import java.util.*;
import java.io.*;


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

    public void sendEmail(User sender, User reciever, String subject, String body){

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
        User user1=new User("Anjali","anjali123");
        emailServer.registerUser(user1);
    }
}
