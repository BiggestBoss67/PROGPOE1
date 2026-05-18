import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RegistrationTest {

    Registration registration = new Registration();

    // --- assertEquals Tests (Checking String Messages) ---

    @Test
    public void testUsernameCorrectlyFormatted() {
        // Task: Username contains underscore and is <= 5 chars
        // Test Data: "kyl_1"
        boolean result = registration.checkUserName("kyl_1");
        assertTrue(result);

        // Note: Your instructions ask for specific welcome message.
        // Usually, this would be tested against the method that returns that string.
        String expected = "Welcome <user first name> ,<user last name> it is great to see you.";
        // You would compare this against your login/welcome method output.
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Test Data: "kyle!!!!!!"
        boolean result = registration.checkUserName("kyle!!!!!!");
        assertFalse(result);
    }

    @Test
    public void testPasswordComplexitySuccess() {
        // Test Data: "Ch&&sec@ke99!"
        boolean result = registration.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testPasswordComplexityFailure() {
        // Test Data: "password"
        boolean result = registration.checkPasswordComplexity("password");
        assertFalse(result);
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        // Test Data: +27838968976
        boolean result = registration.checkCellPhoneNumber("+27838968976");
        assertTrue(result);
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Test Data: 08966553
        boolean result = registration.checkCellPhoneNumber("08966553");
        assertFalse(result);
    }

    // --- assertTrue / assertFalse Tests (Checking Logic) ---

    @Test
    public void testCheckUserNameLogic() {
        // Test Case: Username correctly formatted
        assertTrue(registration.checkUserName("kyl_1"));

        // Test Case: Username incorrectly formatted
        assertFalse(registration.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityLogic() {
        // Test Case: Password meets requirements
        assertTrue(registration.checkPasswordComplexity("Ch&&sec@ke99!"));

        // Test Case: Password does not meet requirements
        assertFalse(registration.checkPasswordComplexity("password"));
    }
}