import java.util.*;

public class Main {
    public static void main(String[] args) {
        //setting up storage for users 
        FileManager usersTextFile = new FileManager("Users.txt");
        ArrayList<User> currentUsers = new  ArrayList<User>();
        Scanner input = new Scanner(System.in);
        User myUser = null;

        currentUsers = addAllCurrentUsers(usersTextFile);
        boolean running = true;
        boolean logged = false;
        System.out.println("Welcome to my Contact Manager");

        while(running){
            if(!logged){
                System.out.println("Menu-------------------" + "\n" + 
                "1) Return all current Users of the program" + "\n" + 
                "2) Add a User to the program " + "\n" +
                "3) Login as a User " + "\n" + 
                "4) return author info " + "\n" + 
                "5) exit program " + "\n");

                String choice = input.nextLine();

                switch(choice){
                    case "1":
                        //go through the users file and print each user Their name and UserName
                        int countForUsers = 0;
                        for(User i : currentUsers){
                            System.out.println(countForUsers + ") Name: " + i.name + " UserName: " + i.userName + "\n");
                            countForUsers += 1;
                        }
                        break;
                    case "2":
                        //will require the add user function just take all the right input and do it
                        boolean validPhoneNumber = false;
                        System.out.println("What is the name of your user?");
                        String choice_name = input.nextLine();
                        String choice_phoneNum = "000-000-0000";
                        while(!validPhoneNumber){
                            System.out.println("What is the phone number of your user?(###-###-####)");
                            choice_phoneNum = input.nextLine();
                            //make a valid phone number program 
                            validPhoneNumber = validNumber(choice_phoneNum);
                            if(!validPhoneNumber){
                                System.out.println("Please enter your phone number in the following format (123-456-7890)");
                            }
                        }
                        System.out.println("What is the email of your user?");
                        String choice_email = input.nextLine();
                        System.out.println("What is the user name of your user?");
                        String choice_userName = input.nextLine();
                        if(choice_userName.equals("Users")){
                            choice_userName = "users";
                        }
                        System.out.println("What is the password of your user?");
                        String choice_password = input.nextLine();
                        if(choice_email.equals("") || choice_name.equals("") || choice_password.equals("") || choice_userName.equals("")){
                            System.out.println("Did not provide valid input for one of the feilds not adding your user to the list");
                        }else{
                            String testCase = choice_email + choice_name + choice_password + choice_phoneNum + choice_userName;
                            if(testCase.indexOf("/") == -1){
                                User tempNewUserVariable = new User(choice_phoneNum, choice_name, choice_email, choice_userName, choice_password);
                                if(checkDupes(currentUsers, tempNewUserVariable)){
                                    usersTextFile.addUser(tempNewUserVariable);
                                    currentUsers.add(tempNewUserVariable);
                                    tempNewUserVariable.file.createFile();
                                }else{
                                    System.out.println("Do not enter someone with the same username or phonenumber as another user");
                                }
                                
                            }else{
                                System.out.println("Not allowed to use / character \n");
                            }
                            
                        }
                        
                        break;
                    case "3":
                        //get the current user if not null and then go through the current users
                        //check each one for the name he is trying to get 
                        //then put them in a loop until they get the password
                        boolean loginLoop = false;
                        User wantUser = null;
                        boolean selectingUser = true;
                        boolean passwordSelect = true;
                        while(!loginLoop){
                            if(selectingUser){
                                System.out.println("Enter your username or just click enter to exit");
                            }

                           
                           
                           if(selectingUser){
                                String loginChoice = input.nextLine();
                                if(loginChoice.equals("")){
                                        System.out.println("Exiting the login \n");
                                        loginLoop = true;
                                }
                                for(User i : currentUsers){
                                    if(i.userName.equals(loginChoice)){
                                      wantUser = i;
                                      selectingUser = false;
                                    }
                                }
                           }
                            if(!selectingUser){
                                System.out.println("Please enter your password");
                                String passSelection = input.nextLine();
                                if(passSelection.equals(wantUser.password)){
                                    myUser = wantUser;
                                    loginLoop = true;
                                    logged = true;
                                }else{
                                    System.out.println("Wrong password");
                                }
                            }       
    
                           

                        }
                        
                         
                        
                        break;
                    case "4":
                        //just return  my info 
                        System.out.println("Programmed by: Oskaras Zincenko");
                        System.out.println("This is a simple project I made to show my java fundementals \n");
                        break;
                    case "5":
                        System.out.println("Terminating the program");
                        running = false;
                        break;
                    default:
                        System.out.println("Please enter a valid aciton");
                }
            }else{
                System.out.println("Logged in Menu-------------------" + "\n" + 
                "1) Return all current stored Contacts" + "\n" + 
                "2) Add a Contact to your List " + "\n" +
                "3) Logout " + "\n" + 
                "4) Change your password " + "\n" + 
                "5) Change your email " + "\n" + 
                "6) Change your number " + "\n" + 
                "7) Remove a contact " + "\n" + "8) Exit Program " + "\n");

                String choice = input.nextLine();

                switch(choice){
                    case "1":
                        ArrayList<String> contactsArray = myUser.file.outputUsers();
                        int itteration = 1;
                        for(String cntct_s : contactsArray){
                            String[] contac_info = cntct_s.split("/");
                            if(!(contac_info.length < 3)){
                                String contact_name = contac_info[0];
                                String contact_phone = contac_info[1];
                                String contact_email = contac_info[2];

                                System.out.println(itteration + ") " + contact_name + "\n\t Phone: " + contact_phone + " \n\t Email: " + contact_email);
                                System.out.println("------------------------------ \n");
                                itteration += 1;
                            }
                        }
                        break;
                    case "2":
                        boolean phoneSelection = false;
                        System.out.println("What is the new contacts name?");
                        String contact_name = input.nextLine();
                        System.out.println("What is the new contacts Phone number(###-###-####) <-- format ?");
                        String contact_phone = "";
                        while(!phoneSelection){
                            contact_phone = input.nextLine();
                            if(validNumber(contact_phone)){
                                phoneSelection = true;
                            }else{
                                System.out.println("Please use the correct format for your contacts number");
                            }
                        }
                        
                        System.out.println("What is the new contacts email?");
                        String contact_email = input.nextLine();
                        if(contact_email.equals("") || contact_name.equals("") || contact_phone.equals("")){
                            System.out.println("Invalid input provided");
                        }
                        String testCase = contact_email + contact_name + contact_phone;
                        if(testCase.indexOf("/") == -1){
                            myUser.file.addContact(contact_name + "/" + contact_phone + "/" + contact_email);
                        }else{
                            System.out.println("Please do not use the / character");
                        }
                        break;
                    case "3":
                        System.out.println("Logging out");
                        logged = false;
                        break;
                    case "4":
                    case "5":
                    case "6":
                        ArrayList<User> tempUsers = new ArrayList<>();
                        System.out.println("What would you like the new one to be?");
                        String newVar = input.nextLine();
                        if(newVar.equals("") || newVar.indexOf("/") != -1){
                            System.out.println("Please enter a valid value");
                            break;
                        }
                        if(choice.equals("4")){
                            myUser.password = newVar;
                        }
                        if(choice.equals("5")){
                            myUser.email = newVar;
                        }
                        if(choice.equals("6")){
                            boolean loops = false;
                            while(!loops){
                                if(validNumber(newVar)){
                                    myUser.phoneNumber = newVar;
                                    loops = true;
                                }else{
                                    System.out.println("Please use the format (###-###-####)");
                                    newVar = input.nextLine();
                                }
                            }
                        }
                        for(User usersPast : currentUsers){
                            if(usersPast.userName.equals(myUser.userName)){
                                tempUsers.add(myUser);
                            }else{
                                tempUsers.add(usersPast);
                            }
                        }
                        currentUsers = tempUsers;
                        usersTextFile.resetUsers(currentUsers);
                        break;
                    case "7":
                        System.out.println("what contact would you like to remove?");
                        String remove = input.nextLine();
                        myUser.file.removeContact(remove);
                        break;
                    case "8":
                        System.out.println("Exiting program");
                        running = false;
                        break;
                    default:
                        System.out.println("Please provide a valid input from the list above");
                }


            }
            
        }
        
    }

    public static ArrayList<User> addAllCurrentUsers(FileManager file){
        ArrayList<User> returnList = new ArrayList<>();
        ArrayList<String> usersTextVersion = file.outputUsers();
        for(String i : usersTextVersion){
            if(i.equals("")){
                continue;
            }
            String[] splitUser = i.split("/");
            User newUser = new User(splitUser[1], splitUser[0], splitUser[2], splitUser[3], splitUser[4]);
            newUser.file.createFile();
            returnList.add(newUser);
        }
        return returnList;
    }

    public static boolean validNumber(String num){
        String[] temp = num.split("-");
        if(temp.length != 3){
            return false;
        }
        int count = 0;
        for(String i : temp){
            try{
                int x = Integer.parseInt(i);
                if(x > 999 && count != 2){
                    return false;
                }
                if(count == 2 && !(x >= 1000 && x <= 9999)){
                    return false;
                }
            }catch(Exception e){
                return false;
            }
            count += 1;
        }
        return true;
    }

    public static boolean checkDupes(ArrayList<User> users, User check){
        for(User i : users){
            if(i.userName.equals(check.userName) || i.phoneNumber.equals(check.phoneNumber)){
                return false;
            }
        }                               
        return true;                   
    }
}
