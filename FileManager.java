import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//making a seperate class for getting info from files in order to make it more easy to work with 
public class FileManager {
    String fileName;
    
    public FileManager(String fileName){
        this.fileName = fileName;
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

    public void addUser(User user){
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
               fileWriter.write("\n" + user.toString());
               System.out.println("Text appended to the file successfully.");
           } catch (IOException e) {
               System.err.println("Error appending to the file: " + e.getMessage());
           }
    }
}
