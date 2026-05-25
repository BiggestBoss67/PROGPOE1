import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the Messages class.
 * Author: Eberechukwu Osondo
 * Student number: ST10536068
 */
public class MessagesTest {

    private Messages messageSystem;

    @BeforeEach
    public void setUp() {
        // Initializes a new Messages object before each test to ensure a clean state
        messageSystem = new Messages();
    }

    @Test
    public void testCheckMessageID() {
        // Act & Assert
        // Verifies that the automatically generated random ID is valid and exactly 10 characters long
        assertTrue(messageSystem.checkMessageID(), "Message ID should be valid and up to 10 characters.");
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        // Arrange
        String validSouthAfricanNumber = "+27831234567";

        // Act
        boolean isValid = messageSystem.checkRecipientCell(validSouthAfricanNumber);

        // Assert
        assertTrue(isValid, "The cellphone number format should be valid for South Africa (+27 followed by 9 digits).");
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        // Arrange
        String invalidNumber = "0831234567"; // Missing +27 prefix

        // Act
        boolean isValid = messageSystem.checkRecipientCell(invalidNumber);

        // Assert
        assertFalse(isValid, "The cellphone number should fail validation if it doesn't match the regex pattern.");
    }

    @Test
    public void testCreateMessageHash() {
        // Arrange
        String sampleMessage = "  Hello world this is a test message  ";
        int messageNumber = 5;

        // Act
        String generatedHash = messageSystem.createMessageHash(sampleMessage, messageNumber);

        // Assert
        // The hash should look like: [FIRST_2_DIGITS_OF_ID]:5:HELLO:MESSAGE
        assertNotNull(generatedHash, "Hash should not be null.");
        assertTrue(generatedHash.contains(":5:"), "Hash should contain the correct message number.");
        assertTrue(generatedHash.endsWith(":MESSAGE"), "Hash should end with the uppercase version of the last word.");
        assertTrue(generatedHash.contains(":HELLO:"), "Hash should contain the uppercase version of the first word.");
    }

    @Test
    public void testSentMessage_Success() {
        // Act
        String result = messageSystem.SentMessage(1);

        // Assert
        assertEquals("Message successfully sent", result);
        assertEquals(1, messageSystem.returnTotalMessages(), " Total messages counter should increment by 1.");
    }

    @Test
    public void testSentMessage_Delete() {
        // Act
        String result = messageSystem.SentMessage(0);

        // Assert
        assertEquals("Press 0 to delete the message", result);
    }

    @Test
    public void testSentMessage_Store() {
        // Act
        String result = messageSystem.SentMessage(2);

        // Assert
        assertEquals("Message successfully stored", result);
    }

    @Test
    public void testSentMessage_Invalid() {
        // Act
        String result = messageSystem.SentMessage(99); // Testing an unhandled choice number

        // Assert
        assertEquals("Invalid Action", result);
    }

    @Test
    public void testPrintMessages() {
        // Arrange
        String cell = "+27831234567";
        String body = "Hello World";

        // Setup state variables inside messageSystem
        messageSystem.checkRecipientCell(cell);
        messageSystem.createMessageHash(body, 1);

        // Act
        String report = messageSystem.printMessages();

        // Assert
        assertTrue(report.contains("Recipient: " + cell), "Printout should contain the correct recipient cell.");
        assertTrue(report.contains("Message: " + body), "Printout should contain the correct message body.");
        assertTrue(report.contains("Message ID: "), "Printout should contain the generated Message ID.");
    }
}