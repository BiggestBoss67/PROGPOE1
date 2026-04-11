import java.util.Scanner;

public class Login {

    public void startLoginProcess(String storedUsername, String storedPassword,String fName, String lName){
        Scanner sc = new Scanner(System.in);
        boolean isLoggedIn = false;

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
}