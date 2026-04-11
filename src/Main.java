import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Create instances of your classes
        Registration reg = new Registration();
        Login login = new Login();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- WELCOME TO REGISTRATION --- \n");

        // 2. Ask for name details to pass to registerUser
        System.out.print("Enter First Name: ");
        String fName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lName = sc.nextLine();

        // 3. Start Registration
        // This will trigger your username, password, and cell phone checks
        String regStatus = reg.registerUser(fName, lName);
        System.out.println(regStatus);

        // 4. Start Login
        // We pull the saved data from 'reg' and pass it to 'login'
        login.startLoginProcess(
                reg.getRegisteredUsername(),
                reg.getRegisteredPassword(),
                reg.getFirstName(),
                reg.getLastName()
        );
    }
}