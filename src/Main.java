void main() {
    /** Author: Eberechukwu Osondo
     * Student number: ST10536068
     *
     * Reference list:
     * Youtu.be. (2026). Available at: https://youtu.be/RAthlOQUMkc?si=pjr3rLybAJ2C9nn8 [Accessed 11 Apr. 2026].
     * in (2022). How to user input with condition in java? [online] Stack Overflow. Available at: https://share.google/T4ky3aDGBoAy3wvYd [Accessed 11 Apr. 2026].
     * Tutorialspoint.com. (2019). Program to check valid mobile number using Java regular expressions. [online] Available at: https://www.tutorialspoint.com/article/program-to-check-valid-mobile-number-using-java-regular-expressions [Accessed 12 Apr. 2026].
     * www.w3schools.com. (n.d.). Java Inner Class (Nested Class). [online] Available at: https://www.w3schools.com/java/java_inner_classes.asp.
     *
     * Description:
     * Our class contains a scanner because we want to separate our concerns.
     * this class is the main class, and is the bridge between the login and registration class
     * this class handles communication between the user as well
     * this class cant be tested as it requires user input.**/

    // 1. Created instances of my logic classes
    Registration reg = new Registration();
    Login login = new Login();

    Scanner sc = new Scanner(System.in);

    System.out.println("--- WELCOME TO REGISTRATION --- \n");

    // Collect Basic Info
    System.out.println("Enter First Name: ");
    String fName = sc.nextLine();
    System.out.println("Enter Last Name: ");
    String lName = sc.nextLine();

    // 2. Registration Loops (Username, Password, Cell)
    String username = "";
    boolean isUserValid = false;
    while (!isUserValid) {
        System.out.println("\nCreate a username. Please ensure that your username contains ONE underscore and is no more than FIVE characters long.");
        System.out.println("\nEnter your username: ");
        username = sc.nextLine();
        isUserValid = reg.checkUserName(username);
    }

    String password = "";
    boolean isPassValid = false;
    while (!isPassValid) {
        System.out.println("\nCreate a password. Please ensure that your password contains at least EIGHT characters, ONE capital letter, ONE digit and ONE special character.  ");
        System.out.println("\nEnter your password: ");
        password = sc.nextLine();
        isPassValid = reg.checkPasswordComplexity(password);
    }
    String number = "";
    boolean isCellValid = false;
    while (!isCellValid) {
        System.out.println("\nEnter your SA cell phone number begin with(+27): ");
        number = sc.nextLine();
        isCellValid = reg.checkCellPhoneNumber(number);
    }
    // 3. Finalize Registration
    String regStatus = reg.registerUser(fName, lName, username, password, number);
    System.out.println("\n" + regStatus);

    // 4. CALL THE LOGIN PROCESS
    // If the registration was successful, grab the official username, password,
    // and name from the registration records and start the login process using that information.

    if (regStatus.contains("SUCCESSFUL")) {
        login.startLoginProcess(reg.getRegisteredUsername(), reg.getRegisteredPassword(), reg.getFirstName(), reg.getLastName() );
    }
}