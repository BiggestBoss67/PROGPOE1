import java.util.regex.Matcher;
import java.util.regex.Pattern;
// imported the above java tools to implement our logic for our cellphone number method//

/**our class does not contain a scanner because we want to separate our concerns.
//this class will only contain the logic behind the registration part of the program,
 so that later we can also test this class.
//you cant test a class that requires user input **/

public class Registration {

    //the following variables can only be used within this class because we have made them private however we can still use them in different methods within this class like the "registerUser" method//
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    // This method coordinates the registration using the values passed from Main
    public String registerUser(String fName, String lName, String username, String password, String cell) {
        //we are assigning the name and surname entered by users to the class variables so that they are not forgotten by our program
        this.firstName = fName;
        this.lastName = lName;

        // these variables only exist within this method, these are boolean flags, we've used them to check whether
        // the username, password and cellphone number meet the specified criteria

        boolean isUsernameValid = checkUserName(username);
        boolean isPasswordValid = checkPasswordComplexity(password);
        boolean isCellValid = checkCellPhoneNumber(cell);

        //only if all those boolean flags are true, will the success message be printed.
        // this was made possible using the Logical AND operator

        if (isUsernameValid && isPasswordValid && isCellValid) {

            return "--- REGISTRATION SUCCESSFUL! :)" +
                   "\nUsername: " + registeredUsername +
                    "\nPassword: "  + registeredPassword;
        // if the boolean flags return false, the following will be printed instead
        } else {
            return "--- REGISTRATION FAILED!---" +
                    "\n Please ensure all fields are correct." +
                    "\n Please ensure that your username contains ONE underscore and is no more than FIVE characters long" +
                    "\n Ensure that your password contains at least EIGHT characters, ONE capital letter, ONE digit and ONE special character" +
                    "\n Ensure that your cellphone number begins with '+27' ";
        }
    }
//the following three methods will have to be called in the main class in order for the program to run successfully

    // Logic for Username... checks if the username is = 5 or less than 5 and checks if it contains an underscore

    public boolean checkUserName(String username) {

        if (username.length() <= 5 && username.contains("_")) {
            this.registeredUsername = username;

            return true;
        } else {
            System.out.println("\nERROR");
            return false;
        }
    }

    // Logic for Password...checks if password contains 8+ characters, one digit,special character and uppercase letter.

    public boolean checkPasswordComplexity(String password) {
// Pessimistic (to assume the condition has  not been met until there's proof that it has.), that is the reason we initialized the variables to be false.
// they act as a checklist. only true once it has been identified otherwise it is false
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        if (password.length() >= 8) {
            for (char c : password.toCharArray()) { //this creates an array out of the password the user inputs so that each character can be checked.

                //we're using built in java tools
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

    // Logic for Cellphone...begin with +27
    public boolean checkCellPhoneNumber(String cellNumber) {
        //we used regular expressions and the matches tool to ensure that the cellphone number matches the format of a South African number
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

    // Getters: allows other classes read the values of our private variables without tampering with them
    public String getRegisteredUsername() { return registeredUsername; }
    public String getRegisteredPassword() { return registeredPassword; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}