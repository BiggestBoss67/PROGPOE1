import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {

    Login login = new Login();

    // --- assertTrue / assertFalse Tests (Logic) ---

    @Test
    public void testLoginSuccessful() {
        // Checking if the login logic returns TRUE for matching credentials
        // Using "kyl_1" and "Ch&&sec@ke99!" as the stored and entered data
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testLoginFailed() {
        // Checking if the login logic returns FALSE for non-matching credentials
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!", "wrongUser", "wrongPass");
        assertFalse(result);
    }

    // --- assertEquals Tests (String Messages) ---

    @Test
    public void testReturnLoginStatusSuccess() {
        // Testing the string returned when login is successful
        String expected = " Welcome Kyle Instance, it is great to see you again.";
        String actual = login.returnLoginStatus(true, "Kyle", "Instance");

        assertEquals(expected, actual);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        // Testing the string returned when login fails
        String expected = " Username or password incorrect, please try again.";
        String actual = login.returnLoginStatus(false, "Kyle", "Instance");

        assertEquals(expected, actual);
    }
}