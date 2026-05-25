
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messages {
    private static int numMessages = 0;

    private String messageId;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    // CORRECTED: Turned into a proper Constructor matching the Class Name
    public Messages() {
        Random rand = new Random();
        // Generates a random 10-digit number string
        long id = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        this.messageId = String.valueOf(id);
    }

    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }

    public Boolean checkRecipientCell(String cellNumber) {
        //we used regular expressions and the matches tool to ensure that the cellphone number matches the format of a South African number
        String regex = "^\\+27[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public String createMessageHash(String rawMessage, int msgNum) {
        this.messageText = rawMessage;
        this.messageNumber = msgNum;

        String firstTwoDigits = this.messageId.substring(0, 2);

        String trimmed = rawMessage.trim();
        String[] words = trimmed.split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String rawHash = firstTwoDigits + ":" + msgNum + ":" + firstWord + ":" + lastWord;
        this.messageHash = rawHash.toUpperCase();

        return this.messageHash;
    }

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
//coming soon
    }
}