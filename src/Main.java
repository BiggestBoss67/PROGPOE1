void main() {
    // 1. Create instances of your logic classes
    Registration reg = new Registration();
    Login login = new Login();
    Scanner sc = new Scanner(System.in);

    IO.println("--- WELCOME TO REGISTRATION --- \n");

    // Collect Basic Info
    IO.print("Enter First Name: ");
    String fName = sc.nextLine();
    IO.print("Enter Last Name: ");
    String lName = sc.nextLine();

    // 2. Registration Loops (Username, Password, Cell)
    String username = "";
    boolean isUserValid = false;
    while (!isUserValid) {
        IO.print("\nCreate a username. Please ensure that your username contains ONE underscore and is no more than FIVE characters long.");
        IO.print("\nEnter your username: ");
        username = sc.nextLine();
        isUserValid = reg.checkUserName(username);
    }

    String password = "";
    boolean isPassValid = false;
    while (!isPassValid) {
        IO.print("\nCreate a password. Please ensure that your password contains at least EIGHT characters, ONE capital letter, ONE digit and ONE special character.  ");
        IO.print("\nEnter your password: ");
        password = sc.nextLine();
        isPassValid = reg.checkPasswordComplexity(password);
    }
    String number = "";
    boolean isCellValid = false;
    while (!isCellValid) {
        IO.print("\nEnter your SA cell phone number begin with(+27): ");
        number = sc.nextLine();
        isCellValid = reg.checkCellPhoneNumber(number);
    }
    // 3. Finalize Registration
    String regStatus = reg.registerUser(fName, lName, username, password, number);
    IO.println("\n" + regStatus);

    // 4. CALL THE LOGIN PROCESS
    // We pass the "stored" data from the 'reg' object into the 'login' object
    if (regStatus.contains("SUCCESSFUL")) {
        login.startLoginProcess(
                reg.getRegisteredUsername(),
                reg.getRegisteredPassword(),
                reg.getFirstName(),
                reg.getLastName()
        );
    }
}