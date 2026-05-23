import java.util.Scanner;

public class Messages {

    public void chatApp() {
        Scanner sc = new Scanner(System.in);

        System.out.println(" Welcome to QuickChat. ");
        System.out.println("What do you wish to do?");
        System.out.println("option 1) Send messages ");
        System.out.println("option 2) Show recently sent messages *Coming soon*");
        System.out.println("option 3) Quit");

        int option = sc.nextInt();

        if (option == 1) {
            System.out.println("Opening message sender...");
            System.out.println("How many messages do you wish to send?");
            int numMessages = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= numMessages; i++) {
                System.out.print("Enter message" + i + ": ");
                String message = sc.nextLine();

                System.out.println("SENT: " + message);
            }
            System.out.println("All " + numMessages + " messages sent successfully!");

        } else if (option == 2) {
            System.out.println("This feature is coming soon!");

        } else if (option == 3) {
            System.out.println("Quitting...");

        } else {
            System.out.println("Invalid option selected.");
        }
    }
}
