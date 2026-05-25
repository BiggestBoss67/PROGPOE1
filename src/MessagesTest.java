import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesTest {

    private Messages messageSystem;

    @BeforeEach
    public void setUp() {

        messageSystem = new Messages();
    }

    @Test
    public void testCheckMessageID_Valid() {

        assertTrue(messageSystem.checkMessageID(), "Message ID should be valid and under 10 characters.");
    }

    @Test
    public void testCheckRecipientCell_ValidSouthAfrican() {

        boolean result = messageSystem.checkRecipientCell("+27821234567");
        assertTrue(result, "Valid SA numbers should return true.");
    }

    @Test
    public void testCheckRecipientCell_InvalidFormat() {

        boolean result1 = messageSystem.checkRecipientCell("0821234567");
        boolean result2 = messageSystem.checkRecipientCell("+27821234"); // Too short

        assertFalse(result1, "Numbers without +27 should fail.");
        assertFalse(result2, "Short numbers should fail.");
    }

    @Test
    public void testCreateMessageHash() {

        String rawMessage = "   Hello beautiful world implementation   ";
        int msgNum = 5;

        String hash = messageSystem.createMessageHash(rawMessage, msgNum);

        String expectedStart = hash.substring(0, 2);


        String expectedPattern = expectedStart + ":" + msgNum + ":HELLO:IMPLEMENTATION";

        assertEquals(expectedPattern, hash, "Hash format or casing does not match expectation.");
    }

    @Test
    public void testSentMessage_Choices() {

        String sentResult = messageSystem.SentMessage(1);
        assertEquals("Message successfully sent", sentResult);

        String deleteResult = messageSystem.SentMessage(0);
        assertEquals("Press 0 to delete the message", deleteResult);


        String storedResult = messageSystem.SentMessage(2);
        assertEquals("Message successfully stored", storedResult);

        String invalidResult = messageSystem.SentMessage(99);
        assertEquals("Invalid Action", invalidResult);
    }

    @Test
    public void testReturnTotalMessages() {
        int initialCount = messageSystem.returnTotalMessages();

        messageSystem.SentMessage(1);

        assertEquals(initialCount + 1, messageSystem.returnTotalMessages(), "Total message count should increment by 1.");
    }
}