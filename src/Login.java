import java.util.Scanner;

/** Author: Eberechukwu Osondo
 * Student number: ST10536068
 * Description:
 * our class does not contain a scanner because we want to separate our concerns.
 * this class will only contain the logic behind the login part of the program,
 * so that later we can also test this class.
 * you cant test a class that requires user input **/

public class Login {
//this class contains no private variables, all our variables are declared within methods.

    public void startLoginProcess(String storedUsername, String storedPassword,String fName, String lName){
        Scanner sc = new Scanner(System.in);
        // our variable starts off false until given a reason to be true. it's a flag
        boolean isLoggedIn = false;

        //this class will run continuously unless the user enters the same data they registered with

        while(!isLoggedIn) {

            System.out.println("\n--- LOGIN TO YOUR ACCOUNT ---");

            System.out.println("Enter username: ");
            String enteredUsername = sc.nextLine();

            System.out.println("Enter password: ");
            String enteredPassword = sc.nextLine();

            isLoggedIn = loginUser(storedUsername, storedPassword,enteredUsername, enteredPassword);
            String message = returnLoginStatus(isLoggedIn, fName, lName);
            System.out.println(message);

            if(!isLoggedIn) {
                System.out.println("Please try again.");
            }
        }
    }
    //this method uses stored data received from the user through the registration class
    public boolean loginUser(String storedUsername, String storedPassword, String enteredUsername, String enteredPassword) {

        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }


    public String returnLoginStatus(boolean isLoggedIn, String fName, String lName) {
        if (isLoggedIn) {
            return " Welcome " + fName + " " + lName + ", it is great to see you again.";
        }else {
            return " Username or password incorrect, please try again.";
        }
    }
}// there is no direct link between the login and registration class,
// call both classes inside the main class and then manually create that bridge.