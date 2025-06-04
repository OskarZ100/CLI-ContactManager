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
                            System.out.println("What is the phone number of your user?");
                            choice_phoneNum = input.nextLine();
                            //make a valid phone number program 
                            validPhoneNumber = validNumber(choice_phoneNum);
                        }
                        System.out.println("What is the email of your user?");
                        String choice_email = input.nextLine();
                        System.out.println("What is the user name of your user?");
                        String choice_userName = input.nextLine();
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
                        
                           String loginChoice = input.nextLine();
                           if(loginChoice.equals("")){
                                System.out.println("Exiting the login \n");
                                loginLoop = true;
                           }
                           
                           if(selectingUser){
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
            returnList.add(newUser);
        }
        return returnList;
    }

    public static boolean validNumber(String num){
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
