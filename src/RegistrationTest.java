import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// 1. Rename the class to RegistrationTest so it doesn't clash with your main class
public class RegistrationTest {

    // This refers to your ACTUAL Registration class in your project
    private Registration registration;

    @BeforeEach
    void setUp() {
        // This creates a fresh instance of your logic class before every test
        registration = new Registration();
    }

    @Test
    void testUsernameCorrectlyFormatted() {
        // Tests the logic in your Registration class
        assertTrue(registration.checkUserName("kyl_1"));
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        assertFalse(registration.checkUserName("kyleishere"));
    }

    @Test
    void testPasswordComplexitySuccess() {
        assertTrue(registration.checkPasswordComplexity("Ch@tter01"));
    }

    @Test
    void testPasswordComplexityFailure() {
        assertFalse(registration.checkPasswordComplexity("password"));
    }

    @Test
    void testLoginSuccess() {
        // Registers a user first
        registration.registerUser("ky_1", "Ch@tter01", "+27123456789", "John", "Doe");

        // Verify login returns true
        boolean actual = registration.loginUser("ky_1", "Ch@tter01");
        assertTrue(actual, "Login should be successful with correct credentials");
    }

    @Test
    void testLoginFailure() {
        registration.registerUser("ky_1", "Ch@tter01", "+27123456789", "John", "Doe");

        // Verify login returns false for wrong password
        boolean actual = registration.loginUser("ky_1", "WrongPass123!");
        assertFalse(actual, "Login should fail with incorrect credentials");
    }
}