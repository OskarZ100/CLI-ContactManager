import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//making a seperate class for getting info from files in order to make it more easy to work with 
public class FileManager {
    String fileName;
    
    public FileManager(String fileName){
        this.fileName = fileName;
    }

    public void createFile(){
        try{
            File newFile = new File(fileName);
            if(newFile.createNewFile()){
                System.out.println("Created Users contact list");
            }else{
                System.out.println(fileName + " already has a contact list");
            }
        }catch(Exception e){
            System.out.println("Could not create new file");
        }
    }

    public ArrayList<String> outputUsers(){
        ArrayList users = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return users;
    }

    public boolean isEmpty(){
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            if(line == null){
                return true;
            }
        }catch(IOException e){
            System.out.println("There has been an error in the isEmpty() function");
            return true;
        }

        return false;
    }

    public void addContact(String contact){
        try (FileWriter fileWriter = new FileWriter(fileName, true)){
            if(!(contact.equals(""))){
                fileWriter.write(("\n" + contact));
            }
            
            System.out.println("added the contact!");
        }catch(Exception e){
            System.out.println("Couldnt add contact");
        }
    }

    public void removeContact(String contact){
        boolean wasRemoved = false;
        ArrayList<String> current = outputUsers();
        ArrayList<String> newList = new ArrayList<>();
        for(String contact_rc : current){
            if(contact_rc.split("/")[0].equals(contact)){
                wasRemoved = true;
                continue;
            }
            newList.add(contact_rc);
        }
        if(wasRemoved == false){
            System.out.println("Did not remove anyone");
        }else{
            File file = new File(fileName);
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.print(""); // Writes an empty string to the file
            } catch (IOException e) {
                System.err.println("Error clearing file: " + e.getMessage());
            }
            for(String contact_rc : newList){
                addContact(contact_rc);
                
            }
        }
    }

    public void addUser(User user){
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
               fileWriter.write("\n" + user.toString());
               System.out.println("\n Added User \n");
           } catch (IOException e) {
               System.err.println("Error appending to the file: " + e.getMessage());
           }
    }
}
