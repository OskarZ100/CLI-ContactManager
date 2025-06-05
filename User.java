//setting up the User class to have easier and a more simple way of getting user info 
public class User {
    FileManager file;
    String name;
    String phoneNumber;
    String email;
    String userName;
    String password;

    public User(String phoneNumber, String name, String email, String userName, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.file = new FileManager(userName + ".txt");
    }

    public void setPassword(String newPassword){

    }

    public void updateNumber(String newNumber){

    }

    public void updateEmail(String newEmail){

    }

    public void removeContact(int number){

    }

    public String toString(){
        String finalString = name + "/" + phoneNumber + "/" + email + "/" + userName + "/" + password;
        return finalString;
    }
} 
