package chatapplication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ChatApplicationTest {
    private final ChatApplication chatApp = new ChatApplication();

    @Test
    public void testCheckUsername() {
        // Corre format
        assertTrue(chatApp.checkUsername("kyl_1"));
        assertTrue(chatApp.checkUsername("a_b"));
        
        // Incorr format
        assertFalse(chatApp.checkUsername("kyle!!!!!!!"));
        assertFalse(chatApp.checkUsername("kyle"));
        assertFalse(chatApp.checkUsername("ky le"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        // Corr passwords
        assertTrue(chatApp.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(chatApp.checkPasswordComplexity("A1@bcdefg"));
        
        // Incorr passwords
        assertFalse(chatApp.checkPasswordComplexity("password"));  // No capital letters, numbers or special chars
        assertFalse(chatApp.checkPasswordComplexity("Password"));  // No numbers or special chars
        assertFalse(chatApp.checkPasswordComplexity("P@ssw0r"));   // Too short
        assertFalse(chatApp.checkPasswordComplexity("password123")); // No caps or special chars
    }

    @Test
    public void testCheckCellPhoneNumber() {
        // Corr formats
        assertTrue(chatApp.checkCellPhoneNumber("+27831234567"));
        assertTrue(chatApp.checkCellPhoneNumber("+1234567890"));
        
        // Incorr formats
        assertFalse(chatApp.checkCellPhoneNumber("08966553"));     // No international code
        assertFalse(chatApp.checkCellPhoneNumber("+123456"));      // Too short
        assertFalse(chatApp.checkCellPhoneNumber("27831234567"));  // Missing +
        assertFalse(chatApp.checkCellPhoneNumber("+abc1234567")); // Contains letters
    }

    @Test
    public void testLoginUser() {
        chatApp.username = "test_1";
        chatApp.password = "P@ssw0rd123";
        
        // Successful login
        assertTrue(chatApp.loginUser("test_1", "P@ssw0rd123"));
        
        // Failed logins
        assertFalse(chatApp.loginUser("wrong", "P@ssw0rd123"));
        assertFalse(chatApp.loginUser("test_1", "wrong"));
        assertFalse(chatApp.loginUser("wrong", "wrong"));
    }

    @Test
    public void testReturnLoginStatus() {
        chatApp.firstName = "John";
        chatApp.lastName = "Doe";
        
        // Successful login text
        assertEquals("Welcome John, Doe it is great to see you again.", 
                    chatApp.returnLoginStatus(true));
        
        // Failed login text
        assertEquals("Username or password incorrect, please try again.", 
                    chatApp.returnLoginStatus(false));
    }
}
