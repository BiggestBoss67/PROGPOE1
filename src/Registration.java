import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    // This method now coordinates the registration using the values passed from Main
    public String registerUser(String fName, String lName, String username, String password, String cell) {
        this.firstName = fName;
        this.lastName = lName;

        // Call the checks and store results
        boolean isUsernameValid = checkUserName(username);
        boolean isPasswordValid = checkPasswordComplexity(password);
        boolean isCellValid = checkCellPhoneNumber(cell);

        if (isUsernameValid && isPasswordValid && isCellValid) {
            // This is the ONLY line that should print/return success
            return "--- REGISTRATION SUCCESSFUL! :)" +
                   "\nUsername: " + registeredUsername +
                    "\nPassword: "  + registeredPassword;
        } else {
            return "--- REGISTRATION FAILED!---" +
                    "\n Please ensure all fields are correct." +
                    "\n Please ensure that your username contains ONE underscore and is no more than FIVE characters long" +
                    "\n Ensure that your password contains at least EIGHT characters, ONE capital letter, ONE digit and ONE special character" +
                    "\n Ensure that your cellphone number begins with '+27' ";
        }
    }

    // Logic for Username
    public boolean checkUserName(String username) {

        if (username.length() <= 5 && username.contains("_")) {
            this.registeredUsername = username;

            return true;
        } else {
            System.out.println("\nERROR");
            return false;
        }
    }

    // Logic for Password
    public boolean checkPasswordComplexity(String password) {

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

            return true;
        } else {
            System.out.println("\nERROR");
            return false;
        }
    }

    // Logic for Cellphone
    public boolean checkCellPhoneNumber(String cellNumber) {
        String regex = "^\\+27[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);

        if (matcher.matches()) {

            return true;
        } else {
            System.out.println("\nERROR");
            return false;
        }
    }

    // Getters
    public String getRegisteredUsername() { return registeredUsername; }
    public String getRegisteredPassword() { return registeredPassword; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}