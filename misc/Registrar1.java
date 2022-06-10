import java.util.Scanner;

/**
 Prompts the user for basic registration information and prints it to the screen.
 */
public class Registrar1 {
    public static void main(String[] args) {

        System.out.print("Welcome to our app!");

        Scanner consoleReader = new Scanner(System.in);
        
        // int numberofTries = 0;
        boolean isRunning = true;

        // while (numberOftries < 3) { ... }
        while (isRunning) {

            System.out.println("Please make a seledtion from the optiond below");
            System.out.println("\t1) Register\n\t2) Login\n\t3) Exit");
            String userSelection = consoleReader.nextLine();

            switch (userSelection) {

                case "1":
                    System.out.println("Please provide us with some basic information to register your account");

                    System.out.print("First name: ");
                    String first = consoleReader.nextLine();

                    System.out.print("Last name: ");
                    String last = consoleReader.nextLine();

                    /*
                    The Scanner#nextInt does not read the new line character in out input created by hitting "Enter"
                    */

                    System.out.print("Age: ");
                    int age = consoleReader.nextInt();
                    consoleReader.nextLine(); // necessary after using nextInt, if we intend to gather more data from the input stream

                    /*
                    prematurely closing a Scanner that is reading fron the standard input steam will make the stdin inaccessaible for the
                    remainder of this applications exicution.
                    */
                    //consoleReader.close();
                    //consoleReader = new Scanner(System.in);

                    System.out.print("Email Address: ");
                    String email = consoleReader.nextLine();

                    System.out.print("Username: ");
                    String username = consoleReader.nextLine();

                    System.out.print("Password:");
                    String password = consoleReader.nextLine();

                    AppUser newUser = new AppUser(first, last, age, email, username, password);
                    
                    // printf id used to format Strings
                    System.out.printf("You provided the information: %s\n", newUser);
                    System.out.println("Hash code of newUser: " + newUser.hashCode());
                    break;
                
                case "2":
                    System.out.println("You selected login");
                    break;
                case "3":
                    System.out.println("You selected exit");
                    isRunning = false;
                    break;
                default:
                    System.out.println("You have an invalid selection.");
                    //numberOfTries++;
                    // mai(args); // recursive call (a method/function that invokes itself; creates a looping construct [an inefficient one is this context])
                
            }

        }

        
    }
}

class AppUser {

    // Instance feilds (values that are specific to and individual instance of this class [aka an object])
    // the "private" access modifier means that the member is not accessible outside of this class declaration
     private String firstName;
     private String lastName;
     private int age;
     private String email;
     private String username;
     private String password;
    
     // this is a constuctor, a special method used to instantiate an object
     // the "public" access modifier means that the member is accessible to all classes within the same application.
    public AppUser(String fn, String ln, int age, String email, String un, String pw) {
        firstName = fn;
        lastName = ln;
        this.age = age; // the this keyword helps to distinguish between the instance feild "age" and the argument/parameter "age"
        this.email = email;
        this.username = un; // leagal as well (in fact, you'll probably see this be the default )
        this.password = pw;
    }

    @Override
    public String toString() {
        return String.format("AppUser{firstName=%s,lastName=%s,ade=%d,email=%s,username=%s,password=%s}\n", firstName, lastName, age, email, username, password);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age <= 0) {
            System.err.println("Invalid age provided. No change made.");
        } else {
           this.age = age; 
        }
    }
        
}      
