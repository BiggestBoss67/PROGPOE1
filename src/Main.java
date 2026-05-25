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
    if (regStatus.contains("SUCCESSFUL")) {
        
        // Executes the continuous loop until the user successfully signs in
        login.startLoginProcess(
                reg.getRegisteredUsername(),
                reg.getRegisteredPassword(),
                reg.getFirstName(),
                reg.getLastName()
        );

        // Since startLoginProcess only finishes when successfully logged in:
        System.out.println("\nWelcome to QuickChat.");

        System.out.print("\nHow many messages do you wish to enter? ");
        int totalMessages = sc.nextInt();
        sc.nextLine(); // Clear the scanner buffer

        int sentMessages = 0;
        boolean chatting = true;

        while (chatting) {
            System.out.println("\nPlease choose an option from the menu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter either 1, 2 or 3: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear the scanner buffer

            if (choice == 1) {
                if (sentMessages >= totalMessages) {
                    System.out.println("You have reached your limit of " + totalMessages + " messages.");
                } else {
                    System.out.println("\n--- New Message ---");

                    System.out.print("Enter recipient cell number. Begin with (+27): ");
                    String cellInput = sc.nextLine();

                    Messages activeMsg = new Messages();
                    Boolean cellStatus = activeMsg.checkRecipientCell(cellInput);

                    if (cellStatus.equals(false)) {
                        System.out.println("Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.  ");
                    } else {
                        System.out.print("Enter your message: ");
                        String textInput = sc.nextLine();

                        if (textInput.length() > 250) {
                            int x = textInput.length() - 250;
                            System.out.println("message exceeds 250 characters by " + x + ", Please reduce the size");
                        } else {
                            sentMessages++;
                            System.out.println("Message ready to send");

                            activeMsg.createMessageHash(textInput, sentMessages);

                            System.out.println("\n Select what you would like to do next: ");
                            System.out.println("1 - Send Message");
                            System.out.println("0 - Disregard Message");
                            System.out.println("2 - Store Message to send later");
                            System.out.print("Select 0, 1 or 2 ");
                            int messagePath = sc.nextInt();
                            sc.nextLine(); // Clear scanner buffer

                            String actionReceipt = activeMsg.SentMessage(messagePath);

                            if (messagePath == 1) {
                                System.out.println(actionReceipt); 
                                System.out.println("\n=== MESSAGE DETAILS ===");
                                System.out.println(activeMsg.printMessages());
                            } else if (messagePath == 0) {
                                System.out.println(actionReceipt); 
                            } else if (messagePath == 2) {
                                System.out.println(actionReceipt); 
                                activeMsg.storeMessage();
                            } else {
                                System.out.println("Invalid selection.");
                            }
                        }
                    }
                }
            } else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                System.out.println("Quitting application...");
                chatting = false;
            } else {
                System.out.println("Invalid option. Please select 1, 2, or 3.");
            }
        }

        Messages summaryCheck = new Messages();
        System.out.println("\n==========================================");
        System.out.println("Total successful messages processed across session: " + summaryCheck.returnTotalMessages());
        System.out.println("==========================================");
    }
}