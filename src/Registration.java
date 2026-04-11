import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    public String registerUser(String fName, String lName) {
        this.firstName = fName;
        this.lastName = lName;
        checkUserName();
        checkPasswordComplexity();
        checkCellPhoneNumber();
        return "--- REGISTRATION SUCCESSFUL! :) ";
    }

    public boolean checkUserName() {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;

        System.out.println("\n Create a username and ensure that it contains ONE underscore and is no more than FIVE characters long.");

        while (!valid) {
            System.out.print("\n Enter your username: ");
            String username = sc.nextLine();

            if (username.length() == 5 && username.contains("_")) {
                this.registeredUsername = username;
                System.out.println("Username successfully captured!\n");
                valid = true;
            } else {
                System.out.println("Username is not correctly formatted, please ensure that your username contains ONE underscore and is no more than FIVE characters long.\n");
            }
        }
        return true;
    }

    public boolean checkPasswordComplexity() {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;

        System.out.print("Create a password that is at least EIGHT characters long. ensure that it contains ONE capital letter, ONE digit and ONE special character. \n");

        while (!valid) {
            System.out.print("\n Enter your password: ");
            String password = sc.nextLine();

            boolean hasUpper = false;
            boolean hasDigit = false;
            boolean hasSpecial = false;

            if (password.length() >= 8) {
                for (char c : password.toCharArray()) {
                    if (Character.isUpperCase(c)) hasUpper = true;
                    if (Character.isDigit(c)) hasDigit = true;
                    if (!Character.isLetterOrDigit(c)) hasSpecial = true;
                }
            }

            if (hasUpper && hasDigit && hasSpecial) {
                this.registeredPassword = password;
                System.out.print("Password successfully captured!\n");
                valid = true;
            } else {
                System.out.print("Password is not correctly formatted, please ensure that your password contains at least EIGHT characters, ONE capital letter, ONE digit and ONE special character.\n");
            }
        }
        return true;
    }

    public boolean checkCellPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String regex = "^\\+27[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);

        while (!valid) {
            System.out.print("\n Enter your SA cell phone number, begin with (+27): ");
            String cellNumber = sc.nextLine();

            Matcher matcher = pattern.matcher(cellNumber);

            if (matcher.matches()) {
                System.out.println("Cellphone number successfully added! \n");
                valid = true;
            } else {
                System.out.println("Cellphone number incorrectly formatted or does not contain international code. Try again: \n");
            }
        }
        return true;
    }

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}