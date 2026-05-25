/** Author: Eberechukwu Osondo
 * Student number: ST10536068

 * Reference list:
 * Youtu.be. (2026). Available at: https://youtu.be/RAthlOQUMkc?si=pjr3rLybAJ2C9nn8 [Accessed 11 Apr. 2026].
 * in (2022). How to user input with condition in java? [online] Stack Overflow. Available at: https://share.google/T4ky3aDGBoAy3wvYd [Accessed 11 Apr. 2026].
 * Tutorialspoint.com. (2019). Program to check valid mobile number using Java regular expressions. [online] Available at: https://www.tutorialspoint.com/article/program-to-check-valid-mobile-number-using-java-regular-expressions [Accessed 12 Apr. 2026].
 * www.w3schools.com. (n.d.). Java Inner Class (Nested Class). [online] Available at: https://www.w3schools.com/java/java_inner_classes.asp.

 * Description:
 * Our class contains a scanner because we want to separate our concerns.
 * this class is the main class, and is the bridge between the login and registration class
 * this class handles communication between the user as well
 * this class cant be tested as it requires user input.**/
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messages {
    private static int numMessages = 0;

    private final String messageId;
    private String recipientCell;
    private String messageText;
    private String messageHash;


    // this method is what will generate the random 10 digit number
    public Messages() {
        Random rand = new Random();
        // Generates a random 10-digit number string
        long id = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        //integers are converted into a string
        this.messageId = String.valueOf(id);
    }
    //this method ensures that the message ID does not exceed 10 chars
    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }
    //this method verifies the validity of the cellphone number
    public Boolean checkRecipientCell(String cellNumber) {
        //we used regular expressions and the matches tool to ensure that the cellphone number matches the format of a South African number
        String regex = "^\\+27[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);

        if (matcher.matches()) {
            this.recipientCell = cellNumber;
            return true;
        } else {
            return false;
        }
    }
    //a method to build the message hash by joining parts of the message and its number to create a code.
    public String createMessageHash(String rawMessage, int msgNum) {
        this.messageText = rawMessage;


        String firstTwoDigits = this.messageId.substring(0, 2);

        String trimmed = rawMessage.trim();
        String[] words = trimmed.split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String rawHash = firstTwoDigits + ":" + msgNum + ":" + firstWord + ":" + lastWord;
        this.messageHash = rawHash.toUpperCase();

        return this.messageHash;
    }
    // this method asks the user to choose their next moves after typing their message
    public String SentMessage(int choice) {
        if (choice == 1) {
            numMessages++;
            return "Message successfully sent";
        } else if (choice == 0) {
            return "Press 0 to delete the message";
        } else if (choice == 2) {
            return "Message successfully stored";
        }
        return "Invalid Action";
    }

    public String printMessages() {
        return "Message ID: " + this.messageId + "\n" +
                "Message Hash: " + this.messageHash + "\n" +
                "Recipient: " + this.recipientCell + "\n" +
                "Message: " + this.messageText;
    }

    public int returnTotalMessages() {
        return numMessages;
    }

    public void storeMessage() {
        // Safely handle special characters in the text for JSON formatting
        String safeText = (this.messageText != null) ? this.messageText.replace("\"", "\\\"") : "";

        // Constructing the JSON string manually to keep the code dependency-free
        String jsonOutput = "{\n" +
                "  \"messageId\": \"" + this.messageId + "\",\n" +
                "  \"recipientCell\": \"" + this.recipientCell + "\",\n" +
                "  \"messageText\": \" " + safeText + "\",\n" +
                "  \"messageHash\": \"" + this.messageHash + "\"\n" +
                "}";

        // Writing the JSON block directly to a local file
        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonOutput + "\n");
            System.out.println("What would you like to do now?.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the message: " + e.getMessage());
        }
    }
}